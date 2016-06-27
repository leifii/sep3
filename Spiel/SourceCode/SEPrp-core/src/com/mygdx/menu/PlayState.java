package com.mygdx.menu;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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
import com.npc.NPC;
import com.objects.Equipment;
import com.objects.EquipmentType;
import com.objects.Experience;
import com.objects.Gold;
import com.objects.Portal;
import com.objects.Truhe;

public class PlayState extends State implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<Truhe> truhenListe = new LinkedList<Truhe>();
	transient List<IDrawable> tempDrawableList = new LinkedList<IDrawable>();

	transient NPC[] Npc;

	private transient List<Gegner> gegnerList;
	private transient List<IDrawable> drawableList;

	private Character c;
	private transient Map map;
	private transient ICharacter s;
	private transient float currentFrameTime;
	transient TextureRegion currentFrame;
	transient int mapPixelWidth;
	transient int mapPixelHeight;
	transient private TiledMapTileLayer[] collisionLayer;

	public transient World world;
	transient private Box2DDebugRenderer b2dr;

	transient Portal Portal[] = new Portal[] { new Portal(50, 50, 500, 500), new Portal(500, 500, 50, 50) };

	private static transient PlayState instance;

	public static PlayState getInstance() {
		return instance;
	}

	public PlayState(GameStateManager gsm, int characterauswahl, int design) {
		super(gsm);

		world = new World(new Vector2(0, 0), false);
		b2dr = new Box2DDebugRenderer();
		MyContactListener cl = new MyContactListener();
		world.setContactListener(cl);

		s = new com.grafiken.Character();
		map = new Map(cam);
		collisionLayer = new TiledMapTileLayer[2];
		collisionLayer[0] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte");
		collisionLayer[1] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte2");

		Npc = new NPC[] { new NPC(120, 300, "grafiken/Kobold.png", "Hallo!", createDynamicBody(120, 300, "npc")),
				new NPC(120, 360, "grafiken/Kobold.png", "Hallo!", createDynamicBody(120, 360, "npc")) };

		Body body = createDynamicBody(100, 100, "charakter");

		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ----------
		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //
		Attributes attributes = new Attributes(1, 1, 1, 1, 1, 1, 1, 2.5f);
		c.setDesign(design);
		if (characterauswahl == 1) {
			//System.out.println("Krieger");
			//c = new Krieger(100, 100, s.getAnimation(0), collisionLayer, attributes, body);
			setCharacterType(0, attributes, body);
		} else if (characterauswahl == 2) {
			//System.out.println("Magier");
			c = new Magier(100, 100, s.getAnimation(1), collisionLayer, attributes, body);
		} else if (characterauswahl == 3) {
			c = new Schurke(100, 100, s.getAnimation(2), collisionLayer, attributes, body);
		} else if (characterauswahl == 4) {
			//System.out.println("Schurke");
			c = new Schuetze(100, 100, s.getSchütze(design), collisionLayer, attributes, body);
			
			// Unnötig, int design kann direkt übergeben werden, denke ich.
			
//			if (design == 0) {
//				c = new Schuetze(100, 100, s.getSchütze(0), collisionLayer, attributes, body);
//			}
//			// else{
//			// c = new Schuetze(100, 100, s.getAnimation(index), collisionLayer,
//			// attributes, body); //Solange Animation nicht fertig ist
//			// }
//			if (design == 1) {
//				c = new Schuetze(100, 100, s.getSchütze(1), collisionLayer, attributes, body);
//			}
//			if (design == 2) {
//				c = new Schuetze(100, 100, s.getSchütze(2), collisionLayer, attributes, body);
//			}
//			if (design == 3) {
//				c = new Schuetze(100, 100, s.getSchütze(3), collisionLayer, attributes, body);
//			}
//			if (design == 4) {
//				c = new Schuetze(100, 100, s.getSchütze(4), collisionLayer, attributes, body);
//			}
//			if (design == 5) {
//				c = new Schuetze(100, 100, s.getSchütze(5), collisionLayer, attributes, body);
//			}
//			if (design == 6) {
//				c = new Schuetze(100, 100, s.getSchütze(6), collisionLayer, attributes, body);
//			}
//			if (design == 7) {
//				c = new Schuetze(100, 100, s.getSchütze(7), collisionLayer, attributes, body);
//			}
			//---------------
			
			// c=new Schuetze(100,100,s.getAnimation(3), (TiledMapTileLayer)
			// map.getMap().getLayers().get("Objekte"), attributes);

			System.out.println("Schütze");
		}

		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ----------
		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //

		initGegner();
		drawableList = new LinkedList<IDrawable>();
		truhenListe.add(new Truhe(100, 200, createTruhenBody(100, 200), new Experience(100), new Gold(30)));

		instance = this;
	}
	
	public void setCharacterType(int animationType, Attributes attributes, Body body){
		c = new Krieger(100, 100, s.getAnimation(animationType), collisionLayer, attributes, body);
	}
	
	

	// Konstruktor für das Laden gespeicherter Spiele --Dom--
	public PlayState(GameStateManager gsm, int characterauswahl, Vector3 position) {
		// TODO weitere Attribute dem Kontruktor übergeben!!
		super(gsm);

		world = new World(new Vector2(0, 0), false);
		b2dr = new Box2DDebugRenderer();
		MyContactListener cl = new MyContactListener();
		world.setContactListener(cl);

		s = new com.grafiken.Character();
		map = new Map(cam);
		collisionLayer = new TiledMapTileLayer[2];
		collisionLayer[0] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte");
		collisionLayer[1] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte2");

		Body body = createDynamicBody(100, 100, "charakter");

		// CHARAKTERAUSWAHL
		Attributes attributes = new Attributes(1, 1, 1, 1, 1, 1, 1, 2.5f);
		if (characterauswahl == 1) {
			System.out.println("Krieger");
			c = new Krieger(position.x, position.y, s.getAnimation(0), collisionLayer, attributes, body);
		} else if (characterauswahl == 2) {
			System.out.println("Magier");
			c = new Magier(100, 100, s.getAnimation(1), collisionLayer, attributes, body);
		} else if (characterauswahl == 3) {
			c = new Schurke(100, 100, s.getAnimation(2), collisionLayer, attributes, body);
		} else if (characterauswahl == 4) {
			System.out.println("SchÜtze");
			c = new Schuetze(100, 100, s.getAnimation(3), collisionLayer, attributes, body);
			// c=new Schuetze(100,100,s.getAnimation(3), (TiledMapTileLayer)
			// map.getMap().getLayers().get("Objekte"), attributes);
			System.out.println("SchÜtze");
		}
		// --------------

		initGegner();
		drawableList = new LinkedList<IDrawable>();
		truhenListe.add(new Truhe(100, 200, createTruhenBody(100, 200), new Experience(100), new Gold(30)));

		instance = this;
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
		if (Gdx.input.isKeyJustPressed(Keys.K)) {
			if (de.SEPL.GameScore.GameScoreManagement.saveGameScore(c) == true) {
				System.out.println("Speichern erfolgreich.");
			}
		}
		// if (Gdx.input.isKeyJustPressed(Keys.L)) {
		// if (de.SEPL.GameScore.GameScoreManagement.loadGameScore() == true) {
		// System.out.println("Laden erflogreich.");
		// }
		// }
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

		// NPCs //
		for (int i = 0; i < Npc.length; i++) {

			Npc[i].render(this, sb, c.getBounds(), c);
		}
		// NPCs //

		// TRUHEN //
		for (Truhe t : truhenListe) {
			t.draw(sb);
		}
		// TRUHEN //

		// PORTALE //
		for (int i = 0; i < Portal.length; i++) {
			Portal[i].render(sb, c);
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

		// b2dr.render(world, cam.combined);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		c.dispose();
		this.dispose();

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
