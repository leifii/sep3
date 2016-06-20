package com.objects;

public class Gold extends AbstractStringItem {

	public Gold(int value) {
		super(ItemType.Gold, value, Integer.toString(value) + " G");
	}


}
