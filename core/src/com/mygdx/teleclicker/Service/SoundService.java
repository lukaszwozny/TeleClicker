package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Senpai on 10.07.2016.
 */
public class SoundService {

    private Sound moneySound;

    public SoundService(){
        init();
    }

    private void init() {
        moneySound = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.ogg"));
    }

    public void playMonaySound(){
        moneySound.play();
    }
}
