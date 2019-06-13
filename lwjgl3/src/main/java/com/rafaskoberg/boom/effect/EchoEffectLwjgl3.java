package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.effect.echo.EchoData;

import static org.lwjgl.openal.EXTEfx.*;

public class EchoEffectLwjgl3 extends BoomEffect {
    private final EchoData data;
    public final  int      alEffect;

    public EchoEffectLwjgl3(EchoData data) {
        this.data = data;
        this.alEffect = alGenEffects();
    }

    @Override
    public void apply(int alAuxSlot) {
        alEffecti(alEffect, AL_EFFECT_TYPE, AL_EFFECT_ECHO);

        alEffectf(alEffect, AL_ECHO_DELAY, data.delay);
        alEffectf(alEffect, AL_ECHO_LRDELAY, data.lrdelay);
        alEffectf(alEffect, AL_ECHO_DAMPING, data.damping);
        alEffectf(alEffect, AL_ECHO_FEEDBACK, data.feedback);
        alEffectf(alEffect, AL_ECHO_SPREAD, data.spread);
    }

    @Override
    public void remove(int alAuxSlot) {
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
    }

}
