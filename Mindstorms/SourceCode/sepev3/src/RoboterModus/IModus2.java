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
    
    protected int richtung;
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
    
    public int getZielKnoten(){
		return zielposition;
	}
    
    public void setNachricht(byte[] mes){
        nachricht = mes;
        
        if(nachricht[8] < 0) {
            powerup = true;
            //TODO Wert vom Knoten auswerten?
        }
    }
    
    public void getRichtung(){
    	
    	// nord = 0, west = 1, sued = 2, ost = 3;
    	
    	if(aktuelleposition -1 == letzterKnoten){
    		richtung = 3;
		}
		
		else if(aktuelleposition +1 == letzterKnoten){
			richtung =  1;
		}
		
		else if(aktuelleposition -10 == letzterKnoten){
			richtung = 2;
		}
		
		else if(aktuelleposition +10 == letzterKnoten){
			richtung = 0;
		}
    }
    
    public void nachrichtenverarbeitung(){
    	
    }
	
	public boolean getDrucksensor(){
		return sensor.druckSensor();
	}
	
	public boolean isAktiviert(){
		return aktiviert;
	}
	
	public boolean isPause(){
		return pause;
	}
	
	public boolean isPowerup(){
		return powerup;
	}

}
