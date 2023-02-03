package com.rafaskoberg.boom.effect;

/**
 * The ring modulator multiplies an input signal by a carrier signal in the time domain, resulting in tremolo or inharmonic effects.
 */
public class RingModulatorData implements BoomEffectData {
    /**
     * Range: 0.0 - 8000.0, Default: 440.0<br> This is the frequency of the carrier signal. If the carrier signal is slowly varying (less
     * than 20 Hz), the result is a tremolo (slow amplitude variation) effect. If the carrier signal is in the audio range, audible upper
     * and lower sidebands begin to appear, causing an inharmonic effect. The carrier signal itself is not heard in the output.
     */
    public float frequency = 440f;

    /**
     * Range: 0.0 - 24000.0, Default: 800.0<br> This controls the cutoff frequency at which the input signal is high-pass filtered before
     * being ring modulated. If the cutoff frequency is 0, the entire signal will be ring modulated. If the cutoff 114/144 frequency is
     * high, very little of the signal (only those parts above the cutoff) will be ring modulated.
     */
    public float highpassCutoff = 800f;

    /**
     * 0 = sinus, 1 = saw, 2 = square<br> This controls which waveform is used as the carrier signal. Traditional ring modulator and tremolo
     * effects generally use a sinusoidal carrier. Sawtooth and square waveforms are may cause unpleasant aliasing.
     */
    public int waveform = 0;

    public RingModulatorData() {
    }

    public RingModulatorData(float frequency, float highpassCutoff, int waveform) {
        this.frequency = frequency;
        this.highpassCutoff = highpassCutoff;
        this.waveform = waveform;
    }

    @Override
    public EffectType getType() {
        return EffectType.RING_MODULATOR;
    }
}
