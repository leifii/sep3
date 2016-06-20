package com.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.character.IDrawable;
import com.mygdx.menu.PlayState;

abstract class AbstractStringItem extends Item implements IDrawable{

	boolean visible;
	BitmapFont font;
	String string;
	public AbstractStringItem(ItemType type, int value, String string) {
		super(type.name(), type, 0, 0);
		visible = true;
		
		font = new BitmapFont();
		this.string = string;
		
		int r = 1, g = 1, b = 1;
		switch(type) {
		case Gold: 			r = 1; g = 1; b = 0; break;
		case Experience: 	r = 0; g = 1; b = 0; break;
		default:
			break;
		}
		
		font.setColor(r, g, b, 1);
		
	}
	
	public int getExperience() {
		return getValue();
	}

	@Override
	public void draw(SpriteBatch sb) {
		if(visible) {
			Vector3 pos = PlayState.getInstance().getPlayer().getPosition();
			font.draw(sb, string, pos.x, pos.y+60);
			font.getColor().a -= 0.02;
			font.getRegion();
			visible = font.getColor().a > 0;
		}
		
	}

	@Override
	public boolean isDisposable() {
		return !visible;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}


}
