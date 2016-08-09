package com.mygdx.teleclicker.ui.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.ui.IClickCallback;

/**
 * Created by Senpai on 09/08/2016.
 */
public class StatsButton extends MyImageButton {
    public StatsButton(IClickCallback callback) {
        super(prepareImage(), callback);
    }

    private static Image prepareImage() {
        Image statsImage = new Image(new Texture(Gdx.files.internal("img/skins/stats.png")));
        return statsImage;
    }


}
