package com.mygdx.teleclicker.Screens;

import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.ui.CashLabel;
import com.mygdx.teleclicker.ui.MainBackground;

/**
 * Created by Senpai on 11/08/2016.
 */
public class GameplayScreen extends AbstractScreen {
    private CashLabel cashLabel;

    @Override
    public void buildStage() {
        initBgTexture();
        initCashLabel();
    }

    private void initCashLabel() {
        cashLabel = new CashLabel();
        addActor(cashLabel);
    }

    @Override
    public void initBgTexture() {
        MainBackground mainBackground = new MainBackground(MainBackground.Direction.COUNTER_CLOSKWISE);
        addActor(mainBackground);
    }
}
