package com.objects;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.character.IDrawable;
import com.mygdx.menu.PlayState;

public class Truhe implements Serializable, IDrawable {

	private static final long serialVersionUID = 1L;
	Vector3 position;
	Rectangle bounds;

	Texture Closed = new Texture("grafiken/Chest.png");
	Texture Open = new Texture("grafiken/Chesto.png");

	Texture Zustand[] = new Texture[] { Closed, Open };
	private List<Item> itemList;
	private boolean open = false;
	
	private boolean disposable, visible, permanent;
	private float alpha = 1;

	public Truhe(float x, float y, boolean permanent, Item...items) {
		position = new Vector3(x, y, 0);
		bounds = new Rectangle(x, y, 40, 40);
		itemList = new LinkedList<Item>();
		for(Item i : items)
			itemList.add(i);
		
		visible = true;
		disposable = false;
		this.permanent = permanent;
	}
	
	public Truhe(float x, float y, Item...items) {
		this(x, y, true, items);
	}
	
	public void addItem(Item i) {
		itemList.add(i);
	}

	@Override
	public void draw(SpriteBatch sb) {
		if(visible) {
			sb.setColor(1, 1, 1, alpha);
			sb.draw(Zustand[open ? 1 : 0], position.x, position.y);
			sb.setColor(1, 1, 1, 1);
		}
		
		if (bounds.overlaps(PlayState.getInstance().getPlayer().getBounds())) {
			// c.getPosition().x=position.x-10; KOLLISIONSSCHEISSE
			// c.getPosition().y=position.y-10; KOLLISIONSSCHEISSE

			if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				if(!open) {
					//addToInventory
					//TODO popups gained Items, evtl Menu
					PlayState.getInstance().getPlayer().gainItems(itemList);
				}
				open = true;
			}
		}
		
		if(open && !permanent && alpha > 0) {
			alpha -= 0.005;
			if(alpha <= 0) {
				disposable = true;
				alpha = 0;
			}
		}
	}


	public void dispose() {
		this.dispose();
		Open.dispose();
		Closed.dispose();

	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Texture getClosed() {
		return Closed;
	}

	public void setClosed(Texture closed) {
		Closed = closed;
	}

	public Texture getOpen() {
		return Open;
	}

	public void setOpen(Texture open) {
		Open = open;
	}

	@Override
	public boolean isDisposable() {
		return disposable;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
