package com.mygdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.sprites.LoadMenuWindow;
import com.mygdx.sprites.MainMenuButton;

public class LoadMenuState extends MenuState {

	
	private Texture background;
	private MainMenuButton loadbutton;
	private MainMenuButton newgamebutton;
	
	private LoadMenuWindow loadmenuwindow;
	
	public LoadMenuState(GameStateManager gsm) {
		super(gsm);
		background=new Texture("back.jpg");
	
		loadmenuwindow=new LoadMenuWindow(1728/2-160,1080/2-200,"loadmenuwindow.jpg");
		loadbutton=new MainMenuButton(1728/2-77,1080/2-66,"loadbutton.jpg");
		newgamebutton=new MainMenuButton(1728/2-77, 1080/2-172, "newgamebutton.jpg");
		beendenbutton=new MainMenuButton(1728/2-77,1080/2-280,"beendenbutton.jpg");
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.L)) {
			gsm.push(new MenuState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
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
		sb.draw(background,0,0,MyGdxGame.WIDTH,MyGdxGame.HEIGHT);
		sb.draw(loadbutton.getTexture(), loadbutton.getPosition().x, loadbutton.getPosition().y);
		sb.draw(newgamebutton.getTexture(), newgamebutton.getPosition().x, newgamebutton.getPosition().y);
		sb.draw(beendenbutton.getTexture(), beendenbutton.getPosition().x, beendenbutton.getPosition().y);
		sb.draw(loadmenuwindow.getTexture(), loadmenuwindow.getPosition().x, loadmenuwindow.getPosition().y);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
		loadmenuwindow.dispose();
		loadbutton.dispose();
		newgamebutton.dispose();
	}

}
