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

    public abstract BoomChannel createChannel(int channelId);

    public abstract BoomChannel getChannel(int id);

    public final long play(Sound sound, int channelId) {
        return play(sound, getChannel(channelId), 1, 1, 0);
    }

    public final long play(Sound sound, int channelId, float volume) {
        return play(sound, getChannel(channelId), volume, 1, 0);
    }

    public final long play(Sound sound, int channelId, float volume, float pitch, float pan) {
        return play(sound, getChannel(channelId), volume, pitch, pan);
    }

    public final long play(Sound sound, BoomChannel channel) {
        return play(sound, channel, 1, 1, 0);
    }

    public final long play(Sound sound, BoomChannel channel, float volume) {
        return play(sound, channel, volume, 1, 0);
    }

    public abstract long play(Sound sound, BoomChannel channel, float volume, float pitch, float pan);

    public final long loop(Sound sound, int channelId) {
        return loop(sound, getChannel(channelId), 1, 1, 0);
    }

    public final long loop(Sound sound, int channelId, float volume) {
        return loop(sound, getChannel(channelId), volume, 1, 0);
    }

    public final long loop(Sound sound, int channelId, float volume, float pitch, float pan) {
        return loop(sound, getChannel(channelId), volume, pitch, pan);
    }

    public final long loop(Sound sound, BoomChannel channel) {
        return loop(sound, channel, 1, 1, 0);
    }

    public final long loop(Sound sound, BoomChannel channel, float volume) {
        return loop(sound, channel, volume, 1, 0);
    }

    public abstract long loop(Sound sound, BoomChannel channel, float volume, float pitch, float pan);

    public final void play(Music music, int channelId) {
        play(music, getChannel(channelId));
    }

    public abstract void play(Music music, BoomChannel channel);

    public final void changeMusicChannel(Music music, int channelId) {
        changeMusicChannel(music, getChannel(channelId));
    }

    public abstract void changeMusicChannel(Music music, BoomChannel channel);
}
