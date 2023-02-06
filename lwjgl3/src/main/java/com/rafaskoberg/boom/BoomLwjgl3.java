package com.rafaskoberg.boom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl3.audio.OpenALLwjgl3Audio;
import com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectIntMap.Entries;
import com.badlogic.gdx.utils.ObjectIntMap.Entry;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.rafaskoberg.boom.filter.BoomFilterLwjgl3;
import com.rafaskoberg.boom.util.BoomError;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.EXTEfx;
import org.lwjgl.openal.SOFTHRTF;

import java.nio.IntBuffer;

import static org.lwjgl.openal.EXTEfx.*;

public class BoomLwjgl3 extends Boom {
    private static BoomLwjgl3 instance;
    private static Field f_soundIdToSource;
    private static Field f_device;

    static {
        try {
            f_soundIdToSource = ClassReflection.getDeclaredField(OpenALLwjgl3Audio.class, "soundIdToSource");
            f_soundIdToSource.setAccessible(true);
            f_device = ClassReflection.getDeclaredField(OpenALLwjgl3Audio.class, "device");
            f_device.setAccessible(true);
        } catch(ReflectionException e) {
            e.printStackTrace();
        }
    }

    static BoomLwjgl3 getInstance() {
        return instance;
    }

    private final IntMap<BoomChannel> channelsById;
    private final ObjectIntMap<Music> musicChannels;
    final int maxAuxiliarySends;

    public BoomLwjgl3() {
        // Singleton
        instance = this;

        // Instantiate members
        channelsById = new IntMap<>();
        musicChannels = new ObjectIntMap<>();

        // Fetch max aux sends
        maxAuxiliarySends = fetchMaxAuxiliarySends();

        // Register application listener
        Timer.schedule(new Task() {
            @Override
            public void run() {
                update();
            }
        }, 0, 1 / 60f, -1);
    }

    private static int fetchMaxAuxiliarySends() {
        final int desiredAmount = 16;
        final int fallbackAmount = 2;

        // Get device handle
        long deviceHandle = getDeviceHandle(); // Will be 0 in case of errors

        // Prepare context attributes
        final IntBuffer contextAttributes = BufferUtils.newIntBuffer(10);
        contextAttributes.put(ALC_MAX_AUXILIARY_SENDS);
        contextAttributes.put(desiredAmount); // Maximum depends on the hardware
        contextAttributes.put(0); // No one knows what this zero is for, yet no one is brave enough to take it away
        contextAttributes.flip();

        // Reset the audio device with the new attributes
        if(!SOFTHRTF.alcResetDeviceSOFT(deviceHandle, contextAttributes)) {
            Gdx.app.error("Boom", String.format("Couldn't reset device with new effect slot limit attributes. deviceHandle: %d, error: %d", deviceHandle, ALC10.alcGetError(deviceHandle)));
            return fallbackAmount;
        }

        // Check how many effect slots we got
        final IntBuffer auxSends = BufferUtils.newIntBuffer(1);
        ALC10.alcGetIntegerv(deviceHandle, EXTEfx.ALC_MAX_AUXILIARY_SENDS, auxSends);
        return auxSends.get(0);
    }

    private void update() {
        // Update music instances being played by Boom
        Entries<Music> it = musicChannels.iterator();
        while(it.hasNext) {
            Entry<Music> entry = it.next();
            OpenALMusic music = (OpenALMusic) entry.key;

            // If music is no longer loaded, remove it from array
            int sourceId = music.getSourceId();
            if(sourceId == -1) {
                it.remove();
                continue;
            }

            // Update channel effects
            int id = entry.value;
            applyChannelToSourceId(sourceId, (BoomChannelLwjgl3) getChannel(id));
        }
    }

    @Override
    public BoomChannel createChannel(int id) {
        if(channelsById.containsKey(id)) {
            throw new IllegalStateException("Failed to create channel. There is already a channel registered to this id: " + id);
        }

        // Create channel
        BoomChannel channel = new BoomChannelLwjgl3(id);
        channelsById.put(id, channel);

        // Return channel
        return channel;
    }

    @Override
    public BoomChannel getChannel(int id) {
        return channelsById.get(id, null);
    }

