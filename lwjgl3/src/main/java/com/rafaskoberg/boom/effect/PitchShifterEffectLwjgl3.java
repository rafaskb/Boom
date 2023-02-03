package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.BoomEffectLwjgl3;
import com.rafaskoberg.boom.effect.pitchshifter.PitchShifterData;

import static org.lwjgl.openal.EXTEfx.*;

public class PitchShifterEffectLwjgl3 extends BoomEffectLwjgl3 {
    private final PitchShifterData data;

    public PitchShifterEffectLwjgl3(PitchShifterData data) {
        super();
        this.data = data;
    }

    @Override
    public void apply() {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_PITCH_SHIFTER);

        alEffecti(alEffect, AL_PITCH_SHIFTER_COARSE_TUNE, data.coarseTune);
        alEffecti(alEffect, AL_PITCH_SHIFTER_FINE_TUNE, data.fineTune);

        // Set effect to aux slot
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

    @Override
    public void remove() {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }

}
