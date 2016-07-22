package com.mygdx.teleclicker.Core;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
        // Textures
        manager.load(AssetsEnum.GAMEPLAY_BG.toString(), Texture.class);
        manager.load(AssetsEnum.PLAYER_TEX.toString(), Texture.class);
        manager.load(AssetsEnum.PHONE_ON_TEX.toString(), Texture.class);
        manager.load(AssetsEnum.PHONE_OFF_TEX.toString(), Texture.class);

        loadFlyingObjectAssets();

        manager.load(AssetsEnum.DIALOG_BOG_TEX.toString(),Texture.class);

        // Sounds
        manager.load(AssetsEnum.POP_SOUND.toString(), Sound.class);

        // Music
        manager.load(AssetsEnum.CAKETOWN_MUSIC.toString(), Music.class);

        // Atlases
        manager.load(AssetsEnum.RED_ATLAS.toString(), TextureAtlas.class);
    }

    private void loadFlyingObjectAssets() {
        //Textures
        manager.load(AssetsEnum.MONEY_TEX.toString(),Texture.class);
        manager.load(AssetsEnum.DIAMOND_TEX.toString(),Texture.class);
        manager.load(AssetsEnum.BOMB_TEX.toString(),Texture.class);
        manager.load(AssetsEnum.JEW_TEX.toString(),Texture.class);

        // Sounds
        manager.load(AssetsEnum.CASH_REGISTER_SOUND.toString(), Sound.class);
        manager.load(AssetsEnum.EVIL_LAUGH_SOUND.toString(), Sound.class);
        manager.load(AssetsEnum.BOMP_EXPLOSION_SOUND.toString(), Sound.class);
        manager.load(AssetsEnum.CLICK_SOUND_1.toString(), Sound.class);
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
