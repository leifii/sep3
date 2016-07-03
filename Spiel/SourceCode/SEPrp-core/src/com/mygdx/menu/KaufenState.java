package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Author;

import de.SEPL.ServerClient.IAuktionshausClient;

@Author(name = "Bijan Shahbaz Nejad")

public class KaufenState extends State {
	private Skin skin;
	private TextureAtlas atlas;
	private Stage stage;
	private Table table;
	private TextButton buttonJ, buttonN, buttonK;
	private BitmapFont white;
	private Label label;
	public PlayState PS;
	public VerkaufenState vs;
	public AuktionshausItem testitem[];
	IAuktionshausClient auktionshausClient = new de.SEPL.ServerClient.FileClient();
	IInventar inventar = new com.mygdx.menu.testInventar();
	public String[] auktionshausContent;


	// PlayState playstate;
	public KaufenState(GameStateManager gsm, PlayState ps, IAuktionshausClient auktionshausClient, IInventar inventar) {
		super(gsm);
		PS = ps;
		
		// --Dom--
		this.auktionshausClient = auktionshausClient;
		this.inventar = inventar;
		
		auktionshausContent = this.auktionshausClient.getContent();

		testitem = new AuktionshausItem[auktionshausContent.length];

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("testb/Texturen.pack");
		skin = new Skin(atlas);

		table = new Table(skin);

		table.setWidth(Gdx.graphics.getWidth() * 0.9f);
		table.align(Align.center | Align.top);
		table.setPosition(0, Gdx.graphics.getHeight());

		white = new BitmapFont(Gdx.files.internal("white.fnt"));

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("blank-2");
		// textButtonStyle.down=skin.getDrawable("blank-3");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = white;

		TextButtonStyle ConfirmButtonStyle = new TextButtonStyle();
		ConfirmButtonStyle.up = skin.getDrawable("blank-2");
		ConfirmButtonStyle.down = skin.getDrawable("blank-3");
		ConfirmButtonStyle.pressedOffsetX = 1;
		ConfirmButtonStyle.pressedOffsetY = -1;
		ConfirmButtonStyle.font = white;

		LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);

		for (int j = 0; j < testitem.length; j++) {

			testitem[j] = new AuktionshausItem(textButtonStyle, labelStyle, auktionshausContent[j],this, this.inventar);

		}
		buttonJ = new TextButton("Zurück", textButtonStyle);
		buttonJ.pad(20);

		table.debug();
		Label überschrift = new Label("Items Kaufen: ", labelStyle);
		überschrift.setFontScale(2.0f);

		table.add(buttonJ).padRight(450).padTop(50);
		table.add(überschrift);
		table.row();
		table.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

		Image img = new Image(new Texture("userInterface/dark background.png"));
		img.setFillParent(true);

		stage.addActor(img);
		stage.addActor(table);
	}

	@Override
	public void handleInput() {
		// if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
		// gsm.push(new AuktionshausState(gsm, PS));
		// }
		// if (buttonN.isChecked()) {
		// }

		if (buttonJ.isChecked()) {
			gsm.push(new AuktionshausState(gsm, PS, inventar));
		}

	}

	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	public void render(SpriteBatch sb) {
		stage.act();
		stage.draw();

		for (int i = 0; i < testitem.length; i++) {
			testitem[i].add(table, this, auktionshausClient);
		}

	}

	public void dispose() {
		// TODO Auto-generated method stub
		this.dispose();
	}

}


