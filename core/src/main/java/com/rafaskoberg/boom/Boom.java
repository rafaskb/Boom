package com.rafaskoberg.boom;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public abstract class Boom {

    public static Boom init() {
        // Get application
        if(Gdx.app == null) {
            throw new IllegalStateException("Couldn't obtain application from Gdx.app to initialize Boom.");
        }

        // Initialize
        Gdx.app.log("Boom", "Initializing Boom.");
        Boom boom = null;
        try {
            ApplicationType type = Gdx.app.getType();
            if(type == ApplicationType.Desktop) {
                String className = "com.rafaskoberg.boom.BoomLwjgl3";
                Class<? extends Boom> clazz = ClassReflection.forName(className);
                Object instance = clazz.newInstance();
                boom = (Boom) instance;
            }
        } catch(ReflectionException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Unsuported platform
        if(boom == null) {
            Gdx.app.log("Boom", "Failed to initialize Boom. This platform is not supported yet.");
        }

        return boom;
    }

    public abstract BoomChannel createChannel(int channelIndex);

    public abstract BoomChannel getChannel(int index);

    public final void play(Sound sound, int channelIndex) {
        play(sound, getChannel(channelIndex), 1, 1, 0);
    }

    public final void play(Sound sound, int channelIndex, float volume) {
        play(sound, getChannel(channelIndex), volume, 1, 0);
    }

    public final void play(Sound sound, int channelIndex, float volume, float pitch, float pan) {
        play(sound, getChannel(channelIndex), volume, pitch, pan);
    }

    public final void play(Sound sound, BoomChannel channel) {
        play(sound, channel, 1, 1, 0);
    }

    public final void play(Sound sound, BoomChannel channel, float volume) {
        play(sound, channel, volume, 1, 0);
    }

    public abstract void play(Sound sound, BoomChannel channel, float volume, float pitch, float pan);

    public final void play(Music music, int channelIndex) {
        play(music, getChannel(channelIndex));
    }

    public abstract void play(Music music, BoomChannel channel);

    public final void changeMusicChannel(Music music, int channelIndex) {
        changeMusicChannel(music, getChannel(channelIndex));
    }

    public abstract void changeMusicChannel(Music music, BoomChannel channel);
}
