package com.grafiken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character implements ICharacter{

	Texture texture,texture1,drachenmensch;
	Sprite [] character;
	private static int WIDTH=Gdx.graphics.getWidth();
	private static int HEIGHT=Gdx.graphics.getWidth();
	TextureRegion[] char1;
	Texture gegner1,gegner2;
	TextureRegion [][]charAnimation;
	TextureRegion [][] angriffKrieger;
	TextureRegion[][][] Animation;
	TextureRegion[][][] Gegner;
	TextureRegion[][][] Angriff;
	

	public Character(){
//		texture=new Texture("grafiken/mage with head+s.png");
		texture1=new Texture("grafiken/Archer.png");
//		character=new Sprite[]{(new Sprite(texture))};
		TextureRegion[][] character1=TextureRegion.split(new Texture("grafiken/squire_m.png"),32,48);
		TextureRegion[][] character2=TextureRegion.split(new Texture("grafiken/Thief_Spreadsheet.png"), 32, 48);
		TextureRegion[][] character3=TextureRegion.split(new Texture("grafiken/Mage_Spreadsheet.png"), 32, 48);
		TextureRegion[][] character4=TextureRegion.split(new Texture("grafiken/Archer Spreadsheet.png"), 32, 48);
		
		angriffKrieger = TextureRegion.split(new Texture("grafiken/AngriffKrieger.png"), 56, 56);
		Gegner= new TextureRegion[][][]{angriffKrieger};
		TextureRegion[][] gegner1=TextureRegion.split(new Texture("grafiken/Slime.png"),35,32);
		Gegner=new TextureRegion[][][]{gegner1};
		
//		char1=new TextureRegion[]{new TextureRegion(new Texture("grafiken/squire_m.png"),0,0,32,48 ), new TextureRegion(texture),new TextureRegion(new Texture("grafiken/Thief Spreadsheet.png"),0,0,32,48), new TextureRegion(texture1)};
		Animation=new TextureRegion [][][]{character1,character3, character2, character4};
	}
	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(char1[0], WIDTH/2,HEIGHT/2);
		sb.end();
	}
	@Override
	public TextureRegion getTextureRegion(int index) {
		return char1[index];
	}
	@Override
	public Sprite getSprite(int index) {
		// TODO Auto-generated method stub
		return character[index];
	}
	public TextureRegion[][] getAnimation(int index){
		
		return Animation[index];
	}
	
	public TextureRegion[][] getAngriff(int index){
		return Angriff[index];
	}
	@Override
	public Texture getGegner(int index ) {
		
		gegner1=new Texture("grafiken/Drachenmensch.png");
		gegner2=new Texture("grafiken/Kobold.png");
		if(index==1) return gegner1;
		else return gegner2;
		
	}
	@Override
	public TextureRegion[][] getGegnerAnimation(int index) {
		return Gegner[index];
	}
	

}
