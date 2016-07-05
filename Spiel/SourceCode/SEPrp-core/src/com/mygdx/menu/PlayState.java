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
import com.gegnerkoordination.Endboss;
import com.gegnerkoordination.Gegner;
import com.gegnerkoordination.GruenerSchleim;
import com.gegnerkoordination.Ork;
import com.gegnerkoordination.OrkEndgegner;
import com.gegnerkoordination.SchleimEndgegner;
import com.gegnerkoordination.Skelett;
import com.gegnerkoordination.SkelettEndgegner;
import com.grafiken.ICharacter;
import com.grafiken.Map;
import com.mygdx.game.Author;
import com.npc.AuktionsHausNPC;
import com.npc.DialogNeu;
import com.npc.NPC;
import com.npc.Speicherstein;
import com.objects.Equipment;
import com.objects.EquipmentType;
import com.objects.Gold;
import com.objects.Key;
import com.objects.Portal;
import com.objects.Trank;
import com.objects.Truhe;

import de.SEPL.ServerClient.IAuktionshausClient;

@Author(name = "Bijan Shahbaz Nejad, Angelo Soltner, Bardia Asemi, Tobias Van den Boom, Dominikus Häckel, Dilara Güler, Sabiha Can" )

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
	public String name;

	public transient World world;
	private Box2DDebugRenderer b2dr;
	private ShapeRenderer sr;

	List<Portal> PortalListe;

	private InventoryState inventoryState;
	private boolean pauseToInventory;
	private static PlayState instance;
	
	// --Dom--
	IAuktionshausClient auktionshausClient = new de.SEPL.ServerClient.FileClient();
	IInventar testInventar = new com.mygdx.menu.testInventar();
	
	DialogNeu dial;
	
	public static PlayState getInstance() {
		return instance;
	}

	public PlayState(GameStateManager gsm, int characterauswahl, int design, String name) {
		super(gsm);
		this.name=name;


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

		keys = new Key(2336, 4352, 32, 1824, 5120, 3072, this,2,1,3);

		Body body = createDynamicBody(100, 100, 32, 48, "charakter");

		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ----------
		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //
		
		
		//int STR, int INT, int DEX, int STA, int ATK, int DEF, int AS, float MS
		
		if (characterauswahl == 1) {
			Attributes attributes = new Attributes(10, 1, 1, 15, 1, 5, 1, 2.5f);
			c = new Krieger(100, 100, s.getKrieger(design), collisionLayer, attributes, body);
			// setCharacterType(0, attributes, body);
		} else if (characterauswahl == 2) {
			Attributes attributes = new Attributes(1, 15, 1, 10, 1, 5, 1, 2.5f);
			c = new Magier(100, 100, s.getMage(design), collisionLayer, attributes, body);
		} else if (characterauswahl == 3) {
			Attributes attributes = new Attributes(1, 1, 15, 10, 1, 5, 1, 2.5f);
			c = new Schurke(100, 100, s.getAnimation(2), collisionLayer, attributes, body);
		} else if (characterauswahl == 4) {
			Attributes attributes = new Attributes(1, 1, 15, 10, 1, 5, 1, 2.5f);
			c = new Schuetze(100, 100, s.getSchütze(design), collisionLayer, attributes, body);
		}
		c.setBosseBesiegt(new boolean[4]);
		for(int i = 0; i < c.getBosseBesiegt().length; i++){
			c.getBosseBesiegt()[i] = false;
		}


		// c=new Schuetze(100,100,s.getAnimation(3), (TiledMapTileLayer)
		// map.getMap().getLayers().get("Objekte"), attributes);
		
	

		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL ----------
		// CHARAKTERAUSWAHL ---------- CHARAKTERAUSWAHL //

		initGegner(1);
		initnpcs(1);
		drawableList = new LinkedList<IDrawable>();
		truhenListe.add(new Truhe(100, 200, createTruhenBody(100, 200), new Trank(10), new Gold(30)));
		PortalListe = new LinkedList<Portal>();
		PortalListe.add(new Portal(50, 50, 2934, 312,2));
		PortalListe.add(new Portal(2934, 312, 50, 50,3));
		
//		Inventat zum testen mit allen Items füllen
		for(EquipmentType t : EquipmentType.values())
			c.getInventory().add(new Equipment(t));
		

		instance = this;
		
		// --Dom--
		c.setMapIndex(1);
		c.setDesign(design);
		c.setCharacterName(this.name);
		// ----
		
		
	}

	// Characterwerte nach laden eines alten Spielstandes setzen --Dom--
	public void setCharacterCharacteristicsAfterReload(int loadedLevel,
			Attributes loadedAttributes, int loadedExp, int loadedMaxHP, int loadedCurrentHP, int loadedNeededExp,
			int loadedDex, int loadedMapIndex, boolean blackKeyRecieved, boolean goldKeyRecieved,
			boolean whiteKeyRecieved, int levelSkill1, int levelSkill2, int levelSkill3, int levelSkill4, int levelSkill0, String[] allItems, boolean[] loadedBosseBesiegt) {

		c.setLevel(loadedLevel);
		c.setAttributes(loadedAttributes);
		c.setCharacter(loadedExp);
		c.setNeededexp(loadedNeededExp);
		c.setMaxHP(loadedMaxHP);
		c.setCurrentHP(loadedCurrentHP);
		c.setDEX(loadedDex);
		c.resetSkillCharacteristics(levelSkill0, levelSkill1, levelSkill2, levelSkill3, levelSkill4);
		changeMap(loadedMapIndex);
		c.setBlackKeyStatus(blackKeyRecieved);
		c.setGoldKeyStatus(goldKeyRecieved);
		c.setWhiteKeyStatus(whiteKeyRecieved);
		keys.setBlackKeyStatus(blackKeyRecieved);
		keys.setGoldKeyStatus(goldKeyRecieved);
		keys.setWhiteKeyStatus(whiteKeyRecieved);
		c.setBosseBesiegt(loadedBosseBesiegt);
//		for (int i = 0; i < allItems.length; i++){
//			testInventar.place(allItems[i]);
//		}
		

//		Items werden als Item Objekt über die Inventory.add(Item i) Methode zum Inventar hinzugefügt
//		for (int i = 0; i < allItems.length; i++){
//			testInventar.place(allItems[i]);
//		}
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

	private void initnpcs(int mapIndex){
		Npc = new LinkedList<NPC>();
		if(mapIndex==1){
		Npc.add(new NPC(120, 300, "grafiken/Kobold.png",
						new String[]{ "[TutorialNPC]  " + "Hallo! "+name ,"Ich erkläre dir wie das Spiel funktioniert. \n \nMit WASD kannst du dich in alle Richtungen bewegen.\nBenutze die Tasten 1234, um Fähigkeiten zu benutzen.\nDabei ist 4 dein stärkster Angriff, den du auch nicht allzu oft benutzen kannst.\nBenutze ihn behutsam.","Falls du einmal in Schwierigkeiten geraten solltest,\nhilft dir meist deine zweite Fähigkeit weiter,\num noch etwas länger durchzuhalten.\nWenn du gerade mal keine Fähigkeiten benutzen kannst,\nversuch es mal mit [Leertaste], um einen Standard-Angriff durchzuführen.\nMit der gleichen Taste kannst du auch mit Dorfbewohnern Interagieren.\nMit I kannst du einen Blick ins Inventar werfen."},
				createDynamicBody(120, 300, 32, 48, "npc")));
		Npc.add(new NPC(2339, 459, "grafiken/KoboldKönig.png",
				new String[]{"[Koboldkönig]  " + "Willkommen im Dorf!" +name ,"Vor langer Zeit herrschte Frieden im Koboldreich.\nJedoch griffen uns dann eines Tages die Truppen des dunklen Königs an.","Er nahm uns alles.\nDas Essen, das Geld, unseren Frieden\nund meinen größten Schatz.","Ich habe Gerüchte gehört,\ndass du im Stande sein könntest,\nes mit den dunklen Truppen aufzunehmen." ,"Auch wenn vielleicht nur ein Funken Hoffnung in dir stecken könnte,\nes würde kein größeres Glück in dieser Welt geben\nals wenn ich meinen Schatz zurückerlangen könnte.","Ich bitte dich Reisender,\nbringe mir meinen Schatz zurück","Ich kann dir zwar nicht viel bieten,\naber sowohl ich als auch das ganze Dorf wären dir sehr dankbar!" },
				createDynamicBody(2339, 459, 32, 48, "npc")));
		Npc.add(new NPC(1032, 1318, "grafiken/Kobold.png", new String[]{"[Dragolas] Hallo "+ name + "\nSei vorsichtig hier ist es gefährlich!!"},
				createDynamicBody(1032, 1318, 32, 48, "npc")));
		Npc.add(new AuktionsHausNPC(2815, 359, "grafiken/Kobold.png",
				"Sprich mich an wenn du ins Auktionshaus möchtest!", createDynamicBody(2815, 359, 32, 48, "npc"), gsm, this, testInventar));
		Npc.add(new NPC(1563, 381, "grafiken/Kobold.png", new String[]{"[Koboldkönig-Fan]  " + "Lang lebe der König!"},
				createDynamicBody(1563, 381, 32, 48, "npc")));
		Npc.add(new NPC(3243, 1596, "grafiken/Kobold.png", new String[]{"[Koboldkönig-Fan]  " + "Eigentlich wollte ich mich ja nur aus Neugierde\n außerhalb des Dorfes aufhalten,\nals ich dann plötzlich von einer Horde Orks überrascht wurde.\nScheinbar sind sie heute aus ihren Verstecken gekommen,\num unschuldige Kobolde anzugreifen.","Du scheinst ein tapferer Geselle zu sein.\nSchau mal, ob du es mit ihrem Anführer im Nordosten aufnehmen kannst.\nIch bin mir sicher, dass ohne ihn \nauch die anderen Orks aufhören würden, das Dorf anzugreifen.\nDarüber hinaus sollte es dir dann möglich sein, den Pfad hinter ihm zu betreten.","Unterschätz ihn aber bloß nicht.\nEs sind schon einige abermutige Reisende verschwunden,\ndie sich mit ihm und seiner Bande angelegt haben."},
				createDynamicBody(3243, 1596, 32, 48, "npc")));
		Npc.add(new NPC(705, 3234, "grafiken/Kobold.png", new String[]{"[Dorian]  " + "Früher konnte man hier in Ruhe angeln.\nHeutzutage vergeht keine Sekunde ohne dass man auf die Orks aufpassen musst"},
				createDynamicBody(705, 3234, 30, 48, "npc")));
		Npc.add(new NPC(2235, 317, "grafiken/Kobold.png", new String[]{"[Koboldkönig-Fan]  " + "Lang lebe der König!"},
				createDynamicBody(2235, 317, 32, 48, "npc")));
		Npc.add(new NPC(1981, 505, "grafiken/Kobold.png", new String[]{"[Koboldkönig-Diener] Nicht nur haben sie uns unseren Schatz gestohlen,\nein paar Tage später tauchten nocheinmal Soldaten des Königs auf,\num uns sogar die Schlüssel zu stehlen.","Ein paar Dorfbewohner berichteten mir,\ndass sie einige der Truppen,\ndie die Schlüssel gestohlen hatten, in der Nähe gesichtet haben.\nWenn du aufmerksam reist, könntest du vielleicht ein paar dieser wertvollen Schlüssel finden.","Der Schatz befindet sich jedoch in eiserner Hand des dunklen Königs...."}, createDynamicBody(1981,505,32, 48, "npc")));
		Npc.add(new Speicherstein(333, 333, "savepoint.png", new String[]{"Drücke K zum speichern."},createDynamicBody(333, 333, 32, 48, "npc")));
		Npc.add(new Speicherstein(139*32,105*32 , "savepoint.png", new String[]{"Drücke K zum speichern."},createDynamicBody(139*32, 105*32, 32, 48, "npc")));
		
		}
		if(mapIndex==2){
			Npc.add(new AuktionsHausNPC(2815, 1616, "grafiken/Kobold.png",
					"Sprich mich an wenn du ins Auktionshaus möchtest!", createDynamicBody(2815, 1616, 32, 48, "npc"), gsm, this, testInventar));
			Npc.add(new NPC(339, 224,"grafiken/Kobold.png",new String[]{"[Viktorius]" +"Hallo Reisender, \nes befinden sich viele gefährliche Kreaturen in der Nähe des Dorfes.\nPass auf dich auf!"},
					createDynamicBody(339, 224, 32, 48, "npc")));
			Npc.add(new NPC(2209,2050 ,"grafiken/Kobold.png", new String[]{"[Arkanus] Wilkommen im Dorf"},
					createDynamicBody(2209, 2050, 32, 48, "npc")));
			Npc.add(new NPC(2341, 1376,"grafiken/Kobold.png" ,new String[]{"[Sinus]  Im Dorf ist es zum Glück sicher."+ '\n' + "Das haben wir unserer Steinbarrikade zu verdanken,\ndie wir aufgestellt haben, um die Monster draußen zu halten.",
											"\n Jedoch können wir uns nicht sehr weit vom Dorf entfernen \nohne von den Kreaturen angegriffen zu werden"},
					createDynamicBody(2341, 1376, 32, 48, "npc")));
			Npc.add(new NPC(3414, 1802,"grafiken/Kobold.png" ,new String[]{"[Siegfried] Ich wünschte wir könnten hier in Frieden leben." ,"Jedoch terrorisieren uns die wilden Kreaturen, die eines Tages plötzlich aufgetaucht sind. "},
					createDynamicBody(3414, 1802, 32, 48, "npc")));
			Npc.add(new NPC(5393, 4149,"grafiken/Kobold.png", new String[]{"[Gabrius] Guten Tag " +name+ "!!\n","Ich hoffe du kannst uns bei dem Skelett im Süden helfen.",  "Es bereitet uns schon seit einer ganzen Weile große Schwierigkeiten."} ,
					createDynamicBody(5393, 4149, 32, 48, "npc")));
			Npc.add(new Speicherstein(114*32, 27*32, "savepoint.png", new String[]{"Drücke K zum speichern."},createDynamicBody(114*32, 27*32, 32, 48, "npc")));
		
			
		}
		if(mapIndex==3){
			Npc.add(new Speicherstein( 34*32,181*32 , "savepoint.png", new String[]{"Drücke K zum speichern."},createDynamicBody(34*32, 32*181, 32, 48, "npc")));
		
			Npc.add(new AuktionsHausNPC(1959, 769, "grafiken/Kobold.png",
					"Sprich mich an wenn du ins Auktionshaus möchtest!", createDynamicBody(1959, 769, 32, 48, "npc"), gsm, this, testInventar));
			Npc.add(new NPC(2036, 775, "grafiken/Kobold.png", new String[]{"[Tsurio]  " + "Du willst es mit dem dunklen König aufnehmen!?\nEinen so mutigen Abenteurer hab ich schon lange nicht mehr gesehen.\nWie ist dein Name?","Nun"+name+"\nIch kann dir nur von deinem Vorhaben abraten.\nJedoch scheinen sich die meisten Monster weit im Norden aufzuhalten...."},createDynamicBody(2036, 775, 32, 48, "npc")));
			Npc.add(new NPC(1604, 2274, "grafiken/Kobold.png", new String[]{"[Yasunda]  " + "Bei all dem Chaos tut es doch auch mal gut sich etwas zu entspannen."},
							createDynamicBody(1604, 2274, 32, 48, "npc")));				
			Npc.add(new NPC(1042, 3573, "grafiken/Kobold.png", new String[]{"[Mamorio]  " + "Warte, Reisender.\nJenseits dieses Felspfades gibt es nur noch Verderben.\nDies scheint das Werk des dunklen Königs zu sein.","Du sagst, du bist"+name+"?\nVon dir habe ich tatsächlich schon gehört.\nNun, wenn man es dir nicht ausreden kann,\nbin ich machtlos."},
					createDynamicBody(1042, 3573, 32, 48, "npc")));		
		}

	}
	private void initGegner(int mapIndex) {
		gegnerList = new LinkedList<Gegner>();
		Attributes ork = new Attributes(5, 5, 5, 5, 5, 5, 5, 0.5f);
		Attributes sch = new Attributes(1, 1, 1, 1, 1, 1, 1, 0.9f);
		Attributes ske = new Attributes(10, 10, 10, 10, 10, 5, 10, 0.4f);
		Attributes boss = new Attributes(10, 10, 10, 20, 10, 20, 10, 1);
		if (mapIndex == 1){
			Attributes tut = new Attributes(0,0,0,10,0,0,0,0);
			Ork TutorialOrk = new Ork(400, 400, s.getGegnerAnimation(2), collisionLayer, 40, tut, createDynamicBody(400, 400, 64, 64, "gegner"));
			gegnerList.add(TutorialOrk);
			Ork[] Ork = new Ork[14];
			Ork[0] = new Ork(832, 1216, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(832, 1216, 64, 64, "gegner"));
			Ork[0].addLoot(EquipmentType.Stoffschuh);
			Ork[1] = new Ork(1088, 1792, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(1088, 1792, 64, 64, "gegner"));
			Ork[1].addLoot(EquipmentType.Dolch);
			Ork[2] = new Ork(4160, 192, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(4160, 192, 64, 64, "gegner"));
			Ork[2].addLoot(EquipmentType.Kupferhelm);
			Ork[3] = new Ork(448, 2624, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(448, 2624, 64, 64, "gegner"));
			Ork[4] = new Ork(1600, 2976, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(1600, 2976, 64, 64, "gegner"));
			Ork[4].addLoot(EquipmentType.Lederhandschuh);
			Ork[5] = new Ork(1632, 2272, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(1632, 2272, 64, 64, "gegner"));
			Ork[5].addLoot(EquipmentType.Lederrüstung);
			Ork[6] = new Ork(2816, 1632, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(2816, 1632, 64, 64, "gegner"));
			Ork[6].addLoot(EquipmentType.Stoffschuh);
			Ork[7] = new Ork(4384, 128, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(4384, 128, 64, 64, "gegner"));
			Ork[7].addLoot(EquipmentType.Kupferhelm);
			Ork[8] = new Ork(4352, 1024, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(4352, 1024, 64, 64, "gegner"));
			Ork[8].addLoot(EquipmentType.Dolch);
			Ork[9] = new Ork(4788, 1984, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(4788, 1984, 64, 64, "gegner"));
			Ork[9].addLoot(EquipmentType.Lederhandschuh);
			Ork[10] = new Ork(3488, 2848, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(3488, 2848, 64, 64, "gegner"));
			Ork[10].addLoot(EquipmentType.Lederrüstung);
			Ork[11] = new Ork(3040, 3456, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(3040, 3456, 64, 64, "gegner"));
			Ork[12] = new Ork(4320, 2144, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(4320, 2144, 64, 64, "gegner"));
			Ork[13] = new Ork(2880, 2976, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(2880, 2976, 64, 64, "gegner"));
			
			for(int i = 0; i < Ork.length; i++)
				gegnerList.add(Ork[i]);
			
			if(!c.getBosseBesiegt()[0]){
				OrkEndgegner Boss = new OrkEndgegner(4185, 3072, s.getGegnerAnimation(2), collisionLayer, 200, boss, createDynamicBody(4352, 608, 64, 64, "gegner"));
				Boss.addLoot(EquipmentType.Lederrüstung);
				gegnerList.add(Boss);
				//136,93
			}
		}
		else if(mapIndex == 2){
			Ork[] Ork = new Ork[7];
			Ork[0] = new Ork(768, 512, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(768, 512, 64, 64, "gegner"));
			Ork[0].addLoot(EquipmentType.Stoffschuh);
			Ork[1] = new Ork(736, 1088, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(736, 1088, 64, 64, "gegner"));
			Ork[1].addLoot(EquipmentType.Dolch);
			Ork[2] = new Ork(832, 1696, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(832, 1696, 64, 64, "gegner"));
			Ork[2].addLoot(EquipmentType.Kupferhelm);
			Ork[3] = new Ork(224, 2560, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(224, 2560, 64, 64, "gegner"));
			Ork[4] = new Ork(3648, 2944, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(3648, 2944, 64, 64, "gegner"));
			Ork[4].addLoot(EquipmentType.Lederhandschuh);
			Ork[5] = new Ork(4864, 1760, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(4864, 1760, 64, 64, "gegner"));
			Ork[6] = new Ork(4992, 1760, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(4992, 1760, 64, 64, "gegner"));
			Ork[6].addLoot(EquipmentType.Kupferhelm);
			
			Skelett[] Skelett = new Skelett[33];
			Skelett[0] = new Skelett(352, 800, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(352, 800, 32, 48, "gegner"));
			Skelett[0].addLoot(EquipmentType.Lederschuh);
			Skelett[1] = new Skelett(896, 1184, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(896, 1184, 32, 48, "gegner"));
			Skelett[2] = new Skelett(416, 1344, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(416, 1344, 32, 48, "gegner"));
			Skelett[2].addLoot(EquipmentType.Kettenhemd);
			Skelett[3] = new Skelett(608, 1888, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(608, 1888, 32, 48, "gegner"));
			Skelett[3].addLoot(EquipmentType.Lederhandschuh);
			Skelett[4] = new Skelett(1056, 1568, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(1056, 1568, 32, 48, "gegner"));
			Skelett[4].addLoot(EquipmentType.Eisenhandschuh);
			Skelett[5] = new Skelett(992, 1952, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(992, 1952, 32, 48, "gegner"));
			Skelett[6] = new Skelett(736, 3488, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(736, 3488, 32, 48, "gegner"));
			Skelett[6].addLoot(EquipmentType.Eisenhelm);
			Skelett[7] = new Skelett(160, 1888, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(160, 1888, 32, 48, "gegner"));
			Skelett[7].addLoot(EquipmentType.Lederschuh);
			Skelett[8] = new Skelett(32, 2240, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(32, 2240, 32, 48, "gegner"));
			Skelett[8].addLoot(EquipmentType.Schwert);
			Skelett[9] = new Skelett(96, 3808, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(96, 3808, 32, 48, "gegner"));
			Skelett[9].addLoot(EquipmentType.Dolch);
			Skelett[10] = new Skelett(160, 3776, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(160, 3776, 32, 48, "gegner"));
			Skelett[10].addLoot(EquipmentType.Lederrüstung);
			Skelett[11] = new Skelett(256, 3872, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(256, 3872, 32, 48, "gegner"));
			Skelett[12] = new Skelett(2208, 3936, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(2208, 3936, 32, 48, "gegner"));
			Skelett[12].addLoot(EquipmentType.Eisenhelm);
			Skelett[13] = new Skelett(2432, 4032, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(2432, 4032, 32, 48, "gegner"));
			Skelett[14] = new Skelett(2400, 3488, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(2400, 3488, 32, 48, "gegner"));
			Skelett[15] = new Skelett(2720, 3200, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(2720, 3200, 32, 48, "gegner"));
			Skelett[15].addLoot(EquipmentType.Stoffschuh);
			Skelett[16] = new Skelett(2816, 4224, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(2816, 4224, 32, 48, "gegner"));
			Skelett[17] = new Skelett(3488, 4032, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(3488, 4032, 32, 48, "gegner"));
			Skelett[17].addLoot(EquipmentType.Eisenhandschuh);
			Skelett[18] = new Skelett(3552, 2848, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(3552, 2848, 32, 48, "gegner"));
			Skelett[19] = new Skelett(3840, 2560, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(3840, 2560, 32, 48, "gegner"));
			Skelett[20] = new Skelett(4032, 4032, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4032, 4032, 32, 48, "gegner"));
			Skelett[20].addLoot(EquipmentType.Kettenhemd);
			Skelett[21] = new Skelett(4288, 4320, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4288, 4320, 32, 48, "gegner"));
			Skelett[21].addLoot(EquipmentType.Kupferhelm);
			Skelett[22] = new Skelett(4512, 3264, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4512, 3264, 32, 48, "gegner"));
			Skelett[23] = new Skelett(5312, 3264, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(5312, 3264, 32, 48, "gegner"));
			Skelett[23].addLoot(EquipmentType.Schwert);
			Skelett[24] = new Skelett(5376, 2752, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(5376, 2752, 32, 48, "gegner"));
			Skelett[25] = new Skelett(4640, 2624, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4640, 2624, 32, 48, "gegner"));
			Skelett[26] = new Skelett(4832, 1664, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4832, 1664, 32, 48, "gegner"));
			Skelett[26].addLoot(EquipmentType.Lederschuh);
			Skelett[27] = new Skelett(5024, 1664, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(5024, 1664, 32, 48, "gegner"));
			Skelett[28] = new Skelett(4832, 1664, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4832, 1664, 32, 48, "gegner"));
			Skelett[28].addLoot(EquipmentType.Eisenhandschuh);
			Skelett[29] = new Skelett(4832, 1664, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4832, 1664, 32, 48, "gegner"));
			Skelett[30] = new Skelett(4832, 1664, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4832, 1664, 32, 48, "gegner"));
			Skelett[30].addLoot(EquipmentType.Kettenhemd);
			Skelett[31] = new Skelett(4832, 1664, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4832, 1664, 32, 48, "gegner"));
			Skelett[32] = new Skelett(4832, 1664, s.getGegnerAnimation(3), collisionLayer, 80, ske, createDynamicBody(4832, 1664, 32, 48, "gegner"));
			Skelett[32].addLoot(EquipmentType.Lederschuh);
			
			for(int i = 0; i < Ork.length; i++)
				gegnerList.add(Ork[i]);
			for(int i = 0; i < Skelett.length; i++)
				gegnerList.add(Skelett[i]);

			boss = new Attributes(40,40,40,30,30,30,30,1);
			
			if(!c.getBosseBesiegt()[1]){
				SkelettEndgegner Boss2 = new SkelettEndgegner(2592, 544, s.getGegnerAnimation(3), collisionLayer, 200, boss, createDynamicBody(2592, 544, 32, 48, "gegner"));
				Boss2.addLoot(EquipmentType.Schwert);
				gegnerList.add(Boss2);
				//81,123
			}
		}
		else if(mapIndex == 3){
			boss = new Attributes(80,80,80,40,40,40,40,2);
			
			Ork[] Ork = new Ork[4];
			Ork[0] = new Ork(4640, 992, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(4640, 992, 64, 64, "gegner"));
			Ork[0].addLoot(EquipmentType.Lederstiefel);
			Ork[1] = new Ork(544, 3680, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(544, 3680, 64, 64, "gegner"));
			Ork[2] = new Ork(2528, 4544, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(2528, 4544, 64, 64, "gegner"));
			Ork[2].addLoot(EquipmentType.Lederschuh);
			Ork[3] = new Ork(5280, 4480, s.getGegnerAnimation(2), collisionLayer, 40, ork, createDynamicBody(5280, 4480, 64, 64, "gegner"));
			
			GruenerSchleim[] GruenerSchleim = new GruenerSchleim[18];
			GruenerSchleim[0] = new GruenerSchleim(640, 832, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(640, 832, 35, 32, "gegner"));
			GruenerSchleim[1] = new GruenerSchleim(2656, 512, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(2656, 512, 35, 32, "gegner"));
			GruenerSchleim[1].addLoot(EquipmentType.Stahlschwert);
			GruenerSchleim[2] = new GruenerSchleim(3712, 736, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(3712, 736, 35, 32, "gegner"));
			GruenerSchleim[2].addLoot(EquipmentType.Eisenhelm);
			GruenerSchleim[3] = new GruenerSchleim(4512, 3104, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(4512, 3104, 35, 32, "gegner"));
			GruenerSchleim[4] = new GruenerSchleim(4224, 3008, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(4224, 3008, 35, 32, "gegner"));
			GruenerSchleim[4].addLoot(EquipmentType.Stahlhelm);
			GruenerSchleim[5] = new GruenerSchleim(3264, 2784, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(3264, 2784, 35, 32, "gegner"));
			GruenerSchleim[5].addLoot(EquipmentType.Stahlhandschuh);
			GruenerSchleim[6] = new GruenerSchleim(160, 3328, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(160, 3328, 35, 32, "gegner"));
			GruenerSchleim[6].addLoot(EquipmentType.Kettenhemd);
			GruenerSchleim[7] = new GruenerSchleim(1152, 5056, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(1152, 5056, 35, 32, "gegner"));
			GruenerSchleim[8] = new GruenerSchleim(4672, 4480, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(4672, 4480, 35, 32, "gegner"));
			GruenerSchleim[8].addLoot(EquipmentType.Stahlrüstung);
			GruenerSchleim[9] = new GruenerSchleim(4416, 4736, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(4416, 4736, 35, 32, "gegner"));
			GruenerSchleim[10] = new GruenerSchleim(4768, 4768, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(4768, 4768, 35, 32, "gegner"));
			GruenerSchleim[10].addLoot(EquipmentType.Lederstiefel);
			GruenerSchleim[11] = new GruenerSchleim(4640, 4224, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(4640, 4224, 35, 32, "gegner"));
			GruenerSchleim[11].addLoot(EquipmentType.Stahlhelm);
			GruenerSchleim[12] = new GruenerSchleim(5568, 4256, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(5568, 4256, 35, 32, "gegner"));
			GruenerSchleim[13] = new GruenerSchleim(4096, 5472, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(4096, 5472, 35, 32, "gegner"));
			GruenerSchleim[14] = new GruenerSchleim(4096, 5376, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(4096, 5376, 35, 32, "gegner"));
			GruenerSchleim[14].addLoot(EquipmentType.Stahlhandschuh);
			GruenerSchleim[15] = new GruenerSchleim(2336, 5824, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(2336, 5824, 35, 32, "gegner"));
			GruenerSchleim[16] = new GruenerSchleim(2208, 5696, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(2208, 5696, 35, 32, "gegner"));
			GruenerSchleim[16].addLoot(EquipmentType.Lederschuh);
			GruenerSchleim[17] = new GruenerSchleim(2432, 5600, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(2432, 5600, 35, 32, "gegner"));
			GruenerSchleim[17].addLoot(EquipmentType.Stahlschwert);
			
			for(int i = 0; i < Ork.length; i++)
				gegnerList.add(Ork[i]);
			for(int i = 0; i < GruenerSchleim.length; i++)
				gegnerList.add(GruenerSchleim[i]);
			
			if(!c.getBosseBesiegt()[2]){
				SchleimEndgegner Boss3 = new SchleimEndgegner(5440, 5600, s.getGegnerAnimation(1), collisionLayer, 200, boss, createDynamicBody(5440, 5600, 35, 32, "gegner"));
				Boss3.addLoot(EquipmentType.Stahlschwert);
				gegnerList.add(Boss3);
				//170,10
			}
			if(!c.getBosseBesiegt()[3]){
				Endboss Boss4= new Endboss(256, 5632, s.getAnimation(1), collisionLayer, 200, boss, createDynamicBody(256, 5632, 32, 48, "gegner"), c);
				Boss4.addLoot(EquipmentType.Stahlhelm);
				gegnerList.add(Boss4);
				//8,9 = 256,5632
			}
		}

	}

	@Override
	protected void handleInput() {
		
		// Update der SkillLevel und des Inventars in Character --Dom--
		c.updateSkillLevel();
		c.setAllItems(testInventar);

		if (!pauseToInventory && Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			if(inventoryState != null)
				inventoryState.dispose();
			inventoryState = null;
			gsm.push(new PauseState(gsm,this, auktionshausClient));
		}
		
		//toogle inventoryState
		if (Gdx.input.isKeyJustPressed(Keys.I) || (pauseToInventory && Gdx.input.isKeyJustPressed(Keys.ESCAPE))) {
			if(inventoryState == null)
				inventoryState = new InventoryState(gsm, this);
			pauseToInventory = !pauseToInventory;
			inventoryState.updateImages();
		}
		
		//input halted
		if(pauseToInventory)
			return;


		// Speichern des aktuellen Spielgeschehens --Dom--
//		if (Gdx.input.isKeyJustPressed(Keys.K)) {
//			if (de.SEPL.GameScore.GameScoreManagement.saveGameScore(c) == true) {
//				System.out.println("Speichern erfolgreich.");
//			}
//		}

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			if (((boolean[]) c.getBody().getUserData())[0]
					&& (c.getRichtung() == AnimationDirection.NORTH_ATTACK
							|| c.getRichtung() == AnimationDirection.NORTH_STAND
							|| c.getRichtung() == AnimationDirection.NORTH_WALK)
					&& c.getSkills().get(0).getCdnow() < 0.1 && c instanceof Krieger) {
				((Krieger) c).getUntouchable()[0] = true;
				((Krieger) c).setUntouchableTime(0.5f);
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
				((Krieger) c).getUntouchable()[1] = true;
				((Krieger) c).setUntouchableTime(0.5f);
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
				((Krieger) c).getUntouchable()[2] = true;
				((Krieger) c).setUntouchableTime(0.5f);
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
				((Krieger) c).getUntouchable()[3] = true;
				((Krieger) c).setUntouchableTime(0.5f);
				for (Gegner g : gegnerList) {
					if (((boolean[]) g.getBody().getUserData())[2]) {
						g.getDamage(c.getSkills().get(0).getDmg());
					}
				}
			}
			if (c.position.x <= 4500 && c.position.x >=4330 && c.position.y >= 2378 && c.position.y <= 2572 && c.getMapIndex() == 1 ) {
				if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					if (c.getBosseBesiegt()[0]) {
						changeMap(2);
					}
					
				}
			}
			if (c.position.x >= 1252 && c.position.x<=1352 && c.position.y >= 1087&&c.position.y <= 1171 && c.getMapIndex() == 2 ) {
				if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					if (c.getBosseBesiegt()[1]) {
						changeMap(3);
					}
				}	//4448   2508
			}
		}
		if (c.getBosseBesiegt()[0] &&c.getBosseBesiegt()[1] &&c.getBosseBesiegt()[2]&&c.getBosseBesiegt()[3]
				&& keys.alle==false) {
			gsm.push(new NotAllKeysWinState(gsm));
		}
		if (c.getBosseBesiegt()[0] &&c.getBosseBesiegt()[1] &&c.getBosseBesiegt()[2]&&c.getBosseBesiegt()[3]
				&& keys.alle==true ) {
			gsm.push(new AllKeysWinState(gsm));
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		
		if(pauseToInventory) {
			inventoryState.update(dt);
			return;
		}
		
		
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
		
		if (c.getMapIndex()==1 && c.getBosseBesiegt()[0]) {
			sb.draw(new Texture("grafiken/treppe.png"),4448,2508);
		}
		else if (c.getMapIndex()==2 &&  c.getBosseBesiegt()[1]) {
			sb.draw(new Texture("grafiken/treppe.png"),1282,1107);
		} else {

		}
		if(c.getCurrentHP()==0){
			System.out.println("Verloren");
			gsm.push(new VerlorenState(gsm));
		}
		
		// KOBOLD DORF LABEL//
		if (c.getPosition().x > 1595 && c.getPosition().x < 1796 && c.getPosition().y > 0 && c.getPosition().y < 1000
				&& !besucht && c.getMapIndex()==1) {
			sb.draw(Kobolddorflabel, Gdx.graphics.getWidth() / 2+300, Gdx.graphics.getHeight() / 2);
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
			p.render(this,sb, c);
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

		if(!pauseToInventory) {
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
	
			if (Gdx.input.isKeyPressed(Keys.UP) && cam.zoom > 0.3) {
				cam.zoom -= 0.01f;
			} else if (Gdx.input.isKeyPressed(Keys.DOWN) && cam.zoom < 1) {
				cam.zoom += 0.01f;
			}
			
		} else
			sb.draw(currentFrame, c.getPosition().x, c.getPosition().y);
		
		/**
		 * KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA KAMERA
		 */
		for (NPC n : Npc) {
			if(!(n instanceof AuktionsHausNPC))
			n.drawDia(sb);
		}
		sb.end();

		//HP
		sr.setProjectionMatrix(sb.getProjectionMatrix());
		sr.begin();

		drawHP(c);
		for(Gegner g : gegnerList)
			drawHP(g);
		
		sr.end();
//		b2dr.render(world, cam.combined);
		
		if(pauseToInventory)
			inventoryState.render(sb);
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
		inventoryState.dispose();
	}

	public void changeMap(int i) {
		c.setMapIndex(i); // --Dom--
		if (i == 2){
			map.setMap(new TmxMapLoader().load("grafiken/bereich2.tmx"));
			collisionLayer = new TiledMapTileLayer[4];
			collisionLayer[0] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte");
			collisionLayer[1] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte2");
			collisionLayer[2] = (TiledMapTileLayer) map.getMap().getLayers().get("Boden");
			collisionLayer[3] = (TiledMapTileLayer) map.getMap().getLayers().get("Boden2");
		}
		else if (i == 3){
			map.setMap(new TmxMapLoader().load("grafiken/map3.1.tmx"));
			collisionLayer = new TiledMapTileLayer[3];
			collisionLayer[0] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte");
			collisionLayer[1] = (TiledMapTileLayer) map.getMap().getLayers().get("Objekte2");
			collisionLayer[2] = (TiledMapTileLayer) map.getMap().getLayers().get("Boden2");
		}
		
		map.setRenderer();
		c.position = new Vector3(0, 0, 0);
		c.setCollisionLayer(collisionLayer);
		for (Skill s : c.getSkills()){
			s.setCollisionLayer(collisionLayer);
		}
		for (Gegner g : gegnerList) {
			if(g.getBody() != null)
				world.destroyBody(g.getBody());
		}
		initGegner(i);
		for (NPC n : Npc) {
			if(n.getBody() != null)
				world.destroyBody(n.getBody());
		}
		initnpcs(i);
		for (Truhe t : truhenListe) {
			if(t.getBody() != null)
				world.destroyBody(t.getBody());
		}
		truhenListe = new LinkedList<Truhe>();
		PortalListe = new LinkedList<Portal>();
		
		if(inventoryState != null)
			inventoryState.dispose();
		inventoryState = null;
	}

	public Body createDynamicBody(int x, int y, int w, int h, String a) { // String a =
															// "gegner",
															// "charakter"
															// oder"npc"
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		bdef.position.set(x + w/2, y + h/2);
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
		shape.setAsBox(w/2-3, 6, new Vector2(0, -h/2+2), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("south");
		shape.setAsBox(w/2-3, 6, new Vector2(0, h/2-2), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("north");
		shape.setAsBox(6, h/2-3, new Vector2(w/2-2, 0), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("east");
		shape.setAsBox(6, h/2-3, new Vector2(-w/2+2, 0), 0);
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
		t.setBody(null);
	}

	public void killGegner(Gegner g) {
		if(g instanceof OrkEndgegner)
			c.getBosseBesiegt()[0]=true;
		if(g instanceof SkelettEndgegner)
			c.getBosseBesiegt()[1]=true;
		if(g instanceof SchleimEndgegner)
			c.getBosseBesiegt()[2]=true;
		if(g instanceof Endboss)
			c.getBosseBesiegt()[3]=true;
		world.destroyBody(g.getBody());
		g.killed();
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

	
	public void resetInventoryState() {
		if(inventoryState != null)
			inventoryState.dispose();
		inventoryState = null;
	}

}
