package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.mygdx.teleclicker.ui.*;
import com.mygdx.teleclicker.ui.Buttons.CloseShopButton;

/**
 * Created by Senpai on 21.07.2016.
 */
public class ShopScreen extends AbstractScreen {
    private CashLabel cashLabel;

    private Label scoreLabelPoints;
    private Label scoreLabelPointsPerClick;
    private Label scoreLabelPointsPerSec;

    private MyTextButton backButton;

    private ShopEntry buyPointsPerSecEntry;
    private ShopEntry buyPointsPerClickEntry;

    public ShopScreen() {
        super();
    }

    @Override
    public void buildStage() {
        initBgTexture();
        initCashLabel();

//        initScoreLabels();
        initButtons();
        initShopEntrys();
        Gdx.input.setCatchBackKey(true);
    }

    private void initButtons() {
        initBackButton();

        final int WIDTH = 150;
        final int HEIGHT = 70;

        final int X = TeleClicker.WIDTH / 2 - WIDTH / 2;
        final int Y = 38+10;
        final int NTERVAL = HEIGHT + 20;

        backButton.setSize(WIDTH,HEIGHT);

        backButton.setPosition(X, Y);

        addActor(backButton);
    }

    private void initBackButton() {
        backButton = new RedTextButton("Back", new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY2);
            }
        });
        addActor(backButton);
    }

    private void initCashLabel() {
        cashLabel = new CashLabel();

        addActor(cashLabel);
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode){
            case Input.Keys.BACK:
                ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY2);
                break;
        }
        return false;
    }

    // ToDo Move to shop entry manager
    private void initShopEntrys() {
        final int START_Y = 596;
        final int INTERVAL_Y = 51;
        buyPointsPerSecEntry = new ShopEntry(new IClickCallback() {
            @Override
            public void onClick() {
                ScoreService.getInstance().addPointsPerSec(1);
                ScoreService.getInstance().addPoints(-100);
                ScoreService.getInstance().increseNumberOfPointsPerSecBuys();
            }
        },START_Y);

        buyPointsPerClickEntry = new ShopEntry(new IClickCallback() {
            @Override
            public void onClick() {
                ScoreService.getInstance().addPointsPerClick(1);
                ScoreService.getInstance().addPoints(-200);
                ScoreService.getInstance().increseNumberOfPointsPerClickBuys();
            }
        },START_Y - INTERVAL_Y);

        addActor(buyPointsPerSecEntry);
        addActor(buyPointsPerClickEntry);
    }

    private void initScoreLabels() {
        final float fontScale = 1.2f;
        final int POS_X = 40;
        final int POS_Y = TeleClicker.HEIGHT - 50;
        final int INTERVAL = 100;

        scoreLabelPoints = new Label("Test", new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
        scoreLabelPoints.setPosition(POS_X,POS_Y);

        scoreLabelPointsPerClick = new Label("Test", new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
        scoreLabelPointsPerClick.setPosition(POS_X+INTERVAL,POS_Y);

        scoreLabelPointsPerSec = new Label("Test", new Label.LabelStyle(FontService.getFont(fontScale), Color.BLACK));
        scoreLabelPointsPerSec.setPosition(POS_X+INTERVAL*2,POS_Y);

        addActor(scoreLabelPoints);
        addActor(scoreLabelPointsPerClick);
        addActor(scoreLabelPointsPerSec);
    }

    @Override
    public void initBgTexture() {
        bgTexture = AssetsEnum.SHOP_BG.getAsset();
        addActor(new Image(bgTexture));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
    }

    private void update() {
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
        if(points > 100){
            buyPointsPerSecEntry.updateColor(true);
        } else {
            buyPointsPerSecEntry.updateColor(false);
        }
        if(points > 200){
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
        scoreLabelPoints.setText("Erlangi\n" + ScoreService.getInstance().getPoints());

        scoreLabelPointsPerClick.setText("Per click\n" + ScoreService.getInstance().getPointsPerClick());

        scoreLabelPointsPerSec.setText("Per sec\n" + ScoreService.getInstance().getPointsPerSec());
    }
}
