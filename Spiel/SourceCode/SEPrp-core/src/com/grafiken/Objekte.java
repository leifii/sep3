package com.grafiken;

import com.badlogic.gdx.graphics.Texture;

public class Objekte implements IObjekte{

	Texture[]skills;
	Texture[]items;
	Texture[] chests;
	public Objekte(){
		skills=new Texture[]{new Texture("grafiken/Energiekugel.png"),new Texture("grafiken/Energiekugel1.png"),new Texture("grafiken/Energiekugel2.png"),new Texture("grafiken/Energiekugel3.png"),new Texture("grafiken/fire.png"),new Texture("grafiken/fire1.png"), new Texture("grafiken/fire2.png")};
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
