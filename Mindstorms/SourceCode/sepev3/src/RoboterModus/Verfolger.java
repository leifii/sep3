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

public class Verfolger extends IModus2{

	private int letzterKnoten;
	private int zielposition;
	
	public Verfolger (int start, Planeinit plane, Linienverfolgung lvfg, Drucksensor drucksensor){
		super(start, plane, lvfg, drucksensor);
		letzterKnoten = aktuelleposition -10;
	}
	
	public void run(){
		if(pause==false && isAktiviert()){
		
	
		Plane knoten;

		
		//Richtungen in die man gehen kann von der aktellen Position aus
		
		boolean[] richtungen = {false, false, false, false};	// Position im Array: nord = 0, west = 1, sued = 2, ost = 3;
		
		int blickrichtung = -1;									// nord = 0, west = 1, sued = 2, ost = 3;
		
		knoten = planeinit.getInfo(aktuelleposition);
		
		if(knoten.getNord() && (aktuelleposition - 10) != letzterKnoten && (aktuelleposition - 10) != nachricht[3] && (aktuelleposition - 10) != nachricht[5] ){
			richtungen[0] = true;
		}
		
		if(knoten.getSued() && (aktuelleposition + 10) != letzterKnoten&& (aktuelleposition + 10) != nachricht[3] && (aktuelleposition + 10) != nachricht[5] ){
			richtungen[2] = true;
		}
		
		if(knoten.getOst() && (aktuelleposition + 1) != letzterKnoten && (aktuelleposition + 1) != nachricht[3] && (aktuelleposition + 1) != nachricht[5] ){
			richtungen[3] = true;
		}
		
		if(knoten.getWest() && (aktuelleposition - 1) != letzterKnoten&& (aktuelleposition - 1) != nachricht[3] && (aktuelleposition - 1) != nachricht[5] ){
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
		
	
		
		int next =-1 ;						
		
		// 0:Oben , 1: Links , 2: Unten, 3: Rechts, -1: Unbeweglich
		
	
		// Tiefer/Rechts
		if( nachricht[6] > aktuelleposition ){
			
		
			// Gleiche Reihe Rechts
			
				if((nachricht[6]-aktuelleposition) < 6 ){ 
				
					if(richtungen[3]){
					next = 3 ;
						}
						else if(richtungen[2]){
							next = 2;
						}
						else if(richtungen[0]) {
							next = 0 ;
						}
						else if (richtungen[1]){
							next = 1;	
						}
						else{
							next = -1 ;
						}
					
					}
			
				// Tiefer
			
				else if((nachricht[6] - aktuelleposition) > 6){
				
					if(richtungen[2]){
					next = 2;
						}
						else if ((nachricht[6] % 10) < (aktuelleposition % 10) && richtungen[1] ) {
						next = 1 ;
						}
						else if (richtungen[3]){
						next = 3 ;
						}
						else if (richtungen[0]){
						next = 0;
						}
						else {
						next = -1 ;
						}
				}
		
		
		
			
		
			//Höher/Links
		
		else
			
			// Gleiche Reihe Links
			
			if((aktuelleposition - nachricht[6]) < 6) {
				
				if(richtungen[1]){
					
					next = 1 ;
				
				}
					else if(richtungen[2]){
						next = 2 ;
					}
					else if(richtungen[0]){
						next = 0 ;
					}
					else if(richtungen[3]){
						next = 3 ;
					}
					else{
						next = -1 ;
					}
				
			}
				
			// Höher
				
			else if (richtungen[0]){
				next = 0;
			}
				else if	((nachricht[6] % 10) < (aktuelleposition % 10) && richtungen[1]){
					next = 1 ;
				}
				else if(richtungen[3]){
					next = 3;
				}
				else if (richtungen[2]){
					next = 2;
				}
				else {
					next = -1;
				}	
	
				}
		
		
		
		
		 
		
		//fahren des Roboters zu der nächsten Postion
		
		
		if(next == -1){
			
		}
		
		else if(next == blickrichtung){
			lvfg.geradeaus();
		}
		
		else if(blickrichtung + 1 == next){
			lvfg.drehenLinks();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung + 2 == next){
			lvfg.drehenLinks();
			lvfg.drehenLinks();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung + 3 == next){
			lvfg.drehenRechts();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung - 1 == next){
			lvfg.drehenRechts();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung - 2 == next){
			lvfg.drehenRechts();
			lvfg.drehenRechts();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung - 3 == next){
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


