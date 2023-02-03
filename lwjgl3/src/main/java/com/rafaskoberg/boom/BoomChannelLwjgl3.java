package com.rafaskoberg.boom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.BoomEffectData;
import com.rafaskoberg.boom.effect.DistortionEffectLwjgl3;
import com.rafaskoberg.boom.effect.EchoEffectLwjgl3;
import com.rafaskoberg.boom.effect.EffectType;
import com.rafaskoberg.boom.effect.ReverbEffectLwjgl3;
import com.rafaskoberg.boom.effect.distortion.DistortionData;
import com.rafaskoberg.boom.effect.echo.EchoData;
import com.rafaskoberg.boom.effect.reverb.ReverbData;
import com.rafaskoberg.boom.util.EFXUtil;
import org.lwjgl.openal.AL10;

import static org.lwjgl.openal.EXTEfx.*;

public class BoomChannelLwjgl3 extends BoomChannel {
    private final Array<BoomEffect> effects;

    private int alAuxSlot = 0;
    private int alSourceFilter = 0;

    BoomChannelLwjgl3(int id) {
        super(id);
        this.effects = new Array<>();
        this.alAuxSlot = alGenAuxiliaryEffectSlots();
        this.alSourceFilter = alGenFilters();
        alFilteri(alSourceFilter, AL_FILTER_TYPE, AL_FILTER_LOWPASS);
    }

    @Override
    public void setSourceGain(float sourceGain) {
        super.setSourceGain(sourceGain);

    }

    @Override
    protected int getAlAuxSlot() {
        return alAuxSlot;
    }

    @Override
    public BoomEffect addEffect(BoomEffectData effectData) {
        // Create effect
        final BoomEffect effect;
        EffectType effectType = effectData.getType();
        if(effectType == EffectType.REVERB) {
            effect = new ReverbEffectLwjgl3((ReverbData) effectData);
        } else if(effectType == EffectType.ECHO) {
            effect = new EchoEffectLwjgl3((EchoData) effectData);
        } else if(effectType == EffectType.DISTORTION) {
            effect = new DistortionEffectLwjgl3((DistortionData) effectData);
        } else {
            effect = null;
        }

        // Ensure effect is not null
        if(effect == null) {
            Gdx.app.error("Boom", "Effect is null, perhaps this effect type (" + effectType + ") is not implemented yet.");
            return null;
        }

        // Apply
        effects.add(effect);
        effect.apply(alAuxSlot);

        // Check for errors
        try {
            EFXUtil.checkAlError();
        } catch(Exception e) {
            Gdx.app.error("Boom", "OpenAL error while applying effect " + effectType, e);
            return null;
        }

        // Return effect
        return effect;
    }

    @Override
    public void removeEffect(BoomEffect effect) {
        if(effects.removeValue(effect, true)) {
            effect.remove(alAuxSlot);
        }
    }

    @Override
    public void removeEffect(int effectIndex) {
        if(effectIndex >= 0 && effectIndex < effects.size) {
            removeEffect(effects.get(effectIndex));
        }
    }

    @Override
    public void removeAllEffects() {
        for(BoomEffect effect : effects) {
            effect.remove(alAuxSlot);
        }
        effects.clear();
    }

    @Override
    protected void apply(int sourceId) {
        alFilterf(alSourceFilter, AL_LOWPASS_GAIN, getSourceGain());
        alFilterf(alSourceFilter, AL_LOWPASS_GAINHF, (float) Math.pow(getSourceCutoff(), 6));
        AL10.alSourcei(sourceId, AL_DIRECT_FILTER, alSourceFilter);
    }

}


