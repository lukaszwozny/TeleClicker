package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 10.07.2016.
 */
public class SplashScreen extends AbstractScreen {
    private Texture splashImg;

    public SplashScreen(final TeleClicker game) {
        super(game);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GameplayScreen(game));
            }
        }, 3);
    }

    @Override
    protected void init() {
        // TODO implement better assets loading when game grows
        splashImg = new Texture("img/splash.png");
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg, 0, 0);
        spriteBatch.end();
    }

}
