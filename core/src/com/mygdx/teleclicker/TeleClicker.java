package com.mygdx.teleclicker;

import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Screens.GameplayScreen_old;
import com.mygdx.teleclicker.Screens.ShopScreenold_1;
import com.mygdx.teleclicker.Screens.SplashScreen_old;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.teleclicker.Service.ScoreService_old;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.Service.SoundService_old;

public class TeleClicker extends Game {
    public final static String GAME_PREFS = "com.mygdx.clicker.prefs";
    public final static String GAME_NAME = "Tele Clicker";

    public static enum ScreenType{
        SPLASH,
        GAMEPLAY,
        SHOP
    }

//    private ScreenType actualScreen;

    private SplashScreen_old splashScreen;
    private GameplayScreen_old gameplayScreen;
    private ShopScreenold_1 shopScreen;

    public final static int WIDTH = 405;
    public final static int HEIGHT = 720;

    private boolean paused;

    private SoundService_old soundService;
    private ScoreService_old scoreService;

    private static Preferences prefs;

    private static ScreenEnum actualScreen;

    @Override
    public void create() {
        SoundService.getInstance().playCaketownMusic(true);
        ScreenService.getInstance().initialize(this);
        ScreenService.getInstance().SetScreen(ScreenEnum.SPLASH);
    }

    public boolean isPaused() {
        return paused;
    }

    /**
     * ---------------------
     * getters and setters
     */

    public static Preferences getPrefs() {
        if(prefs == null){
            prefs = Gdx.app.getPreferences(GAME_PREFS);
        }
        return prefs;
    }

    public static ScreenEnum getActualScreen() {
        return actualScreen;
    }

    public static void setActualScreen(ScreenEnum actualScreen) {
        TeleClicker.actualScreen = actualScreen;
    }

    public ScreenType getActualScreen2() {
        return ScreenType.SPLASH;
    }

    public void setActualScreen(ScreenType actualScreen) {
//        this.actualScreen = actualScreen;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public SoundService_old getSoundService() {
        return soundService;
    }

    public ScoreService_old getScoreService() {
        return scoreService;
    }

    public GameplayScreen_old getGameplayScreen() {
        return gameplayScreen;
    }

    public ShopScreenold_1 getShopScreen() {
        return shopScreen;
    }
}
