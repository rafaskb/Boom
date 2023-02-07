package com.rafaskoberg.boom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Values;
import com.rafaskoberg.boom.effect.AutoWahData;
import com.rafaskoberg.boom.effect.AutoWahEffectLwjgl3;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.BoomEffectData;
import com.rafaskoberg.boom.effect.ChorusData;
import com.rafaskoberg.boom.effect.ChorusEffectLwjgl3;
import com.rafaskoberg.boom.effect.CompressorData;
import com.rafaskoberg.boom.effect.CompressorEffectLwjgl3;
import com.rafaskoberg.boom.effect.DistortionData;
import com.rafaskoberg.boom.effect.DistortionEffectLwjgl3;
import com.rafaskoberg.boom.effect.EchoData;
import com.rafaskoberg.boom.effect.EchoEffectLwjgl3;
import com.rafaskoberg.boom.effect.EffectType;
import com.rafaskoberg.boom.effect.FlangerData;
import com.rafaskoberg.boom.effect.FlangerEffectLwjgl3;
import com.rafaskoberg.boom.effect.PitchShifterData;
import com.rafaskoberg.boom.effect.PitchShifterEffectLwjgl3;
import com.rafaskoberg.boom.effect.ReverbData;
import com.rafaskoberg.boom.effect.ReverbEffectLwjgl3;
import com.rafaskoberg.boom.effect.RingModulatorData;
import com.rafaskoberg.boom.effect.RingModulatorEffectLwjgl3;
import com.rafaskoberg.boom.effect.VocalMorpherData;
import com.rafaskoberg.boom.effect.VocalMorpherEffectLwjgl3;
import com.rafaskoberg.boom.filter.BoomFilterLwjgl3;
import com.rafaskoberg.boom.util.BoomError;
import org.lwjgl.openal.AL10;

import static org.lwjgl.openal.EXTEfx.AL_DIRECT_FILTER;

public class BoomChannelLwjgl3 extends BoomChannel {
    final IntMap<BoomEffectLwjgl3> effectsBySlot;
    private final Array<BoomEffectLwjgl3> tmpEffects;
    private final int alFilter;
    private final int maxEffectsPerChannel;

    BoomChannelLwjgl3(int id) {
        super(id);
        this.effectsBySlot = new IntMap<>();
        this.tmpEffects = new Array<>();
        this.maxEffectsPerChannel = BoomLwjgl3.getInstance().getMaxEffectsPerChannel();

        // Check for errors
        BoomError.check("Error while creating a BoomChannel of ID " + id);

        // Create filter
        this.filter = new BoomFilterLwjgl3();
        this.alFilter = ((BoomFilterLwjgl3) filter).getAlFilter();
    }

    @Override
    public BoomEffect addEffect(BoomEffectData effectData) {
        // Ensure capacity
        if(effectsBySlot.size >= maxEffectsPerChannel) {
            Gdx.app.error("Boom", String.format(
                "Tried to add an effect to Channel ID %d, but it already hit the limit (%d).",
                getId(), maxEffectsPerChannel
            ));
            return null;
        }

        // Create effect
        final BoomEffectLwjgl3 effect;
        EffectType effectType = effectData.getType();
        if(effectType == EffectType.AUTO_WAH) {
            effect = new AutoWahEffectLwjgl3((AutoWahData) effectData);
        } else if(effectType == EffectType.CHORUS) {
            effect = new ChorusEffectLwjgl3((ChorusData) effectData);
        } else if(effectType == EffectType.COMPRESSOR) {
            effect = new CompressorEffectLwjgl3((CompressorData) effectData);
        } else if(effectType == EffectType.DISTORTION) {
            effect = new DistortionEffectLwjgl3((DistortionData) effectData);
        } else if(effectType == EffectType.ECHO) {
            effect = new EchoEffectLwjgl3((EchoData) effectData);
        } else if(effectType == EffectType.FLANGER) {
            effect = new FlangerEffectLwjgl3((FlangerData) effectData);
        } else if(effectType == EffectType.PITCH_SHIFTER) {
            effect = new PitchShifterEffectLwjgl3((PitchShifterData) effectData);
        } else if(effectType == EffectType.REVERB) {
            effect = new ReverbEffectLwjgl3((ReverbData) effectData);
        } else if(effectType == EffectType.RING_MODULATOR) {
            effect = new RingModulatorEffectLwjgl3((RingModulatorData) effectData);
        } else if(effectType == EffectType.VOCAL_MORPHER) {
            effect = new VocalMorpherEffectLwjgl3((VocalMorpherData) effectData);
        } else {
            effect = null;
        }

        // Ensure effect is not null
        if(effect == null) {
            Gdx.app.error("Boom", "Effect is null, perhaps this effect type (" + effectType + ") is not implemented yet.");
            return null;
        }

        // Ensure effect slot is valid
        int effectSlot = findNextEffectSlot();
        if(effectSlot == -1) {
            Gdx.app.error("Boom", "Effect slot is invalid. Channel has " + effectsBySlot.size + " effects.");
            return null;
        }

        // Assign channel slot
        effect.auxSendId = effectSlot;
        effect.channelId = getId();
        effectsBySlot.put(effectSlot, effect);

        // Apply effect
        effect.apply();

        // Check for errors
        if(BoomError.check("Error while applying effect " + effectType)) {
            return null;
        }

        // Return effect
        return effect;
    }

    @Override
    public void removeEffect(BoomEffect _effect) {
        // Remove effect
        BoomEffectLwjgl3 effect = (BoomEffectLwjgl3) _effect;
        int effectSlot = effect.auxSendId;
        boolean removed = effectsBySlot.remove(effectSlot) != null;
        if(removed) {
            effect.dispose();
        }

        // Check for errors
        BoomError.check("Error while removing effect");
    }

    @Override
    public void removeAllEffects() {
        // Remove effects
        for(int slot = 0; slot < maxEffectsPerChannel; slot++) {
            BoomEffectLwjgl3 effect = effectsBySlot.get(slot, null);
            if(effect != null) {
                int effectSlot = effect.auxSendId;
                effectsBySlot.remove(effectSlot);
                effect.dispose();
            }
        }
        effectsBySlot.clear();

        // Check for errors
        BoomError.check("Error while removing effects");
    }

    @Override
    public Array<BoomEffectLwjgl3> getEffects() {
        tmpEffects.clear();
        Values<BoomEffectLwjgl3> values = effectsBySlot.values();
        while(values.hasNext)
            tmpEffects.add(values.next());
        return tmpEffects;
    }

    @Override
    protected void apply(int sourceId) {
        // Apply
        AL10.alSourcei(sourceId, AL_DIRECT_FILTER, alFilter);

        // Register Source ID
        BoomLwjgl3.getInstance().sourceIdsToChannelIds.put(sourceId, getId());
    }

    private int findNextEffectSlot() {
        for(int slot = 0; slot < maxEffectsPerChannel; slot++) {
            if(!effectsBySlot.containsKey(slot)) {
                return slot;
            }
        }
        return -1;
    }

}


