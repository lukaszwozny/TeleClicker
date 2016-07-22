package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.FontService;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.ShopEntry;
import com.mygdx.teleclicker.ui.CloseShopButton;
import com.mygdx.teleclicker.ui.IClickCallback;

/**
 * Created by Senpai on 21.07.2016.
 */
public class ShopScreen extends AbstractScreen {

    private Label scoreLabelUp, scoreLabelBottom;

    private CloseShopButton closeShopButton;

    private ShopEntry buyPointsPerSecEntry;
    private ShopEntry buyPointsPerClickEntry;

    public ShopScreen() {
        super();
    }

    @Override
    public void buildStage() {
        initBgTexture();
        initScoreLabels();
        initCloseShopButton();
        initShopEntrys();
    }

    // ToDo Move to shop entry manager
    private void initShopEntrys() {
        final int START_Y = 545;
        final int INTERVAL_Y = 51;
        buyPointsPerSecEntry = new ShopEntry(new IClickCallback() {
            @Override
            public void onClick() {
                ScoreService.getInstance().addPointsPerSec(1);
                ScoreService.getInstance().addPoints(-10000);
                ScoreService.getInstance().increseNumberOfPointsPerSecBuys();
            }
        },START_Y);

        buyPointsPerClickEntry = new ShopEntry(new IClickCallback() {
            @Override
            public void onClick() {
                ScoreService.getInstance().addPointsPerClick(1);
                ScoreService.getInstance().addPoints(-15000);
                ScoreService.getInstance().increseNumberOfPointsPerClickBuys();
            }
        },START_Y + INTERVAL_Y);

        addActor(buyPointsPerSecEntry);
        addActor(buyPointsPerClickEntry);
    }

    private void initScoreLabels() {
        final float fontScale = 1.2f;
        final int POS_X = 40;
        final int POS_Y = TeleClicker.HEIGHT - 50;

        scoreLabelUp = new Label("Test UP", new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
        scoreLabelUp.setPosition(POS_X,POS_Y);

        scoreLabelBottom = new Label("Test BOTTOM", new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
        scoreLabelBottom.setPosition(POS_X,POS_Y-60);

        addActor(scoreLabelUp);
        addActor(scoreLabelBottom);
    }

    private void initCloseShopButton() {
        closeShopButton = new CloseShopButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY);
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
        updateButtonsColor();
        updateShopEntrysLabels();
    }

    private void updateShopEntrysLabels() {
        buyPointsPerSecEntry.setText("Buy Passive Income (" + ScoreService.getInstance().getNumberOfPointsPerSecBuys()+")");
        buyPointsPerClickEntry.setText("Buy Click Power (" + ScoreService.getInstance().getNumberOfPointsPerClickBuys()+")");
    }

    // ToDo Move to ShopEntryManager
    private void updateButtonsColor() {
        float points = ScoreService.getInstance().getPoints();
        if(points > 10000){
            buyPointsPerSecEntry.updateColor(true);
        } else {
            buyPointsPerSecEntry.updateColor(false);
        }
        if(points > 15000){
            buyPointsPerClickEntry.updateColor(true);
        } else {
            buyPointsPerClickEntry.updateColor(false);
        }

    }

    @Override
    public void show() {
        super.show();
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.SHOP);
    }

    private void updateScoreLabels() {
        scoreLabelUp.setText("Erlangi\n" + ScoreService.getInstance().getPoints());

        scoreLabelBottom.setText("Click power\n" + ScoreService.getInstance().getPointsPerClick());
    }
}
