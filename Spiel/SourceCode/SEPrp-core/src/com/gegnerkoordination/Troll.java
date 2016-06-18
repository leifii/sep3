package com.gegnerkoordination;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Troll extends Gegner {

	private static TextureRegion[][] animation;
	
	Troll(int x, int y, Attributes attributes, int exp, TiledMapTileLayer[] collisionLayer) {
		super(x, y, animation, attributes, exp, collisionLayer);
		// TODO Auto-generated constructor stub
	}

}
