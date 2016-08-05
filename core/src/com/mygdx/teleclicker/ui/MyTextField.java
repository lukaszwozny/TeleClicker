package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * Created by Senpai on 05/08/2016.
 */
public class MyTextField extends TextField {
    private boolean clicked = false;

    public MyTextField(String text, Skin skin) {
        super(text, skin);
    }

    public MyTextField(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public MyTextField(String text, TextFieldStyle style) {
        super(text, style);
    }

    /*
    *
    * Getters and setters
    *
    */

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
