package com.gegnerkoordination;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.AnimationDirection;
import com.character.Attributes;
import com.character.Character;
import com.character.Rolle;
import com.character.Skill;
import com.mygdx.game.Author;
import com.mygdx.menu.PlayState;
import com.objects.Equipment;
import com.objects.EquipmentType;
import com.objects.Experience;
import com.objects.Item;
import com.objects.Truhe;

@Author(name = "?Dilara Güler?")


//TODO abstract class
public class Gegner extends Character {

	private int exp;
	protected TextureRegion currentFrame;
	protected float time;
	private Rectangle sight;
	
	public Gegner (int x,int y, TextureRegion[][] animation, TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body){
		super(x,y,animation,collisionLayer, attributes, body, Rolle.Gegner);
		
		//================TMP===============
		exp = 20;
		setMaxHP(100);
		setCurrentHP(getMaxHP());
		setSkills(new ArrayList<Skill>());
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,1,2,1,1,3,g.getSkill(18), false, 3, 0, this, 10, collisionLayer));	//axtwurf
	}
	
	//Konstruktor ohne Animation für Schleim und andere Gegner
	public Gegner (int x,int y, int width, int height, TiledMapTileLayer[] collisionLayer, int exp, Attributes attributes, Body body){
		super(x,y,width,height,collisionLayer, attributes, body, Rolle.Gegner);
		
		//================TMP===============
		this.exp = exp;
		setMaxHP(100);
		setCurrentHP(getMaxHP());
		setSkills(new ArrayList<Skill>());
	}
	
	public void update(float dt) {
		getBounds().setPosition(this.getPosition().x,this.getPosition().y);
		time += dt;
		currentFrame = getAnimation().getKeyFrame(time);
		
		int dicke = 60;
		int weite = 500;
		
		float x = getPosition().x - getBounds().width / 2;
		float y = getPosition().y - getBounds().height / 2;
		float w = 20;
		float h = 20;

		switch(getRichtung().getOrientation()) {
		case 'n': y += getBounds().height; w = dicke; h = weite; break;
		case 'e': x += getBounds().width; w = weite; h = dicke * 2; break;
		case 's': y -= weite - getBounds().height; w = dicke; h = weite; break;
		case 'w': x -= weite - getBounds().width; w = weite; h = dicke * 2; break;	
		}
		
		sight = new Rectangle(x, y, w, h);
		
		for (int i = 0; i < getSkills().size(); i++) {
			getSkills().get(i).update(dt, this.getPosition().x, this.getPosition().y);

			getSkills().get(i).direction(this);
			// getSkills().get(i).buffed(this);
		}
		
	}
	
	public void draw(SpriteBatch sb){
		sb.draw(currentFrame, getPosition().x, getPosition().y);
		for (int i = 0; i < getSkills().size(); i++) {
			getSkills().get(i).draw(sb);
		}
	}
	
	public void follow(Character c) {
		goToPosition(c.getPosition(), !getBounds().overlaps(c.getBounds()));
		
		if(inSight(c))
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
		for(Skill s : getSkills()) {
			s.activateProjectile(getPosition().x, getPosition().y);
		}
	}
	
	public boolean inSight(Character c) {
		if(sight == null)
			return false;
		return sight.overlaps(c.getBounds());
	}
	
	public void killed() {
		for(Skill s : getSkills())
			s.setAlive(false);
		
		List<Item> items = new LinkedList<Item>();
		for(EquipmentType e : equips)
			items.add(new Equipment(e));
		
		Truhe t = new Truhe(getPosition().x, getPosition().y, false, PlayState.getInstance().createTruhenBody(getPosition().x,getPosition().y), items.toArray(new Item[0]));

		PlayState.getInstance().addTruhe(t);
		PlayState.getInstance().getPlayer().expSammeln(exp);
		PlayState.getInstance().addDrawable(t);
		markToDispose();
	}
	
	private List<EquipmentType> equips = new LinkedList<EquipmentType>();

	
	public void addLoot(EquipmentType...equipments) {
		for(EquipmentType e : equipments)
			equips.add(e);
	}
	
	
}
