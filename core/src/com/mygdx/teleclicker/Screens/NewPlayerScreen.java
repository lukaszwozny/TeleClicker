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
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.DBStatusEnum;
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
public class NewPlayerScreen extends AbstractScreen {
    private CloseSettingsButton closeButton;
    private Label addPlayerResponseLabel;
    private Label addPlayerStatusLabel;
    private ResetScoreButton addPlayerButton;

    private HttpService httpService;

    private TextField loginTextField;
    private TextField emailTextField;
    private TextField passwordTextField;

    private Skin skin;

    @Override
    public void buildStage() {
        initBgTexture();
        initSkin();
        initTextFields();

        initHttpService();

        initAddPlayerButton();
        initCloseButton();

        initLabels();
    }

    private void initTextFields() {
        loginTextField = new TextField("Login", skin);
        emailTextField = new TextField("E-mail", skin);
        passwordTextField = new TextField("Password", skin);

        final float X = TeleClicker.WIDTH / 2 - loginTextField.getWidth() / 2;
        final float START_Y = 450f;
        final float INTERVAL = loginTextField.getHeight() + 20;

        loginTextField.setPosition(X, START_Y);
        emailTextField.setPosition(X, START_Y - INTERVAL);
        passwordTextField.setPosition(X, START_Y - 2 * INTERVAL);

        addActor(loginTextField);
        addActor(emailTextField);
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
                ScreenService.getInstance().setScreen(ScreenEnum.LOGIN);
            }
        });
        addActor(closeButton);
    }

    private void initAddPlayerButton() {
        addPlayerButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                httpService.addPlayerRequest(
                        loginTextField.getText(),
                        emailTextField.getText(),
                        passwordTextField.getText()
                );
                final Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        addPlayerStatusLabel.setText("Status: " + httpService.getStatus().toString());
                        if (httpService.getStatus() == DBStatusEnum.SUCCES) {
                            addPlayerResponseLabel.setText("Response:" + httpService.getResponsStr());
                            timer.clear();
                        }
                    }
                }, 0, 1);
            }
        });
        final float X = TeleClicker.WIDTH / 2 - addPlayerButton.getWidth() / 2;
        final int Y = 10;
        addPlayerButton.setPosition(X, Y);

        addActor(addPlayerButton);
    }

    private void initLabels() {

        Label titleLabel = new Label("New Player Screen", new Label.LabelStyle(FontService.getFont(), Color.BLACK));
        titleLabel.setPosition(10, 600);

        addPlayerResponseLabel = new Label("Response: ", new Label.LabelStyle(FontService.getFont(), Color.BLACK));
        addPlayerResponseLabel.setPosition(10, 550);

        addPlayerStatusLabel = new Label("Status: ", new Label.LabelStyle(FontService.getFont(), Color.BLACK));
        addPlayerStatusLabel.setPosition(10, 500);

        addActor(titleLabel);
        addActor(addPlayerResponseLabel);
        addActor(addPlayerStatusLabel);
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
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.NEW_PLAYER);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
    }

    private void update() {
    }
}
