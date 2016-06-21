package com.character;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {
	
	@Override
	public void beginContact(Contact c) {
		// TODO Auto-generated method stub
		Fixture fa=c.getFixtureA();
		Fixture fb=c.getFixtureB();
		if(fa.getUserData()!=null && fa.getUserData().equals("north")){
			fa.getBody().setUserData("contact north");
		}
		if(fa.getUserData()!=null && fa.getUserData().equals("south")){
			fa.getBody().setUserData("contact south");
		}
		if(fa.getUserData()!=null && fa.getUserData().equals("east")){
			fa.getBody().setUserData("contact east");
		}
		if(fa.getUserData()!=null && fa.getUserData().equals("west")){
			fa.getBody().setUserData("contact west");
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("north")){
			fb.getBody().setUserData("contact north");
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("south")){
			fb.getBody().setUserData("contact south");
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("east")){
			fb.getBody().setUserData("contact east");
		}
		if(fb.getUserData()!=null && fb.getUserData().equals("west")){
			fb.getBody().setUserData("contact west");
		}
	}

	@Override
	public void endContact(Contact c) {
		// TODO Auto-generated method stub
		Fixture fa=c.getFixtureA();
		Fixture fb=c.getFixtureB();
		fa.getBody().setUserData("body");
		fb.getBody().setUserData("body");
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
