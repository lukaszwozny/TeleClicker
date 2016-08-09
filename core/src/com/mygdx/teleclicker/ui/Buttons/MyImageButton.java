package com.mygdx.teleclicker.ui.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.ui.IClickCallback;

/**
 * Created by Senpai on 09/08/2016.
 */
public class MyImageButton extends Group {
    private Image image;
    private final float WIDTH;
    private final float HEIGHT;

    public MyImageButton(Image image, IClickCallback callback) {
        this.image = image;

        WIDTH = image.getWidth();
        HEIGHT = image.getHeight();
        this.setSize(WIDTH, HEIGHT);
        this.setOrigin(WIDTH / 2, HEIGHT / 2);

        iniListener(callback);
    }

    private void iniListener(final IClickCallback callback) {
        image.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SoundService.getInstance().playClickSound();
                setScale(0.8f);
                return super.touchDown(event, x, y, pointer, button);
            }


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setScale(1f);
                if (isVisualPressed()) {
                    callback.onClick();
                }
                super.touchUp(event, x, y, pointer, button);
            }
        });
        addActor(image);
    }

}
