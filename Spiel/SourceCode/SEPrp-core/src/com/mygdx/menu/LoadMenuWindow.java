package com.mygdx.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Author;

@Author(name = "Bijan Shahbaz Nejad")

public class LoadMenuWindow {
	

		private Vector3 position;
		
		private Texture loadmenuwindow;
		
		public LoadMenuWindow(int x,int y,String source){
			position=new Vector3(x,y,0);
			loadmenuwindow=new Texture(source);
		
		}
		public Texture getTexture(){
			return loadmenuwindow;
	}
		public Vector3 getPosition(){
			return position;
		}
		public void dispose(){
			loadmenuwindow.dispose();
		}
		
	}


