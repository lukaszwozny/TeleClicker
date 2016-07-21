package com.mygdx.teleclicker;

import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Screens.GameplayScreen_old;
import com.mygdx.teleclicker.Screens.ShopScreenold_1;
import com.mygdx.teleclicker.Screens.SplashScreen_old;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenManager;
import com.mygdx.teleclicker.Service.SoundService;

public class TeleClicker extends Game {
    public final static String GAME_PREFS = "com.mygdx.clicker.prefs";
    public final static String GAME_NAME = "Tele Clicker";

    public static enum ScreenType{
        SPLASH,
        GAMEPLAY,
        SHOP
    }

    private ScreenType actualScreen;

    private SplashScreen_old splashScreen;
    private GameplayScreen_old gameplayScreen;
    private ShopScreenold_1 shopScreen;

    public final static int WIDTH = 405;
    public final static int HEIGHT = 720;

    private boolean paused;

    private SoundService soundService;
    private ScoreService scoreService;

    private Preferences prefs;


    @Override
    public void create() {
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().SetScreen(ScreenEnum.SHOP);
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        actualScreen = ScreenType.SPLASH;
        soundService = new SoundService();
        scoreService = new ScoreService(this, prefs);

        splashScreen = new SplashScreen_old(this);
        gameplayScreen = new GameplayScreen_old(this);
        shopScreen = new ShopScreenold_1(this);
        shopScreen.hide();
    }

    public boolean isPaused() {
        return paused;
    }

    /**
     * ---------------------
     * getters and setters
     */

    public ScreenType getActualScreen() {
        return ScreenType.SPLASH;
    }

    public void setActualScreen(ScreenType actualScreen) {
        this.actualScreen = actualScreen;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public SoundService getSoundService() {
        return soundService;
    }

    public ScoreService getScoreService() {
        return scoreService;
    }

    public GameplayScreen_old getGameplayScreen() {
        return gameplayScreen;
    }

    public ShopScreenold_1 getShopScreen() {
        return shopScreen;
    }
}
