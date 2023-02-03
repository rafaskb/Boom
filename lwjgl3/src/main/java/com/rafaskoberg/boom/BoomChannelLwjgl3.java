package com.rafaskoberg.boom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.BoomEffectData;
import com.rafaskoberg.boom.effect.DistortionEffectLwjgl3;
import com.rafaskoberg.boom.effect.EchoEffectLwjgl3;
import com.rafaskoberg.boom.effect.EffectType;
import com.rafaskoberg.boom.effect.PitchShifterEffectLwjgl3;
import com.rafaskoberg.boom.effect.ReverbEffectLwjgl3;
import com.rafaskoberg.boom.effect.distortion.DistortionData;
import com.rafaskoberg.boom.effect.echo.EchoData;
import com.rafaskoberg.boom.effect.pitchshifter.PitchShifterData;
import com.rafaskoberg.boom.effect.reverb.ReverbData;
import com.rafaskoberg.boom.util.BoomError;
import org.lwjgl.openal.AL10;

import static org.lwjgl.openal.EXTEfx.*;

public class BoomChannelLwjgl3 extends BoomChannel {
    private final Array<BoomEffectLwjgl3> effects;

    private int alSourceFilter = 0;

    BoomChannelLwjgl3(int id) {
        super(id);
        this.effects = new Array<>();
        this.alSourceFilter = alGenFilters();
        alFilteri(alSourceFilter, AL_FILTER_TYPE, AL_FILTER_LOWPASS);

        // Check for errors
        BoomError.check("Error while creating a BoomChannel of ID " + id);
    }

    @Override
    public void setSourceGain(float sourceGain) {
        super.setSourceGain(sourceGain);

    }

    @Override
    public BoomEffect addEffect(BoomEffectData effectData) {
        // Ensure capacity
        if(effects.size == 2) {
            Gdx.app.error("Boom", "The maximum amount of effects per channel is 2, and this channel (ID " + getId() + ") is already full.");
            return null;
        }

        // Create effect
        final BoomEffectLwjgl3 effect;
        EffectType effectType = effectData.getType();
        if(effectType == EffectType.REVERB) {
            effect = new ReverbEffectLwjgl3((ReverbData) effectData);
        } else if(effectType == EffectType.ECHO) {
            effect = new EchoEffectLwjgl3((EchoData) effectData);
        } else if(effectType == EffectType.DISTORTION) {
            effect = new DistortionEffectLwjgl3((DistortionData) effectData);
        } else if(effectType == EffectType.PITCH_SHIFTER) {
            effect = new PitchShifterEffectLwjgl3((PitchShifterData) effectData);
        } else {
            effect = null;
        }

        // Ensure effect is not null
        if(effect == null) {
            Gdx.app.error("Boom", "Effect is null, perhaps this effect type (" + effectType + ") is not implemented yet.");
            return null;
        }

        // Apply effect
        effect.apply();

        // Cache
        effects.add(effect);

        // Check for errors
        if(BoomError.check("Error while applying effect " + effectType)) {
            return null;
        }

        // Return effect
        return effect;
    }

    @Override
    public void removeEffect(BoomEffect effect) {
        BoomEffectLwjgl3 effectLwjgl3 = (BoomEffectLwjgl3) effect;
        if(effects.removeValue(effectLwjgl3, true)) {
            effectLwjgl3.remove();
            effectLwjgl3.dispose();
        }

        // Check for errors
        BoomError.check("Rrror while removing effect");
    }

    @Override
    public void removeEffect(int effectIndex) {
        if(effectIndex >= 0 && effectIndex < effects.size) {
            removeEffect(effects.get(effectIndex));
        }
    }

    @Override
    public void removeAllEffects() {
        for(BoomEffectLwjgl3 effect : effects) {
            effect.remove();
            effect.dispose();
        }
        effects.clear();

        // Check for errors
        BoomError.check("Error while removing effects");
    }

    public Array<BoomEffectLwjgl3> getEffects() {
        return effects;
    }

    @Override
    protected void apply(int sourceId) {
        alFilterf(alSourceFilter, AL_LOWPASS_GAIN, getSourceGain());
        alFilterf(alSourceFilter, AL_LOWPASS_GAINHF, (float) Math.pow(getSourceCutoff(), 6));
        AL10.alSourcei(sourceId, AL_DIRECT_FILTER, alSourceFilter);
    }

}


