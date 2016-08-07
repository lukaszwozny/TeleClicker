package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Senpai on 05/08/2016.
 */
public abstract class MyTextButton extends Group {
    private Image image;
    protected Drawable upDrawable;
    protected Drawable downDrawable;
    private Label label;

    private float labelX;
    private float labelY;
    private Vector2 labelDownVector;

    public MyTextButton(IClickCallback callback) {
        this("",callback);
    }

    public MyTextButton(String text, IClickCallback calback) {
        labelDownVector = new Vector2(0, -3);

        initAssets(text);
        groupAll();
        initListener(calback);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        image.setSize(width, height);
        refreshLabelPositon();
    }

    protected abstract void initDrawables();

    public void shiftLabel(float shiftX, float shiftY) {
        shiftLabel(new Vector2(shiftX, shiftY));
    }

    public void shiftLabel(Vector2 shiftVector) {
        labelX += shiftVector.x;
        labelY += shiftVector.y;
        label.setPosition(labelX, labelY);
    }

    public void setLabelDownVector(float x, float y) {
        setLabelDownVector(new Vector2(x, y));
    }

    public void setLabelDownVector(Vector2 vector) {
        labelDownVector = vector;
    }

    private void initListener(final IClickCallback calback) {
        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                image.setDrawable(downDrawable);
                label.setPosition(labelX + labelDownVector.x, labelY + labelDownVector.y);
                calback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                image.setDrawable(upDrawable);
                label.setPosition(labelX, labelY);
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    private void groupAll() {
        addActor(image);
        addActor(label);
    }

    private void initAssets(String text) {
        initDrawables();

        Skin skin = new Skin(Gdx.files.internal("libgdx/uiskin.json"));
        label = new Label(text, skin);

        image = new Image(upDrawable);

        refreshLabelPositon();
    }

    protected void refreshLabelPositon(){
        labelX = image.getWidth() / 2 - label.getWidth() / 2;
        labelY = image.getHeight() / 2 - label.getHeight() / 2;

        label.setPosition(labelX, labelY);
    }
}
