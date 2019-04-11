package com.rafaskoberg.boom;

import com.badlogic.gdx.utils.Array;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.ReverbEffectLwjgl3;
import com.rafaskoberg.boom.effect.reverb.ReverbData;
import com.rafaskoberg.boom.effect.reverb.ReverbPreset;
import com.rafaskoberg.boom.util.EFXUtil;
import org.lwjgl.openal.AL10;

import static org.lwjgl.openal.EXTEfx.*;

public class BoomChannelLwjgl3 extends BoomChannel {
    private final Array<BoomEffect> effects;

    private int alAuxSlot      = 0;
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
    public BoomEffect addReverb(ReverbPreset preset) {
        return addReverb(preset.getData());
    }

    @Override
    public BoomEffect addReverb(ReverbData data) {
        ReverbEffectLwjgl3 reverb = new ReverbEffectLwjgl3(data);
        effects.add(reverb);
        reverb.apply(alAuxSlot);
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, reverb.alEffect);
        EFXUtil.checkAlError();
        return reverb;
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


