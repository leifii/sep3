package com.gegnerkoordination;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.Attributes;
import com.character.Skill;
import com.character.Character;
import com.mygdx.game.Author;

@Author(name = "Tobias van den Boom")

public class OrkEndgegner extends Ork {
	public OrkEndgegner(int x, int y, TextureRegion[][] animation,
			TiledMapTileLayer[] collisionLayer, int exp, Attributes attributes,
			Body body) {
		super(x, y, animation, collisionLayer, exp, attributes, body);
		aggro = false;
		setSkills(new ArrayList<Skill>());
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,20,1,1,3,g.getSkill(11), false, 1, 1, this, 12, collisionLayer)); //4-fach Steinwurf
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,20,1,1,3,g.getSkill(11), false, 1, 2, this, 12, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,20,1,1,3,g.getSkill(11), false, 1, 3, this, 12, collisionLayer));
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,20,1,1,3,g.getSkill(11), false, 1, 4, this, 12, collisionLayer));
		
	}
	public void spamSkills(){
		for(int i=0;i<getSkills().size();i++){
			Skill s = getSkills().get(i);
			s.activateProjectile(getPosition().x, getPosition().y);
			if(s.getCd() > 5)
				s.setCd(s.getCd()-1);;
		}
	}
	public void update(float dt){
		if(aggro){
			super.update(dt);
			spamSkills();
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
