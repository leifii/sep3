package com.objects;

import com.mygdx.game.Author;

@Author(name = "Sabiha Can?")


public enum ItemType {

	Helm(true), Waffe(true), Handschuh(true), Brustpanzer(true), Schuhe(true), Trank, 
	
	//Helper für AbstractString
	Heal, Experience, Gold, Schaden;

	private boolean equipable;
	
	ItemType(boolean e) {
		this.equipable = e;
	}
	
	ItemType() {
		this(false);
	}
	
	public boolean isEquipable() {
		return equipable;
	}
	
}
