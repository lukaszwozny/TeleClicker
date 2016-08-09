package com.mygdx.teleclicker.ui.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.ui.IClickCallback;

/**
 * Created by Senpai on 22.07.2016.
 */
public class CornerPhoneButton extends MyImageButton {
    public CornerPhoneButton(IClickCallback callback) {
        super(prepareImage(), callback);
    }

    private static Image prepareImage() {
        Image settingImage = new Image((Texture) AssetsEnum.PHONE_ON_TEX.getAsset());
        return settingImage;
    }
}
