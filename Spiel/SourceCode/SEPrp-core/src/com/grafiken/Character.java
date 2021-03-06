package com.grafiken;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Author;

@Author(name = "Angelo Soltner")

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
	List<TextureRegion[][]> gegnerList = new LinkedList<TextureRegion[][]>();
	TextureRegion[][][] Angriff;
	TextureRegion[][][] archer, warrior,mage,thief;
	
 
	public Character(){
//		texture=new Texture("grafiken/mage with head+s.png");
		texture1=new Texture("grafiken/Archer.png");
//		character=new Sprite[]{(new Sprite(texture))};
		TextureRegion[][] character1=TextureRegion.split(new Texture("grafiken/squire_m.png"),32,48);
		TextureRegion[][] character2=TextureRegion.split(new Texture("grafiken/Thief_Spreadsheet.png"), 32, 48);
		TextureRegion[][] character3=TextureRegion.split(new Texture("grafiken/mage/Mage_Spreadsheet.png"), 32, 48);
		TextureRegion[][] character4=TextureRegion.split(new Texture("grafiken/Archer_Spreadsheet.png"), 32, 48);
		
		angriffKrieger = TextureRegion.split(new Texture("grafiken/SchwertAngriff1.png"), 56, 56);
//		Gegner= new TextureRegion[][][]{angriffKrieger};
		
		gegnerList.add(angriffKrieger);
		gegnerList.add(TextureRegion.split(new Texture("grafiken/Slime.png"),35,32));  //Slime mit 2 Frames
		gegnerList.add(TextureRegion.split(new Texture("grafiken/Ork.png"), 64, 64)); //Ork Laufanimation 4 Richtungen: 8 Frames, Angriff(5-8.Reihe):9 Frames
		gegnerList.add(TextureRegion.split(new Texture("grafiken/Skelett.png"), 64, 64)); //Skelett Laufanimation 9 Frames/Angriff 13 Frames
		
//		char1=new TextureRegion[]{new TextureRegion(new Texture("grafiken/squire_m.png"),0,0,32,48 ), new TextureRegion(texture),new TextureRegion(new Texture("grafiken/Thief Spreadsheet.png"),0,0,32,48), new TextureRegion(texture1)};
		Animation=new TextureRegion [][][]{character1,character3, character2, character4};
		
		archer= new TextureRegion[][][]{TextureRegion.split(new Texture("grafiken/Archer_Spreadsheet.png"), 32, 48),
				TextureRegion.split(new Texture("grafiken/Archer_Spreadsheet-Eyes1.png"), 32, 48),
				TextureRegion.split(new Texture("grafiken/Archer_Spreadsheet-Eyes2.png"), 32, 48),
				TextureRegion.split(new Texture("grafiken/Archer_Spreadsheet-Eyes3.png"), 32, 48),
				TextureRegion.split(new Texture("grafiken/Archer_Spreadsheet-bluehair.png"), 32, 48),
				TextureRegion.split(new Texture("grafiken/Archer_Spreadsheet-bluehair-Eyes1.png"), 32, 48),
				TextureRegion.split(new Texture("grafiken/Archer_Spreadsheet-bluehair-Eyes2.png"), 32, 48),
				TextureRegion.split(new Texture("grafiken/Archer_Spreadsheet-bluehair-Eyes3.png"), 32, 48)
		};
		
		warrior= new TextureRegion[][][]{TextureRegion.split(new Texture("grafiken/krieger/squire_m.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/krieger/squire_m-greenE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/krieger/squire_m-lightblueE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/krieger/squire_m-redE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/krieger/squire_m-yellowE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/krieger/squire_m_rot.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/krieger/squire_m_rot-greenE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/krieger/squire_m_rot-lightblueE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/krieger/squire_m_rot-redE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/krieger/squire_m_rot-yellowE.png"), 32, 48)
		};
		mage=new TextureRegion[][][]{
			TextureRegion.split(new Texture("grafiken/mage/Mage_Spreadsheet.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/mage/Mage_Spreadsheet_Eye_green.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/mage/Mage_Spreadsheet_Eye_red.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/mage/Mage_Spreadsheet_Eye_yellow.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/mage/Mage_blue_spreadsheet.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/mage/Mage_blue_spreadsheet_eye_green.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/mage/Mage_blue_spreadsheet_eye_red.png"), 32, 48), 
			TextureRegion.split(new Texture("grafiken/mage/Mage_blue_spreadsheet_eye_yellow.png"), 32, 48),
		};
		thief=new TextureRegion[][][]{
			TextureRegion.split(new Texture("grafiken/Schurke/Thief_Spreadsheet.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/Schurke/Thief_Spreadsheet-blueE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/Schurke/Thief_Spreadsheet-greenE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/Schurke/Thief_Spreadsheet-lightblueE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/Schurke/Thief_Spreadsheet-red.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/Schurke/Thief_Spreadsheet-red-blueE.png"), 32, 48),
			TextureRegion.split(new Texture("grafiken/Schurke/Thief_Spreadsheet-red-greenE.png"), 32, 48), 
			TextureRegion.split(new Texture("grafiken/Schurke/Thief_Spreadsheet-red-lightblueE.png"), 32, 48),
		};
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
	
	public TextureRegion[][] getKrieger(int index){
		return warrior[index];
	}
	
	public TextureRegion[][] getSchütze(int index){
		return archer[index];
	}
	public TextureRegion[][] getMage(int index){
		return mage[index];
	}
	public TextureRegion[][] getThief(int index){
		return thief[index];
	}
	public Texture getGegner(int index ) {
		
		gegner1=new Texture("grafiken/Drachenmensch.png");
		gegner2=new Texture("grafiken/Kobold.png");
		if(index==1) return gegner1;
		else return gegner2;
		
	}
	@Override
	public TextureRegion[][] getGegnerAnimation(int index) { //3Gegner+Kriegerangriff?
		return gegnerList.get(index);
	}
	
}
