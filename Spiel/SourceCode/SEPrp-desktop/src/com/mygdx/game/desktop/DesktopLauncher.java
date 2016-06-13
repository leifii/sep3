package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=MyGdxGame.WIDTH;
		config.height=MyGdxGame.HEIGHT;
		config.title=MyGdxGame.TITLE;
	
	config.fullscreen=true;
		
		new LwjglApplication(new MyGdxGame(), config);
	}
}
