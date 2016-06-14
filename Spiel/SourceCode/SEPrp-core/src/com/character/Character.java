package com.character;

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


	
	private Vector3 position;
	
	private TextureRegion character;
	private Texture character1;
	
	float laufspeed;
	int STR;
	int INT;
	int STA;
	
	int Angriff;
	int Verteidigung;
	int AtkSpeed;
	
	int exp;
	int neededexp;
	int level;
	
// Skills skills;
	
/*	public Character (int x,int y,String sprite,float speed){
		laufspeed=speed;
		position=new Vector3(x,y,0);
	    character1=new Texture(sprite);    
	}*/
	
	public Character (int x,int y,TextureRegion sprite,float speed){
		laufspeed=speed;
		position=new Vector3(x,y,0);
		character=sprite;
		level=1;
		exp=0;
		neededexp=100;
		 STR=1;
		 INT=1;
		 STA=1;
		
		 Angriff=1;
		 Verteidigung=1;
		 AtkSpeed=1;
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
}
