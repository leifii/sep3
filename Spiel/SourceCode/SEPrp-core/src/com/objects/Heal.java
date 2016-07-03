package com.objects;

public class Heal extends AbstractStringItem {

	public Heal(int value) {
		super(ItemType.Heal, value, "+" + value + " HP");
	}

}
