package com.rafaskoberg.boom.effect;

/**
 * The distortion effect simulates turning up (overdriving) the gain stage on a guitar amplifier or adding a distortion pedal to an
 * instrument’s output. It is achieved by clipping the signal (adding more square wave-like components) and adding rich harmonics. The
 * distortion effect could be very useful for adding extra dynamics to engine sounds in a driving simulator, or modifying samples such as
 * vocal communications. The OpenAL Effects Extension distortion effect also includes EQ on the output signal, to help ‘rein in’ excessive
 * frequency content in distorted audio. A low-pass filter is applied to input signal before the distortion effect, to limit excessive
 * distorted signals at high frequencies.
 */
public enum DistortionPreset implements BoomEffectPreset {

    // ===========================
    // ===   Default Presets   ===
    // ===========================
    SOFT(new DistortionData(0.1f, 1.0f, 8000f, 3600f, 3600f)),
    GENERIC(new DistortionData(0.5f, 1.0f, 8000f, 3600f, 3600f)),
    RUMBLE(new DistortionData(0.7f, 1.0f, 8000f, 3600f, 3600f)),
    CRUSHER(new DistortionData(1.0f, 1.0f, 8000f, 3600f, 3600f)),
    ;

    // Internal

    private final DistortionData data;

    DistortionPreset(DistortionData data) {
        this.data = data;
    }

    @Override
    public DistortionData getData() {
        return data;
    }

}
