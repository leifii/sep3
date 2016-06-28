package com.gegnerkoordination;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.AnimationDirection;
import com.character.Attributes;
import com.character.Character;
import com.mygdx.menu.PlayState;
import com.objects.Equipment;
import com.objects.EquipmentType;
import com.objects.Experience;
import com.objects.Item;
import com.objects.Truhe;

//TODO abstract class
public class Gegner extends Character {

	private int exp;
	private TextureRegion currentFrame;
	private float time;
	
	
	public Gegner (int x,int y, TextureRegion[][] animation, TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body){
		super(x,y,animation,collisionLayer, attributes, body);
		exp = 20; //TMP
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
		goToPosition(c.getPosition(), !getBounds().overlaps(c.getBounds()));
		attack();
	}

	public void goToPosition(Vector3 destination, boolean move) {
		float x = destination.x, y = destination.y;
		double dx = 0, dy = 0;

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
		}

		
		int dySign = y > getPosition().y ? 1 : -1;
		int dxSign = x > getPosition().x ? 1 : -1;
		dx *= dxSign;
		dy *= dySign;
		
		//direction
		AnimationDirection dir = AnimationDirection.SOUTH_STAND;
		if(y > getPosition().y) 
			dir = move ? AnimationDirection.NORTH_WALK : AnimationDirection.NORTH_STAND;
		else if(y < getPosition().y)
			dir = move ? AnimationDirection.SOUTH_WALK : AnimationDirection.SOUTH_STAND;
		else if(x > getPosition().x)
			dir = move ? AnimationDirection.EAST_WALK : AnimationDirection.EAST_STAND;
		else if(x < getPosition().x)
			dir = move ? AnimationDirection.WEST_WALK : AnimationDirection.WEST_STAND;
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
	
	public void killed() {
		List<Item> items = new LinkedList<Item>();
		for(EquipmentType e : equips)
			items.add(new Equipment(e));
		items.add(new Experience(getExp()));
		Truhe t = new Truhe(getPosition().x, getPosition().y, false, PlayState.getInstance().createTruhenBody(getPosition().x,getPosition().y), items.toArray(new Item[0]));
		
		PlayState.getInstance().addTruhe(t);
		
		PlayState.getInstance().addDrawable(t);
		markToDispose();
	}
	
	private List<EquipmentType> equips = new LinkedList<EquipmentType>();
	public void addLoot(int exp, EquipmentType...equipments) {
		this.exp = exp;
		for(EquipmentType e : equipments)
			equips.add(e);
	}
	
	public void addLoot(EquipmentType...equipments) {
		addLoot(0, equipments);
	}
	
	
}
