package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Controllers.FlyingObjectController;
import com.mygdx.teleclicker.Entities.Player;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.PlayerButton;
import com.mygdx.teleclicker.ui.ResetScoreButton;

/**
 * Created by Senpai on 10.07.2016.
 */
public class GameplayScreen extends AbstractScreen{
    private Image bgImg;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private FlyingObjectController flyingObjectController;

    public GameplayScreen(TeleClicker game) {
        super(game);
    }

    @Override
    protected void init() {
        initBg();
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        game.getScoreService().initLabels(stage);
        initFlyingStuffController();
        startTheMusic();
    }

    private void startTheMusic() {
        game.getSoundService().playCaketownMusic(true);
    }

    private void initFlyingStuffController() {
        flyingObjectController = new FlyingObjectController(game, stage);
    }

    private void initBg() {
        bgImg = new Image(new Texture("img/bg/phone_and_paper_bg.png"));
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
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();

    }

    private void update() {
        game.getScoreService().updateScoreLabel();
        stage.act();
    }
}
