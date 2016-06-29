package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Author;
import com.mygdx.game.MyGdxGame;

@Author(name = ,"Bijan Shahbaz Nejad")

public class SkillState extends State {

	private Texture playstatebackground;
	private LoadMenuWindow skills;
	
	protected SkillState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		playstatebackground=new Texture("playstatebackground.jpg");
		skills=new LoadMenuWindow(1100, 500,"skills.jpg");
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
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
		sb.draw(skills.getTexture(), skills.getPosition().x, skills.getPosition().y);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		playstatebackground.dispose();
		skills.dispose();
	}

}
