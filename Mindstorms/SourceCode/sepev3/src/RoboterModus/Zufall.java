/**
 * @author ${user}
 *
 * 
 */
package RoboterModus;


import java.util.Random;

import Linienverfolger.Linienverfolgung;
import Spielfeld.Plane;
import Spielfeld.Planeinit;



public class Zufall implements IModus{
	Planeinit planeinit;
	int aktuelleposition=26;
	int zielposition =0;
	int änderung=0;
	int letzterKnoten;
	Linienverfolgung lvfg;
	byte[] nachricht= new byte[9];
	Random r;
	
	public Zufall(Planeinit plane, Linienverfolgung lvfg){
		this.planeinit = plane;
		this.lvfg = lvfg;
		letzterKnoten = aktuelleposition +1;
	}
	
	public void run(){
		int zahl;
		Plane knoten;

		
		boolean[] richtungen = {false, false, false, false};
		int blickrichtung = -1;
		// nord = 0, west = 1, sued = 2, ost = 3;
		
		knoten = planeinit.getInfo(aktuelleposition);
		
		if(knoten.getNord() && (aktuelleposition - 10) != letzterKnoten){
			richtungen[0] = true;
		}
		
		if(knoten.getSued() && (aktuelleposition + 10) != letzterKnoten){
			richtungen[2] = true;
		}
		
		if(knoten.getOst() && (aktuelleposition + 1) != letzterKnoten){
			richtungen[3] = true;
		}
		
		if(knoten.getWest() && (aktuelleposition - 1) != letzterKnoten){
			richtungen[1] = true;
		}
		
		while(true){
			zahl  =r.nextInt(4);
			if(richtungen[zahl] == true){
				break;
			}
		}
		
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
		
		
		letzterKnoten = aktuelleposition;
		
		
		
		if(zahl == 0){
			aktuelleposition -= 10;
		}
		
		else if(zahl == 1){
			aktuelleposition -= 1;
		}
		
		else if(zahl == 2){
			aktuelleposition += 10;
		}
		
		else if(zahl == 3){
			aktuelleposition +=1;
		}
		
		if(zahl == blickrichtung){
			lvfg.geradeaus();
		}
		
		else if(blickrichtung + 1 == zahl){
			lvfg.drehenLinks();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung + 2 == zahl){
			lvfg.drehenLinks();
			lvfg.drehenLinks();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung + 3 == zahl){
			lvfg.drehenLinks();
			lvfg.drehenLinks();
			lvfg.drehenLinks();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung - 1 == zahl){
			lvfg.drehenRechts();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung - 2 == zahl){
			lvfg.drehenRechts();
			lvfg.drehenRechts();
			lvfg.geradeaus();
		}
		
		else if(blickrichtung - 3 == zahl){
			lvfg.drehenRechts();
			lvfg.drehenRechts();
			lvfg.drehenRechts();
			lvfg.geradeaus();
		}
		
		
		

		
	}
	

	public int getPos(){
		
		return aktuelleposition;
	}
	
	
	public int getDest(){
		
		return zielposition;
		
	}
	
	
	public void setNachricht(byte[] mes){
		
		nachricht = mes;
		
	}
	
	

}
