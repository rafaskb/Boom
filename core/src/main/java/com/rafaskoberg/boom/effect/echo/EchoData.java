package com.rafaskoberg.boom.effect.echo;

/**
 * Settings for AL_EFFECT_ECHO
 */
public class EchoData {

    /**
     * This property controls the delay between the original sound and the first ‘tap’, or echo instance. Subsequently,
     * the value for Echo Delay is used to determine the time delay between each ‘second tap’ and the next ‘first tap’.
     * <p>
     * Value range 0.0 to 0.207; Default value 0.1
     */
    public float delay = 0.1f;

    /**
     * This property controls the delay between the first ‘tap’ and the second ‘tap’. Subsequently, the value for Echo
     * LR Delay is used to determine the time delay between each ‘first tap’ and the next ‘second tap’.
     * <p>
     * Value range 0.0 to 0.404; Default value 0.1
     */
    public float lrdelay = 0.1f;

    /**
     * This property controls the amount of high frequency damping applied to each echo. As the sound is subsequently
     * fed back for further echoes, damping results in an echo which progressively gets softer in tone as well as
     * intensity.
     * <p>
     * Value range 0.0 to 0.99; Default value 0.5
     */
    public float damping = 0.5f;

    /**
     * This property controls the amount of feedback the output signal fed back into the input. Use this parameter to
     * create “cascading” echoes. At full magnitude, the identical sample will repeat endlessly. Below full magnitude,
     * the sample will repeat and fade.
     * <p>
     * Value range 0.0 to 1.0; Default value 0.5
     */
    public float feedback = 0.5f;

    /**
     * This property controls how hard panned the individual echoes are. With a value of 1.0, the first ‘tap’ will be
     * panned hard left, and the second tap hard right. A value of –1.0 gives the opposite result. Settings nearer to
     * 0.0 result in less emphasized panning.
     * <p>
     * Value range -1.0 to 1.0; Default value -1.0
     */
    public float spread = -1.0f;

    /**
     * See Appendix 1 of OpenAL guide to understand more about each parameter: <a href=https://kcat.strangesoft.net/misc-downloads/Effects%20Extension%20Guide.pdf>Effects
     * Extension Guide</a>
     */
    public EchoData(float delay, float lrdelay, float damping, float feedback, float spread) {
        this.delay = delay;
        this.lrdelay = lrdelay;
        this.damping = damping;
        this.feedback = feedback;
        this.spread = spread;
    }
}
