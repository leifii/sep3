package com.mygdx.menu;

import java.util.List;

import com.objects.Item;

public interface IInventar {

	/**
	 * Plaziert eine Instanz eines Items in dem Inventar.
	 * 
	 * @param name: Name des Items, das in dem Invatar plaziert werden soll.
	 */
	void add(Item item);
	
	/**
	 * Gibt den Namen aller Items im Inventar zurück. Wenn ein Typ Item zwei mal vorliegt, wird dieses zweimal zurückgegeben.
	 * 
	 * @return Liste aller Itemnamen.
	 */
	List<String> getAllItems();
	
	/**
	 * Löschte eine Instanz eines Items aus dem Inventar.
	 * 
	 * @param name Der Name des Items, das zu löchen ist.
	 */
	void remove(String name);
	
	/**
	 * Gibt die addierten Verstärkung des Stärkewertes aller im Inventar ausgerüsteten Items zurück.
	 * 
	 * @return Die Sümme über die Stärkewerte aller Items, die im Inventar ausgerüstet sind.
	 */
	int getStrenghtBoost();
	
	/**
	 * Gibt die addierten Verstärkung des Intelligentswertes aller im Inventar ausgerüsteten Items zurück.
	 * 
	 * @return Die Sümme über die Intelligentswerte aller Items, die im Inventar ausgerüstet sind.
	 */
	int getIntelligenceBoost();
	
	/**
	 * Gibt die addierten Verstärkung des Ausdauerwertes aller im Inventar ausgerüsteten Items zurück.
	 * 
	 * @return Die Sümme über die Ausdauerwerte aller Items, die im Inventar ausgerüstet sind.
	 */
	int getStaminaBoost();
	
	/**
	 * Gibt die addierten Verstärkung des Geschickwertes aller im Inventar ausgerüsteten Items zurück.
	 * 
	 * @return Die Sümme über die Geschickwerte aller Items, die im Inventar ausgerüstet sind.
	 */
	int getDexterityBoost();
	
	/* Methode gehört nicht an diese Stelle.
	/**
	 * Gibt zurück wie viele Lebenspunkte durch die Benuztung von Items geheilt wurden seit dem letzten Aufruf dieser Methode.
	 * 
	 * @return Die geheilten Lebenspunkte.
	 *
	int getHealing();
	*/
	
	/**
	 * Gibt das aktuelle Geldvermögen des Charakters zurück. Das Vermögen des Charakter kann niemals negativ sein.
	 * 
	 * @return Das aktuelle Vermögen.
	 */
	int getMoney();
	
	/**
	 * Verändert das Geldvermögen des Charakters, genau dann wennn die Veränderung möglich ist, der Charakter nach der Veränderung also weiterhin ein positives Geldvermögen hat.
	 * 
	 * @param delta: Der Wert, um den das Vermögen verändert werden soll.
	 * @return true, wenn das Vermögen verändert wurde. false, sonst.
	 */
	boolean modifyMoney(int delta);
	
	/**
	 * Gibt zu einem gegebenen Itemnamen den zugehörigen Wert des Items an.
	 * 
	 * @param nameOfItem: Name des Items, dessen Wert zurückgegeben werden soll.
	 * @return Wert des Items als Integer.
	 */
	int getValueToName(String nameOfItem);
	
}
