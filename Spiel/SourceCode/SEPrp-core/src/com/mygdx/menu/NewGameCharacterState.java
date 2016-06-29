package com.mygdx.menu;

import com.android.ide.common.rendering.api.SessionParams.Key;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ai.steer.behaviors.Alignment;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader.AlignMode;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Author;
import com.mygdx.game.MyGdxGame;

import de.SEPL.GameScore.GameScoreManagement;

@Author(name = "Bijan Shahbaz Nejad , Angelo Soltner")

public class NewGameCharacterState extends NewMenuState {
	PlayState playstate;
	Texture back;
	private MainMenuButton loadbutton;
	private MainMenuButton newgamebutton;
	private Skin skin;
	private TextureAtlas atlas;
	private Stage stage;
	private Table table;
	private TextButton buttonJ, buttonN, buttonM, buttonK;
	private BitmapFont white;
	private Label label;
	private Drawable drawable;

	public NewGameCharacterState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		back = new Texture("back.jpg");
		loadbutton = new MainMenuButton(1728 / 2 - 77, 1080 / 2 - 66, "loadbutton.jpg");
		newgamebutton = new MainMenuButton(1728 / 2 - 77, 1080 / 2 - 172, "newgamebutton.jpg");
		beendenbutton = new MainMenuButton(1728 / 2 - 77, 1080 / 2 - 280, "beendenbutton.jpg");

		///////////////////////////////////////////////// CHARAKTERAUSWAHL////////////////////////////////
		///////////////////////////////////////////////// BEGIN

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
		textButtonStyle.down = skin.getDrawable("blank-3");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = white;
		
		
		
		// NinePatch patch= new NinePatch(new
		// Texture("userInterface/border2.9.png"));

		buttonJ = new TextButton("Krieger", textButtonStyle);
		buttonJ.pad(20);

		buttonN = new TextButton("Magier", textButtonStyle);
		buttonN.pad(20);

		buttonM = new TextButton("Schurke", textButtonStyle);
		buttonM.pad(20);

		buttonK = new TextButton("Schütze", textButtonStyle);
		buttonK.pad(20);

		LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);

		label = new Label("Wähle mit welcher Klasse du spielen moechtest", labelStyle);

		label.setFontScale(1.2f);
		Image Rahmen = new Image(new Texture("userInterface/border2.png"));
		Rahmen.setPosition(0, Gdx.graphics.getHeight() * 0.1f + buttonJ.getMinHeight() * 1.5f);
		Rahmen.setWidth(Gdx.graphics.getWidth() * 1.1f);
		Rahmen.setHeight(Gdx.graphics.getHeight() * 0.5f);

		table.add(label).width(100).padBottom(100).padTop(Gdx.graphics.getHeight() / 2 - 50);
		table.row();
		table.add(buttonJ);
		table.add(buttonJ);
		table.add(buttonN);
		table.add(buttonM);
		table.add(buttonK);
		table.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

		Image img = new Image(new Texture("userInterface/dark background.png"));
		img.setFillParent(true);
		Rahmen.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

		stage.addActor(img);
		stage.addActor(Rahmen);
		stage.addActor(table);

		///////////////////////////////////////////////// CHARAKTERAUSWAHL////////////////////////////////
		///////////////////////////////////////////////// END

		/***/ ///////////////////////////////////////////////// CHARAKTEREDITOR////////////////////////////////
				///////////////////////////////////////////////// BEGIN

		/***/ ///////////////////////////////////////////////// CHARAKTEREDITOR////////////////////////////////
				///////////////////////////////////////////////// END

	}

	@Override
	protected void handleInput() {
		
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new NewMenuState(gsm));
		
		}
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		click=Gdx.audio.newSound(Gdx.files.internal("dragstone.mp3"));
		sb.begin();
		sb.draw(back, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		sb.draw(loadbutton.getTexture(), loadbutton.getPosition().x, loadbutton.getPosition().y);
		sb.draw(newgamebutton.getTexture(), newgamebutton.getPosition().x, newgamebutton.getPosition().y);
		sb.draw(beendenbutton.getTexture(), beendenbutton.getPosition().x, beendenbutton.getPosition().y);

		/////////////////////////// CHARACTEREDITOR////////////////////////////////////////////////////////
		stage.act();
		stage.draw();

		if (Gdx.input.isKeyJustPressed(Keys.NUM_1) || buttonJ.isChecked()) {
//	gsm.push(new PlayState(gsm, 1, 0));
			gsm.push(new CharEditorState(gsm, 1));
			click.play();
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_2) || buttonN.isChecked()) {
//			gsm.push(new PlayState(gsm, 2, 0));
			gsm.push(new CharEditorState(gsm, 2));
			click.play();
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_3) || buttonM.isChecked()) {
//			gsm.push(new PlayState(gsm, 3, 0));
			gsm.push(new CharEditorState(gsm, 3));
			click.play();
		}
		if (Gdx.input.isKeyJustPressed(Keys.NUM_4) || buttonK.isChecked()) {
			gsm.push(new CharEditorState(gsm,4));
			click.play();
		}

		/////////////////////////// CHARACTEREDITOR////////////////////////////////////////////////////////

		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		back.dispose();
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
