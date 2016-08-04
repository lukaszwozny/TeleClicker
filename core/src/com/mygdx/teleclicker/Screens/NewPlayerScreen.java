package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
public class NewPlayerScreen extends AbstractScreen {
    private CloseSettingsButton closeButton;
    private Label requestLabel;
    private ResetScoreButton requestButton;

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
        initCloseButton();
        initRequestLabel();
        initRequestButton();
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

    private void initRequestButton() {
        requestButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                httpService.addPlayerRequest(
                        loginTextField.getText(),
                        emailTextField.getText(),
                        passwordTextField.getText()
                );
            }
        });
        final float X = TeleClicker.WIDTH / 2 - requestButton.getWidth() / 2;
        final int Y = 10;
        requestButton.setPosition(X, Y);

        addActor(requestButton);
    }

    private void initRequestLabel() {

        Label titleLabel = new Label("New Player Screen", new Label.LabelStyle(FontService.getFont(), Color.BLACK));
        titleLabel.setPosition(10, 600);

        requestLabel = new Label("Test", new Label.LabelStyle(FontService.getFont(), Color.BLACK));
        requestLabel.setPosition(10, 550);

        addActor(titleLabel);
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
        ScreenService.getInstance().setActualScreenEnum(ScreenEnum.NEW_PLAYER);
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