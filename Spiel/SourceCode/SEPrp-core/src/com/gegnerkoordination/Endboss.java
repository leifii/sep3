package com.gegnerkoordination;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.Attributes;
import com.character.Character;
import com.character.Krieger;
import com.character.Magier;
import com.character.Schuetze;
import com.character.Schurke;
import com.character.Skill;

public class Endboss extends Gegner {
	private boolean aggro;
	public Endboss(int x, int y, TextureRegion[][] animation,
			TiledMapTileLayer[] collisionLayer, int exp, Attributes attributes, Body body, Character c) {
		super(x, y, animation, collisionLayer, attributes, body);
		this.exp = exp;
		aggro = false;
		setSkills(new ArrayList<Skill>());
		if(c instanceof Krieger){
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,1,1,1,g.getSkill(7), true, 0, 0, this, 1, collisionLayer));		//auto-attack
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,3,1,1,3,g.getSkill(11), false, 1, 0, this, 12, collisionLayer));	//steinwurf
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,10,1,1,1.5f,g.getSkill(8), true, 2, 0, this, 1, collisionLayer));	//heal
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,2,1,1,3,g.getSkill(18), false, 3, 0, this, 10, collisionLayer));	//axtwurf
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,30,1,1,15,g.getSkill(7), true, 4, 0, this, 1, collisionLayer));	//berserker
		}
		else if(c instanceof Magier){
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,1,1,3,g.getSkill(1), false, 0, 0, this, 10, collisionLayer));	//auto-attack
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,3,1,1,3,g.getSkill(6), false, 1, 0, this, 10, collisionLayer));	//feuerball
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,40,15,1,1,2.5f,g.getSkill(9), true, 2, 0, this, 1, collisionLayer));	//eisschild
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,20,5,1,1,3,g.getSkill(2), false, 3, 0, this, 10, collisionLayer));	//eis/wasserball
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,100,30,1,1,3,g.getSkill(17), false, 4, 0, this, 15, collisionLayer));	//geist
		}
		else if(c instanceof Schurke){
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,1,1,3,g.getSkill(14), false, 0, 0, this, 10, collisionLayer));	//auto-attack
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,3,1,1,1,g.getSkill(14), false, 1, 0, this, 10, collisionLayer));// 3-fach dolch
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,3,1,0.9f,1.5f,g.getSkill(14), false, 1, 0, this, 10, collisionLayer)); 
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,3,1,0.8f,2,g.getSkill(14), false, 1, 0, this, 10, collisionLayer));
			
//			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,1,2,1,1,10,g.getSkill(10), true, 2, 0, this, 1, collisionLayer));	//tempo+
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,30,3,1,1,3,g.getSkill(15), false, 3, 0, this, 10, collisionLayer));	//giftdolchwurf
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,15,1,1,3,g.getSkill(14), false, 4, 1, this, 10, collisionLayer));	//dolchfächer 
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,15,1,1,3,g.getSkill(14), false, 4, 2, this, 10, collisionLayer));
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,15,1,1,3,g.getSkill(14), false, 4, 3, this, 10, collisionLayer));
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,50,15,1,1,3,g.getSkill(14), false, 4, 4, this, 10, collisionLayer));
		}
		else if(c instanceof Schuetze){
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,1,1,2,g.getSkill(12), false, 0, 0, this, 10, collisionLayer));//auto-attack
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,3,1,1,1,g.getSkill(12), false, 1, 0, this, 10, collisionLayer));// 3-fach pfeil
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,3,1,0.9f,1.5f,g.getSkill(12), false, 1, 0, this, 10, collisionLayer));
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,3,1,0.8f,2,g.getSkill(12), false, 1, 0, this, 10, collisionLayer));
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,10,1,1,3,g.getSkill(8), true, 2, 0, this, 1, collisionLayer));	//heal over time
			
//			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,20,10,1,1,20,g.getSkill(16), true, 3, 0, this, 7, collisionLayer));//falle
			
			getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,100,30,1,1,3,g.getSkill(13), false, 4, 0, this, 10, collisionLayer));//großer pfeil
		}
		setMaxHP(c.getMaxHP()*2);
		setCurrentHP(getMaxHP());
		setAnimationMap(c.getAnimationMap());
	}
	public void spamSkills(){
		for(int i=0;i<getSkills().size();i++){
			Skill s = getSkills().get(i);
			s.activateProjectile(getPosition().x, getPosition().y);
		}
	}
	public void update(float dt){
		super.update(dt);
		if(aggro){
			spamSkills();
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
