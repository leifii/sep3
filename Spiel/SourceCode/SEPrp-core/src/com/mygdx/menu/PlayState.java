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
import com.character.Sch�tze;
import com.mygdx.game.MyGdxGame;

import com.grafiken.*;

public class PlayState extends State{

	int drehmoment=0;
	int teleportz�hler=0;
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
			System.out.println("Sch�tze");
			c=new Sch�tze(100,100,s.getTextureRegion(3),2.5f);
			System.out.println("Sch�tze");
		}
	
	}

	
	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
	/*	if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new PauseState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			gsm.push(new MapState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.I)) {
			gsm.push(new InventoryState(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			gsm.push(new SkillState(gsm));
		}*/
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
		c.update(dt);
		
		//////////////////////////////////////////////////////////////////////PORTAL ANFANG
		

		if (c.getPosition().x>=Portal.getX()-10 && c.getPosition().x<=Portal.getX()+50 &&  c.getPosition().y<=Portal.getY()+50 && c.getPosition().y>=Portal.getY()-10) {
			drehmoment+=3;
			teleportz�hler+=1;
		Portal.setColor(Color.GOLD);
		if (teleportz�hler==200) {
			c.getPosition().x=2926;
		c.getPosition().y=209;
		}
		
		}
		else {
			Portal.setColor(Color.SKY);
			drehmoment++;
			teleportz�hler=0;
		}
	
		
		//////////////////////////////////////////////////////////////////////PORTAL ENDE
		
		if (c.getPosition().x<=0) {
			c.getPosition().x=0;
		}
		if (c.getPosition().y<=0) {
			c.getPosition().y=0;
		}
	
//		if(c.getPosition().x>0 && c.getPosition().x< Gdx.graphics.getWidth()/2){
//			batch.begin();
//			batch.draw(c.getTextureRegion(), c.getPosition().x, c.getPosition().y);
//			batch.end();
//		}
//		
//		if (c.getPosition().x<0 && c.getPosition().y<0) {
//		
//		}
//		else if (c.getPosition().x<=0+Gdx.graphics.getWidth()/2) {
//			
//			cam.position.set(Gdx.graphics.getWidth()/2,c.getPosition().y,0);
//		}
//		el

		
		
		cam.update();

	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
		map.render(sb);
		sb.begin();
		
		Portal.setPosition(200,200);
		Portal.setRotation(drehmoment);
		Portal.draw(sb);
	
		
		
//		sb.draw(s.getTextureRegion(0),MyGdxGame.WIDTH/2,MyGdxGame.HEIGHT/2);
	//	sb.draw(c.getTextureRegion(), c.getPosition().x, c.getPosition().y);
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
		
		
		sb.end();
	}
		
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		c.dispose();
	}

}
