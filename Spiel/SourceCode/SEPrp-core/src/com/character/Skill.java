package com.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Skill {

	int lvl;
	int dmg;
	int dmgfaktor;
	float cdnow;
	float cd;
	int cdfaktor;
	
	private Texture bild;
	private float lifeTime;
	private float lifeTimer;
	
	protected float x;
	protected float y;
	
	protected float dx;	 //richtung in die der skill sich bewegt
	protected float dy;
	
	protected float speed;
	
	protected int width;
	protected int height;
	
	private boolean remove;
	private boolean alive;
	
	boolean locked;
	//boolean ready;
	
	
	public Skill(float x, float y, float dx, float dy, int lvl, int dmg, int dmgfaktor, int cdnow, int cd, int cdfaktor, Texture bild, boolean locked){
		this.lvl = lvl;
		this.dmg = dmg;
		this.dmgfaktor = dmgfaktor;
		this.cdnow = cdnow;
		this.cd = cd;
		this.cdfaktor = cdfaktor;
		this.bild = bild;
		this.locked = locked;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		lifeTime = 3;
		
	}
	
	
	
	public float gethitcd(){
		return cdnow;
	}
	
	
	public boolean shouldRemove(){
		return remove;
	}
	
	public void update(float dt, float xx, float yy){
		handleInput(xx, yy);
		cdnow -= dt;
		if(alive == true){
		x += dx * dt;
		y += dy * dt;
		
		
		System.out.println(cdnow);
				
		lifeTimer += dt;
		if(lifeTimer > lifeTime){
			remove = true;
			alive = false;
		}
		
		
		}
		
	}
	
	public void handleInput(float x, float y){
		if(cdnow < 0.1){
			if(Gdx.input.isKeyPressed(Keys.NUM_1)){
				this.x = x;
				this.y = y;
				cdnow = cd;
				lifeTimer = 0;
				alive = true;
				
			}
		}

		
		
	}
	
	
	public void draw(SpriteBatch s){
		if(alive == true){
		s.draw(bild,x,y);
		}
	}
	
	
	public void upgrade(){
		lvl += 1;
		dmg += 2 * dmgfaktor;
		cd -= 1*cdfaktor;
	}
	
	
	
	
	
	
	
}
