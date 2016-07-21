package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.ui.CloseShopButton;
import com.mygdx.teleclicker.ui.IClickCallback;

/**
 * Created by Senpai on 21.07.2016.
 */
public class ShopScreen extends AbstractScreen {

    private CloseShopButton closeShopButton;

    public ShopScreen(){
        super();
    }

    @Override
    public void buildStage() {
        initBgTexture();
        initCloseShopButton();
    }

    private void initCloseShopButton() {
        closeShopButton = new CloseShopButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().SetScreen(ScreenEnum.GAMEPLAY);
            }
        });
        addActor(closeShopButton);
    }

    @Override
    public void initBgTexture() {
        bgTexture = Assets.getInstance().manager.get(AssetsEnum.SHOP_BG.toString());
        addActor(new Image(bgTexture));
    }
}
