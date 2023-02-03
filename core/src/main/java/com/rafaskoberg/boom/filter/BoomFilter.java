package com.rafaskoberg.boom.filter;

/**
 * BandPass filter that combines both low and high passes with their own individual controls.
 */
public interface BoomFilter {

    /**
     * Returns the gain of this filter. Defaults to 1.
     */
    float getGain();

    /**
     * Sets the gain of this filter, modifying the output volume of its source. Defaults to 1.
     */
    void setGain(float gain);

    /**
     * Returns the low pass cutoff factor, from 0 to 1. Higher values cut more frequencies, allowing only the lowest ones to pass. Defaults
     * to 0.
     */
    float getLowPass();

    /**
     * Sets the low pass cutoff factor, from 0 to 1. Higher values cut more frequencies, allowing only the lowest ones to pass. Defaults to
     * 0.
     */
    void setLowPass(float lowPass);

    /**
     * Returns the high pass cutoff factor, from 0 to 1. Higher values cut more frequencies, allowing only the highest ones to pass.
     * Defaults to 0.
     */
    float getHighPass();

    /**
     * Sets the high pass cutoff factor, from 0 to 1. Higher values cut more frequencies, allowing only the highest ones to pass. Defaults
     * to 0.
     */
    void setHighPass(float highPass);

}



