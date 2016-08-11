package com.mygdx.teleclicker.Screens;

import com.mygdx.teleclicker.Controllers.FlyingObjectController;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Entities.Player;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.*;
import com.mygdx.teleclicker.ui.Buttons.CornerPhoneButton;
import com.mygdx.teleclicker.ui.Buttons.MyImageButton;
import com.mygdx.teleclicker.ui.Buttons.StatsButton;

/**
 * Created by Senpai on 11/08/2016.
 */
public class GameplayScreen extends AbstractScreen {
    private CashLabel cashLabel;

    private Player player;
    private PlayerButton playerButton;

    private MyImageButton cornerPhoneButton;
    private MyImageButton settingsButton;
    private MyImageButton statsButton;

    private FlyingObjectController flyingObjectController;

    @Override
    public void buildStage() {
        initFlyingObjectController();

        initBgTexture();
        initCashLabel();

        initPlayer();
        initPlayerButton();

        initButtons();
    }

    private void initFlyingObjectController() {
        flyingObjectController = new FlyingObjectController(this);
    }

    private void initButtons() {
        initCornerPhoneButton();
        initStatsButton();
        initSettingsButton();

        final float Y = TeleClicker.HEIGHT - 110;

        cornerPhoneButton.setPosition(0, Y);
        statsButton.setPosition(TeleClicker.WIDTH / 2 - statsButton.getWidth() / 2, Y);
        settingsButton.setPosition(TeleClicker.WIDTH-settingsButton.getWidth(),Y);

        addActor(cornerPhoneButton);
        addActor(statsButton);
        addActor(settingsButton);
    }

    private void initSettingsButton() {
        settingsButton = new SettingsButton(new IClickCallback() {
            @Override
            public void onClick() {
                ScreenService.getInstance().setScreen(ScreenEnum.SETTINGS);
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

    private void initCornerPhoneButton() {
        cornerPhoneButton = new CornerPhoneButton(new IClickCallback() {
            @Override
            public void onClick() {
                ScreenService.getInstance().setScreen(ScreenEnum.SHOP);
            }
        });
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

    private void initPlayer() {
        player = new Player();
        addActor(player);
    }

    private void initCashLabel() {
        cashLabel = new CashLabel();
        addActor(cashLabel);
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
        flyingObjectController.clearTimer();
    }

    @Override
    public void initBgTexture() {
        MainBackground mainBackground = new MainBackground(MainBackground.Direction.COUNTER_CLOSKWISE);
        addActor(mainBackground);
    }
}
