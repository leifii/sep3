package com.grafiken;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Author;

@Author(name = "Angelo Soltner")

public interface ICharacter {
	public void render(SpriteBatch sb); //zeichnet den Charakter auf die Mitte des Bildes
	
	public TextureRegion getTextureRegion(int index); //returned einen bestimmten Charakter 0=Krieger,1=Mage,2=Schurke, 3=Bogenschütze
	public Sprite getSprite(int index);
	
	
	public TextureRegion[][] getAnimation(int index); //zweidimensionales Array ------Index 0=> 4*4 Animationen, Index 1=> 2*4 Animationen(links+rechts)
	public Texture getGegner(int index);
	public TextureRegion[][] getGegnerAnimation(int index); //returned Schleim für index 1 (Position 1,0 und 1,1 im Array)
	
	public TextureRegion[][] getAngriff(int index); //Array mit Angriffsbildern , 00=oben 01=rechts, 10=unten, 11=rechts (siehe Assets)
	public TextureRegion[][] getSchütze(int index); //einzelne Kollorierungen des Schützens
	public TextureRegion[][] getKrieger(int index); //einzelne Kollorierungen des Kriegers
	public TextureRegion[][] getMage(int index); //einzelne Kollorierungen des Magiers
	}
