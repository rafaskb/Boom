package com.rafaskoberg.boom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl3.audio.OpenALAudio;
import com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.EXTEfx;

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

    public BoomLwjgl3() {
        channelsByIndex = new IntMap<>();
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
    public void play(Sound sound, BoomChannel abstractChannel, float volume, float pitch, float pan) {
        // Cast channel to its real class
        BoomChannelLwjgl3 channel = (BoomChannelLwjgl3) abstractChannel;

        // Play sound
        long soundId = sound.play(volume, pitch, pan);
        int sourceId = getSourceId(soundId);
        if(sourceId != -1) {

            // Apply channel effect
            int alAuxSlot = channel == null ? EXTEfx.AL_EFFECTSLOT_NULL : channel.getAlAuxSlot();
            AL11.alSource3i(sourceId, EXTEfx.AL_AUXILIARY_SEND_FILTER, alAuxSlot, 0, EXTEfx.AL_FILTER_NULL);
            BoomLwjgl3.checkAlError();
        }
    }

    @Override
    public void changeMusicChannel(Music abstractMusic, BoomChannel abstractChannel) {
        // Cast channel and music to their real classes
        BoomChannelLwjgl3 channel = (BoomChannelLwjgl3) abstractChannel;
        OpenALMusic music = (OpenALMusic) abstractMusic;

        // If music is playing and has a source ID, apply channel effect
        if(music.isPlaying()) {
            int sourceId = music.getSourceId();
            if(sourceId != -1) {
                int alAuxSlot = channel == null ? EXTEfx.AL_EFFECTSLOT_NULL : channel.getAlAuxSlot();
                AL11.alSource3i(sourceId, EXTEfx.AL_AUXILIARY_SEND_FILTER, alAuxSlot, 0, EXTEfx.AL_FILTER_NULL);
                BoomLwjgl3.checkAlError();
            }
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