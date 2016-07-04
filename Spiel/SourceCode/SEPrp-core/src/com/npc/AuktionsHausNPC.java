package com.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.character.Character;
import com.mygdx.game.Author;
import com.mygdx.menu.AuktionshausState;
import com.mygdx.menu.GameStateManager;
import com.mygdx.menu.IInventar;
import com.mygdx.menu.PlayState;

@Author(name = "Bijan Shahbaz Nejad")

public class AuktionsHausNPC extends NPC {

	GameStateManager gsm;
//	AuktionshausState auktionshaus;
	IInventar inventarr;

	public AuktionsHausNPC(int x, int y, String source, String TEXT, Body body, GameStateManager gsmm,
			PlayState playstate, IInventar inventar) {
		super();
		position=new Vector3(x, y, 0);
		bounds=new Rectangle(x-2, y-2, 36, 52);
		NPCtexture=new Texture(source);
		Dialog=new Dialog(TEXT,x+32,y+48,"dialogfenster.png");
		this.body=body;
		text=TEXT;
		
		gsm = gsmm;
//		auktionshaus = new AuktionshausState(gsm, playstate, inventar);
		inventarr=inventar;
		
		NPCtexture=new Texture("grafiken/Kobold_Shop.png");
	}

	boolean angesprochen2 = false;

	@Override
	public void render(PlayState ps, SpriteBatch sb, Rectangle Character, Character c) {
		sb.draw(NPCtexture, position.x, position.y);

		if (angesprochen && Gdx.input.isKeyJustPressed(Keys.SPACE) && !angesprochen2) {
			angesprochen = false;
			angesprochen2 = true;
			if (angesprochen2 && Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				angesprochen2 = false;
				gsm.push(new AuktionshausState(gsm, ps, inventarr));

			}
		}

		if (c.getPosition().x < position.x + 200 && c.getPosition().x > position.x - 200
				&& c.getPosition().y < position.y + 200 && c.getPosition().y > position.y - 200) {
			angesprochen = true;

			Dialog.render(ps, sb, Character, c, angesprochen);
		} else {
			angesprochen = false;
		}

	}

	@Override
	public void dispose() {
		super.dispose();

	}

}
