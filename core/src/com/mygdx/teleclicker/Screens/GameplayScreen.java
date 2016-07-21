package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 21.07.2016.
 */
public class GameplayScreen extends AbstractScreen {

    private Label scoreLabel;

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
