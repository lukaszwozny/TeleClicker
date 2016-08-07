package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;

/**
 * Created by Senpai on 22.07.2016.
 */
public class SoundService {

    private static SoundService instance;

    private Sound popSound;
    private Sound cashRegisterSound;
    private Sound evilLaughJewSound;
    private Sound bombExplosionSound;
    private Sound clickSound;

    private Music caketownMusic;

    private SoundService() {
        init();
    }

    public static SoundService getInstance() {
        if (instance == null) {
            instance = new SoundService();
        }
        return instance;
    }

    private boolean isSounds(){
        return SettingsService.getInstance().isSounds();
    }

    private boolean isMusic(){
        return SettingsService.getInstance().isMusic();
    }


    private void init() {
        popSound = AssetsEnum.POP_SOUND.getAsset();
        cashRegisterSound = AssetsEnum.CASH_REGISTER_SOUND.getAsset();
        evilLaughJewSound = AssetsEnum.EVIL_LAUGH_SOUND.getAsset();
        bombExplosionSound = AssetsEnum.BOMP_EXPLOSION_SOUND.getAsset();
        clickSound = AssetsEnum.CLICK_SOUND_1.getAsset();

        caketownMusic = AssetsEnum.CAKETOWN_MUSIC.getAsset();
    }

    public void playPopSound() {
        if (isSounds())
            popSound.play();
    }

    public void playCashRegisterSound() {
        if (isSounds())
            cashRegisterSound.play();
    }

    public void playEvillaughJewSound() {
        if (isSounds())
            evilLaughJewSound.play();
    }

    public void playBombExplosionSound() {
        if (isSounds())
            bombExplosionSound.play();
    }

    public void playClickSound() {
        if (isSounds())
            clickSound.play();
    }

    public void playCaketownMusic(boolean looped) {
        if (isMusic()){
            caketownMusic.play();
            caketownMusic.setLooping(looped);
        }
    }

    public void stopCaketownMusic() {
        caketownMusic.stop();
    }

}
