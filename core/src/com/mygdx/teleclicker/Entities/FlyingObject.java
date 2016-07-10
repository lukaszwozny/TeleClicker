package com.mygdx.teleclicker.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 10.07.2016.
 */
public class FlyingObject extends Image{
    public enum FlyingObjectType{
        MONEY, MONEY_DOWN, PASSIVE, PASSIVE_DOWN
    }

    public final static String MONEY = "img/skins/flying_object/cash_1.png";
    public final static String PASSIVE = "img/skins/flying_object/diamond_1.png";
    public final static String MONEY_DOWN = "img/skins/flying_object/bomb_1.png";
    public final static String PASSIVE_DOWN = "img/skins/flying_object/jew_greedy.png";

    private final static int WIDHT = 150;
    private final static int HEIGHT = 150;

    private final static int STARTING_X = 0;
    private final static int STARTING_Y = -100;

    private TeleClicker game;
    private FlyingObjectType type;

    public FlyingObject(FlyingObjectType type, TeleClicker game){
        super(new Texture(getTextureString(type)));

        this.type = type;
        this.game = game;

        this.setOrigin(WIDHT / 2, HEIGHT / 2);
        this.setSize(WIDHT, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                reactOnClick();
                return super.touchDown(event, x, y, pointer, button);
            }


        });
    }

    private void reactOnClick() {
        switch (type) {
            case MONEY:
                game.addPoints(50);
                break;
            case MONEY_DOWN:
                game.addPoints(-50);
                break;
            case PASSIVE:
                game.addPassiveIncome(5);
                break;
            case PASSIVE_DOWN:
                game.addPassiveIncome(-5);
                break;
        }
        FlyingObject.this.remove();
    }

    private static String getTextureString(FlyingObjectType type) {
        switch (type) {
            case MONEY:
                return MONEY;
            case MONEY_DOWN:
                return MONEY_DOWN;
            case PASSIVE:
                return PASSIVE;
            case PASSIVE_DOWN:
                return PASSIVE_DOWN;
        }
        return "";
    }

    public void flyLikeHell(){

        Action a = Actions.parallel(
                Actions.moveBy(300, 200, 5),
                Actions.rotateBy(360, 5)
        );

        Action b = Actions.parallel(
                Actions.moveBy(-500, 900, 3),
                Actions.rotateBy(360, 3)
        );

        Action c = Actions.run(new Runnable() {

            @Override
            public void run() {
                FlyingObject.this.remove();
            }
        });


        this.addAction(Actions.sequence(a, b, c));
    }
}
