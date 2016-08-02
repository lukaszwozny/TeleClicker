package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Entities.PlayerStats;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.StatusEnum;
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
public class LoginScreen extends AbstractScreen {
    private CloseSettingsButton closeButton;
    private Label requestLabel;
    private ResetScoreButton requestButton;
    private ResetScoreButton newPlayerButton;

    private HttpService httpService;

    private TextField loginTextField;
    private TextField passwordTextField;

    private Skin skin;

    private PlayerStats playerStats;

    @Override
    public void buildStage() {
        initBgTexture();
        initSkin();
        initTextFields();

        initHttpService();
        initCloseButton();
        initRequestLabel();
        initRequestButton();
        initNewPlayerButton();

        initPlayerStats();
    }

    private void initPlayerStats() {
        playerStats = new PlayerStats.Builder()
                .id(1)
                .points(10)
                .pointsPerSec(50)
                .pointsPerClick(4)
                .numberOfClicks(0)
                .numberOfPointsPerClickPBuys(1)
                .numberOfPointsPerSecBuys(5)
                .build();
    }

    private void initNewPlayerButton() {
        newPlayerButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().setScreen(ScreenEnum.NEW_PLAYER);
            }
        });
        addActor(newPlayerButton);
    }

    private void initTextFields() {
        loginTextField = new TextField("Login", skin);
        passwordTextField = new TextField("Password", skin);

        final float X = TeleClicker.WIDTH / 2 - loginTextField.getWidth() / 2;
        final float START_Y = 450f;
        final float INTERVAL = loginTextField.getHeight() + 20;

        loginTextField.setPosition(X, START_Y);
        passwordTextField.setPosition(X, START_Y - INTERVAL);

        addActor(loginTextField);
        addActor(passwordTextField);
    }

    private void initSkin() {
        skin = new Skin(Gdx.files.internal("libgdx/uiskin.json"));
    }


    private void initHttpService() {
        httpService = new HttpService();
    }

    private void initCloseButton() {
        closeButton = new CloseSettingsButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY);
            }
        });
        addActor(closeButton);
    }

    private void initRequestButton() {
        requestButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                httpService.saveStatsRequest(playerStats);
//                httpService.loginRequest(
//                        loginTextField.getText(),
//                        passwordTextField.getText()
//                );
            }
        });
        final float X = TeleClicker.WIDTH / 2 - requestButton.getWidth() / 2;
        final int Y = 10;
        requestButton.setPosition(X, Y);

        addActor(requestButton);
    }

    private void initRequestLabel() {
        requestLabel = new Label("Test", new Label.LabelStyle(FontService.getFont(), Color.BLACK));
        requestLabel.setPosition(10, 550);

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
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.LOGIN);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
    }

    private void update() {
        requestLabel.setText(httpService.getResponsStr());
        if(httpService.getResponsStr().equals(StatusEnum.SUCCES.toString())){
            ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY);
        }
    }
}
