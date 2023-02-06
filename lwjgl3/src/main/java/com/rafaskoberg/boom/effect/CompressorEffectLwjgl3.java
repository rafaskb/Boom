package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.BoomEffectLwjgl3;

import static org.lwjgl.openal.EXTEfx.*;

public class CompressorEffectLwjgl3 extends BoomEffectLwjgl3 {
    private final CompressorData data;

    public CompressorEffectLwjgl3(CompressorData data) {
        super();
        this.data = data;
    }

    @Override
    public void apply() {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_COMPRESSOR);

        alEffecti(alEffect, AL_COMPRESSOR_ONOFF, data.onOff);

        // Set effect to aux slot
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

}
