package com.grafiken;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface ICharacter {
	public void render(SpriteBatch sb);
	
	public TextureRegion getTextureRegion(int index); //returned einen bestimmten Charakter z.B.0=Krieger,1=Mage
	public Sprite getSprite(int index);
	
	
	public TextureRegion[][] getAnimation(int index); //returned ein Array für einen bestimmten Charakter(index, bisher nur 0)
														//Das returnte Array ist zweidimensional und enthält für jede Reihe(erster Index)4 Animationen
														//jede Reihe stellt eine Bewegungsrichtung dar
	public Texture getGegner(int index);
}
