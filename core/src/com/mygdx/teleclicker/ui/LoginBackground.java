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
public class LoginBackground extends Group {
    private Image partImage;

    public enum Direction {
        CLOCKWISE,
        COUNTER_CLOSKWISE
    }

    public LoginBackground() {
        this(Direction.CLOCKWISE);
    }

    public LoginBackground(Direction direction) {
        initPartImage();
        initRotation(direction);
    }

    private void initRotation(final Direction direction) {
        setOrigin(TeleClicker.WIDTH / 2, TeleClicker.HEIGHT / 2);
        Timer timer = new Timer();

        final float ROTATE_SPEED = 0.2f;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if (direction == Direction.CLOCKWISE)
                    rotateBy(ROTATE_SPEED);
                else
                    rotateBy(-ROTATE_SPEED);
            }
        }, 0.030f, 0.030f);
    }

    private void initPartImage() {
        Texture tex = new Texture(Gdx.files.internal("img/bg/spin_wheel_part.png"));

        float rotation = 0f;
        final float PART_NUMBER = 25f;
        for (int i = 0; i < PART_NUMBER; i++) {
            partImage = new Image(tex);

            partImage.setOrigin(partImage.getWidth(), 0);
            partImage.setPosition(TeleClicker.WIDTH / 2 - partImage.getWidth(), TeleClicker.HEIGHT / 2);
            partImage.rotateBy(rotation);
            addActor(partImage);

            rotation += 360.f / PART_NUMBER;
        }
    }
}
