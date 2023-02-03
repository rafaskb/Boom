package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.BoomEffectLwjgl3;

import static org.lwjgl.openal.EXTEfx.*;

public class FlangerEffectLwjgl3 extends BoomEffectLwjgl3 {
    private final FlangerData data;

    public FlangerEffectLwjgl3(FlangerData data) {
        super();
        this.data = data;
    }

    @Override
    public void apply() {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_FLANGER);

        alEffecti(alEffect, AL_FLANGER_WAVEFORM, data.waveform);
        alEffecti(alEffect, AL_FLANGER_PHASE, data.phase);
        alEffectf(alEffect, AL_FLANGER_RATE, data.rate);
        alEffectf(alEffect, AL_FLANGER_DEPTH, data.depth);
        alEffectf(alEffect, AL_FLANGER_FEEDBACK, data.feedback);
        alEffectf(alEffect, AL_FLANGER_DELAY, data.delay);

        // Set effect to aux slot
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

    @Override
    public void remove() {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }

}
