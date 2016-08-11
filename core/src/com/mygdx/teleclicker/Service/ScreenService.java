package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Screen;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 21.07.2016.
 */
public class ScreenService {

    private static ScreenService instance;

    private ScreenEnum actualScreenEnum;

    private TeleClicker game;

    private ScreenService(){
        super();
    }

    public void initialize(TeleClicker game){
        this.game = game;
    }

    public void setScreen(ScreenEnum screenEnum){
        // Get current screen to dispose it
        Screen curentScreen = game.getScreen();
        ScreenEnum currentScreenEnum = actualScreenEnum;

        AbstractScreen newScreen = null;

//        switch (screenEnum){
//            case GAMEPLAY:
//                if(gameplayScreen == null){
//                    gameplayScreen = screenEnum.getScreen();
//                    gameplayScreen.buildStage();
//                }
//                newScreen = gameplayScreen;
//                break;
//            case SHOP:
//                if(shopScreen == null){
//                    shopScreen = screenEnum.getScreen();
//                    shopScreen.buildStage();
//                }
//                newScreen = shopScreen;
//                break;
//            default:
//                newScreen = screenEnum.getScreen();
//                newScreen.buildStage();
//                break;
//        }


        newScreen = screenEnum.getScreen();
        newScreen.buildStage();
        game.setScreen(newScreen);

        // Dispose old screen
        if(curentScreen!=null){
            curentScreen.dispose();
        }
    }

    public static ScreenService getInstance() {
        if(instance == null){
            instance = new ScreenService();
        }
        return instance;
    }

    public ScreenEnum getActualScreenEnum() {
        return actualScreenEnum;
    }

    public void setActualScreenEnum(ScreenEnum actualScreenEnum) {
        this.actualScreenEnum = actualScreenEnum;
    }
}
