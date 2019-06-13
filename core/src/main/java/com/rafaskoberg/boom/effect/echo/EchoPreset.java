package com.rafaskoberg.boom.effect.echo;

public enum EchoPreset {

    // ===========================
    // ===   Default Presets   ===
    // ===========================
    // TODO Create presets such as "Small room", "Cave", "Distant Echo" etc.
    ;

    // Internal

    private final EchoData data;

    EchoPreset(EchoData data) {
        this.data = data;
    }

    public EchoData getData() {
        return data;
    }

}
