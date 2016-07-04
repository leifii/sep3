package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
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
import com.mygdx.game.Author;
import com.npc.NPC;

import de.SEPL.GameScore.GameScoreManagement;

@Author(name = "Bijan Shahbaz Nejad")

public class CharEditorState extends State {

	PlayState playstate;
	int charauswahl = 0;
	int design = 0;
	int augenindex = 0;
	int haarindex = 0;
	Texture[] augen;
	Texture[] haare;
	Image charbild[][];

	private Skin skin,skin1;
	private TextureAtlas atlas, atlas1;
	private Stage stage;
	private Table table;
	private TextButton buttonJ, buttonN, buttonM, buttonK, buttonL;
	TextField a;
	private BitmapFont white;
	private Label label;

	public CharEditorState(GameStateManager gsm, int ch) {
		super(gsm);
		// TODO Auto-generated constructor stub

		charauswahl = ch;

		if (charauswahl == 1) { // Krieger
			haare = new Texture[] { new Texture("grafiken/krieger/squire_m_rot-solo.png"), new Texture("grafiken/krieger/squire_m_rot-greenE-solo.png"),
					new Texture("grafiken/krieger/squire_m_rot-lightblueE-solo.png"), new Texture("grafiken/krieger/squire_m_rot-redE-solo.png")
					, new Texture("grafiken/krieger/squire_m_rot-yellowE-solo.png") };
			augen = new Texture[] { new Texture("grafiken/krieger/squire_m-solo.png"), new Texture("grafiken/krieger/squire_m-greenE-solo.png"),
					new Texture("grafiken/krieger/squire_m-lightblueE-solo.png"), new Texture("grafiken/krieger/squire_m-redE-solo.png"), new Texture("grafiken/krieger/squire_m-yellowE-solo.png") };
			charbild = new Image[][] { { new Image(augen[0]), new Image(haare[0]) },
					{ new Image(augen[1]), new Image(haare[1]) }, { new Image(augen[2]), new Image(haare[2]) },
					{ new Image(augen[3]), new Image(haare[3]) }, {new Image(augen[4]), new Image(haare[4]) }};
		} else if (charauswahl == 2) { // Magier
			augen = new Texture[] { new Texture("grafiken/mage/mage.png"), new Texture("grafiken/mage/mage_eye_green.png"),
					new Texture("grafiken/mage/mage_eye_red.png"), new Texture("grafiken/mage/mage_eye_yellow.png") };
			haare = new Texture[] { new Texture("grafiken/mage/mage_blue.png"), new Texture("grafiken/mage/mage_blue_eye_green.png"),
					new Texture("grafiken/mage/mage_blue_eye_red.png"), new Texture("grafiken/mage/mage_blue_eye_yellow.png") };
			charbild = new Image[][] { { new Image(augen[0]), new Image(haare[0]) },
					{ new Image(augen[1]), new Image(haare[1]) }, { new Image(augen[2]), new Image(haare[2]) },
					{ new Image(augen[3]), new Image(haare[3]) } };
		}

		else if (charauswahl == 3) { // Schurke
			augen = new Texture[] { new Texture("grafiken/Archer.png"), new Texture("grafiken/Archer-Eyes1.png"),
					new Texture("grafiken/Archer-Eyes2.png"), new Texture("grafiken/Archer-Eyes3.png") };
			haare = new Texture[] { new Texture("grafiken/ArcherBlue.png"), new Texture("grafiken/Schütze_blauE.png"),
					new Texture("grafiken/Schütze_grünE.png"), new Texture("grafiken/Schütze_rotE.png") };
			charbild = new Image[][] { { new Image(augen[0]), new Image(haare[0]) },
					{ new Image(augen[1]), new Image(haare[1]) }, { new Image(augen[2]), new Image(haare[2]) },
					{ new Image(augen[3]), new Image(haare[3]) } };
		} else if (charauswahl == 4) { // Schütze
			augen = new Texture[] { new Texture("grafiken/Archer.png"), new Texture("grafiken/Archer-Eyes1.png"),
					new Texture("grafiken/Archer-Eyes2.png"), new Texture("grafiken/Archer-Eyes3.png") };
			haare = new Texture[] { new Texture("grafiken/ArcherBlue.png"), new Texture("grafiken/Schütze_blauE.png"),
					new Texture("grafiken/Schütze_grünE.png"), new Texture("grafiken/Schütze_rotE.png") };
			charbild = new Image[][] { { new Image(augen[0]), new Image(haare[0]) },
					{ new Image(augen[1]), new Image(haare[1]) }, { new Image(augen[2]), new Image(haare[2]) },
					{ new Image(augen[3]), new Image(haare[3]) } };
		}
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("testb/Texturen.pack");
		atlas1 = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		skin = new Skin(atlas);
		skin1= new Skin(Gdx.files.internal("uiskin.json"));
		a=new TextField("", skin1);

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

		buttonJ = new TextButton("<", textButtonStyle);
		buttonJ.pad(5);

		buttonN = new TextButton(">", textButtonStyle);
		buttonN.pad(5);

		buttonM = new TextButton("Bestätigen", ConfirmButtonStyle);
		buttonM.pad(25);

		buttonK = new TextButton("<", textButtonStyle);
		buttonK.pad(5);

		buttonL = new TextButton(">", textButtonStyle);
		buttonL.pad(5);
		


		LabelStyle labelStyle = new LabelStyle(white, com.badlogic.gdx.graphics.Color.WHITE);

		label = new Label("Wähle dein Aussehen", labelStyle);
		label.setFontScale(1.2f);
		Image Rahmen = new Image(new Texture("userInterface/border2.png"));
		Rahmen.setPosition(0, Gdx.graphics.getHeight() * 0.1f + buttonJ.getMinHeight() * 1.5f - 200);
		Rahmen.setWidth(Gdx.graphics.getWidth() * 0.95f);
		Rahmen.setHeight(Gdx.graphics.getHeight() * 1.0f);

		// table.debug();
		table.add(label).width(100).padBottom(100).padTop(Gdx.graphics.getHeight() / 2 - 50);

		table.row();
		Label augenfarbe = new Label(" Augenfarbe   ", labelStyle);
		table.add(augenfarbe);
		table.add(buttonJ);
		table.add(buttonN);
		table.row();
		Label haarfarbe = new Label("Haarfarbe   ", labelStyle);
		table.add(haarfarbe).padBottom(50);
		table.add(buttonK).align(Align.center).padBottom(50);
		table.add(buttonL).center().padBottom(50);
		table.row();
		Label Name = new Label("Name:   ", labelStyle);
		table.add(Name);
		
		table.add(a).spaceRight(100).spaceLeft(100).center();

		table.add(buttonM);

		table.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

		Image img = new Image(new Texture("userInterface/dark background.png"));
		img.setFillParent(true);
		Rahmen.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));

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

		//Load-Test --Dom--
