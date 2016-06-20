package com.npc;

import java.awt.Font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.character.Character;
import com.mygdx.menu.PlayState;

public class Dialog  {
	
	Vector3 position;
	Texture Dialogfenster;
	
	String text;
	BitmapFont font;
	
	Dialog(String tekst,int x,int y,String source){
		text=TextAnpassen(tekst);
		font= new BitmapFont(Gdx.files.internal("white.fnt"));
		position=new Vector3(x, y, 0);
		Dialogfenster=new Texture(source);
	}

	public String TextAnpassen(String text){
		String neuerText="";
		char TEXT[]=text.toCharArray();
		for (int i = 0; i < TEXT.length; i++) {
			if (i>0 && i%30==0) {
				neuerText+="\n";
			}
			neuerText+=TEXT[i];
			///////////////////////////////////////JETZT NOCH MIT LEERZEICHEN BEARBEITEN !!!!!!!///////////////////
		}
		return neuerText;
	}

	public void render(PlayState ps,SpriteBatch sb,Rectangle Character,Character c,boolean geöffnet){
if (geöffnet==true) {
					sb.draw(Dialogfenster,position.x,position.y);
	
	font.draw(sb, text, position.x+5, position.y+Dialogfenster.getHeight()-5);
		}
		else{
			
	}
		
	}
	
	public void dispose(){
		this.dispose();
	}
}

