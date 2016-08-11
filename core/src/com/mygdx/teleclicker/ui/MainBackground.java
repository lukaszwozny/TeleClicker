package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 09/08/2016.
 */
public class MainBackground extends Group {
    private Image partImage;
    private Group star;

    public enum Direction {
        CLOCKWISE,
        COUNTER_CLOSKWISE
    }

    public MainBackground() {
        this(Direction.CLOCKWISE);
    }

    public MainBackground(Direction direction) {
        initPartImage();
        initRotation(direction);
    }

    private void initRotation(final Direction direction) {
        star.setOrigin(TeleClicker.WIDTH / 2, TeleClicker.HEIGHT / 2);
        Timer timer = new Timer();

        final float ROTATE_SPEED = 0.2f;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                System.out.println(")");
                if (direction == Direction.CLOCKWISE)
                    star.rotateBy(ROTATE_SPEED);
                else
                    star.rotateBy(-ROTATE_SPEED);
            }
        }, 0.030f, 0.030f);
    }

    private void initPartImage() {
        Texture purpleBg = new Texture(Gdx.files.internal("img/bg/purple_bg.png"));
        addActor(new Image(purpleBg));

        Texture tex = new Texture(Gdx.files.internal("img/bg/spin_wheel_part.png"));
        star = new Group();

        float rotation = 0f;
        final float PART_NUMBER = 25f;
        for (int i = 0; i < PART_NUMBER; i++) {
            partImage = new Image(tex);

            partImage.setOrigin(partImage.getWidth(), 0);
            partImage.setPosition(TeleClicker.WIDTH / 2 - partImage.getWidth(), TeleClicker.HEIGHT / 2);
            partImage.rotateBy(rotation);
            star.addActor(partImage);

            rotation += 360.f / PART_NUMBER;
        }
        addActor(star);
    }
}
