package com.mygdx.teleclicker.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;

/**
 * Created by Senpai on 22.07.2016.
 */
public class CornerPhone extends Image {
    private final static int WIDTH_OFF = 100;
    private final static int HEIGHT_OFF = 69;
    private final static int WIDTH_ON = 113;
    private final static int HEIGHT_ON = 122;

    private final static int STARTING_X = 0;
    private final static int STARTING_Y = 0;

    private boolean isOff = true;

    public CornerPhone(){
        super(getPhoneTexture());

        this.setOrigin(WIDTH_OFF / 2, HEIGHT_OFF / 2);
        this.setSize(WIDTH_OFF, HEIGHT_OFF);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);
    }

    public static Texture getPhoneTexture() {
        return AssetsEnum.PHONE_OFF_TEX.getAsset();
    }

    public void reactOnClick(){
        changeTexture();
    }

    private void changeTexture() {
        Texture newTexture;
        if(isOff){
            newTexture = AssetsEnum.PHONE_ON_TEX.getAsset();
            this.setWidth(WIDTH_ON);
            this.setHeight(HEIGHT_ON);
            this.setDrawable(new SpriteDrawable(new Sprite(newTexture)));
            isOff = false;
        } else {
            newTexture = AssetsEnum.PHONE_OFF_TEX.getAsset();
            this.setWidth(WIDTH_OFF);
            this.setHeight(HEIGHT_OFF);
            this.setDrawable(new SpriteDrawable(new Sprite(newTexture)));
            isOff = true;
        }
    }
}
