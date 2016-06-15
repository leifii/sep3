package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json.Serializable;

import com.character.Character;
import com.character.Schurke;
import com.character.Krieger;
import com.character.Magier;
import com.character.Schuetze;
import com.mygdx.game.MyGdxGame;
import com.objects.Truhe;
import com.objects.Portal;
import com.grafiken.*;

public class PlayState extends State {

	
	Truhe Truhe[]=new Truhe[]{new Truhe(100, 200),new Truhe(150,200),new Truhe(200,200),new Truhe(250,200)};
	
	
	private Character c;
	private Map map;
	private ICharacter s;
	private float currentFrameTime;
	TextureRegion currentFrame;
	
	Portal Portal[]=new Portal[]{new Portal(50, 50, 500, 500),new Portal(500, 500, 50, 50)};
	
	public PlayState(GameStateManager gsm,int characterauswahl) {
		super(gsm);
		

		
		s=new com.grafiken.Character();
		map=new Map(cam);
		
	// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //
		if(characterauswahl==1){
			System.out.println("Krieger");
			c=new Krieger(100,100,s.getAnimation(0),2.5f);
			System.out.println("Krieger");
		}
		else if(characterauswahl==2){
			System.out.println("Magier");
			c=new Magier(100,100,s.getTextureRegion(1),2.5f,null,0);
			System.out.println("Magier");
		}
		else if(characterauswahl==3){
			System.out.println("Drachenmensch");
			c=new Schurke(100,100,s.getTextureRegion(2),2.5f,null,0);
			System.out.println("Drachenmensch");
		}
		else if(characterauswahl==4){
			System.out.println("SchÜtze");
			c=new Schuetze(100,100,s.getTextureRegion(3),2.5f,null,0);
			System.out.println("SchÜtze");
		}
		
	// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //
	}

	
	@Override
	protected void handleInput() {
		
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
		c.update(dt);
		currentFrameTime+=dt;
		currentFrame=c.getAnimation().getKeyFrame(currentFrameTime);
		
		
		
		if (c.getPosition().x<=0) {
			c.getPosition().x=0;
		}
		if (c.getPosition().y<=0) {
			c.getPosition().y=0;
		}
		
		cam.update();

	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
		map.render(sb);
		sb.begin();

		
		c.draw(sb);
		

		
		// TRUHEN //
		for (int i = 0; i < Truhe.length; i++) {
			Truhe[i].render(this, sb,c.getBounds(),c);
		}
		// TRUHEN //
		
		// PORTALE //
		for (int i = 0; i < Portal.length; i++) {
			Portal[i].render(sb,c);
		}
		// PORTALE //
		
		
		
		
	/**KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA*/	
		if(c.getPosition().y>=0 && c.getPosition().y< Gdx.graphics.getHeight()/2 && c.getPosition().x>=0 && c.getPosition().x< Gdx.graphics.getWidth()/2)
			{sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
			cam.update();
			
			}
		else if(c.getPosition().x>=0 && c.getPosition().x< Gdx.graphics.getWidth()/2){
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(Gdx.graphics.getWidth()/2,c.getPosition().y,0);
			cam.update();
			
		}
		else if(c.getPosition().y>=0 && c.getPosition().y< Gdx.graphics.getHeight()/2){
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(c.getPosition().x,Gdx.graphics.getHeight()/2,0);
			cam.update();
		}
		else{
		sb.setProjectionMatrix(cam.combined);	
		sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
		cam.position.set(c.getPosition().x,c.getPosition().y,0);
		cam.update();
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			cam.zoom-=0.01f;
		}
		else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			cam.zoom+=0.01f;
		}
	/**KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA*/	
		
		sb.end();
	}
		
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		c.dispose();
		this.dispose();
	
	}

}
