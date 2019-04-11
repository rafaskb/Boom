package com.rafaskoberg.boom;

import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.reverb.ReverbData;
import com.rafaskoberg.boom.effect.reverb.ReverbPreset;

public abstract class BoomChannel {
    private final int   index;

    BoomChannel(int index) {
        this.index = index;
    }

    /**
     * Returns the index this channel was registered at.
     */
    public int getIndex() {
        return index;
    }


    public abstract BoomEffect addReverb(ReverbPreset preset);

    public abstract BoomEffect addReverb(ReverbData data);

    protected abstract int getAlAuxSlot();

}
