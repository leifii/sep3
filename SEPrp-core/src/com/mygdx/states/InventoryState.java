package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.sprites.LoadMenuWindow;

public class InventoryState extends PlayState {

	private Texture playstatebackground;
	private LoadMenuWindow inventar;
	
	protected InventoryState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		playstatebackground=new Texture("playstatebackground.jpg");
		inventar=new LoadMenuWindow(1000, 700,"inventar.jpg");
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.I)) {
			gsm.push(new PlayState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new PauseState(gsm));
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
		sb.draw(inventar.getTexture(), inventar.getPosition().x, inventar.getPosition().y);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		playstatebackground.dispose();
		inventar.dispose();
	}

}
