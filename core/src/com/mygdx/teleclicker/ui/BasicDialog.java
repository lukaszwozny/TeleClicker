package com.mygdx.teleclicker.ui;

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

    public final static int WIDTH = 290;
    public final static int HEIGHT = 170;


    public BasicDialog(){
        super(new Texture("img/popups/dialog.png"));

        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        this.setPosition(40, 70);

        label = new GameLabel();
        label.setPosition(80,180);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Test popup.");
                fadeOutDialog();
                
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void initContent(String text){
        label.setText(text);
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
}
