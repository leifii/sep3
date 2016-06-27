package com.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.Character;
import com.mygdx.menu.PlayState;

public class AuktionsHausNPC extends NPC{

	public AuktionsHausNPC(int x, int y, String source, String TEXT, Body body) {
		super(x, y, source, TEXT, body);

	}

	@Override
	public void render(PlayState ps, SpriteBatch sb, Rectangle Character, Character c) {
		sb.draw(NPCtexture,position.x,position.y);
//
//		if (Character.overlaps(bounds) && Gdx.input.isKeyJustPressed(Keys.SPACE) && angesprochen==false) {
//		
//			angesprochen=true;
//		}
		if (angesprochen && Gdx.input.isKeyJustPressed(Keys.SPACE) ) {
			angesprochen=false;
			Gdx.app.exit();
			
		}
		
			if (c.getPosition().x<position.x+200 && c.getPosition().x>position.x-200 && c.getPosition().y<position.y+200 && c.getPosition().y>position.y-200) {
				angesprochen=true;
				Dialog.render(ps, sb, Character, c, angesprochen);
			}
			
	}

	
	
	@Override
	public void dispose() {
		super.dispose();
		
	}

}
