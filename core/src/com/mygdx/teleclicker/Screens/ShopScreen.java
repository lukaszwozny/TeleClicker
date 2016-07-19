package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 12.07.2016.
 */
public class ShopScreen extends AbstractScreen{
    private final String SHOP_BG_DIR = "img/bg/shop_bg.png";
    private Image shopBg;

    public ShopScreen(TeleClicker game){
        super(game);
    }

    @Override
    protected void init() {
        shopBg = new Image(new Texture(SHOP_BG_DIR));
        //stage.addActor(shopBg);
    }

    @Override
    public void render(float delta) {
    }
}
