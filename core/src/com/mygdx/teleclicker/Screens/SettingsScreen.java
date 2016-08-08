package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SettingsService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.*;

import java.util.Set;

/**
 * Created by Senpai on 22.07.2016.
 */
public class SettingsScreen extends AbstractScreen {
    private com.mygdx.teleclicker.ui.Buttons.CloseSettingsButton closeButton;

    private CheckboxLabel checkboxMusic;
    private CheckboxLabel checkboxSound;

    private MyTextButton resetScoreButton;
    private WarningBox resetScoreWarningBox;

    public SettingsScreen(){
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode){
            case Input.Keys.BACK:
                ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY);
                break;
        }
        return false;
    }

    @Override
    public void buildStage() {
        initBgTexture();
        initCloseButton();
        initCheckboxes();
        initResetScoreButton();
    }

    private void initResetScoreWarningBox() {
        if(resetScoreWarningBox != null){
            resetScoreWarningBox.removeAll();
            resetScoreWarningBox = null;
        }
        resetScoreWarningBox = new WarningBox(new IWarningCallback() {
            @Override
            public void Yes() {
                ScoreService.getInstance().resetGameScore();
            }

            @Override
            public void No() {
            }
        });

        final float POSX = TeleClicker.WIDTH / 2 - resetScoreWarningBox.getWidth() / 2;
        final float POSY = TeleClicker.HEIGHT / 2 - resetScoreWarningBox.getHeight() / 2;

        resetScoreWarningBox.setPosition(POSX, POSY);

        resetScoreWarningBox.setText("Your progress will be lost.\n" +
                "Are you sure?");

        addActor(resetScoreWarningBox);
    }

    private void initResetScoreButton() {
        resetScoreButton = new RedTextButton("Reset data", new IClickCallback() {
            @Override
            public void onClick() {
                initResetScoreWarningBox();
            }
        });

        resetScoreButton.setSize(150,70);
        resetScoreButton.setPosition(TeleClicker.WIDTH / 2 - resetScoreButton.getWidth() / 2, 50);
        addActor(resetScoreButton);
    }

    private void initCheckboxes() {
        final int START_X = 40;
        final int START_Y = 550;
        final int INTERVAL = 80;

        String musicText = "MUSIC";
        String soundText = "SOUND";
        float fontScale = 2.0f;

        checkboxMusic = new CheckboxLabel(SettingsService.getInstance().isMusic(),
                new ICheckboxCallback() {
                    @Override
                    public void Check() {
                        SettingsService.getInstance().setMusic(true);
                        SoundService.getInstance().playCaketownMusic(true);
                    }

                    @Override
                    public void Uncheck() {
                        SettingsService.getInstance().setMusic(false);
                        SoundService.getInstance().stopCaketownMusic();
                    }
                });
        checkboxMusic.setPosition(START_X, START_Y);
        checkboxMusic.setText(musicText);

        checkboxSound = new CheckboxLabel(SettingsService.getInstance().isSounds(),
                new ICheckboxCallback() {
                    @Override
                    public void Check() {
                        SettingsService.getInstance().setSounds(true);
                    }

                    @Override
                    public void Uncheck() {
                        SettingsService.getInstance().setSounds(false);
                    }
                });
        checkboxSound.setPosition(START_X, START_Y - INTERVAL);
        checkboxSound.setText(soundText);

        addActor(checkboxMusic);
        addActor(checkboxSound);
    }

    private void initCloseButton() {
        closeButton = new com.mygdx.teleclicker.ui.Buttons.CloseSettingsButton(new IClickCallback() {
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
        bgTexture = AssetsEnum.SETTINGS_BG.getAsset();
        addActor(new Image(bgTexture));
    }

    @Override
    public void show() {
        super.show();
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.SETTINGS);
    }

    @Override
    public void hide() {
        super.hide();
    }
}
