package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.utils.Json.Serializable;

import com.character.Character;
import com.character.Schurke;
import com.character.Krieger;
import com.character.Magier;
import com.character.Schuetze;
import com.mygdx.game.MyGdxGame;

import com.grafiken.*;

public class PlayState extends State {

	int drehmoment=0;
	int teleportzähler[]=new int[]{0,0};
	private Texture portal=new Texture("grafiken/crystal.png");
	
	private Sprite Portale[]=new Sprite[]{(new Sprite(portal)),(new Sprite(portal))};
	
	
	
	private Character c;
	private Map map;
	private ICharacter s;
	private float currentFrameTime;
	TextureRegion currentFrame;
	
	
	public PlayState(GameStateManager gsm,int characterauswahl) {
		super(gsm);
		
		// TODO Auto-generated constructor stub
		
		s=new com.grafiken.Character();
		map=new Map(cam);
		
	// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //
		if(characterauswahl==1){
			System.out.println("Krieger");
			c=new Krieger(100,100,s.getAnimation(0),2.5f,null,0);
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
			System.out.println("Schï¿½tze");
			c=new Schuetze(100,100,s.getTextureRegion(3),2.5f,null,0);
			System.out.println("Schï¿½tze");
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
		
		//////////////////////////////////////////////////////////////////////PORTALSHIT ANFANG////////////////////////
		
		//=================================================================//ERSTES PORTAL ANFANG

		if (c.getPosition().x>=Portale[0].getX()-10 && c.getPosition().x<=Portale[0].getX()+50 &&  c.getPosition().y<=Portale[0].getY()+50 && c.getPosition().y>=Portale[0].getY()-10) {
			drehmoment+=3;
			teleportzähler[0]+=1;
		Portale[0].setColor(Color.GOLD);
		if (teleportzähler[0]==200) {
			c.getPosition().x=Portale[1].getX()+70;
		c.getPosition().y=Portale[1].getY();
		}
		
		}
		else {
			Portale[0].setColor(Color.SKY);
			drehmoment++;
			teleportzähler[0]=0;
		}
		
		//=================================================================//ERSTES PORTAL ENDE
		
		//=================================================================//ZWEITES PORTAL ANFANG
		
		if (c.getPosition().x>=Portale[1].getX()-10 && c.getPosition().x<=Portale[1].getX()+50 &&  c.getPosition().y<=Portale[1].getY()+50 && c.getPosition().y>=Portale[1].getY()-10) {
			drehmoment+=3;
			teleportzähler[1]+=1;
		Portale[1].setColor(Color.GOLD);
		if (teleportzähler[1]==200) {
			c.getPosition().x=Portale[0].getX()+70;
		c.getPosition().y=Portale[0].getY();
		}
		
		}
		else {
			Portale[1].setColor(Color.SKY);
			drehmoment++;
			teleportzähler[1]=0;
		}
		
		//=================================================================//ZWEITES PORTAL ENDE
		
		//////////////////////////////////////////////////////////////////////PORTALSHIT ENDE////////////////////////////////
		
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
	//PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE //	
		Portale[0].setPosition(2926,1000);
		Portale[0].setRotation(drehmoment);
		Portale[0].draw(sb);
		
		Portale[1].setPosition(200,200);
		Portale[1].setRotation(drehmoment);
		Portale[1].draw(sb);
		
	//PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE //	
		
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
		portal.dispose();
	}

}
