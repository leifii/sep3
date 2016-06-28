package com.character;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gegnerkoordination.Gegner;

public class MyContactListener implements ContactListener {
	
	@Override
	public void beginContact(Contact c) {
		// TODO Auto-generated method stub
		Fixture fa=c.getFixtureA();
		Fixture fb=c.getFixtureB();
		boolean[] b;
		if(fa.getUserData()!=null && fa.getUserData().equals("north")){
			b=(boolean[]) fa.getBody().getUserData();
			b[0]=true;
			fa.getBody().setUserData(b);
		}
		if(fa.getUserData()!=null && fa.getUserData().equals("south")){
			b=(boolean[]) fa.getBody().getUserData();
			b[1]=true;
			fa.getBody().setUserData(b);
		}
		if(fa.getUserData()!=null && fa.getUserData().equals("east")){
			b=(boolean[]) fa.getBody().getUserData();
			b[2]=true;
			fa.getBody().setUserData(b);
		}
		if(fa.getUserData()!=null && fa.getUserData().equals("west")){
			b=(boolean[]) fa.getBody().getUserData();
			b[3]=true;
			fa.getBody().setUserData(b);
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("north")){
			b=(boolean[]) fb.getBody().getUserData();
			b[0]=true;
			fb.getBody().setUserData(b);
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("south")){
			b=(boolean[]) fb.getBody().getUserData();
			b[1]=true;
			fb.getBody().setUserData(b);
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("east")){
			b=(boolean[]) fb.getBody().getUserData();
			b[2]=true;
			fb.getBody().setUserData(b);
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("west")){
			b=(boolean[]) fb.getBody().getUserData();
			b[3]=true;
			fb.getBody().setUserData(b);
		}
		//1 für String, 0 für Objekt (in der FixtureList)
		
		checkSkillCollision(fa, fb);
		checkSkillCollision(fb, fa);
		
	}
	
	private void checkSkillCollision(Fixture fa, Fixture fb) {
		if(fa.getUserData() != null && fa.getUserData().equals("skill") && 
				fb.getBody().getFixtureList().size>1 && 
				(fb.getBody().getFixtureList().get(1).getUserData().equals("charakter") || fb.getBody().getFixtureList().get(1).getUserData().equals("gegner"))) {
			
			Skill s = ((Skill) fa.getBody().getUserData());
			Character g = ((Character) fb.getBody().getFixtureList().get(0).getUserData());
			
			if(s.getCasterRolle() != g.getRolle()) {
				s.setAlive(false);
				g.getDamage(s.getDmg());
			}
			
		}
	}

	@Override
	public void endContact(Contact c) {
		// TODO Auto-generated method stub
		Fixture fa=c.getFixtureA();
		Fixture fb=c.getFixtureB();
		boolean[] b;
		if(fa.getUserData()!=null && fa.getUserData().equals("north")){
			b=(boolean[]) fa.getBody().getUserData();
			b[0]=false;
			fa.getBody().setUserData(b);
		}
		if(fa.getUserData()!=null && fa.getUserData().equals("south")){
			b=(boolean[]) fa.getBody().getUserData();
			b[1]=false;
			fa.getBody().setUserData(b);
		}
		if(fa.getUserData()!=null && fa.getUserData().equals("east")){
			b=(boolean[]) fa.getBody().getUserData();
			b[2]=false;
			fa.getBody().setUserData(b);
		}
		if(fa.getUserData()!=null && fa.getUserData().equals("west")){
			b=(boolean[]) fa.getBody().getUserData();
			b[3]=false;
			fa.getBody().setUserData(b);
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("north")){
			b=(boolean[]) fb.getBody().getUserData();
			b[0]=false;
			fb.getBody().setUserData(b);
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("south")){
			b=(boolean[]) fb.getBody().getUserData();
			b[1]=false;
			fb.getBody().setUserData(b);
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("east")){
			b=(boolean[]) fb.getBody().getUserData();
			b[2]=false;
			fb.getBody().setUserData(b);
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("west")){
			b=(boolean[]) fb.getBody().getUserData();
			b[3]=false;
			fb.getBody().setUserData(b);
		}
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub

	}

}
