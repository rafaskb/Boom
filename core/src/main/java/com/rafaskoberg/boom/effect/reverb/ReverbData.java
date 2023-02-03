package com.rafaskoberg.boom.effect.reverb;

import com.rafaskoberg.boom.effect.BoomEffectData;
import com.rafaskoberg.boom.effect.EffectType;

/**
 * Simulates a reverberation effect, which occurs when a sound hits any hard surface and reflects back to the listener at varying times and
 * amplitudes to create a complex echo, which carries information about that physical space. Reverb pedals or effects simulate or exaggerate
 * natural reverberations.
 */
public class ReverbData implements BoomEffectData {

    /**
     * Controls the coloration of the late reverb
     * <p>
     * Value range: (0; 1]
     */
    public float density;
    /**
     * Controls the echo density when the reverb is decaying
     * <p>
     * Value range: [0; 1]
     */
    public float diffusion;
    /**
     * Controls the reflected sound that the reverb adds to all sound sources attached to.
     * <p>
     * Value range [0; 1] Default: 0.89
     */
    public float gain;
    /**
     * Controls the reflected sound at high frequences
     * <p>
     * Value range: [0; 1] Default: 0.0
     */
    public float gainHF;
    /**
     * Controls the reflected sound at low frequences
     * <p>
     * Value range: [0; 1] Default: 0.0
     */
    public float gainLF;
    /**
     * Controls reverb decay time. (similiar to echo) Ranges from 0.1 (suitable for very small room) to 20.0 (large room)
     * <p>
     * Value range: [0.1; 20] Default: 1.49 (seconds)
     */
    public float decayTime;
    /**
     * Adjusts the spectral quality of the [decayTime] in high frequencies.
     * <p>
     * Value range: (0; 20.0] Default: 0.83
     */
    public float decayHFRatio;
    /**
     * Adjusts the spectral quality of the [decayTime] in low frequencies.
     * <p>
     * Value range: (0; 20.0] Default: 0.83
     */
    public float decayLFRatio;
    /**
     * Overall amount of the initial reflections relative to [gain]
     * <p>
     * Value range: [0, 3.16] (from -100dB to +10dB) Default: 0.05
     */
    public float reflectionsGain;
    /**
     * Delay between the arrival time of the direct path from the source to the first from the source.
     * <p>
     * Value range: [0; 0.3] (0 to 300 milliseconds)
     */
    public float reflectionsDelay;
    /**
     * 3D vector that controls the spatial distribution of the cluster of early reflections. The direction of this vector controls the
     * global direction of the reflections, while its magnitude controls how focused the reflections are towards this direction.
     * <p>
     * If the magnitude of Reflections Pan is zero (the default setting), the early reflections come evenly from all directions. As the
     * magnitude increases, the reflections become more focused in the direction pointed to by the vector. A magnitude of 1.0 would
     * represent the extreme case, where all reflections come from a single direction.
     * <p>
     * Value range: [-1,-1,-1; 1,1,1] Default: [0,0,0]
     */
    public float[] reflectionsPan;
    /**
     * Controls the overall amount of LATE (not initial) reverb relative to the [gain]
     * <p>
     * Value range: [0; 10.0] Default: 1.26
     */
    public float lateReverbGain;
    /**
     * Defines the starting time of the late reverb relative to the time of the initial reflection
     * <p>
     * Value range: [0.0; 0.1] Default: 0.011
     */
    public float lateReverbDelay;
    /**
     * 3D vector that controls the spatial distribution of the late reverb. The direction of this vector controls the global direction of
     * the reverb, while its magnitude controls how focused the reverb are towards this direction.
     * <p>
     * Value range: [-1,-1,-1; 1,1,1] Default: [0,0,0]
     */
    public float[] lateReverbPan;
    /**
     * Controls the rate at which the cyclic echo repeats itself along the reverberation decay.
     * <p>
     * Value range: [0.075; 0.25] Default: 0.25 (seconds)
     */
    public float echoTime;
    /**
     * Introduces a cyclic echo in the reverberation decay, which will be noticeable with transient or percussive sounds. A larger value
     * will make this effect more prominent.
     * <p>
     * Value range: [0; 1] Default: 0
     */
    public float echoDepth;
    /**
     * Controls the speed of the vibrato (rate of periodic changes in pitch).
     * <p>
     * Value range: [0.04; 4] Default: 0.25 (seconds)
     */
    public float modulationTime;
    /**
     * Controls the amount of pitch change. Low values will contribute to reinforcing the perceived effect by reducing the mixing of
     * overlapping reflections in the reverberation decay.
     * <p>
     * Value range: [0; 1] Default: 0
     */
    public float modulationDepth;
    /**
     * Controls the distance-dependent attenuation at high frequencies caused by the propagation minimum
     * <p>
     * Value range: [0.892; 1.0] Default: 0.994
     */
    public float airAbsorptionGainHF;
    /**
     * Determine the frequencies at which the high-frequency effects created by this Reverb properties are measured.
     * <p>
     * Value range: [1000; 20000] Default: 5000
     */
    public float hfReference;
    /**
     * Determine the frequencies at which the low-frequency effects created by this Reverb properties are measured.
     * <p>
     * This value should be less than 1/10 of the HF Reference value.
     * <p>
     * Value range: [20; 1000] Default: 250
     */
    public float lfReference;
    /**
     * Attenuates the reflected sound
     * <p>
     * Value range: [0.0; 10.0] Default: 0.0
     */
    public float roomRolloffFactor;
    /**
     * When this flag is set, the high-frequency decay time automatically stays below a limit value that’s derived from the setting of the
     * property Air Absorption Gain HF. This limit applies regardless of the setting of the property Decay HF Ratio, and the limit doesn’t
     * affect the value of Decay HF Ratio. This limit, when on, maintains a natural sounding reverberation decay by allowing you to increase
     * the value of Decay Time without the risk of getting an unnaturally long decay time at high frequencies. If this flag is set to
     * AL_FALSE, high-frequency decay time isn’t automatically limited.
     * <p>
     * Value range: [0; 1] Default: 1
     */
    public int decayHFLimit;

