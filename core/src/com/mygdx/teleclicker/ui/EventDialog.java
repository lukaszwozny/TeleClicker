package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Service.FontService;

/**
 * Created by Senpai on 22.07.2016.
 */
public class EventDialog extends Group {

    private Label texLabel;
    private Image dialogBox;

    public final static int WIDTH = 365;
    public final static int HEIGHT = 218;

    public EventDialog(String text) {
        super();
        initDialogBox();
        initTextLabel(text);
    }

    private void initDialogBox() {
        Texture dialogBoxTex = Assets.getInstance().manager.get(AssetsEnum.DIALOG_BOG_TEX.toString());
        dialogBox = new Image(dialogBoxTex);
        addActor(dialogBox);
    }

    private void initTextLabel(String text) {
        final int START_X = 30;
        final float MIDDLE_Y = (dialogBox.getHeight()+40) / 2;

        float fontScale = 1.4f;
        texLabel = new Label(text, new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
        texLabel.setPosition(START_X, MIDDLE_Y - texLabel.getHeight() / 2);
        this.addActor(texLabel);
    }
}
