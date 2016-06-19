package com.gegnerkoordination;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.character.Character;

//TODO abstract class
public class Gegner extends Character {
	private int exp;
	private TextureRegion currentFrame;
	private float time;
	
	
	//public Character (int x,int y, TextureRegion[][] animation,float speed){
	public Gegner(int x, int y, TextureRegion[][] animation ,Attributes attributes, int exp, TiledMapTileLayer[] collisionLayer){
		super(x, y, animation, attributes.MS, collisionLayer);
		setAttributes(attributes);
		this.exp=exp;
		
	}
	
	public Gegner (int x,int y, TextureRegion[][] animation,float speed, TiledMapTileLayer[] collisionLayer){
		super(x,y,animation,speed, collisionLayer);
		setLaufspeed(speed);
	}
	
	public void update(float dt) {
		getBounds().setPosition(this.getPosition().x,this.getPosition().y);
		time += dt;
		currentFrame = getAnimation().getKeyFrame(time);
		
	}
	
	public void draw(SpriteBatch sb){
		sb.draw(currentFrame, getPosition().x, getPosition().y);
	}
	

	public void follow(Character c) {
		float x = c.getPosition().x, y = c.getPosition().y;
		double dx = 0, dy = 0;
		final int abstand = 50;
		
		float currentDistance = Vector2.dst(x, y, getPosition().x, getPosition().y);
		boolean move = currentDistance > abstand;

		if(move) {
			//movement
			if(y != getPosition().y && x != getPosition().x) {
				dy = getLaufspeed() * (2 - 1/Math.sqrt(2));
				dx = getLaufspeed() * (2 - 1/Math.sqrt(2));
			} else if(y != getPosition().y) {
				dy =  2 * getLaufspeed();
			} else if(x != getPosition().x) {
				dx = 2 * getLaufspeed();
			}
			
			int dySign = y > getPosition().y ? 1 : -1;
			int dxSign = x > getPosition().x ? 1 : -1;
			dx *= dxSign;
			dy *= dySign;
			
			//direction
			Direction dir = null;
			if(dy > 0)
				dir = Direction.NORTH;
			else if(dy < 0)
				dir = Direction.SOUTH;
			else if(dx > 0)
				dir = Direction.EAST;
			else if(dx < 0)
				dir = Direction.WEST;
			
			setRichtung(dir);
		
		//not moving - standing animation
		} else {
			int richtung = 0;
			switch(getRichtung()) {
			case 0: richtung = 4; break;
			case 3: richtung = 5; break;
			case 2: richtung = 6; break;
			case 1: richtung = 7; break;
			}
			
			setRichtung(richtung);
		}
		
		

		//normalize movement
		if (y < getPosition().y && y > getPosition().y + dy || y > getPosition().x && y < getPosition().y + dy) {
			 dy = 0;
			 setY(y);
		}
		
		if (x < getPosition().x && x > getPosition().x + dx || x > getPosition().x && x < getPosition().x + dx) {
			 dx = 0;
			 setX(x);
		}
		
		move((float)dx, (float)dy);
		
	}
	
	public void killed(Character c) {
		c.expSammeln(exp, true);
	}
	
}
