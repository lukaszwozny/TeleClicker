package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Senpai on 10.07.2016.
 */
public class SoundService {

    private Sound popSound;
    private Sound cashRegisterSound;
    private Sound evilLaughJewSound;
    private Sound bombExplosionSound;

    public SoundService(){
        init();
    }

    private void init() {
        popSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.mp3"));
        cashRegisterSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cash_register.mp3"));
        evilLaughJewSound = Gdx.audio.newSound(Gdx.files.internal("sounds/evil_laugh_jew.mp3"));
        bombExplosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bomb_explosion.mp3"));
    }

    public void playPopSound(){
        popSound.play();
    }

    public void playCashRegisterSound(){
        cashRegisterSound.play();
    }

    public void playEvillaughJewSound(){
        evilLaughJewSound.play();
    }

    public void playBombExplosionSound(){
        bombExplosionSound.play();
    }
}
