package com.rafaskoberg.boom;

import com.badlogic.gdx.utils.Disposable;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.filter.BoomFilterLwjgl3;
import com.rafaskoberg.boom.util.BoomError;

import static org.lwjgl.openal.EXTEfx.*;

public abstract class BoomEffectLwjgl3 extends BoomEffect implements Disposable {

    public final int alAuxSlot;
    public final int alEffect;
    int auxSendId = -1;

    public BoomEffectLwjgl3() {
        // Create aux slot
        this.alAuxSlot = alGenAuxiliaryEffectSlots();

        // Create effect
        this.alEffect = alGenEffects();

        // Check for errors
        BoomError.check("Error while creating a BoomEffect");

        // Create filter
        this.filter = new BoomFilterLwjgl3();
    }

    @Override
    public void dispose() {
        // Dispose
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
        alDeleteAuxiliaryEffectSlots(alAuxSlot);
        alDeleteEffects(alEffect);
        ((BoomFilterLwjgl3) filter).dispose();

        // Check for errors
        BoomError.check("Error while disposing a BoomEffect");
    }

}
