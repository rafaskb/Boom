package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.effect.pitchshifter.PitchShifterData;

import static org.lwjgl.openal.EXTEfx.*;

public class PitchShifterEffectLwjgl3 extends BoomEffect {
    private final PitchShifterData data;
    public final int alEffect;

    public PitchShifterEffectLwjgl3(PitchShifterData data) {
        this.data = data;
        this.alEffect = alGenEffects();
    }

    @Override
    public void apply(int alAuxSlot) {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_PITCH_SHIFTER);

        alEffecti(alEffect, AL_PITCH_SHIFTER_COARSE_TUNE, data.coarseTune);
        alEffecti(alEffect, AL_PITCH_SHIFTER_FINE_TUNE, data.fineTune);

        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

    @Override
    public void remove(int alAuxSlot) {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }

}
