package com.rafaskoberg.boom;

import com.badlogic.gdx.math.MathUtils;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.BoomEffectData;
import com.rafaskoberg.boom.effect.BoomEffectPreset;

/**
 * Channel where sound effects and music tracks can be played through.
 */
public abstract class BoomChannel {
    private final int id;
    private float sourceGain = 1.0f;
    private float sourceCutoff = 1.0f;

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
     * Returns the volume gain of the source audio, before any effects are applied.
     */
    public float getSourceGain() {
        return sourceGain;
    }

    /**
     * Sets volume gain of the source audio, before any effects are applied.
     */
    public void setSourceGain(float sourceGain) {
        this.sourceGain = MathUtils.clamp(sourceGain, 0, 1);
    }

    /**
     * Returns the source audio low pass filter cutoff, from 0 to 1. The lower this value is, less high frequencies will be played.
     */
    public float getSourceCutoff() {
        return sourceCutoff;
    }

    /**
     * Sets the source audio low pass filter cutoff, from 0 to 1. The lower this value is, less high frequencies will be played.
     */
    public void setSourceCutoff(float sourceCutoff) {
        this.sourceCutoff = MathUtils.clamp(sourceCutoff, 0, 1);
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
     * Removes a certain effect from this channel.
     */
    public abstract void removeEffect(int effectIndex);

    /**
     * Removes all effects from this channel.
     */
    public abstract void removeAllEffects();

    protected abstract int getAlAuxSlot();

    protected abstract void apply(int sourceId);
}
