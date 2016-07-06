package com.mygdx.menu;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Author;

@Author(name = "Bijan Shahbaz Nejad, Angelo Soltner")

public class GameStateManager {
	private Stack<State> states;
	Music a,b,c,d;
	long timer=System.currentTimeMillis();
	
	public GameStateManager(){
		states= new Stack<State>();
		a=Gdx.audio.newMusic(Gdx.files.internal("RiseOfSpirit.mp3"));
		b=Gdx.audio.newMusic(Gdx.files.internal("TownTheme.mp3"));
		c=Gdx.audio.newMusic(Gdx.files.internal("battleThemeA.mp3"));
		d=Gdx.audio.newMusic(Gdx.files.internal("Героическая минорная.mp3"));
	}
	public void push(State state){
		if(state instanceof PlayState)
			((PlayState) state).resetInventoryState();
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
		if(states.peek() instanceof PlayState && PlayState.getInstance().getC().getMapIndex()==1){
			b.play();
		}
		else b.stop();
		if(states.peek() instanceof PlayState && PlayState.getInstance().getC().getMapIndex()==2){
			d.play();
		}
		else b.stop();
		if(states.peek() instanceof PlayState && PlayState.getInstance().getC().getMapIndex()==3){
			c.play();
		}
		else c.stop();
		if(states.peek() instanceof KönigGebenState || states.peek() instanceof NotAllKeysWinState || states.peek() instanceof BehaltenState){
			a.stop();
			a.play();
		}
		else a.stop();
		
		
	}
	public long getTimer() {
		return timer;
	}
	public void setTimer(long timer) {
		this.timer = timer;
	}
}
