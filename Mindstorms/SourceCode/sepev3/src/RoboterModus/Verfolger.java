/**
 * @author ${user}
 *
 * 
 */
package RoboterModus;

import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;
import Spielfeld.Plane;
import Spielfeld.Planeinit;

public class Verfolger extends IModus{

	private int letzterKnoten;
	private int zielposition;
	
	public Verfolger (int start, Planeinit plane, Linienverfolgung lvfg, Drucksensor drucksensor){
		super(plane, lvfg, drucksensor);
		aktuelleposition = start;
		letzterKnoten = aktuelleposition -10;
	}
	
	public void run(){
		if(pause==false && isAktiviert()){
		
	
		Plane knoten;

		
		//Richtungen in die man gehen kann von der aktellen Position aus
		
		boolean[] richtungen = {false, false, false, false};	// Position im Array: nord = 0, west = 1, sued = 2, ost = 3;
		
		int blickrichtung = -1;									// nord = 0, west = 1, sued = 2, ost = 3;
		
		knoten = planeinit.getInfo(aktuelleposition);
		
		if(knoten.getNord() && (aktuelleposition - 10) != letzterKnoten && (aktuelleposition - 10) != nachricht[1] && (aktuelleposition - 10) != nachricht[3] ){
			richtungen[0] = true;
		}
		
		if(knoten.getSued() && (aktuelleposition + 10) != letzterKnoten&& (aktuelleposition + 10) != nachricht[1] && (aktuelleposition + 10) != nachricht[3] ){
			richtungen[2] = true;
		}
		
		if(knoten.getOst() && (aktuelleposition + 1) != letzterKnoten && (aktuelleposition + 1) != nachricht[1] && (aktuelleposition + 1) != nachricht[3] ){
			richtungen[3] = true;
		}
		
		if(knoten.getWest() && (aktuelleposition - 1) != letzterKnoten&& (aktuelleposition - 1) != nachricht[1] && (aktuelleposition - 1) != nachricht[3] ){
			richtungen[1] = true;
		}
		
		//Mögliche Richtungen gesetzt  
		
		
		
			
		//Blickrichtung des Geistes
		
		
		if(aktuelleposition -1 == letzterKnoten){
			blickrichtung = 3;
		}
		
		else if(aktuelleposition +1 == letzterKnoten){
			blickrichtung =  1;
		}
		
		else if(aktuelleposition -10 == letzterKnoten){
			blickrichtung = 2;
		}
		
		else if(aktuelleposition +10 == letzterKnoten){
			blickrichtung = 0;
		}
		
		//setzen der letzten Position
		
		letzterKnoten = aktuelleposition;
		
		//setzen der neuen zielposition Position
		
	/*	if(zufallszahl == 0){
			zielposition -= 10;
		}
		
		else if(zufallszahl == 1){
			zielposition -= 1;
		}
		
		else if(zufallszahl == 2){
			zielposition += 10;
		}
		
		else if(zufallszahl == 3){
			zielposition +=1;
		}
		
		*/
		
		
		//fahren des Roboters zu der nächsten Postion
		
		if(zufallszahl == blickrichtung){
			lvfg.geradeaus();
		}
		
		else if(blickrichtung + 1 == zufallszahl){
			lvfg.drehenLinks();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung + 2 == zufallszahl){
			lvfg.drehenLinks();
			lvfg.drehenLinks();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung + 3 == zufallszahl){
			lvfg.drehenRechts();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung - 1 == zufallszahl){
			lvfg.drehenRechts();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung - 2 == zufallszahl){
			lvfg.drehenRechts();
			lvfg.drehenRechts();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung - 3 == zufallszahl){
			lvfg.drehenLinks();
			lvfg.geradeaus();
		}
		
		//setzen der neuen aktuellen Position
		
		aktuelleposition = zielposition;
		
		}
		
		else
			lvfg.stop();
	}



	
	

}

}
