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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.character.Attributes;
import com.character.Character;
import com.character.IDrawable;
import com.character.Krieger;
import com.character.Magier;
import com.character.Schuetze;
import com.character.Schurke;
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
	List<IDrawable> tempDrawableList = new LinkedList<IDrawable>();
	
	NPC Npc = new NPC(120, 300, "grafiken/Kobold.png", "Hallo!");

	private List<Gegner> gegnerList;
	private List<IDrawable> drawableList;

	private Character c;
	private Map map;
	private ICharacter s;
	private float currentFrameTime;
	TextureRegion currentFrame;
	int mapPixelWidth;
	int mapPixelHeight;
	private TiledMapTileLayer[] collisionLayer;
	
	private World world;
	private Box2DDebugRenderer b2dr;

	Portal Portal[] = new Portal[] { new Portal(50, 50, 500, 500), new Portal(500, 500, 50, 50) };

	private static PlayState instance;
	public static PlayState getInstance() {
		return instance;
	}
	
	public PlayState(GameStateManager gsm, int characterauswahl) {
		super(gsm);

		world=new World(new Vector2(0,0),true);
		b2dr=new Box2DDebugRenderer();
		
		s = new com.grafiken.Character();
		map = new Map(cam);
		collisionLayer = new TiledMapTileLayer[2];
		collisionLayer[0] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte");
		collisionLayer[1] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte2");
		
		Body body=createDynamicBody(100,100);

		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ----------
		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //
		Attributes attributes = new Attributes(1, 1, 1, 1, 1, 1, 2.5f);
		if (characterauswahl == 1) {
			System.out.println("Krieger");
			c = new Krieger(100, 100, s.getAnimation(0), collisionLayer, attributes, body);
		} else if (characterauswahl == 2) {
			System.out.println("Magier");
			c = new Magier(100, 100, s.getAnimation(1), collisionLayer, attributes, body);
		} else if (characterauswahl == 3) {


			c= new Schurke(100, 100, s.getAnimation(2), collisionLayer, attributes, body);

			

		} else if (characterauswahl == 4) {
			System.out.println("SchÜtze");


			c=new Schuetze(100, 100, s.getAnimation(3), collisionLayer, attributes, body);

			//c=new Schuetze(100,100,s.getAnimation(3), (TiledMapTileLayer) map.getMap().getLayers().get("Objekte"), attributes);


			System.out.println("SchÜtze");
		}

		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ----------
		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //

		initGegner();
		drawableList = new LinkedList<IDrawable>();
		truhenListe.add(new Truhe(100, 200, new Experience(100), new Gold(30)));
		
		instance = this;
	}

	private void initGegner() {
		gegnerList = new LinkedList<Gegner>();

		Attributes a1 = new Attributes(1, 1, 1, 1, 1, 1, 0.5f);
		Gegner testGegner = new Gegner(200, 200, s.getAnimation(0), collisionLayer, a1, createDynamicBody(200,200));
		testGegner.addLoot(EquipmentType.Lederrüstung);
		gegnerList.add(testGegner);

	}

	@Override
	protected void handleInput() {

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		if(Gdx.input.isKeyJustPressed(Keys.I))
			gsm.push(new InventoryState(gsm, this, c));
		
		if(Gdx.input.isKeyJustPressed(Keys.O))
			drawableList.add(Equipment.spawnRandomItem(c.getPosition()));
		if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE))
			for(Gegner g : gegnerList)
				g.killed();
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
		c.update(dt);

		if (gegnerList != null)
			for (Gegner g : gegnerList) {
				g.update(dt);
				g.follow(c);
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
		
		world.step(dt, 6, 2);
		
		cam.update();

	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub

		map.render(sb);
		sb.begin();

		c.draw(sb);

		// NPCs //
		Npc.render(this, sb, c.getBounds(), c);

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
		if(gegnerList != null) {
			Iterator<Gegner> iter = gegnerList.listIterator();
			while(iter.hasNext()) {
				IDrawable d = iter.next();
				
				if(d.isVisible())
					d.draw(sb);
				if(d.isDisposable())
					iter.remove();
			}
		}

		// GEGNER //
		
		
		
		// ITEMS //
		if(drawableList != null) {
			Iterator<IDrawable> iter = drawableList.listIterator();
			while(iter.hasNext()) {
				IDrawable d = iter.next();

				if(d.isDisposable())
					iter.remove();
				if(d.isVisible())
					d.draw(sb);
			}
		}
		
		if(tempDrawableList != null) {
			Iterator<IDrawable> iter = tempDrawableList.listIterator();
			while(iter.hasNext()) {
				IDrawable d = iter.next();
				
				if(d.isDisposable())
					iter.remove();
				if(d.isVisible()) {
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
		
//		b2dr.render(world, cam.combined);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		c.dispose();
		this.dispose();

	}
	
	public Body createDynamicBody(int x, int y) {
		BodyDef bdef=new BodyDef();
		FixtureDef fdef=new FixtureDef();
		PolygonShape shape=new PolygonShape();
		bdef.position.set(x,y);
		bdef.type=BodyType.DynamicBody;
		Body body=world.createBody(bdef);
		shape.setAsBox(16, 24);
		fdef.shape=shape;
		body.createFixture(fdef);
		shape.setAsBox(16, 4, new Vector2(0,-22), 0);
		fdef.shape=shape;
		fdef.isSensor=true;
		body.createFixture(fdef);
		shape.setAsBox(16, 4, new Vector2(0,22), 0);
		fdef.shape=shape;
		fdef.isSensor=true;
		body.createFixture(fdef);
		shape.setAsBox(4, 24, new Vector2(14,0), 0);
		fdef.shape=shape;
		fdef.isSensor=true;
		body.createFixture(fdef);
		shape.setAsBox(4, 24, new Vector2(-14,0), 0);
		fdef.shape=shape;
		fdef.isSensor=true;
		body.createFixture(fdef);
		return body;
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