//package com.mygdx.menu;
//
//import java.util.ArrayList;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.actions.Actions;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
//import com.badlogic.gdx.utils.Align;
//import com.mygdx.game.Author;
//
//@Author(name = "Bijan Shahbaz Nejad")
//
//public class KaufenState extends State{
//	private Skin skin;
//	private TextureAtlas atlas;
//	private Stage stage;
//	private Table table;
//	private TextButton buttonJ;
//	private BitmapFont white;
//	private Label label;	
//	public PlayState PS;
//	
//	
//	public AuktionshausItem testitem[];
////PlayState playstate;	
//	public KaufenState(GameStateManager gsm,PlayState ps) {
//		super(gsm);
//		testitem=new AuktionshausItem[]{
//				new AuktionshausItem(0, 120),
//				new AuktionshausItem(1, 330),
//				new AuktionshausItem(2, 55),
//				new AuktionshausItem(3, 525),
//				new AuktionshausItem(4, 855),
//				new AuktionshausItem(5, 505),
//				new AuktionshausItem(6, 525),
//				new AuktionshausItem(7, 855),
//				new AuktionshausItem(8, 505),
//				new AuktionshausItem(9,	192 )
//		};
//		
//		
//		for (int i = 0; i < testitem.length; i++) {
//		
//		}
//	
//		PS=ps;
//	
//	
//	stage = new Stage();
//	Gdx.input.setInputProcessor(stage);
//	
//	atlas = new TextureAtlas("testb/Texturen.pack");
//	skin = new Skin(atlas);
//	
//	table = new Table(skin);
//	
//	table.setWidth(Gdx.graphics.getWidth() * 0.9f);
//	table.align(Align.center | Align.top);
//	table.setPosition(0, Gdx.graphics.getHeight());
//
//	white = new BitmapFont(Gdx.files.internal("white.fnt"));
//
//	TextButtonStyle textButtonStyle = new TextButtonStyle();
//	textButtonStyle.up = skin.getDrawable("blank-2");
//	// textButtonStyle.down=skin.getDrawable("blank-3");
//	textButtonStyle.pressedOffsetX = 1;
//	textButtonStyle.pressedOffsetY = -1;
//	textButtonStyle.font = white;
//
//	TextButtonStyle ConfirmButtonStyle = new TextButtonStyle();
//	ConfirmButtonStyle.up = skin.getDrawable("blank-2");
//	ConfirmButtonStyle.down = skin.getDrawable("blank-3");
//	ConfirmButtonStyle.pressedOffsetX = 1;
//	ConfirmButtonStyle.pressedOffsetY = -1;
//	ConfirmButtonStyle.font = white;
//
//	buttonJ = new TextButton("Zurück", textButtonStyle);
//	buttonJ.pad(10);
//
////	buttonN = new TextButton("xyz", textButtonStyle);
////	buttonN.pad(10);
//
//	
//	LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);
//
//	label = new Label("Kaufen", labelStyle);
//	label.setFontScale(2.0f);
//	Image Rahmen = new Image(new Texture("userInterface/Inventar.png"));
//	Rahmen.setPosition(0, Gdx.graphics.getHeight() * 0.1f + buttonJ.getMinHeight() * 1.5f - 200);
//	Rahmen.setWidth(Gdx.graphics.getWidth() * 1.0f);
//	Rahmen.setHeight(Gdx.graphics.getHeight() * 1.0f);
//
//	table.add(buttonJ).padRight(450).padTop(50);
//	table.add(label).padRight(250).padTop(50);
//
//	
//	table.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
//
//	Image img = new Image(new Texture("userInterface/dark background.png"));
//	img.setFillParent(true);
//	Rahmen.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
//
//	stage.addActor(img);
//	stage.addActor(Rahmen);
//	stage.addActor(table);
//	}
//
//	
//	@Override	
//	public void handleInput() {
//		if (buttonJ.isChecked()) {
//			gsm.push(new AuktionshausState(gsm, PS));
//		}
//	}
//
//	public void update(float dt) {
//		// TODO Auto-generated method stub
//		handleInput();
//		System.out.println("X:"+Gdx.input.getX()+" Y: "+Gdx.input.getY());
//	}
//
//	public void render(SpriteBatch sb) {
//	
//		stage.act();
//		
//
//for (int i = 0; i < testitem.length; i++) {
//	
//	
//	
////	if (Gdx.input.getX()>testitem[i].position.x && testitem[i].position.x<(Gdx.input.getX()+172)/* && Gdx.input.getY()>testitem[i].position.y && testitem[i].position.y<(Gdx.input.getY()-190)*/) {
////		if (Gdx.input.isTouched()) {
////
////AuktionshausItem []neu=new AuktionshausItem[testitem.length];
////testitem[i]=null;		
////			for (int j = 0; j < testitem.length; j++) {
////			
////				if (testitem[j]!=null) {
////					neu[j]=testitem[j];
////					x--;
////				}
////				
////			}
////			
////			testitem=neu;
////		
//			
////			testitem[i].remove( this,i);
////		}
////	}
////	
////	
//	
//	
//		
//			testitem[i].add(stage);
//				
//		
//		testitem[i].remove(stage);
//}		
//		stage.draw();
//		
//		
//	
//
//}
//
//	
//	public void dispose() {
//	
//		this.dispose();
//	}
//
//}
