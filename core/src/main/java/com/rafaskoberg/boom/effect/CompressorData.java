package com.rafaskoberg.boom.effect;

/**
 * The Automatic Gain Control effect performs the same task as a studio compressor – evening out the audio dynamic range of an input sound.
 * This results in audio exhibiting smaller variation in intensity between the loudest and quietest portions. The AGC Compressor will boost
 * quieter portions of the audio, while louder portions will stay the same or may even be reduced. The Compressor effect cannot be tweaked
 * in depth – it can just be switched on and off.
 */
public class CompressorData implements BoomEffectData {
    /**
     * 0 = on, 1 = off, Default = 1<br> The OpenAL Effect Extension Compressor can only be switched on and off – it cannot be adjusted.
     */
    public int onOff = 1;

    public CompressorData() {
    }

    public CompressorData(int onOff) {
        this.onOff = onOff;
    }

    @Override
    public EffectType getType() {
        return EffectType.COMPRESSOR;
    }
}

