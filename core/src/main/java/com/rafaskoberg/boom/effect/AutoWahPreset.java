package com.rafaskoberg.boom.effect;

/**
 * The Auto-wah effect emulates the sound of a wah-wah pedal used with an electric guitar, or a mute on a brass instrument. Such effects
 * allow a musician to control the tone of their instrument by varying the point at which high frequencies are cut off. This OpenAL Effects
 * Extension effect is called Auto-wah because there is no user input for modulating the cut-off point. Instead the effect is achieved by
 * analysing the input signal, and applying a band-pass filter according the intensity of the incoming audio.
 */
public enum AutoWahPreset implements BoomEffectPreset {
    FUNKY_BEATS(new AutoWahData(0.0001f, 0.07f, 1000, 4100)),
    RESONANT_WAVES(new AutoWahData(0.01f, 0.13f, 1000, 12000)),
    SCRAMBLED(new AutoWahData(0.0001f, 0.01f, 1000, 6900)),
    WAH_GHOSTS(new AutoWahData(0.04f, 0.73f, 85, 4750)),
    WINDY_NIGHTS(new AutoWahData(0.12f, 0.30f, 650, 24000)),
    ;

    private final AutoWahData data;

    AutoWahPreset(AutoWahData data) {
        this.data = data;
    }

    @Override
    public AutoWahData getData() {
        return data;
    }

}
