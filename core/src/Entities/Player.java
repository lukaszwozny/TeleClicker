package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 10.07.2016.
 */
public class Player extends Image{
    private final static int WIDTH = 277;
    private final static int HEIGHT = 300;

    public Player() {
        super(new Texture("img/penguin_cinema.png"));

        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        this.setPosition(TeleClicker.WIDTH/2 - WIDTH/2, 50);
    }

    public void reactOnClick() {
        int moveAmountX = MathUtils.random(-130, 130);
        int moveAmountY = MathUtils.random(-130, 130);
        Action moveAction = Actions.sequence(
                Actions.moveBy(moveAmountX, moveAmountY, 0.30f, Interpolation.circleOut),
                Actions.moveBy(-moveAmountX, -moveAmountY, 0.30f, Interpolation.circle)
        );

        float growAmountX = MathUtils.random(-30, 100);
        float growAmountY = MathUtils.random(-40, 60);
        Action growAction = Actions.sequence(
                Actions.sizeBy(growAmountX, growAmountY, 0.3f, Interpolation.bounce),
                Actions.sizeBy(-growAmountX, -growAmountY, 0.3f, Interpolation.elastic)
        );

        this.addAction(moveAction);
        this.addAction(growAction);

        if (this.getHeight() > 170) {
            this.addAction(Actions.rotateBy(MathUtils.randomSign() * 360.0f, 0.3f));
        }
        if (this.getHeight() > 200) {
            this.addAction(Actions.sequence(
                    Actions.rotateBy(MathUtils.randomSign() * 360.0f, 0.3f, Interpolation.elasticIn),
                    Actions.rotateBy(MathUtils.randomSign() * 360.0f, 0.2f, Interpolation.pow4)
            ) );
        }
    }
}
