package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.MyGdxGame;

public class NewMenuState extends MenuState {

	private Texture back;
	private LoadMenuWindow newgamewindow;
	private MainMenuButton loadbutton;
	private MainMenuButton newgamebutton;

	private Skin skin;
	private TextureAtlas atlas;
	private Stage stage;
	private Table table;
	private TextButton buttonJ, buttonN;
	private BitmapFont white;
	private Label label;

	public NewMenuState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		back = new Texture("back.jpg");
		newgamewindow = new LoadMenuWindow(1728 / 2 - 160, 1080 / 2 - 200, "newgamewindow.jpg");
		loadbutton = new MainMenuButton(1728 / 2 - 77, 1080 / 2 - 66, "loadbutton.jpg");
		newgamebutton = new MainMenuButton(1728 / 2 - 77, 1080 / 2 - 172, "newgamebutton.jpg");
		beendenbutton = new MainMenuButton(1728 / 2 - 77, 1080 / 2 - 280, "beendenbutton.jpg");

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("testb/Texturen.pack");
		skin = new Skin(atlas);

		table = new Table(skin);
		white = new BitmapFont(Gdx.files.internal("white.fnt"));

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("blank-2");
		textButtonStyle.down = skin.getDrawable("blank-3");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = white;
		NinePatch patch = new NinePatch(new Texture("userInterface/border2.9.png"));

		TextButtonStyle textButtonStyle1 = new TextButtonStyle();
		textButtonStyle1.up = skin.getDrawable("blank-2");
		textButtonStyle1.down = skin.getDrawable("blank-4");
		textButtonStyle1.pressedOffsetX = 1;
		textButtonStyle1.pressedOffsetY = -1;
		textButtonStyle1.font = white;

		buttonJ = new TextButton("Ja", textButtonStyle);
		buttonJ.pad(20);
		table.setBounds(Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() / 2 - buttonJ.getMinHeight(),
				buttonJ.getMinWidth(), buttonJ.getMinHeight());

		buttonN = new TextButton("Nein", textButtonStyle1);
		buttonN.pad(20);
		LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);
		label = new Label("Möchtest du ein neues Spiel starten?", labelStyle);
		label.setFontScale(1.2f);
		Image Rahmen = new Image(patch);

		table.add(label).width(150);
		table.row();
		table.getCell(label).spaceBottom(20);
		table.add(buttonJ).padLeft(Gdx.graphics.getWidth() / 2f);
		table.getCell(buttonJ).spaceLeft(100);
		table.add(buttonN);
		Rahmen.setBounds(table.getX() - label.getMinWidth() / 2, table.getY() - table.getHeight(),
				label.getMinWidth() * 2, table.getMinHeight() * 2);
		// table.setBackground(new TextureRegionDrawable(new
		// TextureRegion(Rahmen));
		Image background = new Image(new Texture("userInterface/dark background.png"));
		table.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

		stage.addActor(background);
		background.setFillParent(true);
		Rahmen.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(5)));
		stage.addActor(Rahmen);
		stage.addActor(table);

	}

	protected void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.N) || buttonN.isChecked()) {
			gsm.push(new NewMenuState1(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new NewMenuState1(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.J) || buttonJ.isChecked()) {
			click.play();
			gsm.push(new NewGameCharacterState(gsm));
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
		sb.begin();
		sb.draw(back, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		sb.draw(loadbutton.getTexture(), loadbutton.getPosition().x, loadbutton.getPosition().y);
		sb.draw(newgamebutton.getTexture(), newgamebutton.getPosition().x, newgamebutton.getPosition().y);
		sb.draw(beendenbutton.getTexture(), beendenbutton.getPosition().x, beendenbutton.getPosition().y);
		sb.draw(newgamewindow.getTexture(), newgamewindow.getPosition().x, newgamewindow.getPosition().y);
		stage.act();
		stage.draw();
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		back.dispose();
		newgamewindow.dispose();
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