//		if (Gdx.input.isKeyJustPressed(Keys.L)){
//			GameScoreManagement.loadGameScore(gsm);
//			System.out.println("Hier");
//		}
		//----
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	int x = 1;
	Sound buttonclick;
	float XX = 0;
	float YY = 0;

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		buttonclick = (Sound) Gdx.audio.newSound(Gdx.files.internal("toggle_switch.mp3"));
		x++;
		if (x <= 180) {

			charbild[augenindex][haarindex].scaleBy(0.085f);
			charbild[augenindex][haarindex].setRotation(-x * 2);
			XX = charbild[augenindex][haarindex].getScaleX();
			YY = charbild[augenindex][haarindex].getScaleY();

		}

		if (buttonJ.isPressed() && x >= 180) {
			buttonclick.play();
			augenindex--;
			if (augenindex == -1) {
				augenindex = augen.length - 1;

			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			charbild[augenindex][haarindex].setScale(XX, YY);

		}
		if (buttonN.isPressed() && x >= 180) {
			buttonclick.play();
			augenindex++;
			if (augenindex == augen.length) {
				augenindex = 0;

			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			charbild[augenindex][haarindex].setScale(XX, YY);

		}
		if (buttonK.isPressed() && x >= 180) {
			buttonclick.play();
			haarindex--;
			if (haarindex == -1) {
				haarindex = 1;

			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			charbild[augenindex][haarindex].setScale(XX, YY);

		}
		if (buttonL.isPressed() && x >= 180) {
			buttonclick.play();
			haarindex++;
			if (haarindex == 2) {
				haarindex = 0;

			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			charbild[augenindex][haarindex].setScale(XX, YY);
			
		}
		
		if (x > 50) {
			stage.addActor(charbild[augenindex][haarindex]);
		}
		stage.act();
		stage.draw();
		
		// DESIGN
		if (haarindex == 0 && augenindex == 0) {
			design = 0;
		} else if (haarindex == 0 && augenindex == 1) {
			design = 1;
		} else if (haarindex == 0 && augenindex == 2) {
			design = 2;
		} else if (haarindex == 0 && augenindex == 3) {
			design = 3;
		} else if (charauswahl==1 && haarindex ==0 && augenindex == 4) {
			design = 4;
		}
		
		else if(charauswahl==1){
			if (haarindex == 1 && augenindex == 0) {
				design = 5;
			} else if (haarindex == 1 && augenindex == 1) {
				design = 6;
			} else if (haarindex == 1 && augenindex == 2) {
				design = 7;
			} else if (haarindex == 1 && augenindex == 3) {
				design = 8;
			}
			else if (haarindex == 1 && augenindex ==4){
				design = 9;
			}
			
		}
		
		else if (haarindex == 1 && augenindex == 0) {
			design = 4;
		} else if (haarindex == 1 && augenindex == 1) {
			design = 5;
		} else if (haarindex == 1 && augenindex == 2) {
			design = 6;
		} else if (haarindex == 1 && augenindex == 3) {
			design = 7;
		}

		// DESIGN

		if (buttonM.isChecked() || Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			playstate = new PlayState(gsm, charauswahl, design, a.getText());

			
			gsm.push(playstate);
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

		this.dispose();
	}

}
