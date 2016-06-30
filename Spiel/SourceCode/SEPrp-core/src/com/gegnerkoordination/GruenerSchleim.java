package com.gegnerkoordination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.AnimationDirection;
import com.character.Attributes;
import com.character.Rolle;
import com.character.Skill;
import com.badlogic.gdx.graphics.g2d.Animation;

public class GruenerSchleim extends Gegner {
	transient Map<AnimationDirection, Animation> animationMap;
	AnimationDirection richtung = AnimationDirection.SOUTH_STAND;
	Animation animation;
	
	public GruenerSchleim(int x, int y, TextureRegion[][] animation,
			TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body) {
		super(x, y, animation, collisionLayer, attributes, body);
		Map<AnimationDirection, Animation> animationMap = new HashMap<AnimationDirection, Animation>();
		// TODO Auto-generated constructor stub
		setSkills(new ArrayList<Skill>());
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,3,1,1,3,g.getSkill(1), false, 1, 0, this, 10, collisionLayer));
		TextureRegion [] keyframes=new TextureRegion[2];
		for(int i=0;i<2;i++){
			keyframes[i]=animation[0][i];
		}
		this.animation=new Animation(0.25f, keyframes);
		animationMap.put(AnimationDirection.SOUTH_WALK, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.WEST_WALK, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.EAST_WALK, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.NORTH_WALK, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.SOUTH_STAND, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.NORTH_STAND, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.EAST_STAND, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.WEST_STAND, new Animation(0.25f, keyframes));
	}
}
