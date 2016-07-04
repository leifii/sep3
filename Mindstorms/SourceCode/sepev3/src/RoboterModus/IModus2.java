package RoboterModus;

import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;
import RoboterModus.IModus.Richtung;
import Spielfeld.Planeinit;

public abstract class IModus2 {
	
	Planeinit planeinit;                        //bei einem Interface kann man nur Prototypen von Methoden machen
    int aktuelleposition=0;
	int zielposition;
	int letzterKnoten;
	
	Drucksensor sensor;
    Linienverfolgung lvfg;
    
    byte[] nachricht = new byte[9];
    
    private int richtung;
    boolean powerup;
    boolean pause;
    boolean spielLaeuft;
    boolean aktiviert;
    
    public IModus2(int start, Planeinit plane, Linienverfolgung lvfg, Drucksensor drucksensor){
    	this.planeinit = plane;
        this.lvfg = lvfg;
        this.sensor = drucksensor;
        this.aktuelleposition = start;
    }
    
    public abstract void run();
    
    public int getPos(){			//ersatzmethode
    	return aktuelleposition;
    }
    
    public int getZiel(){
		return zielposition;
	}
    
    public void setNachricht(byte[] mes){
        nachricht = mes;
        
        if(nachricht[8] < 0) {
            powerup = true;
            //TODO Wert vom Knoten auswerten?
        }
    }
    
    public int getRichtung(){
    	int blickrichtung = -1;
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
    	
    	return blickrichtung;
    }
    
    public abstract void nachrichtenverarbeitung();
	
	public boolean getDrucksensor(){
		return sensor.druckSensor();
	}
	
	public boolean getAktivierung(){
		return aktiviert;
	}
	
	public boolean getPause(){
		return pause;
	}
	
	public boolean getPowerup(){
		return powerup;
	}

}
