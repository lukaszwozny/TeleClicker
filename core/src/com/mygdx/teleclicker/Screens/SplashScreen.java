package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;

/**
 * Created by Senpai on 21.07.2016.
 */
public class SplashScreen extends AbstractScreen {

    private Texture bgTexture;

    public SplashScreen(){
        super();
    }

    private void initTexture() {
        bgTexture = Assets.getInstance().manager.get(AssetsEnum.SPLASH_BG.toString());
        addActor(new Image(bgTexture));
    }

    @Override
    public void buildStage() {
        initTexture();
    }
}
