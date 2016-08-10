package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.ui.MyLabel;

/**
 * Created by Senpai on 10/08/2016.
 */
public class StatsScreen extends AbstractScreen {

    private MyLabel scores;

    @Override
    public void buildStage() {
        initBgTexture();

        initLabels();
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
