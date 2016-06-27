package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.menu.PlayState;


public class KeyUI {
	PlayState ps;
	Texture key[];
	public KeyUI(PlayState playstate) {
		key=new Texture[]{new Texture("grafiken/keyEmptyBig.png"),new Texture("grafiken/keyEmptyBig.png"),new Texture("grafiken/keyEmptyBig.png")};
		ps=playstate;
	}
	public void Render(SpriteBatch sb, boolean foundGold,boolean foundBlack,boolean foundWhite){
	sb.draw(key[0],ps.c.position.x+100 , ps.c.position.y+100);	
	sb.draw(key[1], Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()/2);	
	sb.draw(key[2], Gdx.graphics.getWidth()/2+200, Gdx.graphics.getHeight()/2);	
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
