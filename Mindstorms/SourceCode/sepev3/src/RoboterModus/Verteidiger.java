/**
 * @author ${Mark}
 *
 * 
 */



//**********************
// Noch nicht feritg!!!
//**********************
package RoboterModus;

import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;
import Spielfeld.Planeinit;

public class Verteidiger extends IModus{
	
	private int letzterKnoten;
	private int zielposition;
	public boolean alive = true;

	public Verteidiger(int start ,Planeinit plane, Linienverfolgung lvfg, Drucksensor drucksensor) {
		super(start, plane, lvfg, drucksensor);
		letzterKnoten = aktuelleposition -1;

	}

	@Override
	public void runRobo() {
		if(!isPause() && isAktiviert() /*&& isStarted()*/){
			Bewegung();
		}
		else lvfg.stop();
		
	}
	
	

   public void Bewegung(){
			int pos = aktuelleposition;
			switch(pos){
			case 31 : 
				lvfg.geradeaus();
				letzterKnoten = 31;
				aktuelleposition = 32;
			break;
			case 32 : 
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 32;
				aktuelleposition = 22;
			break;
			case 22: 
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 22;
				aktuelleposition = 23;
			break;
			case 23 : 
				lvfg.geradeaus();
				letzterKnoten = 23;
				aktuelleposition = 24;
			break;
			case 24 :  
				lvfg.geradeaus();
				letzterKnoten = 24;
				aktuelleposition = 25;
			break;
			case 25 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 25;
				aktuelleposition = 15;
			break;
			case 15 :  
				lvfg.geradeaus();
				letzterKnoten = 15;
				aktuelleposition = 05;
			break;
			case 05 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 05;
				aktuelleposition = 06;
			break;
			case 06 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 06;
				aktuelleposition = 16;
			break;
			case 16 :
				lvfg.geradeaus();
				letzterKnoten = 16;
				aktuelleposition = 26;
			break;
			case 26 :
				lvfg.geradeaus();
				letzterKnoten = 26;
				aktuelleposition = 36;
			break;
			case 36 :  
				lvfg.geradeaus();
				letzterKnoten = 36;
				aktuelleposition = 46;
			break;
			case 46 :  
				lvfg.geradeaus();
				letzterKnoten = 46;
				aktuelleposition = 56;
			break;
			case 56 :
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 56;
				aktuelleposition = 55;
			break;
			case 55 :  
				lvfg.geradeaus();
				letzterKnoten = 55;
				aktuelleposition = 54;
			break;
			case 54 :  
				lvfg.geradeaus();
				letzterKnoten = 54;
				aktuelleposition = 53;
			break;
			case 53 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 53;
				aktuelleposition = 43;
			break;
			case 43 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 43;
				aktuelleposition = 42;
			break;
			case 42 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 42;
				aktuelleposition = 52;
			break;
			case 52 :  
				lvfg.drehenRechts();
				lvfg.geradeaus();
				letzterKnoten = 52;
				aktuelleposition = 51;
			break;
			case 51 :  
				lvfg.drehenLinks();
				lvfg.geradeaus();
				letzterKnoten = 51;
				aktuelleposition = 41;
			break;
			case 41 :  
				lvfg.geradeaus();
				letzterKnoten = 41;
				aktuelleposition = 31;
			break;
	   }
   }
   public void reset(){
		this.powerup = false;
       this.pause = false;
       this.aktiviert = true;
       this.spielLaeuft = false;
       aktuelleposition = initpos;
       letzterKnoten = aktuelleposition -1;
	}

@Override
public void nachrichtenverarbeitung() {
	switch(nachricht[8]){
	case 12: aktiviert = false; break;
	case 22: aktiviert = true; break;
	}
	
}

   }

   
