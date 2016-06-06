package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.sprites.LoadMenuWindow;
import com.mygdx.sprites.MainMenuButton;

public class NewMenuState extends MenuState {

	
	private Texture back;
	private LoadMenuWindow newgamewindow;
	private MainMenuButton loadbutton;
	private MainMenuButton newgamebutton;
	
	protected NewMenuState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		back=new Texture("back.jpg");
		newgamewindow=new LoadMenuWindow(1728/2-160,1080/2-200,"newgamewindow.jpg");
		loadbutton=new MainMenuButton(1728/2-77,1080/2-66,"loadbutton.jpg");
		newgamebutton=new MainMenuButton(1728/2-77, 1080/2-172, "newgamebutton.jpg");
		beendenbutton=new MainMenuButton(1728/2-77,1080/2-280,"beendenbutton.jpg");
		
	}

	
	protected void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.N)) {
			gsm.push(new MenuState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new MenuState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.J)) {
			gsm.push(new NewGameCharacterState(gsm));
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
		sb.draw(newgamewindow.getTexture(), newgamewindow.getPosition().x, newgamewindow.getPosition().y);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		back.dispose();
		newgamewindow.dispose();
	}

}
