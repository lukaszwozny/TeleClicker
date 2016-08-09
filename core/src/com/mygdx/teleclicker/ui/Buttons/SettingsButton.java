package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.Buttons.MyImageButton;

/**
 * Created by Senpai on 22.07.2016.
 */
public class SettingsButton extends MyImageButton {
    public SettingsButton(IClickCallback callback) {
        super(prepareImage(), callback);
    }

    private static Image prepareImage() {
        Image settingImage = new Image((Texture) AssetsEnum.SETTINGS_TEX.getAsset());
        return settingImage;
    }
}
