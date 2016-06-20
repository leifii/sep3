package com.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface IDrawable {

	public void draw(SpriteBatch sb);
	public boolean isDisposable();
	public boolean isVisible();
	public void setVisible(boolean visible);
}
