package com.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.character.Character;
import com.mygdx.menu.PlayState;

public class NPC {
	

		Vector3 position;
		Rectangle bounds;
		
		
		
		Texture NPCtexture;
		
		
		
		
		public NPC(int x,int y,String source){
			position=new Vector3(x, y, 0);
			bounds=new Rectangle(x, y, 32, 48);
			NPCtexture=new Texture(source);
			
		}
		


		public void render(PlayState ps,SpriteBatch sb,Rectangle Character,Character c){
			sb.draw(NPCtexture,position.x,position.y);
			if ( bounds.overlaps(Character)&& Gdx.input.isKeyJustPressed(Keys.SPACE)){
				
				
			}
			
		}
		
		public void dispose(){
			this.dispose();
		
		}
}
