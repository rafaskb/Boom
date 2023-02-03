package com.rafaskoberg.boom.effect;

/**
 * The pitch shifter applies time-invariant pitch shifting to the input signal, over a one octave range and controllable at a semi-tone and
 * cent resolution.
 */
public class PitchShifterData implements BoomEffectData {
    /**
     * Range: -12 - 12, Default: 12<br> This sets the number of semitones by which the pitch is shifted. There are 12 semitones per octave.
     * Negative values create a downwards shift in pitch, positive values pitch the sound upwards.
     */
    public int coarseTune = 12;

    /**
     * Range: -50 - 50, Default: 0<br> This sets the number of cents between Semitones a pitch is shifted. A Cent is 1/100th of a Semitone.
     * Negative values create a downwards shift in pitch, positive values pitch the sound upwards.
     */
    public int fineTune = 0;

    public PitchShifterData() {

    }

    public PitchShifterData(int coarseTune, int fineTune) {
        this.coarseTune = coarseTune;
        this.fineTune = fineTune;
    }

    @Override
    public EffectType getType() {
        return EffectType.PITCH_SHIFTER;
    }
}
