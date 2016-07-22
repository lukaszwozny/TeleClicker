package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.ui.CloseSettingsButton;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.CheckboxLabel;

/**
 * Created by Senpai on 22.07.2016.
 */
public class SettingsScreen extends AbstractScreen {
    private CloseSettingsButton closeButton;

    private CheckboxLabel checkboxMusic;
    private CheckboxLabel checkboxSound;


    @Override
    public void buildStage() {
        initBgTexture();
        initCloseButton();
        initCheckboxes();
    }

    private void initCheckboxes() {
        final int START_X = 40;
        final int START_Y = 550;
        final int INTERVAL = 80;

        String musicText = "MUSIC";
        String soundText = "SOUND";
        float fontScale = 2.0f;

        checkboxMusic = new CheckboxLabel();
        checkboxMusic.setPosition(START_X,START_Y);
        checkboxMusic.setText(musicText);

        checkboxSound = new CheckboxLabel();
        checkboxSound.setPosition(START_X,START_Y-INTERVAL);
        checkboxSound.setText(soundText);

        addActor(checkboxMusic);
        addActor(checkboxSound);
    }

    private void initCloseButton() {
        closeButton = new CloseSettingsButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY);
            }
        });
        addActor(closeButton);
    }

    @Override
    public void initBgTexture() {
        bgTexture = Assets.getInstance().manager.get(AssetsEnum.SETTINGS_BG.toString());
        addActor(new Image(bgTexture));
    }

    @Override
    public void show() {
        super.show();
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.SETTINGS);
    }
}
