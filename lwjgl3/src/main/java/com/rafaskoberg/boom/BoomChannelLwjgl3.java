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

    private int alAuxSlot   = 0;
    private int alDryFilter = 0;

    BoomChannelLwjgl3(int id) {
        super(id);
        this.effects = new Array<>();
        this.alAuxSlot = alGenAuxiliaryEffectSlots();
        this.alDryFilter = alGenFilters();
        alFilteri(alDryFilter, AL_FILTER_TYPE, AL_FILTER_LOWPASS);
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
    protected void apply(int sourceId) {
        alFilterf(alDryFilter, AL_LOWPASS_GAIN, getSourceGain());
        AL10.alSourcei(sourceId, AL_DIRECT_FILTER, alDryFilter);
    }

}


