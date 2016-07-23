package com.mygdx.teleclicker;

import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SettingsService;
import com.mygdx.teleclicker.Service.SoundService;

public class TeleClicker extends Game {
    public final static String GAME_PREFS = "com.mygdx.clicker.prefs";
    public final static String GAME_NAME = "Tele Clicker";

    public final static int WIDTH = 405;
    public final static int HEIGHT = 720;

    private static Preferences prefs;

    @Override
    public void create() {
        SoundService.getInstance().playCaketownMusic(true);
        ScreenService.getInstance().initialize(this);
        ScreenService.getInstance().setScreen(ScreenEnum.SPLASH);
    }

    /**
     * ---------------------
     * getters and setters
     */

    public static Preferences getPrefs() {
        if (prefs == null) {
            prefs = Gdx.app.getPreferences(GAME_PREFS);
        }
        return prefs;
    }
}
