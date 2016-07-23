package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
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

    private boolean checked;

    public CheckboxLabel(boolean defValue, final ICheckboxCallback chackboxCallback) {
        checked = defValue;
        initLabel();
        initCheckbox(chackboxCallback);
        group();
    }

    private void ChangeCheckboxTexture() {
        if (checked) {
            checkBoxImage.setDrawable(new SpriteDrawable(new Sprite(checkboxOff)));
        } else {
            checkBoxImage.setDrawable(new SpriteDrawable(new Sprite(checkboxOn)));
        }
    }

    private void group() {
        addActor(label);
        addActor(checkBoxImage);
    }

    private void initCheckbox(final ICheckboxCallback chackboxCallback) {
        checkboxOn = Assets.getInstance().manager.get(AssetsEnum.CHECKBOX_ON_TEX.toString());
        checkboxOff = Assets.getInstance().manager.get(AssetsEnum.CHECKBOX_OFF_TEX.toString());

        if (checked)
            checkBoxImage = new Image(checkboxOn);
        else
            checkBoxImage = new Image(checkboxOff);

        checkBoxImage.setX(270);

        checkBoxImage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (checked) {
                    chackboxCallback.Uncheck();
                    ChangeCheckboxTexture();
                    checked = false;
                } else {
                    chackboxCallback.Check();
                    ChangeCheckboxTexture();
                    checked = true;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void initLabel() {
        float fontScale = 2.0f;
        label = new Label("DEFAULT TEXT", new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
    }

    public void setText(String text) {
        label.setText(text);
    }

    public boolean isChecked() {
        return checked;
    }
}
