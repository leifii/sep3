package com.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.character.Character;

public class Portal {

	
	Texture textur=new Texture("grafiken/crystal.png");
	Sprite portal=new Sprite(textur);
	Vector3 position;
	Vector3 teleOrt;
	
	int teleportzähler[]=new int[]{0,0};
	
	int drehmoment=0;
	

	
	
	public Portal(int posX,int posyY,int teleX,int teleY){
		
		position=new Vector3(posX, posyY, 0);
		teleOrt=new Vector3(teleX, teleY, 0);
		
		
	}
	
	
	
	
	public void update(float dt){
	
	}
	public void render(SpriteBatch sb,Character c){
		if (c.getPosition().x>=position.x-10 && c.getPosition().x<=position.x+40 &&  c.getPosition().y<=position.y+40 && c.getPosition().y>=position.y-10) {
			drehmoment+=3;
			teleportzähler[0]+=1;
		portal.setColor(Color.GOLD);
		if (teleportzähler[0]==200) {
			c.getPosition().x+=700;
		c.getPosition().y+=700;
		}
		
		}
		else {
			portal.setColor(Color.SKY);
			drehmoment++;
			teleportzähler[0]=0;
		}
		portal.setPosition(position.x,position.y);
		portal.setRotation(drehmoment);
		portal.draw(sb);
	}
	
//	int drehmoment=0;
//	int teleportzähler[]=new int[]{0,0};
//	private Texture portal=new Texture("grafiken/crystal.png");
//	
//	private Sprite Portale[]=new Sprite[]{(new Sprite(portal)),(new Sprite(portal))};
	
//	
//	
//	
//	
//	if (c.getPosition().x>=Portale[0].getX()-10 && c.getPosition().x<=Portale[0].getX()+40 &&  c.getPosition().y<=Portale[0].getY()+40 && c.getPosition().y>=Portale[0].getY()-10) {
//		drehmoment+=3;
//		teleportzähler[0]+=1;
//	Portale[0].setColor(Color.GOLD);
//	if (teleportzähler[0]==200) {
//		c.getPosition().x=Portale[1].getX()+70;
//	c.getPosition().y=Portale[1].getY();
//	}
//	
//	}
//	else {
//		Portale[0].setColor(Color.SKY);
//		drehmoment++;
//		teleportzähler[0]=0;
//	}
//	
//	
	
//
//	Portale[0].setPosition(2926,1000);
//	Portale[0].setRotation(drehmoment);
//	Portale[0].draw(sb);
}
