package com.rafaskoberg.boom;

import com.badlogic.gdx.utils.Array;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.DistortionEffectLwjgl3;
import com.rafaskoberg.boom.effect.EchoEffectLwjgl3;
import com.rafaskoberg.boom.effect.ReverbEffectLwjgl3;
import com.rafaskoberg.boom.effect.distortion.DistortionData;
import com.rafaskoberg.boom.effect.distortion.DistortionPreset;
import com.rafaskoberg.boom.effect.echo.EchoData;
import com.rafaskoberg.boom.effect.echo.EchoPreset;
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
        try {
            EFXUtil.checkAlError();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return reverb;
    }

    @Override
    public BoomEffect addDistortion(DistortionPreset preset) {
        return addDistortion(preset.getData());
    }

    @Override
    public BoomEffect addDistortion(DistortionData data) {
        DistortionEffectLwjgl3 distortion = new DistortionEffectLwjgl3(data);
        effects.add(distortion);
        distortion.apply(alAuxSlot);
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, distortion.alEffect);
        try {
            EFXUtil.checkAlError();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return distortion;
    }

    @Override
    public BoomEffect addEcho(EchoPreset preset) {
        return addEcho(preset.getData());
    }

    @Override
    public BoomEffect addEcho(EchoData data) {
        EchoEffectLwjgl3 echo = new EchoEffectLwjgl3(data);
        effects.add(echo);
        echo.apply(alAuxSlot);
        alAuxiliaryEffectSloti(alAuxSlot, AL_EFFECTSLOT_EFFECT, echo.alEffect);
        try {
            EFXUtil.checkAlError();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return echo;
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


