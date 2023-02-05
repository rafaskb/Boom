package com.rafaskoberg.boom.effect;

/**
 * The chorus effect essentially replays the input audio accompanied by another slightly delayed version of the signal, creating a
 * ‘doubling’ effect. This was originally intended to emulate the effect of several musicians playing the same notes simultaneously, to
 * create a thicker, more satisfying sound. To add some variation to the effect, the delay time of the delayed versions of the input signal
 * is modulated by an adjustable oscillating waveform. This causes subtle shifts in the pitch of the delayed signals, emphasizing the
 * thickening effect.
 */
public enum ChorusPreset implements BoomEffectPreset {
    CHORE(new ChorusData(0, 0, 3, 0.15f, 0.55f, 0.015f)),
    VOICE_BREAK(new ChorusData(0, 0, 3, 0.2f, 0.75f, 0.016f)),
    GOOFY(new ChorusData(0, 0, 3, 0.6f, 0.75f, 0.016f)),
    GOOFY_ROBOT(new ChorusData(1, 0, 3, 0.6f, 0.75f, 0.016f)),
    TOO_MANY_ROBOTS(new ChorusData(1, 156, 9.2f, 0.38f, 0.9f, 0.0106f)),
    ;

    private final ChorusData data;

    ChorusPreset(ChorusData data) {
        this.data = data;
    }

    @Override
    public ChorusData getData() {
        return data;
    }

}
