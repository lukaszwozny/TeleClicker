package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.Controllers.FlyingObjectController;
import com.mygdx.teleclicker.Controllers.RandomEventsController;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Entities.Player;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.FontService;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.*;
import com.mygdx.teleclicker.ui.Buttons.CornerPhoneButton;
import com.mygdx.teleclicker.ui.Buttons.MyImageButton;
import com.mygdx.teleclicker.ui.Buttons.StatsButton;

/**
 * Created by Senpai on 21.07.2016.
 */
public class GameplayScreen extends AbstractScreen {
    private Label scoreLabel;
    private Player player;
    private PlayerButton playerButton;
    private com.mygdx.teleclicker.ui.Buttons.CornerPhoneButton cornerPhoneButton;

    private MyImageButton cornerPhone;
    private MyImageButton settingsButton;
    private MyImageButton statsButton;

    public GameplayScreen() {
        super();
        Gdx.input.setCatchBackKey(true);
        ScoreService.getInstance().initPlayTimeTimer();
        FlyingObjectController.getInstance().Initialize(this);
        RandomEventsController.getInstance().Initialize(this);
    }

    @Override
    public void buildStage() {
        initBgTexture();
        addActor(ScoreService.getInstance().getCashLabel());

        initScoreLabel();
        initPlayer();
        initPlayerButton();

        initButtons();
    }

    private void initButtons() {
        initCornerPhoneButton();
        initStatsButton();
        initSettingsButton();

        final float Y = 50;

        cornerPhoneButton.setPosition(0, Y);
        statsButton.setPosition(TeleClicker.WIDTH / 2 - statsButton.getWidth() / 2, Y);
        settingsButton.setPosition(TeleClicker.WIDTH-settingsButton.getWidth(),Y);

        addActor(cornerPhoneButton);
        addActor(statsButton);
        addActor(settingsButton);
    }

    private void initCornerPhoneButton() {
        cornerPhoneButton = new CornerPhoneButton(new IClickCallback() {
            @Override
            public void onClick() {
                ScreenService.getInstance().setScreen(ScreenEnum.SHOP);
            }
        });
    }

    private void initStatsButton() {
        statsButton = new StatsButton(new IClickCallback() {
            @Override
            public void onClick() {
                ScreenService.getInstance().setScreen(ScreenEnum.STATS);
            }
        });
    }

    @Override
    public void initBgTexture() {
        MainBackground mainBackground = new MainBackground(MainBackground.Direction.COUNTER_CLOSKWISE);
        addActor(mainBackground);
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.BACK:
                Gdx.app.exit();
                break;
        }
        return false;
    }

    private void initSettingsButton() {
        settingsButton = new SettingsButton(new IClickCallback() {
            @Override
            public void onClick() {
                ScreenService.getInstance().setScreen(ScreenEnum.SETTINGS);
            }
        });
    }

    private void initPlayer() {
        player = new Player();
        addActor(player);
    }

    private void initScoreLabel() {
        final float fontScale = 1.2f;
        final int POS_X = 40;
        final int POS_Y = TeleClicker.HEIGHT - 50;

        scoreLabel = new Label("", new Label.LabelStyle(FontService.getFont(fontScale), Color.BLUE));
        scoreLabel.setPosition(POS_X, POS_Y);

        addActor(scoreLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                player.reactOnClick();
                ScoreService.getInstance().addPoint();
                ScoreService.getInstance().addClickSpeed();
                ScoreService.getInstance().increseNumberOfClick();
            }
        });
        addActor(playerButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
    }

    private void update() {
        updateScoreLabel();
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Erlangi: " + ScoreService.getInstance().getPoints() + "\n" +
                "Per sec: " + ScoreService.getInstance().getPointsPerSec() + "\n" +
                "Click speed: " + ScoreService.getInstance().getClickSpeedPerSec()+"\n" +
                "Playtime: " + ScoreService.getInstance().getPlayTimeSec());
    }

    @Override
    public void show() {
        super.show();
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.GAMEPLAY);
    }

    @Override
    public void pause() {
        super.pause();
        ScoreService.getInstance().saveStats();
    }
}
