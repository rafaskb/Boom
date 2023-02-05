package com.rafaskoberg.boom.effect;

/**
 * The ring modulator multiplies an input signal by a carrier signal in the time domain, resulting in tremolo or inharmonic effects.
 */
public enum RingModulatorPreset implements BoomEffectPreset {

    // ===========================
    // ===   Default Presets   ===
    // ===========================
    TREMOLO(new RingModulatorData(3.5f, 0, 0)),
    SLOW_TREMOLO(new RingModulatorData(1.5f, 0, 0)),
    FAST_TREMOLO(new RingModulatorData(5f, 0, 0)),
    ODD_ROBOT_IN_C(new RingModulatorData(1760, 800, 0)),
    ;

    // Internal

    private final RingModulatorData data;

    RingModulatorPreset(RingModulatorData data) {
        this.data = data;
    }

    @Override
    public RingModulatorData getData() {
        return data;
    }

}
