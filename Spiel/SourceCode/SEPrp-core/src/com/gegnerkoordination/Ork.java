package com.gegnerkoordination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
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
		super(x, y, 64, 64, collisionLayer, attributes, body);
		animationMap = new HashMap<AnimationDirection, Animation>();

		setSkills(new ArrayList<Skill>());
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,3,1,1,3,g.getSkill(7), true, 1, 0, this, 1, collisionLayer));
		
		TextureRegion[] keyframes = new TextureRegion[9];
		TextureRegion[] keyframes1 = new TextureRegion[9];
		TextureRegion[] keyframes2 = new TextureRegion[9];
		TextureRegion[] keyframes3 = new TextureRegion[9];
		TextureRegion[] keyframes4 = new TextureRegion[] { animation[0][0] };
		TextureRegion[] keyframes5 = new TextureRegion[] { animation[1][0] };
		TextureRegion[] keyframes6 = new TextureRegion[] { animation[2][0] };
		TextureRegion[] keyframes7 = new TextureRegion[] { animation[3][0] };
		TextureRegion[] keyframes8 = new TextureRegion[8];
		TextureRegion[] keyframes9 = new TextureRegion[8];
		TextureRegion[] keyframes10 = new TextureRegion[8];
		TextureRegion[] keyframes11 = new TextureRegion[8];
		
		for (int i = 0; i < 9; i++) {
			keyframes[i] = animation[4][i];
		}
		for (int i = 0; i < 9; i++) {
			keyframes1[i] = animation[5][i];
		}
		for (int i = 0; i < 9; i++) {
			keyframes2[i] = animation[6][i];
		}
		for (int i = 0; i < 9; i++) {
			keyframes3[i] = animation[7][i];
		}
		for (int i = 0; i < 8; i++) {
			keyframes8[i] = animation[0][i];
		}
		for (int i = 0; i < 8; i++) {
			keyframes9[i] = animation[1][i];
		}
		for (int i = 0; i < 8; i++) {
			keyframes10[i] = animation[2][i];
		}
		for (int i = 0; i < 8; i++) {
			keyframes11[i] = animation[3][i];
		}

		animationMap.put(AnimationDirection.NORTH_WALK, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.WEST_WALK, new Animation(0.25f, keyframes1));
		animationMap.put(AnimationDirection.SOUTH_WALK, new Animation(0.25f, keyframes2));
		animationMap.put(AnimationDirection.EAST_WALK, new Animation(0.25f, keyframes3));
		animationMap.put(AnimationDirection.NORTH_STAND, new Animation(0.25f, keyframes4));
		animationMap.put(AnimationDirection.WEST_STAND, new Animation(0.25f, keyframes5));
		animationMap.put(AnimationDirection.SOUTH_STAND, new Animation(0.25f, keyframes6));
		animationMap.put(AnimationDirection.EAST_STAND, new Animation(0.25f, keyframes7));
		animationMap.put(AnimationDirection.NORTH_ATTACK, new Animation(0.25f, keyframes8));
		animationMap.put(AnimationDirection.WEST_ATTACK, new Animation(0.25f, keyframes9));
		animationMap.put(AnimationDirection.SOUTH_ATTACK, new Animation(0.25f, keyframes10));
		animationMap.put(AnimationDirection.EAST_ATTACK, new Animation(0.25f, keyframes11));
		
		for (Entry<AnimationDirection, Animation> a : animationMap.entrySet())
			a.getValue().setPlayMode(PlayMode.LOOP);
		
	}

	public Animation getAnimation() {
		return animationMap.get(richtung);
	}
	
	public void attack(){
		if(((boolean[]) getBody().getUserData())[0] && getSkills().get(0).getCdnow() <= 0){
			setRichtung(AnimationDirection.NORTH_ATTACK);
		}
		if(((boolean[]) getBody().getUserData())[1] && getSkills().get(0).getCdnow() <= 0){
			setRichtung(AnimationDirection.SOUTH_ATTACK);
		}
		if(((boolean[]) getBody().getUserData())[2] && getSkills().get(0).getCdnow() <= 0){
			setRichtung(AnimationDirection.EAST_ATTACK);
		}
		if(((boolean[]) getBody().getUserData())[3] && getSkills().get(0).getCdnow() <= 0){
			setRichtung(AnimationDirection.WEST_ATTACK);
		}
	}
}
