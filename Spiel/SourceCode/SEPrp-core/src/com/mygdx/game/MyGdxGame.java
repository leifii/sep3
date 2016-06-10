package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.menu.GameStateManager;
import com.mygdx.menu.MenuState;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	public static final int WIDTH=1728;
	public static final int HEIGHT=1080;
	public static final String TITLE ="sepRP-Gruppe-L";
	private GameStateManager gsm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
	gsm=new GameStateManager();
	Gdx.gl.glClearColor(0.2f, 0.3f, 0.6f, 1);
	
	gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
