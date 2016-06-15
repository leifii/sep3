package com.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.character.Character;
import com.mygdx.menu.PlayState;

public class Dialog {
	

	Vector3 position;

	
	
	
	Texture Dialogfenster;
	
	
	
	
	public Dialog(int x,int y,String source){
		position=new Vector3(x, y, 0);
		Dialogfenster=new Texture(source);
		
	}
	


	public void render(PlayState ps,SpriteBatch sb,Rectangle Character,Character c,boolean geöffnet){
		if (geöffnet==true) {
					sb.draw(Dialogfenster,position.x,position.y);

		}
		else{
			
		}
		
	}
	
	public void dispose(){
		this.dispose();
	
	}
}