    public ReverbData() {
    }

    public ReverbData(float density, float diffusion, float gain, float gainHF, float gainLF, float decayTime,
                      float decayHFRatio, float decayLFRatio, float reflectionsGain, float reflectionsDelay,
                      float[] reflectionsPan, float lateReverbGain, float lateReverbDelay, float[] lateReverbPan,
                      float echoTime, float echoDepth, float modulationTime, float modulationDepth,
                      float airAbsorptionGainHF, float hfReference, float lfReference, float roomRolloffFactor,
                      int decayHFLimit) {
        this.density = density;
        this.diffusion = diffusion;
        this.gain = gain;
        this.gainHF = gainHF;
        this.gainLF = gainLF;
        this.decayTime = decayTime;
        this.decayHFRatio = decayHFRatio;
        this.decayLFRatio = decayLFRatio;
        this.reflectionsGain = reflectionsGain;
        this.reflectionsDelay = reflectionsDelay;
        this.reflectionsPan = reflectionsPan;
        this.lateReverbGain = lateReverbGain;
        this.lateReverbDelay = lateReverbDelay;
        this.lateReverbPan = lateReverbPan;
        this.echoTime = echoTime;
        this.echoDepth = echoDepth;
        this.modulationTime = modulationTime;
        this.modulationDepth = modulationDepth;
        this.airAbsorptionGainHF = airAbsorptionGainHF;
        this.hfReference = hfReference;
        this.lfReference = lfReference;
        this.roomRolloffFactor = roomRolloffFactor;
        this.decayHFLimit = decayHFLimit;
    }

    @Override
    public EffectType getType() {
        return EffectType.REVERB;
    }

}