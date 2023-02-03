package com.rafaskoberg.boom.effect;

/**
 * The Auto-wah effect emulates the sound of a wah-wah pedal used with an electric guitar, or a mute on a brass instrument. Such effects
 * allow a musician to control the tone of their instrument by varying the point at which high frequencies are cut off. This OpenAL Effects
 * Extension effect is called Auto-wah because there is no user input for modulating the cut-off point. Instead the effect is achieved by
 * analysing the input signal, and applying a band-pass filter according the intensity of the incoming audio.
 */
public class AutoWahData implements BoomEffectData {
    /**
     * Range: 0.0001 - 1.0, Default: 0.06<br> This property controls the time the filtering effect takes to sweep from minimum to maximum
     * center frequency when it is triggered by input signal.
     */
    public float attackTime = 0.06f;

    /**
     * Range: 0.0001 - 1.0, Default: 0.06<br> This property controls the time the filtering effect takes to sweep from maximum back to base
     * center frequency, when the input signal ends.
     */
    public float releaseTime = 0.06f;

    /**
     * Range: 2.0 - 1000.0, Default: 1000.0<br> This property controls the resonant peak, sometimes known as emphasis or Q, of the auto-wah
     * band-pass filter. Resonance occurs when the effect boosts the frequency content of the sound 115/144 around the point at which the
     * filter is working. A high value promotes a highly resonant, sharp sounding effect.
     */
    public float resonance = 1000f;

    /**
     * Range: 0.00003 - 31621.0, Default: 11.22<br> This property controls the input signal level at which the band-pass filter will be
     * fully opened.
     */
    public float peakGain = 11.22f;

    public AutoWahData() {
    }

    public AutoWahData(float attackTime, float releaseTime, float resonance, float peakGain) {
        this.attackTime = attackTime;
        this.releaseTime = releaseTime;
        this.resonance = resonance;
        this.peakGain = peakGain;
    }

    @Override
    public EffectType getType() {
        return EffectType.AUTO_WAH;
    }
}
