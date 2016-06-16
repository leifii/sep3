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
		
		Dialog Dialog;
		
		Texture NPCtexture;
		boolean angesprochen=false;
		
		
		
		public NPC(int x,int y,String source){
			position=new Vector3(x, y, 0);
			bounds=new Rectangle(x, y, 32, 48);
			NPCtexture=new Texture(source);
			Dialog=new Dialog(x+500,y+200 , "dialogfenster1.png");
		}
		
		
		

		public void render(PlayState ps,SpriteBatch sb,Rectangle Character,Character c){
			sb.draw(NPCtexture,position.x,position.y);

			if (Character.overlaps(bounds) && Gdx.input.isKeyJustPressed(Keys.SPACE) && angesprochen==false) {
			
				angesprochen=true;
			}
			else if (angesprochen && Gdx.input.isKeyJustPressed(Keys.SPACE) || Character.overlaps(bounds)==false) {
				angesprochen=false;
			}
			
				Dialog.render(ps, sb, Character, c, angesprochen);
				
				
		
		}
		
		public void dispose(){
			this.dispose();
		
		}
}