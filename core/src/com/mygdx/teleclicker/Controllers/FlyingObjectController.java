package com.mygdx.teleclicker.Controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Entities.FlyingObject;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 10.07.2016.
 */
public class FlyingObjectController {
    private int spawnTime;

    public FlyingObjectController(TeleClicker game, Stage stage){
        init(game, stage);
    }

    private void init(final TeleClicker game, final Stage stage) {
        randomizeSpawnTime();

        Timer.schedule(new Timer.Task() {

            @Override
            public void run() {

                Timer.schedule(new Timer.Task() {

                    @Override
                    public void run() {

                        addRandomFlyingObjectToStage(game, stage);
                        randomizeSpawnTime();

                    }
                }, spawnTime);
            }
        }, 0, 5);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5, 10);
    }

    private void addRandomFlyingObjectToStage(TeleClicker game, Stage stage){
        FlyingObject flyingObject = null;

        if(MathUtils.randomBoolean()){
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.MONEY, game);
        } else{
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.PASSIVE, game);
        }

        stage.addActor(flyingObject);
        flyingObject.flyLikeHell();
    }
}
