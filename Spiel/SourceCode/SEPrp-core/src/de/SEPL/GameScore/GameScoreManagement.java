package de.SEPL.GameScore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
				com.character.Character so = (com.character.Character) obj;
				System.out.println("Objekt der Klasse PlayState geladen. Und jetzt?");
				// TODO PlayState neu instanziieren

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
}