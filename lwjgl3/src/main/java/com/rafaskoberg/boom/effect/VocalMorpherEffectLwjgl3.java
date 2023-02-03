package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.BoomEffectLwjgl3;

import static org.lwjgl.openal.EXTEfx.*;

public class VocalMorpherEffectLwjgl3 extends BoomEffectLwjgl3 {
    private final VocalMorpherData data;

    public VocalMorpherEffectLwjgl3(VocalMorpherData data) {
        super();
        this.data = data;
    }

    @Override
    public void apply() {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_VOCAL_MORPHER);

        alEffecti(alEffect, AL_VOCMORPHER_PHONEMEA, data.phonemea);
        alEffecti(alEffect, AL_VOCMORPHER_PHONEMEB, data.phonemeb);
        alEffecti(alEffect, AL_VOCMORPHER_PHONEMEA_COARSE_TUNING, data.phonemeaCoarseTuning);
        alEffecti(alEffect, AL_VOCMORPHER_PHONEMEB_COARSE_TUNING, data.phonemebCoarseTuning);
        alEffecti(alEffect, AL_VOCMORPHER_WAVEFORM, data.waveform);
        alEffectf(alEffect, AL_VOCMORPHER_RATE, data.rate);

        // Set effect to aux slot
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

    @Override
    public void remove() {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }
}
