package com.rafaskoberg.boom;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

/**
 * Play music tracks and sound effects with the capability of having special post-processing effects, such as reverb and filters.
 */
public abstract class Boom {

    /**
     * Initiates Boom using an existing backend. Currently only lwjgl3 is supported.
     *
     * @return A Boom instance that can be used to play sounds and music.
     */
    public static Boom init() {
        return init(false);
    }

    /**
     * Initiates Boom using an existing backend. Currently only lwjgl3 is supported.
     *
     * @param verbose Whether or not the init process should be logged to the console.
     * @return A Boom instance that can be used to play sounds and music.
     */
    public static Boom init(boolean verbose) {
        // Get application
        if(Gdx.app == null) {
            throw new IllegalStateException("Couldn't obtain application from Gdx.app to initialize Boom.");
        }

        // Initialize
        if(verbose) {
            Gdx.app.log("Boom", "Initializing Boom.");
        }
        Boom boom = null;
        try {
            ApplicationType type = Gdx.app.getType();
            if(type == ApplicationType.Desktop) {
                if(verbose) {
                    Gdx.app.log("Boom", "Attempting to load BoomLwjgl3...");
                }
                String className = "com.rafaskoberg.boom.BoomLwjgl3";
                Class<? extends Boom> clazz = ClassReflection.forName(className);
                Object instance = clazz.newInstance();
                boom = (Boom) instance;
                if(verbose) {
                    Gdx.app.log("Boom", "Loaded BoomLwjgl3.");
                }
            }
        } catch(ReflectionException | InstantiationException | IllegalAccessException ignored) {
        }

        // Log failure or success
        if(verbose) {
            if(boom == null) {
                Gdx.app.log("Boom", "Failed to initialize Boom. This platform is not supported yet.");
            } else {
                Gdx.app.log("Boom", "Successfully initiated Boom.");
            }
        }

        return boom;
    }

    /**
     * Creates a new {@link BoomChannel} using the given ID.
     *
     * @throws IllegalStateException If there's already a channel registered to the given ID.
     */
    public abstract BoomChannel createChannel(int channelId);

    /**
     * Returns the {@link BoomChannel} associated with the given ID, if any.
     */
    public abstract BoomChannel getChannel(int id);

