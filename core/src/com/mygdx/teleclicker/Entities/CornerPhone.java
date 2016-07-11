package com.mygdx.teleclicker.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 11.07.2016.
 */
public class CornerPhone extends Image{
    private final static int WIDTH = 100;
    private final static int HEIGHT = 69;

    private static final String PHONE_DIR = "img/skins/phone_corner_off.png";
    private static final String PHONE_DIR_ON = "img/skins/phone_corner_on.png";

    private final static int STARTING_X = 0;
    private final static int STARTING_Y = 0;

    private TeleClicker game;

    public CornerPhone(TeleClicker game){
        super(new Texture(PHONE_DIR));
        this.game = game;

        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);


        // starting position
        this.setPosition(STARTING_X, STARTING_Y);
    }

    public void reactOnClick(){
        changeTexture();
    }

    private void changeTexture() {
        Texture newTexture = new Texture(PHONE_DIR_ON);
        this.setDrawable(new SpriteDrawable(new Sprite(newTexture)));
    }
}
