package com.mygdx.teleclicker.Controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Senpai on 11.07.2016.
 */
public class RandomEventsController {

    private static final int RANDOM_TICK_INTERVAL = 5;
    private int spawnTime;

    public RandomEventsController() {
        init();
    }

    private void init() {
        randomizeSpawnTime();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        System.out.println("Spawn time " + spawnTime);
                        randomizeSpawnTime();
                    }
                }, spawnTime);
            }
        }, 0, RANDOM_TICK_INTERVAL);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5, 10);
    }
}
