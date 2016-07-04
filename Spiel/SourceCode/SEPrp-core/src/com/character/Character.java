package com.character;

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
import com.mygdx.game.Author;
import com.mygdx.menu.IInventar;
import com.mygdx.menu.PlayState;
import com.objects.AbstractStringItem;
import com.objects.Equipment;
import com.objects.Experience;
import com.objects.Heal;
import com.objects.Item;
import com.objects.ItemType;

@Author(name = "Bijan Shahbaz Nejad, Angelo Soltner , Bardia Asemi , Tobias Van den Boom , Dominikus Häckel, ???????")

public class Character implements IDrawable {
	
	transient protected ArrayList<Skill> skills;
	transient protected IObjekte g;
	transient private TextureRegion character;
	transient private Inventory inventory;
	transient protected TiledMapTileLayer[] collisionLayer;
	private transient Body body;
	// private float cd;
	transient protected Rectangle bounds;
	protected int height = 48, width = 32;
	transient private boolean disposable = false;
	transient private boolean visible = true;
	AnimationDirection richtung = AnimationDirection.SOUTH_STAND;
	transient TextureRegion[] keyframes, keyframes1, keyframes2, keyframes3, keyframes4, keyframes5, keyframes6,
			keyframes7;
	transient Animation Animation, Animation1, Animation2, Animation3, Animation4, Animation5, Animation6, Animation7;
	transient Map<AnimationDirection, Animation> animationMap = new HashMap<AnimationDirection, Animation>();

	// Variablen, die gespeichert werden --Dom--
	private boolean[] bosseBesiegt;
	public int design;
	public Vector3 position;
	public Attributes attributes;
	public int DEX;
	public int MaxHP;
	public int currentHP;
	public int dmgFaktor;
	public static int exp;
	public static int neededexp;
	public static int level;
	public int mapIndex;
	boolean blackKeyRecieved;
	boolean goldKeyRecieved;
	boolean whiteKeyRecieved;
	public int levelSkill1;
	public int levelSkill2;
	public int levelSkill3;
	public int levelSkill4;
	public int levelSkill0;
	public String[] allItems;
	public String characterName;
	// int currentMoney; ist schon im Inventory implementiert
	// int STR, INT, STA, ATK, DEF, AS; float MS
	public boolean skillup;
	// public Character (int x,int y,String sprite,float speed){
	// laufspeed=speed;
	// position=new Vector3(x,y,0);
	// character1=new Texture(sprite);
	// }

	protected final Rolle rolle;

	public Character(float x, float y, TextureRegion[][] animation, TiledMapTileLayer[] collisionLayer,
			Attributes attributes, Body body, Rolle rolle) {
		this.rolle = rolle;
		g = new Objekte();

		this.collisionLayer = collisionLayer;
		this.setBody(body);
		body.getFixtureList().get(0).setUserData(this);

		bounds = new Rectangle(x - 4, y - 4, width + 8, height + 8);

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

		/////////////////// Wie wird MaxHP
		/////////////////// ausgerechnet?/////////////////////////
		MaxHP = 10 * attributes.getSTA();

		setCurrentHP(MaxHP);

		skillup = false;

		dmgFaktor = 1;

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
		inventory = new Inventory(this);
	}
	
	public TextureRegion getDefaultTexture() {
		return animationMap.get(AnimationDirection.SOUTH_STAND).getKeyFrame(0);
	}


	// Konstruktor ohne Animation für Schleim
	public Character(float x, float y, int width, int height, TiledMapTileLayer[] collisionLayer, Attributes attributes,
			Body body, Rolle rolle) {

		this.rolle = rolle;
		g = new Objekte();

		this.collisionLayer = collisionLayer;
		this.setBody(body);
		body.getFixtureList().get(0).setUserData(this);

		this.height = height;
		this.width = width;

		bounds = new Rectangle(x - 2, y - 2, width + 4, height + 4);

		this.attributes = attributes;

		/////////////////// Wie wird MaxHP
		/////////////////// ausgerechnet?/////////////////////////
		MaxHP = 100;

		setCurrentHP(MaxHP);
		dmgFaktor = 1;

		position = new Vector3(x, y, 0);

		level = 1;
		exp = 0;
		neededexp = 100;
		inventory = new Inventory(this);
	}

	public int getDesign() {
		return design;
	}

	public void setDesign(int design) {
		this.design = design;
	}

	public Character(int x, int y, TextureRegion[][] animation, ArrayList<Skill> skills,
			TiledMapTileLayer[] collisionLayer, Attributes attributes, Body body, Rolle rolle) {
		this(x, y, animation, collisionLayer, attributes, body, rolle);
		this.setSkills(skills);
	}

