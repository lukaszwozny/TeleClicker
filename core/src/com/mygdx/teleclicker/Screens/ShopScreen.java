package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 11.07.2016.
 */
public class ShopScreen extends AbstractScreen {
    private final String SHOP_BG_DIR = "img/bg/shop_bg.png";
    private Texture shopBg;

    public ShopScreen(final TeleClicker game) {
        super(game);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GameplayScreen(game, true));
            }
        },5);
    }

    @Override
    protected void init() {
        // TODO implement better assets loading when game grows
        shopBg = new Texture(SHOP_BG_DIR);
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(shopBg, 0, 0);
        spriteBatch.end();
    }
}
