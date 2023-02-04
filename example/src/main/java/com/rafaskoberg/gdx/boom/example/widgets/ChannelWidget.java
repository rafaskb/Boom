package com.rafaskoberg.gdx.boom.example.widgets;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.rafaskoberg.gdx.boom.example.BoomExample;
import com.rafaskoberg.gdx.boom.example.ChannelType;
import com.rafaskoberg.gdx.boom.example.util.Utils;

public class ChannelWidget extends Table {
    private final BoomExample app;
    private VisLabel channelLabel;

    public ChannelWidget(BoomExample app) {
        this.app = app;

        // Previous button
        VisImageButton previousButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("previous")));
        Utils.addSoundToButton(previousButton, app.boom, app.uiClickSound);
        previousButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.onChannelButton(false);
                updateLabels();
            }
        });

        // Next button
        VisImageButton nextButton = new VisImageButton(Utils.createModernButtonStyle(Utils.loadImageDrawable("next")));
        Utils.addSoundToButton(nextButton, app.boom, app.uiClickSound);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.onChannelButton(true);
                updateLabels();
            }
        });

        // Header label
        VisLabel headerLabel = new VisLabel("BOOM CHANNEL", "small-font", "t-medium");
        headerLabel.setAlignment(Align.center);

        // Artist label
        channelLabel = new VisLabel("", "default-font", "t-white");
        channelLabel.setAlignment(Align.center);

        // Configure widget
        background(Utils.createColorDrawable(VisUI.getSkin().getColor("t-dark"), 0.66f));
        pad(5);
        defaults().uniformX().expand();

        // Populate widget
        defaults().pad(5).space(0, 20, 0, 20).center().grow().uniform();
        add(headerLabel).colspan(3).row();
        add(previousButton);
        add(channelLabel);
        add(nextButton);

        // Update labels
        updateLabels();
    }

    private void updateLabels() {
        ChannelType currentChannel = app.currentChannel;
        channelLabel.setText(String.format("%d: %s", currentChannel.id, currentChannel.displayName));
    }

}
