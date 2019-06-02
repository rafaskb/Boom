package com.rafaskoberg.boom.effect.reverb;

public enum ReverbPreset {

    // ===========================
    // ===   Default Presets   ===
    // ===========================
    GENERIC(new ReverbData(1.0f, 1.0f, 0.3162f, 0.8913f, 1.0f, 1.49f, 0.83f, 1.0f, 0.05f, 0.007f, new float[]{0f, 0f, 0f}, 1.2589f, 0.011f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    ROOM(new ReverbData(0.4287f, 1.0f, 0.3162f, 0.5929f, 1.0f, 0.4f, 0.83f, 1.0f, 0.1503f, 0.002f, new float[]{0f, 0f, 0f}, 1.0629f, 0.003f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    BATHROOM(new ReverbData(0.1715f, 1.0f, 0.3162f, 0.2512f, 1.0f, 1.49f, 0.54f, 1.0f, 0.6531f, 0.007f, new float[]{0f, 0f, 0f}, 3.2734f, 0.011f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    STONEROOM(new ReverbData(1.0f, 1.0f, 0.3162f, 0.7079f, 1.0f, 2.31f, 0.64f, 1.0f, 0.4411f, 0.012f, new float[]{0f, 0f, 0f}, 1.1003f, 0.017f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    AUDITORIUM(new ReverbData(1.0f, 1.0f, 0.3162f, 0.5781f, 1.0f, 4.32f, 0.59f, 1.0f, 0.4032f, 0.02f, new float[]{0f, 0f, 0f}, 0.717f, 0.03f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    CONCERTHALL(new ReverbData(1.0f, 1.0f, 0.3162f, 0.5623f, 1.0f, 3.92f, 0.7f, 1.0f, 0.2427f, 0.02f, new float[]{0f, 0f, 0f}, 0.9977f, 0.029f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    CAVE(new ReverbData(1.0f, 1.0f, 0.3162f, 1.0f, 1.0f, 2.91f, 1.3f, 1.0f, 0.5f, 0.015f, new float[]{0f, 0f, 0f}, 0.7063f, 0.022f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x0)),
    ARENA(new ReverbData(1.0f, 1.0f, 0.3162f, 0.4477f, 1.0f, 7.24f, 0.33f, 1.0f, 0.2612f, 0.02f, new float[]{0f, 0f, 0f}, 1.0186f, 0.03f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    HANGAR(new ReverbData(1.0f, 1.0f, 0.3162f, 0.3162f, 1.0f, 10.05f, 0.23f, 1.0f, 0.5f, 0.02f, new float[]{0f, 0f, 0f}, 1.256f, 0.03f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    HALLWAY(new ReverbData(0.3645f, 1.0f, 0.3162f, 0.7079f, 1.0f, 1.49f, 0.59f, 1.0f, 0.2458f, 0.007f, new float[]{0f, 0f, 0f}, 1.6615f, 0.011f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    STONECORRIDOR(new ReverbData(1.0f, 1.0f, 0.3162f, 0.7612f, 1.0f, 2.7f, 0.79f, 1.0f, 0.2472f, 0.013f, new float[]{0f, 0f, 0f}, 1.5758f, 0.02f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    ALLEY(new ReverbData(1.0f, 0.3f, 0.3162f, 0.7328f, 1.0f, 1.49f, 0.86f, 1.0f, 0.25f, 0.007f, new float[]{0f, 0f, 0f}, 0.9954f, 0.011f, new float[]{0f, 0f, 0f}, 0.125f, 0.95f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    FOREST(new ReverbData(1.0f, 0.3f, 0.3162f, 0.0224f, 1.0f, 1.49f, 0.54f, 1.0f, 0.0525f, 0.162f, new float[]{0f, 0f, 0f}, 0.7682f, 0.088f, new float[]{0f, 0f, 0f}, 0.125f, 1.0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    QUARRY(new ReverbData(1.0f, 1.0f, 0.3162f, 0.3162f, 1.0f, 1.49f, 0.83f, 1.0f, 0f, 0.061f, new float[]{0f, 0f, 0f}, 1.7783f, 0.025f, new float[]{0f, 0f, 0f}, 0.125f, 0.7f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    PARKINGLOT(new ReverbData(1.0f, 1.0f, 0.3162f, 1.0f, 1.0f, 1.65f, 1.5f, 1.0f, 0.2082f, 0.008f, new float[]{0f, 0f, 0f}, 0.2652f, 0.012f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x0)),
    SEWERPIPE(new ReverbData(0.3071f, 0.8f, 0.3162f, 0.3162f, 1.0f, 2.81f, 0.14f, 1.0f, 1.6387f, 0.014f, new float[]{0f, 0f, 0f}, 3.2471f, 0.021f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    UNDERWATER(new ReverbData(0.3645f, 1.0f, 0.3162f, 0.01f, 1.0f, 1.49f, 0.1f, 1.0f, 0.5963f, 0.007f, new float[]{0f, 0f, 0f}, 7.0795f, 0.011f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 1.18f, 0.348f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    DRUGGED(new ReverbData(0.4287f, 0.5f, 0.3162f, 1.0f, 1.0f, 8.39f, 1.39f, 1.0f, 0.876f, 0.002f, new float[]{0f, 0f, 0f}, 3.1081f, 0.03f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 1.0f, 0.9943f, 5000f, 250f, 0f, 0x0)),
    DIZZY(new ReverbData(0.3645f, 0.6f, 0.3162f, 0.631f, 1.0f, 17.23f, 0.56f, 1.0f, 0.1392f, 0.02f, new float[]{0f, 0f, 0f}, 0.4937f, 0.03f, new float[]{0f, 0f, 0f}, 0.25f, 1.0f, 0.81f, 0.31f, 0.9943f, 5000f, 250f, 0f, 0x0)),
    PSYCHOTIC(new ReverbData(0.0625f, 0.5f, 0.3162f, 0.8404f, 1.0f, 7.56f, 0.91f, 1.0f, 0.4864f, 0.02f, new float[]{0f, 0f, 0f}, 2.4378f, 0.03f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 4.0f, 1.0f, 0.9943f, 5000f, 250f, 0f, 0x0)),

    // ==========================
    // ===   Castle Presets   ===
    // ==========================
    CASTLE_SMALLROOM(new ReverbData(1.0f, 0.89f, 0.3162f, 0.3981f, 0.1f, 1.22f, 0.83f, 0.31f, 0.8913f, 0.022f, new float[]{0f, 0f, 0f}, 1.9953f, 0.011f, new float[]{0f, 0f, 0f}, 0.138f, 0.08f, 0.25f, 0f, 0.9943f, 5168.6001f, 139.5f, 0f, 0x1)),
    CASTLE_SHORTPASSAGE(new ReverbData(1.0f, 0.89f, 0.3162f, 0.3162f, 0.1f, 2.32f, 0.83f, 0.31f, 0.8913f, 0.007f, new float[]{0f, 0f, 0f}, 1.2589f, 0.023f, new float[]{0f, 0f, 0f}, 0.138f, 0.08f, 0.25f, 0f, 0.9943f, 5168.6001f, 139.5f, 0f, 0x1)),
    CASTLE_MEDIUMROOM(new ReverbData(1.0f, 0.93f, 0.3162f, 0.2818f, 0.1f, 2.04f, 0.83f, 0.46f, 0.631f, 0.022f, new float[]{0f, 0f, 0f}, 1.5849f, 0.011f, new float[]{0f, 0f, 0f}, 0.155f, 0.03f, 0.25f, 0f, 0.9943f, 5168.6001f, 139.5f, 0f, 0x1)),
    CASTLE_LARGEROOM(new ReverbData(1.0f, 0.82f, 0.3162f, 0.2818f, 0.1259f, 2.53f, 0.83f, 0.5f, 0.4467f, 0.034f, new float[]{0f, 0f, 0f}, 1.2589f, 0.016f, new float[]{0f, 0f, 0f}, 0.185f, 0.07f, 0.25f, 0f, 0.9943f, 5168.6001f, 139.5f, 0f, 0x1)),
    CASTLE_LONGPASSAGE(new ReverbData(1.0f, 0.89f, 0.3162f, 0.3981f, 0.1f, 3.42f, 0.83f, 0.31f, 0.8913f, 0.007f, new float[]{0f, 0f, 0f}, 1.4125f, 0.023f, new float[]{0f, 0f, 0f}, 0.138f, 0.08f, 0.25f, 0f, 0.9943f, 5168.6001f, 139.5f, 0f, 0x1)),
    CASTLE_HALL(new ReverbData(1.0f, 0.81f, 0.3162f, 0.2818f, 0.1778f, 3.14f, 0.79f, 0.62f, 0.1778f, 0.056f, new float[]{0f, 0f, 0f}, 1.122f, 0.024f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5168.6001f, 139.5f, 0f, 0x1)),
    CASTLE_CUPBOARD(new ReverbData(1.0f, 0.89f, 0.3162f, 0.2818f, 0.1f, 0.67f, 0.87f, 0.31f, 1.4125f, 0.01f, new float[]{0f, 0f, 0f}, 3.5481f, 0.007f, new float[]{0f, 0f, 0f}, 0.138f, 0.08f, 0.25f, 0f, 0.9943f, 5168.6001f, 139.5f, 0f, 0x1)),
    CASTLE_COURTYARD(new ReverbData(1.0f, 0.42f, 0.3162f, 0.4467f, 0.1995f, 2.13f, 0.61f, 0.23f, 0.2239f, 0.16f, new float[]{0f, 0f, 0f}, 0.7079f, 0.036f, new float[]{0f, 0f, 0f}, 0.25f, 0.37f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x0)),
    CASTLE_ALCOVE(new ReverbData(1.0f, 0.89f, 0.3162f, 0.5012f, 0.1f, 1.64f, 0.87f, 0.31f, 1.0f, 0.007f, new float[]{0f, 0f, 0f}, 1.4125f, 0.034f, new float[]{0f, 0f, 0f}, 0.138f, 0.08f, 0.25f, 0f, 0.9943f, 5168.6001f, 139.5f, 0f, 0x1)),

    // ===========================
    // ===   Factory Presets   ===
    // ===========================
    FACTORY_SMALLROOM(new ReverbData(0.3645f, 0.82f, 0.3162f, 0.7943f, 0.5012f, 1.72f, 0.65f, 1.31f, 0.7079f, 0.01f, new float[]{0f, 0f, 0f}, 1.7783f, 0.024f, new float[]{0f, 0f, 0f}, 0.119f, 0.07f, 0.25f, 0f, 0.9943f, 3762.6001f, 362.5f, 0f, 0x1)),
    FACTORY_SHORTPASSAGE(new ReverbData(0.3645f, 0.64f, 0.2512f, 0.7943f, 0.5012f, 2.53f, 0.65f, 1.31f, 1.0f, 0.01f, new float[]{0f, 0f, 0f}, 1.2589f, 0.038f, new float[]{0f, 0f, 0f}, 0.135f, 0.23f, 0.25f, 0f, 0.9943f, 3762.6001f, 362.5f, 0f, 0x1)),
    FACTORY_MEDIUMROOM(new ReverbData(0.4287f, 0.82f, 0.2512f, 0.7943f, 0.5012f, 2.76f, 0.65f, 1.31f, 0.2818f, 0.022f, new float[]{0f, 0f, 0f}, 1.4125f, 0.023f, new float[]{0f, 0f, 0f}, 0.174f, 0.07f, 0.25f, 0f, 0.9943f, 3762.6001f, 362.5f, 0f, 0x1)),
    FACTORY_LARGEROOM(new ReverbData(0.4287f, 0.75f, 0.2512f, 0.7079f, 0.631f, 4.24f, 0.51f, 1.31f, 0.1778f, 0.039f, new float[]{0f, 0f, 0f}, 1.122f, 0.023f, new float[]{0f, 0f, 0f}, 0.231f, 0.07f, 0.25f, 0f, 0.9943f, 3762.6001f, 362.5f, 0f, 0x1)),
    FACTORY_LONGPASSAGE(new ReverbData(0.3645f, 0.64f, 0.2512f, 0.7943f, 0.5012f, 4.06f, 0.65f, 1.31f, 1.0f, 0.02f, new float[]{0f, 0f, 0f}, 1.2589f, 0.037f, new float[]{0f, 0f, 0f}, 0.135f, 0.23f, 0.25f, 0f, 0.9943f, 3762.6001f, 362.5f, 0f, 0x1)),
    FACTORY_HALL(new ReverbData(0.4287f, 0.75f, 0.3162f, 0.7079f, 0.631f, 7.43f, 0.51f, 1.31f, 0.0631f, 0.073f, new float[]{0f, 0f, 0f}, 0.8913f, 0.027f, new float[]{0f, 0f, 0f}, 0.25f, 0.07f, 0.25f, 0f, 0.9943f, 3762.6001f, 362.5f, 0f, 0x1)),
    FACTORY_CUPBOARD(new ReverbData(0.3071f, 0.63f, 0.2512f, 0.7943f, 0.5012f, 0.49f, 0.65f, 1.31f, 1.2589f, 0.01f, new float[]{0f, 0f, 0f}, 1.9953f, 0.032f, new float[]{0f, 0f, 0f}, 0.107f, 0.07f, 0.25f, 0f, 0.9943f, 3762.6001f, 362.5f, 0f, 0x1)),
    FACTORY_COURTYARD(new ReverbData(0.3071f, 0.57f, 0.3162f, 0.3162f, 0.631f, 2.32f, 0.29f, 0.56f, 0.2239f, 0.14f, new float[]{0f, 0f, 0f}, 0.3981f, 0.039f, new float[]{0f, 0f, 0f}, 0.25f, 0.29f, 0.25f, 0f, 0.9943f, 3762.6001f, 362.5f, 0f, 0x1)),
    FACTORY_ALCOVE(new ReverbData(0.3645f, 0.59f, 0.2512f, 0.7943f, 0.5012f, 3.14f, 0.65f, 1.31f, 1.4125f, 0.01f, new float[]{0f, 0f, 0f}, 1.0f, 0.038f, new float[]{0f, 0f, 0f}, 0.114f, 0.1f, 0.25f, 0f, 0.9943f, 3762.6001f, 362.5f, 0f, 0x1)),

    // ==============================
    // ===   Ice Palace Presets   ===
    // ==============================
    ICEPALACE_SMALLROOM(new ReverbData(1.0f, 0.84f, 0.3162f, 0.5623f, 0.2818f, 1.51f, 1.53f, 0.27f, 0.8913f, 0.01f, new float[]{0f, 0f, 0f}, 1.4125f, 0.011f, new float[]{0f, 0f, 0f}, 0.164f, 0.14f, 0.25f, 0f, 0.9943f, 12428.5f, 99.6f, 0f, 0x1)),
    ICEPALACE_SHORTPASSAGE(new ReverbData(1.0f, 0.75f, 0.3162f, 0.5623f, 0.2818f, 1.79f, 1.46f, 0.28f, 0.5012f, 0.01f, new float[]{0f, 0f, 0f}, 1.122f, 0.019f, new float[]{0f, 0f, 0f}, 0.177f, 0.09f, 0.25f, 0f, 0.9943f, 12428.5f, 99.6f, 0f, 0x1)),
    ICEPALACE_MEDIUMROOM(new ReverbData(1.0f, 0.87f, 0.3162f, 0.5623f, 0.4467f, 2.22f, 1.53f, 0.32f, 0.3981f, 0.039f, new float[]{0f, 0f, 0f}, 1.122f, 0.027f, new float[]{0f, 0f, 0f}, 0.186f, 0.12f, 0.25f, 0f, 0.9943f, 12428.5f, 99.6f, 0f, 0x1)),
    ICEPALACE_LARGEROOM(new ReverbData(1.0f, 0.81f, 0.3162f, 0.5623f, 0.4467f, 3.14f, 1.53f, 0.32f, 0.2512f, 0.039f, new float[]{0f, 0f, 0f}, 1.0f, 0.027f, new float[]{0f, 0f, 0f}, 0.214f, 0.11f, 0.25f, 0f, 0.9943f, 12428.5f, 99.6f, 0f, 0x1)),
    ICEPALACE_LONGPASSAGE(new ReverbData(1.0f, 0.77f, 0.3162f, 0.5623f, 0.3981f, 3.01f, 1.46f, 0.28f, 0.7943f, 0.012f, new float[]{0f, 0f, 0f}, 1.2589f, 0.025f, new float[]{0f, 0f, 0f}, 0.186f, 0.04f, 0.25f, 0f, 0.9943f, 12428.5f, 99.6f, 0f, 0x1)),
    ICEPALACE_HALL(new ReverbData(1.0f, 0.76f, 0.3162f, 0.4467f, 0.5623f, 5.49f, 1.53f, 0.38f, 0.1122f, 0.054f, new float[]{0f, 0f, 0f}, 0.631f, 0.052f, new float[]{0f, 0f, 0f}, 0.226f, 0.11f, 0.25f, 0f, 0.9943f, 12428.5f, 99.6f, 0f, 0x1)),
    ICEPALACE_CUPBOARD(new ReverbData(1.0f, 0.83f, 0.3162f, 0.5012f, 0.2239f, 0.76f, 1.53f, 0.26f, 1.122f, 0.012f, new float[]{0f, 0f, 0f}, 1.9953f, 0.016f, new float[]{0f, 0f, 0f}, 0.143f, 0.08f, 0.25f, 0f, 0.9943f, 12428.5f, 99.6f, 0f, 0x1)),
    ICEPALACE_COURTYARD(new ReverbData(1.0f, 0.59f, 0.3162f, 0.2818f, 0.3162f, 2.04f, 1.2f, 0.38f, 0.3162f, 0.173f, new float[]{0f, 0f, 0f}, 0.3162f, 0.043f, new float[]{0f, 0f, 0f}, 0.235f, 0.48f, 0.25f, 0f, 0.9943f, 12428.5f, 99.6f, 0f, 0x1)),
    ICEPALACE_ALCOVE(new ReverbData(1.0f, 0.84f, 0.3162f, 0.5623f, 0.2818f, 2.76f, 1.46f, 0.28f, 1.122f, 0.01f, new float[]{0f, 0f, 0f}, 0.8913f, 0.03f, new float[]{0f, 0f, 0f}, 0.161f, 0.09f, 0.25f, 0f, 0.9943f, 12428.5f, 99.6f, 0f, 0x1)),

    // =================================
    // ===   Space Station Presets   ===
    // =================================
    SPACESTATION_SMALLROOM(new ReverbData(0.2109f, 0.7f, 0.3162f, 0.7079f, 0.8913f, 1.72f, 0.82f, 0.55f, 0.7943f, 0.007f, new float[]{0f, 0f, 0f}, 1.4125f, 0.013f, new float[]{0f, 0f, 0f}, 0.188f, 0.26f, 0.25f, 0f, 0.9943f, 3316.1001f, 458.2f, 0f, 0x1)),
    SPACESTATION_SHORTPASSAGE(new ReverbData(0.2109f, 0.87f, 0.3162f, 0.631f, 0.8913f, 3.57f, 0.5f, 0.55f, 1.0f, 0.012f, new float[]{0f, 0f, 0f}, 1.122f, 0.016f, new float[]{0f, 0f, 0f}, 0.172f, 0.2f, 0.25f, 0f, 0.9943f, 3316.1001f, 458.2f, 0f, 0x1)),
    SPACESTATION_MEDIUMROOM(new ReverbData(0.2109f, 0.75f, 0.3162f, 0.631f, 0.8913f, 3.01f, 0.5f, 0.55f, 0.3981f, 0.034f, new float[]{0f, 0f, 0f}, 1.122f, 0.035f, new float[]{0f, 0f, 0f}, 0.209f, 0.31f, 0.25f, 0f, 0.9943f, 3316.1001f, 458.2f, 0f, 0x1)),
    SPACESTATION_LARGEROOM(new ReverbData(0.3645f, 0.81f, 0.3162f, 0.631f, 0.8913f, 3.89f, 0.38f, 0.61f, 0.3162f, 0.056f, new float[]{0f, 0f, 0f}, 0.8913f, 0.035f, new float[]{0f, 0f, 0f}, 0.233f, 0.28f, 0.25f, 0f, 0.9943f, 3316.1001f, 458.2f, 0f, 0x1)),
    SPACESTATION_LONGPASSAGE(new ReverbData(0.4287f, 0.82f, 0.3162f, 0.631f, 0.8913f, 4.62f, 0.62f, 0.55f, 1.0f, 0.012f, new float[]{0f, 0f, 0f}, 1.2589f, 0.031f, new float[]{0f, 0f, 0f}, 0.25f, 0.23f, 0.25f, 0f, 0.9943f, 3316.1001f, 458.2f, 0f, 0x1)),
    SPACESTATION_HALL(new ReverbData(0.4287f, 0.87f, 0.3162f, 0.631f, 0.8913f, 7.11f, 0.38f, 0.61f, 0.1778f, 0.1f, new float[]{0f, 0f, 0f}, 0.631f, 0.047f, new float[]{0f, 0f, 0f}, 0.25f, 0.25f, 0.25f, 0f, 0.9943f, 3316.1001f, 458.2f, 0f, 0x1)),
    SPACESTATION_CUPBOARD(new ReverbData(0.1715f, 0.56f, 0.3162f, 0.7079f, 0.8913f, 0.79f, 0.81f, 0.55f, 1.4125f, 0.007f, new float[]{0f, 0f, 0f}, 1.7783f, 0.018f, new float[]{0f, 0f, 0f}, 0.181f, 0.31f, 0.25f, 0f, 0.9943f, 3316.1001f, 458.2f, 0f, 0x1)),
    SPACESTATION_ALCOVE(new ReverbData(0.2109f, 0.78f, 0.3162f, 0.7079f, 0.8913f, 1.16f, 0.81f, 0.55f, 1.4125f, 0.007f, new float[]{0f, 0f, 0f}, 1.0f, 0.018f, new float[]{0f, 0f, 0f}, 0.192f, 0.21f, 0.25f, 0f, 0.9943f, 3316.1001f, 458.2f, 0f, 0x1)),

    // ==================================
    // ===   Wooden Galleon Presets   ===
    // ==================================
    WOODEN_SMALLROOM(new ReverbData(1.0f, 1.0f, 0.3162f, 0.1122f, 0.3162f, 0.79f, 0.32f, 0.87f, 1.0f, 0.032f, new float[]{0f, 0f, 0f}, 0.8913f, 0.029f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 4705.0f, 99.6f, 0f, 0x1)),
    WOODEN_SHORTPASSAGE(new ReverbData(1.0f, 1.0f, 0.3162f, 0.1259f, 0.3162f, 1.75f, 0.5f, 0.87f, 0.8913f, 0.012f, new float[]{0f, 0f, 0f}, 0.631f, 0.024f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 4705.0f, 99.6f, 0f, 0x1)),
    WOODEN_MEDIUMROOM(new ReverbData(1.0f, 1.0f, 0.3162f, 0.1f, 0.2818f, 1.47f, 0.42f, 0.82f, 0.8913f, 0.049f, new float[]{0f, 0f, 0f}, 0.8913f, 0.029f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 4705.0f, 99.6f, 0f, 0x1)),
    WOODEN_LARGEROOM(new ReverbData(1.0f, 1.0f, 0.3162f, 0.0891f, 0.2818f, 2.65f, 0.33f, 0.82f, 0.8913f, 0.066f, new float[]{0f, 0f, 0f}, 0.7943f, 0.049f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 4705.0f, 99.6f, 0f, 0x1)),
    WOODEN_LONGPASSAGE(new ReverbData(1.0f, 1.0f, 0.3162f, 0.1f, 0.3162f, 1.99f, 0.4f, 0.79f, 1.0f, 0.02f, new float[]{0f, 0f, 0f}, 0.4467f, 0.036f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 4705.0f, 99.6f, 0f, 0x1)),
    WOODEN_HALL(new ReverbData(1.0f, 1.0f, 0.3162f, 0.0794f, 0.2818f, 3.45f, 0.3f, 0.82f, 0.8913f, 0.088f, new float[]{0f, 0f, 0f}, 0.7943f, 0.063f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 4705.0f, 99.6f, 0f, 0x1)),
    WOODEN_CUPBOARD(new ReverbData(1.0f, 1.0f, 0.3162f, 0.1413f, 0.3162f, 0.56f, 0.46f, 0.91f, 1.122f, 0.012f, new float[]{0f, 0f, 0f}, 1.122f, 0.028f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 4705.0f, 99.6f, 0f, 0x1)),
    WOODEN_COURTYARD(new ReverbData(1.0f, 0.65f, 0.3162f, 0.0794f, 0.3162f, 1.79f, 0.35f, 0.79f, 0.5623f, 0.123f, new float[]{0f, 0f, 0f}, 0.1f, 0.032f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 4705.0f, 99.6f, 0f, 0x1)),
    WOODEN_ALCOVE(new ReverbData(1.0f, 1.0f, 0.3162f, 0.1259f, 0.3162f, 1.22f, 0.62f, 0.91f, 1.122f, 0.012f, new float[]{0f, 0f, 0f}, 0.7079f, 0.024f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 4705.0f, 99.6f, 0f, 0x1)),

    // ==========================
    // ===   Sports Presets   ===
    // ==========================
    SPORT_EMPTYSTADIUM(new ReverbData(1.0f, 1.0f, 0.3162f, 0.4467f, 0.7943f, 6.26f, 0.51f, 1.1f, 0.0631f, 0.183f, new float[]{0f, 0f, 0f}, 0.3981f, 0.038f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    SPORT_SQUASHCOURT(new ReverbData(1.0f, 0.75f, 0.3162f, 0.3162f, 0.7943f, 2.22f, 0.91f, 1.16f, 0.4467f, 0.007f, new float[]{0f, 0f, 0f}, 0.7943f, 0.011f, new float[]{0f, 0f, 0f}, 0.126f, 0.19f, 0.25f, 0f, 0.9943f, 7176.8999f, 211.2f, 0f, 0x1)),
    SPORT_SMALLSWIMMINGPOOL(new ReverbData(1.0f, 0.7f, 0.3162f, 0.7943f, 0.8913f, 2.76f, 1.25f, 1.14f, 0.631f, 0.02f, new float[]{0f, 0f, 0f}, 0.7943f, 0.03f, new float[]{0f, 0f, 0f}, 0.179f, 0.15f, 0.895f, 0.19f, 0.9943f, 5000f, 250f, 0f, 0x0)),
    SPORT_LARGESWIMMINGPOOL(new ReverbData(1.0f, 0.82f, 0.3162f, 0.7943f, 1.0f, 5.49f, 1.31f, 1.14f, 0.4467f, 0.039f, new float[]{0f, 0f, 0f}, 0.5012f, 0.049f, new float[]{0f, 0f, 0f}, 0.222f, 0.55f, 1.159f, 0.21f, 0.9943f, 5000f, 250f, 0f, 0x0)),
    SPORT_GYMNASIUM(new ReverbData(1.0f, 0.81f, 0.3162f, 0.4467f, 0.8913f, 3.14f, 1.06f, 1.35f, 0.3981f, 0.029f, new float[]{0f, 0f, 0f}, 0.5623f, 0.045f, new float[]{0f, 0f, 0f}, 0.146f, 0.14f, 0.25f, 0f, 0.9943f, 7176.8999f, 211.2f, 0f, 0x1)),
    SPORT_FULLSTADIUM(new ReverbData(1.0f, 1.0f, 0.3162f, 0.0708f, 0.7943f, 5.25f, 0.17f, 0.8f, 0.1f, 0.188f, new float[]{0f, 0f, 0f}, 0.2818f, 0.038f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    SPORT_STADIUMTANNOY(new ReverbData(1.0f, 0.78f, 0.3162f, 0.5623f, 0.5012f, 2.53f, 0.88f, 0.68f, 0.2818f, 0.23f, new float[]{0f, 0f, 0f}, 0.5012f, 0.063f, new float[]{0f, 0f, 0f}, 0.25f, 0.2f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),

    // =================================
    // ===   Dome and Pipe Presets   ===
    // =================================
    DOME_TOMB(new ReverbData(1.0f, 0.79f, 0.3162f, 0.3548f, 0.2239f, 4.18f, 0.21f, 0.1f, 0.3868f, 0.03f, new float[]{0f, 0f, 0f}, 1.6788f, 0.022f, new float[]{0f, 0f, 0f}, 0.177f, 0.19f, 0.25f, 0f, 0.9943f, 2854.3999f, 20f, 0f, 0x0)),
    PIPE_SMALL(new ReverbData(1.0f, 1.0f, 0.3162f, 0.3548f, 0.2239f, 5.04f, 0.1f, 0.1f, 0.5012f, 0.032f, new float[]{0f, 0f, 0f}, 2.5119f, 0.015f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 2854.3999f, 20f, 0f, 0x1)),
    DOME_SAINTPAULS(new ReverbData(1.0f, 0.87f, 0.3162f, 0.3548f, 0.2239f, 10.48f, 0.19f, 0.1f, 0.1778f, 0.09f, new float[]{0f, 0f, 0f}, 1.2589f, 0.042f, new float[]{0f, 0f, 0f}, 0.25f, 0.12f, 0.25f, 0f, 0.9943f, 2854.3999f, 20f, 0f, 0x1)),
    PIPE_LONGTHIN(new ReverbData(0.256f, 0.91f, 0.3162f, 0.4467f, 0.2818f, 9.21f, 0.18f, 0.1f, 0.7079f, 0.01f, new float[]{0f, 0f, 0f}, 0.7079f, 0.022f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 2854.3999f, 20f, 0f, 0x0)),
    PIPE_LARGE(new ReverbData(1.0f, 1.0f, 0.3162f, 0.3548f, 0.2239f, 8.45f, 0.1f, 0.1f, 0.3981f, 0.046f, new float[]{0f, 0f, 0f}, 1.5849f, 0.032f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 2854.3999f, 20f, 0f, 0x1)),
    PIPE_RESONANT(new ReverbData(0.1373f, 0.91f, 0.3162f, 0.4467f, 0.2818f, 6.81f, 0.18f, 0.1f, 0.7079f, 0.01f, new float[]{0f, 0f, 0f}, 1.0f, 0.022f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 2854.3999f, 20f, 0f, 0x0)),

    // ============================
    // ===   Outdoors Presets   ===
    // ============================
    OUTDOORS_BACKYARD(new ReverbData(1.0f, 0.45f, 0.3162f, 0.2512f, 0.5012f, 1.12f, 0.34f, 0.46f, 0.4467f, 0.069f, new float[]{0f, 0f, -0f}, 0.7079f, 0.023f, new float[]{0f, 0f, 0f}, 0.218f, 0.34f, 0.25f, 0f, 0.9943f, 4399.1001f, 242.9f, 0f, 0x0)),
    OUTDOORS_ROLLINGPLAINS(new ReverbData(1.0f, 0f, 0.3162f, 0.0112f, 0.631f, 2.13f, 0.21f, 0.46f, 0.1778f, 0.3f, new float[]{0f, 0f, -0f}, 0.4467f, 0.019f, new float[]{0f, 0f, 0f}, 0.25f, 1.0f, 0.25f, 0f, 0.9943f, 4399.1001f, 242.9f, 0f, 0x0)),
    OUTDOORS_DEEPCANYON(new ReverbData(1.0f, 0.74f, 0.3162f, 0.1778f, 0.631f, 3.89f, 0.21f, 0.46f, 0.3162f, 0.223f, new float[]{0f, 0f, -0f}, 0.3548f, 0.019f, new float[]{0f, 0f, 0f}, 0.25f, 1.0f, 0.25f, 0f, 0.9943f, 4399.1001f, 242.9f, 0f, 0x0)),
    OUTDOORS_CREEK(new ReverbData(1.0f, 0.35f, 0.3162f, 0.1778f, 0.5012f, 2.13f, 0.21f, 0.46f, 0.3981f, 0.115f, new float[]{0f, 0f, -0f}, 0.1995f, 0.031f, new float[]{0f, 0f, 0f}, 0.218f, 0.34f, 0.25f, 0f, 0.9943f, 4399.1001f, 242.9f, 0f, 0x0)),
    OUTDOORS_VALLEY(new ReverbData(1.0f, 0.28f, 0.3162f, 0.0282f, 0.1585f, 2.88f, 0.26f, 0.35f, 0.1413f, 0.263f, new float[]{0f, 0f, -0f}, 0.3981f, 0.1f, new float[]{0f, 0f, 0f}, 0.25f, 0.34f, 0.25f, 0f, 0.9943f, 2854.3999f, 107.5f, 0f, 0x0)),

    // ========================
    // ===   Mood Presets   ===
    // ========================
    MOOD_HEAVEN(new ReverbData(1.0f, 0.94f, 0.3162f, 0.7943f, 0.4467f, 5.04f, 1.12f, 0.56f, 0.2427f, 0.02f, new float[]{0f, 0f, 0f}, 1.2589f, 0.029f, new float[]{0f, 0f, 0f}, 0.25f, 0.08f, 2.742f, 0.05f, 0.9977f, 5000f, 250f, 0f, 0x1)),
    MOOD_HELL(new ReverbData(1.0f, 0.57f, 0.3162f, 0.3548f, 0.4467f, 3.57f, 0.49f, 2.0f, 0f, 0.02f, new float[]{0f, 0f, 0f}, 1.4125f, 0.03f, new float[]{0f, 0f, 0f}, 0.11f, 0.04f, 2.109f, 0.52f, 0.9943f, 5000f, 139.5f, 0f, 0x0)),
    MOOD_MEMORY(new ReverbData(1.0f, 0.85f, 0.3162f, 0.631f, 0.3548f, 4.06f, 0.82f, 0.56f, 0.0398f, 0f, new float[]{0f, 0f, 0f}, 1.122f, 0f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.474f, 0.45f, 0.9886f, 5000f, 250f, 0f, 0x0)),

    // ===========================
    // ===   Driving Presets   ===
    // ===========================
    DRIVING_PITGARAGE(new ReverbData(0.4287f, 0.59f, 0.3162f, 0.7079f, 0.5623f, 1.72f, 0.93f, 0.87f, 0.5623f, 0f, new float[]{0f, 0f, 0f}, 1.2589f, 0.016f, new float[]{0f, 0f, 0f}, 0.25f, 0.11f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x0)),
    DRIVING_INCAR_RACER(new ReverbData(0.0832f, 0.8f, 0.3162f, 1.0f, 0.7943f, 0.17f, 2.0f, 0.41f, 1.7783f, 0.007f, new float[]{0f, 0f, 0f}, 0.7079f, 0.015f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 10268.2002f, 251.0f, 0f, 0x1)),
    DRIVING_FULLGRANDSTAND(new ReverbData(1.0f, 1.0f, 0.3162f, 0.2818f, 0.631f, 3.01f, 1.37f, 1.28f, 0.3548f, 0.09f, new float[]{0f, 0f, 0f}, 0.1778f, 0.049f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 10420.2002f, 250f, 0f, 0x0)),
    DRIVING_EMPTYGRANDSTAND(new ReverbData(1.0f, 1.0f, 0.3162f, 1.0f, 0.7943f, 4.62f, 1.75f, 1.4f, 0.2082f, 0.09f, new float[]{0f, 0f, 0f}, 0.2512f, 0.049f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0f, 0.9943f, 10420.2002f, 250f, 0f, 0x0)),
    DRIVING_TUNNEL(new ReverbData(1.0f, 0.81f, 0.3162f, 0.3981f, 0.8913f, 3.42f, 0.94f, 1.31f, 0.7079f, 0.051f, new float[]{0f, 0f, 0f}, 0.7079f, 0.047f, new float[]{0f, 0f, 0f}, 0.214f, 0.05f, 0.25f, 0f, 0.9943f, 5000f, 155.3f, 0f, 0x1)),

    // ========================
    // ===   City Presets   ===
    // ========================
    CITY_STREETS(new ReverbData(1.0f, 0.78f, 0.3162f, 0.7079f, 0.8913f, 1.79f, 1.12f, 0.91f, 0.2818f, 0.046f, new float[]{0f, 0f, 0f}, 0.1995f, 0.028f, new float[]{0f, 0f, 0f}, 0.25f, 0.2f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    CITY_SUBWAY(new ReverbData(1.0f, 0.74f, 0.3162f, 0.7079f, 0.8913f, 3.01f, 1.23f, 0.91f, 0.7079f, 0.046f, new float[]{0f, 0f, 0f}, 1.2589f, 0.028f, new float[]{0f, 0f, 0f}, 0.125f, 0.21f, 0.25f, 0f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    CITY_MUSEUM(new ReverbData(1.0f, 0.82f, 0.3162f, 0.1778f, 0.1778f, 3.28f, 1.4f, 0.57f, 0.2512f, 0.039f, new float[]{0f, 0f, -0f}, 0.8913f, 0.034f, new float[]{0f, 0f, 0f}, 0.13f, 0.17f, 0.25f, 0f, 0.9943f, 2854.3999f, 107.5f, 0f, 0x0)),
    CITY_LIBRARY(new ReverbData(1.0f, 0.82f, 0.3162f, 0.2818f, 0.0891f, 2.76f, 0.89f, 0.41f, 0.3548f, 0.029f, new float[]{0f, 0f, -0f}, 0.8913f, 0.02f, new float[]{0f, 0f, 0f}, 0.13f, 0.17f, 0.25f, 0f, 0.9943f, 2854.3999f, 107.5f, 0f, 0x0)),
    CITY_UNDERPASS(new ReverbData(1.0f, 0.82f, 0.3162f, 0.4467f, 0.8913f, 3.57f, 1.12f, 0.91f, 0.3981f, 0.059f, new float[]{0f, 0f, 0f}, 0.8913f, 0.037f, new float[]{0f, 0f, 0f}, 0.25f, 0.14f, 0.25f, 0f, 0.992f, 5000f, 250f, 0f, 0x1)),
    CITY_ABANDONED(new ReverbData(1.0f, 0.69f, 0.3162f, 0.7943f, 0.8913f, 3.28f, 1.17f, 0.91f, 0.4467f, 0.044f, new float[]{0f, 0f, 0f}, 0.2818f, 0.024f, new float[]{0f, 0f, 0f}, 0.25f, 0.2f, 0.25f, 0f, 0.9966f, 5000f, 250f, 0f, 0x1)),

    // =========================
    // ===   Misc. Presets   ===
    // =========================
    DUSTYROOM(new ReverbData(0.3645f, 0.56f, 0.3162f, 0.7943f, 0.7079f, 1.79f, 0.38f, 0.21f, 0.5012f, 0.002f, new float[]{0f, 0f, 0f}, 1.2589f, 0.006f, new float[]{0f, 0f, 0f}, 0.202f, 0.05f, 0.25f, 0f, 0.9886f, 13046.0f, 163.3f, 0f, 0x1)),
    CHAPEL(new ReverbData(1.0f, 0.84f, 0.3162f, 0.5623f, 1.0f, 4.62f, 0.64f, 1.23f, 0.4467f, 0.032f, new float[]{0f, 0f, 0f}, 0.7943f, 0.049f, new float[]{0f, 0f, 0f}, 0.25f, 0f, 0.25f, 0.11f, 0.9943f, 5000f, 250f, 0f, 0x1)),
    SMALLWATERROOM(new ReverbData(1.0f, 0.7f, 0.3162f, 0.4477f, 1.0f, 1.51f, 1.25f, 1.14f, 0.8913f, 0.02f, new float[]{0f, 0f, 0f}, 1.4125f, 0.03f, new float[]{0f, 0f, 0f}, 0.179f, 0.15f, 0.895f, 0.19f, 0.992f, 5000f, 250f, 0f, 0x0)),
    ;

    // Internal

    private final ReverbData data;

    ReverbPreset(ReverbData data) {
        this.data = data;
    }

    public ReverbData getData() {
        return data;
    }

}
