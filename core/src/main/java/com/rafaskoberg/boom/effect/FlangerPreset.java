package com.rafaskoberg.boom.effect;

/**
 * The flanger effect creates a “tearing” or “whooshing” sound (like a jet flying overhead). It works by sampling a portion of the input
 * signal, delaying it by a period modulated between 0 and 4ms by a low-frequency oscillator, and then mixing it with the source signal.
 */
public enum FlangerPreset implements BoomEffectPreset {

    // ===========================
    // ===   Default Presets   ===
    // ===========================
    FRIGHTENED(new FlangerData(0, -180, 9.1f, 1, -0.64f, 0.0022f)),
    MIC_FEEDBACK(new FlangerData(1, -138, 8.3f, 1, -0.98f, 0.0003f)),
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
