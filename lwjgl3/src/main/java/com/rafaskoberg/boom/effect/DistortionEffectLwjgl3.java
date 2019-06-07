package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.effect.distortion.DistortionData;

import static org.lwjgl.openal.EXTEfx.*;

public class DistortionEffectLwjgl3 extends BoomEffect {
    private final DistortionData data;
    public final  int            alEffect;

    public DistortionEffectLwjgl3(DistortionData data) {
        this.data = data;
        this.alEffect = alGenEffects();
    }

    @Override
    public void apply(int alAuxSlot) {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_DISTORTION);

        alEffectf(alEffect, AL_DISTORTION_EDGE, data.edge);
        alEffectf(alEffect, AL_DISTORTION_GAIN, data.gain);
        alEffectf(alEffect, AL_DISTORTION_LOWPASS_CUTOFF, data.lowPassCutoff);
        alEffectf(alEffect, AL_DISTORTION_EQCENTER, data.eqCenter);
        alEffectf(alEffect, AL_DISTORTION_EQBANDWIDTH, data.eqBandwidth);
    }

    @Override
    public void remove(int alAuxSlot) {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }

}
