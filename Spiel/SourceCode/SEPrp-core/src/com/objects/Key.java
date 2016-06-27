package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.character.Character;

public class Key {
	Texture texturKeyBlack=new Texture("grafiken/KeyBlack.png");
	Texture texturKeyWhite=new Texture("grafiken/KeyWhite.png");
	Texture texturKeyGold=new Texture("grafiken/KeyGold.png");
	Vector3 positionBlack;
	Vector3 positionWhite;
	Vector3 positionGold;
	boolean blackaufgehoben;
	boolean whiteaufgehoben;
	boolean goldaufgehoben;
	KeyUI ui;
	public Key(int xBlack,int yBlack,int xWhite,int yWhite,int xGold,int yGold){
		ui=new KeyUI();
		positionBlack=new Vector3(xBlack, yBlack, 0);
		positionWhite=new Vector3(xWhite, yWhite, 0);
		positionGold=new Vector3(xGold, yGold, 0);
		blackaufgehoben=false;
		goldaufgehoben=false;
		whiteaufgehoben=false;
	}
	
	
	
	
	public void update(float dt){
	
	}
	public void render(SpriteBatch sb,Character c){
		if (!blackaufgehoben) {
			sb.draw(texturKeyBlack, positionBlack.x, positionBlack.y);
			if (Gdx.input.isKeyJustPressed(Keys.SPACE) && c.getPosition().x>positionBlack.x-40 && c.getPosition().x<positionBlack.x+40 && c.getPosition().y>positionBlack.y-40 && c.getPosition().y<positionBlack.y+40) {
				blackaufgehoben=true;
			}
		}
		if (!whiteaufgehoben ) {
			sb.draw(texturKeyWhite, positionWhite.x, positionWhite.y);
			if (Gdx.input.isKeyJustPressed(Keys.SPACE)&& c.getPosition().x>positionWhite.x-40 && c.getPosition().x<positionWhite.x+40 && c.getPosition().y>positionWhite.y-40 && c.getPosition().y<positionWhite.y+40) {
				whiteaufgehoben=true;
			}
		}
		if (!goldaufgehoben ) {
			sb.draw(texturKeyGold, positionGold.x, positionGold.y);
			if (Gdx.input.isKeyJustPressed(Keys.SPACE)&& c.getPosition().x>positionGold.x-40 && c.getPosition().x<positionGold.x+40 && c.getPosition().y>positionGold.y-40 && c.getPosition().y<positionGold.y+40) {
				goldaufgehoben=true;
			}
		}
		ui.Render(sb, goldaufgehoben, blackaufgehoben, whiteaufgehoben);
	}
	
	public void dispose(){
		this.dispose();
	}
}
