package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.MyLabel;
import com.mygdx.teleclicker.ui.MyTextButton;
import com.mygdx.teleclicker.ui.RedTextButton;

/**
 * Created by Senpai on 10/08/2016.
 */
public class StatsScreen extends AbstractScreen {

    private MyLabel scores;

    private MyTextButton backButton;

    @Override
    public void buildStage() {
        initBgTexture();

        initLabels();
        initButtons();
    }

    private void initButtons() {
        initBackButton();

        final int WIDTH = 150;
        final int HEIGHT = 70;

        final int X = TeleClicker.WIDTH / 2 - WIDTH / 2;
        final int Y = 38+10;
        final int NTERVAL = HEIGHT + 20;

        backButton.setSize(WIDTH,HEIGHT);

        backButton.setPosition(X, Y);

        addActor(backButton);
    }

    private void initBackButton() {
        backButton = new RedTextButton("Back", new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY2);
            }
        });
        addActor(backButton);
    }

    private void initLabels() {
        scores = new MyLabel("TEST");

        scores.setPosition(10,300);

        addActor(scores);
    }

    @Override
    public void initBgTexture() {
        bgTexture = AssetsEnum.SETTINGS_BG.getAsset();

        addActor(new Image(bgTexture));
    }

    @Override
    public void show() {
        super.show();
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.STATS);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
    }

    private void update() {
        String scoresStr = "BestClickingSpeed:\n" +
                "\tperSec; " + ScoreService.getInstance().getBestClckSpeedPerSec() + "\n" +
                "\tperMin: " + ScoreService.getInstance().getBestClckSpeedPerMinute() + "\n" +
                "Best playTime: " + ScoreService.getInstance().getBestTimeInGame();
        scores.setText(scoresStr);
    }
}
