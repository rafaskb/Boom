package com.rafaskoberg.boom.effect;

/**
 * The flanger effect creates a “tearing” or “whooshing” sound (like a jet flying overhead). It works by sampling a portion of the input
 * signal, delaying it by a period modulated between 0 and 4ms by a low-frequency oscillator, and then mixing it with the source signal.
 */
public enum FlangerPreset implements BoomEffectPreset {

    // ===========================
    // ===   Default Presets   ===
    // ===========================
    ROBOT_HIGH(new FlangerData(1, 180, 2f, 0.5f, 0.9f, 0.002f)),
    ROBOT_LOW(new FlangerData(1, 180, 2f, 0.5f, 0.9f, 0.004f)),
    ROBOT_METALLIC(new FlangerData(1, 180, 2f, 0.5f, -0.95f, 0.004f)),
    ;

    // Internal

    private final FlangerData data;

    FlangerPreset(FlangerData data) {
        this.data = data;
    }

    @Override
    public FlangerData getData() {
        return data;
    }

}
