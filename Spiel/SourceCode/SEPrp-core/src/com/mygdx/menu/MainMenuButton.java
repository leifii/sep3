package com.mygdx.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.game.Author;

@Author(name = "Bijan Shahbaz Nejad")


public class MainMenuButton {

	private Vector3 position;
	
	private Texture button;
	
	/** private Animation buttonAnimation;**/
	
	public MainMenuButton(int x,int y,String source){
		position=new Vector3(x,y,0);
		button=new Texture(source);
		
		
	/**	Texture texture=new Texture("buttonanimation.png");
		buttonAnimation=new Animation(new TextureRegion(texture), 2, 0.5f);**/
	
	}
	public Texture getTexture(){
		return button;
}
	public Vector3 getPosition(){
		return position;
	}
	public void dispose(){
		button.dispose();
	}
	
}
