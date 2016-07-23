package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 23.07.2016.
 */
public class SettingsService {
    private final static String SETTING_MUSIC = "com.mygdx.clicker.prefs.setting.music";
    private final static String SETTING_SOUNDS = "com.mygdx.clicker.prefs.setting.sounds";

    private static SettingsService instance;

    private Preferences prefs;

    private boolean isMusic;
    private boolean isSounds;

    private SettingsService(){
        this.prefs = TeleClicker.getPrefs();
        load();
    }

    public void saveCurrentSettingsState() {
        prefs.putBoolean(SETTING_MUSIC, isMusic);
        prefs.putBoolean(SETTING_SOUNDS, isSounds);
        prefs.flush();
    }

    private void load() {
        isMusic = prefs.getBoolean(SETTING_MUSIC, false);
        isSounds = prefs.getBoolean(SETTING_SOUNDS, false);
    }

    /*
    *
    * Getters and setters
    *
    */

    public static SettingsService getInstance() {
        if(instance == null){
            instance = new SettingsService();
        }
        return instance;
    }

    public boolean isMusic() {
        return isMusic;
    }

    public boolean isSounds() {
        return isSounds;
    }

    public void setMusic(boolean music) {
        isMusic = music;
    }

    public void setSounds(boolean sounds) {
        isSounds = sounds;
    }
}
