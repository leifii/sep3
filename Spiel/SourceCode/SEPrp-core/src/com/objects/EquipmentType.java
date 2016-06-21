package com.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.character.Attributes;
import com.grafiken.Objekte;

public enum EquipmentType {
//											  STR,INT,DEX,STA,ATK,DEF,AS,MS
	Holzschwert(ItemType.Waffe, 10, 2, 			2, 	0, 	0,	0, 	2, 0, 0, 0,		0, 0),
	Lederhelm(ItemType.Helm, 5, 2, 				0, 	3, 	0,	0, 	0, 0, 0, 0,		1, 1),
	Holzschild(ItemType.Schild, 5, 2, 			0, 	0, 	0,	0, 	0, 0, 3, 0,		0, 2),
	Lederschuh(ItemType.Schuhe, 5, 2, 			0, 	0, 	0,	0, 	0, 0, 0, 0.5f,	0,15),
	Lederrüstung(ItemType.Brustpanzer, 5, 2,	0, 	0, 	0,	0, 	0, 3, 0, 0,		0, 5);
	
	/*
	Holzschwert, Lederhelm,	Holzschild,	Lederschuh,	Lederrüstung
	 */
	private Attributes attributes;
	private int value, rarity;
	private TextureRegion texture;
	private ItemType type;

	
	private EquipmentType(ItemType type, int value, int rarity,
			int STR, int INT, int DEX, int STA, int ATK, int DEF, int AS, float MS,
			int xTexture, int yTexture) {
		this.type = type;
		this.value = value;
		this.rarity = rarity;
		attributes = new Attributes(STR, INT, DEX, STA, ATK, DEF, AS, MS);
		texture = Objekte.ITEMS2[xTexture][yTexture];
	}
	
	public Attributes getAttributes() {
		return attributes;
	}

	public ItemType getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getRarity() {
		return rarity;
	}

	public TextureRegion getTextureRegion() {
		return texture;
	}
	
}
