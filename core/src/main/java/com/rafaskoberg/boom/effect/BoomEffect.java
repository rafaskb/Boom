package com.rafaskoberg.boom.effect;

import com.rafaskoberg.boom.filter.BoomFilter;

public abstract class BoomEffect {

    protected BoomFilter filter;

    public abstract void apply();

    public abstract void remove();

    /**
     * Returns the filter for this channel.
     */
    public BoomFilter getFilter() {
        return filter;
    }

}
