package com.character;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Author;



@Author(name = "Bijan Nejad , Bardia Asemi-Soloot")


public class Magier extends Character{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	transient private Vector3 position;
	
	transient private TextureRegion character;

	transient private Texture character1;

	public Magier(float x,float y,TextureRegion[][] sprite,TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body){
		super(x, y, sprite, collisionLayer, attributes, body, Rolle.Spieler);
		System.out.println(sprite);
		position=new Vector3(x,y,0);

		

		setSkills(new ArrayList<Skill>());
//		x-Position, y-Position, lvl, dmg, cd, cdfaktor, speed, lifeTime, bild, buff, button, helpNr, character, radius, collision
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,1,1,3,g.getSkill(1), false, 0, 0, this, 10, collisionLayer));	//auto-attack
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,3,1,1,3,g.getSkill(6), false, 1, 0, this, 10, collisionLayer));	//feuerball
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,40,15,1,1,2.5f,g.getSkill(9), true, 2, 0, this, 1, collisionLayer));	//eisschild
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,20,5,1,1,3,g.getSkill(2), false, 3, 0, this, 10, collisionLayer));	//eis/wasserball
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,100,30,1,1,3,g.getSkill(17), false, 4, 0, this, 15, collisionLayer));	//geist
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1, 1, 0, 1, 1, 2, g.getSkill(19), true, 5, 1, this, 1, collisionLayer)); 	//lvlup

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
