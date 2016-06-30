package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Author;

@Author(name = "Bijan Shahbaz Nejad")

public class AuktionshausItem {

	Vector3 position;
	Texture item;
	Image Item;
	int Index;
	
	/////////////
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private BitmapFont white;
	private Label label;
	String Preis;
	Stage stageitem;
	int abstand;
	
	public AuktionshausItem(int index,int preis){
		
		Index=index;
		abstand=index*172;
	
		position=new Vector3(188+abstand, 720, 0);
	
		
		stageitem = new Stage();
		Gdx.input.setInputProcessor(stageitem);
		
		item=new Texture("userInterface/auktionshausitem.png");
		Item=new Image(item);
		
		Preis=Integer.toString(preis);
		Item.setScale(9.0f, 10.0f);
		Item.setPosition(position.x, position.y);
		
		///////////////////
		
		white = new BitmapFont(Gdx.files.internal("white.fnt"));
		LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);
		label = new Label(Preis, labelStyle);
		label.setFontScale(3f);
		label.setPosition(position.x+10, position.y+120);
		label.setColor(Color.YELLOW);
	}
	
	public void verkaufen(){
		
	}
	
//		if (Gdx.input.getX()>position.x && position.x<(Gdx.input.getX()+172) && Gdx.input.getY()>position.y && position.y<(Gdx.input.getY()+19)) {
//			if (Gdx.input.isTouched()) {
//	
//				
//				
//			}
//		}
				
//	public void remove(KaufenState ks,int i){
//		ks.testitem[i].Item.remove();
//	
//	}
//	
	public void add(Stage stage){
		
		stage.addActor(Item);
		stage.addActor(label);

	
	}
	
	public void dispose(){
		this.dispose();
	}
	
}