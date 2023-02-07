package com.rafaskoberg.boom;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntIntMap.Entry;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.filter.BoomFilterLwjgl3;
import com.rafaskoberg.boom.util.BoomError;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.EXTEfx;

import static org.lwjgl.openal.EXTEfx.*;

public abstract class BoomEffectLwjgl3 extends BoomEffect implements Disposable {

    public final int alAuxSlot;
    public final int alEffect;
    int channelId = -1;
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
        // Detach sourceIds from this effect
        IntIntMap sourceIdsToChannelIds = BoomLwjgl3.getInstance().sourceIdsToChannelIds;
        for(Entry entry : sourceIdsToChannelIds) {
            int sourceId = entry.key;
            int channelId = entry.value;
            if(channelId == this.channelId) {
                AL11.alSource3i(sourceId, EXTEfx.AL_AUXILIARY_SEND_FILTER, EXTEfx.AL_EFFECTSLOT_NULL, auxSendId, EXTEfx.AL_FILTER_NULL);
            }
        }

        // Check for errors
        BoomError.check("Error while detaching effect from sources. channelId: " + channelId + ", auxSendId: " + auxSendId);

        // Dispose
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, AL_EFFECT_NULL);
        alDeleteAuxiliaryEffectSlots(alAuxSlot);
        alDeleteEffects(alEffect);
        ((BoomFilterLwjgl3) filter).dispose();

        // Check for errors
        BoomError.check("Error while disposing a BoomEffect. alAuxSlot: " + alAuxSlot + ", alEffect: " + alEffect);
    }

}
