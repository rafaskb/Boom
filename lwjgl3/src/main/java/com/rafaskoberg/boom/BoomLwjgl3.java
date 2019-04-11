package com.rafaskoberg.boom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl3.audio.OpenALAudio;
import com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic;
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
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.EXTEfx;

import static org.lwjgl.openal.EXTEfx.AL_DIRECT_FILTER;

public class BoomLwjgl3 extends Boom {
    private static Field f_soundIdToSource;

    static {
        try {
            f_soundIdToSource = ClassReflection.getDeclaredField(OpenALAudio.class, "soundIdToSource");
            f_soundIdToSource.setAccessible(true);
        } catch(ReflectionException e) {
            e.printStackTrace();
        }
    }

    private IntMap<BoomChannel> channelsByIndex;
    private ObjectIntMap<Music> musicChannels;

    public BoomLwjgl3() {
        channelsByIndex = new IntMap<>();
        musicChannels = new ObjectIntMap<>();

        // Register application listener
        Timer.schedule(new Task() {
            @Override
            public void run() {
                update();
            }
        }, 0, 1 / 60f, -1);
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
            postPlay(sourceId, (BoomChannelLwjgl3) getChannel(id));
        }
    }

    @Override
    public BoomChannel createChannel(int index) {
        if(channelsByIndex.containsKey(index)) {
            throw new IllegalStateException("Failed to create channel. There is already a channel registered to this index: " + index);
        }

        // Create channel
        BoomChannel channel = new BoomChannelLwjgl3(index);
        channelsByIndex.put(index, channel);

        // Return channel
        return channel;
    }

    @Override
    public BoomChannel getChannel(int index) {
        return channelsByIndex.get(index, null);
    }

    @Override
    public void play(Sound sound, BoomChannel channel, float volume, float pitch, float pan) {
        long soundId = sound.play(volume, pitch, pan);
        int sourceId = getSourceId(soundId);
        if(sourceId != -1) {
            postPlay(sourceId, (BoomChannelLwjgl3) channel);
        }
    }

    @Override
    public void play(Music music, BoomChannel channel) {
        music.play();
        changeMusicChannel(music, channel);
    }

    @Override
    public void changeMusicChannel(Music music, BoomChannel channel) {
        if(music.isPlaying()) {
            int sourceId = ((OpenALMusic) music).getSourceId();
            if(sourceId != -1) {
                musicChannels.put(music, channel == null ? -1 : channel.getIndex());
                postPlay(sourceId, (BoomChannelLwjgl3) channel);
            }
        }
    }

    private void postPlay(int sourceId, BoomChannelLwjgl3 channel) {
        if(sourceId != -1) {

            // Apply channel effect
            int alAuxSlot = channel == null ? EXTEfx.AL_EFFECTSLOT_NULL : channel.getAlAuxSlot();
            if(channel != null) {
                channel.apply(sourceId);
            } else {
                // Reset channel gain
                AL10.alSourcei(sourceId, AL_DIRECT_FILTER, EXTEfx.AL_FILTER_NULL);
            }
            AL11.alSource3i(sourceId, EXTEfx.AL_AUXILIARY_SEND_FILTER, alAuxSlot, 0, EXTEfx.AL_FILTER_NULL);
            BoomLwjgl3.checkAlError();
        }
    }

    private static Integer getSourceId(long soundId) {
        try {
            LongMap<Integer> soundIdToSource = (LongMap<Integer>) f_soundIdToSource.get(Gdx.audio);
            return soundIdToSource.get(soundId);
        } catch(ReflectionException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void checkAlError() {
        int error = AL10.alGetError();
        if(error == AL10.AL_NO_ERROR) {
            return;
        }

        if(error == AL10.AL_INVALID_NAME) throw new IllegalStateException("AL_INVALID_NAME");
        else if(error == AL10.AL_INVALID_ENUM) throw new IllegalStateException("AL_INVALID_ENUM");
        else if(error == AL10.AL_INVALID_VALUE) throw new IllegalStateException("AL_INVALID_VALUE");
        else if(error == AL10.AL_INVALID_OPERATION) throw new IllegalStateException("AL_INVALID_OPERATION");
        else if(error == AL10.AL_OUT_OF_MEMORY) throw new IllegalStateException("AL_OUT_OF_MEMORY");
        else throw new IllegalStateException("Unknown  AL exception: " + error);
    }

}