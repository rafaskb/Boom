package com.rafaskoberg.boom;

import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.reverb.ReverbData;
import com.rafaskoberg.boom.effect.reverb.ReverbPreset;

public abstract class BoomChannel {

    public abstract BoomEffect addReverb(ReverbPreset preset);

    public abstract BoomEffect addReverb(ReverbData data);

    protected abstract int getAlAuxSlot();

}
