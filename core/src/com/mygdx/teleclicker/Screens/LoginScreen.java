package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Entities.PlayerStats;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.DBStatusEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.*;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.CloseSettingsButton;
import com.mygdx.teleclicker.ui.IClickCallback;
import com.mygdx.teleclicker.ui.ResetScoreButton;

/**
 * Created by Senpai on 01/08/2016.
 */
public class LoginScreen extends AbstractScreen {
    private CloseSettingsButton closeButton;
    private Label responseLabel;
    private Label statusLabel;
    private ResetScoreButton loginButton;
    private ResetScoreButton newPlayerButton;
    private ResetScoreButton loadStatsButton;

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

        initLabels();

        initCloseButton();
        initLoginButton();
        initNewPlayerButton();
        initLoadStatsButton();

        initPlayerStats();
    }

    private void initLoadStatsButton() {
        loadStatsButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                ScoreService.getInstance().loadPlayerStatsFromServer("21");
            }
        });
        loadStatsButton.setSize(60, 60);
        final float X = TeleClicker.WIDTH - loginButton.getWidth();
        final int Y = 10;
        loadStatsButton.setPosition(X, Y);

        addActor(loadStatsButton);
    }

    private void initPlayerStats() {
        playerStats = new PlayerStats();
        playerStats.setId(21);
        playerStats.setPoints(11);
        playerStats.setPointsPerSec(2);
        playerStats.setPointsPerClick(4);
        playerStats.setNumberOfClicks(13);
        playerStats.setNumberOfPointsPerClickPBuys(2);
        playerStats.setNumberOfPointsPerSecBuys(11);
        playerStats.setPassiveIncomeTimeInHour(0.5f);
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

    private void initLoginButton() {
        loginButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                httpService.loginRequest(
                        loginTextField.getText(),
                        passwordTextField.getText()
                );
                final Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        responseLabel.setText("Response: " + httpService.getStatus().toString());

                        if (httpService.getStatus() == DBStatusEnum.SUCCES) {
                            statusLabel.setText("Status: " + httpService.getResponsStr());
                            timer.clear();
                        }
                    }
                }, 0, 1);
            }
        });
        final float X = TeleClicker.WIDTH / 2 - loginButton.getWidth() / 2;
        final int Y = 10;
        loginButton.setPosition(X, Y);

        addActor(loginButton);
    }

    private void initLabels() {
        responseLabel = new Label("Response", new Label.LabelStyle(FontService.getFont(), Color.BLACK));
        responseLabel.setPosition(10, 550);

        statusLabel = new Label("Status", new Label.LabelStyle(FontService.getFont(), Color.BLACK));
        statusLabel.setPosition(10, 500);

        addActor(responseLabel);
        addActor(statusLabel);
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
        if (httpService.getStatus() == DBStatusEnum.SUCCES) {
            //ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY);
        }
    }
}
