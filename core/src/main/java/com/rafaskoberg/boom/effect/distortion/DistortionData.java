package com.rafaskoberg.boom.effect.distortion;

import com.rafaskoberg.boom.effect.BoomEffectData;
import com.rafaskoberg.boom.effect.EffectType;

/**
 * The distortion effect simulates turning up (overdriving) the gain stage on a guitar amplifier or adding a distortion pedal to an
 * instrument’s output. It is achieved by clipping the signal (adding more square wave-like components) and adding rich harmonics. The
 * distortion effect could be very useful for adding extra dynamics to engine sounds in a driving simulator, or modifying samples such as
 * vocal communications. The OpenAL Effects Extension distortion effect also includes EQ on the output signal, to help ‘rein in’ excessive
 * frequency content in distorted audio. A low-pass filter is applied to input signal before the distortion effect, to limit excessive
 * distorted signals at high frequencies.
 */
public class DistortionData implements BoomEffectData {

    /**
     * This property controls the shape of the distortion. The higher the value for Edge, the ‘dirtier’ and ‘fuzzier’ the effect.
     * <p>
     * Value range: 0.0 to 1.0; Default value: 0.2
     */
    public float edge = 0.2f;

    /**
     * This property allows you to attenuate the distorted sound.
     * <p>
     * Value range 0.01 to 1.0; Default value 0.05
     */
    public float gain = 0.05f;

    /**
     * Input signal can have a low pass filter applied, to limit the amount of high frequency signal feeding into the distortion effect.
     * <p>
     * Value range 80.0 to 24000.0; Default value 8000.0
     */
    public float lowPassCutoff = 8000;

    /**
     * This property controls the frequency at which the post-distortion attenuation (Distortion Gain) is active.
     * <p>
     * Value range 80.0 to 24000.0; Default value 3600.0
     */
    public float eqCenter = 3600;

    /**
     * This property controls the bandwidth of the post-distortion attenuation.
     * <p>
     * Value range 80.0 to 24000.0; Default value 3600.0
     */
    public float eqBandwidth = 3600;

    public DistortionData() {
    }

    public DistortionData(float edge, float gain, float lowPassCutoff, float eqCenter, float eqBandwidth) {
        this.edge = edge;
        this.gain = gain;
        this.lowPassCutoff = lowPassCutoff;
        this.eqCenter = eqCenter;
        this.eqBandwidth = eqBandwidth;
    }

    @Override
    public EffectType getType() {
        return EffectType.DISTORTION;
    }
}
