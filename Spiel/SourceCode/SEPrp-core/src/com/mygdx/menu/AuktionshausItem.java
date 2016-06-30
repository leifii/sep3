package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Author;

@Author(name = "Bijan Shahbaz Nejad")

public class AuktionshausItem {

	Vector3 position;
	Texture item;
	Sprite Item;
	int Index;
	/////////////
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private BitmapFont white;
	
	public AuktionshausItem(int index){
		
		item=new Texture("userInterface/auktionshausitem.png");
		Item=new Sprite(item);
		Index=index;
		
		///////////////////
		atlas = new TextureAtlas("testb/Texturen.pack");
		skin = new Skin(atlas);
		table = new Table(skin);
		
		table.setWidth(Item.getWidth());
		table.setHeight(Item.getHeight());
		table.setPosition(position.x,Item.getHeight());
		
		white = new BitmapFont(Gdx.files.internal("white.fnt"));
	}
	
	
	
	
	
}