package com.character;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

public class Schuetze extends Character{
	
	private Vector3 position;
	
	private TextureRegion character;

	private Texture character1;
	
	

	
	public Schuetze(float x,float y,TextureRegion[][] sprite, TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body){
		super(x, y, sprite,  collisionLayer, attributes, body);


		position=new Vector3(x,y,0);

		

		skills = new ArrayList<Skill>();
		
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
