package com.gegnerkoordination;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.Attributes;
import com.character.Skill;

public class GruenerSchleim extends Gegner {

	public GruenerSchleim(int x, int y, TextureRegion[][] animation,
			TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body) {
		super(x, y, animation, collisionLayer, attributes, body);
		// TODO Auto-generated constructor stub
		setSkills(new ArrayList<Skill>());
		getSkills().add(new Skill(this.getPosition().x, this.getPosition().y, 1,10,1,3,1,1,3,g.getSkill(1), false, 1, 0, this, 10, collisionLayer));
		
	}
}
