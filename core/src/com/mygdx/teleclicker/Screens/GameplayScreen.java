package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Controllers.FlyingObjectsController;
import com.mygdx.teleclicker.Controllers.RandomEventsController;
import com.mygdx.teleclicker.Entities.CornerPhone;
import com.mygdx.teleclicker.Entities.Player;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.*;

/**
 * Created by Senpai on 10.07.2016.
 */
public class GameplayScreen extends AbstractScreen {
    private Image bgImg;
    private Player player;
    private PlayerButton playerButton;
    private CornerPhone cornerPhone;
    private CornerPhoneButton cornerPhoneButton;
    private ResetScoreButton resetScoreButton;
    private FlyingObjectsController flyingObjectsController;
    private RandomEventsController randomEventsController;
    private ShopScreen shopScreen;
    static boolean backFromShop;


    public GameplayScreen(TeleClicker game, boolean isShop) {
        super(setDestination(game,isShop));
    }

    private static TeleClicker setDestination(TeleClicker game, boolean isShop){
        backFromShop = isShop;
        return game;
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

    private void initCornerPhoneButton() {
        cornerPhoneButton = new CornerPhoneButton(new IClickCallback() {
            @Override
            public void onClick() {
                cornerPhone.reactOnClick();
                game.setScreen(new ShopScreen(game));
            }
        });
        stage.addActor(cornerPhoneButton);
    }


    private void initCornerPhone() {
        cornerPhone = new CornerPhone(game);
        stage.addActor(cornerPhone);
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
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.getScoreService().addPoint();
            }
        });
        stage.addActor(playerButton);
    }

    private void initPlayer() {
        player = new Player(game);
        stage.addActor(player);
    }

    @Override
    public void pause() {
        super.pause();
        game.getScoreService().saveCurrentGameState();
        //TODO make flush() of scoreservice olways on screen pause()
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

    private void update() {
        game.getScoreService().updateScoreLabel(false);
        stage.act();
    }
}
