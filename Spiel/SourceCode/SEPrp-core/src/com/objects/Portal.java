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

@Author(name = "Bijan Shahbaz Nejad")


public class Portal {

	
	Texture textur=new Texture("grafiken/crystal.png");
	Sprite portal=new Sprite(textur);
	Vector3 position;
	Vector3 teleOrt;
	
	int teleportzähler[]=new int[]{0,0};
	
	int drehmoment=0;
	
	int benutzt=0;
	
	
	public Portal(int posX,int posyY,int teleX,int teleY){
		
		position=new Vector3(posX, posyY, 0);
		teleOrt=new Vector3(teleX, teleY, 0);
		
		
	}
	
	
	
	
	public void update(float dt){
	
	}
	public void render(SpriteBatch sb,Character c){
		if (c.getPosition().x>=position.x-10 && c.getPosition().x<=position.x+40 &&  c.getPosition().y<=position.y+40 && c.getPosition().y>=position.y-10 && benutzt<3) {
			drehmoment+=3;
			teleportzähler[0]+=1;
		portal.setColor(Color.GOLD);
		if (teleportzähler[0]==200 ) {
			c.getPosition().x=teleOrt.x;
		c.getPosition().y=teleOrt.y;
		benutzt++;
		}
		
		}
		else {
			portal.setColor(Color.SKY);
			drehmoment++;
			teleportzähler[0]=0;
		}
		if(benutzt==3){
			portal.setColor(Color.BLACK);
		}
		if (c.getPosition().x>=position.x-10 && c.getPosition().x<=position.x+40 &&  c.getPosition().y<=position.y+40 && c.getPosition().y>=position.y-10 && benutzt==3 && Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			Gdx.app.exit();
		}
		portal.setPosition(position.x,position.y);
		portal.setRotation(drehmoment);
		portal.draw(sb);
	}
	
	public void dispose(){
		this.dispose();
	}
}
