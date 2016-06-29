package com.objects;

import com.mygdx.game.Author;

@Author(name = "???")


public class Gold extends AbstractStringItem {

	public Gold(int value) {
		super(ItemType.Gold, value, Integer.toString(value) + " G");
	}


}
