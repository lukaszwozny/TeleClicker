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
        popSound = Assets.getInstance().manager.get(AssetsEnum.POP_SOUND.toString());
        cashRegisterSound = Assets.getInstance().manager.get(AssetsEnum.CASH_REGISTER_SOUND.toString());
        evilLaughJewSound = Assets.getInstance().manager.get(AssetsEnum.EVIL_LAUGH_SOUND.toString());
        bombExplosionSound = Assets.getInstance().manager.get(AssetsEnum.BOMP_EXPLOSION_SOUND.toString());
        clickSound = Assets.getInstance().manager.get(AssetsEnum.CLICK_SOUND_1.toString());

        caketownMusic = Assets.getInstance().manager.get(AssetsEnum.CAKETOWN_MUSIC.toString());
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
