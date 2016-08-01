package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.FontService;
import com.mygdx.teleclicker.Service.HttpService;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.Service.SoundService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.CloseSettingsButton;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.ResetScoreButton;

/**
 * Created by Senpai on 01/08/2016.
 */
public class HttpScreen extends AbstractScreen {
    private CloseSettingsButton closeButton;
    private Label requestLabel;
    private ResetScoreButton requestButton;

    private HttpService httpService;

    @Override
    public void buildStage() {
        initBgTexture();

        initHttpService();
        initCloseButton();
        initRequestLabel();
        initRequestButton();
    }

    private void initHttpService() {
        httpService = new HttpService();
    }

    private void initCloseButton() {
        closeButton = new CloseSettingsButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().setScreen(ScreenEnum.SETTINGS);
            }
        });
        addActor(closeButton);
    }

    private void initRequestButton() {
        requestButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                httpService.addPlayerRequest("Android","Test");
            }
        });
        final float X = TeleClicker.WIDTH/2 - requestButton.getWidth()/2;
        final int Y = 10;
        requestButton.setPosition(X,Y);

        addActor(requestButton);
    }

    private void initRequestLabel() {
        requestLabel = new Label("Test", new Label.LabelStyle(FontService.getFont(), Color.BLACK));
        requestLabel.setPosition(10,550);

        addActor(requestLabel);
    }

    @Override
    public void initBgTexture() {
        bgTexture = Assets.getInstance().manager
                .get(AssetsEnum.SETTINGS_BG.toString());
        addActor(new Image(bgTexture));
    }

    @Override
    public void show() {
        super.show();
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.HTTP);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
    }

    private void update() {
        requestLabel.setText(httpService.getResponsStr());
    }
}
