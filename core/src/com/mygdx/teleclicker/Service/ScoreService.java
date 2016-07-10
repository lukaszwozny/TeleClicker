package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.teleclicker.ui.ScoreLabel;

/**
 * Created by Senpai on 10.07.2016.
 */
public class ScoreService {
    public final static String GAME_SCORE = "com.mygdx.clicker.prefs.score";
    public final static String GAME_PASSIVE_INCOME = "com.mygdx.clicker.prefs.passive";
    public final static String GAME_SAVED_TIMESTAMP = "com.mygdx.clicker.prefs.timestamp";

    private float points;
    private float passiveIncome;

    private ScoreLabel scoreLabel;
    private ScoreLabel passiveIncomeLabel;

    private Preferences prefs;

    public ScoreService(Preferences prefs) {
        this.prefs = prefs;
        init();
    }

    public void initLabels(Stage stage) {
        scoreLabel = new ScoreLabel(20, 650);
        passiveIncomeLabel = new ScoreLabel(20, 630);
        stage.addActor(scoreLabel);
        stage.addActor(passiveIncomeLabel);
    }

    public void saveTimeStamp(){

    }

    private void init() {
        loadScore();
        calculateGainedPassiveIncome();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {

                System.out.println("Test");
                points += passiveIncome;
            }
        }, 1, 1);
    }

    private void calculateGainedPassiveIncome() {
        long deleyTime = TimeUtils.millis() - getSavedTimeStamp();
        points += deleyTime / 1000 * passiveIncome / 10;
    }

    public void addPoints(float pointsToAdd) {
        points += pointsToAdd;
        updateSavedScoreInPrefs();
    }

    public void addPoint() {
        points++;
        updateSavedScoreInPrefs();
    }

    public void resetGameScore() {
        points = 0.0f;
        passiveIncome = 0.0f;
        updateSavedScoreInPrefs();
    }

    public void updateScoreLabel() {
        scoreLabel.setText("Erlangi: " + points);
        passiveIncomeLabel.setText("Erl / sec: " + passiveIncome);
    }

    public void saveCurrentTmeStamp() {
        prefs.putLong(GAME_SAVED_TIMESTAMP,TimeUtils.millis());
        prefs.flush();
    }

    public void addPassiveIncome(int value) {
        passiveIncome += value;
        updateSavedScoreInPrefs();
    }

    private void loadScore() {
        points = prefs.getFloat(GAME_SCORE);
        passiveIncome = prefs.getFloat(GAME_PASSIVE_INCOME);
    }

    private void updateSavedScoreInPrefs() {
        prefs.putFloat(GAME_SCORE, points);
        prefs.putFloat(GAME_PASSIVE_INCOME, passiveIncome);
        prefs.flush();
    }

    /**
     * ---------------------
     * getters and setters
     */

    private Long getSavedTimeStamp(){
        return prefs.getLong(GAME_SAVED_TIMESTAMP);
    }

    public float getPoints() {
        return points;
    }
}
