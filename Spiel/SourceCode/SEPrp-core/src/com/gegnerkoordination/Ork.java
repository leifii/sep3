package com.gegnerkoordination;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ork extends Gegner {

	private static TextureRegion[][] animation;
	
	Ork(int x, int y, Attributes attributes, int exp) {
		super(x, y, animation, attributes, exp);
	}
	

}
