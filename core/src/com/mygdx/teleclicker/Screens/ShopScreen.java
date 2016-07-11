package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.CloseShopButton;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.BuyButton;

/**
 * Created by Senpai on 11.07.2016.
 */
public class ShopScreen extends AbstractScreen {
    private final String SHOP_BG_DIR = "img/bg/shop_bg.png";
    private Image shopBg;

    private CloseShopButton closeShopButton;

    private BuyButton clickBuyButton;
    private BuyButton passiveBuyButton;

    public ShopScreen(final TeleClicker game) {
        super(game);
    }

    @Override
    protected void init() {
        // TODO implement better assets loading when game grows
        initBg();
        initCloseShopButton();
        initBuyButtons();
        game.getScoreService().printLabels(stage,true);
    }

    private void initBuyButtons() {
        final int START_X = 330;
        final int START_Y = 545;
        final int INTERVAL_Y = 51;
        clickBuyButton = new BuyButton(new IClickCallback() {
            @Override
            public void onClick() {
                clickBuyButton.updateColor(true);
            }
        },START_X,START_Y);
        passiveBuyButton = new BuyButton(new IClickCallback() {
            @Override
            public void onClick() {
                clickBuyButton.updateColor(true);
            }
        },START_X,START_Y - INTERVAL_Y);
        stage.addActor(clickBuyButton);
        stage.addActor(passiveBuyButton);
    }

    private void initCloseShopButton() {
        closeShopButton = new CloseShopButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.setScreen(new GameplayScreen(game, true));
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
        stage.act();
    }
}
