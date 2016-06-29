package com.mygdx.menu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.character.AnimationDirection;
import com.character.Attributes;
import com.character.Character;
import com.character.IDrawable;
import com.character.Krieger;
import com.character.Magier;
import com.character.MyContactListener;
import com.character.Schuetze;
import com.character.Schurke;
import com.character.Skill;
import com.gegnerkoordination.Gegner;
import com.grafiken.ICharacter;
import com.grafiken.Map;
import com.npc.AuktionsHausNPC;
import com.npc.NPC;
import com.objects.Equipment;
import com.objects.EquipmentType;
import com.objects.Experience;
import com.objects.Gold;
import com.objects.Portal;
import com.objects.Truhe;
import com.objects.Key;

public class PlayState extends State {

	boolean besucht;
	List<Truhe> truhenListe = new LinkedList<Truhe>();
	List<IDrawable> tempDrawableList = new LinkedList<IDrawable>();
	private Texture Kobolddorflabel;
	List<NPC> Npc;
	public Key keys;
	private List<Gegner> gegnerList;
	private List<IDrawable> drawableList;

	public Character c;
	private Map map;
	private ICharacter s;
	private float currentFrameTime;
	TextureRegion currentFrame;
	int mapPixelWidth;
	int mapPixelHeight;
	private TiledMapTileLayer[] collisionLayer;

	public transient World world;
	private Box2DDebugRenderer b2dr;
	private ShapeRenderer sr;

	List<Portal> PortalListe;

	private static PlayState instance;

	public static PlayState getInstance() {
		return instance;
	}

	public PlayState(GameStateManager gsm, int characterauswahl, int design) {
		super(gsm);
		besucht = false;
		Kobolddorflabel = new Texture("grafiken/KoboldDorfLabel.png");
		world = new World(new Vector2(0, 0), false);
		b2dr = new Box2DDebugRenderer();
		MyContactListener cl = new MyContactListener();
		world.setContactListener(cl);
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);

		s = new com.grafiken.Character();
		map = new Map(cam);
		collisionLayer = new TiledMapTileLayer[4];
		collisionLayer[0] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte");
		collisionLayer[1] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte2");
		collisionLayer[2] = (TiledMapTileLayer) map.getMap().getLayers().get("Boden");
		collisionLayer[3] = (TiledMapTileLayer) map.getMap().getLayers().get("Boden2");

		keys = new Key(200, 200, 250, 200, 300, 200, this);
		Npc = new LinkedList<NPC>();
		Npc.add(new NPC(120, 300, "grafiken/Kobold.png",
				"[TutorialNPC]  "
						+ "Hallo! Ich erkläre dir wie das Spiel funktioniert. WASD:Laufen, 1234: Skills, Leertaste: Angreifen/Interagieren, I:Inventar",
				createDynamicBody(120, 300, "npc")));
		Npc.add(new NPC(2339, 459, "grafiken/KoboldKönig.png",
				"[Koboldkönig]  " + "Willkommen im Dorf! Suche die Schlüssel und hol meinen Schatz zurück!",
				createDynamicBody(2339, 459, "npc")));
		Npc.add(new NPC(1032, 1318, "grafiken/Kobold.png", "[Dragolas]  " + "Sei vorsichtig hier ist es gefährlich!!",
				createDynamicBody(1032, 1318, "npc")));
		Npc.add(new AuktionsHausNPC(2815, 359, "grafiken/Kobold.png",
				"Sprich mich an wenn du ins Auktionshaus möchtest!", createDynamicBody(2815, 359, "npc"), gsm, this));
		Npc.add(new NPC(1563, 381, "grafiken/Kobold.png", "[Koboldkönig-Fan]  " + "Lang lebe der König!",
				createDynamicBody(1563, 381, "npc")));
		Npc.add(new NPC(2235, 317, "grafiken/Kobold.png", "[Koboldkönig-Fan]  " + "Lang lebe der König!",
				createDynamicBody(2235, 317, "npc")));

		Body body = createDynamicBody(100, 100, "charakter");

		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ----------
		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //
		Attributes attributes = new Attributes(1, 1, 1, 1, 1, 1, 1, 2.5f);

