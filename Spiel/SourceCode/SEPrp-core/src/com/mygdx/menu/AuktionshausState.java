package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Author;
import com.mygdx.game.MyGdxGame;

@Author(name = "Bijan Shahbaz Nejad")


public class AuktionshausState extends State{

	private Skin skin;
	private TextureAtlas atlas;
	private Stage stage;
	private Table table;
	private TextButton buttonJ, buttonN ,buttonX;
	private BitmapFont white;
	private Label label;	
	
	
PlayState playstate;	
	public AuktionshausState(GameStateManager gsm,PlayState ps) {
		super(gsm);
	playstate=ps;
	
	
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

	buttonJ = new TextButton("Kaufen", textButtonStyle);
	buttonJ.pad(15);

	buttonN = new TextButton("Verkaufen", textButtonStyle);
	buttonN.pad(15);

	buttonX = new TextButton("Verlassen", textButtonStyle);
	buttonX.pad(20);
	
	LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);

	label = new Label("Auktionshaus", labelStyle);
	label.setFontScale(2.2f);
	Image Rahmen = new Image(new Texture("userInterface/border2.png"));
	Rahmen.setPosition(0, Gdx.graphics.getHeight() * 0.1f + buttonJ.getMinHeight() * 1.5f - 200);
	Rahmen.setWidth(Gdx.graphics.getWidth() * 0.95f);
	Rahmen.setHeight(Gdx.graphics.getHeight() * 1.0f);
	
	
	table.add(label).width(100).padBottom(200).padTop(Gdx.graphics.getHeight() / 2 - 50);

	table.row();

	table.add(buttonJ);
	table.add(buttonN);
	table.add(buttonX).padLeft(200);
	
	
	table.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

	Image img = new Image(new Texture("userInterface/dark background.png"));
	img.setFillParent(true);
	Rahmen.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

	stage.addActor(img);
	stage.addActor(Rahmen);
	stage.addActor(table);
	}

	@Override
	public void handleInput() {
		if (buttonX.isChecked()) {
			gsm.push(playstate);
		}
		if (buttonJ.isChecked()) {
			gsm.push(new KaufenState(gsm,playstate,new String[]{"ABC"}/*HIER MUSS DAS STRING-ARRAY REIN*/)); // MAN MUSS HIER DIE LISTE AUS DEM AUKTIONSHAUS ÜBERGEBEN
		}
		if (buttonN.isChecked()) {
			gsm.push(new VerkaufenState(gsm,playstate)); // MAN MUSS HIER DIE LISTE AUF DEM INVENTAR ÜBERGEBEN
		}
	}

	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}
	public void render(SpriteBatch sb) {
		stage.act();
	
		stage.draw();
	}

	public void dispose() {
		// TODO Auto-generated method stub
		this.dispose();
	}

}
