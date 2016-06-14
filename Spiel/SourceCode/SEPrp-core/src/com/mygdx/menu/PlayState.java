package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.character.Character;
import com.character.Drachenmensch;
import com.character.Krieger;
import com.character.Magier;
import com.character.Schütze;
import com.mygdx.game.MyGdxGame;

import com.grafiken.*;

public class PlayState extends State{

	int drehmoment=0;
	int teleportzähler=0;
	private Texture portal=new Texture("grafiken/crystal.png");
	private Sprite Portal=new Sprite(portal);
	
	
	
	private Character c;
	private Map map;
	private ICharacter s;
	
	
	public PlayState(GameStateManager gsm,int characterauswahl) {
		super(gsm);
		
		// TODO Auto-generated constructor stub
		
		s=new com.grafiken.Character();
		map=new Map(cam);
	// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //
		if(characterauswahl==1){
			System.out.println("Krieger");
			c=new Krieger(100,100,s.getTextureRegion(0),2.5f);
			System.out.println("Krieger");
		}
		else if(characterauswahl==2){
			System.out.println("Magier");
			c=new Magier(100,100,s.getTextureRegion(1),2.5f);
			System.out.println("Magier");
		}
		else if(characterauswahl==3){
			System.out.println("Drachenmensch");
			c=new Drachenmensch(100,100,s.getTextureRegion(2),2.5f);
			System.out.println("Drachenmensch");
		}
		else if(characterauswahl==4){
			System.out.println("Schütze");
			c=new Schütze(100,100,s.getTextureRegion(3),2.5f);
			System.out.println("Schütze");
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
		
		//////////////////////////////////////////////////////////////////////PORTALSHIT ANFANG////////////////////////
		
		//=================================================================//ERSTES PORTAL ANFANG

		if (c.getPosition().x>=Portal.getX()-10 && c.getPosition().x<=Portal.getX()+50 &&  c.getPosition().y<=Portal.getY()+50 && c.getPosition().y>=Portal.getY()-10) {
			drehmoment+=3;
			teleportzähler+=1;
		Portal.setColor(Color.GOLD);
		if (teleportzähler==200) {
			c.getPosition().x=2926;
		c.getPosition().y=209;
		}
		
		}
		else {
			Portal.setColor(Color.SKY);
			drehmoment++;
			teleportzähler=0;
		}
		
		//=================================================================//ERSTES PORTAL ENDE
		
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
		Portal.setPosition(200,200);
		Portal.setRotation(drehmoment);
		Portal.draw(sb);
		
	//PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE PORTALE //	
		
	/**KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA*/	
		if(c.getPosition().y>=0 && c.getPosition().y< Gdx.graphics.getHeight()/2 && c.getPosition().x>=0 && c.getPosition().x< Gdx.graphics.getWidth()/2)
			{sb.setProjectionMatrix(cam.combined);
			sb.draw(c.getTextureRegion(), c.getPosition().x, c.getPosition().y);
			cam.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
			cam.update();
			
			}
		else if(c.getPosition().x>=0 && c.getPosition().x< Gdx.graphics.getWidth()/2){
			sb.setProjectionMatrix(cam.combined);
			sb.draw(c.getTextureRegion(), c.getPosition().x, c.getPosition().y);
			cam.position.set(Gdx.graphics.getWidth()/2,c.getPosition().y,0);
			cam.update();
			
		}
		else if(c.getPosition().y>=0 && c.getPosition().y< Gdx.graphics.getHeight()/2){
			sb.setProjectionMatrix(cam.combined);
			sb.draw(c.getTextureRegion(), c.getPosition().x, c.getPosition().y);
			cam.position.set(c.getPosition().x,Gdx.graphics.getHeight()/2,0);
			cam.update();
		}
		else{
		sb.setProjectionMatrix(cam.combined);	
		sb.draw(c.getTextureRegion(), c.getPosition().x, c.getPosition().y);
		cam.position.set(c.getPosition().x,c.getPosition().y,0);
		cam.update();
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
