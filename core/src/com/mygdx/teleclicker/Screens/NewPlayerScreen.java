package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
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
    private Label addPlayerResponseLabel;
    private Label addPlayerStatusLabel;

    private MyTextButton createButton;

    private HttpService httpService;

    private MyTextField loginTextField;
    private MyTextField emailTextField;
    private MyTextField passwordTextField;
    private boolean loginPut = false;
    private boolean emailPut = false;
    private boolean passwordPut = false;

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
    }

    private void initTextFields() {
        loginTextField = new MyTextField("login", skin);
        emailTextField = new MyTextField("e-mail", skin);
        passwordTextField = new MyTextField("password", skin);

        ArrayList<MyTextField> textFieldArrayList = new ArrayList<MyTextField>();
        textFieldArrayList.add(loginTextField);
        textFieldArrayList.add(emailTextField);
        textFieldArrayList.add(passwordTextField);

        setTextFieldsPossition(textFieldArrayList);
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

    private void setTextFieldsPossition(ArrayList<MyTextField> textFieldArrayList) {
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
