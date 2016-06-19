package com.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.character.IDrawable;

public class Equipment implements IDrawable {
	
	private EquipmentType type;
	private float x, y;

	public Equipment(float x, float y, EquipmentType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	public void draw(SpriteBatch sb) {
		sb.draw(type.getTexture(), x, y);
	}
	
	public EquipmentType getType() {
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
}
