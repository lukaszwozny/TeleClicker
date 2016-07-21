package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.teleclicker.TeleClicker;

import java.util.concurrent.TimeUnit;

/**
 * Created by Senpai on 21.07.2016.
 */
public class ScoreService {
    public final static String GAME_SCORE = "com.mygdx.clicker.prefs.score";
    public final static String GAME_PASSIVE_INCOME = "com.mygdx.clicker.prefs.passive";
    public final static String GAME_SAVED_TIMESTAMP = "com.mygdx.clicker.prefs.timestamp";
    public final static String GAME_POINTS_PER_CLICK = "com.mygdx.clicker.prefs.pointsperclick";

    public final static String GAME_NO_POINTS_PER_CLICK_BUYS = "com.mygdx.clicker.prefs.pointsperclickbuys";
    public final static String GAME_NO_PASSIVE_POINTS_BUYS = "com.mygdx.clicker.prefs.passivepointsbuys";

    private static ScoreService instance;

    private float points;
    private float pointsPerSec;
    private float pointsPerClick = 1.0f;
    private float pointsToAdd;

    private int numberOfPointsPerClickPBuys = 2;
    private int numberOfPointsPerSecBuys;

    private Preferences prefs;

    private ScoreService() {
        this.prefs = TeleClicker.getPrefs();
        loadScore();
        init();
    }

    public static ScoreService getInstance() {
        if(instance == null){
            instance = new ScoreService();
        }
        return instance;
    }

    public void init() {
    }

    private void calculateGainedPassiveIncome() {
        if (getSavedTimeStamp() > 0) {
            final float multiplier = 0.1f;
            long deleyTime = TimeUtils.millis() - getSavedTimeStamp();

            pointsToAdd = TimeUnit.MILLISECONDS.toSeconds(deleyTime) * multiplier * pointsPerSec;
            points += pointsToAdd;
        }
    }

    public void addPoints(float pointsToAdd) {
        points += pointsToAdd;
        if (points < 0)
            points = 0;
    }

    public void addPoint() {
        points += pointsPerClick;
    }

    public void addPointsPerClick(int add) {
        pointsPerClick += add;
    }

    public void resetGameScore() {
        points = 0.0f;
        pointsPerSec = 0.0f;
        pointsPerClick = 1.0f;

        numberOfPointsPerClickPBuys = 0;
        numberOfPointsPerSecBuys = 0;
    }

    public void addPointsPerSec(int value) {
        pointsPerSec += value;
        if (pointsPerSec < 0)
            pointsPerSec = 0;
    }

    public void increseNumberOfPointsPerClickBuys() {
        System.out.println("Points per click++");
        numberOfPointsPerClickPBuys++;
    }

    public void increseNumberOfPointsPerSecBuys() {
        numberOfPointsPerSecBuys++;
    }

    private void loadScore() {
        pointsPerSec = prefs.getFloat(GAME_PASSIVE_INCOME);
        points = prefs.getFloat(GAME_SCORE);
        pointsPerClick = prefs.getFloat(GAME_POINTS_PER_CLICK);

        numberOfPointsPerClickPBuys = prefs.getInteger(GAME_NO_POINTS_PER_CLICK_BUYS);
        numberOfPointsPerSecBuys = prefs.getInteger(GAME_NO_PASSIVE_POINTS_BUYS);
    }

    public void saveCurrentGameState() {
        prefs.putFloat(GAME_SCORE, points);
        prefs.putFloat(GAME_PASSIVE_INCOME, pointsPerSec);
        prefs.putFloat(GAME_POINTS_PER_CLICK, pointsPerClick);
        prefs.putLong(GAME_SAVED_TIMESTAMP, TimeUtils.millis());

        // Shop values
        prefs.putInteger(GAME_NO_POINTS_PER_CLICK_BUYS, numberOfPointsPerClickPBuys);
        prefs.putInteger(GAME_NO_PASSIVE_POINTS_BUYS, numberOfPointsPerSecBuys);

        prefs.flush();
    }

    /**
     * ---------------------
     * getters and setters
     */

    private Long getSavedTimeStamp() {
        return prefs.getLong(GAME_SAVED_TIMESTAMP);
    }

    public float getPoints() {
        return points;
    }

    public float getPointsPerSec(){
        return pointsPerSec;
    }

    public float getPointsPerClick(){
        return pointsPerClick;
    }

    public int getNumberOfPointsPerClickBuys() {
        return numberOfPointsPerClickPBuys;
    }

    public int getNumberOfPointsPerSecBuys() {
        return numberOfPointsPerSecBuys;
    }

    public float getPointsToAdd() {
        return pointsToAdd;
    }

    public void multiplierPoints(float multiplier) {
        points *= multiplier;
    }
}
