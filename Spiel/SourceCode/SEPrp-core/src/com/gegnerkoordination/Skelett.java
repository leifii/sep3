package com.gegnerkoordination;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.AnimationDirection;
import com.character.Attributes;
import com.mygdx.game.Author;


public class Skelett extends Gegner {
	transient Map<AnimationDirection, Animation> animationMap;
	AnimationDirection richtung = AnimationDirection.SOUTH_STAND;
	Animation animation;

	private static final long serialVersionUID = -3846155411884336598L;

	public Skelett(int x, int y, TextureRegion[][] animation, TiledMapTileLayer[] collisionLayer,
			Attributes attributes, Body body) {
		
		super(x, y, 64, 64, collisionLayer, attributes, body);
		animationMap = new HashMap<AnimationDirection, Animation>();
		
		TextureRegion [] keyframes=new TextureRegion[9];
		TextureRegion [] keyframes1=new TextureRegion[9];
		TextureRegion [] keyframes2=new TextureRegion[9];
		TextureRegion [] keyframes3=new TextureRegion[9];
		TextureRegion [] keyframes4=new TextureRegion[9];
		TextureRegion [] keyframes5=new TextureRegion[9];
		TextureRegion [] keyframes6=new TextureRegion[9];
		TextureRegion [] keyframes7=new TextureRegion[9];
		TextureRegion [] keyframesAngriff= new TextureRegion[13];
		TextureRegion [] keyframesAngriff1= new TextureRegion[13];
		TextureRegion [] keyframesAngriff2= new TextureRegion[13];
		TextureRegion [] keyframesAngriff3= new TextureRegion[13];
		
			for(int j=0;j<10;j++){
			keyframes[j]=animation[0][j];
			keyframes1[j]=animation[1][j];
			keyframes2[j]=animation[2][j];
			keyframes3[j]=animation[3][j];
			}
			for(int j=0;j<10;j++){
				keyframes4[j]=animation[0][0];
				keyframes5[j]=animation[1][0];
				keyframes6[j]=animation[2][0];
				keyframes7[j]=animation[3][0];
			}
			for(int i=0;i<14;i++){
				keyframesAngriff[i]=animation[4][i];				//Gegnerangriffsanimation, jedoch bisher noch nicht umgesetzt
				keyframesAngriff1[i]=animation[5][i];
				keyframesAngriff2[i]=animation[6][i];
				keyframesAngriff3[i]=animation[7][i];
			}
		animationMap.put(AnimationDirection.NORTH_WALK, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.WEST_WALK, new Animation(0.25f, keyframes1));
		animationMap.put(AnimationDirection.SOUTH_WALK, new Animation(0.25f, keyframes2));
		animationMap.put(AnimationDirection.EAST_WALK, new Animation(0.25f, keyframes3));

		animationMap.put(AnimationDirection.NORTH_STAND, new Animation(0.25f, keyframes4));
		animationMap.put(AnimationDirection.WEST_STAND, new Animation(0.25f, keyframes5));
		animationMap.put(AnimationDirection.SOUTH_STAND, new Animation(0.25f, keyframes6));
		animationMap.put(AnimationDirection.EAST_STAND, new Animation(0.25f, keyframes7));

			for (Entry<AnimationDirection, Animation> a : animationMap.entrySet())
				a.getValue().setPlayMode(PlayMode.LOOP);	
			
	}
	
	public Animation getAnimation() {
		return animationMap.get(richtung);
	}


}
