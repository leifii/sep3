package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.character.Character;
import com.mygdx.game.Author;

@Author(name = "?Dilara Güler?")


public class InventoryState extends State {

	private Texture playstatebackground;
	private LoadMenuWindow inventar;
	private PlayState playState;
	private Character player;
	
	protected InventoryState(GameStateManager gsm, PlayState state, Character player) {
		super(gsm);
		// TODO Auto-generated constructor stub
		playstatebackground=new Texture("userInterface/Inventar.png");
		inventar=new LoadMenuWindow(1000, 700,"userInterface/Inventar.png");
		playState = state;
		this.player = player;
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(playState);
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
		sb.draw(playstatebackground,-31,-31,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
