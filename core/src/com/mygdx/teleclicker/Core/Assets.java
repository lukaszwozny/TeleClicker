package com.mygdx.teleclicker.Core;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.teleclicker.Enums.AssetsEnum;

/**
 * Created by Senpai on 21.07.2016.
 */
public class Assets implements Disposable {

    private static Assets instance;

    public final AssetManager manager = new AssetManager();

    private Assets(){
        super();
        loadAll();
        manager.finishLoading();
        if(manager.update()){
            System.out.println("Finished loading");
        }
    }

    private void loadAll() {
        loadSplahAssets();
        loadGameplayAssets();
        loadShopAssets();
    }


    public void loadSplahAssets(){
        manager.load(AssetsEnum.SPLASH_BG.toString(), Texture.class);
    }

    public void loadGameplayAssets(){
        manager.load(AssetsEnum.GAMEPLAY_BG.toString(), Texture.class);
    }

    public void loadShopAssets(){
        manager.load(AssetsEnum.SHOP_BG.toString(), Texture.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

    public static Assets getInstance() {
        if(instance == null){
            instance = new Assets();
        }
        return instance;
    }

}
