package com.gegnerkoordination;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.AnimationDirection;
import com.character.Attributes;
import com.character.Character;
import com.character.Skill;
import com.mygdx.game.Author;

@Author(name = "Tobias van den Boom")

public class SchleimEndgegner extends GruenerSchleim {
	private boolean aggro;
	private float verzoegerung;
	private int direction; //0 north, 1 east, 2 south, 3 west
	private float richtungswechsel, max;
	private boolean follow; //bei false rumrennen, bei true folgen
	public SchleimEndgegner(int x, int y, TextureRegion[][] animation,
			TiledMapTileLayer[] collisionLayer, int exp, Attributes attributes,
			Body body) {
		super(x, y, animation, collisionLayer, exp, attributes, body);
		aggro = false;
		direction = 2;
		verzoegerung = 1;
		max = 3;
		richtungswechsel = max;
		setSkills(new ArrayList<Skill>());
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,2,1,1,3,g.getSkill(0), false, 0, 0, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,2,1,1,3,g.getSkill(0), false, 3, 0, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,3,1,1,3,g.getSkill(0), false, 1, 1, this, 10, collisionLayer));//in alle Richtungen
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,4,1,1,3,g.getSkill(0), false, 1, 2, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,5,1,1,3,g.getSkill(0), false, 1, 3, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,6,1,1,3,g.getSkill(0), false, 1, 4, this, 10, collisionLayer));
	}
	public void spamSkills(){
		for(int i=0;i<getSkills().size();i++){
			Skill s = getSkills().get(i);
			if(s.getButton() == 2 && verzoegerung > 0){
			}
			else{
				s.activateProjectile(getPosition().x, getPosition().y);
			}
		}
	}
	public void update(float dt){
		if(aggro){
			super.update(dt);
			spamSkills();
			verzoegerung -= dt;
			richtungswechsel -= dt;
			if(richtungswechsel <= 0){
				richtungswechsel = max;
				direction += 1;
				if(direction == 4)
					direction = 0;
			}
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
		if(aggro){
			if(follow){
				super.follow(c);
			}
			else{
				rumrennen(direction);
				if(getCurrentHP() <= 0.3 * getMaxHP()){
					follow = true;
				}
			}
		}
	}
	public void rumrennen(int direction){
		float dx = 0, dy = 0;
		if(direction == 0){
			dx = 0;
			dy = getLaufspeed() * 2;
			setRichtung(AnimationDirection.EAST_WALK);
		}
		else if(direction == 1){
			dx = getLaufspeed() * 2;
			dy = 0;
			setRichtung(AnimationDirection.SOUTH_WALK);
		}
		else if(direction == 2){
			dx = 0;
			dy = getLaufspeed() * -2;
			setRichtung(AnimationDirection.WEST_WALK);
		}
		else if(direction == 3){
			dx = getLaufspeed() * -2;
			dy = 0;
			setRichtung(AnimationDirection.NORTH_WALK);
		}
		move(dx,dy);
	}
}
