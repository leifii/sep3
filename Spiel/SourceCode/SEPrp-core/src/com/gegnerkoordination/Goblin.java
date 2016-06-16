package com.gegnerkoordination;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Goblin extends Gegner {

	private static TextureRegion[][] animation;
	
	Goblin(int x, int y, Attributes attributes, int exp) {
		super(x, y, animation, attributes, exp);
		// TODO Auto-generated constructor stub
	}
	
}
