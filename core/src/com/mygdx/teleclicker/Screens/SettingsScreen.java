package com.mygdx.teleclicker.Screens;

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

/**
 * Created by Senpai on 22.07.2016.
 */
public class SettingsScreen extends AbstractScreen {
    private CloseSettingsButton closeButton;

    private CheckboxLabel checkboxMusic;
    private CheckboxLabel checkboxSound;

    private ResetScoreButton resetScoreButton;
    private WarningBox resetScoreWarningBox;

    private ResetScoreButton httpButton;

    @Override
    public void buildStage() {
        initBgTexture();
        initCloseButton();
        initCheckboxes();
        initResetScoreButton();

        initHttpButton();
    }

    private void initHttpButton() {
        httpButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().setScreen(ScreenEnum.HTTP);
            }
        });
        httpButton.setSize(50,40);
        
        final float X =TeleClicker.WIDTH - httpButton.getWidth() - 10;
        final int Y = 10;
        httpButton.setPosition(X,Y);

        addActor(httpButton);
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
        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                initResetScoreWarningBox();
            }
        });
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

    @Override
    public void hide() {
        super.hide();
        SettingsService.getInstance().saveCurrentSettingsState();
    }
}
