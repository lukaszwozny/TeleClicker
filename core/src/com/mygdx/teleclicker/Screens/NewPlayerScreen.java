package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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

import java.util.ArrayList;

/**
 * Created by Senpai on 01/08/2016.
 */
public class NewPlayerScreen extends AbstractScreen {
    private MyLabel statusLabel;

    private MyTextButton createButton;

    private HttpService httpService;

    private MyTextField loginTextField;
    private MyTextField emailTextField;
    private MyTextField passwordTextField;

    private Skin skin;

    @Override
    public void buildStage() {
        initBgTexture();
        initSkin();
        initTextFields();

        initButtons();
        initHttpService();

        initLabels();
    }

    private void initButtons() {
        initCreateButton();

        final float BUTTON_WIDTH = 180.0f;
        final float BUTTON_HEIGHT = 50.0f;

        createButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        final float X = TeleClicker.WIDTH / 2 - BUTTON_WIDTH / 2;
        final float Y = 270.0f;
        final float INTERVAL = BUTTON_HEIGHT + 10;

        createButton.setPosition(X, Y);

        addActor(createButton);
    }

    private void initCreateButton() {
        createButton = new RedTextButton("Create", new IClickCallback() {
            @Override
            public void onClick() {
                // Play sound
                SoundService.getInstance().playClickSound();
                // DB Request
                httpService.addPlayerRequest(
                        loginTextField.getText(),
                        emailTextField.getText(),
                        passwordTextField.getText()
                );
                //Init timer to check connection status each sec.
                final Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        DBStatusEnum status = httpService.getStatus();
                        switch (status){
                            case CONNECTING:
                                statusLabel.setText("Connecting to server.");
                                statusLabel.setColor(Color.BLACK);
                                break;
                            case SUCCES:
                                updateStatusLabel(httpService.getResponsStr());
                                timer.clear();
                                break;
                            case AUTHORIZATION_FAILED:
                                statusLabel.setText("Authorization failed.");
                                statusLabel.setColor(Color.RED);
                                timer.clear();
                                break;
                            default:
                                statusLabel.setText("We're sorry. Unrecognized error.");
                                statusLabel.setColor(Color.RED);
                                timer.clear();
                                break;
                        }
                    }
                }, 0, 1);
            }
        });
    }

    private void updateStatusLabel(String responsStr) {
        Json json = new Json();
        PlayerStats playerStats = json.fromJson(PlayerStats.class, responsStr);
        DBStatusEnum statusEnum = DBStatusEnum.valueOf(playerStats.getStatus());

        System.out.println(statusEnum.toString());

        String statusString = "";
        switch (statusEnum) {
            case AUTHORIZATION_FAILED:
                statusString = "Authorization error.";
                statusLabel.setColor(Color.RED);
                break;
            case PLAYER_ADDED:
                statusString = "Player successfully added.";
                statusLabel.setColor(Color.GREEN);
                break;
            case FAILED:
                statusString = "we're sorry. Connection ends with 'fail error'.\n" +
                        "Try again later.";
                statusLabel.setColor(Color.RED);
                break;
            case CANCELLED:
                statusString = "Connection was cancelled. Try again.";
                statusLabel.setColor(Color.RED);
                break;
            case PLAYER_ALREADY_EXIST:
                statusString = "Player with this login already exists.";
                statusLabel.setColor(Color.RED);
                break;
            case LOGIN_IS_NULL:
                statusString = "Login is required.";
                statusLabel.setColor(Color.RED);
                break;
            case EMAIL_IS_NULL:
                statusString = "E-mail is required.";
                statusLabel.setColor(Color.RED);
                break;
            case PASSWORD_IS_NULL:
                statusString = "Password is required.";
                statusLabel.setColor(Color.RED);
                break;
            default:
                statusString = "We're sorry. Unrecognized error.";
                statusLabel.setColor(Color.RED);
                break;
        }

        statusLabel.setText(statusString);
    }

    private void initTextFields() {
        loginTextField = new MyTextField("login", skin);
        emailTextField = new MyTextField("e-mail", skin);
        passwordTextField = new MyTextField("password", skin);

        ArrayList<MyTextField> textFieldArrayList = new ArrayList<MyTextField>();
        textFieldArrayList.add(loginTextField);
        textFieldArrayList.add(emailTextField);
        textFieldArrayList.add(passwordTextField);

        setTextFieldsPosition(textFieldArrayList);
        initTextFieldsListeners(textFieldArrayList);

        passwordTextField.setPasswordCharacter('*');
        passwordTextField.setPasswordMode(true);

        addTextFieldToStage(textFieldArrayList);
    }

    private void addTextFieldToStage(ArrayList<MyTextField> textFieldArrayList) {
        for(MyTextField f : textFieldArrayList){
            addActor(f);
        }
    }

    private void setTextFieldsPosition(ArrayList<MyTextField> textFieldArrayList) {
        final float X = TeleClicker.WIDTH / 2 - loginTextField.getWidth() / 2;
        final float START_Y = 450f;
        final float INTERVAL = loginTextField.getHeight() + 20;

        int counter = 0;
        for(MyTextField f : textFieldArrayList){
            f.setPosition(X,START_Y - counter * INTERVAL);
            counter++;
        }
    }

    private void initTextFieldsListeners(ArrayList<MyTextField> textFieldArrayList) {
        for (final MyTextField f : textFieldArrayList) {
            f.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (!f.isClicked()) {
                        f.setText("");
                        f.setClicked(true);
                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
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
