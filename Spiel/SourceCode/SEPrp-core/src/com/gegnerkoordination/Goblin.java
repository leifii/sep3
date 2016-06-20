package com.gegnerkoordination;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.character.Attributes;

public class Goblin extends Gegner {

	private static TextureRegion[][] animation;
	
	Goblin(int x, int y, Attributes attributes, int exp, TiledMapTileLayer[] collisionLayer) {
		super(x, y, animation, attributes, exp, collisionLayer);
		// TODO Auto-generated constructor stub
	}
	
}
