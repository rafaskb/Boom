package com.rafaskoberg.boom.effect;

/**
 * The pitch shifter applies time-invariant pitch shifting to the input signal, over a one octave range and controllable at a semi-tone and
 * cent resolution.
 */
public enum PitchShifterPreset implements BoomEffectPreset {

    // ===========================
    // ===   Default Presets   ===
    // ===========================
    CHIPMUNK(new PitchShifterData(8, 0)),
    DEMON(new PitchShifterData(-9, 0)),
    MAX_PITCH(new PitchShifterData(12, 50)),
    MIN_PITCH(new PitchShifterData(-12, -50)),
    ;

    // Internal

    private final PitchShifterData data;

    PitchShifterPreset(PitchShifterData data) {
        this.data = data;
    }

    @Override
    public PitchShifterData getData() {
        return data;
    }

}
