package com.rafaskoberg.boom.filter;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.rafaskoberg.boom.util.BoomError;

import static org.lwjgl.openal.EXTEfx.*;

public class BoomFilterLwjgl3 implements BoomFilter, Disposable {

    private final int alFilter;
    private float gain = 1;
    private float lowPass = 0;
    private float highPass = 0;

    public BoomFilterLwjgl3() {
        this.alFilter = alGenFilters();
        alFilteri(alFilter, AL_FILTER_TYPE, AL_FILTER_BANDPASS);

        // Check for errors
        BoomError.check("Error while creating a Bandpass Filter");
    }

    public int getAlFilter() {
        return alFilter;
    }

    @Override
    public float getGain() {
        return gain;
    }

    @Override
    public void setGain(float gain) {
        this.gain = MathUtils.clamp(gain, 0f, 1f);
        alFilterf(alFilter, AL_BANDPASS_GAIN, this.gain);
        BoomError.check("Error while setting the filter's AL_BANDPASS_GAIN to " + this.gain);
    }

    @Override

    public float getLowPass() {
        return lowPass;
    }

    @Override
    public void setLowPass(float lowPass) {
        this.lowPass = MathUtils.clamp(lowPass, 0f, 1f);
        float realValue = (float) Math.pow(1f - this.lowPass, 6);
        alFilterf(alFilter, AL_BANDPASS_GAINHF, realValue);
        BoomError.check("Error while setting the filter's AL_BANDPASS_GAINHF to " + this.lowPass + " (Real value: " + realValue + ")");
    }

    @Override
    public float getHighPass() {
        return highPass;
    }

    @Override
    public void setHighPass(float highPass) {
        this.highPass = MathUtils.clamp(highPass, 0f, 1f);
        float realValue = (float) Math.pow(1f - this.highPass, 6);
        alFilterf(alFilter, AL_BANDPASS_GAINLF, realValue);
        BoomError.check("Error while setting the filter's AL_BANDPASS_GAINLF to " + this.highPass + " (Real value: " + realValue + ")");
    }

    @Override
    public void dispose() {
        alDeleteFilters(alFilter);
    }

}
