package com.mygdx.teleclicker.Service;

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

    }

    /*
    *
    * Getters and etters
    *
    */

    public static ScreenManager getInstance() {
        return instance;
    }
}
