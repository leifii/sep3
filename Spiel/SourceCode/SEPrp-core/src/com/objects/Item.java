package com.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.character.IDrawable;
import com.mygdx.game.Author;

@Author(name = "Sabiha Can?")


public abstract class Item implements IDrawable {

	private final String NAME;
	private final ItemType TYPE;
	private int value;
	private int rarity;
	private TextureRegion texture;
	private String description;
	
	public Item(String name, ItemType type, int value, int rarity, TextureRegion texture){
		NAME = name;
		TYPE = type;
		this.value = value;
		this.rarity = rarity;
		this.texture = texture;
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
	
	public TextureRegion getTextureRegion() {
		return texture;
	}
	
	protected void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		if(description == null)
			return getNAME() + " vom Typ " + getType();
		return description;
	}
	
	public String getPriceAsString() {
		return "(" + value + " G)";
	}
}
