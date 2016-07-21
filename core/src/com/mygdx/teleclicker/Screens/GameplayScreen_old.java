package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Controllers.FlyingObjectsController;
import com.mygdx.teleclicker.Controllers.RandomEventsController;
import com.mygdx.teleclicker.Entities.CornerPhone_old;
import com.mygdx.teleclicker.Entities.Player_old;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.*;

/**
 * Created by Senpai on 10.07.2016.
 */
public class GameplayScreen_old extends AbstractScreen_old {
    private Image bgImg;
    private Player_old player;
    private PlayerButton_old playerButton;
    private CornerPhone_old cornerPhone;
    private CornerPhoneButton cornerPhoneButton;
    private ResetScoreButton resetScoreButton;
    private FlyingObjectsController flyingObjectsController;
    private RandomEventsController randomEventsController;
    static boolean backFromShop;


    public GameplayScreen_old(TeleClicker game) {
        this(game,false);
    }

    public GameplayScreen_old(TeleClicker game, boolean isShop) {
        super(game);
    }

    @Override
    protected void init() {
        initBg();
        initPlayer();
        initPlayerButton();
        initCornerPhone();
        initCornerPhoneButton();
        initResetScoreButton();
        game.getScoreService().printLabels(stage);
        initFlyingObjectsController();
        initRandomEventsController();
        startTheMusic();
        initPassiveIncomeDialog();
    }


    private void initCornerPhone() {
        cornerPhone = new CornerPhone_old(game);
        stage.addActor(cornerPhone);
    }

    private void initCornerPhoneButton() {
        cornerPhoneButton = new CornerPhoneButton(new IClickCallback() {
            @Override
            public void onClick() {
                cornerPhone.reactOnClick();
                game.setScreen(game.getShopScreen());
            }
        });
        stage.addActor(cornerPhoneButton);
    }

    private void initRandomEventsController() {
        randomEventsController = new RandomEventsController(game, stage);
    }

    private void initPassiveIncomeDialog() {
        float pointsGained = game.getScoreService().getPointsToAdd();
        if (!backFromShop && (pointsGained > 0)) {
            BasicDialog basicDialog = new BasicDialog();

            stage.addActor(basicDialog);
            basicDialog.initContent("Podczas nieobecnosci \n" +
                    "zarobiles\n" +
                    String.format("%.1f",pointsGained) + " Erlangow.");
        }
    }

    private void startTheMusic() {
        game.getSoundService().playCaketownMusic(true);
    }

    private void initFlyingObjectsController() {
        flyingObjectsController = new FlyingObjectsController(game, stage);
    }

    private void initBg() {
        bgImg = new Image(new Texture("img/bg/basic_bg.png"));
        stage.addActor(bgImg);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallback() {

            @Override
            public void onClick() {
                game.getScoreService().resetGameScore();
            }
        });
        stage.addActor(resetScoreButton);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton_old(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.getScoreService().addPoint();
            }
        });
        stage.addActor(playerButton);
    }

    private void initPlayer() {
        player = new Player_old(game);
        stage.addActor(player);
    }

    @Override
    public void pause() {
        super.pause();
        game.getScoreService().saveCurrentGameState();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void show() {
        super.show();
        System.out.println("Shoe gameplay");
        game.setActualScreen(TeleClicker.ScreenType.GAMEPLAY);
    }

    private void update() {
        game.getScoreService().updateScoreLabel();
        stage.act();
    }
}
