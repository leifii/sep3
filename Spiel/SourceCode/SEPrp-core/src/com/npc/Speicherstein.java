package com.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.Character;
import com.mygdx.menu.PlayState;

public class Speicherstein extends NPC {

	public Speicherstein(int x, int y, String source, String TEXT, Body body) {
		super(x,y,source,TEXT,body);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void render(PlayState ps, SpriteBatch sb, Rectangle Character, Character c) {
		// TODO Auto-generated method stub
		sb.draw(NPCtexture,position.x,position.y);

		if (Character.overlaps(bounds) && Gdx.input.isKeyJustPressed(Keys.SPACE) && angesprochen==false ) {
		
			angesprochen=true;
		}
		
		
		
		else if (angesprochen && Gdx.input.isKeyJustPressed(Keys.SPACE) || Character.overlaps(bounds)==false) {
			angesprochen=false;
			
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.K) && angesprochen) {
				System.out.println("Speichern erfolgreich unter Spielstand " + de.SEPL.GameScore.GameScoreManagement.saveGameScore(c));
			
		}
			Dialog.render(ps, sb, Character, c, angesprochen);
			
			
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	
	
	
	
	
	
	
	
}
