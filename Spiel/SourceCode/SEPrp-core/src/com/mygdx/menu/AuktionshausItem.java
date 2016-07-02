package com.mygdx.menu;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.Author;

//package com.mygdx.menu;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
//import com.badlogic.gdx.utils.Align;
//import com.mygdx.game.Author;
//
@Author(name = "Bijan Shahbaz Nejad")
//
//public class AuktionshausItem {
//
//	Vector3 position;
//	Texture item;
//	Image Item;
//	int Index;
//	boolean removed;
//	/////////////
//	private Skin skin;
//	private TextureAtlas atlas;
//	private Table table;
//	private BitmapFont white;
//	private Label label;
//	String Preis;
//	Stage stageitem;
//	int abstand,abstand1;
//	Actor actor;
//	Actor actor1;
//	
//	public AuktionshausItem(int index,int preis){
//		abstand1=0;
//		Index=index;
//		
//		if (index%9==0&& index != 0) {
//			index=0;
//			abstand1-=190;
//		}
//		abstand=index*172;
//	
//		position=new Vector3(188+abstand, 720+abstand1, 0);
//	
//		
//		stageitem = new Stage();
//		Gdx.input.setInputProcessor(stageitem);
//		
//		item=new Texture("userInterface/auktionshausitem.png");
//		Item=new Image(item);
//		
//		Preis=Integer.toString(preis);
//		Item.setScale(9.0f, 10.0f);
//		Item.setPosition(position.x, position.y);
//		
//		
//		
//		///////////////////
//		
//		white = new BitmapFont(Gdx.files.internal("white.fnt"));
//		LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);
//		label = new Label(Preis, labelStyle);
//		label.setFontScale(3f);
//		label.setPosition(position.x+10, position.y+120);
//		label.setColor(Color.YELLOW);
//		actor=Item;
//		actor1=label;
//	}
//	
//	public void verkaufen(){
//		
//	}
//	
////		if (Gdx.input.getX()>position.x && position.x<(Gdx.input.getX()+172) && Gdx.input.getY()>position.y && position.y<(Gdx.input.getY()+19)) {
////			if (Gdx.input.isTouched()) {
////	
////				
////				
////			}
////		}				
////	public void remove(KaufenState ks,int i){
////		ks.testitem[i].Item.remove();
////	
////	}
////	
//	
//	public void add(Stage stage){
//		if (removed==false) {
//			System.out.println(position.x+"  "+position.y);
//		stage.addActor(actor);
//		stage.addActor(actor1);
//		}
//	
//	}
//	public void remove(Stage stage){
//	
//		if (Gdx.input.getX()>position.x && position.x<(Gdx.input.getX()+172)&& position.y>Gdx.input.getY()+360 && position.y<Gdx.input.getY()+360+190) {
//			if (Gdx.input.isTouched()) {	
//			actor1.remove();
//			actor.remove();
//			removed=true;
//			}
//		}
//	}
//	
//	public void dispose(){
//		this.dispose();
//	}
//	
//}


class AuktionshausItem implements IInventar{
	
	TextButton itemkauf;
	
	Label	iteminfo;
	String Name,Preis;
	
	
	boolean gekauft;
	
	public AuktionshausItem(TextButtonStyle textButtonStyle,LabelStyle labelStyle,String name){
		Name=name;
		Preis="[PREIS]";
		itemkauf=new TextButton("Kaufen ->", textButtonStyle);
		itemkauf.pad(20);
		iteminfo=new Label(Name+" "+Preis, labelStyle);
		gekauft=false;
	}
	
	
	public void add(Table table,State state){
		if (!gekauft) {	
		table.add(itemkauf);
		table.add(iteminfo);
		table.row();
		}
		if (itemkauf.isPressed()) {
			itemkauf.remove();
			iteminfo.remove();
			gekauft=true;
		
			if (state instanceof KaufenState) {
		/////     DOM HIER !!!!!                     auktionshausClient.deleteItem(Name);
				
				place(Name); // INVENTAR
			}
			if(state instanceof VerkaufenState){
					remove(Name); // INVENTAR
					
					///// DOM HIER !!!!!!			auktionshausClient.pasteItem(Name);
			}
		}
	}


	@Override
	public void place(String name) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<String> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void remove(String name) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getStrenghtBoost() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getIntelligenceBoost() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getStaminaBoost() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getDexterityBoost() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getHealing() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getMoney() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean modifyMoney(int delta) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
