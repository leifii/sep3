package com.character;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Krieger extends Character{
	
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
	
	public Krieger(int x,int y,TextureRegion[][] sprite,float speed){
		super(x, y, sprite, speed);
		System.out.println(sprite);
		laufspeed=speed;
		position=new Vector3(x,y,0);
		
		skills = new ArrayList<skill>();
		skills.add(new skill(this.getPosition().x, this.getPosition().y, 200, 0, 1, 1, 1, 0, 2, 1, g.getSkill(6), false));
	}
	
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		super.update(dt);
	}

	@Override
	public Vector3 getPosition() {
		// TODO Auto-generated method stub
		return super.getPosition();
	}

//	@Override
//	public Texture getTexture() {
//		// TODO Auto-generated method stub
//		return super.getTexture();
//	}

//	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	

}
