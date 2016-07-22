package com.mygdx.teleclicker.Controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.BasicDialogold;
import com.mygdx.teleclicker.ui.EventDialog;

/**
 * Created by Senpai on 22.07.2016.
 */
public class RandomEventsController {

    private static RandomEventsController instance;

    private static final int TICK_INTERVAL = 30;
    private int spawnTime;

    private AbstractScreen screen;

    public RandomEventsController() {
        initEventController();
    }

    public static RandomEventsController getInstance() {
        if(instance == null){
            instance = new RandomEventsController();
        }
        return instance;
    }

    public void Initialize(AbstractScreen screen){
        this.screen = screen;
    }

    private void initEventController() {
        randomizeSpawnTime();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        System.out.println("New event!");
                        triggerRandomEvent();
                        randomizeSpawnTime();
                    }
                }, spawnTime);
            }
        }, 1, TICK_INTERVAL);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(10, 60);
        System.out.println("SpawnTime: "+spawnTime);
    }

    private void triggerRandomEvent() {
        int randEvent = MathUtils.random(0,3);
        switch (randEvent) {
            case 0:
                moneyMultiplierDownEvent();
                break;
            case 1:
                moneyUpEvent();
                break;
            case 2:
                moneyDownEvent();
                break;
            case 3:
                passiveUpEvent();
                break;
//            case 4:
//                break;
        }
    }

    private void passiveUpEvent() {
        int rand = MathUtils.random(1, 3);
        String content = "Bawiac sie w piaskownicy\n" +
                "odkrywasz zrodlo \n" +
                "natezenia ruchu.\n" +
                "Zyskujesz " + rand + " Erl/sec.";

        EventDialog eventDialog = new EventDialog(content);

        screen.addActor(eventDialog);
        ScoreService.getInstance().addPointsPerSec(-rand);

    }

    private void moneyDownEvent() {
        int rand = MathUtils.random(1, 3) * 100;
        String content = "Probujesz dodzwonic sie\n" +
                "na niebieska linie,\n" +
                "niestety, nikt nie odbiera.\n" +
                "Tracisz " + rand + " Erlangow.";

        EventDialog eventDialog = new EventDialog(content);

        screen.addActor(eventDialog);
        ScoreService.getInstance().addPoints(-rand);
    }

    private void moneyUpEvent() {
        int rand = MathUtils.random(1, 3) * 10;
        String content = "Zmieniasz taryfe na\n" +
                "Telgam - standard " + rand*12 +"mc.\n"+
                "Zyskujesz " + rand + " Erlangow.";

        EventDialog eventDialog = new EventDialog(content);

        screen.addActor(eventDialog);
        ScoreService.getInstance().addPoints(rand);
    }

    private void moneyMultiplierDownEvent() {
        float rand = MathUtils.random(1, 5);
        float randMultiplier = 1 - rand/100;
        String content = "Przewalutowales swoje\n" +
                "Erlangi na Franki.\n" +
                "Tracisz na tym "+rand+"%.";

        EventDialog eventDialog = new EventDialog(content);

        screen.addActor(eventDialog);
        ScoreService.getInstance().multiplierPoints(randMultiplier);
    }
}
