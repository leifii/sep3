package com.gegnerkoordination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.AnimationDirection;
import com.character.Attributes;
import com.character.Character;
import com.character.Rolle;
import com.character.Skill;
import com.mygdx.game.Author;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

@Author(name = "Tobias van den Boom")

public class GruenerSchleim extends Gegner {
	transient Map<AnimationDirection, Animation> animationMap;
	AnimationDirection richtung = AnimationDirection.SOUTH_STAND;
	Animation animation;
	
	public GruenerSchleim(int x, int y, TextureRegion[][] animation,
			TiledMapTileLayer[] collisionLayer, int exp, Attributes attributes, Body body) {
		super(x, y, 32, 32, collisionLayer, exp, attributes, body);
		animationMap = new HashMap<AnimationDirection, Animation>();
		setSkills(new ArrayList<Skill>());
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,3,1,1,3,g.getSkill(0), false, 1, 0, this, 10, collisionLayer));
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
		
		for (Entry<AnimationDirection, Animation> a : animationMap.entrySet())
			a.getValue().setPlayMode(PlayMode.LOOP);
	}
	public void update(float dt){
		if(aggro){
			super.update(dt);
		} else{
			getBounds().setPosition(this.getPosition().x,this.getPosition().y);
			time += dt;
			currentFrame = getAnimation().getKeyFrame(time);
		}
	}
	public void follow(Character c){
		if(Math.sqrt(Math.pow((c.getPosition().x-getPosition().x),2)+Math.pow((c.getPosition().y-getPosition().y),2)) <= 500){
			aggro = true;
		}
		if(aggro)
			super.follow(c);
		if(Math.sqrt(Math.pow((c.getPosition().x-getPosition().x),2)+Math.pow((c.getPosition().y-getPosition().y),2)) >= 1500){
			aggro = false;
		}
	}
	public void attack(){
		for(Skill s : getSkills()) {
			s.activateProjectile(getPosition().x, getPosition().y);
		}
	}
	public Animation getAnimation() {
		return animationMap.get(richtung);
	}

	public Map<AnimationDirection, Animation> getAnimationMap() {
		return animationMap;
	}

	public void setAnimationMap(Map<AnimationDirection, Animation> animationMap) {
		this.animationMap = animationMap;
	}

	public AnimationDirection getRichtung() {
		return richtung;
	}

	public void setRichtung(AnimationDirection richtung) {
		this.richtung = richtung;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
}
