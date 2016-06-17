package com.mygdx.menu;

import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State{
		
	private Texture background[];
	private MainMenuButton loadbutton;
	private MainMenuButton newgamebutton;
	 MainMenuButton	beendenbutton;
	
	private MainMenuButton loadbuttonpressed;
	private MainMenuButton newgamebuttonpressed;
	 MainMenuButton	beendenbuttonpressed;
	 
	 private int indexback;
	 private int framecounter;
	 
	 protected Sound click;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		click=Gdx.audio.newSound(Gdx.files.internal("pindrop.mp3"));
		 indexback=0;
		 framecounter=0;
		background=new Texture[4];
		background[0]=new Texture("back.jpg");
		background[1]=new Texture("1.jpg");
		background[2]=new Texture("2.jpg");
		background[3]=new Texture("1.jpg");
	loadbutton=new MainMenuButton(1728/2-77,1080/2-66,"loadbutton.jpg");
	loadbuttonpressed=new MainMenuButton(1728/2-77,1080/2-66,"loadbuttonpressed.jpg");
	newgamebutton=new MainMenuButton(1728/2-77, 1080/2-172, "newgamebutton.jpg");
	newgamebuttonpressed=new MainMenuButton(1728/2-77, 1080/2-172, "newgamebuttonpressed.jpg");
	beendenbutton=new MainMenuButton(1728/2-77,1080/2-280,"beendenbutton.jpg");
	beendenbuttonpressed=new MainMenuButton(1728/2-77,1080/2-280,"beendenbuttonpressed.jpg");
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		/*BSP F�R KEYLISTENER:
		 * if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) 
         * marioX -= Gdx.graphics.getDeltaTime() * marioSpeed;
		 */
		
		
	
		if (Gdx.input.isKeyJustPressed(Keys.L)) {
			loadbutton=new MainMenuButton(1728/2-77,1080/2-66,"loadbuttonpressed.jpg");
			gsm.push(new LoadMenuState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.N)) {
			
			
			gsm.push(new NewMenuState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			
			Gdx.app.exit();
		}
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
	if(framecounter==0){
		indexback++;
		indexback%=4;
	}
		framecounter++;
		framecounter%=20;
		handleInput();
		try {
			Thread.sleep(20);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("HURRA!");
	}
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.begin();
		sb.draw(background[indexback],0,0,MyGdxGame.WIDTH,MyGdxGame.HEIGHT);
		sb.draw(loadbutton.getTexture(), loadbutton.getPosition().x, loadbutton.getPosition().y);
		if (Gdx.input.getX()> loadbutton.getPosition().x && Gdx.input.getX()< loadbutton.getPosition().x+250 && Gdx.input.getY()> loadbutton.getPosition().y && Gdx.input.getY()< loadbutton.getPosition().y+88) {
			sb.draw(loadbuttonpressed.getTexture(), loadbuttonpressed.getPosition().x, loadbuttonpressed.getPosition().y);
			if (Gdx.input.isTouched()) {
				gsm.push(new LoadMenuState(gsm));
				click.play();
			}
		}
		sb.draw(newgamebutton.getTexture(), newgamebutton.getPosition().x, newgamebutton.getPosition().y);
		if (Gdx.input.getX()> newgamebutton.getPosition().x && Gdx.input.getX()< newgamebutton.getPosition().x+250 && Gdx.input.getY()>1080/2+55 && Gdx.input.getY()<1080/2+140) {
			sb.draw(newgamebuttonpressed.getTexture(), newgamebuttonpressed.getPosition().x, newgamebuttonpressed.getPosition().y);
			if (Gdx.input.isTouched()) {
				gsm.push(new NewMenuState(gsm));
				click.play();
			}
		}
		sb.draw(beendenbutton.getTexture(), beendenbutton.getPosition().x, beendenbutton.getPosition().y);
		if (Gdx.input.getX()> beendenbutton.getPosition().x && Gdx.input.getX()< beendenbutton.getPosition().x+250 && Gdx.input.getY()>1080/2+155 && Gdx.input.getY()<1080/2+240) {
			sb.draw(beendenbuttonpressed.getTexture(), beendenbuttonpressed.getPosition().x, beendenbuttonpressed.getPosition().y);
			if (Gdx.input.isTouched()) {
				click.play();
				Gdx.app.exit();
			}
		}
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background[1].dispose();	
		background[2].dispose();
		background[3].dispose();
		loadbutton.dispose();
		loadbuttonpressed.dispose();
		newgamebutton.dispose();
		newgamebuttonpressed.dispose();
		beendenbutton.dispose();
		beendenbuttonpressed.dispose();
	}
	
}
