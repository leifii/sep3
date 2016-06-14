package com.character;



import java.util.ArrayList;

import com.android.sdklib.devices.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.menu.InventoryState;
import com.mygdx.menu.MapState;
import com.mygdx.menu.PauseState;
import com.mygdx.menu.SkillState;
import com.grafiken.*;

public class Character {


	private ArrayList<skill> skills;
	
	private Vector3 position;
	
	private TextureRegion character;
	private Texture character1;
	
	float laufspeed;
	int STR;
	int INT;
	int STA;
	int DEX;
	
	int MaxHP;
	int Angriff;
	int Verteidigung;
	int AtkSpeed;
	
	int exp;
	int neededexp;
	int level;
	
	float hitcd;
	
	
/*	public Character (int x,int y,String sprite,float speed){
		laufspeed=speed;
		position=new Vector3(x,y,0);
	    character1=new Texture(sprite);    
	}*/
	
	public Character (int x,int y,TextureRegion sprite,float speed, ArrayList<skill> skills, float hitcd){
		//this.hitcd = skills.gethitcd;
		this.skills = skills;
		laufspeed=speed;
		position=new Vector3(x,y,0);
		character=sprite;
		level=1;
		exp=0;
		neededexp=100;
		
		 STR=1;
		 INT=1;
		 STA=1;
		 DEX=1;
		 
		 Angriff=1;
		 Verteidigung=1;
		 AtkSpeed=1;
	}
	
	public float getLaufspeed() {
		return laufspeed;
	}

	public void setLaufspeed(float laufspeed) {
		this.laufspeed = laufspeed;
	}

	public int getSTR() {
		return STR;
	}

	public void setSTR(int sTR) {
		STR = sTR;
	}

	public int getINT() {
		return INT;
	}

	public void setINT(int iNT) {
		INT = iNT;
	}

	public int getSTA() {
		return STA;
	}

	public void setSTA(int sTA) {
		STA = sTA;
	}

	public int getDEX() {
		return DEX;
	}

	public void setDEX(int dEX) {
		DEX = dEX;
	}

	public int getMaxHP() {
		return MaxHP;
	}

	public void setMaxHP(int maxHP) {
		MaxHP = maxHP;
	}

	public int getAngriff() {
		return Angriff;
	}

	public void setAngriff(int angriff) {
		Angriff = angriff;
	}

	public int getVerteidigung() {
		return Verteidigung;
	}

	public void setVerteidigung(int verteidigung) {
		Verteidigung = verteidigung;
	}

	public int getAtkSpeed() {
		return AtkSpeed;
	}

	public void setAtkSpeed(int atkSpeed) {
		AtkSpeed = atkSpeed;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getNeededexp() {
		return neededexp;
	}

	public void setNeededexp(int neededexp) {
		this.neededexp = neededexp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public void expSammeln(int Monsterexp,boolean monsterkilled){
		//if monsterkilled
		//exp+=Monsterexp;
		if (exp>=neededexp) {
			levelup();
			neededexp+=neededexp*(1.5f);
		}
		
	}
	public void levelup(){
		level++;
	}
	
	public void update(float dt){
		if (Gdx.input.isKeyPressed(Keys.W)) {
			position.y+=2*laufspeed;
			if (Gdx.input.isKeyPressed(Keys.A)) {
				position.y-=laufspeed*(1/Math.sqrt(2));
				position.x-=2*laufspeed;
				position.x+=laufspeed*(1/Math.sqrt(2));
			}
			else if (Gdx.input.isKeyPressed(Keys.D)) {
				position.y-=laufspeed*(1/Math.sqrt(2));
				position.x+=2*laufspeed;
				position.x-=laufspeed*(1/Math.sqrt(2));
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.S)) {
			position.y-=2*laufspeed;
			if (Gdx.input.isKeyPressed(Keys.A)) {
				position.y+=laufspeed*(1/Math.sqrt(2));
				position.x-=2*laufspeed;
				position.x+=laufspeed*(1/Math.sqrt(2));
			}
			else if (Gdx.input.isKeyPressed(Keys.D)) {
				position.y+=laufspeed*(1/Math.sqrt(2));
				position.x+=2*laufspeed;
				position.x-=laufspeed*(1/Math.sqrt(2));
			}
		}
			else if (Gdx.input.isKeyPressed(Keys.A)) {
			position.x-=2*laufspeed;
		
		}
		else if (Gdx.input.isKeyPressed(Keys.D)) {
			position.x+=2*laufspeed;
	
		}
		
	
	}
	public Vector3 getPosition(){
		return position;
	}
	public TextureRegion getTextureRegion(){
		return character;
	}
	public Texture getTexture(){
		return character1;
	}
	public void dispose(){
//		character.dispose();
	}
	
	
	/*public void angriff(){
	
		if(skills.gethitcd() == 0)
			return;
		
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
