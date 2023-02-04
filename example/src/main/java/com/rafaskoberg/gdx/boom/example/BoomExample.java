package com.rafaskoberg.gdx.boom.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.rafaskoberg.boom.Boom;
import com.rafaskoberg.boom.BoomChannel;
import com.rafaskoberg.boom.BoomChannelLwjgl3;
import com.rafaskoberg.boom.BoomEffectLwjgl3;
import com.rafaskoberg.boom.effect.AutoWahData;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.effect.ChorusData;
import com.rafaskoberg.boom.effect.CompressorData;
import com.rafaskoberg.boom.effect.DistortionData;
import com.rafaskoberg.boom.effect.DistortionPreset;
import com.rafaskoberg.boom.effect.EchoData;
import com.rafaskoberg.boom.effect.FlangerData;
import com.rafaskoberg.boom.effect.PitchShifterData;
import com.rafaskoberg.boom.effect.ReverbPreset;
import com.rafaskoberg.boom.effect.RingModulatorData;
import com.rafaskoberg.boom.effect.VocalMorpherData;
import com.rafaskoberg.gdx.boom.example.widgets.AudioPlayerWidget;
import com.rafaskoberg.gdx.boom.example.widgets.ChannelWidget;
import com.rafaskoberg.gdx.boom.example.widgets.FilterWidget;

public class BoomExample extends ApplicationAdapter {
    public Boom boom;
    private Stage stage;
    private Sound voiceSound;
    public Sound uiClickSound;
    private Music music;
    private Music ambience;
    private boolean isVoicePlaying;
    private long voiceSoundId;
    private FilterWidget filterWidget;
    public ChannelType currentChannel = ChannelType.NONE;

    @Override
    public void create() {
        // Init Boom
        boom = Boom.init();

        // Create channels
        createChannels();

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
    }

    private void createChannels() {
        /*
            Channel: Aerial Reverb
         */
        {
            BoomChannel channel = boom.createChannel(ChannelType.AERIAL_REVERB.id);
            channel.addEffect(ReverbPreset.HANGAR);
            channel.addEffect(new FlangerData());
            channel.getFilter().setGain(0f);
        }

        /*
            Channel: Evil Realm
         */
        {
            BoomChannel channel = boom.createChannel(ChannelType.EVIL_REALM.id);
            channel.addEffect(new PitchShifterData(-12, 0));
            channel.addEffect(new DistortionData(0.4f, 0.5f, 8000, 3600, 3600));
            channel.getFilter().setGain(0f);
        }

        /*
            Channel: Tiny Little Birds
         */
        {
            BoomChannel channel = boom.createChannel(ChannelType.TINY_LITTLE_BIRDS.id);
            BoomEffect effect = channel.addEffect(new PitchShifterData(12, 0));
            effect.getFilter().setHighPass(0.5f);
            channel.getFilter().setGain(0.5f);
        }

        /*
            Channel: Funky
         */
        {
            BoomChannel channel = boom.createChannel(ChannelType.FUNKY_STUFF.id);
            channel.addEffect(new AutoWahData());
            channel.addEffect(new ChorusData());
            channel.getFilter().setGain(0.5f);
        }

        /*
            Channel: Odd But Nice
         */
        {
            BoomChannel channel = boom.createChannel(ChannelType.ODD_BUT_NICE.id);
            FlangerData flangerData = new FlangerData();
            flangerData.phase = 180;
            flangerData.feedback = 0.7f;
            flangerData.delay = 0.004f;
            channel.addEffect(flangerData);
            channel.addEffect(new EchoData());
        }

        /*
            Channel: Bad Robot in C
         */
        {
            BoomChannel channel = boom.createChannel(ChannelType.BAD_ROBOT_IN_C.id);
            channel.addEffect(new RingModulatorData());
            channel.addEffect(DistortionPreset.SOFT);
            channel.getFilter().setGain(0f);
        }

        /*
            Channel: Oscillators Forever
         */
        {
            BoomChannel channel = boom.createChannel(ChannelType.OSCILLATORS_FOREVER.id);
            {
                VocalMorpherData vocalMorpherData = new VocalMorpherData();
                vocalMorpherData.phonemea = 3;
                vocalMorpherData.phonemeb = 4;
                vocalMorpherData.phonemeaCoarseTuning = -24;
                vocalMorpherData.phonemebCoarseTuning = 24;
                vocalMorpherData.waveform = 0;
                vocalMorpherData.rate = 1f;
                channel.addEffect(vocalMorpherData);
            }
            {
                VocalMorpherData vocalMorpherData = new VocalMorpherData();
                vocalMorpherData.rate = 10;
                channel.addEffect(vocalMorpherData);
            }
            channel.getFilter().setGain(1f);
            channel.getFilter().setLowPass(0.3f);
        }

        /*
            Channel: In Your Face
         */
        {
            BoomChannel channel = boom.createChannel(ChannelType.IN_YOUR_FACE.id);
            channel.addEffect(new CompressorData());
            channel.getFilter().setGain(0f);
        }

        /*
            Channel: Double Stereo
         */
        {
            BoomChannel channel = boom.createChannel(ChannelType.DOUBLE_STEREO.id);
            ChorusData chorusData = new ChorusData();
            chorusData.waveForm = 0;
            chorusData.phase = 180;
            channel.addEffect(chorusData);
            channel.getFilter().setGain(0f);
        }
    }

