package com.mygdx.teleclicker.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 11.07.2016.
 */
public class CornerPhone extends Image{
    private final static int WIDTH_OFF = 100;
    private final static int HEIGHT_off = 69;

    private static final String PHONE_OFF_DIR = "img/skins/phone_corner_off.png";

    private final static int STARTING_X = 0;
    private final static int STARTING_Y = 0;

    private TeleClicker game;

    public CornerPhone(TeleClicker game){
        super(new Texture(PHONE_OFF_DIR));
        this.game = game;

        this.setOrigin(WIDTH_OFF / 2, HEIGHT_off / 2);
        this.setSize(WIDTH_OFF, HEIGHT_off);


        // starting position
        this.setPosition(STARTING_X, STARTING_Y);
    }

    public void reactOnClick(){

    }
}
