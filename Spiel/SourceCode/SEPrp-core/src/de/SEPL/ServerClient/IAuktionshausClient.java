package de.SEPL.ServerClient;

import com.mygdx.game.Author;

@Author(name = "Dominikus Häckel")

public interface IAuktionshausClient {

	// shutDown nur für Testzwecke, wird im normalen Spielbetrieb nicht
	// benötigt.
	void shutDown();

	String[] getContent();

	void deleteItem(String name);

	void pasteItem(String name);

}
