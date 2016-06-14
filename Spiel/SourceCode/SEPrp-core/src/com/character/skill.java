package com.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class skill {

	int lvl;
	int dmg;
	int dmgfaktor;
	int cdnow;
	int cd;
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
	
	boolean locked;
	//boolean ready;
	
	
	skill(int lvl, int dmg, int dmgfaktor, int cdnow, int cd, int cdfaktor, String bild, boolean locked){
		this.lvl = lvl;
		this.dmg = dmg;
		this.dmgfaktor = dmgfaktor;
		this.cdnow = cdnow;
		this.cd = cd;
		this.cdfaktor = cdfaktor;
		this.bild = new Texture(Gdx.files.internal(bild));
		this.locked = locked;
		
	}
	
	
	
	public float gethitcd(){
		return cdnow;
	}
	
	
	public boolean shouldRemove(){
		return remove;
	}
	
	public void update(float dt){
		x += dx * dt;
		y += dy * dt;
		
		lifeTimer += dt;
		if(lifeTimer > lifeTime){
			remove = true;
		}
	}
	
	public void upgrade(){
		lvl += 1;
		dmg += 2 * dmgfaktor;
		cd -= 1*cdfaktor;
	}
	
	public void cdtimer(){
		
	}
	
	
	
	
	
}
