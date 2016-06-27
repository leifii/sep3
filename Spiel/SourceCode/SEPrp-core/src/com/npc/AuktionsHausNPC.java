package com.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.Character;
import com.mygdx.menu.AuktionshausState;
import com.mygdx.menu.GameStateManager;
import com.mygdx.menu.PlayState;

public class AuktionsHausNPC extends NPC{
	
	GameStateManager gsm;
	AuktionshausState auktionshaus;
	public AuktionsHausNPC(int x, int y, String source, String TEXT, Body body,GameStateManager gsmm,PlayState playstate) {
		super(x, y, source, TEXT, body);
gsm=gsmm;
auktionshaus=new AuktionshausState(gsm,playstate);
	}

	@Override
	public void render(PlayState ps, SpriteBatch sb, Rectangle Character, Character c) {
		sb.draw(NPCtexture,position.x,position.y);

		if (angesprochen && Gdx.input.isKeyJustPressed(Keys.SPACE) ) {
			angesprochen=false;
			gsm.push(auktionshaus);
			
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
