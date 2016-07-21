package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScreenManager;

/**
 * Created by Senpai on 21.07.2016.
 */
public class SplashScreen extends AbstractScreen {

    public SplashScreen(){
        super();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                ScreenManager.getInstance().SetScreen(ScreenEnum.GAMEPLAY);
            }
        },2);
    }

    @Override
    public void buildStage() {
        initBgTexture();
    }

    @Override
    public void initBgTexture() {
        bgTexture = Assets.getInstance().manager.get(AssetsEnum.SPLASH_BG.toString());
        addActor(new Image(bgTexture));
    }
}
