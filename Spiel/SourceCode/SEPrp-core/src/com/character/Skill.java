package com.character;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

	private transient Texture bild;
	private float lifeTime;
	private float lifeTimer;

	protected float x;
	protected float y;

	protected float dx; 	
	protected float dy;
	
	protected float speed;
	
	protected int button;			//auf welcher taste der skill liegt
	protected int position;
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
			Texture bild, boolean buff, int button, int helpNr, Character c /*boolean locked*/) {
		this.x = x;
		this.y = y;
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
			alive = false;
			
		}
		

			
		if (alive == true && buff == false){			//falls kein Buff, dann bewegen
			x += dx * dt * speed;
			y += dy * dt * speed;
		}
		else if(alive == true && buff == true){			//falls Buff, dann auf Spielerposition halten
			x = c.getPosition().x;
			y = c.getPosition().y;
		}
			

			


		}

	

	public void handleInput(float x, float y) {
		if (cdnow < 0.1) {				//falls skill benutzbar
			if (button == 1){
			if (Gdx.input.isKeyPressed(Keys.NUM_1)) {	
				this.x = x;
				this.y = y;
				cdnow = cd;
				lifeTimer = 0;
				alive = true;
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
				} } }
			if (button == 2){						//auf taste 2 sind alle buffs
				if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
					this.x = x;
					this.y = y;
					cdnow = cd;
					lifeTimer = 0;
					alive = true;
				}
			}
			if (button == 3){
				if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
					this.x = x;
					this.y = y;
					cdnow = cd;
					lifeTimer = 0;
					alive = true;
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
					this.x = x;
					this.y = y;
					cdnow = cd;
					lifeTimer = 0;
					alive = true;
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
		if (alive == true) {
			s.draw(bild, x, y);
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
		if(buff == true && alive == true){
			if(c instanceof Krieger){
				System.out.println("buff");
			}
		}
	}

}
