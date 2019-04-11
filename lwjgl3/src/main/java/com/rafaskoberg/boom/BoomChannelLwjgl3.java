package com.rafaskoberg.boom;

import com.badlogic.gdx.utils.Array;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.ReverbEffectLwjgl3;
import com.rafaskoberg.boom.effect.reverb.ReverbData;
import com.rafaskoberg.boom.effect.reverb.ReverbPreset;

import static org.lwjgl.openal.EXTEfx.*;

public class BoomChannelLwjgl3 extends BoomChannel {
    private final Array<BoomEffect> effects;

    private int alAuxSlot = 0;

    BoomChannelLwjgl3(int index) {
        super(index);
        this.effects = new Array<>();

        this.alAuxSlot = alGenAuxiliaryEffectSlots();
    }

    @Override
    protected int getAlAuxSlot() {
        return alAuxSlot;
    }

    @Override
    public BoomEffect addReverb(ReverbPreset preset) {
        return addReverb(preset.getData());
    }

    @Override
    public BoomEffect addReverb(ReverbData data) {
        ReverbEffectLwjgl3 reverb = new ReverbEffectLwjgl3(data);
        effects.add(reverb);
        reverb.apply(alAuxSlot);
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, reverb.alEffect);
        BoomLwjgl3.checkAlError();
        return reverb;
    }

}


