package com.gegnerkoordination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.AnimationDirection;
import com.character.Attributes;
import com.character.Skill;

public class Ork extends Gegner {
	transient Map<AnimationDirection, Animation> animationMap;
	AnimationDirection richtung = AnimationDirection.SOUTH_STAND;
	Animation animation;

	public Ork(int x, int y, TextureRegion[][] animation,
			TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body) {
		super(x, y, collisionLayer, attributes, body);
		animationMap = new HashMap<AnimationDirection, Animation>();

		setSkills(new ArrayList<Skill>());
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,3,1,1,3,g.getSkill(12), false, 1, 0, this, 10, collisionLayer));
		
	}

	public Animation getAnimation() {
		return animationMap.get(richtung);
	}
}
