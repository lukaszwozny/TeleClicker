package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 10.07.2016.
 */
public class SplashScreen extends AbstractScreen {
    private final String SPLASH_BG_DIR = "img/bg/splash.png";
    private Texture splashBg;

    public SplashScreen(final TeleClicker game) {
        super(game);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(game.getGameplayScreen());
            }
        }, 3);
    }

    @Override
    public void show() {
        super.show();
        game.setActualScreen(TeleClicker.ScreenType.SPLASH);
    }

    @Override
    protected void init() {
        // TODO implement better assets loading when game grows
        splashBg = new Texture(SPLASH_BG_DIR);
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashBg, 0, 0);
        spriteBatch.end();
    }

}
