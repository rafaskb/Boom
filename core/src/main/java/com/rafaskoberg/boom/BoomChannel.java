package com.rafaskoberg.boom;

import com.badlogic.gdx.math.MathUtils;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.distortion.DistortionData;
import com.rafaskoberg.boom.effect.distortion.DistortionPreset;
import com.rafaskoberg.boom.effect.echo.EchoData;
import com.rafaskoberg.boom.effect.echo.EchoPreset;
import com.rafaskoberg.boom.effect.reverb.ReverbData;
import com.rafaskoberg.boom.effect.reverb.ReverbPreset;

/**
 * Channel where sound effects and music tracks can be played through.
 */
public abstract class BoomChannel {
    private final int   id;
    private       float sourceGain   = 1.0f;
    private       float sourceCutoff = 1.0f;

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
     * Returns the source audio low pass filter cutoff, from 0 to 1. The lower this value is, less high frequencies will
     * be played.
     */
    public float getSourceCutoff() {
        return sourceCutoff;
    }

    /**
     * Sets the source audio low pass filter cutoff, from 0 to 1. The lower this value is, less high frequencies will be
     * played.
     */
    public void setSourceCutoff(float sourceCutoff) {
        this.sourceCutoff = MathUtils.clamp(sourceCutoff, 0, 1);
    }

    /**
     * Adds a reverb effect to this channel.
     */
    public abstract BoomEffect addReverb(ReverbPreset preset);

    /**
     * Adds a reverb effect to this channel.
     */
    public abstract BoomEffect addReverb(ReverbData data);

    /**
     * Adds a distortion effect to this channel.
     */
    public abstract BoomEffect addDistortion(DistortionPreset preset);

    /**
     * Adds a distortion effect to this channel.
     */
    public abstract BoomEffect addDistortion(DistortionData data);

    /**
     * Adds an echo effect to this channel.
     */
    public abstract BoomEffect addEcho(EchoPreset preset);

    /**
     * Adds an echo effect to this channel.
     */
    public abstract BoomEffect addEcho(EchoData data);

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