    private void createUi() {
        // Create root table
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        this.stage.addActor(rootTable);

        // Create audio player widget
        AudioPlayerWidget audioPlayerWidget = new AudioPlayerWidget(this);
        audioPlayerWidget.pack();

        // Create channel widget
        ChannelWidget channelWidget = new ChannelWidget(this);
        channelWidget.pack();

        // Create filter widget
        filterWidget = new FilterWidget(this);
        filterWidget.pack();

        // Configure table
        rootTable.defaults().growX().center();
        rootTable.setFillParent(true);
        rootTable.add(audioPlayerWidget);
        rootTable.row();
        rootTable.add(channelWidget);
        rootTable.row().growY();
        rootTable.add(filterWidget);
        rootTable.pack();
    }

    private void update(float delta) {
        // Update stage
        stage.act(delta);
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

    public void setChannel(ChannelType channelType) {
        if(channelType == currentChannel) return;

        currentChannel = channelType;
        boom.changeMusicChannel(music, currentChannel.id);
        boom.changeMusicChannel(ambience, currentChannel.id);

        if(isVoicePlaying) {
            boom.updateSoundToChannel(voiceSoundId, currentChannel.id);
        }

        // Update UI
        if(filterWidget != null) {
            filterWidget.update(boom.getChannel(currentChannel.id));
        }
    }

    /**
     * Called when the channel buttons are clicked.
     */
    public void onChannelButton(boolean next) {
        ChannelType[] channelTypes = ChannelType.values();
        int limit = channelTypes.length;

        // Advance index
        int index = currentChannel.ordinal();
        index += next ? 1 : -1;

        // Wrap index around the array boundaries
        if(index < 0) {
            index += limit;
        } else if(index >= limit) {
            index -= limit;
        }

        // Set new value
        setChannel(channelTypes[index]);
    }

    public void onPlayVoiceButton() {
        if(isVoicePlaying) {
            voiceSound.stop();
            voiceSoundId = 0;
        } else {
            voiceSoundId = boom.loop(voiceSound, currentChannel.id);
        }
        isVoicePlaying = !isVoicePlaying;
    }

    public void onPlayMusicButton() {
        if(music.isPlaying()) {
            music.stop();
        } else {
            boom.play(music, currentChannel.id);
        }
    }

    public void onPlayAmbienceButton() {
        if(ambience.isPlaying()) {
            ambience.stop();
        } else {
            boom.play(ambience, currentChannel.id);
        }
    }

    public void onFilterSliderChannelGain(float value) {
        BoomChannelLwjgl3 channel = (BoomChannelLwjgl3) boom.getChannel(currentChannel.id);
        if(channel == null) return;
        channel.getFilter().setGain(value);
    }

    public void onFilterSliderEffectGain(float value, int slot) {
        BoomChannelLwjgl3 channel = (BoomChannelLwjgl3) boom.getChannel(currentChannel.id);
        if(channel == null) return;
        Array<BoomEffectLwjgl3> effects = channel.getEffects();
        int slotIndex = slot - 1;
        if(slotIndex < effects.size) {
            BoomEffectLwjgl3 effect = effects.get(slotIndex);
            effect.getFilter().setGain(value);
        }
    }

    public void onFilterSliderChannelLowPass(float value) {
        BoomChannelLwjgl3 channel = (BoomChannelLwjgl3) boom.getChannel(currentChannel.id);
        if(channel == null) return;
        channel.getFilter().setLowPass(value);
    }

    public void onFilterSliderEffectLowPass(float value, int slot) {
        BoomChannelLwjgl3 channel = (BoomChannelLwjgl3) boom.getChannel(currentChannel.id);
        if(channel == null) return;
        Array<BoomEffectLwjgl3> effects = channel.getEffects();
        int slotIndex = slot - 1;
        if(slotIndex < effects.size) {
            BoomEffectLwjgl3 effect = effects.get(slotIndex);
            effect.getFilter().setLowPass(value);
        }
    }

    public void onFilterSliderChannelHighPass(float value) {
        BoomChannelLwjgl3 channel = (BoomChannelLwjgl3) boom.getChannel(currentChannel.id);
        if(channel == null) return;
        channel.getFilter().setHighPass(value);
    }

    public void onFilterSliderEffectHighPass(float value, int slot) {
        BoomChannelLwjgl3 channel = (BoomChannelLwjgl3) boom.getChannel(currentChannel.id);
        if(channel == null) return;
        Array<BoomEffectLwjgl3> effects = channel.getEffects();
        int slotIndex = slot - 1;
        if(slotIndex < effects.size) {
            BoomEffectLwjgl3 effect = effects.get(slotIndex);
            effect.getFilter().setHighPass(value);
        }
    }

    @Override
    public void dispose() {
        VisUI.dispose();
        stage.dispose();
    }
}
