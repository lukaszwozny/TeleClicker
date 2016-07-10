package com.mygdx.teleclicker;

import com.mygdx.teleclicker.Screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class TeleClicker extends Game {
	public final static String GAME_PREFS = "com.mygdx.clicker.prefs";
	public final static String GAME_SCORE = "com.mygdx.clicker.prefs.score";

	public final static String GAME_NAME = "Tele Clicker";

	public final static int WIDTH = 480;
	public final static int HEIGHT = 720;

	private boolean paused;

	private Preferences prefs;

	private int points;

	@Override
	public void create() {
		this.setScreen(new SplashScreen(this));
		init();
	}

	private void init() {
		prefs = Gdx.app.getPreferences(GAME_PREFS);
		loadScore();
	}

	private void loadScore() {
		points = prefs.getInteger(GAME_SCORE);
	}

	public void addPoint() {
		++points;
		updateScorePrefs();
	}

	public void resetScore() {
		points = 0;
		updateScorePrefs();
	}

	private void updateScorePrefs() {
		prefs.putInteger(GAME_SCORE, points);
		prefs.flush();
	}

	/*
    *
	* ----------------------
	* Getters and setters
	*
	 */

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getPoints() {
		return points;
	}
}
