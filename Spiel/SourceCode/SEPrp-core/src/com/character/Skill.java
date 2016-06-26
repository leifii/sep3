package com.character;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.menu.PlayState;

public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int lvl;
	int dmg;
	int dmgfaktor;
	float cdnow;
	float cd;
	int cdfaktor;

	public AnimationDirection direction;
	
	private Body body;
	private int radius;
	private TiledMapTileLayer[] collisionLayer;

	private transient Texture bild;
	private float lifeTime;
	private float lifeTimer;

	private float x;
	private float y;

	protected float dx; 	
	protected float dy;
	
	protected float speed;
	
	protected int button;			//auf welcher taste der skill liegt
	private int position;
	protected int width;
	protected int height;

	protected boolean look;
	
	protected Character c;

	private boolean remove;
	private boolean alive;

	//protected boolean locked;
	protected boolean buff;
	protected int helpNr;
	

	public Skill(float x, float y, int lvl, int dmg, int dmgfaktor, int cd, int cdfaktor, float speed, float lifeTime, 
			Texture bild, boolean buff, int button, int helpNr, Character c, int radius, TiledMapTileLayer[] collisionLayer /*boolean locked*/) {
		this.setX(x);
		this.setY(y);
		this.lvl = lvl;
		this.dmg = dmg;
		this.dmgfaktor = dmgfaktor;
		this.cd = cd;
		this.cdfaktor = cdfaktor;
		this.speed = speed;
		this.lifeTime = lifeTime;
		this.bild = bild;
		this.buff = buff;
		this.button = button;
		this.c = c;
		remove = false;
		cdnow = 0;
		this.setRadius(radius);
		this.collisionLayer=collisionLayer;
	}

	public float gethitcd() {
		return cdnow;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update(float dt, float xx, float yy) {
		handleInput(xx, yy);
		cdnow -= dt;					//cd nach Benutzung reduzieren
		
		lifeTimer += dt;
		if (lifeTimer > lifeTime) {
			remove = true;
			setAlive(false);
			
		}
		

			
		if (isAlive() == true && buff == false){			//falls kein Buff, dann bewegen
			setX(getX() + dx * dt * speed);
			setY(getY() + dy * dt * speed);
		}
		else if(isAlive() == true && buff == true){			//falls Buff, dann auf Spielerposition halten
			setX(c.getPosition().x);
			setY(c.getPosition().y);
		}
		
		if(body!=null)
			body.setTransform(getX()+16, getY()+16, 0);
			
		if(isCellBlocked(x,y))
			alive=false;
	}

	

	public void handleInput(float x, float y) {
		if (cdnow < 0.1) {				//falls skill benutzbar
			if (button == 1){
			if (Gdx.input.isKeyPressed(Keys.NUM_1)) {	
				this.setX(x);
				this.setY(y);
				cdnow = cd;
				lifeTimer = 0;
				setAlive(true);
				if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) {   //richtung anpassen
					dx = 0 * speed;
					dy = -300 * speed;
				} else if (direction == AnimationDirection.WEST_WALK || direction == AnimationDirection.WEST_STAND) {
					dx = -300 * speed;
					dy = 0 * speed;
				} else if (direction == AnimationDirection.EAST_WALK || direction == AnimationDirection.EAST_STAND) {
					dx = 300 * speed;
					dy = 0 * speed;
				} else if (direction == AnimationDirection.NORTH_WALK || direction == AnimationDirection.NORTH_STAND) {
					dx = 0 * speed;
					dy = 300 * speed;
				}
			} }
			if (button == 2){						//auf taste 2 sind alle buffs
				if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
					this.setX(x);
					this.setY(y);
					cdnow = cd;
					lifeTimer = 0;
					setAlive(true);
				}
			}
			if (button == 3){
				if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
					this.setX(x);
					this.setY(y);
					cdnow = cd;
					lifeTimer = 0;
					setAlive(true);
					if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) {   //richtung anpassen
						dx = 0 * speed;
						dy = -300 * speed;
					} else if (direction == AnimationDirection.WEST_WALK || direction == AnimationDirection.WEST_STAND) {
						dx = -300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.EAST_WALK || direction == AnimationDirection.EAST_STAND) {
						dx = 300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.NORTH_WALK || direction == AnimationDirection.NORTH_STAND) {
						dx = 0 * speed;
						dy = 300 * speed;
					} 
				}
			}
			if (button == 4){
				if (Gdx.input.isKeyPressed(Keys.NUM_4)) {
					this.setX(x);
					this.setY(y);
					cdnow = cd;
					lifeTimer = 0;
					setAlive(true);
					if (direction == AnimationDirection.SOUTH_WALK || direction == AnimationDirection.SOUTH_STAND) {   //richtung anpassen
						dx = 0 * speed;
						dy = -300 * speed;
					} else if (direction == AnimationDirection.WEST_WALK || direction == AnimationDirection.WEST_STAND) {
						dx = -300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.EAST_WALK || direction == AnimationDirection.EAST_STAND) {
						dx = 300 * speed;
						dy = 0 * speed;
					} else if (direction == AnimationDirection.NORTH_WALK || direction == AnimationDirection.NORTH_STAND) {
						dx = 0 * speed;
						dy = 300 * speed;
					} 
				}
			}
			if (button == 0){
				
			}
			}
			else remove = true;
		}

	

	public void draw(SpriteBatch s) {
		if (isAlive() == true) {
			s.draw(bild, getX(), getY());
		}
	}

	public void upgrade() {
		lvl += 1;
		dmg += 2 * dmgfaktor;
		cd -= 1 * cdfaktor;
	}

	public AnimationDirection direction(Character c) {
		return direction = c.getRichtung();
	}
	
	public void buffed(Character c){
		if(buff == true && isAlive() == true){
			if(c instanceof Krieger){
				System.out.println("buff");
			}
		}
	}

	private boolean isCellBlocked(float x, float y) {
		Cell cell;
		boolean blocked = false;
		for (int i = 0; i < collisionLayer.length; i++) {
			cell = collisionLayer[i].getCell((int) (x / collisionLayer[i].getTileWidth()),
					(int) (y / collisionLayer[i].getTileHeight()));
			blocked = blocked || (cell != null && cell.getTile() != null
					&& cell.getTile().getProperties().containsKey("blocked"));
		}
		return blocked;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
