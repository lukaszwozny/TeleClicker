package com.mygdx.teleclicker.ui.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.IClickCallback;

/**
 * Created by Senpai on 22.07.2016.
 */
public class CloseSettingsButton extends Button {
    private final int WIDTH = 42;
    private final int HEIGHT = 42;

    private final int START_X = TeleClicker.WIDTH-WIDTH;
    private final int START_Y = TeleClicker.HEIGHT-HEIGHT;

    public CloseSettingsButton(final IClickCallback callback) {
        super(new ButtonStyle());
        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setX(START_X);
        this.setY(START_Y);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
}
