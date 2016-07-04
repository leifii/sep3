package com.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.character.Character;
import com.mygdx.menu.PlayState;

public class Speicherstein extends NPC {

	boolean gespeichert;
	Label speichern;
	BitmapFont	white = new BitmapFont(Gdx.files.internal("white.fnt"));
	LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);
	
	public Speicherstein(int x, int y, String source, String[] TEXT, Body body) {
		super(x,y,source,TEXT,body);
		// TODO Auto-generated constructor stub
	gespeichert=false;
	}

	@Override
	public void render(PlayState ps, SpriteBatch sb, Rectangle Character, Character c) {
		// TODO Auto-generated method stub
		sb.draw(NPCtexture,position.x,position.y);

		if (Character.overlaps(bounds) && Gdx.input.isKeyJustPressed(Keys.SPACE) && angesprochen==false ) {
		
			angesprochen=true;dia.setGeöffnet(true);dia.setZähler(0);
		}
		
		
		
		else if (angesprochen && Gdx.input.isKeyJustPressed(Keys.SPACE) || Character.overlaps(bounds)==false) {
			angesprochen=false; dia.setText("Drücke K, um zu Speichern");
			
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.K) && angesprochen) {
				// @Bijan: Hier ist die Methode, die speichert und dir die Nummer des Spielstandes zurückgibt.
				// Die System.out.println kannst du rausnehmen, das war nur zu Testzwecken solange wir noch keine InGameEinblendung hatten.
				// --Dom--
				System.out.println("Speichern erfolgreich unter Spielstand " + de.SEPL.GameScore.GameScoreManagement.saveGameScore(c));

				speichern=new Label("Speichern erfolgreich unter Spielstand " + de.SEPL.GameScore.GameScoreManagement.saveGameScore(c), labelStyle);
				speichern.setPosition(400,400);
				speichern.setFontScale(1.2f);
				gespeichert=true;
				}
		if (gespeichert&&angesprochen) {
			speichern.draw(sb, 1);


				dia.setText("Speichern erfolgreich unter Spielstand "+de.SEPL.GameScore.GameScoreManagement.saveGameScore(c));
			

		}
			
	}
	public void drawDia(SpriteBatch sb){
		if(angesprochen==true)
		dia.draw(sb, 0.5f);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	
	
	
	
	
	
	
	
}
