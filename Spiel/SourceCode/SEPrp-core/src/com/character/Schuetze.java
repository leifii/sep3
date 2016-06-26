package com.character;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.menu.PlayState;

public class Schuetze extends Character{
	
	private Vector3 position;
	
	private TextureRegion character;

	private Texture character1;
	
	

	
	public Schuetze(float x,float y,TextureRegion[][] sprite, TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body){
		super(x, y, sprite,  collisionLayer, attributes, body);


		position=new Vector3(x,y,0);

		

		setSkills(new ArrayList<Skill>());
			//		x-Position, y-Position, lvl, dmg, dmgfaktor, cd, cdfaktor, speed, lifeTime, bild, buff, button
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,1,3,1,1,3,g.getSkill(12), false, 1, 0, this, 10, collisionLayer)); //eigentlich 3-fach pfeil
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,10,1,1,3,g.getSkill(8), true, 2, 0, this, 1, collisionLayer));	//heal over time
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,20,1,10,1,1,20,g.getSkill(0), false, 3, 0, this, 10, collisionLayer));	//falle
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,100,1,45,1,1,3,g.getSkill(13), false, 4, 0, this, 10, collisionLayer));	//großer pfeil
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
