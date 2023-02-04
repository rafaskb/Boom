package com.rafaskoberg.gdx.boom.example.widgets;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.rafaskoberg.gdx.boom.example.BoomExample;
import com.rafaskoberg.gdx.boom.example.util.Utils;

public class AudioPlayerWidget extends Table {
    private final BoomExample app;

    public AudioPlayerWidget(BoomExample app) {
        this.app = app;

        // Voice Label
        VisLabel voiceLabel = new VisLabel("Voice", "small-font", "t-white");
        voiceLabel.setAlignment(Align.center);

        // Music Label
        VisLabel musicLabel = new VisLabel("Music", "small-font", "t-white");
        musicLabel.setAlignment(Align.center);

        // Ambience Label
        VisLabel ambienceLabel = new VisLabel("Ambience", "small-font", "t-white");
        ambienceLabel.setAlignment(Align.center);

        // Play Voice
        VisImageButton playVoiceButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("play"), true));
        Utils.addSoundToButton(playVoiceButton, app.boom, app.uiClickSound);
        playVoiceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.onPlayVoiceButton();
            }
        });

        // Play Music
        VisImageButton playMusicButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("play"), true));
        Utils.addSoundToButton(playMusicButton, app.boom, app.uiClickSound);
        playMusicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.onPlayMusicButton();
            }
        });

        // Play Ambience
        VisImageButton playAmbienceButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("play"), true));
        Utils.addSoundToButton(playAmbienceButton, app.boom, app.uiClickSound);
        playAmbienceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.onPlayAmbienceButton();
            }
        });

        // Configure widget
        background(Utils.createColorDrawable(VisUI.getSkin().getColor("t-dark"), 0.33f));
        pad(5);
        defaults().pad(5).center().growX().uniform();

        // Populate widget
        add(voiceLabel);
        add(musicLabel);
        add(ambienceLabel);
        row();
        add(playVoiceButton).growY();
        add(playMusicButton).growY();
        add(playAmbienceButton).growY();
    }

}
