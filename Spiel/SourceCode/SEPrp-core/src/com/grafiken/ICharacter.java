package com.grafiken;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface ICharacter {
	public void render(SpriteBatch sb);
	
	public TextureRegion getTextureRegion(int index);
	public Sprite getSprite(int index);
}
