package com.rafaskoberg.boom;

import com.badlogic.gdx.math.MathUtils;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.reverb.ReverbData;
import com.rafaskoberg.boom.effect.reverb.ReverbPreset;

public abstract class BoomChannel {
    private       float sourceGain = 1.0f;
    private final int   id;

    BoomChannel(int id) {
        this.id = id;
    }

    /**
     * Returns the id this channel was registered at.
     */
    public int getId() {
        return id;
    }

    public float getSourceGain() {
        return sourceGain;
    }

    public void setSourceGain(float sourceGain) {
        this.sourceGain = MathUtils.clamp(sourceGain, 0, 1);
    }

    public abstract BoomEffect addReverb(ReverbPreset preset);

    public abstract BoomEffect addReverb(ReverbData data);

    protected abstract int getAlAuxSlot();

    protected abstract void apply(int sourceId);

}
