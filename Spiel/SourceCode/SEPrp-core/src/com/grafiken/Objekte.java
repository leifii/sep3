package com.grafiken;

import com.badlogic.gdx.graphics.Texture;

public class Objekte implements IObjekte{

	Texture[]skills;
	public Objekte(){
		skills=new Texture[]{new Texture("grafiken/Energiekugel.png"),new Texture("grafiken/Energiekugel1.png"),new Texture("grafiken/Energiekugel2.png"),new Texture("grafiken/Energiekugel3.png"),new Texture("grafiken/fire.png"),new Texture("grafiken/fire1.png")};
	}
	
	@Override
	public Texture getSkill(int index) {
		
		return skills[index];
	}

}
