package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.npc.DialogNeu;

public class KönigGebenState extends State{
	private Skin skin;
	private TextureAtlas atlas;
	private Stage stage;
	private Table table;
	private TextButton buttonJ;
	private BitmapFont white;
	private Label label;	
	DialogNeu dia;
	String[]a;
	
PlayState playstate;	
	public KönigGebenState(GameStateManager gsm) {
		super(gsm);
		dia=new DialogNeu(a=new String[]{"Geschichte: alle Schlüssel gefunden","versucht nicht Truhe selbst zu öffnen.\n Gebe Schlüssel König","Gold und Essen!!!\nKobolde leben wieder in Frieden und Wohlstand\nganzes Dorf dankbar :)"});
		dia.setZähler(1);
	
	
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

	buttonJ = new TextButton("Hauptmenü", textButtonStyle);
	buttonJ.pad(30);

	
	LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);

	label = new Label("Du wurdest besiegt!", labelStyle);
	label.setFontScale(2.0f);
	Image Rahmen = new Image(new Texture("userInterface/border2.png"));
	Rahmen.setPosition(0, Gdx.graphics.getHeight() * 0.1f + buttonJ.getMinHeight() * 1.5f - 200);
	Rahmen.setWidth(Gdx.graphics.getWidth() * 0.95f);
	Rahmen.setHeight(Gdx.graphics.getHeight() * 1.0f);
	
	
	table.add(label).width(100).padBottom(200).padTop(Gdx.graphics.getHeight() / 2 - 50).padRight(600);

	table.row();

	table.add(buttonJ);
	
	
	
	
	table.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

	Image img = new Image(new Texture("ENDESCHLÜSSELGEBEN.png"));
	img.setFillParent(true);
	Rahmen.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

	stage.addActor(img);
//	stage.addActor(Rahmen);
//	stage.addActor(table);
	}

	@Override
	public void handleInput() {
		
		
		if (buttonJ.isChecked()) {
			gsm.push(new NewMenuState1(gsm));
		}
	}

	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}
	public void render(SpriteBatch sb) {
		stage.act();
//	
		stage.draw();
		dia.setGeöffnet(true);
		sb.begin();
		dia.draw(sb, 0.5f);
		if(dia.getZähler()>=a.length)
		{
			Gdx.app.exit();
		}
		sb.end();
	}

	public void dispose() {
		// TODO Auto-generated method stub
		this.dispose();
	}

}
