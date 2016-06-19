package com.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.android.sdklib.devices.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.gegnerkoordination.Attributes;
import com.gegnerkoordination.Gegner;
import com.mygdx.menu.InventoryState;
import com.mygdx.menu.MapState;
import com.mygdx.menu.PauseState;
import com.mygdx.menu.SkillState;
import com.npc.NPC;
import com.grafiken.*;

public class Character implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ArrayList<Skill> skills;

	protected IObjekte g;

	private Vector3 position;

	private TextureRegion character;

	protected TiledMapTileLayer[] collisionLayer;

	private float cd;

	private Rectangle bounds;

	int richtung = 0;

	float laufspeed;
	int STR;
	int INT;
	int STA;
	int DEX;

	int MaxHP;
	int Angriff;
	int Verteidigung;
	int AtkSpeed;

	TextureRegion[] keyframes, keyframes1, keyframes2, keyframes3, keyframes4, keyframes5, keyframes6, keyframes7;
	Animation Animation, Animation1, Animation2, Animation3, Animation4, Animation5, Animation6, Animation7;
	int exp;
	int neededexp;
	int level;

	// public Character (int x,int y,String sprite,float speed){
	// laufspeed=speed;
	// position=new Vector3(x,y,0);
	// character1=new Texture(sprite);
	// }

	public Character(int x, int y, TextureRegion[][] animation, float speed, TiledMapTileLayer[] collisionLayer) {

		g = new Objekte();

		this.collisionLayer = collisionLayer;

		bounds = new Rectangle(x, y, 32, 48);

		/////////////////// MOVEMENT//ANGFANG//////////////////////////////////

		keyframes = new TextureRegion[4];
		keyframes1 = new TextureRegion[4];
		keyframes2 = new TextureRegion[4];
		keyframes3 = new TextureRegion[4];
		keyframes4 = new TextureRegion[] { animation[0][0] };
		keyframes5 = new TextureRegion[] { animation[3][0] };
		keyframes6 = new TextureRegion[] { animation[2][0] };
		keyframes7 = new TextureRegion[] { animation[1][0] };

		laufspeed = speed;
		position = new Vector3(x, y, 0);
		for (int i = 0; i < 4; i++) {
			keyframes[i] = animation[0][i];
		}
		for (int i = 0; i < 4; i++) {
			keyframes1[i] = animation[1][i];
		}
		for (int i = 0; i < 4; i++) {
			keyframes2[i] = animation[2][i];
		}
		for (int i = 0; i < 4; i++) {
			keyframes3[i] = animation[3][i];
		}
		// character=sprite;
		Animation = new Animation(0.25f, keyframes);
		Animation1 = new Animation(0.25f, keyframes1);
		Animation2 = new Animation(0.25f, keyframes2);
		Animation3 = new Animation(0.25f, keyframes3);
		Animation4 = new Animation(0.25f, keyframes4);
		Animation5 = new Animation(0.25f, keyframes5);
		Animation6 = new Animation(0.25f, keyframes6);
		Animation7 = new Animation(0.25f, keyframes7);
		Animation.setPlayMode(PlayMode.LOOP);
		Animation1.setPlayMode(PlayMode.LOOP);
		Animation2.setPlayMode(PlayMode.LOOP);
		Animation3.setPlayMode(PlayMode.LOOP);
		Animation4.setPlayMode(PlayMode.LOOP);
		Animation5.setPlayMode(PlayMode.LOOP);
		Animation6.setPlayMode(PlayMode.LOOP);
		Animation7.setPlayMode(PlayMode.LOOP);
		/////////////////// MOVEMENT//ENDE//////////////////////////////////
		level = 1;
		exp = 0;
		neededexp = 100;

		STR = 1;
		INT = 1;
		STA = 1;
		DEX = 1;

		Angriff = 1;
		Verteidigung = 1;
		AtkSpeed = 1;
	}

	public Character(int x, int y, TextureRegion[][] animation, ArrayList<Skill> skills, float speed,
			TiledMapTileLayer[] collisionLayer) {
		this(x, y, animation, speed, collisionLayer);
		this.skills = skills;
	}

	public Animation getAnimation() {
		if (richtung == 0) {
			return Animation;
		} else if (richtung == 1) {
			return Animation1;
		} else if (richtung == 2) {
			return Animation2;
		} else if (richtung == 3) {
			return Animation3;
		} else if (richtung == 4) {
			return Animation4;
		} else if (richtung == 5) {
			return Animation5;
		} else if (richtung == 6) {
			return Animation6;
		} else if (richtung == 7) {
			return Animation7;
		} else {
			return Animation4;
		}
	}

	public void expSammeln(int Monsterexp, boolean monsterkilled) {
		// if monsterkilled
		// exp+=Monsterexp;
		if (exp >= neededexp) {
			levelup();
			neededexp += neededexp * (1.5f);
		}

	}

	public int getRichtung() {
		return richtung;
	}

	public void setRichtung(int richtung) {
		this.richtung = richtung;
	}

	public void levelup() {
		level++;
	}

	public boolean collision(Rectangle object) {
		if (bounds.overlaps(object)) {
			return true;
		}
		return false;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void update(float dt) {
		// public void update(float dt,LinkedList<Gegner> gegnerList,NPC Npc){

		cd = skills.get(0).gethitcd();

		for (int i = 0; i < skills.size(); i++) {
			skills.get(i).update(dt, this.getPosition().x, this.getPosition().y);
			skills.get(i).direction(this);
		}

		bounds.setPosition(this.getPosition().x, this.getPosition().y);

		float oldX = this.getPosition().x, oldY = this.getPosition().y;
		boolean collisionX = false, collisionY = false;

		if (Gdx.input.isKeyPressed(Keys.W)) {
			position.y += 2 * laufspeed;
			richtung = 3;

			collisionY = false;

			collisionY = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth() / 2,
						position.y + collisionLayer[0].getTileHeight());
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight());

			if (Gdx.input.isKeyPressed(Keys.A)) {
				position.y -= laufspeed * (1 / Math.sqrt(2));
				position.x -= 2 * laufspeed;
				position.x += laufspeed * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());

				if (collisionX)
					position.x = oldX;

			} else if (Gdx.input.isKeyPressed(Keys.D)) {
				position.y -= laufspeed * (1 / Math.sqrt(2));
				position.x += 2 * laufspeed;
				position.x -= laufspeed * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
							position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
							position.y + collisionLayer[0].getTileHeight());

				if (collisionX)
					position.x = oldX;

			}

			if (collisionY)
				position.y = oldY;

		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			position.y -= 2 * laufspeed;
			richtung = 0;

			collisionY = false;

			collisionY = isCellBlocked(position.x, position.y);
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth() / 2, position.y);
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth(), position.y);

			if (Gdx.input.isKeyPressed(Keys.A)) {
				position.y += laufspeed * (1 / Math.sqrt(2));
				position.x -= 2 * laufspeed;
				position.x += laufspeed * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());

				if (collisionX)
					position.x = oldX;

			} else if (Gdx.input.isKeyPressed(Keys.D)) {
				position.y += laufspeed * (1 / Math.sqrt(2));
				position.x += 2 * laufspeed;
				position.x -= laufspeed * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
							position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
							position.y + collisionLayer[0].getTileHeight());

				if (collisionX)
					position.x = oldX;
			}

			if (collisionY)
				position.y = oldY;

		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			position.x -= 2 * laufspeed;
			richtung = 1;

			collisionX = false;

			collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
			if (!collisionX)
				collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight() / 2);
			if (!collisionX)
				collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());

			if (collisionX)
				position.x = oldX;
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			position.x += 2 * laufspeed;
			richtung = 2;

			collisionX = false;

			collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
					position.y + collisionLayer[0].getTileHeight());
			if (!collisionX)
				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight() / 2);
			if (!collisionX)
				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight());

			if (collisionX)
				position.x = oldX;
		} else if (richtung == 0) {
			richtung = 4;
		} else if (richtung == 3) {
			richtung = 5;
		} else if (richtung == 2) {
			richtung = 6;
		} else if (richtung == 1) {
			richtung = 7;
		}
		if (isCellBlocked(position.x, position.y)) {
			position.x = oldX;
			position.y = oldY;
		}

	}

	protected boolean isCellBlocked(float x, float y) {
		Cell cell;
		boolean blocked = false;
		for (int i = 0; i < collisionLayer.length; i++) {
			cell = collisionLayer[i].getCell((int) (x / collisionLayer[i].getTileWidth()),
					(int) (y / collisionLayer[i].getTileHeight()));
			blocked = blocked || (cell != null && cell.getTile() != null
					&& cell.getTile().getProperties().containsKey("blocked"));
		}
		return blocked;
	}

	public void move(float dx, float dy) {
		int korrekturx=0,korrektury=0;
		if(dx>0)
			korrekturx=32;
		if(dy>0)
			korrektury=48;
		if(!isCellBlocked(position.x+dx+korrekturx,position.y+dy+korrektury)){
			position.x += dx;
			position.y += dy;
		}
	}

	public Vector3 getPosition() {
		return position;
	}

	public TextureRegion getTextureRegion() {
		return character;
	}

	public void dispose() {
		this.dispose();
	}

	public void angriff() {

		if (cd == 0)
			return;

	}

	public void handleInput() {

	}

	public void draw(SpriteBatch sb) {
		skills.get(0).draw(sb);
	}

	/*
	 * this.STR=STR; this.INT=INT; this.STA=STA; this.Angriff=Angriff;
	 * this.Verteidigung=Verteidigung; this.AtkSpeed=AtkSpeed;
	 */

	public void setAttributes(Attributes attributes) {
		this.STR = attributes.STR;
		this.INT = attributes.INT;
		this.STA = attributes.STA;
		this.Angriff = attributes.ATK;
		this.Verteidigung = attributes.DEF;
		this.AtkSpeed = attributes.AS;
		this.laufspeed = attributes.MS;
	}

	public float getLaufspeed() {
		return laufspeed;
	}

	public void setLaufspeed(float laufspeed) {
		this.laufspeed = laufspeed;
	}

	public int getSTR() {
		return STR;
	}

	public void setSTR(int sTR) {
		STR = sTR;
	}

	public int getINT() {
		return INT;
	}

	public void setINT(int iNT) {
		INT = iNT;
	}

	public int getSTA() {
		return STA;
	}

	public void setSTA(int sTA) {
		STA = sTA;
	}

	public int getDEX() {
		return DEX;
	}

	public void setDEX(int dEX) {
		DEX = dEX;
	}

	public int getMaxHP() {
		return MaxHP;
	}

	public void setMaxHP(int maxHP) {
		MaxHP = maxHP;
	}

	public int getAngriff() {
		return Angriff;
	}

	public void setAngriff(int angriff) {
		Angriff = angriff;
	}

	public int getVerteidigung() {
		return Verteidigung;
	}

	public void setVerteidigung(int verteidigung) {
		Verteidigung = verteidigung;
	}

	public int getAtkSpeed() {
		return AtkSpeed;
	}

	public void setAtkSpeed(int atkSpeed) {
		AtkSpeed = atkSpeed;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getNeededexp() {
		return neededexp;
	}

	public void setNeededexp(int neededexp) {
		this.neededexp = neededexp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

}
