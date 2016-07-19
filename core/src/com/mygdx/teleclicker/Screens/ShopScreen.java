package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.BuyButton;
import com.mygdx.teleclicker.ui.CloseShopButton;
import com.mygdx.teleclicker.ui.IClickCallback;

/**
 * Created by Senpai on 12.07.2016.
 */
public class ShopScreen extends AbstractScreen {
    private final String SHOP_BG_DIR = "img/bg/shop_bg.png";
    private Image shopBg;

    private CloseShopButton closeShopButton;

    private BuyButton clickBuyButton;
    private BuyButton passiveBuyButton;

    public ShopScreen(TeleClicker game) {
        super(game);
    }

    @Override
    protected void init() {
        System.out.println("Hej tu shopscreen");
        // TODO implement better assets loading when game grows
        initBg();
        initCloseShopButton();
//        initBuyButtons();
//        game.getScoreService().printLabels(stage,true);
    }

    private void initCloseShopButton() {
        closeShopButton = new CloseShopButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.setScreen(game.getGameplayScreen());
            }
        });
        stage.addActor(closeShopButton);
    }

    private void initBg() {
        shopBg = new Image(new Texture(SHOP_BG_DIR));
        stage.addActor(shopBg);
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        game.getScoreService().updateScoreLabel(true);
        //updateButtonsColor();
        //updateLabelsForButtons();
        stage.act();
    }
}
