package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.BuyButton;
import com.mygdx.teleclicker.ui.CloseShopButton;
import com.mygdx.teleclicker.ui.IClickCallback;

/**
 * Created by Senpai on 21.07.2016.
 */
public class ShopScreen extends AbstractScreen {

    private Label scoreLabelUp, scoreLabelBottom;

    private CloseShopButton closeShopButton;
    private BuyButton buyPointsPerSecButton;
    private BuyButton buyPointsPerClickButton;

    public ShopScreen() {
        super();
    }

    @Override
    public void buildStage() {
        initBgTexture();
        initScoreLabels();
        initCloseShopButton();
        initBuyButtons();
    }

    private void initScoreLabels() {
        final float fontScale = 1.2f;
        final int POS_X = 40;
        final int POS_Y = TeleClicker.HEIGHT - 50;

        BitmapFont font = new BitmapFont();
        font.getData().setScale(fontScale);

        scoreLabelUp = new Label("Test UP", new Label.LabelStyle(font, Color.BLACK));
        scoreLabelUp.setPosition(POS_X,POS_Y);

        scoreLabelBottom = new Label("Test BOTTOM", new Label.LabelStyle(font, Color.BLACK));
        scoreLabelBottom.setPosition(POS_X,POS_Y-60);

        addActor(scoreLabelUp);
        addActor(scoreLabelBottom);
    }

    private void initBuyButtons() {
        final int START_X = 330;
        final int START_Y = 545;
        final int INTERVAL_Y = 51;
        buyPointsPerSecButton = new BuyButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScoreService.getInstance().increseNumberOfPointsPerSecBuys();
                ScoreService.getInstance().addPointsPerSec(1);
            }
        }, START_X, START_Y);
        addActor(buyPointsPerSecButton);

        buyPointsPerClickButton = new BuyButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScoreService.getInstance().increseNumberOfPointsPerClickBuys();
                ScoreService.getInstance().addPointsPerClick(1);
            }
        }, START_X, START_Y - INTERVAL_Y);
        addActor(buyPointsPerClickButton);
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

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
    }

    private void update() {
        updateScoreLabels();
    }

    private void updateScoreLabels() {
        scoreLabelUp.setText("Erlangi\n" + ScoreService.getInstance().getPoints());

        scoreLabelBottom.setText("Click power\n" + ScoreService.getInstance().getPointsPerClick());
    }
}
