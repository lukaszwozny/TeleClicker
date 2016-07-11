package com.mygdx.teleclicker.Controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.BasicDialog;

/**
 * Created by Senpai on 11.07.2016.
 */
public class RandomEventsController {

    private static final int RANDOM_TICK_INTERVAL = 5; //TODO Change spawnEvent Time
    private int spawnTime;
    private Stage stage;
    private TeleClicker game;

    public RandomEventsController(TeleClicker game, Stage stage) {
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
                        triggerRandomEvent();
                        randomizeSpawnTime();
                    }
                }, spawnTime);
            }
        }, 0, RANDOM_TICK_INTERVAL);
    }

    private void triggerRandomEvent() {
        int randEvent = MathUtils.random(5);
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
            case 4:
                break;
        }
    }

    private void moneyMultiplierDownEvent() {
        BasicDialog basicDialog = new BasicDialog();
        stage.addActor(basicDialog);
        float randMultiplier = MathUtils.random(1, 10);
        System.out.println("Randmulti :" + randMultiplier);
        basicDialog.initContent("Przewalutowales swoje\n" +
                "Erlangi na Franki.\n" +
                "Tracisz na tym "+randMultiplier+"%.");
        randMultiplier = 1 - randMultiplier/100;
        game.getScoreService().multiplierPoints(randMultiplier);
    }

    private void moneyUpEvent() {
        BasicDialog basicDialog = new BasicDialog();
        stage.addActor(basicDialog);
        int randMoney = MathUtils.random(1, 3);
        basicDialog.initContent("Zmieniasz taryfe na\n" +
                "Telgam - standard " + randMoney*12 +"mc.\n"+
                "Zyskujesz " + randMoney*100 + " Erlangow.");
        game.getScoreService().addPoints(randMoney*100);
    }

    private void moneyDownEvent() {
        BasicDialog basicDialog = new BasicDialog();
        stage.addActor(basicDialog);
        int randMoney = MathUtils.random(1, 10) * 100;
        basicDialog.initContent("Probujesz dodzwonic sie\n" +
                "na niebieska linie,\n" +
                "niestety, nikt nie odbiera.\n" +
                "Tracisz " + randMoney + " Erlangow.");
        game.getScoreService().addPoints(-randMoney);
    }

    private void passiveUpEvent() {
        BasicDialog basicDialog = new BasicDialog();
        stage.addActor(basicDialog);
        int randPassive = MathUtils.random(1, 10) * 10;
        basicDialog.initContent("Bawiac sie w piaskownicy\n" +
                "odkrywasz zrodlo natezenia\n" +
                "ruchu.\n" +
                "Zyskujesz " + randPassive + " Erl/sec.");
        game.getScoreService().addPassiveIncome(randPassive);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5, 10);
    }
}
