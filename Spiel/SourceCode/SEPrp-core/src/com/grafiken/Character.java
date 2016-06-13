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
	TextureRegion [][]charAnimation;
	TextureRegion[][][] Animation;
	

	public Character(){
		texture=new Texture("grafiken/mage with head+s.png");
		texture1=new Texture("grafiken/Archer.png");
		drachenmensch=new Texture("grafiken/Drachenmensch1.png");
		character=new Sprite[]{(new Sprite(texture))};
		TextureRegion[][] character1=TextureRegion.split(new Texture("grafiken/squire_m.png"),32,48);
		char1=new TextureRegion[]{new TextureRegion(new Texture("grafiken/squire_m.png"),0,0,32,48 ), new TextureRegion(texture),new TextureRegion(drachenmensch), new TextureRegion(texture1),new TextureRegion(new Texture("grafiken/Kobold.png"))};
		Animation=new TextureRegion [][][]{character1};
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

}
