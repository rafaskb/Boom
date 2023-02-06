package com.rafaskoberg.boom;

import com.badlogic.gdx.utils.Array;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.BoomEffectData;
import com.rafaskoberg.boom.effect.BoomEffectPreset;
import com.rafaskoberg.boom.filter.BoomFilter;

/**
 * Channel where sound effects and music tracks can be played through.
 */
public abstract class BoomChannel {
    private final int id;
    protected BoomFilter filter;

    BoomChannel(int id) {
        this.id = id;
    }

    /**
     * Returns the id this channel was registered at.
     */
    public int getId() {
        return id;
    }

    /**
     * Adds a new effect to this channel.
     */
    public BoomEffect addEffect(BoomEffectPreset effect) {
        return addEffect(effect.getData());
    }

    /**
     * Adds a new effect to this channel.
     */
    public abstract BoomEffect addEffect(BoomEffectData effect);

    /**
     * Removes a certain effect from this channel.
     */
    public abstract void removeEffect(BoomEffect effect);

    /**
     * Removes all effects from this channel.
     */
    public abstract void removeAllEffects();

    /**
     * Returns a temporary list of all effects currently applied to this channel. Changes to this list have no effect.
     */
    public abstract Array<? extends BoomEffect> getEffects();

    /**
     * Returns the filter for this channel.
     */
    public BoomFilter getFilter() {
        return filter;
    }

    protected abstract void apply(int sourceId);
}
