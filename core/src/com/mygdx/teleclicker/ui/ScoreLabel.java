package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Senpai on 10.07.2016.
 */
public class ScoreLabel extends Label {
    public ScoreLabel(int x, int y){
        super("",prepareLabelStyle());
        this.setX(x);
        this.setY(y);
    }

    private static LabelStyle prepareLabelStyle() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        return labelStyle;
    }
}
