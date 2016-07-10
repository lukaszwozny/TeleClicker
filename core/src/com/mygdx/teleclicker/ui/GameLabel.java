package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Senpai on 10.07.2016.
 */
public class GameLabel extends Label {
    public GameLabel(){
        super("",prepareLabelStyle());
    }

    private static LabelStyle prepareLabelStyle() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        return labelStyle;
    }
}
