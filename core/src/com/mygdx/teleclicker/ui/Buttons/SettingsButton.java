package com.mygdx.teleclicker.ui;

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

/**
 * Created by Senpai on 22.07.2016.
 */
public class SettingsButton extends ImageButton {
    private final int WIDTH = 100;
    private final int HEIGHT = 100;

    private final int START_X = TeleClicker.WIDTH - WIDTH;
    private final int START_Y = 0;

    private Texture textureButton;

    public SettingsButton(final IClickCallback callback) {
        this(prepareSkin());

        this.setSize(WIDTH, HEIGHT);
        this.setPosition(START_X,START_Y);

        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private static Drawable prepareSkin() {
        Texture tex = AssetsEnum.SETTINGS_TEX.getAsset();
        Image img = new Image(tex);
        return img.getDrawable();
    }

    private SettingsButton(Drawable skin) {
        super(skin);
    }
}
