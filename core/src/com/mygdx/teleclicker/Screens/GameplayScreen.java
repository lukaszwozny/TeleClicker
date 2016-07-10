package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.teleclicker.Entities.Player;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.PlayerButton;
import com.mygdx.teleclicker.ui.ResetScoreButton;
import com.mygdx.teleclicker.ui.ScoreLabel;

/**
 * Created by Senpai on 10.07.2016.
 */
public class GameplayScreen extends AbstractScreen{
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private Label scoreLabel;
    private Label resetButtonLabel;

    public GameplayScreen(TeleClicker game) {
        super(game);
    }

    @Override
    protected void init() {
        initPlayer();
        initPlayerButton();
        initResetButtonLabel();
        initResetScoreButton();
        initScoreLabel();
    }

    private void initResetButtonLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        resetButtonLabel = new Label("RESET", labelStyle);
        resetButtonLabel.setX(385);
        resetButtonLabel.setY(640);
        stage.addActor(resetButtonLabel);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.resetScore();
            }
        });
        stage.addActor(resetScoreButton);
    }

    private void initScoreLabel() {
        scoreLabel = new ScoreLabel();
        stage.addActor(scoreLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.addPoint();
            }
        });
        stage.addActor(playerButton);

    }


    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        System.out.println(game.getPoints());

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        scoreLabel.setText("Score: " + game.getPoints());
        stage.act();
    }
}