    /**
     * Plays the sound. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound     Sound to be played.
     * @param channelId The ID of the channel this sound should be played through.
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long play(Sound sound, int channelId) {
        return play(sound, getChannel(channelId), 1, 1, 0);
    }

    /**
     * Plays the sound. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound     Sound to be played.
     * @param channelId The ID of the channel this sound should be played through.
     * @param volume    the volume in the range [0,1]
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long play(Sound sound, int channelId, float volume) {
        return play(sound, getChannel(channelId), volume, 1, 0);
    }

    /**
     * Plays the sound. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound     Sound to be played.
     * @param channelId The ID of the channel this sound should be played through.
     * @param volume    the volume in the range [0,1]
     * @param pitch     the pitch multiplier, 1 == default, >1 == faster, <1 == slower, the value has to be between 0.5 and 2.0
     * @param pan       panning in the range -1 (full left) to 1 (full right). 0 is center position.
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long play(Sound sound, int channelId, float volume, float pitch, float pan) {
        return play(sound, getChannel(channelId), volume, pitch, pan);
    }

    /**
     * Plays the sound. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound   Sound to be played.
     * @param channel Channel this sound should be played through.
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long play(Sound sound, BoomChannel channel) {
        return play(sound, channel, 1, 1, 0);
    }

    /**
     * Plays the sound. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound   Sound to be played.
     * @param channel Channel this sound should be played through.
     * @param volume  the volume in the range [0,1]
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long play(Sound sound, BoomChannel channel, float volume) {
        return play(sound, channel, volume, 1, 0);
    }

    /**
     * Plays the sound. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound   Sound to be played.
     * @param channel Channel this sound should be played through.
     * @param volume  the volume in the range [0,1]
     * @param pitch   the pitch multiplier, 1 == default, >1 == faster, <1 == slower, the value has to be between 0.5 and 2.0
     * @param pan     panning in the range -1 (full left) to 1 (full right). 0 is center position.
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public abstract long play(Sound sound, BoomChannel channel, float volume, float pitch, float pan);

    /**
     * Plays the sound, looping it forever. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound     Sound to be played.
     * @param channelId The ID of the channel this sound should be played through.
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long loop(Sound sound, int channelId) {
        return loop(sound, getChannel(channelId), 1, 1, 0);
    }

    /**
     * Plays the sound, looping it forever. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound     Sound to be played.
     * @param channelId The ID of the channel this sound should be played through.
     * @param volume    the volume in the range [0, 1]
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long loop(Sound sound, int channelId, float volume) {
        return loop(sound, getChannel(channelId), volume, 1, 0);
    }

    /**
     * Plays the sound, looping it forever. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound     Sound to be played.
     * @param channelId The ID of the channel this sound should be played through.
     * @param volume    the volume in the range [0, 1]
     * @param pitch     the pitch multiplier, 1 == default, >1 == faster, <1 == slower, the value has to be between 0.5 and 2.0
     * @param pan       panning in the range -1 (full left) to 1 (full right). 0 is center position.
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long loop(Sound sound, int channelId, float volume, float pitch, float pan) {
        return loop(sound, getChannel(channelId), volume, pitch, pan);
    }

    /**
     * Plays the sound, looping it forever. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound   Sound to be played.
     * @param channel Channel this sound should be played through.
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long loop(Sound sound, BoomChannel channel) {
        return loop(sound, channel, 1, 1, 0);
    }

    /**
     * Plays the sound, looping it forever. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound   Sound to be played.
     * @param channel Channel this sound should be played through.
     * @param volume  the volume in the range [0, 1]
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public final long loop(Sound sound, BoomChannel channel, float volume) {
        return loop(sound, channel, volume, 1, 0);
    }

    /**
     * Plays the sound, looping it forever. If the sound is already playing, it will be played again, concurrently.
     *
     * @param sound   Sound to be played.
     * @param channel Channel this sound should be played through.
     * @param volume  the volume in the range [0, 1]
     * @param pitch   the pitch multiplier, 1 == default, >1 == faster, <1 == slower, the value has to be between 0.5 and 2.0
     * @param pan     panning in the range -1 (full left) to 1 (full right). 0 is center position.
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public abstract long loop(Sound sound, BoomChannel channel, float volume, float pitch, float pan);

    /**
     * Starts the play back of the music stream. In case the stream was paused this will resume the play back. In case the music stream is
     * finished playing this will restart the play back.
     *
     * @param music     Music to be played.
     * @param channelId The ID of the channel this music should be played through.
     */
    public final void play(Music music, int channelId) {
        play(music, getChannel(channelId));
    }

    /**
     * Starts the play back of the music stream. In case the stream was paused this will resume the play back. In case the music stream is
     * finished playing this will restart the play back.
     *
     * @param music   Music to be played.
     * @param channel Channel this music should be played through.
     */
    public abstract void play(Music music, BoomChannel channel);

    /**
     * Changes the {@link BoomChannel} the given music is playing through. The music must be playing for this method to have any effect.
     *
     * @param music     Music to have its channel changed.
     * @param channelId The ID of the channel this music should be played through.
     */
    public final void changeMusicChannel(Music music, int channelId) {
        changeMusicChannel(music, getChannel(channelId));
    }

    /**
     * Changes the {@link BoomChannel} the given music is playing through. The music must be playing for this method to have any effect.
     *
     * @param music   Music to have its channel changed.
     * @param channel Channel this music should be played through.
     */
    public abstract void changeMusicChannel(Music music, BoomChannel channel);

    /**
     * Updates all the parameters of a specific SoundID to match the given channel. This can be used to change the channel of a certain
     * sound, or update the sound if the channel or its effects have been changed.
     * <p>
     * Note that this process happens automatically for music, but it must be done manually for sounds.
     *
     * @param soundId   Music to have its channel changed.
     * @param channelId The ID of the channel this sound should be updated against.
     */
    public final void updateSoundToChannel(long soundId, int channelId) {
        updateSoundToChannel(soundId, getChannel(channelId));
    }

    /**
     * Updates all the parameters of a specific SoundID to match the given channel. This can be used to change the channel of a certain
     * sound, or update the sound if the channel or its effects have been changed.
     * <p>
     * Note that this process happens automatically for music, but it must be done manually for sounds.
     *
     * @param soundId Music to have its channel changed.
     * @param channel Channel this sound should be updated against.
     */
    public abstract void updateSoundToChannel(long soundId, BoomChannel channel);
}
