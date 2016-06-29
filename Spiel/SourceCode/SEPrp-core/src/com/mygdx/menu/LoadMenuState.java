package com.mygdx.menu;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Author;
import com.mygdx.game.MyGdxGame;

@Author(name = "Angelo Soltner, Bijan Shahbaz Nejad")


public class LoadMenuState extends MenuState {


	private Image background;
	private Stage stage;
	private TextureAtlas atlas,atlas1;
	private Skin skin, skin1;
	private Table table;
	private BitmapFont white;
	private MainMenuButton loadbutton;
	private MainMenuButton newgamebutton;
	private TextButton loadButton1, loadButton2, loadButton3;
	private List<TextButton> buttonList;
	TextButtonStyle textButtonStyle;
	ImageButton pfeil,pfeil1;
	private int i;
	
	private LoadMenuWindow loadmenuwindow;
	
	public LoadMenuState(GameStateManager gsm) {
		super(gsm);
		stage=new Stage();
		background=new Image(new Texture("userInterface/dark background.png"));
		stage.addActor(background);
		background.setFillParent(true);
		Gdx.input.setInputProcessor(stage);
		
		
		atlas=new TextureAtlas("userInterface/border1.txt");
		atlas1=new TextureAtlas("userInterface/Texturen.atlas");
		skin=new Skin(atlas);
		skin1=new Skin(atlas1);
		
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
		
		textButtonStyle=new TextButtonStyle();
		textButtonStyle.up= skin.getDrawable("Rahmen");
		textButtonStyle.pressedOffsetX=1;
		textButtonStyle.pressedOffsetY=-1;
		textButtonStyle.font=white;
		
		ImageButtonStyle test=new ImageButtonStyle();
		ImageButtonStyle test1=new ImageButtonStyle();
		test.up=skin1.getDrawable("Menupfeil");
		test1.up=skin1.getDrawable("Oben");
		
		pfeil=new ImageButton(test);
		pfeil1=new ImageButton(test1);
		
		loadButton1= new TextButton("Spielstand-1",textButtonStyle);
		loadButton1.pad(30);
		loadButton2= new TextButton("Spielstand-2",textButtonStyle);
		loadButton2.pad(30);
		loadButton3= new TextButton("Spielstand-3",textButtonStyle);
		loadButton3.pad(30);
		
		buttonList=new LinkedList<TextButton>(Arrays.asList(loadButton1,loadButton2,loadButton3));
		i=2;
		
		
		
		table.add(label).expandX();
		table.add(pfeil1).align(Align.right).row();
		table.add();
		table.add(pfeil).row();
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
		pfeil.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				TextButton a;
				if(i==buttonList.size()-1){
			
				buttonList.add(a=new TextButton("Spielstand-"+(i+2), textButtonStyle));
				i++;
				a.pad(30);
				table.removeActor(buttonList.get(i-3));
				table.add(buttonList.get(i)).row();
				
				
			}
				else
				{
					table.removeActor(buttonList.get(i-2));
					table.add(a=buttonList.get(++i)).row();
				}
				}					
			});
		pfeil1.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				if(i>2){
						
//						buttonList.add(a=new TextButton("Spielstand-"+i++, textButtonStyle));
				
						table.removeActor(buttonList.get(i-2));
						table.removeActor(buttonList.get(i-1));
						table.removeActor(buttonList.get(i));
						table.add(buttonList.get(i-3)).row();
						table.add(buttonList.get(i-2)).row();
						table.add(buttonList.get(i-1)).row();
						i--;
						
					}
			}
		});
	}
	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			TextButton a;
			if(i==buttonList.size()-1){
		
			buttonList.add(a=new TextButton("Spielstand-"+(i+2), textButtonStyle));
			i++;
			a.pad(30);
			table.removeActor(buttonList.get(i-3));
			table.add(buttonList.get(i)).row();
			
			
		}
			else
			{
				table.removeActor(buttonList.get(i-2));
				table.add(a=buttonList.get(++i)).row();
			}
			}					
			


		if(i>2){
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			
//			buttonList.add(a=new TextButton("Spielstand-"+i++, textButtonStyle));
	
			table.removeActor(buttonList.get(i-2));
			table.removeActor(buttonList.get(i-1));
			table.removeActor(buttonList.get(i));
			table.add(buttonList.get(i-3)).row();
			table.add(buttonList.get(i-2)).row();
			table.add(buttonList.get(i-1)).row();
			i--;
			
		}
		}
		if (Gdx.input.isKeyJustPressed(Keys.L)) {
			gsm.push(new NewMenuState1(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new NewMenuState1(gsm));
		}
		for(TextButton b:buttonList){
		if (b.isPressed()) {
			de.SEPL.GameScore.GameScoreManagement.loadGameScore(gsm, b.getText().toString());
		}}
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
