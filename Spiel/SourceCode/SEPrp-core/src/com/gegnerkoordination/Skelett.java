package com.gegnerkoordination;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.Attributes;

public class Skelett extends Gegner {

	private static final long serialVersionUID = -3846155411884336598L;

	public Skelett(int x, int y, TextureRegion[][] animation, TiledMapTileLayer[] collisionLayer,
			Attributes attributes, Body body) {
		
		super(x, y, animation, collisionLayer, attributes, body);
	}
	
	private static void ani() {
		
	}



}
