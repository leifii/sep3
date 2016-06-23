package de.SEPL.GameScore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.character.Character;
import com.mygdx.game.MyGdxGame;
import com.mygdx.menu.GameStateManager;
import com.mygdx.menu.NewGameCharacterState;
import com.mygdx.menu.NewMenuState;
import com.mygdx.menu.NewMenuState1;
import com.mygdx.menu.PlayState;

public class GameScoreManagement {

	// Speichere aktuellen Spielstand
	public static boolean saveGameScore(com.character.Character character) {

		boolean gameSaved = false;
		// Streams zum speichern öffnen
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;

		try {
			// File anlegen und Objekt speichern
			fos = new FileOutputStream("score.ser");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(character);
			gameSaved = true;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Wenn try erfolgreich war, werden ObjektOutputStream und
			// FileOutputStream wieder geschlossen
			if (oos != null)
				try {
					oos.close();
				} catch (IOException e) {
				}
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
				}
		}
		return gameSaved;

	}

	// Lade Spielstand
	public static boolean loadGameScore() {

		boolean gameLoaded = false;

		// Streams zum lesen öffnen
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			// File auslesen und Inhalt an neues Objekt übergeben
			fis = new FileInputStream("score.ser");
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			if (obj instanceof com.character.Character) {
				com.character.Character loadedCharacter = (com.character.Character) obj;
				// TODO Character neu instanziieren
				de.SEPL.GameScore.GameScoreManagement.setCharacter(loadedCharacter);

			}
			gameLoaded = true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
				}
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
				}
		}

		return gameLoaded;
	}

	public static void setCharacter(com.character.Character loadedCharacter) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MyGdxGame.WIDTH;
		config.height = MyGdxGame.HEIGHT;
		config.title = MyGdxGame.TITLE;
		config.fullscreen = true;
		//new LwjglApplication(new MyGdxGame(), config);
		
		//TODO characterID implementieren
		int characterID = 1;
		
		GameStateManager gsm = new GameStateManager();
		gsm.push(new NewGameCharacterState(gsm));
		PlayState playstate = new PlayState(gsm, characterID, loadedCharacter.getPosition());
		gsm.push(playstate);
		


	}

}