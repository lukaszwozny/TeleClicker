package com.mygdx.teleclicker.Controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Entities.FlyingObject;
import com.mygdx.teleclicker.Enums.FlyingObjectTypeEnum;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScreenService;

/**
 * Created by Senpai on 22.07.2016.
 */
public class FlyingObjectController {
    private static final int RANDOM_TICK_INTERVAL = 5;
    private int spawnTime;
    private final int MIN_SPAWN_TIME_INTERVAL = 0;
    private final int MAX_SPAWN_TIME_INTERVAL = 10;

    private Timer timer;

    private AbstractScreen screen;

    public FlyingObjectController(AbstractScreen screen) {
        this.screen = screen;
        initTimer();
    }

    private void initTimer() {
        timer = new Timer();
        randomizeSpawnTime();

        timer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                System.out.println("WORK");
                boolean rand = MathUtils.randomBoolean();
                rand = true;
                if (rand) {
                    {
                        timer.scheduleTask(new Timer.Task() {
                            @Override
                            public void run() {
                                addRandomFlyingObjectToStage();
                                randomizeSpawnTime();
                            }
                        }, spawnTime);
                    }
                }
            }
        }, 0, RANDOM_TICK_INTERVAL);
    }

    public void clearTimer(){
        timer.clear();
    }

    private void addRandomFlyingObjectToStage() {
        FlyingObject flyingObject = null;

        switch (MathUtils.random(0, 3)) {
            case 0:
                flyingObject = new FlyingObject(FlyingObjectTypeEnum.MONEY);
                break;
            case 1:
                flyingObject = new FlyingObject(FlyingObjectTypeEnum.MONEY_DOWN);
                break;
            case 2:
                flyingObject = new FlyingObject(FlyingObjectTypeEnum.PASSIVE);
                break;
            case 3:
                flyingObject = new FlyingObject(FlyingObjectTypeEnum.PASSIVE_DOWN);
                break;
        }

        screen.addActor(flyingObject);
        flyingObject.Fly();
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(MIN_SPAWN_TIME_INTERVAL, MAX_SPAWN_TIME_INTERVAL);
    }
}