	public void setCharacter(int exp) {
		this.exp = exp;
	}

	public Rolle getRolle() {
		return rolle;
	}

	public Animation getAnimation() {
		return animationMap.get(richtung);
	}

	public void expSammeln(int Monsterexp) {
		exp += Monsterexp;
		PlayState.getInstance().addTempDrawable(new Experience(exp));
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
		if(this instanceof Krieger){
			attributes.setSTR(attributes.getSTR()+1);
			attributes.setSTA(attributes.getSTA()+2);
			attributes.setDEF(attributes.getDEF()+2);
		}
		if(this instanceof Schurke){
			attributes.setDEX(attributes.getDEX()+2);
			attributes.setSTA(attributes.getSTA()+1);
			attributes.setDEF(attributes.getDEF()+1);
		}
		if(this instanceof Schuetze){
			attributes.setDEX(attributes.getDEX()+2);
			attributes.setSTA(attributes.getSTA()+1);
			attributes.setDEF(attributes.getDEF()+1);
		}
		if(this instanceof Magier){
			attributes.setINT(attributes.getINT()+2);
			attributes.setSTA(attributes.getSTA()+1);
			attributes.setDEF(attributes.getDEF()+1);
		}
		skillup = true;
		for (int i = 0; i < getSkills().size(); i++) {
			getSkills().get(i).setskillup(true);
		}
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
		// WER AUCH IMMER DAS WEGGEMACHT HAT SOLL ES LASSEN ICH BRAUCHE DAS
		// /BIJAN
		System.out.println("Charakterposition " + "X= " + this.getPosition().x + " Y= " + this.getPosition().y);
		// WER AUCH IMMER DAS WEGGEMACHT HAT SOLL ES LASSEN ICH BRAUCHE DAS
		// /BIJAN
		// cd = skills.get(0).gethitcd();

		this.handleInput();

		for (int i = 0; i < getSkills().size(); i++) {
			getSkills().get(i).update(dt, this.getPosition().x, this.getPosition().y);

			getSkills().get(i).direction(this);
			// getSkills().get(i).buffed(this);
		}
		// cd = skills.get(0).gethitcd();

		bounds.setPosition(this.getPosition().x, this.getPosition().y);

		float oldX = this.getPosition().x, oldY = this.getPosition().y;
		boolean collisionX = false, collisionY = false;

		if (Gdx.input.isKeyPressed(Keys.W)) {
			position.y += 2 * attributes.getMS();
			richtung = AnimationDirection.NORTH_WALK;

			collisionY = false;

			collisionY = isCellBlocked(position.x, position.y - 20 + collisionLayer[0].getTileHeight());
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2 / 2,
						position.y - 20 + collisionLayer[0].getTileHeight());
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2,
						position.y - 20 + collisionLayer[0].getTileHeight());
			if (!collisionY && getBody().getUserData() != null)
				collisionY = ((boolean[]) getBody().getUserData())[0];

			if (Gdx.input.isKeyPressed(Keys.A)) {
				position.y -= attributes.getMS() * (1 / Math.sqrt(2));
				position.x -= 2 * attributes.getMS();
				position.x += attributes.getMS() * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x, position.y - 20 + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x, position.y // +
																		// collisionLayer[0].getTileHeight()
					);
				if (!collisionX && getBody().getUserData() != null)
					collisionX = ((boolean[]) getBody().getUserData())[3];

