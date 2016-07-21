package com.mygdx.teleclicker.Core;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Senpai on 21.07.2016.
 */
public class Assets implements Disposable {

    public final AssetManager manager = new AssetManager();

    public void load(){

    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
