package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 10/08/2016.
 */
public class CashLabel extends Group {
    private Image background;
    private MyLabel cashLabel;

    public CashLabel() {
        initImage();
        initLabel();
        group();
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

    public void updateCashLabel(){
        String cash = "" + ScoreService.getInstance().getPoints();
        cashLabel.setText(cash);

        System.out.println("WIDTH: " + cashLabel.getWidth() + "\n" +
                "HEiGHT: " + cashLabel.getHeight());

        final float X = TeleClicker.WIDTH - 10 - cashLabel.getLabelWidth();
        final float Y = (background.getHeight() - 9) / 2 - cashLabel.getHeight()/2;

        cashLabel.setPosition(X, Y);
    }
}
