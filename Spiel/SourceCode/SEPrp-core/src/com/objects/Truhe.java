package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.menu.PlayState;

public class Truhe {

	Vector3 position;
	Rectangle bounds;
	
	
	
	Texture Closed=new Texture("grafiken/Chest.png");
	Texture Open=new Texture("grafiken/Chesto.png");
	
	Texture Zustand[]=new Texture[]{Closed,Open};
	int i=0;
	
	public Truhe(int x,int y){
		position=new Vector3(x, y, 0);
		bounds=new Rectangle(x, y, 40, 40);
		
		
	}
	

//	public void Öffne(Rectangle Character){
//		if (Gdx.input.isKeyPressed(Keys.O)&& bounds.overlaps(Character)) {
//			geöffnet=true;
//		}
		
//	}
	public void render(PlayState ps,SpriteBatch sb,Rectangle Character){
		sb.draw(Zustand[i],position.x,position.y);
		if (Gdx.input.isKeyJustPressed(Keys.O)&& bounds.overlaps(Character)) {
			
		i++;
		if (i>0) {
			i=1;
		}
		}
		
	}
	
	public void dispose(){
		this.dispose();
		Open.dispose();
		Closed.dispose();
	
	}
	
	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	

	public Texture getClosed() {
		return Closed;
	}

	public void setClosed(Texture closed) {
		Closed = closed;
	}

	public Texture getOpen() {
		return Open;
	}

	public void setOpen(Texture open) {
		Open = open;
	}


	
	
}
