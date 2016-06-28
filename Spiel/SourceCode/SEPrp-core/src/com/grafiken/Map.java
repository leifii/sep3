package com.grafiken;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {
	OrthogonalTiledMapRenderer renderer;
	private TiledMap map;
	OrthographicCamera cam;
	
	
	public Map(OrthographicCamera cam){
		this.cam=cam;
		cam.setToOrtho(false);
		cam.update();
		setMap(new TmxMapLoader().load("grafiken/test.tmx"));
		renderer=new OrthogonalTiledMapRenderer(getMap());
	}

	
	public void render(SpriteBatch sb){
	cam.update();
	renderer.setView(cam);
	renderer.render();
	}


	public TiledMap getMap() {
		return map;
	}


	public void setMap(TiledMap map) {
		this.map = map;
	}
	
	public void setRenderer(){
		renderer = new OrthogonalTiledMapRenderer(getMap());
	}
}
