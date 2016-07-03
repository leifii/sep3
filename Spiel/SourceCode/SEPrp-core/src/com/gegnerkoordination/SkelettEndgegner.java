package com.gegnerkoordination;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.Attributes;
import com.character.Character;
import com.character.Skill;
import com.mygdx.game.Author;

@Author(name = "Tobias van den Boom")

public class SkelettEndgegner extends Skelett {
	private boolean aggro;
	private float verzoegerung;
	public SkelettEndgegner(int x, int y, TextureRegion[][] animation,
			TiledMapTileLayer[] collisionLayer, int exp, Attributes attributes,
			Body body) {
		super(x, y, animation, collisionLayer, exp, attributes, body);
		aggro = false;
		verzoegerung = 1;
		setSkills(new ArrayList<Skill>());
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,2,1,1,3,g.getSkill(12), false, 0, 0, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,2,1,1,3,g.getSkill(12), false, 2, 0, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,5,1,1,3,g.getSkill(12), false, 1, 1, this, 10, collisionLayer));//in alle richtungen
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,5,1,1,3,g.getSkill(12), false, 1, 2, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,5,1,1,3,g.getSkill(12), false, 1, 3, this, 10, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,5,1,1,3,g.getSkill(12), false, 1, 4, this, 10, collisionLayer));
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
	}

}
