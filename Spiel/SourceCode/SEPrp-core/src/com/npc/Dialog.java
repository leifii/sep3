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
import com.mygdx.game.Author;
import com.mygdx.menu.PlayState;

@Author(name = "Bijan Nejad")

public class Dialog  {
	
	Vector3 position;
	Texture Dialogfenster;
	
	String text;
	BitmapFont font;
	float geändert=1f;
	
	Dialog(String tekst,int x,int y,String source){
		text=TextAnpassen(tekst);
		font= new BitmapFont(Gdx.files.internal("white.fnt"));
		font.getData().setScale(geändert);
		position=new Vector3(x, y, 0);
		Dialogfenster=new Texture(source);
	}

	public String TextAnpassen(String text){
		String neuerText="";
		int zähler=0;
		int erlaubt=3;
		
	
		char TEXT[]=text.toCharArray();
		for (int i = 0; i < TEXT.length; i++) {
			if ((i%30>=20) && TEXT[i]==' ' ) {
				
				neuerText+="\n";
				zähler++;
			}
			else {
				neuerText+=TEXT[i];
			}
			
		
			if (zähler>erlaubt) {
				geändert-=0.01f;
				if (geändert<=0.5f) {
					geändert=0.5f;
				}
			}
	}
			
		
		
		return neuerText;
	}

	public void render(PlayState ps,SpriteBatch sb,Rectangle Character,Character c,boolean geöffnet){
if (geöffnet==true) {
					sb.draw(Dialogfenster,position.x,position.y);
	
	font.draw(sb, text, position.x+5, position.y+Dialogfenster.getHeight()-6);
		}
		else{
			
	}
		
	}
	
	public void dispose(){
		this.dispose();
	}
}

