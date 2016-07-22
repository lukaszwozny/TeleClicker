package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Senpai on 22.07.2016.
 */
public class FontService {

    public static BitmapFont getFont(float fontScale){
        BitmapFont font = new BitmapFont();
        font.getData().setScale(fontScale);

        return font;
    }

}
