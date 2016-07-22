package com.mygdx.teleclicker.Controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.BasicDialogold;

/**
 * Created by Senpai on 11.07.2016.
 */
public class RandomEventsController_old {

    private static final int TICK_INTERVAL = 5;
    private int spawnTime;
    private Stage stage;
    private TeleClicker game;

    public RandomEventsController_old(TeleClicker game, Stage stage) {
        this.game = game;
        this.stage = stage;
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
                        System.out.println("Random event time! Can't touch this! ");
                        triggerRandomEvent();
                        randomizeSpawnTime();
                    }
                }, 5);
            }
        }, 1, TICK_INTERVAL);
    }

    private void triggerRandomEvent() {
        int randEvent = MathUtils.random(4);
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

    private void moneyMultiplierDownEvent() {
        BasicDialogold basicDialog = new BasicDialogold();
        stage.addActor(basicDialog);
        float randMultiplier = MathUtils.random(1, 10);
        basicDialog.initContent("Przewalutowales swoje\n" +
                "Erlangi na Franki.\n" +
                "Tracisz na tym "+randMultiplier+"%.");
        randMultiplier = 1 - randMultiplier/100;
        game.getScoreService().multiplierPoints(randMultiplier);
    }

    private void moneyUpEvent() {
        BasicDialogold basicDialog = new BasicDialogold();
        stage.addActor(basicDialog);
        int randMoney = MathUtils.random(1, 3);
        basicDialog.initContent("Zmieniasz taryfe na\n" +
                "Telgam - standard " + randMoney*12 +"mc.\n"+
                "Zyskujesz " + randMoney*100 + " Erlangow.");
        game.getScoreService().addPoints(randMoney*100);
    }

    private void moneyDownEvent() {
        BasicDialogold basicDialog = new BasicDialogold();
        stage.addActor(basicDialog);
        int randMoney = MathUtils.random(1, 10) * 100;
        basicDialog.initContent("Probujesz dodzwonic sie\n" +
                "na niebieska linie,\n" +
                "niestety, nikt nie odbiera.\n" +
                "Tracisz " + randMoney + " Erlangow.");
        game.getScoreService().addPoints(-randMoney);
    }

    private void passiveUpEvent() {
        BasicDialogold basicDialog = new BasicDialogold();
        stage.addActor(basicDialog);
        int randPassive = MathUtils.random(1, 10) * 10;
        basicDialog.initContent("Bawiac sie w piaskownicy\n" +
                "odkrywasz zrodlo \n" +
                "natezenia ruchu.\n" +
                "Zyskujesz " + randPassive + " Erl/sec.");
        game.getScoreService().addPassiveIncome(randPassive);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(30, 60);
    }
}
