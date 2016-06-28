package RoboterModus;

import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;
import Spielfeld.Planeinit;

public class Sepman extends IModus{

	private Richtung aktuelleRichtung;
	
	public Sepman(Planeinit plane, Linienverfolgung lvfg, Drucksensor drucksensor) {
		super(plane, lvfg, drucksensor);
	}

	@Override
	public void run() {
		if(aktuelleRichtung != getSepmanRichtung())
			neuAusrichten();
		
//		lvfg.geradeaus(); 
	}
	
	private void neuAusrichten() {
		int diff = aktuelleRichtung.getDifferenz(getSepmanRichtung());
		switch (diff)
		{
		
		aktuelleRichtung=getSepmanRichtung();
		case 90: lvfg.drehenRechts(); break;
		case 180: lvfg.drehenLinks(); lvfg.drehenLinks(); break;
		case 270: lvfg.drehenLinks(); break;
		}
	}


}
