package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Author;
import com.mygdx.menu.PlayState;

@Author(name = "Bijan Nejad")


public class KeyUI {
	PlayState ps;
	Texture key[];
	Sprite keys[];
	public KeyUI(PlayState playstate) {
		key=new Texture[]{new Texture("grafiken/keyEmptyBig.png"),new Texture("grafiken/keyEmptyBig.png"),new Texture("grafiken/keyEmptyBig.png")};
	keys=new Sprite[3];
		
		ps=playstate;
	}
	public void Render(SpriteBatch sb, boolean foundGold,boolean foundBlack,boolean foundWhite){
		for (int i = 0; i < key.length; i++) {
			keys[i]=new Sprite(key[i]);
		}
//	if (Gdx.input.isKeyJustPressed(Keys.K)) {
//		sb.draw(key[0], Gdx.graphics.getWidth()/2+200, Gdx.graphics.getHeight()/2+400);	
//		sb.draw(key[1], Gdx.graphics.getWidth()/2+300, Gdx.graphics.getHeight()/2+400);	
//		sb.draw(key[2], Gdx.graphics.getWidth()/2+400, Gdx.graphics.getHeight()/2+400);	
		
////	}
//	if(Gdx.input.isKeyJustPressed(Keys.P)){
//	sb.draw(key[0], Gdx.graphics.getWidth()/2+200, Gdx.graphics.getHeight()/2+400);	
//	sb.draw(key[1], Gdx.graphics.getWidth()/2+300, Gdx.graphics.getHeight()/2+400);	
//	sb.draw(key[2], Gdx.graphics.getWidth()/2+400, Gdx.graphics.getHeight()/2+400);	
//	}
	
	for (int i = 0; i < keys.length; i++) {
		
	
		keys[0].setPosition(ps.c.position.x-100-42, ps.c.position.y+40);
		keys[1].setPosition(ps.c.position.x-42, ps.c.position.y+40);
		keys[2].setPosition(ps.c.position.x+100-42, ps.c.position.y+40);
		keys[i].draw(sb);
	}
	
	
//		sb.draw(key[0],ps.c.position.y+300, ps.c.position.y+400);
//		sb.draw(key[1],ps.c.position.y+400, ps.c.position.y+400);
//		sb.draw(key[2],ps.c.position.y+500, ps.c.position.y+400);
	
	
	if (foundGold) {
		key[0]=new Texture("grafiken/keyGoldBig.png");
	}
	if (foundWhite) {
		key[1]=new Texture("grafiken/keyWhiteBig.png");
	}
	if (foundBlack) {
		key[2]=new Texture("grafiken/keyBlackBig.png");
	}
	
	
	}

public void dispose(){
	this.dispose();
}
}
