package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.BoomEffectLwjgl3;

import static org.lwjgl.openal.EXTEfx.*;

public class RingModulatorEffectLwjgl3 extends BoomEffectLwjgl3 {
    private final RingModulatorData data;

    public RingModulatorEffectLwjgl3(RingModulatorData data) {
        super();
        this.data = data;
    }

    @Override
    public void apply() {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_RING_MODULATOR);

        alEffectf(alEffect, AL_RING_MODULATOR_FREQUENCY, data.frequency);
        alEffectf(alEffect, AL_RING_MODULATOR_HIGHPASS_CUTOFF, data.highpassCutoff);
        alEffecti(alEffect, AL_RING_MODULATOR_WAVEFORM, data.waveform);

        // Set effect to aux slot
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

    @Override
    public void remove() {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }

}
