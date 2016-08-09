package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Entities.PlayerStats;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.Enums.DBStatusEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.*;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.*;

/**
 * Created by Senpai on 01/08/2016.
 */
public class LoginScreen extends AbstractScreen {
    private MyLabel statusLabel;

    private MyTextButton loginButton;
    private MyTextButton newPlayerButton;
    private MyTextButton quitButton;

    private HttpService httpService;

    private TextField loginTextField;
    private TextField passwordTextField;

    private CheckBox rememberCheckbox;

    private Skin skin;

    private PlayerStats playerStats;

    public LoginScreen(){
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode){
            case Input.Keys.BACK:
                Gdx.app.exit();
                break;
        }
        return false;
    }

    @Override
    public void buildStage() {
        initBgTexture();
        initSkin();

        initHttpService();

        initRememberCheckbox();
        initTextFields();
        initLabels();
        initButtons();
    }

    private void initButtons() {
        initLoginButton();
        initNewPlayerButton();
        initQuitButton();

        final float BUTTON_WIDTH = 180.0f;
        final float BUTTON_HEIGHT = 50.0f;

        loginButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        newPlayerButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);


        final float X = TeleClicker.WIDTH / 2 - BUTTON_WIDTH / 2;
        final float Y = 300.0f;
        final float INTERVAL = BUTTON_HEIGHT + 10;

        loginButton.setPosition(X, Y);
        newPlayerButton.setPosition(X, Y - INTERVAL);
        quitButton.setPosition(X, Y - 2 * INTERVAL);

        addActor(loginButton);
        addActor(newPlayerButton);
        addActor(quitButton);
    }

    private void initQuitButton() {
        quitButton = new RedTextButton("Quit", new IClickCallback() {
            @Override
            public void onClick() {
                Gdx.app.exit();
            }
        });
    }

    private void initNewPlayerButton() {
        newPlayerButton = new RedTextButton("Create new account", new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();
                ScreenService.getInstance().setScreen(ScreenEnum.NEW_PLAYER);
            }
        });
    }

    private void initRememberCheckbox() {
        rememberCheckbox = new CheckBox(" Remember me", skin);

        rememberCheckbox.setChecked(SettingsService.getInstance().isRemember());
        rememberCheckbox.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SoundService.getInstance().playClickSound();
                if (rememberCheckbox.isChecked()) {
                    //Turned off
                    SettingsService.getInstance().setRemember(false);
                } else {
                    // Turned on
                    SettingsService.getInstance().setRemember(true);
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        addActor(rememberCheckbox);
    }

    private void initLoginButton() {
        loginButton = new RedTextButton("Connect", new IClickCallback() {
            @Override
            public void onClick() {
                SoundService.getInstance().playClickSound();

                String login = loginTextField.getText();
                String password = passwordTextField.getText();

                ScoreService.getInstance().setLastLogin(login);
                ScoreService.getInstance().setLastPassword(password);

                ScoreService.getInstance().loadScore(login, password);
            }
        });
    }

    private void initTextFields() {
        final String loginText, passwordText;
        if (SettingsService.getInstance().isRemember()) {
            loginText = ScoreService.getInstance().getLastLogin();
            passwordText = ScoreService.getInstance().getLastPassword();
        } else {
            loginText = "login";
            passwordText = "password";
        }

        loginTextField = new TextField(loginText, skin);
        passwordTextField = new TextField(passwordText, skin);
        passwordTextField.setPasswordCharacter('*');

        if (SettingsService.getInstance().isRemember()) {
            passwordTextField.setPasswordMode(true);
        }

        loginTextField.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!SettingsService.getInstance().isRemember()) {
                    loginTextField.setText("");
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        passwordTextField.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!SettingsService.getInstance().isRemember()) {
                    passwordTextField.setText("");
                }
                passwordTextField.setPasswordMode(true);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        final float X = TeleClicker.WIDTH / 2 - loginTextField.getWidth() / 2;
        final float START_Y = 450f;
        final float INTERVAL = loginTextField.getHeight() + 20;

        loginTextField.setPosition(X, START_Y);
        passwordTextField.setPosition(X, START_Y - INTERVAL);
        //rememberChceckbox position
        rememberCheckbox.setPosition(X, START_Y - INTERVAL - passwordTextField.getHeight());

        addActor(loginTextField);
        addActor(passwordTextField);
    }

    private void initSkin() {
        skin = new Skin(Gdx.files.internal("libgdx/uiskin.json"));
    }

    private void initHttpService() {
        httpService = new HttpService();
    }

    private void initLabels() {
        statusLabel = new MyLabel("");
        statusLabel.setPosition(10, 500);

        addActor(statusLabel);
    }

    @Override
    public void initBgTexture() {
        // ToDo Change background
        bgTexture = new Texture(Gdx.files.internal("img/bg/purple_bg.png"));
        addActor(new Image(bgTexture));

        LoginBackground loginBackground = new LoginBackground();

        addActor(loginBackground);
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
        DBStatusEnum status = ScoreService.getInstance().getLoginStatus();
        if(status != null){
            statusLabel.setText(status.toString());
            statusLabel.setColor(status.getMessageColor());
        }

        if (ScoreService.getInstance().isLoaded()) {
            ScreenService.getInstance().setScreen(ScreenEnum.GAMEPLAY);
        }
    }
}
