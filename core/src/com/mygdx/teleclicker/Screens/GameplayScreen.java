package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Entities.CornerPhone;
import com.mygdx.teleclicker.Entities.FlyingObject;
import com.mygdx.teleclicker.Entities.Player;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.FlyingObjectTypeEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.CornerPhoneButton;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.PlayerButton;

/**
 * Created by Senpai on 21.07.2016.
 */
public class GameplayScreen extends AbstractScreen {

    private Label scoreLabel;
    private Player player;
    private PlayerButton playerButton;
    private CornerPhone cornerPhone;
    private CornerPhoneButton cornerPhoneButton;

    private FlyingObject flyingObject;

    public GameplayScreen(){
        super();
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
        initFlyingObject();
    }

    private void initFlyingObject() {
        flyingObject = new FlyingObject(FlyingObjectTypeEnum.MONEY);
        addActor(flyingObject);
        flyingObject.Fly();
    }

    private void initCornerPhoneButton() {
        cornerPhoneButton = new CornerPhoneButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                cornerPhone.reactOnClick();
                ScreenService.getInstance().SetScreen(ScreenEnum.SHOP);
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

        BitmapFont font = new BitmapFont();
        font.getData().setScale(fontScale);

        scoreLabel = new Label("", new Label.LabelStyle(font, Color.BLUE));
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
    public void pause() {
        super.pause();
        ScoreService.getInstance().saveCurrentGameState();
    }
}
