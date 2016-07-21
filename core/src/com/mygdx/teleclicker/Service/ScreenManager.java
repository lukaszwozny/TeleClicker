package com.mygdx.teleclicker.Service;

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

    /*
    *
    * Getters and etters
    *
    */

    public static ScreenManager getInstance() {
        return instance;
    }
}
