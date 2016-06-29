package com.grafiken;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Author;

@Author(name = "???")

public interface IObjekte {
	
	public Texture getSkill(int index);		//0-3 Energiekugelvariationen; 4,5 Feuer;6 Feuerkugel
	public Texture getItem(int index);		//0 für Schwert, 1 für Münze
	public Texture getChest(boolean opened); //false für geschlossene Box, true für geöffnet
}
