package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;

/**
 * Created by Senpai on 10.07.2016.
 */
public class ResetScoreButton extends Button {

    final int WIDTH = 100;
    final int HEIGHT = 100;

    public ResetScoreButton(IClickCallback callback){
        super(prepareResetButtonStyle());

        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private static ButtonStyle prepareResetButtonStyle() {
        TextureAtlas atlas = Assets.getInstance().manager.get(AssetsEnum.RED_ATLAS.toString());
        Skin skin = new Skin(atlas);
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = skin.getDrawable("button_03");
        buttonStyle.down = skin.getDrawable("button_01");

        return buttonStyle;
    }
}
