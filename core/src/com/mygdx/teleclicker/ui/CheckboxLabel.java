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
public class CheckboxLabel extends Group {

    private Label label;
    private Texture checkboxOn;
    private Texture checkboxOff;
    private Image checkBoxImage;

    private boolean isOn;

    public CheckboxLabel() {
        initLabel();
        initCheckbox();
        gropu();
    }

    private void gropu() {
        addActor(label);
        addActor(checkBoxImage);
    }

    private void initCheckbox() {
        checkboxOn = Assets.getInstance().manager.get(AssetsEnum.CHECKBOX_ON_TEX.toString());
        checkboxOff = Assets.getInstance().manager.get(AssetsEnum.CHECKBOX_OFF_TEX.toString());

        checkBoxImage = new Image(checkboxOn);
        checkBoxImage.setX(270);
    }

    private void initLabel() {
        float fontScale = 2.0f;
        label = new Label("DEFAULT TEXT", new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
    }

    public void setText(String text) {
        label.setText(text);
    }

    public boolean isOn() {
        return isOn;
    }
}
