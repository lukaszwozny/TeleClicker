package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;

/**
 * Created by Senpai on 05/08/2016.
 */
public class RedTextButton extends MyTextButton {
    public RedTextButton(IClickCallback clickCallback) {
        this("", clickCallback);
    }

    public RedTextButton(String text, IClickCallback clickCallback) {
        super(text, clickCallback);
    }

    @Override
    protected void initDrawables() {
        TextureAtlas atlas = Assets.getInstance().manager.get(AssetsEnum.RED_ATLAS.toString());

        Sprite spriteUp = atlas.createSprite("button_03");
        SpriteDrawable spriteDrawableUp = new SpriteDrawable(spriteUp);
        Sprite spriteDown = atlas.createSprite("button_01");
        SpriteDrawable spriteDrawableDown = new SpriteDrawable(spriteDown);

        upDrawable = spriteDrawableUp;
        downDrawable = spriteDrawableDown;

    }

}
