package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.Service.FontService;

/**
 * Created by Senpai on 22.07.2016.
 */
public class EventDialog extends Group {

    private Label texLabel;
    private Image dialogBox;

    public final static int WIDTH = 365;
    public final static int HEIGHT = 218;

    public EventDialog(){
        super();
        initDialogBox();
        initTextLabel();
    }

    private void initDialogBox() {
    }

    private void initTextLabel() {
        float fontScale = 1.4f;
        texLabel = new Label("Test", new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
        this.addActor(texLabel);
    }
}
