package com.rafaskoberg.gdx.boom.example.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.rafaskoberg.boom.BoomChannel;
import com.rafaskoberg.boom.BoomChannelLwjgl3;
import com.rafaskoberg.boom.BoomEffectLwjgl3;
import com.rafaskoberg.boom.effect.BoomEffect;
import com.rafaskoberg.boom.filter.BoomFilter;
import com.rafaskoberg.gdx.boom.example.BoomExample;
import com.rafaskoberg.gdx.boom.example.util.Utils;

public class FilterWidget extends Table {
    private final BoomExample app;

    private VisSlider sliderChannelGain;
    private VisSlider sliderChannelLowPass;
    private VisSlider sliderChannelHighPass;
    private VisSlider sliderFx1Gain;
    private VisSlider sliderFx1LowPass;
    private VisSlider sliderFx1HighPass;
    private VisSlider sliderFx2Gain;
    private VisSlider sliderFx2LowPass;
    private VisSlider sliderFx2HighPass;

    public FilterWidget(BoomExample app) {
        this.app = app;

        // Create labels
        VisLabel gainLabel = new VisLabel("Gain", "small-font", "t-white");
        gainLabel.setAlignment(Align.center);
        VisLabel lowPassLabel = new VisLabel("Low Pass", "small-font", "t-white");
        lowPassLabel.setAlignment(Align.center);
        VisLabel highPassLabel = new VisLabel("High Pass", "small-font", "t-white");
        highPassLabel.setAlignment(Align.center);
        VisLabel channelLabel = new VisLabel("Channel", "small-font", "t-medium");
        channelLabel.setAlignment(Align.center);
        VisLabel effect1Label = new VisLabel("FX 1", "small-font", "t-medium");
        effect1Label.setAlignment(Align.center);
        VisLabel effect2Label = new VisLabel("FX 1", "small-font", "t-medium");
        effect2Label.setAlignment(Align.center);

        // Create sliders
        sliderChannelGain = createSlider(1, 0);
        sliderChannelLowPass = createSlider(2, 0);
        sliderChannelHighPass = createSlider(3, 0);
        sliderFx1Gain = createSlider(1, 1);
        sliderFx1LowPass = createSlider(2, 1);
        sliderFx1HighPass = createSlider(3, 1);
        sliderFx2Gain = createSlider(1, 2);
        sliderFx2LowPass = createSlider(2, 2);
        sliderFx2HighPass = createSlider(3, 2);

        // Configure widget
        background(Utils.createColorDrawable(VisUI.getSkin().getColor("t-dark"), 0.33f));
        pad(5);
        defaults().uniformY().pad(5, 10, 5, 10).center();

        // Populate widget
        add();
        add(gainLabel).growX().uniformX();
        add(lowPassLabel).growX().uniformX();
        add(highPassLabel).growX().uniformX();
        row();
        add(channelLabel);
        add(sliderChannelGain).growX().uniformX();
        add(sliderChannelLowPass).growX().uniformX();
        add(sliderChannelHighPass).growX().uniformX();
        row();
        add(effect1Label);
        add(sliderFx1Gain).growX().uniformX();
        add(sliderFx1LowPass).growX().uniformX();
        add(sliderFx1HighPass).growX().uniformX();
        row();
        add(effect2Label);
        add(sliderFx2Gain).growX().uniformX();
        add(sliderFx2LowPass).growX().uniformX();
        add(sliderFx2HighPass).growX().uniformX();
        pack();
    }

    private VisSlider createSlider(int function, int slot) {
        VisSlider slider = new VisSlider(0, 1, 0.01f, false);
        Utils.addSoundToButton(slider, app.boom, app.uiClickSound);
        slider.setValue(0);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = slider.getValue();
                if(function == 1) {
                    if(slot == 0) {
                        app.onFilterSliderChannelGain(value);
                    } else {
                        app.onFilterSliderEffectGain(value, slot);
                    }
                } else if(function == 2) {
                    if(slot == 0) {
                        app.onFilterSliderChannelLowPass(value);
                    } else {
                        app.onFilterSliderEffectLowPass(value, slot);
                    }
                } else if(function == 3) {
                    if(slot == 0) {
                        app.onFilterSliderChannelHighPass(value);
                    } else {
                        app.onFilterSliderEffectHighPass(value, slot);
                    }
                }
            }
        });
        return slider;
    }

    public void update(BoomChannel channel) {
        // Channel
        BoomFilter channelFilter = channel != null ? channel.getFilter() : null;
        sliderChannelGain.setValue(channelFilter != null ? channelFilter.getGain() : 0);
        sliderChannelLowPass.setValue(channelFilter != null ? channelFilter.getLowPass() : 0);
        sliderChannelHighPass.setValue(channelFilter != null ? channelFilter.getHighPass() : 0);

        // Get effects
        Array<BoomEffectLwjgl3> effects = channel != null ? ((BoomChannelLwjgl3) channel).getEffects() : null;
        BoomEffect effect1 = effects != null && effects.size >= 1 ? effects.get(0) : null;
        BoomEffect effect2 = effects != null && effects.size >= 2 ? effects.get(1) : null;

        // Effect 1
        BoomFilter effectFilter1 = effect1 != null ? effect1.getFilter() : null;
        sliderFx1Gain.setValue(effectFilter1 != null ? effectFilter1.getGain() : 0);
        sliderFx1LowPass.setValue(effectFilter1 != null ? effectFilter1.getLowPass() : 0);
        sliderFx1HighPass.setValue(effectFilter1 != null ? effectFilter1.getHighPass() : 0);

        // Effect 2
        BoomFilter effectFilter2 = effect2 != null ? effect2.getFilter() : null;
        sliderFx2Gain.setValue(effectFilter2 != null ? effectFilter2.getGain() : 0);
        sliderFx2LowPass.setValue(effectFilter2 != null ? effectFilter2.getLowPass() : 0);
        sliderFx2HighPass.setValue(effectFilter2 != null ? effectFilter2.getHighPass() : 0);
    }

}
