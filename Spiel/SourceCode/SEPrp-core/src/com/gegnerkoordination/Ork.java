package com.gegnerkoordination;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Ork extends Gegner {

	private static TextureRegion[][] animation;
	
	Ork(int x, int y, Attributes attributes, int exp, TiledMapTileLayer[] collisionLayer) {
		super(x, y, animation, attributes, exp, collisionLayer);
	}
	

}