		if (characterauswahl == 1) {
			c = new Krieger(100, 100, s.getAnimation(0), collisionLayer, attributes, body);
			// setCharacterType(0, attributes, body);
		} else if (characterauswahl == 2) {
			c = new Magier(100, 100, s.getAnimation(1), collisionLayer, attributes, body);
		} else if (characterauswahl == 3) {
			c = new Schurke(100, 100, s.getAnimation(2), collisionLayer, attributes, body);
		} else if (characterauswahl == 4) {
			c = new Schuetze(100, 100, s.getSchütze(design), collisionLayer, attributes, body);

		}

		// Zur Speicherung, dass gerade die erste Welt bespielt wird --Dom--
		c.setMapIndex(1);

		// c=new Schuetze(100,100,s.getAnimation(3), (TiledMapTileLayer)
		// map.getMap().getLayers().get("Objekte"), attributes);
		c.setDesign(design);

		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ----------
		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //

		initGegner();
		drawableList = new LinkedList<IDrawable>();
		truhenListe.add(new Truhe(100, 200, createTruhenBody(100, 200), new Experience(100), new Gold(30)));
		PortalListe = new LinkedList<Portal>();
		PortalListe.add(new Portal(50, 50, 2934, 312));
		PortalListe.add(new Portal(2934, 312, 50, 50));
		instance = this;
	}

	// Characterwerte nach laden eines alten Spielstandes setzen --Dom--
	public void setCharacterCharacteristicsAfterReload(Vector3 loadedPosition, int loadedLevel,
			Attributes loadedAttributes, int loadedExp, int loadedMaxHP, int loadedCurrentHP, int loadedNeededExp,
			int loadedDex, int loadedMapIndex, boolean blackKeyRecieved, boolean goldKeyRecieved,
			boolean whiteKeyRecieved) {

		c.setPosition(loadedPosition);
		c.setLevel(loadedLevel);
		c.setAttributes(loadedAttributes);
		c.setCharacter(loadedExp);
		c.setNeededexp(loadedNeededExp);
		c.setMaxHP(loadedMaxHP);
		c.setCurrentHP(loadedCurrentHP);
		c.setDEX(loadedDex);
		changeMap(loadedMapIndex);
		c.setBlackKeyStatus(blackKeyRecieved);
		c.setGoldKeyStatus(goldKeyRecieved);
		c.setWhiteKeyStatus(whiteKeyRecieved);
		keys.setBlackKeyStatus(blackKeyRecieved);
		keys.setGoldKeyStatus(goldKeyRecieved);
		keys.setWhiteKeyStatus(whiteKeyRecieved);
	}

	public Character getC() {
		return c;
	}

	public void setC(Character c) {
		this.c = c;
	}

	public void setCharacterType(int animationType, Attributes attributes, Body body) {
		c = new Krieger(100, 100, s.getAnimation(animationType), collisionLayer, attributes, body);
	}

	private void initGegner() {
		gegnerList = new LinkedList<Gegner>();

		Attributes a1 = new Attributes(1, 1, 1, 1, 1, 1, 1, 0.5f);
		Gegner testGegner = new Gegner(200, 200, s.getAnimation(0), collisionLayer, a1,
				createDynamicBody(200, 200, "gegner"));
		testGegner.addLoot(EquipmentType.Lederrüstung);
		gegnerList.add(testGegner);

	}

	@Override
	protected void handleInput() {

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
			// gsm.push(new CharEditorState(gsm, 4));
		}

		if (Gdx.input.isKeyJustPressed(Keys.I))
			gsm.push(new InventoryState(gsm, this, c));

		if (Gdx.input.isKeyJustPressed(Keys.O))
			drawableList.add(Equipment.spawnRandomItem(c.getPosition()));
		// if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE))
		// for (Gegner g : gegnerList)
		// killGegner(g);

		// Speichern des aktuellen Spielgeschehens --Dom--
		if (Gdx.input.isKeyJustPressed(Keys.K)) {
			if (de.SEPL.GameScore.GameScoreManagement.saveGameScore(c) == true) {
				System.out.println("Speichern erfolgreich.");
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			if (((boolean[]) c.getBody().getUserData())[0]
					&& (c.getRichtung() == AnimationDirection.NORTH_ATTACK
							|| c.getRichtung() == AnimationDirection.NORTH_STAND
							|| c.getRichtung() == AnimationDirection.NORTH_WALK)
					&& c.getSkills().get(0).getCdnow() < 0.1 && c instanceof Krieger) {
				for (Gegner g : gegnerList) {
					if (((boolean[]) g.getBody().getUserData())[1]) {
						g.getDamage(c.getSkills().get(0).getDmg());
					}
				}
			}
			if (((boolean[]) c.getBody().getUserData())[1]
					&& (c.getRichtung() == AnimationDirection.SOUTH_ATTACK
							|| c.getRichtung() == AnimationDirection.SOUTH_STAND
							|| c.getRichtung() == AnimationDirection.SOUTH_WALK)
					&& c.getSkills().get(0).getCdnow() < 0.1 && c instanceof Krieger) {
				for (Gegner g : gegnerList) {
					if (((boolean[]) g.getBody().getUserData())[0]) {
						g.getDamage(c.getSkills().get(0).getDmg());
					}
				}
			}
			if (((boolean[]) c.getBody().getUserData())[2]
					&& (c.getRichtung() == AnimationDirection.EAST_ATTACK
							|| c.getRichtung() == AnimationDirection.EAST_STAND
							|| c.getRichtung() == AnimationDirection.EAST_WALK)
					&& c.getSkills().get(0).getCdnow() < 0.1 && c instanceof Krieger) {
				for (Gegner g : gegnerList) {
					if (((boolean[]) g.getBody().getUserData())[3]) {
						g.getDamage(c.getSkills().get(0).getDmg());
					}
				}
			}
			if (((boolean[]) c.getBody().getUserData())[3]
					&& (c.getRichtung() == AnimationDirection.WEST_ATTACK
							|| c.getRichtung() == AnimationDirection.WEST_STAND
							|| c.getRichtung() == AnimationDirection.WEST_WALK)
					&& c.getSkills().get(0).getCdnow() < 0.1 && c instanceof Krieger) {
				for (Gegner g : gegnerList) {
					if (((boolean[]) g.getBody().getUserData())[2]) {
						g.getDamage(c.getSkills().get(0).getDmg());
					}
				}
			}
			if (c.position.x >= 4500 && c.position.y >= 3500) {
				if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					changeMap(2);
				}
			}
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		c.update(dt);

		for (Skill s : c.getSkills()) {
			if (s.isAlive() && s.getBody() == null)
				s.setBody(createSkillBody(s));
			if (!s.isAlive() && s.getBody() != null) {
				world.destroyBody(s.getBody());
				s.setBody(null);
			}
		}

		if (gegnerList != null)
			for (Gegner g : gegnerList) {
				g.update(dt);
				g.follow(c);
				if (g.getCurrentHP() <= 0)
					killGegner(g);
				
				for (Skill s : g.getSkills()) {
					if (s.isAlive() && s.getBody() == null)
						s.setBody(createSkillBody(s));
					if (!s.isAlive() && s.getBody() != null) {
						world.destroyBody(s.getBody());
						s.setBody(null);
					}
				}
				
			}

		currentFrameTime += dt;
		currentFrame = c.getAnimation().getKeyFrame(currentFrameTime);

		if (c.getPosition().x <= 0) {
			c.getPosition().x = 0;
		}
		if (c.getPosition().y <= 0) {
			c.getPosition().y = 0;
		}
		if (c.getPosition().x >= mapPixelWidth - 32) {
			Vector3 temp = c.getPosition();
			temp.x = mapPixelWidth - 32;
			c.setPosition(temp);
		}
		if (c.getPosition().y >= mapPixelHeight - 32) {
			Vector3 temp = c.getPosition();
			temp.y = mapPixelHeight - 32;
			c.setPosition(temp);
		}

		for (Truhe t : truhenListe) {
			if (t.isDestroyable()) {
				removeTruhe(t);
				t.setDestroyable(false);
			}
		}

		world.step(dt, 8, 8);

		cam.update();

	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub

		map.render(sb);
		
		
		sb.begin();
		c.draw(sb);


		// KOBOLD DORF LABEL//
		if (c.getPosition().x > 1595 && c.getPosition().x < 1796 && c.getPosition().y > 0 && c.getPosition().y < 1000
				&& !besucht) {
			sb.draw(Kobolddorflabel, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		}
		if (c.getPosition().x > 1795) {
			besucht = true;
		}
		if (c.getPosition().x < 1595) {
			besucht = false;
		}
		// KOBOLDORFLABEL //
		// NPCs //
		for (NPC n : Npc) {
			n.render(this, sb, c.getBounds(), c);
		}
		// NPCs //

		// TRUHEN //
		for (Truhe t : truhenListe) {
			t.draw(sb);
		}
		// TRUHEN //

		// PORTALE //
		for (Portal p : PortalListe) {
			p.render(sb, c);
		}
		// PORTALE //

		// GEGNER //
		if (gegnerList != null) {
			Iterator<Gegner> iter = gegnerList.listIterator();
			while (iter.hasNext()) {
				IDrawable d = iter.next();

				if (d.isVisible())
					d.draw(sb);
				if (d.isDisposable())
					iter.remove();
			}
		}

		// GEGNER //

		// KEYS//
		keys.render(sb, c);

		// KEYS//
		// ITEMS //
		if (drawableList != null) {
			Iterator<IDrawable> iter = drawableList.listIterator();
			while (iter.hasNext()) {
				IDrawable d = iter.next();

				if (d.isDisposable())
					iter.remove();
				if (d.isVisible())
					d.draw(sb);
			}
		}

		if (tempDrawableList != null) {
			Iterator<IDrawable> iter = tempDrawableList.listIterator();
			while (iter.hasNext()) {
				IDrawable d = iter.next();

				if (d.isDisposable())
					iter.remove();
				if (d.isVisible()) {
					d.draw(sb);
					break;
				}

			}
		}

		/**
		 * KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA
		 */
		MapProperties prop = map.getMap().getProperties();
		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		int tilePixelWidth = prop.get("tilewidth", Integer.class);
		int tilePixelHeight = prop.get("tileheight", Integer.class);

		mapPixelWidth = mapWidth * tilePixelWidth;
		mapPixelHeight = mapHeight * tilePixelHeight;

		// links unten
		if (c.getPosition().y >= 0 - 32 && c.getPosition().y < Gdx.graphics.getHeight() / 2 - 32
				&& c.getPosition().x >= 0 - 32 && c.getPosition().x < Gdx.graphics.getWidth() / 2 - 32) {
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(Gdx.graphics.getWidth() / 2 - 32, Gdx.graphics.getHeight() / 2 - 32, 0);
			cam.update();

		}
		// links oben
		else if (c.getPosition().x >= 0 - 32 && c.getPosition().x < Gdx.graphics.getWidth() / 2 - 32
				&& c.getPosition().y > mapPixelHeight - Gdx.graphics.getHeight() / 2 + 32
				&& c.getPosition().y <= mapPixelHeight + 32) {
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(Gdx.graphics.getWidth() / 2 - 32, mapPixelHeight - Gdx.graphics.getHeight() / 2 + 32, 0);
			cam.update();
		}
		// rechts oben
		else if (c.getPosition().x > mapPixelWidth - Gdx.graphics.getWidth() / 2 + 32
				&& c.getPosition().x <= mapPixelWidth + 32
				&& c.getPosition().y > mapPixelHeight - Gdx.graphics.getHeight() / 2 + 32
				&& c.getPosition().y <= mapPixelHeight + 32) {
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(mapPixelWidth - Gdx.graphics.getWidth() / 2 + 32,
					mapPixelHeight - Gdx.graphics.getHeight() / 2 + 32, 0);
			cam.update();
		}
		// rechts unten
		else if (c.getPosition().x > mapPixelWidth - Gdx.graphics.getWidth() / 2 + 32
				&& c.getPosition().x <= mapPixelWidth + 32 && c.getPosition().y >= 0 - 32
				&& c.getPosition().y < Gdx.graphics.getHeight() / 2 - 32) {
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(mapPixelWidth - Gdx.graphics.getWidth() / 2 + 32, Gdx.graphics.getHeight() / 2 - 32, 0);
		}

		else if (c.getPosition().x >= 0 - 32 && c.getPosition().x < Gdx.graphics.getWidth() / 2 - 32) { // Mitte
																										// links
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(Gdx.graphics.getWidth() / 2 - 32, c.getPosition().y, 0);
			cam.update();

		} else if (c.getPosition().y >= 0 - 32 && c.getPosition().y < Gdx.graphics.getHeight() / 2 - 32) { // Mitte
																											// unten
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(c.getPosition().x, Gdx.graphics.getHeight() / 2 - 32, 0);
			cam.update();
		} else if (c.getPosition().y > mapPixelHeight - Gdx.graphics.getHeight() / 2 + 32
				&& c.getPosition().y <= mapPixelHeight + 32) { // Mitte oben
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(c.getPosition().x, mapPixelHeight - Gdx.graphics.getHeight() / 2 + 32, 0);
		} else if (c.getPosition().x > mapPixelWidth - Gdx.graphics.getWidth() / 2 + 32
				&& c.getPosition().x <= mapPixelWidth + 32) { // Mitte rechts
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(mapPixelWidth - Gdx.graphics.getWidth() / 2 + 32, c.getPosition().y, 0);
		} else {
			sb.setProjectionMatrix(cam.combined);
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
			cam.position.set(c.getPosition().x, c.getPosition().y, 0);
			cam.update();
		}

		if (Gdx.input.isKeyPressed(Keys.UP) && cam.zoom > 0.3) {
			cam.zoom -= 0.01f;
		} else if (Gdx.input.isKeyPressed(Keys.DOWN) && cam.zoom < 1) {
			cam.zoom += 0.01f;
		}
		/**
		 * KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA
		 */

		sb.end();

		//DRAW HP
		sr.setProjectionMatrix(sb.getProjectionMatrix());
		sr.begin();

		drawHP(c);
		for(Gegner g : gegnerList)
			drawHP(g);
		
		sr.end();
		
		//DRAW HP
		
		b2dr.render(world, cam.combined);

	}
	
	private void drawHP(Character c) {
		Vector3[] currentHPVector = c.getHPVectors();
		sr.setColor(Color.GREEN);
		sr.line(currentHPVector[0], currentHPVector[1]);
		sr.setColor(Color.RED);
		sr.line(currentHPVector[1], currentHPVector[2]);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		c.dispose();
		this.dispose();

	}

	public void changeMap(int i) {
		c.setMapIndex(i); // --Dom--
		if (i == 2)
			map.setMap(new TmxMapLoader().load("grafiken/bereich2.tmx"));
		else if (i == 3)
			map.setMap(new TmxMapLoader().load("grafiken/map3.tmx"));
		map.setRenderer();
		c.position = new Vector3(0, 0, 0);
		collisionLayer = new TiledMapTileLayer[3];
		collisionLayer[0] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte");
		collisionLayer[1] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte2");
		collisionLayer[2] = (TiledMapTileLayer) map.getMap().getLayers().get("Boden");
		c.setCollisionLayer(collisionLayer);
		for (Gegner g : gegnerList) {
			world.destroyBody(g.getBody());
		}
		gegnerList = new LinkedList<Gegner>();
		for (NPC n : Npc) {
			world.destroyBody(n.getBody());
		}
		Npc = new LinkedList<NPC>();
		for (Truhe t : truhenListe) {
			world.destroyBody(t.getBody());
		}
		truhenListe = new LinkedList<Truhe>();
		PortalListe = new LinkedList<Portal>();
	}

	public Body createDynamicBody(int x, int y, String a) { // String a =
															// "gegner",
															// "charakter"
															// oder"npc"
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		bdef.position.set(x + 16, y + 24);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		// 0 für north, 1 für south, 2 für east, 3 für west
		boolean[] contact = { false, false, false, false };
		body.setUserData(contact);
		shape.setAsBox(10, 10);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef);
		shape.setAsBox(10, 10);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData(a);
		shape.setAsBox(13, 6, new Vector2(0, -22), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("south");
		shape.setAsBox(13, 6, new Vector2(0, 22), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("north");
		shape.setAsBox(6, 21, new Vector2(14, 0), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("east");
		shape.setAsBox(6, 21, new Vector2(-14, 0), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("west");
		return body;
	}

	public Body createTruhenBody(float x, float y) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		bdef.position.set(x + 24, y + 20);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		shape.setAsBox(18, 18);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef);
		shape.setAsBox(1, 1);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("truhe");
		return body;
	}

	public Body createSkillBody(Skill skill) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		bdef.position.set(skill.getX() + 16, skill.getY() + 16);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		shape.setRadius(skill.getRadius());
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("skill");
		body.setUserData(skill);
		return body;
	}

	public void removeTruhe(Truhe t) {
		world.destroyBody(t.getBody());
	}

	public void killGegner(Gegner g) {
		g.killed();
		world.destroyBody(g.getBody());
	}

	public void addTruhe(Truhe t) {
		truhenListe.add(t);
	}

	public void addDrawable(IDrawable drawable) {
		drawableList.add(drawable);
	}

	public void addTempDrawable(IDrawable drawable) {
		tempDrawableList.add(drawable);
	}

	public Character getPlayer() {
		return c;
	}

	public List<IDrawable> getTempDrawable() {
		return tempDrawableList;
	}


}
