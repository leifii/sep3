package de.SEPL.ServerClient;

public interface IAuktionshausClient {

	// shutDown nur für Testzwecke, wird im normalen Spielbetrieb nicht
	// benötigt.
	void shutDown();

	String[] getContent();

	void deleteItem(String art, String name, double seltenheit, int staerke, int intelligenz, int stamina,
			int geschicklichkeit, int wert);

	void pasteItem(String art, String name, double seltenheit, int staerke, int intelligenz, int stamina,
			int geschicklichkeit, int wert);

}
