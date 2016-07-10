package com.mygdx.teleclicker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.teleclicker.TeleClicker;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = TeleClicker.GAME_NAME;
		config.width = TeleClicker.WIDTH;
		config.height = TeleClicker.HEIGHT;
		config.resizable = false;

		new LwjglApplication(new TeleClicker(), config);
	}
}
