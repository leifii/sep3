package com.character;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

public class Schurke extends Character{
	
	private Vector3 position;
	
	private TextureRegion character;

	private Texture character1;

	public Schurke(float x,float y,TextureRegion[][] sprite, TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body){
		super(x, y, sprite,  collisionLayer, attributes, body);

		position=new Vector3(x,y,0);
		
		
		skills = new ArrayList<Skill>();
		//		x-Position, y-Position, lvl, dmg, dmgfaktor, cd, cdfaktor, speed, lifeTime, bild, buff, button
		skills.add(new Skill(this.getPosition().x, this.getPosition().y, 1,1,1,2,1,1,3,g.getSkill(3), false, 1, 0));
		skills.add(new Skill(this.getPosition().x, this.getPosition().y, 1,1,1,2,1,1,3,g.getSkill(0), true, 2, 0));
		skills.add(new Skill(this.getPosition().x, this.getPosition().y, 1,1,1,2,1,1,3,g.getSkill(6), false, 3, 0));
		skills.add(new Skill(this.getPosition().x, this.getPosition().y, 1,5,1,2,1,1,3,g.getSkill(7), false, 4, 0));
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
