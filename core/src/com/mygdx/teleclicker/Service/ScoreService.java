package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.teleclicker.ui.ScoreLabel;

/**
 * Created by Senpai on 10.07.2016.
 */
public class ScoreService {
    public final static String GAME_SCORE = "com.mygdx.clicker.prefs.score";

    private int points;
    private int passiveIncome;

    private ScoreLabel scoreLabel;
    private ScoreLabel passiveIncomeLabel;

    private Preferences prefs;

    public ScoreService(Preferences prefs){
        this.prefs = prefs;
        init();
    }

    public void initLabels(Stage stage) {
        scoreLabel = new ScoreLabel(20,650);
        passiveIncomeLabel = new ScoreLabel(20, 630);
        stage.addActor(scoreLabel);
        stage.addActor(passiveIncomeLabel);
    }

    private void init() {
        loadScore();
    }

    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
        updateSavedScoreInPrefs();
    }

    public void addPoint(){
        points++;
        updateSavedScoreInPrefs();
    }

    public void resetGameScore() {
        points = 0;
        updateSavedScoreInPrefs();
    }

    private void loadScore() {
        points = prefs.getInteger(GAME_SCORE);
    }

    private void updateSavedScoreInPrefs() {
        prefs.putInteger(GAME_SCORE, points);
        prefs.flush();
    }

    public void addPassiveIncome(int value) {
        // TODO implement
        System.out.println("passive income click");
    }

    /**
     * ---------------------
     * getters and setters
     *
     */

    public int getPoints() {
        return points;
    }

    public void updateScoreLabel() {
        scoreLabel.setText("Erlangi: " + points);
        passiveIncomeLabel.setText("Erl / sec: " + passiveIncome);
    }
}
