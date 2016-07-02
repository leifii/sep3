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
import com.character.Character;
import com.character.*;
import com.mygdx.menu.PlayState;

public class Ork extends Gegner {
	transient Map<AnimationDirection, Animation> animationMap;
	AnimationDirection richtung = AnimationDirection.SOUTH_STAND;
	Animation animation;
	private float cd;
	private float cdnow;

	public Ork(int x, int y, TextureRegion[][] animation,
			TiledMapTileLayer[] collisionLayer, int exp, Attributes attributes, Body body) {
		super(x, y, 64, 64, collisionLayer, exp, attributes, body);
		cd = 2;
		cdnow = 0;
		animationMap = new HashMap<AnimationDirection, Animation>();
		
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

	public Map<AnimationDirection,Animation> getAnimationMap(){
		return animationMap;
	}
	
	public void setAnimationMap(Map<AnimationDirection,Animation> animationMap){
		this.animationMap = animationMap;
	}
	
	public Animation getAnimation() {
		return animationMap.get(richtung);
	}
	
	public AnimationDirection getRichtung() {
		return richtung;
	}

	public void setRichtung(AnimationDirection direction) {
		richtung = direction;
	}
	
	public void attack(){
		if(((boolean[]) getBody().getUserData())[0]){
			setRichtung(AnimationDirection.NORTH_ATTACK);
			if(cdnow <= 0){
				Character cha = PlayState.getInstance().c;
				if(bounds.overlaps(cha.getBounds())){
					cha.getDamage(10);
					cdnow = cd;
				}
			}
		}
		else if(((boolean[]) getBody().getUserData())[1]){
			setRichtung(AnimationDirection.SOUTH_ATTACK);
			if(cdnow <= 0){
				Character cha = PlayState.getInstance().c;
				if(bounds.overlaps(cha.getBounds())){
					cha.getDamage(10);
					cdnow = cd;
				}
			}
		}
		else if(((boolean[]) getBody().getUserData())[2]){
			setRichtung(AnimationDirection.EAST_ATTACK);
			if(cdnow <= 0){
				Character cha = PlayState.getInstance().c;
				if(bounds.overlaps(cha.getBounds())){
					cha.getDamage(10);
					cdnow = cd;
				}
			}
		}
		else if(((boolean[]) getBody().getUserData())[3]){
			setRichtung(AnimationDirection.WEST_ATTACK);
			if(cdnow <= 0){
				Character cha = PlayState.getInstance().c;
				if(bounds.overlaps(cha.getBounds())){
					cha.getDamage(10);
					cdnow = cd;
				}
			}
		}
	}
	
	public void update(float dt){
		super.update(dt);
		cdnow -= dt;
	}
}
