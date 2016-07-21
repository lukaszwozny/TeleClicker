package com.mygdx.teleclicker.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.AssetsEnum;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 21.07.2016.
 */
public class Player extends Image {
    private final static int WIDTH = 180;
    private final static int HEIGHT = 200;

    private final static int STARTING_X = TeleClicker.WIDTH / 2 - WIDTH / 2;
    private final static int STARTING_Y = TeleClicker.HEIGHT / 2 - HEIGHT / 2 - 50;



    public Player(){
        super(getPlayerTexture());

        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);
    }

    private static Texture getPlayerTexture(){
        return Assets.getInstance().manager.get(AssetsEnum.PLAYER_TEX.toString());
    }

    public void reactOnClick(){
        int xMoveAmmount = MathUtils.random(-130, 130);
        int yMoveAmmount = 10;
        float moveActionTime = 0.30f;
        Action moveAction = Actions.sequence(
                Actions.moveBy(xMoveAmmount, yMoveAmmount, moveActionTime, Interpolation.circleOut),
                Actions.moveBy(-xMoveAmmount, -yMoveAmmount, moveActionTime, Interpolation.circle)
        );

        int xGrowAmmount = MathUtils.random(-30, 100);
        int yGrowAmmount = 20;
        float growActionTime = 0.2f;
        Action growAction = Actions.sequence(
                Actions.sizeBy(xGrowAmmount, yGrowAmmount, growActionTime, Interpolation.circleOut),
                Actions.sizeBy(-xGrowAmmount, -yGrowAmmount, growActionTime, Interpolation.circle)
        );

        this.addAction(moveAction);
        this.addAction(growAction);

        if (this.getHeight() > 170) {
            this.addAction(Actions.rotateBy(MathUtils.randomSign() * 360, 0.4f));
        }
    }

}
