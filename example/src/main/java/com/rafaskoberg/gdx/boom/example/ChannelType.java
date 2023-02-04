package com.rafaskoberg.gdx.boom.example;

public enum ChannelType {
    NONE(0, "None"),
    AERIAL_REVERB(1, "Aerial Reverb"),
    EVIL_REALM(2, "Evil Realm"),
    TINY_LITTLE_BIRDS(3, "Tiny Little Birds"),
    FUNKY_STUFF(4, "Funky Stuff"),
    ODD_BUT_NICE(5, "Odd But Nice"),
    BAD_ROBOT_IN_C(6, "Bad Robot in C"),
    OSCILLATORS_FOREVER(7, "Oscillators Forever"),
    IN_YOUR_FACE(8, "In Your Face"),
    DOUBLE_STEREO(9, "Double Stereo");

    public final int id;
    public final String displayName;

    ChannelType(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}
