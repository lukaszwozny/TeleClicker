package com.mygdx.teleclicker.ui.Buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.MyTextButton;

/**
 * Created by Senpai on 08/08/2016.
 */
public class ShopButton extends MyTextButton {
    private Drawable redUp;
    private Drawable redDown;

    private Drawable greenUp;
    private Drawable getRedDown;

    public ShopButton(IClickCallback clickCallback) {
        this("", clickCallback);
    }

    public ShopButton(String text, IClickCallback clickCallback) {
        super(text, clickCallback);
    }

    @Override
    protected void initDrawables() {
        //R Red drawables
        TextureAtlas atlas = AssetsEnum.RED_ATLAS.getAsset();

        Sprite spriteUp = atlas.createSprite("button_03");
        SpriteDrawable spriteDrawableUp = new SpriteDrawable(spriteUp);
        Sprite spriteDown = atlas.createSprite("button_01");
        SpriteDrawable spriteDrawableDown = new SpriteDrawable(spriteDown);

        upDrawable = spriteDrawableUp;
        downDrawable = spriteDrawableDown;

        atlas = AssetsEnum.GREEN_ATLAS.getAsset();

    }
}
