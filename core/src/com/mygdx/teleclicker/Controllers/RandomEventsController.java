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
        int randEvent = MathUtils.random(4);
        switch (randEvent) {
            case 0:
                break;
            case 1:
                moneyDownEvent();
                break;
            case 2:
                passiveUpEvent();
                break;
            case 3:
                break;
        }
    }

    private void passiveUpEvent() {
        BasicDialog passiveUpDialog = new BasicDialog();
        stage.addActor(passiveUpDialog);
        int randPassive = MathUtils.random(1, 10) * 10;
        passiveUpDialog.initContent("Bawiac sie w piaskownicy\n" +
                "odkrywasz zrodlo natezenia\n" +
                "ruchu.\n" +
                "Zyskujesz " + randPassive + " Erl/sec.");
        game.getScoreService().addPassiveIncome(randPassive);
    }

    private void moneyDownEvent() {
        BasicDialog moneyUpDialog = new BasicDialog();
        stage.addActor(moneyUpDialog);
        int randUpMoney = MathUtils.random(1, 10) * 100;
        moneyUpDialog.initContent("Probujesz dodzwonic sie\n" +
                "na niebieska linie,\n" +
                "niestety, nikt nie odbiera.\n" +
                "Tracisz " + randUpMoney + " Erlangow.");
        game.getScoreService().addPoints(-randUpMoney);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5, 10);
    }
}
