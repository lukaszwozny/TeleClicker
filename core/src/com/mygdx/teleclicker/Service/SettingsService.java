package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Preferences;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 23.07.2016.
 */
public class SettingsService {
    private final static String SETTING_MUSIC = "com.mygdx.clicker.prefs.setting.music";
    private final static String SETTING_SOUNDS = "com.mygdx.clicker.prefs.setting.sounds";
    private final static String SETTING_REMEMBER = "com.mygdx.clicker.prefs.setting.remember";

    private static SettingsService instance;

    private Preferences prefs;

    private boolean music;
    private boolean sounds;

    private boolean remember;

    private SettingsService(){
        this.prefs = TeleClicker.getPrefs();
        load();
    }

    public void saveCurrentSettingsState() {
        prefs.putBoolean(SETTING_MUSIC, music);
        prefs.putBoolean(SETTING_SOUNDS, sounds);
        prefs.putBoolean(SETTING_REMEMBER, remember);
        prefs.flush();
    }

    private void load() {
        music = prefs.getBoolean(SETTING_MUSIC, false);
        sounds = prefs.getBoolean(SETTING_SOUNDS, false);
        remember = prefs.getBoolean(SETTING_REMEMBER, false);
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
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isSounds() {
        return sounds;
    }

    public void setSounds(boolean sounds) {
        this.sounds = sounds;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
