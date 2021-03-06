package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.character.Character;
import com.mygdx.game.Author;
import com.mygdx.menu.PlayState;
import com.npc.DialogNeu;

@Author(name = "Bijan Shahbaz Nejad")


public class Key {
	Texture texturKeyBlack=new Texture("grafiken/keyBlack.png");
	Texture texturKeyWhite=new Texture("grafiken/keyWhite.png");
	Texture texturKeyGold=new Texture("grafiken/keyGold.png");
	Vector3 positionBlack;
	Vector3 positionWhite;
	Vector3 positionGold;
	boolean blackaufgehoben;
	boolean whiteaufgehoben;
	boolean goldaufgehoben;
	public boolean alle;
	KeyUI ui;
	int mapb,mapw,mapg;
	public Key(int xBlack,int yBlack,int xWhite,int yWhite,int xGold,int yGold,PlayState ps,int mapBlack,int mapWhite,int mapGold){
		ui=new KeyUI(ps);
		positionBlack=new Vector3(xBlack, yBlack, 0);
		positionWhite=new Vector3(xWhite, yWhite, 0);
		positionGold=new Vector3(xGold, yGold, 0);
		blackaufgehoben=false;
		goldaufgehoben=false;
		whiteaufgehoben=false;
		mapb=mapBlack;
		mapw=mapWhite;
		mapg=mapGold;
		alle=false;
	}
	
	
	public void update(float dt){
	
	}
	public void render(SpriteBatch sb,Character c){
		if (!blackaufgehoben && c.getMapIndex()==mapb) {
			sb.draw(texturKeyBlack, positionBlack.x, positionBlack.y);
			if (Gdx.input.isKeyJustPressed(Keys.SPACE) && c.getPosition().x>positionBlack.x-40 && c.getPosition().x<positionBlack.x+40 && c.getPosition().y>positionBlack.y-40 && c.getPosition().y<positionBlack.y+40) {
				blackaufgehoben=true;
				c.setBlackKeyStatus(true); // Zur Speicherung der Keystati --Dom--
			}
		}
		if (!whiteaufgehoben && c.getMapIndex()==mapw ) {
			sb.draw(texturKeyWhite, positionWhite.x, positionWhite.y);
			if (Gdx.input.isKeyJustPressed(Keys.SPACE)&& c.getPosition().x>positionWhite.x-40 && c.getPosition().x<positionWhite.x+40 && c.getPosition().y>positionWhite.y-40 && c.getPosition().y<positionWhite.y+40) {
				whiteaufgehoben=true;
//				new DialogNeu(new String[]{})
				c.setWhiteKeyStatus(true); // Zur Speicherung der Keystati --Dom--
			}
		}
		if (!goldaufgehoben && c.getMapIndex()==mapg ) {
			sb.draw(texturKeyGold, positionGold.x, positionGold.y);
			if (Gdx.input.isKeyJustPressed(Keys.SPACE)&& c.getPosition().x>positionGold.x-40 && c.getPosition().x<positionGold.x+40 && c.getPosition().y>positionGold.y-40 && c.getPosition().y<positionGold.y+40) {
				goldaufgehoben=true;
				c.setGoldKeyStatus(true); // Zur Speicherung der Keystati --Dom--
			}
		}
		ui.Render(sb, goldaufgehoben, blackaufgehoben, whiteaufgehoben);
		if (blackaufgehoben && whiteaufgehoben &&  goldaufgehoben) {
			alle=true;
		}
	}
	
	public void dispose(){
		this.dispose();
	}
	
	// Zur Speicherung der Keystati --Dom--
	public void setBlackKeyStatus(boolean blackKeyRecieved){
		this.blackaufgehoben = blackKeyRecieved;
	}
	public void setGoldKeyStatus(boolean goldKeyRecieved){
		this.goldaufgehoben = goldKeyRecieved;
	}
	public void setWhiteKeyStatus(boolean whiteKeyRecieved){
		this.whiteaufgehoben = whiteKeyRecieved;
	}
}
