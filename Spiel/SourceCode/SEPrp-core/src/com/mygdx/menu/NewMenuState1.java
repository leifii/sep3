package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.menu.LoadMenuState;
import com.mygdx.menu.NewMenuState;
import com.mygdx.game.Author;
import com.mygdx.menu.GameStateManager;

@Author(name = "???")

public class NewMenuState1 extends State {
	private Stage stage;	
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private BitmapFont white;
	private Sound click;

	TextButton NewGame,LoadGame,EndGame;
	boolean cl;
	
	public NewMenuState1(GameStateManager gsm) {
		super(gsm);
		cl=true;
		
		// TODO Auto-generated constructor stub
		stage=new Stage();
		
		
		atlas=new TextureAtlas(Gdx.files.internal("userInterface/border2.txt"));
		skin=new Skin(atlas);
		
		table=new Table(skin);
		table.setBackground(skin.getDrawable("border2.2"));
		table.getBackground().setMinHeight(Gdx.graphics.getHeight()/2);
		table.getBackground().setMinWidth(Gdx.graphics.getWidth()/2);
		
		table.setWidth(Gdx.graphics.getWidth());
		table.setBounds(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
//		table.align(Align.center|Align.top);
//		table.setDebug(true);
		
//		table.setPosition(0,Gdx.graphics.getHeight());
		
		
		white=new BitmapFont(Gdx.files.internal("white.fnt"));
		
		LabelStyle labelstyle= new LabelStyle();
		labelstyle.font=white;
		
		
		Label Titel =new Label("SEP RP Gruppe L", labelstyle);
		Titel.setFontScale(1.5f);
		Label Schrift= new Label("Drücke N, um ein neues Spiel zu starten und Esc um es zu beenden", labelstyle);
		Schrift.setFontScale(0.5f);
		
		TextButtonStyle textButtonStyle=new TextButtonStyle();
		textButtonStyle.up= skin.getDrawable("Button");
		textButtonStyle.pressedOffsetX=1;
		textButtonStyle.pressedOffsetY=-1;
		textButtonStyle.font=white;
		
		NewGame= new TextButton("Neues Spiel",textButtonStyle);
		NewGame.pad(20);
		NewGame.setWidth(500);
		
		LoadGame=new TextButton("Spiel laden", textButtonStyle);
		LoadGame.pad(20);
		LoadGame.setWidth(500);
		EndGame=new TextButton("Spiel beenden", textButtonStyle);
		EndGame.pad(20);
	
		
		table.add(Titel).padTop(50).padBottom(80).row();
		table.add(NewGame).align(Align.center).padBottom(20).row();
		
		table.add(LoadGame).padBottom(20).row();
		table.add(EndGame).row();
		table.add(Schrift).align(Align.bottom|Align.right).expandY().padBottom(5f);
		table.pack();
//		table.setDebug(true);
		table.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(5)));



		
		Image img= new Image(new Texture("userInterface/dark background.png"));
		
		stage.addActor(img);
		
		img.setFillParent(true);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}
	
	public void render(SpriteBatch batch){
		batch.begin();
		stage.act();
		stage.draw();
		
		
		batch.end();
	}
	
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}


	@Override
	public void update(float dt) {
		handleInput();
		
	}


	@Override
	public void handleInput() {
		click=Gdx.audio.newSound(Gdx.files.internal("dragstone.mp3"));
		if (Gdx.input.isKeyJustPressed(Keys.L) || LoadGame.isPressed()) {
			if(cl)
				
			click.play();
			cl=false;
			SequenceAction action;
			AlphaAction action1=new AlphaAction();
			action1.setAlpha(0);
			action1.setDuration(2);
			Action action2=new Action() {
				
				@Override
				public boolean act(float arg0) {
					gsm.push(new LoadMenuState(gsm));
					return false;
				}
			
			};

				
		
			action=new SequenceAction(action1, action2);
			stage.addAction(action);
			
			
		}
		if (Gdx.input.isKeyJustPressed(Keys.N)|| NewGame.isPressed()) {
			if(cl)
			click.play();
			cl=false;
			SequenceAction action;
			AlphaAction action1=new AlphaAction();
			action1.setAlpha(0);
			action1.setDuration(2);
			Action action2=new Action() {
				
				@Override
				public boolean act(float arg0) {
					gsm.push(new NewMenuState(gsm));
					return false;
				}
			};
				
		
			action=new SequenceAction(action1, action2);
			stage.addAction(action);
//			gsm.push(new NewMenuState(gsm));
			
			
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE) || EndGame.isPressed()) {
			if(cl)
			click.play();
			cl=false;
			SequenceAction action;
			AlphaAction action1=new AlphaAction();
			action1.setAlpha(0);
			action1.setDuration(2);
			Action action2=new Action() {
				
				@Override
				public boolean act(float arg0) {
					Gdx.app.exit();;
					return false;
				}
			};
				
		
			action=new SequenceAction(action1, action2);
			stage.addAction(action);
			
		}

		if(Gdx.input.isKeyJustPressed(Keys.NUM_1))
			gsm.push(new PlayState(gsm, 1, 0));
		else if(Gdx.input.isKeyJustPressed(Keys.NUM_2))
			gsm.push(new PlayState(gsm, 2, 0));
		else if(Gdx.input.isKeyJustPressed(Keys.NUM_3))
			gsm.push(new PlayState(gsm, 3, 0));
		else if(Gdx.input.isKeyJustPressed(Keys.NUM_4))
			gsm.push(new PlayState(gsm, 4, 0));
		
	}
	

}

