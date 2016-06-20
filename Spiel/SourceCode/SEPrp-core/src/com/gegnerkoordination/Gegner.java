package com.gegnerkoordination;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.character.Attributes;
import com.character.Character;

//TODO abstract class
public class Gegner extends Character {
	private int exp;
	private TextureRegion currentFrame;
	private float time;
	
	
	//public Character (int x,int y, TextureRegion[][] animation,float speed){
	public Gegner(int x, int y, TextureRegion[][] animation ,Attributes attributes, int exp, TiledMapTileLayer[] collisionLayer){
		super(x, y, animation, collisionLayer, attributes);
		this.exp=exp;
		
	}
	
	public Gegner (int x,int y, TextureRegion[][] animation, TiledMapTileLayer[] collisionLayer, Attributes attributes){
		super(x,y,animation,collisionLayer, attributes);
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

		} 
		
		//direction
		Direction dir = Direction.SOUTH_STAND;
		if(y > getPosition().y) 
			dir = move ? Direction.NORTH : Direction.NORTH_STAND;
		else if(y < getPosition().y)
			dir = move ? Direction.SOUTH : Direction.SOUTH_STAND;
		else if(x > getPosition().x)
			dir = move ? Direction.EAST : Direction.EAST_STAND;
		else if(x < getPosition().x)
			dir = move ? Direction.WEST : Direction.WEST_STAND;
		setRichtung(dir);
		
		

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
	
	public void attack() {
		
	}
	
	public void killed(Character c) {
		c.expSammeln(exp, true);
	}
	
}
