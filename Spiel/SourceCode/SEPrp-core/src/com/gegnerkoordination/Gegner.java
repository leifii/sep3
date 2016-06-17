package com.gegnerkoordination;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.character.Character;

//TODO abstract class
public class Gegner extends Character {
	private int exp;
	private TextureRegion currentFrame;
	private float time;
	
	
	//public Character (int x,int y, TextureRegion[][] animation,float speed){
	public Gegner(int x, int y, TextureRegion[][] animation ,Attributes attributes, int exp, TiledMapTileLayer collisionLayer){
		super(x, y, animation, attributes.MS, collisionLayer);
		setAttributes(attributes);
		this.exp=exp;
		
	}
	
	public Gegner (int x,int y, TextureRegion[][] animation,float speed, TiledMapTileLayer collisionLayer){
		super(x,y,animation,speed, collisionLayer);
		setLaufspeed(speed);
	}
	
	public void update(float dt) {
		getBounds().setPosition(this.getPosition().x,this.getPosition().y);
		time += dt;
		currentFrame = getAnimation().getKeyFrame(time);
		
	}
	
	public void draw(SpriteBatch sb){
		sb.draw(currentFrame,getPosition().x,getPosition().y);
	}
	

	public void follow(Character c) {
		float x = c.getPosition().x, y = c.getPosition().y;
		float dx = 0, dy = 0;
		final int abstand = 50;
		
		double currentDistance = Math.sqrt(Math.pow(x-getPosition().x, 2) + Math.pow(y - getPosition().y, 2));
		//System.out.println(getPosition().x + "x" + getPosition().y + " at " + getLaufspeed() + " " + currentDistance);
		
		boolean move = currentDistance > abstand;

		if(move) {

			if (y > getPosition().y) { //north
				dy += 2*getLaufspeed();
				setRichtung(3);
				if (x < getPosition().x) { //northwest
					dy -= getLaufspeed()*(1/Math.sqrt(2));
					dx -= 2*getLaufspeed();
					dx += getLaufspeed()*(1/Math.sqrt(2));
				
				}
				else if (x > getPosition().x ) { //northeast
					dy -= getLaufspeed()*(1/Math.sqrt(2));
					dx += 2*getLaufspeed();
					dx -= getLaufspeed()*(1/Math.sqrt(2));
				
				}
				
				 if (y < getPosition().y + dy)
					 dy = getPosition().y - y;
				
			} else if (y < getPosition().y) { //south
				dy -= 2*getLaufspeed();
				setRichtung(0);
				if (x < getPosition().x) { //southwest
					dy += getLaufspeed()*(1/Math.sqrt(2));
					dx -= 2*getLaufspeed();
					dx += getLaufspeed()*(1/Math.sqrt(2));
					
					
					
				} else if (x > getPosition().x) { //southeast
					dy += getLaufspeed()*(1/Math.sqrt(2));
					dx += 2*getLaufspeed();
					dx -= getLaufspeed()*(1/Math.sqrt(2));
					
				}
				
				 if (y > getPosition().y + dy)
					 dy = getPosition().y - y;
				
			} else if (x < getPosition().x) { //west
				dx -= 2*getLaufspeed();
				setRichtung(1);
				
				 if (x > getPosition().y + dx)
					 dx = getPosition().x - x;
				
			} else if (x > getPosition().x) { //east
				dx += 2*getLaufspeed();
				setRichtung(2);
				
				 if (x < getPosition().y + dx)
					 dx = getPosition().x - x;
			} 
		} else if(getRichtung() == 0) {
			setRichtung(4);
		} else if(getRichtung() == 3) {
			setRichtung(5);
		} else if(getRichtung() == 2) {
			setRichtung(6);
		} else if(getRichtung() == 1) {
			setRichtung(7);
		}
		
		move(dx, dy);
		
	}
	
	public void killed(Character c) {
		c.expSammeln(exp, true);
	}
	
}
