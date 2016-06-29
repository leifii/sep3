package com.character;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Author;

@Author(name = "Bijan Nejad" "Bardia Asemi-Soloot")

public class Schurke extends Character{
	
	private Vector3 position;
	
	private TextureRegion character;

	private Texture character1;

	public Schurke(float x,float y,TextureRegion[][] sprite, TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body){
		super(x, y, sprite,  collisionLayer, attributes, body, Rolle.Spieler);

		position=new Vector3(x,y,0);
		
		
		setSkills(new ArrayList<Skill>());
		//		x-Position, y-Position, lvl, dmg, dmgfaktor, cd, cdfaktor, speed, lifeTime, bild, buff, button, helpNr, character, radius, collision
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,1,1,1,3,g.getSkill(14), false, 0, 0, this, 10, collisionLayer));	//auto-attack
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,3,1,1,1,g.getSkill(14), false, 1, 0, this, 10, collisionLayer));// 3-fach dolch
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,3,1,0.9f,1.5f,g.getSkill(14), false, 1, 0, this, 10, collisionLayer)); 
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,3,1,0.8f,2,g.getSkill(14), false, 1, 0, this, 10, collisionLayer));
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,1,1,2,1,1,10,g.getSkill(10), true, 2, 0, this, 1, collisionLayer));	//unsichtbar 
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,1,3,1,1,3,g.getSkill(15), false, 3, 0, this, 10, collisionLayer));	//giftdolchwurf
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,1,15,1,1,3,g.getSkill(14), false, 4, 1, this, 10, collisionLayer));	//dolchfächer 
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,1,15,1,1,3,g.getSkill(14), false, 4, 2, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,1,15,1,1,3,g.getSkill(14), false, 4, 3, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,1,15,1,1,3,g.getSkill(14), false, 4, 4, this, 10, collisionLayer));
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
