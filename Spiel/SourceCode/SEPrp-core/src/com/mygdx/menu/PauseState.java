package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class PauseState extends PlayState {

		Texture playstatebackground;
		LoadMenuWindow pausefenster;
	protected PauseState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		playstatebackground=new Texture("playstatebackground.jpg");
		pausefenster=new LoadMenuWindow(1728/2-215,1080/2-400, "pausewindow.jpg");
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new PlayState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.B)) {
			
			gsm.push(new MenuState(gsm));
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.begin();
		sb.draw(playstatebackground,0,0,MyGdxGame.WIDTH,MyGdxGame.HEIGHT);
		sb.draw(pausefenster.getTexture(), pausefenster.getPosition().x, pausefenster.getPosition().y);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		playstatebackground.dispose();
		pausefenster.dispose();

	}

}
