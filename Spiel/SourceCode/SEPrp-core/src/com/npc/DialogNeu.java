package com.npc;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
public class DialogNeu extends Actor {
	private ShapeRenderer renderer;
	Dialog dialog;
	SpriteBatch sb;
	Skin skin;
	BitmapFont font;
	private float StartX =50, StartY = Gdx.graphics.getHeight() * 0.4f;
	boolean geöffnet;
	int zähler;
	String[] text;

	
	public DialogNeu(String[]text){
		renderer=new ShapeRenderer();
		skin=new Skin(Gdx.files.internal("uiskin.json"));
		font=new BitmapFont(Gdx.files.internal("white1.fnt"));
		dialog=new Dialog("warning", skin, "dialog");
		sb=new SpriteBatch();
		zähler=0;
		this.text=text;
	}


	@Override
	public void draw(Batch batch, float parentAlpha ) {
		// TODO Auto-generated method stub
		
//		renderer.setColor(1,1,1,1);
		batch.end();
		font.setColor(1,1,1,1);
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) && geöffnet==true ){
			zähler++;
		}
		if(zähler > text.length){
			geöffnet=false;
		}
		if(geöffnet==true){
			Gdx.gl.glEnable(GL11.GL_BLEND);
			renderer.begin(ShapeType.Filled);
			renderer.setColor(0,0,0,0.5f);
			renderer.rect(0, 0, Gdx.graphics.getWidth()	, Gdx.graphics.getHeight()/2);
			renderer.end();
			sb.begin();
			font.draw(sb, text[zähler-1], StartX, StartY);
			sb.end();
		}
		batch.begin();
}


	public boolean isGeöffnet() {
		return geöffnet;
	}


	public void setGeöffnet(boolean geöffnet) {
		this.geöffnet = geöffnet;
	}


	public int getZähler() {
		return zähler;
	}


	public void setZähler(int zähler) {
		this.zähler = zähler;
	}
	
}
