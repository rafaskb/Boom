package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.effect.reverb.ReverbData;

import static org.lwjgl.openal.EXTEfx.*;

public class ReverbEffectLwjgl3 extends BoomEffect {
    private final ReverbData data;
    public final  int        alEffect;

    public ReverbEffectLwjgl3(ReverbData data) {
        this.data = data;
        this.alEffect = alGenEffects();
    }

    @Override
    public void apply(int alAuxSlot) {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_EAXREVERB);

        alEffectf(alEffect, AL_EAXREVERB_DENSITY, data.density);
        alEffectf(alEffect, AL_EAXREVERB_DIFFUSION, data.diffusion);
        alEffectf(alEffect, AL_EAXREVERB_GAIN, data.gain);
        alEffectf(alEffect, AL_EAXREVERB_GAINHF, data.gainHF);
        alEffectf(alEffect, AL_EAXREVERB_GAINLF, data.gainLF);
        alEffectf(alEffect, AL_EAXREVERB_DECAY_TIME, data.decayTime);
        alEffectf(alEffect, AL_EAXREVERB_DECAY_HFRATIO, data.decayHFRatio);
        alEffectf(alEffect, AL_EAXREVERB_DECAY_LFRATIO, data.decayLFRatio);
        alEffectf(alEffect, AL_EAXREVERB_REFLECTIONS_GAIN, data.reflectionsGain);
        alEffectf(alEffect, AL_EAXREVERB_REFLECTIONS_DELAY, data.reflectionsDelay);
        alEffectfv(alEffect, AL_EAXREVERB_REFLECTIONS_PAN, data.reflectionsPan);
        alEffectf(alEffect, AL_EAXREVERB_LATE_REVERB_GAIN, data.lateReverbGain);
        alEffectf(alEffect, AL_EAXREVERB_LATE_REVERB_DELAY, data.lateReverbDelay);
        alEffectfv(alEffect, AL_EAXREVERB_REFLECTIONS_PAN, data.lateReverbPan);
        alEffectf(alEffect, AL_EAXREVERB_ECHO_TIME, data.echoTime);
        alEffectf(alEffect, AL_EAXREVERB_ECHO_DEPTH, data.echoDepth);
        alEffectf(alEffect, AL_EAXREVERB_MODULATION_TIME, data.modulationTime);
        alEffectf(alEffect, AL_EAXREVERB_MODULATION_DEPTH, data.modulationDepth);
        alEffectf(alEffect, AL_EAXREVERB_AIR_ABSORPTION_GAINHF, data.airAbsorptionGainHF);
        alEffectf(alEffect, AL_EAXREVERB_HFREFERENCE, data.hfReference);
        alEffectf(alEffect, AL_EAXREVERB_LFREFERENCE, data.lfReference);
        alEffectf(alEffect, AL_EAXREVERB_ROOM_ROLLOFF_FACTOR, data.roomRolloffFactor);
        alEffecti(alEffect, AL_EAXREVERB_DECAY_HFLIMIT, data.decayHFLimit);
    }

    @Override
    public void remove(int alAuxSlot) {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }

}
