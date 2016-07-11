package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Senpai on 10.07.2016.
 */
public class BasicDialog extends Image {

    private GameLabel label;

    public final static int WIDTH = 405;
    public final static int HEIGHT = 218;


    public BasicDialog() {
        this(GameLabel.FontType.TIMES_NEW_ROMAN);
    }

    public BasicDialog(GameLabel.FontType fontType){
        super(new Texture("img/popups/dialog.png"));

        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        this.setPosition(10, 70);

        label = new GameLabel(fontType);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fadeOutDialog();
                
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void initContent(String text){
        final int MIDDLE_Y = 200;
        label.setText(text);
        label.setPosition(45,MIDDLE_Y - label.getHeight()/2);

        this.getStage().addActor(label);
    }

    private void fadeOutDialog() {
        SequenceAction sequence = new SequenceAction();
        sequence.addAction(Actions.fadeOut(1.5f));
        sequence.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                label.remove();
                BasicDialog.this.remove();
                return false;
            }
        });
        this.addAction(sequence);
        label.addAction(Actions.fadeOut(1.5f));
    }

    public GameLabel getLabel(){
        return label;
    }

}
