package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.BoomEffectLwjgl3;

import static org.lwjgl.openal.EXTEfx.*;

public class AutoWahEffectLwjgl3 extends BoomEffectLwjgl3 {
    private final AutoWahData data;

    public AutoWahEffectLwjgl3(AutoWahData data) {
        super();
        this.data = data;
    }

    @Override
    public void apply() {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_AUTOWAH);

        alEffectf(alEffect, AL_AUTOWAH_ATTACK_TIME, data.attackTime);
        alEffectf(alEffect, AL_AUTOWAH_RELEASE_TIME, data.releaseTime);
        alEffectf(alEffect, AL_AUTOWAH_RESONANCE, data.resonance);
        alEffectf(alEffect, AL_AUTOWAH_PEAK_GAIN, data.peakGain);

        // Set effect to aux slot
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

}
