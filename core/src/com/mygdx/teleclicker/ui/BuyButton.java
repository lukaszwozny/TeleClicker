package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Senpai on 11.07.2016.
 */
public class BuyButton extends Button {

    private static ButtonStyle buttonStyleGreen;
    private static ButtonStyle buttonStyleRed;

    private GameLabel label;

    private int posX;
    private int posY;

    private int numberOfBuys;

    private boolean active = false;

    public BuyButton(final IClickCallback callback, int posX, int posY) {
        super(prepareResetButtonStyle());
        init(callback);
        this.setX(posX);
        this.setY(posY);
        this.posX = posX;
        this.posY = posY;

        label = new GameLabel();
    }

    public void initContent(String text) {
        label.setText(text);
        label.setPosition(15, posY + 20);

        this.getStage().addActor(label);
    }

    public void Buy(){
        numberOfBuys++;
    }

    public void updateColor(boolean isActive) {
        if (isActive) {
            this.setStyle(buttonStyleGreen);
        } else {
            this.setStyle(buttonStyleRed);
        }
        active = isActive;
    }

    private void init(final IClickCallback callback) {
        this.setWidth(70);
        this.setHeight(38);

        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private static ButtonStyle prepareResetButtonStyle() {
        TextureAtlas atlasRed = new TextureAtlas(Gdx.files.internal("img/atlas/ui-red.atlas"));
        Skin skinRed = new Skin(atlasRed);
        buttonStyleRed = new ButtonStyle();
        buttonStyleRed.up = skinRed.getDrawable("button_04");
        buttonStyleRed.down = skinRed.getDrawable("button_02");

        TextureAtlas atlasGreen = new TextureAtlas(Gdx.files.internal("img/atlas/ui-green.atlas"));
        Skin skinGreen = new Skin(atlasGreen);
        buttonStyleGreen = new ButtonStyle();
        buttonStyleGreen.up = skinGreen.getDrawable("button_04");
        buttonStyleGreen.down = skinGreen.getDrawable("button_02");

        return buttonStyleRed;
    }


    public boolean isActive() {
        return active;
    }

    public int getNumberOfBuys() {
        return numberOfBuys;
    }
}
