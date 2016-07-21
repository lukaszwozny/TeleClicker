package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Screen;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 21.07.2016.
 */
public class ScreenManager {

    private static ScreenManager instance;

    private TeleClicker game;

    private ScreenManager(){
        super();
    }

    public void initialize(TeleClicker game){
        this.game = game;
    }

    public void SetScreen(ScreenEnum screenEnum){
        // Get current screen to dispose it
        Screen curentScreen = game.getScreen();

        // Show new Screen
        AbstractScreen newScreen = screenEnum.getScreen();
        newScreen.buildStage();
        game.setScreen(newScreen);

        // Dispose old screen
        if(curentScreen!=null){
            curentScreen.dispose();
        }
    }

    public static ScreenManager getInstance() {
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }
}
