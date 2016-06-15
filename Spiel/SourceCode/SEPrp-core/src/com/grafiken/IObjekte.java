package com.grafiken;

import com.badlogic.gdx.graphics.Texture;

public interface IObjekte {
	
	public Texture getSkill(int index);
	public Texture getItem(int index);
	public Texture getChest(boolean opened);
}
