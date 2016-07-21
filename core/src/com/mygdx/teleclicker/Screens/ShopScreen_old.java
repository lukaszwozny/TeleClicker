package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.CloseShopButton;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.BuyButton_old;

/**
 * Created by Senpai on 11.07.2016.
 */
public class ShopScreen_old extends AbstractScreen_old {
    private final String SHOP_BG_DIR = "img/bg/shop_bg.png";
    private Image shopBg;

    private CloseShopButton closeShopButton;

    private BuyButton_old clickBuyButton;
    private BuyButton_old passiveBuyButton;

    public ShopScreen_old(TeleClicker game) {
        super(game);
    }

    @Override
    protected void init() {
        System.out.println("Hej tu shopscreen");
        // TODO implement better assets loading when game grows
//        initBg();
//        initCloseShopButton();
//        initBuyButtons();
//        game.getScoreService().printLabels(stage,true);
    }

    private void initBuyButtons() {
        final int START_X = 330;
        final int START_Y = 545;
        final int INTERVAL_Y = 51;
        clickBuyButton = new BuyButton_old(new IClickCallback() {
            @Override
            public void onClick() {
                if(clickBuyButton.isActive()){
                    game.getScoreService().increseNumberOfPointsPerClickBuys();
                }
                System.out.println("click button");
            }
        },START_X,START_Y);
        passiveBuyButton = new BuyButton_old(new IClickCallback() {
            @Override
            public void onClick() {
                if(passiveBuyButton.isActive()){
                    game.getScoreService().increseNumberOfPassivePointsBuys();
                }
                System.out.println("Passive");
            }
        },START_X,START_Y - INTERVAL_Y);
        stage.addActor(clickBuyButton);
        stage.addActor(passiveBuyButton);
    }

    private void updateLabelsForButtons() {
        clickBuyButton.initContent("Buy Click Power (" + game.getScoreService().getNumberOfPointsPerClickBuys()+")");
        passiveBuyButton.initContent("Buy Passive Income (" + game.getScoreService().getNumberOfPassivePointsPBuys()+")");
    }

    private void initCloseShopButton() {
        closeShopButton = new CloseShopButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.setScreen(new GameplayScreen_old(game, true));
            }
        });
        stage.addActor(closeShopButton);
    }

    private void initBg() {
        shopBg = new Image(new Texture(SHOP_BG_DIR));
        stage.addActor(shopBg);
    }

    @Override
    public void pause() {
        super.pause();
        game.getScoreService().saveCurrentGameState();
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
        game.getScoreService().updateScoreLabel();
        updateButtonsColor();
        updateLabelsForButtons();
        stage.act();
    }

    private void updateButtonsColor() {
        float points = game.getScoreService().getPoints();
        if(points > 20){
            clickBuyButton.updateColor(true);
        } else {
            clickBuyButton.updateColor(false);
        }
        if(points > 50){
            passiveBuyButton.updateColor(true);
        } else {
            passiveBuyButton.updateColor(false);
        }
    }
}
