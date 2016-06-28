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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;

public class LoadMenuState extends MenuState {

	
	private Image background;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private BitmapFont white;
	private MainMenuButton loadbutton;
	private MainMenuButton newgamebutton;
	private TextButton loadButton1, loadButton2, loadButton3;
	
	private LoadMenuWindow loadmenuwindow;
	
	public LoadMenuState(GameStateManager gsm) {
		super(gsm);
		stage=new Stage();
		background=new Image(new Texture("userInterface/dark background.png"));
		stage.addActor(background);
		background.setFillParent(true);
		Gdx.input.setInputProcessor(stage);
		
		atlas=new TextureAtlas("userInterface/border1.txt");
		skin=new Skin(atlas);
		
		table=new Table(skin);
		table.setBackground(skin.getDrawable("border2.1"));
		table.getBackground().setMinHeight(Gdx.graphics.getHeight()/2);
		table.getBackground().setMinWidth(Gdx.graphics.getWidth()/2);
		table.setWidth(Gdx.graphics.getWidth());
		table.setBounds(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		white=new BitmapFont(Gdx.files.internal("white.fnt"));
		
		LabelStyle labelstyle= new LabelStyle();
		labelstyle.font=white;
		Label label=new Label("Spielstände", labelstyle);
		label.setFontScale(1.5f);
		
		TextButtonStyle textButtonStyle=new TextButtonStyle();
		textButtonStyle.up= skin.getDrawable("Rahmen");
		textButtonStyle.pressedOffsetX=1;
		textButtonStyle.pressedOffsetY=-1;
		textButtonStyle.font=white;
		
		loadButton1= new TextButton("Spielsta 1",textButtonStyle);
		loadButton1.pad(30);
		loadButton2= new TextButton("Spielstand 2",textButtonStyle);
		loadButton2.pad(30);
		loadButton3= new TextButton("Spielstand 3",textButtonStyle);
		loadButton3.pad(30);
		
		table.add(label).pad(50).row();
		table.add(loadButton1).row();
		table.add(loadButton2).align(Align.center).row();
		table.add(loadButton3).row();
		table.pack();
		table.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2)));
		stage.addActor(table);
	
		loadmenuwindow=new LoadMenuWindow(1728/2-160,1080/2-200,"loadmenuwindow.jpg");
		loadbutton=new MainMenuButton(1728/2-77,1080/2-66,"loadbutton.jpg");
		newgamebutton=new MainMenuButton(1728/2-77, 1080/2-172, "newgamebutton.jpg");
		beendenbutton=new MainMenuButton(1728/2-77,1080/2-280,"beendenbutton.jpg");
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyJustPressed(Keys.L)) {
			gsm.push(new NewMenuState1(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new NewMenuState1(gsm));
		}
		if (loadButton1.isChecked()) {
			de.SEPL.GameScore.GameScoreManagement.loadGameScore(gsm, (String) loadButton1.getText());
		}
		if (loadButton2.isChecked()) {
			de.SEPL.GameScore.GameScoreManagement.loadGameScore(gsm, (String) loadButton2.getText());
		}
		if (loadButton3.isChecked()) {
			de.SEPL.GameScore.GameScoreManagement.loadGameScore(gsm, (String) loadButton3.getText());
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
		sb.draw(loadbutton.getTexture(), loadbutton.getPosition().x, loadbutton.getPosition().y);
		sb.draw(newgamebutton.getTexture(), newgamebutton.getPosition().x, newgamebutton.getPosition().y);
		sb.draw(beendenbutton.getTexture(), beendenbutton.getPosition().x, beendenbutton.getPosition().y);
		sb.draw(loadmenuwindow.getTexture(), loadmenuwindow.getPosition().x, loadmenuwindow.getPosition().y);
		stage.act();
		stage.draw();
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		loadmenuwindow.dispose();
		loadbutton.dispose();
		newgamebutton.dispose();
	}

}
