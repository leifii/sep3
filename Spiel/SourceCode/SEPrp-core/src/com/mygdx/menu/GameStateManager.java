package com.mygdx.menu;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Author;

@Author(name = "Bijan Shahbaz Nejad, Angelo Soltner")

public class GameStateManager {
	private Stack<State> states;
	Music a,b;
	
	public GameStateManager(){
		states= new Stack<State>();
		a=Gdx.audio.newMusic(Gdx.files.internal("RiseOfSpirit.mp3"));
		b=Gdx.audio.newMusic(Gdx.files.internal("TownTheme.mp3"));
	}
	public void push(State state){
		states.push(state);
		state.update(0);
	}
	public void pop(){
		states.pop();
	}
	public void setStates(State state) {
		states.pop();
		states.push(state);
	}
	public void update(float dt){
		states.peek().update(dt);
	}
	
	public void render(SpriteBatch sb){
		states.peek().render(sb);
		if(states.peek() instanceof NewMenuState1 || states.peek() instanceof NewMenuState || states.peek() instanceof NewGameCharacterState
				|| states.peek() instanceof CharEditorState || states.peek() instanceof LoadMenuState ){
			a.play();
		}
		else a.stop();
		if(states.peek() instanceof PlayState){
			b.play();
		}
	}
}
