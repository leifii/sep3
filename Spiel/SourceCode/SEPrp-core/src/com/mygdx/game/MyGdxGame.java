package com.mygdx.game;

import java.awt.Toolkit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grafiken.Map;
import com.mygdx.menu.GameStateManager;
import com.mygdx.menu.MenuState;
import com.mygdx.menu.NewMenuState;
import com.mygdx.menu.NewMenuState1;

@Author(name = "Bijan Shahbaz Nejad , Angelo Soltner")

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;


	public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public static final String TITLE = "sepRP-Gruppe-L";

	private GameStateManager gsm;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);

		gsm.push(new NewMenuState1(gsm));
	}

	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
