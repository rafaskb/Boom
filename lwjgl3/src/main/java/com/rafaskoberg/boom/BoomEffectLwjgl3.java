package com.rafaskoberg.boom;

import com.badlogic.gdx.utils.Disposable;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.util.BoomError;

import static org.lwjgl.openal.EXTEfx.*;

public abstract class BoomEffectLwjgl3 extends BoomEffect implements Disposable {

    public final int alAuxSlot;
    public final int alEffect;

    public BoomEffectLwjgl3() {
        // Create aux slot
        this.alAuxSlot = alGenAuxiliaryEffectSlots();

        // Create effect
        this.alEffect = alGenEffects();

        // Check for errors
        BoomError.check("Error while creating a BoomEffect");
    }

    @Override
    public void dispose() {
        // Dispose
        alDeleteAuxiliaryEffectSlots(alAuxSlot);
        alDeleteEffects(alEffect);

        // Check for errors
        BoomError.check("Error while disposing a BoomEffect");
    }

}
