package com.rafaskoberg.boom.effect;

/**
 * The vocal morpher consists of a pair of 4-band formant filters, used to impose vocal tract effects upon the input signal. If the input
 * signal is a broadband sound such as pink noise or a car engine, the vocal morpher can provide a wide variety of filtering effects. A
 * low-frequency oscillator can be used to morph the filtering effect between two different phonemes. The vocal morpher is not necessarily
 * intended for use on voice signals; it is primarily intended for pitched noise effects, vocal-like wind effects, etc.
 */
public enum VocalMorpherPreset implements BoomEffectPreset {

    // ===========================
    // ===   Default Presets   ===
    // ===========================
    MUFFLED(new VocalMorpherData(4, 29, -16, 0, 0, 0)),
    CHEAP_RADIO(new VocalMorpherData(4, 29, 24, 0, 0, 0)),
    PLASTIC_BOX(new VocalMorpherData(2, 29, 13, 0, 0, 0)),
    CRISPY_COMMS(new VocalMorpherData(0, 29, 11, 0, 0, 0)),
    WANDERING_FILTER(new VocalMorpherData(0, 3, 5, -19, 1, 0.8f)),
    ;

    // Internal

    private final VocalMorpherData data;

    VocalMorpherPreset(VocalMorpherData data) {
        this.data = data;
    }

    @Override
    public VocalMorpherData getData() {
        return data;
    }

}
