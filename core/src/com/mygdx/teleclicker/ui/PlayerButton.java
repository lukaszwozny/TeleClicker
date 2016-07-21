package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Senpai on 22.07.2016.
 */
public class PlayerButton extends Button {
    public PlayerButton(final IClickCallback callback){
        super(new ButtonStyle());
        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(480);
        this.setHeight(500);
        this.setX(0);
        this.setY(80);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
}
