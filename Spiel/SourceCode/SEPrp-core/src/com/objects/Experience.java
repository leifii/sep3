package com.objects;

import com.mygdx.game.Author;

@Author(name = "???")


public class Experience extends AbstractStringItem {

	public Experience(int value) {
		super(ItemType.Experience, value, Integer.toString(value) + " EXP");
		// TODO Auto-generated constructor stub
	}

}
