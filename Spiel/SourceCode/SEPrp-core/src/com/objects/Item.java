package com.objects;

public abstract class Item {

	private final String NAME;
	private final ItemType TYPE;
	private int value;
	private int rarity;
	
	public Item(String name, ItemType type, int value, int rarity){
		NAME = name;
		TYPE = type;
	}

	public String getNAME() {
		return NAME;
	}

	public int getValue() {
		return value;
	}

	public int getRarity() {
		return rarity;
	}

	public ItemType getType() {
		return TYPE;
	}
	
}
