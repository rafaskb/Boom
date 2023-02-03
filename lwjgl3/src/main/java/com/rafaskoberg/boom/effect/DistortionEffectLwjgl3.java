package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.BoomEffectLwjgl3;
import com.rafaskoberg.boom.effect.distortion.DistortionData;

import static org.lwjgl.openal.EXTEfx.*;

public class DistortionEffectLwjgl3 extends BoomEffectLwjgl3 {
    private final DistortionData data;

    public DistortionEffectLwjgl3(DistortionData data) {
        super();
        this.data = data;
    }

    @Override
    public void apply() {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_DISTORTION);

        alEffectf(alEffect, AL_DISTORTION_EDGE, data.edge);
        alEffectf(alEffect, AL_DISTORTION_GAIN, data.gain);
        alEffectf(alEffect, AL_DISTORTION_LOWPASS_CUTOFF, data.lowPassCutoff);
        alEffectf(alEffect, AL_DISTORTION_EQCENTER, data.eqCenter);
        alEffectf(alEffect, AL_DISTORTION_EQBANDWIDTH, data.eqBandwidth);

        // Set effect to aux slot
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

    @Override
    public void remove() {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }

}
