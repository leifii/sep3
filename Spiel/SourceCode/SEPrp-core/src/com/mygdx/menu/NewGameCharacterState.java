package com.mygdx.menu;

import com.android.ide.common.rendering.api.SessionParams.Key;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class NewGameCharacterState extends NewMenuState {
	PlayState playstate;
	Texture back;
	private MainMenuButton loadbutton;
	private MainMenuButton newgamebutton;
	private LoadMenuWindow newcharwindow;
	
	protected NewGameCharacterState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		back=new Texture("back.jpg");
		loadbutton=new MainMenuButton(1728/2-77,1080/2-66,"loadbutton.jpg");
		newgamebutton=new MainMenuButton(1728/2-77, 1080/2-172, "newgamebutton.jpg");
		beendenbutton=new MainMenuButton(1728/2-77,1080/2-280,"beendenbutton.jpg");
		newcharwindow=new LoadMenuWindow(1728/2-160,1080/2-200, "newcharwindow.jpg");
	}
	

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				gsm.push(new NewMenuState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			playstate=new PlayState(gsm, 1);
			gsm.push(playstate);
	}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			playstate=new PlayState(gsm, 2);
			gsm.push(playstate);
	}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
			playstate=new PlayState(gsm, 3);
			gsm.push(playstate);
	}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_4)) {
			playstate=new PlayState(gsm, 4);
			gsm.push(playstate);
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
		sb.draw(back,0,0,MyGdxGame.WIDTH,MyGdxGame.HEIGHT);
		sb.draw(loadbutton.getTexture(), loadbutton.getPosition().x, loadbutton.getPosition().y);
		sb.draw(newgamebutton.getTexture(), newgamebutton.getPosition().x, newgamebutton.getPosition().y);
		sb.draw(beendenbutton.getTexture(), beendenbutton.getPosition().x, beendenbutton.getPosition().y);
		sb.draw(newcharwindow.getTexture(), newcharwindow.getPosition().x, newcharwindow.getPosition().y);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		back.dispose();
	}

}
