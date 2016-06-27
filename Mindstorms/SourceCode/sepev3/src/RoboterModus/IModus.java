/**
 * @author ${Katharina Böse}
 *
 * 
 */
package RoboterModus;

import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;
import Spielfeld.Planeinit;

public abstract class IModus {					//eine abstract class kann Variablen beinhalten und man kann Methoden direkt implementieren.
	Planeinit planeinit;						//bei einem Interface kann man nur Prototypen von Methoden machen
	int aktuelleposition=0;
	int zielposition =0;
	int änderung=0;
	int letzterKnoten;
	boolean aktivierung;
	boolean powerup;
	boolean pause;
	
	Drucksensor sensor;
	Linienverfolgung lvfg;
	byte[] nachricht= new byte[9];
	
	
	public abstract void run();
	
	
	public int getPos(){
		return aktuelleposition;
	}
	
	public int getDest(){
		return zielposition;
	}
	
	//aktuelle Nachricht wird übergeben 
	public void setNachricht(byte[] mes){
		nachricht = mes;

	}
	
	public abstract void nachrichtenverarbeitung();
	
	public boolean getDrucksensor(){
		return sensor.druckSensor();
	}
	
	public boolean getAktivierung(){
		return aktivierung;
	}
	
	public boolean getPause(){
		return pause;
	}
	
	public boolean getPowerup(){
		return powerup;
	}
	

}
