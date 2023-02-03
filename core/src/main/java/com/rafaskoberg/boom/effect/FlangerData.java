package com.rafaskoberg.boom.effect;

/**
 * The flanger effect creates a “tearing” or “whooshing” sound (like a jet flying overhead). It works by sampling a portion of the input
 * signal, delaying it by a period modulated between 0 and 4ms by a low-frequency oscillator, and then mixing it with the source signal.
 */
public class FlangerData implements BoomEffectData {
    /**
     * 0 = sinusoid, 1 = triangle<br> Selects the shape of the LFO waveform that controls the amount of the delay of the sampled signal.
     * Zero is a sinusoid and one is a triangle.
     */
    public int waveform = 1;

    /**
     * Range: -180 - 180, Default: 0<br> This changes the phase difference between the left and right LFO’s. At zero degrees the two LFOs
     * are synchronized.
     */
    public int phase = 0;

    /**
     * Range: 0.0 - 10.0, Default: 0.27<br> The number of times per second the LFO controlling the amount of delay repeats. Higher values
     * increase the pitch modulation.
     */
    public float rate = 0.27f;

    /**
     * Range: 0.0 - 1.0, Default: 1.0<br> The ratio by which the delay time is modulated by the LFO. Use this parameter to increase the
     * pitch modulation.
     */
    public float depth = 1f;

    /**
     * Range: -1.0 - 1.0, Default: -0.5<br> This is the amount of the output signal level fed back into the effect’s input. A negative value
     * will reverse the phase of the feedback signal. Use this parameter to create an “intense metallic” effect. At full magnitude, the
     * identical sample will repeat endlessly. At less than full magnitude, the sample will repeat and fade out over time.
     */
    public float feedback = -0.5f;

    /**
     * Range: 0.0 - 0.004, Default: 0.002<br> The average amount of time the sample is delayed before it is played back; with feedback, the
     * amount of time between iterations of the sample.
     */
    public float delay = 0.002f;

    public FlangerData() {
    }

    public FlangerData(int waveform, int phase, float rate, float depth, float feedback, float delay) {
        this.waveform = waveform;
        this.phase = phase;
        this.rate = rate;
        this.depth = depth;
        this.feedback = feedback;
        this.delay = delay;
    }

    @Override
    public EffectType getType() {
        return EffectType.FLANGER;
    }
}
