package com.rafaskoberg.gdx.boom.example.effect_explorer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.rafaskoberg.boom.Boom;
import com.rafaskoberg.boom.BoomChannel;
import com.rafaskoberg.boom.effect.AutoWahData;
import com.rafaskoberg.boom.effect.AutoWahPreset;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.BoomEffectData;
import com.rafaskoberg.boom.effect.BoomEffectPreset;
import com.rafaskoberg.boom.effect.ChorusData;
import com.rafaskoberg.boom.effect.ChorusPreset;
import com.rafaskoberg.boom.effect.CompressorData;
import com.rafaskoberg.boom.effect.DistortionData;
import com.rafaskoberg.boom.effect.DistortionPreset;
import com.rafaskoberg.boom.effect.EchoData;
import com.rafaskoberg.boom.effect.EchoPreset;
import com.rafaskoberg.boom.effect.EffectType;
import com.rafaskoberg.boom.effect.FlangerData;
import com.rafaskoberg.boom.effect.FlangerPreset;
import com.rafaskoberg.boom.effect.PitchShifterData;
import com.rafaskoberg.boom.effect.PitchShifterPreset;
import com.rafaskoberg.boom.effect.ReverbData;
import com.rafaskoberg.boom.effect.ReverbPreset;
import com.rafaskoberg.boom.effect.RingModulatorData;
import com.rafaskoberg.boom.effect.RingModulatorPreset;
import com.rafaskoberg.boom.effect.VocalMorpherData;
import com.rafaskoberg.boom.effect.VocalMorpherPreset;
import com.rafaskoberg.gdx.boom.example.util.Utils;

import java.util.function.Consumer;

public class EffectExplorer extends ApplicationAdapter {
    private static final float SLIDER_LABEL_WIDTH = 250f;

    public Boom boom;
    private BoomChannel boomChannel;
    private EffectType currentEffect;
    private BoomEffect boomEffect;
    private BoomEffectData boomEffectData;
    private BoomEffectPreset currentPreset;
    private int currentPresetIndex;
    private Stage stage;
    private Sound voiceSound;
    public Sound uiClickSound;
    private Music music;
    private Music ambience;
    private boolean isVoicePlaying;
    private long voiceSoundId;
    private VisLabel effectLabel;
    private VisLabel presetLabel;
    private Container<Table> container;

