package com.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.character.IDrawable;
import com.mygdx.game.Author;
import com.mygdx.menu.PlayState;

@Author(name = "Dilara Güler, Sabiha Can")

public class Equipment extends Item implements IDrawable {
	
	private EquipmentType type;
	private float alpha = 1;
	
	public Equipment(EquipmentType type) {
		super(type.toString(), type.getType(), type.getValue(), type.getRarity(), type.getTextureRegion());
		this.type = type;
		setDescription(type.getAttributes().toString());
	}
	
	//nur als gain icon über Spieler.
	@Override
	public void draw(SpriteBatch sb) {
		if(isVisible()) {
			float x = PlayState.getInstance().getPlayer().getPosition().x;
			float y = PlayState.getInstance().getPlayer().getPosition().y;
			alpha -= 0.02d;
			sb.setColor(1, 1, 1, alpha);
			sb.draw(getTextureRegion(), x, y);
			sb.setColor(1, 1, 1, 1);
		}

	}
	
	public EquipmentType getEquipmentType() {
		return type;
	}

	@Override
	public boolean isDisposable() {
		return alpha <= 0;
	}

	@Override
	public boolean isVisible() {
		return alpha > 0;
	}

	@Override
	public void setVisible(boolean visible) {
		alpha = visible ? 1 : 0;
	}
}
