package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.BoomEffectLwjgl3;

import static org.lwjgl.openal.EXTEfx.*;

public class ChorusEffectLwjgl3 extends BoomEffectLwjgl3 {
    private final ChorusData data;

    public ChorusEffectLwjgl3(ChorusData data) {
        super();
        this.data = data;
    }

    @Override
    public void apply() {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_CHORUS);

        alEffecti(alEffect, AL_CHORUS_WAVEFORM, data.waveForm);
        alEffecti(alEffect, AL_CHORUS_PHASE, data.phase);
        alEffectf(alEffect, AL_CHORUS_RATE, data.rate);
        alEffectf(alEffect, AL_CHORUS_DEPTH, data.depth);
        alEffectf(alEffect, AL_CHORUS_FEEDBACK, data.feedback);
        alEffectf(alEffect, AL_CHORUS_DELAY, data.delay);

        // Set effect to aux slot
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

    @Override
    public void remove() {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }

}
