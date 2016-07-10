package com.mygdx.teleclicker;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.teleclicker.Screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.teleclicker.Service.SoundService;

public class TeleClicker extends Game {
	public final static String GAME_PREFS = "com.mygdx.clicker.prefs";
	public final static String GAME_SCORE = "com.mygdx.clicker.prefs.score";

	public final static String GAME_NAME = "Tele Clicker";

	public final static int WIDTH = 480;
	public final static int HEIGHT = 700;

	private boolean paused;

	private SoundService soundService;

	private Preferences prefs;

	private int points;

	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init() {
		prefs =  Gdx.app.getPreferences(GAME_PREFS);
		loadScore();
		soundService = new SoundService();
	}

	private void loadScore() {
		points = prefs.getInteger(GAME_SCORE);
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

	private void updateSavedScoreInPrefs() {
		prefs.putInteger(GAME_SCORE, points);
		prefs.flush();
	}


	public void addPassiveIncome(int value) {
		// TODO implement
		System.out.println("passive income click");
	}

	public boolean isPaused() {
		return paused;
	}

	/**
	 * ---------------------
	 * getters and setters
	 *
	 */

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getPoints() {
		return points;
	}

	public SoundService getSoundService() {
		return soundService;
	}
}
