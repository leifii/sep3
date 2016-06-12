package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.character.Character;
import com.mygdx.game.MyGdxGame;
import com.grafiken.*;

public class PlayState extends State{
	
	private Texture playstatebackground;
	private Character c;
	private Map map;
	private ICharacter s;
	

	protected PlayState(GameStateManager gsm) {
		super(gsm);
		
		// TODO Auto-generated constructor stub
		s=new com.grafiken.Character();
		map=new Map(cam);
		playstatebackground=new Texture("playstatebackground.jpg");
		c=new Character(100,100,s.getTextureRegion(1),2.5f);
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new PauseState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			gsm.push(new MapState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.I)) {
			gsm.push(new InventoryState(gsm));
		}
		/*if (Gdx.input.isKeyJustPressed(Keys.S)) {
			gsm.push(new SkillState(gsm));
		}*/
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
		c.update(dt);
		
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
		map.render(sb);
		sb.begin();
//		sb.draw(s.getTextureRegion(0),MyGdxGame.WIDTH/2,MyGdxGame.HEIGHT/2);
		sb.draw(c.getTextureRegion(), c.getPosition().x, c.getPosition().y);
		
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		playstatebackground.dispose();
		
	}

}
