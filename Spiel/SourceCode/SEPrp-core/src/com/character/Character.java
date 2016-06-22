package com.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.grafiken.IObjekte;
import com.grafiken.Objekte;
import com.mygdx.menu.PlayState;
import com.objects.Equipment;
import com.objects.Item;
import com.objects.ItemType;

public class Character implements IDrawable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ArrayList<Skill> skills;

	transient protected IObjekte g;

	private Vector3 position;

	transient private TextureRegion character;
	transient private Inventory inventory;

	transient protected TiledMapTileLayer[] collisionLayer;
	transient protected Body body;

	private float cd;

	transient private Rectangle bounds;
	transient private boolean disposable = false;
	transient private boolean visible = true;

	AnimationDirection richtung = AnimationDirection.SOUTH_STAND;

	// int STR, INT, STA, ATK, DEF, AS; float MS
	private Attributes attributes;
	int DEX;
	int MaxHP;

	transient TextureRegion[] keyframes, keyframes1, keyframes2, keyframes3, keyframes4, keyframes5, keyframes6,
			keyframes7;
	transient Animation Animation, Animation1, Animation2, Animation3, Animation4, Animation5, Animation6, Animation7;
	transient Map<AnimationDirection, Animation> animationMap = new HashMap<AnimationDirection, Animation>();
	public static int exp;
	public static int neededexp;
	public static int level;

	// public Character (int x,int y,String sprite,float speed){
	// laufspeed=speed;
	// position=new Vector3(x,y,0);
	// character1=new Texture(sprite);
	// }

	public Character(int x, int y, TextureRegion[][] animation, TiledMapTileLayer[] collisionLayer,
			Attributes attributes, Body body) {

		g = new Objekte();

		this.collisionLayer = collisionLayer;
		this.body = body;

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

		this.attributes = attributes;
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

		animationMap.put(AnimationDirection.SOUTH_WALK, new Animation(0.25f, keyframes));
		animationMap.put(AnimationDirection.WEST_WALK, new Animation(0.25f, keyframes1));
		animationMap.put(AnimationDirection.EAST_WALK, new Animation(0.25f, keyframes2));
		animationMap.put(AnimationDirection.NORTH_WALK, new Animation(0.25f, keyframes3));
		animationMap.put(AnimationDirection.SOUTH_STAND, new Animation(0.25f, keyframes4));
		animationMap.put(AnimationDirection.NORTH_STAND, new Animation(0.25f, keyframes5));
		animationMap.put(AnimationDirection.EAST_STAND, new Animation(0.25f, keyframes6));
		animationMap.put(AnimationDirection.WEST_STAND, new Animation(0.25f, keyframes7));

		for (Entry<AnimationDirection, Animation> a : animationMap.entrySet())
			a.getValue().setPlayMode(PlayMode.LOOP);

		/////////////////// MOVEMENT//ENDE//////////////////////////////////
		level = 1;
		exp = 0;
		neededexp = 100;
		inventory = new Inventory();
	}

	public Character(int x, int y, TextureRegion[][] animation, ArrayList<Skill> skills,
			TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body) {
		this(x, y, animation, collisionLayer, attributes, body);
		this.skills = skills;
	}
	

	public Animation getAnimation() {
		return animationMap.get(richtung);
	}

	public void expSammeln(int Monsterexp) {
		// if monsterkilled
		// exp+=Monsterexp;
		if (exp >= neededexp) {
			levelup();
			neededexp += neededexp * (1.5f);
		}

	}

	public AnimationDirection getRichtung() {
		return richtung;
	}

	public void setRichtung(AnimationDirection direction) {
		richtung = direction;
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
			position.y += 2 * attributes.getMS();
			richtung = AnimationDirection.NORTH_WALK;

			collisionY = false;

			collisionY = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth() / 2,
						position.y + collisionLayer[0].getTileHeight());
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight());
			if (!collisionY && body.getUserData() != null)
				collisionY = ((boolean[]) body.getUserData())[0];

			if (Gdx.input.isKeyPressed(Keys.A)) {
				position.y -= attributes.getMS() * (1 / Math.sqrt(2));
				position.x -= 2 * attributes.getMS();
				position.x += attributes.getMS() * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
				if (!collisionX && body.getUserData() != null)
					collisionX = ((boolean[]) body.getUserData())[3];

				if (collisionX)
					position.x = oldX;

			} else if (Gdx.input.isKeyPressed(Keys.D)) {
				position.y -= attributes.getMS() * (1 / Math.sqrt(2));
				position.x += 2 * attributes.getMS();
				position.x -= attributes.getMS() * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
							position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
							position.y + collisionLayer[0].getTileHeight());
				if (!collisionX && body.getUserData() != null)
					collisionX = ((boolean[]) body.getUserData())[2];

				if (collisionX)
					position.x = oldX;

			}

			if (collisionY)
				position.y = oldY;

		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			position.y -= 2 * attributes.getMS();
			richtung = AnimationDirection.SOUTH_WALK;

			collisionY = false;

			collisionY = isCellBlocked(position.x, position.y);
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth() / 2, position.y);
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth(), position.y);
			if (!collisionY && body.getUserData() != null)
				collisionY = ((boolean[]) body.getUserData())[1];

			if (Gdx.input.isKeyPressed(Keys.A)) {
				position.y += attributes.getMS() * (1 / Math.sqrt(2));
				position.x -= 2 * attributes.getMS();
				position.x += attributes.getMS() * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
				if (!collisionX && body.getUserData() != null)
					collisionX = ((boolean[]) body.getUserData())[3];

				if (collisionX)
					position.x = oldX;

			} else if (Gdx.input.isKeyPressed(Keys.D)) {
				position.y += attributes.getMS() * (1 / Math.sqrt(2));
				position.x += 2 * attributes.getMS();
				position.x -= attributes.getMS() * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
							position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
							position.y + collisionLayer[0].getTileHeight());
				if (!collisionX && body.getUserData() != null)
					collisionX = ((boolean[]) body.getUserData())[2];

				if (collisionX)
					position.x = oldX;
			}

			if (collisionY)
				position.y = oldY;

		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			position.x -= 2 * attributes.getMS();
			richtung = AnimationDirection.WEST_WALK;

			collisionX = false;

			collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
			if (!collisionX)
				collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight() / 2);
			if (!collisionX)
				collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight());
			if (!collisionX && body.getUserData() != null)
				collisionX = ((boolean[]) body.getUserData())[3];

			if (collisionX)
				position.x = oldX;
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			position.x += 2 * attributes.getMS();
			richtung = AnimationDirection.EAST_WALK;

			collisionX = false;

			collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
					position.y + collisionLayer[0].getTileHeight());
			if (!collisionX)
				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight() / 2);
			if (!collisionX)
				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth(),
						position.y + collisionLayer[0].getTileHeight());
			if (!collisionX && body.getUserData() != null)
				collisionX = ((boolean[]) body.getUserData())[2];

			if (collisionX)
				position.x = oldX;
		} else if (richtung == AnimationDirection.NORTH_WALK) {
			richtung = AnimationDirection.NORTH_STAND;
		} else if (richtung == AnimationDirection.EAST_WALK) {
			richtung = AnimationDirection.EAST_STAND;
		} else if (richtung == AnimationDirection.SOUTH_WALK) {
			richtung = AnimationDirection.SOUTH_STAND;
		} else if (richtung == AnimationDirection.WEST_WALK) {
			richtung = AnimationDirection.WEST_STAND;
		}
		if (isCellBlocked(position.x, position.y)) {
			position.x = oldX;
			position.y = oldY;
		}

		body.setTransform(position.x + 16, position.y + 24, 0);

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
		int korrekturx = 0, korrektury = 0;
		if (dx > 0) {
			korrekturx = 32;
			if (((boolean[]) body.getUserData())[2])
				dx = 0;
		}
		if (dx < 0 && ((boolean[]) body.getUserData())[3])
			dx = 0;
		if (dy > 0) {
			korrektury = 48;
			if (((boolean[]) body.getUserData())[0])
				dy = 0;
		}
		if (dy < 0 && ((boolean[]) body.getUserData())[1])
			dy = 0;
		if (!isCellBlocked(position.x + dx + korrekturx, position.y + dy + korrektury)) {
			position.x += dx;
			position.y += dy;
		}
		body.setTransform(position.x + 16, position.y + 24, 0);
	}

	public void setX(float x) {
		position.x = x;
	}

	public void setY(float y) {
		position.y = y;
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
		this.attributes = attributes;
	}

	public float getLaufspeed() {
		return attributes.getMS();
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

	public int getExp() {
		return exp;
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

	public Attributes getAttributes() {
		return attributes;
	}

	public void gainItems(List<Item> items) {
		gainItems(items.toArray(new Item[0]));
	}

	public void gainItems(Item... items) {
		for (Item i : items) {
			System.out.println("gained " + i.getNAME());
			if (i.getType() == ItemType.Experience) {
				expSammeln(i.getValue());
				PlayState.getInstance().addTempDrawable(i);

			} else if (i.getType() == ItemType.Gold) {
				inventory.addGold(i.getValue());
				PlayState.getInstance().addTempDrawable(i);
			} else {
				inventory.getItemList().add(i);
				if (i instanceof Equipment) {
					Equipment e = (Equipment) i;
					e.setAsIcon();
					PlayState.getInstance().addTempDrawable(e);
				}
			}

		}
	}

	public boolean isDisposable() {
		return disposable;
	}

	public void markToDispose() {
		disposable = true;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setCharacterValues(int exp, int neededExp) {
		this.exp = exp;
		this.neededexp = neededExp;
	}

}
