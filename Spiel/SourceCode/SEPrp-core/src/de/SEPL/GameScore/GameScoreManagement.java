package de.SEPL.GameScore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.mygdx.menu.GameStateManager;
import com.mygdx.menu.PlayState;

/* --Dom-- */

public class GameScoreManagement {
	
	public static void setRunningNr(int runningNr){
		try {
			FileWriter writer = new FileWriter("runningNr.txt");
			writer.write(runningNr);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException f) {
			f.printStackTrace();
		} 

	}
	
	
	public static int getRunningNr(){
		int runningNr = 1;
		FileReader fileReader;
		try {
			fileReader = new FileReader("runningNr.txt");
			runningNr = fileReader.read();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return runningNr;
	}
	

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
	public static boolean loadGameScore(GameStateManager gsm, String toLoadGameScore) {

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
				de.SEPL.GameScore.GameScoreManagement.setCharacter(loadedCharacter, 1, gsm);

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

	// Spielmit altem Spielstand laden und Werte des gespeicherten Characters an den neuen Character übergeben
	public static void setCharacter(com.character.Character loadedCharacter, int characterType, GameStateManager gsm) {
		PlayState playState;
		if (loadedCharacter instanceof com.character.Krieger) {
			playState = new PlayState(gsm, 1, loadedCharacter.design);
		}
		if (loadedCharacter instanceof com.character.Magier) {
			playState = new PlayState(gsm, 2, loadedCharacter.design);
		}
		if (loadedCharacter instanceof com.character.Schurke) {
			playState = new PlayState(gsm, 3, loadedCharacter.design);
		}
		if (loadedCharacter instanceof com.character.Schuetze) {
			playState = new PlayState(gsm, 4, loadedCharacter.design);
		} else {
			playState = new PlayState(gsm, 1, loadedCharacter.design);
		}
		gsm.push(playState);
		playState.setCharacterCharacteristicsAfterReload(loadedCharacter.getPosition(), loadedCharacter.getLevel(),
				loadedCharacter.getAttributes(), loadedCharacter.getExp(), loadedCharacter.getMaxHP(),
				loadedCharacter.getCurrentHP(), loadedCharacter.getNeededexp(), loadedCharacter.getDEX());

	}

}