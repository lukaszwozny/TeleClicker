package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Service.FontService;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 23.07.2016.
 */
public class WarningBox extends Group {

    private Image bgImage;
    private Image buttonYes;
    private Image buttonNo;

    private Label textLabel;

    public WarningBox(IWarningCallback callback) {
        super();
        initImages();
        initLabel();
        setWidth(bgImage.getWidth());
        setHeight(bgImage.getHeight());
        initButtons(callback);
        group();
    }

    private final void initButtons(final IWarningCallback callback) {
        Texture buttonYesTex = AssetsEnum.BUTTON_YES_TEX.getAsset();
        Texture buttonNoTex = AssetsEnum.BUTTON_NO_TEX.getAsset();

        buttonYes = new Image(buttonYesTex);
        buttonNo = new Image(buttonNoTex);

        final int POSY = 20;
        final float INTERVAL = buttonYes.getWidth()/2 + 20;

        buttonYes.setPosition(this.getWidth()/2 - buttonYes.getWidth()/2 - INTERVAL, POSY);
        buttonNo.setPosition(this.getWidth()/2 - buttonNo.getWidth()/2 + INTERVAL, POSY);

        buttonYes.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.Yes();
                removeAll();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonNo.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.No();
                removeAll();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void removeAll() {
        bgImage.remove();
        buttonYes.remove();
        buttonNo.remove();
        textLabel.remove();
        remove();
    }

    public void setText(String text) {
        textLabel.setText(text);
    }

    private void group() {
        addActor(bgImage);
        addActor(textLabel);
        addActor(buttonYes);
        addActor(buttonNo);
    }

    private void initLabel() {
        textLabel = new Label("TEST", new Label.LabelStyle(FontService.getFont(), Color.BLACK));

        final int POSX = 25;
        final float POSY = bgImage.getWidth() / 2 - textLabel.getHeight() / 2 - 55;

        textLabel.setPosition(POSX, POSY);
    }

    private void initImages() {
        Texture bgTexture = AssetsEnum.WARNING_BG.getAsset();
        bgImage = new Image(bgTexture);
    }
}