				if (collisionX)
					position.x = oldX;

			} else if (Gdx.input.isKeyPressed(Keys.D)) {
				position.y -= attributes.getMS() * (1 / Math.sqrt(2));
				position.x += 2 * attributes.getMS();
				position.x -= attributes.getMS() * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2,
						position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2,
							position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2, position.y // +
																												// collisionLayer[0].getTileHeight()
					);
				if (!collisionX && getBody().getUserData() != null)
					collisionX = ((boolean[]) getBody().getUserData())[2];

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
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2 / 2, position.y);
			if (!collisionY)
				collisionY = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2, position.y);
			if (!collisionY && getBody().getUserData() != null)
				collisionY = ((boolean[]) getBody().getUserData())[1];

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
				if (!collisionX && getBody().getUserData() != null)
					collisionX = ((boolean[]) getBody().getUserData())[3];

				if (collisionX)
					position.x = oldX;

			} else if (Gdx.input.isKeyPressed(Keys.D)) {
				position.y += attributes.getMS() * (1 / Math.sqrt(2));
				position.x += 2 * attributes.getMS();
				position.x -= attributes.getMS() * (1 / Math.sqrt(2));

				collisionX = false;

				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2,
						position.y + collisionLayer[0].getTileHeight());
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2,
							position.y + collisionLayer[0].getTileHeight() / 2);
				if (!collisionX)
					collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2,
							position.y + collisionLayer[0].getTileHeight());
				if (!collisionX && getBody().getUserData() != null)
					collisionX = ((boolean[]) getBody().getUserData())[2];

				if (collisionX)
					position.x = oldX;
			}

			if (collisionY)
				position.y = oldY;

		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			position.x -= 2 * attributes.getMS();
			richtung = AnimationDirection.WEST_WALK;

			collisionX = false;

			collisionX = isCellBlocked(position.x, position.y - 20 + collisionLayer[0].getTileHeight());
			if (!collisionX)
				collisionX = isCellBlocked(position.x, position.y + collisionLayer[0].getTileHeight() / 2);
			if (!collisionX)
				collisionX = isCellBlocked(position.x, position.y // +
																	// collisionLayer[0].getTileHeight()
				);
			if (!collisionX && getBody().getUserData() != null)
				collisionX = ((boolean[]) getBody().getUserData())[3];

			if (collisionX)
				position.x = oldX;
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			position.x += 2 * attributes.getMS();
			richtung = AnimationDirection.EAST_WALK;

			collisionX = false;

			collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2,
					position.y - 20 + collisionLayer[0].getTileHeight());
			if (!collisionX)
				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2,
						position.y + collisionLayer[0].getTileHeight() / 2);
			if (!collisionX)
				collisionX = isCellBlocked(position.x + collisionLayer[0].getTileWidth() - 2, position.y // +
																											// collisionLayer[0].getTileHeight()
				);
			if (!collisionX && getBody().getUserData() != null)
				collisionX = ((boolean[]) getBody().getUserData())[2];

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

		getBody().setTransform(position.x + width / 2, position.y + height / 2, 0);

	}

	protected boolean isCellBlocked(float x, float y) {
		Cell cell;
		boolean blocked = false;
		for (int i = 0; i < collisionLayer.length; i++) {
			if (collisionLayer[i] != null) {
				cell = collisionLayer[i].getCell((int) (x / collisionLayer[i].getTileWidth()),
						(int) (y / collisionLayer[i].getTileHeight()));
				blocked = blocked || (cell != null && cell.getTile() != null
						&& cell.getTile().getProperties().containsKey("blocked"));
			}
		}
		return blocked;
	}

	public void move(float dx, float dy) {
		int korrekturx = 0, korrektury = 0;
		if (dx > 0) {
			korrekturx = 32;
			if (getBody().getUserData() != null && ((boolean[]) getBody().getUserData())[2])
				dx = 0;
		}
		if (dx < 0 && getBody().getUserData() != null && ((boolean[]) getBody().getUserData())[3])
			dx = 0;
		if (dy > 0) {
			korrektury = 28;
			if (getBody().getUserData() != null && ((boolean[]) getBody().getUserData())[0])
				dy = 0;
		}
		if (dy < 0 && getBody().getUserData() != null && ((boolean[]) getBody().getUserData())[1])
			dy = 0;
		if (!isCellBlocked(position.x + dx + korrekturx, position.y + dy + korrektury)) {
			position.x += dx;
			position.y += dy;
		}
		getBody().setTransform(position.x + width / 2, position.y + height / 2, 0);
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

		// if (cd == 0)
		// return;

	}

	public void handleInput() {
		if (skillup == true) {
			if (Gdx.input.isKeyPressed(Keys.NUM_1)) {
				getSkills().get(1).lvlup();
				skillup = false;
			}
			if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
				getSkills().get(2).lvlup();
				skillup = false;
			}
			if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
				getSkills().get(3).lvlup();
			}
			if (Gdx.input.isKeyPressed(Keys.NUM_4)) {
				getSkills().get(4).lvlup();
			}
			for (int i = 0; i < getSkills().size(); i++) {
				getSkills().get(i).setskillup(false);
			}
		}
	}

	public void draw(SpriteBatch sb) {
		for (int i = 0; i < getSkills().size(); i++) {
//			System.out.println("skills durchsuchen");
			getSkills().get(i).draw(sb);
		}
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
	
	public Attributes getEnhancedAttributes() {
		Attributes a = new Attributes(attributes);
		a.addAttributeValues(getInventory().getAttributeBoost());
		return a;
	}

	public void gainItems(List<Item> items) {
		gainItems(items.toArray(new Item[0]));
	}

	public void gainItems(Item... items) {
		for (Item i : items) {
			System.out.println("gained " + i.getNAME() + " " + i.getValue());
			if (i.getType() == ItemType.Experience) {
				expSammeln(i.getValue());
			} else if (i.getType() == ItemType.Gold) {
				inventory.modifyMoney(i.getValue());
				PlayState.getInstance().addTempDrawable(i);
			} else {
				inventory.add(i);
				if (i instanceof Equipment)
					PlayState.getInstance().addTempDrawable(i);
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

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public void heal(int hp) {
		int healAmount = currentHP + hp > MaxHP ? MaxHP - currentHP : hp;
		PlayState.getInstance().addTempDrawable(new Heal(healAmount));
		currentHP = Math.min(currentHP + hp, MaxHP);
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public void getDamage(int damage) {
		float dmg = (damage - (5 *(attributes.getDEF()/2.5f)));
		currentHP -= (int)dmg;
		PlayState.getInstance()
				.addTempDrawable(new AbstractStringItem(ItemType.Schaden, damage, Integer.toString(damage), this));
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public Vector3[] getHPVectors() {
		float x = getPosition().x;
		float y = getPosition().y;
		float offset = Math.max(getBounds().width * getCurrentHP() / getMaxHP(), 0);

		Vector3 start = new Vector3(x, y, 0);
		Vector3 mid = new Vector3(x + offset, y, 0);
		Vector3 end = new Vector3(x + getBounds().width, y, 0);

		return new Vector3[] { start, mid, end };
	}

	public void setCollisionLayer(TiledMapTileLayer[] collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	public int getdmgFaktor() {
		return dmgFaktor;
	}

	public void setdmgFaktor(int x) {
		dmgFaktor = x;
	}

	public void setAnimationMap(Map<AnimationDirection, Animation> animationMap) {
		this.animationMap = animationMap;
	}

	public Map<AnimationDirection, Animation> getAnimationMap() {
		return animationMap;
	}
	
	
	// --Dom--
	public void setMapIndex(int mapIndex) {
		this.mapIndex = mapIndex;
	}

	public int getMapIndex() {
		return this.mapIndex;
	}
	
	public void setBlackKeyStatus(boolean blackKeyRecieved) {
		this.blackKeyRecieved = blackKeyRecieved;
	}
	
	public void setGoldKeyStatus(boolean goldKeyRecieved) {
		this.goldKeyRecieved = goldKeyRecieved;
	}
	
	public void setWhiteKeyStatus(boolean whiteKeyRecieved) {
		this.whiteKeyRecieved = whiteKeyRecieved;
	}
	
	public boolean getBlackKeyStatus() {
		return blackKeyRecieved;
	}
	
	public boolean getGoldKeyStatus() {
		return goldKeyRecieved;
	}
	
	public boolean getWhiteKeyStatus() {
		return whiteKeyRecieved;
	}
	
	public void setAllItems(IInventar testInventar){
		this.allItems = testInventar.getAllItems().toArray(new String[testInventar.getAllItems().size()]);
	}
	
	public String[] getAllItems(){
		return this.allItems;
	}
	
	public void updateSkillLevel(){
		this.levelSkill0 = getSkills().get(0).getlvl();
		this.levelSkill1 = getSkills().get(1).getlvl();
		this.levelSkill2 = getSkills().get(2).getlvl();
		this.levelSkill3 = getSkills().get(3).getlvl();
		this.levelSkill4 = getSkills().get(4).getlvl();
	}

	public int getLevelSkill1(){
		return this.levelSkill1;
	}
	public int getLevelSkill0(){
		return this.levelSkill0;
	}
	public int getLevelSkill2(){
		return this.levelSkill2;
	}
	public int getLevelSkill3(){
		return this.levelSkill3;
	}
	public int getLevelSkill4(){
		return this.levelSkill4;
	}
	
	public void resetSkillCharacteristics(int levelSkill0, int levelSkill1, int levelSkill2, int levelSkill3,
			int levelSkill4) {

		int counter = 0;
		while (counter < levelSkill1) {
			getSkills().get(0).lvlup();
		}
		counter = 0;
		while (counter < levelSkill2) {
			getSkills().get(1).lvlup();
		}
		counter = 0;
		while (counter < levelSkill3) {
			getSkills().get(2).lvlup();
		}
		counter = 0;
		while (counter < levelSkill4) {
			getSkills().get(3).lvlup();
		}
		counter = 0;
		while (counter < levelSkill0) {
			getSkills().get(4).lvlup();
		}

	}
	
	public void setCharacterName(String characterName){
		this.characterName = characterName;
	}
	
	public String getCharacterName(){
		return this.characterName;
	}
	// ------

	public boolean[] getBosseBesiegt() {
		return bosseBesiegt;
	}

	public void setBosseBesiegt(boolean[] bosseBesiegt) {
		this.bosseBesiegt = bosseBesiegt;
	}

}