    @Override
    public long play(Sound sound, BoomChannel channel, float volume, float pitch, float pan) {
        // Sanitize parameters
        volume = MathUtils.clamp(volume, 0, 1);
        pitch = MathUtils.clamp(pitch, 0.05f, 10f);
        pan = MathUtils.clamp(pan, -1f, 1f);

        // Play
        long soundId = sound == null ? -1 : sound.play(volume, pitch, pan);
        int sourceId = getSourceId(soundId);
        if(sourceId != -1) {
            if(!applyChannelToSourceId(sourceId, (BoomChannelLwjgl3) channel)) {
                return -1;
            }
        }
        return soundId;
    }

    @Override
    public long loop(Sound sound, BoomChannel channel, float volume, float pitch, float pan) {
        // Sanitize parameters
        volume = MathUtils.clamp(volume, 0, 1);
        pitch = MathUtils.clamp(pitch, 0.05f, 10f);
        pan = MathUtils.clamp(pan, -1f, 1f);

        // Play
        long soundId = sound == null ? -1 : sound.loop(volume, pitch, pan);
        int sourceId = getSourceId(soundId);
        if(sourceId != -1) {
            if(!applyChannelToSourceId(sourceId, (BoomChannelLwjgl3) channel)) {
                return -1;
            }
        }
        return soundId;
    }

    @Override
    public void play(Music music, BoomChannel channel) {
        if(music != null) {
            music.play();
            changeMusicChannel(music, channel);
        }
    }

    @Override
    public void changeMusicChannel(Music music, BoomChannel channel) {
        if(music != null && music.isPlaying()) {
            int sourceId = ((OpenALMusic) music).getSourceId();
            if(sourceId != -1) {
                musicChannels.put(music, channel == null ? -1 : channel.getId());
                applyChannelToSourceId(sourceId, (BoomChannelLwjgl3) channel);
            }
        }
    }

    @Override
    public void updateSoundToChannel(long soundId, BoomChannel channel) {
        int sourceId = getSourceId(soundId);
        if(sourceId != -1) {
            applyChannelToSourceId(sourceId, (BoomChannelLwjgl3) channel);
        }
    }

    private boolean applyChannelToSourceId(int sourceId, BoomChannelLwjgl3 channel) {
        if(sourceId != -1) {

            // If channel is null, reset audio source
            if(channel == null) {
                AL10.alSourcei(sourceId, AL_DIRECT_FILTER, AL_FILTER_NULL);
                for(int i = 0; i < maxAuxiliarySends; i++) {
                    AL11.alSource3i(sourceId, AL_AUXILIARY_SEND_FILTER, AL_EFFECTSLOT_NULL, i, AL_FILTER_NULL);
                }

                // Check for errors
                if(BoomError.check("Error while resetting an audio source with no channel")) {
                    return false;
                }
            }

            // Apply channel effects
            if(channel != null) {
                channel.apply(sourceId);
                Array<BoomEffectLwjgl3> effects = channel.getEffects();
                for(int i = 0; i < maxAuxiliarySends; i++) {
                    BoomEffectLwjgl3 effect = i < effects.size ? effects.get(i) : null;
                    BoomFilterLwjgl3 filter = effect != null ? (BoomFilterLwjgl3) effect.getFilter() : null;
                    int alAuxSlot = effect != null ? effect.alAuxSlot : AL_EFFECTSLOT_NULL;
                    int alFilter = filter != null ? filter.getAlFilter() : AL_FILTER_NULL;
                    AL11.alSource3i(sourceId, AL_AUXILIARY_SEND_FILTER, alAuxSlot, i, alFilter);
                }

                // Check for errors
                if(BoomError.check("Error while applying channel effects to an an audio source")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getSourceId(long soundId) {
        try {
            LongMap<Integer> soundIdToSource = (LongMap<Integer>) f_soundIdToSource.get(Gdx.audio);
            Integer result = soundIdToSource.get(soundId);
            if(result != null) {
                return result;
            }
        } catch(ReflectionException e) {
            Gdx.app.error("Boom", "Error while getting the sourceId from soundId " + soundId + " via reflection.", e);
        }
        return -1;
    }

    private static long getDeviceHandle() {
        try {
            return (long) f_device.get(Gdx.audio);
        } catch(ReflectionException e) {
            Gdx.app.error("Boom", "Error while getting the device handle via reflection.", e);
        }
        return -1;
    }

    @Override
    public int getMaxEffectsPerChannel() {
        return maxAuxiliarySends;
    }
}