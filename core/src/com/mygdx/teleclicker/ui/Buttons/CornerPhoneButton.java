package com.mygdx.teleclicker.ui.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.teleclicker.ui.IClickCallback;

/**
 * Created by Senpai on 22.07.2016.
 */
public class CornerPhoneButton extends Button {
    public CornerPhoneButton(final IClickCallback callback){
        super(new ButtonStyle());
        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(100);
        this.setHeight(70);
        this.setX(0);
        this.setY(0);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
}
