package com.character;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Author;

@Author(name = "Bijan Nejad")

public class Krieger extends Character{
	
	private Vector3 position;
	
	private TextureRegion character;

	private Texture character1;

	public Krieger(float x,float y,TextureRegion[][] sprite, TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body){
		super(x, y, sprite, collisionLayer, attributes, body, Rolle.Spieler);
		System.out.println(sprite);
		position=new Vector3(x,y,0);

		setSkills(new ArrayList<Skill>());										
//		x-Position, y-Position, lvl, dmg, dmgfaktor, cd, cdfaktor, speed, lifeTime, bild, buff, button, helpNr, character, radius, collision
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,1,1,1,1,g.getSkill(7), true, 0, 0, this, 1, collisionLayer));		//auto-attack
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,1,3,1,1,3,g.getSkill(11), false, 1, 0, this, 10, collisionLayer));	//steinwurf
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,1,10,1,1,1.5f,g.getSkill(8), true, 2, 0, this, 1, collisionLayer));	//heal
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,1,2,1,1,3,g.getSkill(18), false, 3, 0, this, 10, collisionLayer));	//axtwurf
		
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,1,30,1,1,15,g.getSkill(7), true, 4, 0, this, 1, collisionLayer));	//berserker
		
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
	
//	public void handleInput(){
//		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
//			if(((boolean[]) body.getUserData())[0] && (richtung == AnimationDirection.NORTH_ATTACK)){
//				if(bounds)
//			}
//		}
//	}

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
