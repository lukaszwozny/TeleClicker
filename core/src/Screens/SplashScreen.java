package Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 10.07.2016.
 */
public class SplashScreen extends AbstractScreen {
    private Texture textureImg;

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
        textureImg = new Texture("img/phone_background.png");
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(textureImg, 0, 0);
        spriteBatch.end();
    }
}
