package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScreenService;

/**
 * Created by Senpai on 21.07.2016.
 */
public class SplashScreen extends AbstractScreen {

    public SplashScreen(){
        super();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY);
            }
        },2);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode){
            case Input.Keys.BACK:
                Gdx.app.exit();
                break;
        }
        return false;
    }

    @Override
    public void buildStage() {
        initBgTexture();
    }

    @Override
    public void show() {
        super.show();
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.SPLASH);
    }

    @Override
    public void initBgTexture() {
        bgTexture = AssetsEnum.SPLASH_BG.getAsset();
        addActor(new Image(bgTexture));
    }
}
