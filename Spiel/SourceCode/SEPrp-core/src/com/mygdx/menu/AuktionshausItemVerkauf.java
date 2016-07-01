package com.mygdx.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class AuktionshausItemVerkauf {
	
	TextButton itemkauf;
	
	Label	iteminfo;
	String Name,Preis;
	
	
	boolean gekauft;
	
	public AuktionshausItemVerkauf(TextButtonStyle textButtonStyle,LabelStyle labelStyle){
		Name="[NAME]";
		Preis="[PREIS]";
		itemkauf=new TextButton("Verkaufen ->", textButtonStyle);
		itemkauf.pad(20);
		iteminfo=new Label(Name+" "+Preis, labelStyle);
		gekauft=false;
	}
	
	
	public void add(Table table){
		if (!gekauft) {	
		table.add(itemkauf);
		table.add(iteminfo);
		table.row();
		}
		if (itemkauf.isPressed()) {
			itemkauf.remove();
			iteminfo.remove();
			gekauft=true;
		}
		
	}
	
}
