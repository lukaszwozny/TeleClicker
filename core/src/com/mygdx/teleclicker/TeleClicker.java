package com.mygdx.teleclicker;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.teleclicker.Screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.teleclicker.Service.ScoreService;
import com.mygdx.teleclicker.Service.SoundService;

public class TeleClicker extends Game {
	public final static String GAME_PREFS = "com.mygdx.clicker.prefs";
	public final static String GAME_NAME = "Tele Clicker";

	public final static int WIDTH = 405;
	public final static int HEIGHT = 720;

	private boolean paused;

	private SoundService soundService;
	private ScoreService scoreService;

	private Preferences prefs;


	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init() {
		prefs =  Gdx.app.getPreferences(GAME_PREFS);
		soundService = new SoundService();
		scoreService = new ScoreService(prefs);
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

	public SoundService getSoundService() {
		return soundService;
	}

	public ScoreService getScoreService() {
		return scoreService;
	}
}
