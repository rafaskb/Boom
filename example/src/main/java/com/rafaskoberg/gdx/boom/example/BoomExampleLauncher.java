package com.rafaskoberg.gdx.boom.example;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class BoomExampleLauncher {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Boom Example");
        config.setWindowedMode(1024, 576);
        config.setAudioConfig(128, 4096, 9); // Increase libGDX's default audio limits which are pretty low
        config.setResizable(false);
        new Lwjgl3Application(new BoomExample(), config);
    }

}
