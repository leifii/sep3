package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
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
	private Label label;
	String Preis;
	
	
	public AuktionshausItem(int index){
		if (index==0) {
			position=new Vector3(188, 360, 0);
		}
		
		item=new Texture("userInterface/auktionshausitem.png");
		Item=new Sprite(item);
		Index=index;
		Preis="200";
		Item.setScale(8.94f, 10.0f);
		Item.setPosition(position.x, position.y);
		
		///////////////////
		atlas = new TextureAtlas("testb/Texturen.pack");
		skin = new Skin(atlas);
		table = new Table(skin);
		
		table.debug();
		table.setWidth(Item.getWidth());
		table.setHeight(Item.getHeight());
		table.setPosition(position.x,Item.getHeight());
		
		white = new BitmapFont(Gdx.files.internal("white.fnt"));
		LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);
		label = new Label(Preis, labelStyle);
		label.setFontScale(0.3f);
		
		table.add(label);
	}
	
	public void render(SpriteBatch sb){
		
		Item.draw(sb);
	}
	
	public void dispose(){
		this.dispose();
	}
	
}