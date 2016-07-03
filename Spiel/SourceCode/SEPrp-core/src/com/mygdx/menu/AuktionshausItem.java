package com.mygdx.menu;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.Author;

import de.SEPL.ServerClient.IAuktionshausClient;

@Author(name = "Bijan Shahbaz Nejad, Dominikus Häckel")

class AuktionshausItem implements IInventar {

	
	TextButton itemkauf;
	Label iteminfo;
	String Name, Preis;
	boolean gekauft;
	boolean pressedOnce = true; // zur Sicherstellung, dass trotz isPressed Button nur einmal ausgeführt wird --Dom--
	IInventar inventar = new com.mygdx.menu.testInventar();


	public AuktionshausItem(TextButtonStyle textButtonStyle, LabelStyle labelStyle, String name,State state, IInventar inventar) {
		Name = name;
		Preis = "30";
		
		if (state instanceof KaufenState) {
			itemkauf = new TextButton("Kaufen ->", textButtonStyle);
		}
		if (state instanceof VerkaufenState) {
			itemkauf = new TextButton("Verkaufen ->", textButtonStyle);
		}
		itemkauf.pad(20);
		iteminfo = new Label(Name + " " + Preis, labelStyle);
		gekauft = false;
	}

	public void add(Table table, State state, IAuktionshausClient auktionshausClient) {
	
		if (!gekauft) {
			table.add(itemkauf);
			table.add(iteminfo);
			table.row();
		}
		if (itemkauf.isPressed()) { //If-Abfrage, zur Sicherstellung, dass trotz isPressed Button nur einmal ausgeführt wird --Dom--
			if (pressedOnce == true) {
				
				gekauft = true;

				if (state instanceof KaufenState) {
				if(inventar.modifyMoney(-Integer.parseInt(Preis))){
					auktionshausClient.deleteItem(Name);
					itemkauf.remove();
					iteminfo.remove();																			
					inventar.place(Name); // INVENTAR
					}
				}
				if (state instanceof VerkaufenState ) {
					inventar.remove(Name); // INVENTAR
					auktionshausClient.pasteItem(Name); 
					itemkauf.remove();
					iteminfo.remove();		
					inventar.modifyMoney(Integer.parseInt(Preis));						
				}
			}
			pressedOnce = false;
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

	@Override
	public int getValueToName(String nameOfItem) {
		// TODO Auto-generated method stub
		return 0;
	}

}

// package com.mygdx.menu;
//
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Input.Keys;
// import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.g2d.TextureAtlas;
// import com.badlogic.gdx.math.Vector3;
// import com.badlogic.gdx.scenes.scene2d.Actor;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.Image;
// import com.badlogic.gdx.scenes.scene2d.ui.Label;
// import com.badlogic.gdx.scenes.scene2d.ui.Skin;
// import com.badlogic.gdx.scenes.scene2d.ui.Table;
// import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
// import com.badlogic.gdx.utils.Align;
// import com.mygdx.game.Author;
//

//
// public class AuktionshausItem {
//
// Vector3 position;
// Texture item;
// Image Item;
// int Index;
// boolean removed;
// /////////////
// private Skin skin;
// private TextureAtlas atlas;
// private Table table;
// private BitmapFont white;
// private Label label;
// String Preis;
// Stage stageitem;
// int abstand,abstand1;
// Actor actor;
// Actor actor1;
//
// public AuktionshausItem(int index,int preis){
// abstand1=0;
// Index=index;
//
// if (index%9==0&& index != 0) {
// index=0;
// abstand1-=190;
// }
// abstand=index*172;
//
// position=new Vector3(188+abstand, 720+abstand1, 0);
//
//
// stageitem = new Stage();
// Gdx.input.setInputProcessor(stageitem);
//
// item=new Texture("userInterface/auktionshausitem.png");
// Item=new Image(item);
//
// Preis=Integer.toString(preis);
// Item.setScale(9.0f, 10.0f);
// Item.setPosition(position.x, position.y);
//
//
//
// ///////////////////
//
// white = new BitmapFont(Gdx.files.internal("white.fnt"));
// LabelStyle labelStyle = new LabelStyle(white,
// com.badlogic.gdx.graphics.Color.WHITE);
// label = new Label(Preis, labelStyle);
// label.setFontScale(3f);
// label.setPosition(position.x+10, position.y+120);
// label.setColor(Color.YELLOW);
// actor=Item;
// actor1=label;
// }
//
// public void verkaufen(){
//
// }
//
//// if (Gdx.input.getX()>position.x && position.x<(Gdx.input.getX()+172) &&
// Gdx.input.getY()>position.y && position.y<(Gdx.input.getY()+19)) {
//// if (Gdx.input.isTouched()) {
////
////
////
//// }
//// }
//// public void remove(KaufenState ks,int i){
//// ks.testitem[i].Item.remove();
////
//// }
////
//
// public void add(Stage stage){
// if (removed==false) {
// System.out.println(position.x+" "+position.y);
// stage.addActor(actor);
// stage.addActor(actor1);
// }
//
// }
// public void remove(Stage stage){
//
// if (Gdx.input.getX()>position.x && position.x<(Gdx.input.getX()+172)&&
// position.y>Gdx.input.getY()+360 && position.y<Gdx.input.getY()+360+190) {
// if (Gdx.input.isTouched()) {
// actor1.remove();
// actor.remove();
// removed=true;
// }
// }
// }
//
// public void dispose(){
// this.dispose();
// }
//
// }
