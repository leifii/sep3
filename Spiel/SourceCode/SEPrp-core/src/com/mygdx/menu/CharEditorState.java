package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

public class CharEditorState extends State {

	PlayState playstate;
	int charauswahl=0;
	
	int augenindex=0;
	Texture[]augen=new Texture[]{new Texture("grafiken/Archer.png"),new Texture("grafiken/ArcherEye1.png"),new Texture("grafiken/ArcherEye2.png")};
	Image charbild[]= new Image[]{new Image(augen[0]),new Image(augen[1]),new Image(augen[2])};

	private Skin skin;
	private TextureAtlas atlas;
	private Stage stage;
	private Table table;
	private TextButton buttonJ, buttonN,buttonM,buttonK,buttonL;
	private BitmapFont white;
	private Label label;

	
	protected CharEditorState(GameStateManager gsm,PlayState ps,int ch) {
		super(gsm);
		// TODO Auto-generated constructor stub
	
		
			
		
		
		this.playstate=ps;
		charauswahl=ch;
		stage=new Stage();
		Gdx.input.setInputProcessor(stage);
		
		atlas=new TextureAtlas("testb/Texturen.pack");
		skin=new Skin(atlas);
		
		table=new Table(skin);
		

		table.setWidth(Gdx.graphics.getWidth()*0.9f);
		table.align(Align.center|Align.top);
		table.setPosition(0,Gdx.graphics.getHeight());
		
		white=new BitmapFont(Gdx.files.internal("white.fnt"));
		
		TextButtonStyle textButtonStyle=new TextButtonStyle();
		textButtonStyle.up= skin.getDrawable("blank-2");
//		textButtonStyle.down=skin.getDrawable("blank-3");
		textButtonStyle.pressedOffsetX=1;
		textButtonStyle.pressedOffsetY=-1;
		textButtonStyle.font=white;
		
		TextButtonStyle ConfirmButtonStyle= new TextButtonStyle();
		ConfirmButtonStyle.up= skin.getDrawable("blank-2");
		ConfirmButtonStyle.down=skin.getDrawable("blank-3");
		ConfirmButtonStyle.pressedOffsetX=1;
		ConfirmButtonStyle.pressedOffsetY=-1;
		ConfirmButtonStyle.font=white;
		
		buttonJ=new TextButton("<", textButtonStyle);
		buttonJ.pad(5);

		buttonN= new TextButton(">", textButtonStyle);
		buttonN.pad(5);
		
		buttonM=new TextButton("Bestätigen",ConfirmButtonStyle);
		buttonM.pad(25);

		buttonK=new TextButton("<", textButtonStyle);
		buttonK.pad(5);

		buttonL= new TextButton(">", textButtonStyle);
		buttonL.pad(5);
	
		
		LabelStyle labelStyle= new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);

		label= new Label("Wähle dein Aussehen",labelStyle);
		label.setFontScale(1.2f);
		Image Rahmen=new Image(new Texture("userInterface/border2.png"));
		Rahmen.setPosition(0, Gdx.graphics.getHeight()*0.1f+buttonJ.getMinHeight()*1.5f-200);
		Rahmen.setWidth(Gdx.graphics.getWidth()*0.95f);
		Rahmen.setHeight(Gdx.graphics.getHeight()*1.0f);
		

//		table.debug();
		table.add(label).width(100).padBottom(100).padTop(Gdx.graphics.getHeight()/2-50);  

		table.row();
		Label augenfarbe= new Label(" Augenfarbe   ", labelStyle);
		table.add(augenfarbe);
		table.add(buttonJ);
		table.add(buttonN);
		table.row();
		Label haarfarbe= new Label("Haarfarbe   ", labelStyle);
		table.add(haarfarbe);
		table.add(buttonK);
		table.add(buttonL);
		table.row();
		Label Name= new Label("Name:   ", labelStyle);
		table.add(Name);

		TextFieldStyle abc=new TextFieldStyle();
		abc.font=white;
		TextField name=new TextField("",abc);
		table.add(name);
		
		
		table.add(buttonM);
		
		table.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2)));

		Image img=new Image(new Texture("userInterface/dark background.png"));
		img.setFillParent(true);
		Rahmen.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2)));
		
		stage.addActor(img);
		stage.addActor(Rahmen);
		stage.addActor(table);
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.push(new NewGameCharacterState(gsm));
			}
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}
int x=1;

float XX=0;float YY=0;
	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
		x+=1;
		if (x<160) {
			
		charbild[augenindex].scaleBy(0.1f);
		XX=charbild[augenindex].getScaleX();
		YY=charbild[augenindex].getScaleY();
		}
		
		
	
			
		if (buttonJ.isPressed()) {	
			augenindex--;
			if (augenindex==-1) {
				augenindex=2;
				
			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			charbild[augenindex].setScale(XX,YY);	

				
		
		}
			if (buttonN.isPressed()){
				augenindex++;
				if (augenindex==3) {
					augenindex=0;
					
				}
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				charbild[augenindex].setScale(XX,YY);	

					
		}
		
		
		stage.addActor(charbild[augenindex]);
		stage.act();
		stage.draw();
		

		
		
		
		if ( buttonN.isChecked()) {
			
	}
		if ( buttonM.isChecked()) {
			playstate=new PlayState(gsm, charauswahl);
			gsm.push(playstate);
	}


		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
		this.dispose();
	}

}
