package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.BoomEffectLwjgl3;

import static org.lwjgl.openal.EXTEfx.*;

public class EchoEffectLwjgl3 extends BoomEffectLwjgl3 {
    private final EchoData data;

    public EchoEffectLwjgl3(EchoData data) {
        super();
        this.data = data;
    }

    @Override
    public void apply() {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_ECHO);

        alEffectf(alEffect, AL_ECHO_DELAY, data.delay);
        alEffectf(alEffect, AL_ECHO_LRDELAY, data.lrdelay);
        alEffectf(alEffect, AL_ECHO_DAMPING, data.damping);
        alEffectf(alEffect, AL_ECHO_FEEDBACK, data.feedback);
        alEffectf(alEffect, AL_ECHO_SPREAD, data.spread);

        // Set effect to aux slot
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, alEffect);
    }

}
