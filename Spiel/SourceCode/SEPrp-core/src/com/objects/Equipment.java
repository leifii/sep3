package com.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.character.IDrawable;
import com.mygdx.menu.PlayState;

public class Equipment extends Item implements IDrawable {
	
	private boolean visible;
	private EquipmentType type;
	private float x, y;
	private boolean gainIcon;
	private float alpha = 1;
	
	public Equipment(float x, float y, EquipmentType type, boolean gainIcon) {
		super(type.toString(), type.getType(), type.getValue(), type.getRarity());
		this.x = x;
		this.y = y;
		this.type = type;
		visible = true;
	}
	
	public Equipment(float x, float y, EquipmentType type) {
		this(x, y, type, false);
	}
	
	public Equipment(EquipmentType type) {
		this(-1, -1, type, true);
	}

	@Override
	public void draw(SpriteBatch sb) {
		if(gainIcon) {
			x = PlayState.getInstance().getPlayer().getPosition().x;
			y = PlayState.getInstance().getPlayer().getPosition().y;
			alpha -= 0.02d;
			sb.setColor(1, 1, 1, alpha);
		}
		
		sb.draw(type.getTextureRegion(), x, y + 60);
		sb.setColor(1, 1, 1, 1);
	}
	
	public EquipmentType getEquipmentType() {
		return type;
	}
	
	// STATIC HELPER
	public static Equipment spawnRandomItem(float xPos, float yPos) {
		int randomIndex = (int)(Math.random() * (EquipmentType.values().length));
		System.out.println("spawn " + EquipmentType.values()[randomIndex].name());
		return new Equipment(xPos, yPos, EquipmentType.values()[randomIndex]);
	}
	
	public static Equipment spawnRandomItem(Vector3 position) {
		return spawnRandomItem(position.x, position.y);
	}

	@Override
	public boolean isDisposable() {
		return alpha <= 0;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setAsIcon() {
		gainIcon = true;
	}

}
