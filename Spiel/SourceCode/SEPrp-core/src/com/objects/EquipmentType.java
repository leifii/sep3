package com.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.character.Attributes;
import com.grafiken.Objekte;
import com.mygdx.game.Author;

@Author(name = "Dilara Güler, Sabiha Can")


public enum EquipmentType {
//	ItemType, value, rarity						  STR,INT,DEX,STA,ATK,DEF,AS,MS
	Dolch(ItemType.Waffe, 10, 2, 					2, 	0, 	0,	0, 	2, 0, 0, 0,		8,15),
	Schwert(ItemType.Waffe, 15, 5, 					4, 	0, 	0,	0, 	2, 0, 0, 0,		2, 0),
	Stahlschwert(ItemType.Waffe, 25, 9, 			6, 	0, 	0,	0, 	2, 0, 0, 0,		5,15),
	
	Kupferhelm(ItemType.Helm, 5, 2, 				0, 	3, 	0,	0, 	0, 1, 0, 0,		5, 1),
	Eisenhelm(ItemType.Helm, 12, 4,					0, 	4, 	0,	0, 	0, 2, 0, 0,		3, 1),
	Stahlhelm(ItemType.Helm, 18, 5,					0, 	5, 	0,	0, 	0, 4, 0, 0,		1, 1),
	
	Lederhandschuh(ItemType.Handschuh, 5, 2, 		0, 	0, 	3,	0, 	0, 1, 0, 0,		1, 2),
	Eisenhandschuh(ItemType.Handschuh, 10, 4,		0, 	0, 	5,	0, 	1, 1, -1, 0,	3, 2),
	Stahlhandschuh(ItemType.Handschuh, 15, 6,		0, 	0, 	8,	0, 	2, 2, -2, 0,	5, 2),
	
	Stoffschuh(ItemType.Schuhe, 3, 1, 				0, 	0, 	0,	1, 	0, 0, 0, 0.5f,	3,13),
	Lederschuh(ItemType.Schuhe, 6, 3, 				0, 	0, 	0,	1, 	0, 0, 1, 1f,	4,13),
	Lederstiefel(ItemType.Schuhe, 10, 5,			0, 	0, 	2,	3, 	0, 0, 1, 1.5f,	0,15),
	
	Lederrüstung(ItemType.Brustpanzer, 8, 3,		1, 	0, 	0,	0, 	0, 3, 0, -0.3f,	0, 5),
	Kettenhemd(ItemType.Brustpanzer, 15, 6,			0, 	0, 	0,	0, 	0, 5, 0, -0.1f,	1, 6),
	Stahlrüstung(ItemType.Brustpanzer, 20, 9,		3, 	0, 	0,	0, 	0, 8, 0, -0.5f,	5, 0);
	
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
