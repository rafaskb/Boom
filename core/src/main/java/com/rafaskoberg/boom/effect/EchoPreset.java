package com.rafaskoberg.boom.effect;

/**
 * The echo effect generates discrete, delayed instances of the input signal. The amount of delay and feedback is controllable. The delay is
 * ‘two tap’ – you can control the interaction between two separate instances of echoes.
 */
public enum EchoPreset implements BoomEffectPreset {

    // ===========================
    // ===   Default Presets   ===
    // ===========================
    VERY_FAR_AWAY(new EchoData(0.207f, 0.404f, 0.6f, 0.7f, 0f)),
    FAR_AWAY(new EchoData(0.207f, 0.404f, 0.5f, 0.5f, 0f)),
    PING_PONG_LEFT(new EchoData(0.05f, 0.1f, 0.1f, 0.8f, 1f)),
    PING_PONG_RIGHT(new EchoData(0.05f, 0.1f, 0.1f, 0.8f, 1f)),
    PING_PONG_CENTER(new EchoData(0.05f, 0.1f, 0.1f, 0.8f, 0f)),
    DOPPELGANGER(new EchoData(0.1f, 0.1f, 0.4f, 0.5f, 0.2f)),
    ;

    // Internal

    private final EchoData data;

    EchoPreset(EchoData data) {
        this.data = data;
    }

    @Override
    public EchoData getData() {
        return data;
    }

}
