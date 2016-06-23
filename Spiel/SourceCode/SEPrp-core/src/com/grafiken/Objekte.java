package com.grafiken;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Objekte implements IObjekte{

	Texture[]skills;
	Texture[]items;
	Texture[] chests;
	final public static TextureRegion[][] ITEMS2 =  TextureRegion.split(new Texture("grafiken/items1.png"),32,32);
	public Objekte(){
		skills=new Texture[]{new Texture("grafiken/Energiekugel.png"),new Texture("grafiken/Energiekugel1.png"),new Texture("grafiken/Energiekugel2.png"),new Texture("grafiken/Energiekugel3.png"),new Texture("grafiken/Fire.png"),new Texture("grafiken/Fire1.png"), new Texture("grafiken/Fire2.png"), new Texture("grafiken/aura.png")};
		chests=new Texture[]{new Texture("grafiken/Chest.png"),new Texture("grafiken/Chesto.png")};
		
	}
	
	@Override
	public Texture getSkill(int index) {
		
		return skills[index];
	}

	@Override
	public Texture getItem(int index) {
		items=new Texture[]{new Texture("grafiken/sword.png"),new Texture("grafiken/coin.png")};
		return null;
	}
	public Texture getChest(boolean opened){
		if(opened)
			return chests[1];
		else return chests[0];
	}

}
