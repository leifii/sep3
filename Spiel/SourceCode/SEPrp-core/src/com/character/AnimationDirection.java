package com.character;

import com.mygdx.game.Author;

@Author(name = "Dilara Güler, Sabiha Can")

public enum AnimationDirection {
	SOUTH_WALK('s'), WEST_WALK('w'), EAST_WALK('e'), NORTH_WALK('n'), 
	SOUTH_STAND('s'), NORTH_STAND('n'), EAST_STAND('e'), WEST_STAND('w'), 
	SOUTH_ATTACK('s'), WEST_ATTACK('w'), EAST_ATTACK('e'), NORTH_ATTACK('n');
	
	final private char orientation;
	
	private AnimationDirection(char orientation) {
		this.orientation = orientation;
	}
	
	public char getOrientation() {
		return orientation;
	}
}