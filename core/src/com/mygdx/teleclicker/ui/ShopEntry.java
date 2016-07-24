package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Service.FontService;

/**
 * Created by Senpai on 22.07.2016.
 */
public class ShopEntry extends Group {

    private static Button.ButtonStyle buttonStyleGreen;
    private static Button.ButtonStyle buttonStyleRed;

    private Button button;
    private Label label;

    private int labelPosX;
    private int buttonPosX;
    private int posY;

    private boolean buyable = false;

    public ShopEntry(final IClickCallback callback, int posY) {
        this.labelPosX = 10;
        this.buttonPosX = 330;
        this.posY = posY;

        initLabel();
        initBuyButton(callback);
    }

    public void setText(String text){
        label.setText(text);
    }

    private void initBuyButton(final IClickCallback callback) {
        prepareResetButtonStyle();
        button = new Button(buttonStyleRed);
        button.setPosition(buttonPosX, posY);

        button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if(buyable){
                    callback.onClick();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        addActor(button);
    }

    private void initLabel() {
        final int START_X = 30;

        String text = "DEFAULT TEXT";
        float fontScale = 1.4f;
        label = new Label(text, new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
        label.setPosition(labelPosX, posY);
        this.addActor(label);
    }

    public void updateColor(boolean buyable) {
        if (buyable) {
            button.setStyle(buttonStyleGreen);
        } else {
            button.setStyle(buttonStyleRed);
        }
        this.buyable = buyable;
    }

    private static void prepareResetButtonStyle() {
        TextureAtlas atlasRed = Assets.getInstance().manager.get(AssetsEnum.RED_ATLAS.toString());
        Skin skinRed = new Skin(atlasRed);
        buttonStyleRed = new Button.ButtonStyle();
        buttonStyleRed.up = skinRed.getDrawable("button_04");
        buttonStyleRed.down = skinRed.getDrawable("button_02");

        TextureAtlas atlasGreen = Assets.getInstance().manager.get(AssetsEnum.GREEN_ATLAS.toString());
        Skin skinGreen = new Skin(atlasGreen);
        buttonStyleGreen = new Button.ButtonStyle();
        buttonStyleGreen.up = skinGreen.getDrawable("button_04");
        buttonStyleGreen.down = skinGreen.getDrawable("button_02");
    }

    public boolean isBuyble() {
        return buyable;
    }
}
