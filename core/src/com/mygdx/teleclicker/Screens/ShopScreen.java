package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;

/**
 * Created by Senpai on 21.07.2016.
 */
public class ShopScreen extends AbstractScreen {

    public ShopScreen(){
        super();
    }

    @Override
    public void buildStage() {
        initBgTexture();
    }

    @Override
    public void initBgTexture() {
        bgTexture = Assets.getInstance().manager.get(AssetsEnum.SHOP_BG.toString());
        addActor(new Image(bgTexture));
    }
}
