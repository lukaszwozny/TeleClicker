package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.TeleClicker;

import java.text.DecimalFormat;

/**
 * Created by Senpai on 10/08/2016.
 */
public class CashLabel extends Group {
    private Image background;
    private MyLabel cashLabel;

    public CashLabel() {
        initImage();
        initLabel();
        initTimer();
        group();
    }

    private void initTimer() {
        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                updateCashLabel();
            }
        },0,0.1f);
    }

    private void group() {
        addActor(background);
        addActor(cashLabel);
    }

    private void initLabel() {
        cashLabel = new MyLabel("Test");
    }

    private void initImage() {
        background = new Image((Texture) AssetsEnum.CASH_LABEL.getAsset());
    }

    private void updateCashLabel(){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        df.setMinimumFractionDigits(1);

        float cash = ScoreService.getInstance().getPoints();
        cashLabel.setText(df.format(cash));

        final float X = TeleClicker.WIDTH - 10 - cashLabel.getLabelWidth();
        final float Y = (background.getHeight() - 9) / 2 - cashLabel.getHeight()/2;

        cashLabel.setPosition(X, Y);
    }
}