    @Override
    public void create() {
        // Init Boom
        boom = Boom.init();
        boomChannel = boom.createChannel(0);

        // Load audio
        voiceSound = Gdx.audio.newSound(Gdx.files.internal("audio/boom_voice.wav"));
        uiClickSound = Gdx.audio.newSound(Gdx.files.internal("audio/ui_click.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("audio/club_seamus.ogg"));
        music.setLooping(true);
        music.setVolume(0.4f);
        ambience = Gdx.audio.newMusic(Gdx.files.internal("audio/restaurant.ogg"));
        ambience.setLooping(true);
        ambience.setVolume(0.15f);

        // Load VisUI
        VisUI.load(Gdx.files.internal("skin/tinted.json"));

        // Create batch and stage
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create UI
        createUi();

        // Set current effect
        setEffect(EffectType.values()[0]);
    }

    private void createUi() {
        // Create root table
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        this.stage.addActor(rootTable);

        // Audio Widget
        Table audioWidget = createAudioWidget();
        audioWidget.pack();

        // Effect selector
        Table effectSelector = createEffectSelector();
        effectSelector.pack();

        // Preset selector
        Table presetSelector = createPresetSelector();
        effectSelector.pack();

        // Container
        container = new Container<>();
        container.fill();

        // Build widget
        rootTable.add(audioWidget).growX().row();
        rootTable.add(effectSelector).growX().row();
        rootTable.add(container).grow().row();
        rootTable.add(presetSelector).growX();
    }

    private Table createAudioWidget() {
        Table table = new Table();

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
        VisImageButton playVoiceButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("play-small"), true));
        Utils.addSoundToButton(playVoiceButton, boom, uiClickSound);
        playVoiceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(isVoicePlaying) {
                    voiceSound.stop();
                    voiceSoundId = 0;
                } else {
                    voiceSoundId = boom.loop(voiceSound, boomChannel);
                }
                isVoicePlaying = !isVoicePlaying;
            }
        });

        // Play Music
        VisImageButton playMusicButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("play-small"), true));
        Utils.addSoundToButton(playMusicButton, boom, uiClickSound);
        playMusicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(music.isPlaying()) {
                    music.stop();
                } else {
                    boom.play(music, boomChannel);
                }
            }
        });

        // Play Ambience
        VisImageButton playAmbienceButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("play-small"), true));
        Utils.addSoundToButton(playAmbienceButton, boom, uiClickSound);
        playAmbienceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(ambience.isPlaying()) {
                    ambience.stop();
                } else {
                    boom.play(ambience, boomChannel);
                }
            }
        });

        // Configure widget
        table.background(Utils.createColorDrawable(VisUI.getSkin().getColor("t-dark"), 0.33f));
        table.pad(5);
        table.defaults().pad(5).center().growX().uniform();

        // Populate widget
        table.add(voiceLabel);
        table.add(musicLabel);
        table.add(ambienceLabel);
        table.row();
        table.add(playVoiceButton).growY();
        table.add(playMusicButton).growY();
        table.add(playAmbienceButton).growY();

        return table;
    }

    private Table createEffectSelector() {
        Table table = new Table();

        // Previous button
        VisImageButton previousButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("previous")));
        Utils.addSoundToButton(previousButton, boom, uiClickSound);
        previousButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onEffectButton(false);
            }
        });

        // Next button
        VisImageButton nextButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("next")));
        Utils.addSoundToButton(nextButton, boom, uiClickSound);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onEffectButton(true);
            }
        });

        // Effect Label
        effectLabel = new VisLabel("", "default-font", "t-white");
        effectLabel.setAlignment(Align.center);

        // Configure widget
        table.background(Utils.createColorDrawable(VisUI.getSkin().getColor("t-dark"), 0.66f));
        table.pad(5);
        table.defaults().uniformX().expand();

        // Populate widget
        table.defaults().pad(5).space(0, 10, 0, 10).center().expandY();
        table.add(previousButton).uniformX();
        table.add(effectLabel).grow();
        table.add(nextButton).uniformX();

        return table;
    }

    private Table createPresetSelector() {
        Table table = new Table();

        // Previous button
        VisImageButton previousButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("previous")));
        Utils.addSoundToButton(previousButton, boom, uiClickSound);
        previousButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onPresetButton(false);
            }
        });

        // Next button
        VisImageButton nextButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("next")));
        Utils.addSoundToButton(nextButton, boom, uiClickSound);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onPresetButton(true);
            }
        });

        // Preset Label
        presetLabel = new VisLabel("", "small-font", "t-white");
        presetLabel.setAlignment(Align.center);

        // Configure widget
        table.background(Utils.createColorDrawable(VisUI.getSkin().getColor("t-dark"), 0.5f));
        table.pad(5);
        table.defaults().uniformX().expand();

        // Populate widget
        table.defaults().pad(5).space(0, 10, 0, 10).center().expandY();
        table.add(previousButton).uniformX();
        table.add(presetLabel).grow();
        table.add(nextButton).uniformX();

        return table;
    }

    private Table createEffectTable(EffectType effectType) {
        Table table = new Table();
        table.defaults().growX().top().pad(10).uniformY();

        // Create source gain special slider
        table.add(
            createSlider("Channel Gain", 0f, 1f, boomChannel.getFilter().getGain(), 2, (value) -> boomChannel.getFilter().setGain(value))
        ).row();
        table.add().row();

        if(effectType == EffectType.AUTO_WAH) {
            AutoWahData thisData = (AutoWahData) boomEffectData;
            table.add(createSlider("attackTime", 0.0001f, 1f, thisData.attackTime, 4, (value) -> thisData.attackTime = value)).row();
            table.add(createSlider("releaseTime", 0.0001f, 1f, thisData.releaseTime, 4, (value) -> thisData.releaseTime = value)).row();
            table.add(createSlider("resonance", 2f, 1000f, thisData.resonance, 0, (value) -> thisData.resonance = value)).row();
            table.add(createSlider("peakGain", 0.00003f, 31621f, thisData.peakGain, 2, (value) -> thisData.peakGain = value)).row();
        }

        if(effectType == EffectType.CHORUS) {
            ChorusData thisData = (ChorusData) boomEffectData;
            table.add(createSlider("waveForm", 0, 1, thisData.waveForm, (value) -> thisData.waveForm = value)).row();
            table.add(createSlider("phase", -180, 180, thisData.phase, (value) -> thisData.phase = value)).row();
            table.add(createSlider("rate", 0, 10, thisData.rate, 2, (value) -> thisData.rate = value)).row();
            table.add(createSlider("depth", 0, 1, thisData.depth, 2, (value) -> thisData.depth = value)).row();
            table.add(createSlider("feedback", -1, 1, thisData.feedback, 2, (value) -> thisData.feedback = value)).row();
            table.add(createSlider("delay", 0, 0.016f, thisData.delay, 4, (value) -> thisData.delay = value)).row();
        }

        if(effectType == EffectType.DISTORTION) {
            DistortionData thisData = (DistortionData) boomEffectData;
            table.add(createSlider("edge", 0f, 1f, thisData.edge, 2, (value) -> thisData.edge = value)).row();
            table.add(createSlider("gain", 0.01f, 1f, thisData.gain, 2, (value) -> thisData.gain = value)).row();
            table.add(createSlider("lowPassCutoff", 80, 24000, thisData.lowPassCutoff, 0, (value) -> thisData.lowPassCutoff = value)).row();
            table.add(createSlider("eqCenter", 80, 24000, thisData.eqCenter, 0, (value) -> thisData.eqCenter = value)).row();
            table.add(createSlider("eqBandwidth", 80, 24000, thisData.eqBandwidth, 0, (value) -> thisData.eqBandwidth = value)).row();
        }

        if(effectType == EffectType.ECHO) {
            EchoData thisData = (EchoData) boomEffectData;
            table.add(createSlider("delay", 0, 0.207f, thisData.delay, 3, (value) -> thisData.delay = value)).row();
            table.add(createSlider("lrdelay", 0, 0.404f, thisData.lrdelay, 3, (value) -> thisData.lrdelay = value)).row();
            table.add(createSlider("damping", 0, 0.99f, thisData.damping, 2, (value) -> thisData.damping = value)).row();
            table.add(createSlider("feedback", 0, 1f, thisData.feedback, 2, (value) -> thisData.feedback = value)).row();
            table.add(createSlider("spread", -1f, 1f, thisData.spread, 2, (value) -> thisData.spread = value)).row();
        }

        if(effectType == EffectType.FLANGER) {
            FlangerData thisData = (FlangerData) boomEffectData;
            table.add(createSlider("waveform", 0, 1, thisData.waveform, (value) -> thisData.waveform = value)).row();
            table.add(createSlider("phase", -180, 180, thisData.phase, (value) -> thisData.phase = value)).row();
            table.add(createSlider("rate", 0, 10, thisData.rate, 2, (value) -> thisData.rate = value)).row();
            table.add(createSlider("depth", 0, 1, thisData.depth, 2, (value) -> thisData.depth = value)).row();
            table.add(createSlider("feedback", -1, 1, thisData.feedback, 2, (value) -> thisData.feedback = value)).row();
            table.add(createSlider("delay", 0, 0.004f, thisData.delay, 4, (value) -> thisData.delay = value)).row();
        }

        if(effectType == EffectType.PITCH_SHIFTER) {
            PitchShifterData thisData = (PitchShifterData) boomEffectData;
            table.add(createSlider("coarseTune", -12, 12, thisData.coarseTune, (value) -> thisData.coarseTune = value)).row();
            table.add(createSlider("fineTune", -50, 50, thisData.fineTune, (value) -> thisData.fineTune = value)).row();
        }

        if(effectType == EffectType.RING_MODULATOR) {
            RingModulatorData thisData = (RingModulatorData) boomEffectData;
            table.add(createSlider("frequency", 0, 8000, thisData.frequency, 2, (value) -> thisData.frequency = value)).row();
            table.add(createSlider("highpassCutoff", 0, 24000, thisData.highpassCutoff, 0, (value) -> thisData.highpassCutoff = value)).row();
            table.add(createSlider("waveform", 0, 2, thisData.waveform, (value) -> thisData.waveform = value)).row();
        }

        if(effectType == EffectType.VOCAL_MORPHER) {
            VocalMorpherData thisData = (VocalMorpherData) boomEffectData;
            table.add(createSlider("phonemea", 0, 29, thisData.phonemea, (value) -> thisData.phonemea = value)).row();
            table.add(createSlider("phonemeb", 0, 29, thisData.phonemeb, (value) -> thisData.phonemeb = value)).row();
            table.add(createSlider("phonemeaCoarseTuning", -24, 24, thisData.phonemeaCoarseTuning, (value) -> thisData.phonemeaCoarseTuning = value)).row();
            table.add(createSlider("phonemebCoarseTuning", -24, 24, thisData.phonemebCoarseTuning, (value) -> thisData.phonemebCoarseTuning = value)).row();
            table.add(createSlider("waveform", 0, 2, thisData.waveform, (value) -> thisData.waveform = value)).row();
            table.add(createSlider("rate", 0, 10, thisData.rate, 2, (value) -> thisData.rate = value)).row();
        }

        return table;
    }

    private Table createSlider(String name, int from, int to, int value, Consumer<Integer> consumer) {
        // Create name label
        VisLabel nameLabel = new VisLabel(name, "small-font", "t-white");
        nameLabel.setAlignment(Align.center);

        // Create value label
        VisLabel valueLabel = new VisLabel(Integer.toString(value), "small-font", "t-white");
        valueLabel.setAlignment(Align.center);

        // Create slider
        VisSlider slider = new VisSlider(from, to, 1, false);
        Utils.addSoundToButton(slider, boom, uiClickSound);
        slider.setValue(value);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int value = Math.round(slider.getValue());

                // Update label
                valueLabel.setText(Integer.toString(value));

                // Call consumer
                consumer.accept(value);

                // Update effect
                boomEffect.apply();

                // Reset preset
                currentPreset = null;
                currentPresetIndex = -1;
                presetLabel.setText("Custom");
            }
        });

        // Create table
        Table table = new Table();
        table.defaults().pad(0, 10, 0, 10).center();
        table.add(nameLabel).width(SLIDER_LABEL_WIDTH);
        table.add(slider).growX();
        table.add(valueLabel).width(SLIDER_LABEL_WIDTH);

        return table;
    }

    private Table createSlider(String name, float from, float to, float value, int labelPrecision, Consumer<Float> consumer) {
        // Create name label
        VisLabel nameLabel = new VisLabel(name, "small-font", "t-white");
        nameLabel.setAlignment(Align.center);

        // Create value label
        final String textFormat = "%.0" + labelPrecision + "f";
        VisLabel valueLabel = new VisLabel(String.format(textFormat, value), "small-font", "t-white");
        valueLabel.setAlignment(Align.center);

        // Create slider
        float step = (to - from) / 100f;
        VisSlider slider = new VisSlider(from, to, step, false);
        Utils.addSoundToButton(slider, boom, uiClickSound);
        slider.setValue(value);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = slider.getValue();

                // Update label
                valueLabel.setText(String.format(textFormat, value));

                // Call consumer
                consumer.accept(value);

                // Update effect
                boomEffect.apply();

                // Reset preset
                currentPreset = null;
                currentPresetIndex = -1;
                presetLabel.setText("Custom");
            }
        });

        // Create table
        Table table = new Table();
        table.defaults().pad(0, 10, 0, 10).center();
        table.add(nameLabel).width(SLIDER_LABEL_WIDTH);
        table.add(slider).growX();
        table.add(valueLabel).width(SLIDER_LABEL_WIDTH);

        return table;
    }

    private void onEffectButton(boolean next) {
        EffectType[] effectTypes = EffectType.values();
        int limit = effectTypes.length;

        // Advance index
        int index = currentEffect.ordinal();
        index += next ? 1 : -1;

        // Wrap index around the array boundaries
        if(index < 0) {
            index += limit;
        } else if(index >= limit) {
            index -= limit;
        }

        // Set new value
        setEffect(effectTypes[index]);
    }

    private void setEffect(EffectType effectType) {
        currentEffect = effectType;
        currentPreset = null;
        currentPresetIndex = -1;

        // Effect label
        effectLabel.setText(effectType.toString());
        presetLabel.setText("Default");

        // Create effect data
        BoomEffectData effectData;
        if(effectType == EffectType.AUTO_WAH) {
            effectData = new AutoWahData();
        } else if(effectType == EffectType.CHORUS) {
            effectData = new ChorusData();
        } else if(effectType == EffectType.COMPRESSOR) {
            effectData = new CompressorData();
        } else if(effectType == EffectType.DISTORTION) {
            effectData = new DistortionData();
        } else if(effectType == EffectType.ECHO) {
            effectData = new EchoData();
        } else if(effectType == EffectType.FLANGER) {
            effectData = new FlangerData();
        } else if(effectType == EffectType.PITCH_SHIFTER) {
            effectData = new PitchShifterData();
        } else if(effectType == EffectType.REVERB) {
            effectData = new ReverbData();
        } else if(effectType == EffectType.RING_MODULATOR) {
            effectData = new RingModulatorData();
        } else if(effectType == EffectType.VOCAL_MORPHER) {
            effectData = new VocalMorpherData();
        } else {
            return;
        }

        // Add effect to boom
        boomChannel.removeAllEffects();
        boomEffect = boomChannel.addEffect(effectData);
        boomEffectData = effectData;

        // Create effect table
        Table table = createEffectTable(effectType);
        container.setActor(table);
    }

    private void onPresetButton(boolean next) {
        // Get preset values
        BoomEffectPreset[] presetValues;
        if(currentEffect == EffectType.AUTO_WAH) {
            presetValues = AutoWahPreset.values();
        } else if(currentEffect == EffectType.CHORUS) {
            presetValues = ChorusPreset.values();
        } else if(currentEffect == EffectType.COMPRESSOR) {
            presetValues = null;
        } else if(currentEffect == EffectType.DISTORTION) {
            presetValues = DistortionPreset.values();
        } else if(currentEffect == EffectType.ECHO) {
            presetValues = EchoPreset.values();
        } else if(currentEffect == EffectType.FLANGER) {
            presetValues = FlangerPreset.values();
        } else if(currentEffect == EffectType.PITCH_SHIFTER) {
            presetValues = PitchShifterPreset.values();
        } else if(currentEffect == EffectType.REVERB) {
            presetValues = ReverbPreset.values();
        } else if(currentEffect == EffectType.RING_MODULATOR) {
            presetValues = RingModulatorPreset.values();
        } else if(currentEffect == EffectType.VOCAL_MORPHER) {
            presetValues = VocalMorpherPreset.values();
        } else {
            presetValues = null;
        }

        // If we have no presets, do nothing
        if(presetValues == null) {
            return;
        }

        // Advance index
        int limit = presetValues.length;
        currentPresetIndex += next ? 1 : -1;

        // Wrap index around the array boundaries
        if(currentPresetIndex < 0) {
            currentPresetIndex += limit;
        } else if(currentPresetIndex >= limit) {
            currentPresetIndex -= limit;
        }

        // Set new value
        currentPreset = presetValues[currentPresetIndex];

        // Add effect to boom
        boomChannel.removeAllEffects();
        boomEffect = boomChannel.addEffect(currentPreset.getData());
        boomEffectData = currentPreset.getData();

        // Create effect table
        Table table = createEffectTable(currentEffect);
        container.setActor(table);

        // Update UI
        presetLabel.setText(currentPreset + "");
    }

    private void update(float delta) {
        // Update stage
        stage.act(delta);

        // Update all sound and music to the current channel
        boom.updateSoundToChannel(voiceSoundId, boomChannel);
        boom.changeMusicChannel(music, boomChannel);
        boom.changeMusicChannel(ambience, boomChannel);
    }

    @Override
    public void render() {
        // Update app
        update(Gdx.graphics.getDeltaTime());

        // Clean screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw stage
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Resize stage
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        VisUI.dispose();
        stage.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Boom Example");
        config.setWindowedMode(960, 1024);
        config.setAudioConfig(128, 4096, 9); // Increase libGDX's default audio limits which are pretty low
        config.setResizable(false);
        new Lwjgl3Application(new EffectExplorer(), config);
    }
}