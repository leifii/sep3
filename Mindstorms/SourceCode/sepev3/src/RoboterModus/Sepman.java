package RoboterModus;

import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;
import Spielfeld.Planeinit;

public class Sepman extends IModus2{



	public Sepman(int start, Planeinit plane, Linienverfolgung lvfg, Drucksensor drucksensor) {
		super(start, plane, lvfg, drucksensor);
		letzterKnoten = aktuelleposition + 10;
	}

	@Override
	public void run() {
		if(isAktiviert() && isPause()){
		getRichtung();
		bewegen();
		}
		
		else lvfg.stop();
	}

	private void bewegen() {

		//Richtung Norden
		if(nachricht[8] == 4){
			
			zielposition = aktuelleposition - 10;
			
			//Blickrichtung Norden
			if(richtung == 0){
				lvfg.geradeaus();
			}

			//Blickrichtung Westen
			else if(richtung == 1){
				lvfg.drehenRechts();
				lvfg.geradeaus();
			}

			//Blickrichtung Sueden
			else if(richtung == 2){
				lvfg.drehenLinks();
				lvfg.drehenLinks();
				lvfg.geradeaus();
			}

			//Blickrichtung Osten
			else if(richtung == 3){
				lvfg.drehenLinks();
				lvfg.geradeaus();
			}
		}

		//Richtung Osten
		else if(nachricht[8] == 5){
			
			zielposition = aktuelleposition + 1;

			//Blickrichtung Norden
			if(richtung == 0){
				lvfg.drehenRechts();
				lvfg.geradeaus();
			}

			//Blickrichtung Westen
			else if(richtung == 1){
				lvfg.drehenLinks();
				lvfg.drehenLinks();
				lvfg.geradeaus();
			}

			//Blickrichtung Sueden
			else if(richtung == 2){
				lvfg.drehenLinks();
				lvfg.geradeaus();
			}

			//Blickrichtung Osten
			else if(richtung == 3){
				lvfg.geradeaus();
			}
		}



		//Richtung Sueden
		else if(nachricht[8] == 6){
			
			zielposition = aktuelleposition + 10;

			//Blickrichtung Norden
			if(richtung == 0){
				lvfg.drehenLinks();
				lvfg.drehenLinks();
				lvfg.geradeaus();
			}

			//Blickrichtung Westen
			else if(richtung == 1){
				lvfg.drehenLinks();
				lvfg.geradeaus();
			}

			//Blickrichtung Sueden
			else if(richtung == 2){
				lvfg.geradeaus();
			}

			//Blickrichtung Osten
			else if(richtung == 3){
				lvfg.drehenRechts();
				lvfg.geradeaus();
			}
		}

		//Richtung Westen
		else if(nachricht[8] == 7){
			
			zielposition = aktuelleposition - 1;

			//Blickrichtung Norden
			if(richtung == 0){
				lvfg.drehenLinks();
				lvfg.geradeaus();
			}

			//Blickrichtung Westen
			else if(richtung == 1){
				lvfg.geradeaus();
			}

			//Blickrichtung Sueden
			else if(richtung == 2){
				lvfg.drehenRechts();
				lvfg.geradeaus();
			}

			//Blickrichtung Osten
			else if(richtung == 3){
				lvfg.drehenLinks();
				lvfg.drehenLinks();
				lvfg.geradeaus();
			}
		}
		
		letzterKnoten = aktuelleposition;
		aktuelleposition = zielposition;


	}

	public void reset(){
		this.powerup = false;
        this.pause = false;
        this.aktiviert = true;
        this.spielLaeuft = false;
        aktuelleposition = initpos;
        letzterKnoten = aktuelleposition + 10;
	}

	@Override
	public void nachrichtenverarbeitung() {
		
		return;
		
	}

}

