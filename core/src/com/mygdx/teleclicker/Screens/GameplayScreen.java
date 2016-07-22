package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.Controllers.FlyingObjectController;
import com.mygdx.teleclicker.Controllers.RandomEventsController;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Entities.CornerPhone;
import com.mygdx.teleclicker.Entities.Player;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.FontService;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.*;

/**
 * Created by Senpai on 21.07.2016.
 */
public class GameplayScreen extends AbstractScreen {

    private Label scoreLabel;
    private Player player;
    private PlayerButton playerButton;
    private CornerPhone cornerPhone;
    private CornerPhoneButton cornerPhoneButton;
    private SettingsButton settingsButton;

    public GameplayScreen(){
        super();
        FlyingObjectController.getInstance().Initialize(this);
        RandomEventsController.getInstance().Initialize(this);
    }

    @Override
    public void initBgTexture() {
        bgTexture = Assets.getInstance().manager.get(AssetsEnum.GAMEPLAY_BG.toString());
        addActor(new Image(bgTexture));
    }

    @Override
    public void buildStage() {
        initBgTexture();
        initScoreLabel();
        initPlayer();
        initPlayerButton();
        initCornerPhone();
        initCornerPhoneButton();
        initSettingsButton();
    }

    private void initSettingsButton() {
        settingsButton = new SettingsButton(new IClickCallback() {
            @Override
            public void onClick() {
                ScreenService.getInstance().setScreen(ScreenEnum.SETTINGS);
            }
        });
        addActor(settingsButton);
    }

    private void initCornerPhoneButton() {
        cornerPhoneButton = new CornerPhoneButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                cornerPhone.reactOnClick();
                ScreenService.getInstance().setScreen(ScreenEnum.SHOP);
            }
        });
        addActor(cornerPhoneButton);
    }

    private void initCornerPhone() {
        cornerPhone = new CornerPhone();
        addActor(cornerPhone);
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
        scoreLabel.setPosition(POS_X,POS_Y);

        addActor(scoreLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                player.reactOnClick();
                ScoreService.getInstance().addPoint();
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
                "Per sec: " + ScoreService.getInstance().getPointsPerSec());
    }

    @Override
    public void show() {
        super.show();
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.GAMEPLAY);
    }

    @Override
    public void pause() {
        super.pause();
        System.out.println("Pause gameplayScreen");
        ScoreService.getInstance().saveCurrentGameState();
    }
}
