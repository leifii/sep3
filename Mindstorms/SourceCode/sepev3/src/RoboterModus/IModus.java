
/**
 * @author ${Katharina Böse, Gizem}
 *
 *
 */
package RoboterModus;
 
import java.util.HashMap;
import java.util.Map;
 
import Drucksensorverarbeitung.Drucksensor;
import Linienverfolger.Linienverfolgung;
import Spielfeld.Planeinit;
 
public abstract class IModus {                  //eine abstract class kann Variablen beinhalten und man kann Methoden direkt implementieren.
    Planeinit planeinit;                        //bei einem Interface kann man nur Prototypen von Methoden machen
    int aktuelleposition=0;
	int zielposition;
 
    Drucksensor sensor;
    Linienverfolgung lvfg;
   
    byte[] nachricht = new byte[9];
    //Maps evtl. static, da für alle Roboter gleich
    private Map<Rolle, Byte> mapAusgangsKnoten;
    private Map<Rolle, Byte> mapZielKnoten;
    private HashMap<Rolle, Boolean> mapAktiviert;
   
    private Richtung sepmanRichtung;
    boolean powerup;
    boolean pause;
    boolean spielLaeuft;
    
    private final Rolle rolle;
   
    protected IModus(Planeinit plane, Linienverfolgung lvfg, Drucksensor drucksensor, Rolle rolle) {
        this.planeinit = plane;
        this.lvfg = lvfg;
        this.sensor = drucksensor;
        this.rolle = rolle;
        
        //initiale Werte (am Anfang)
        spielLaeuft = false;
        sepmanRichtung = Richtung.Norden;
        powerup = false;
        pause = false;
        boolean initalAktiviert = true;
        
        //Initialisierung der Maps
        mapAusgangsKnoten = new HashMap<>();
        mapZielKnoten = new HashMap<>();
        mapAktiviert = new HashMap<>();
       
        mapAktiviert.put(Rolle.Geist_Verfolgung, initalAktiviert);
        mapAktiviert.put(Rolle.Geist_Verteidigung, initalAktiviert);
        mapAktiviert.put(Rolle.Geist_Zufall, initalAktiviert);
        mapAktiviert.put(Rolle.SEPman, initalAktiviert);
    }
   
    public abstract void run();
   
    
    
    
    
    //KATHI START
   
 
    public int getPos(){
     return getAusgangsKnoten(); 
       
    }
    
    public int getPos2(){			//ersatzmethode
    	return aktuelleposition;
    }
    
	public int getZiel(){
		return zielposition;
	}
    
	//KATHI END

	
	
	
    public int getZielKnoten(){
        return mapZielKnoten.get(rolle);
    }
   
    public int getAusgangsKnoten() {
        return mapAusgangsKnoten.get(rolle);
    }
   
    //aktuelle Nachricht wird übergeben
    public void setNachricht(byte[] mes){
        nachricht = mes;
        nachrichtenverarbeitung();
    }
   
    private void nachrichtenverarbeitung() {
        mapAusgangsKnoten.put(Rolle.Geist_Verfolgung, nachricht[0]);
        mapAusgangsKnoten.put(Rolle.Geist_Verteidigung, nachricht[2]);
        mapAusgangsKnoten.put(Rolle.Geist_Zufall, nachricht[4]);
        mapAusgangsKnoten.put(Rolle.SEPman, nachricht[6]);
       
        mapZielKnoten.put(Rolle.Geist_Verfolgung, nachricht[1]);
        mapZielKnoten.put(Rolle.Geist_Verteidigung, nachricht[3]);
        mapZielKnoten.put(Rolle.Geist_Zufall, nachricht[5]);
        mapZielKnoten.put(Rolle.SEPman, nachricht[7]);
       
        //Powerup WERT -0XX (Sepman fährt über Powerup auf Knoten XX)
        if(nachricht[8] < 0) {
            powerup = true;
            //TODO Wert vom Knoten auswerten?
        }
       
        switch(nachricht[8]) {     
        case 1: //Spielstart
        	spielLaeuft = true;
            break;
           
        //Pause
        case 2: pause = true; break;
        case 3: pause = false; break;
       
        //Richtung
        case 4: sepmanRichtung = Richtung.Norden;   break;
        case 5: sepmanRichtung = Richtung.Osten;    break;
        case 6: sepmanRichtung = Richtung.Süden;   break;
        case 7: sepmanRichtung = Richtung.Westen;   break;
//       Geist_Verfolgung, Geist_Verteidigung, Geist_Zufall, SEPman;
        //Aktivierung/Deaktivierung
        case 11: mapAktiviert.put(Rolle.Geist_Verfolgung, false); break;
        case 12: mapAktiviert.put(Rolle.Geist_Verteidigung, false); break;
        case 13: mapAktiviert.put(Rolle.Geist_Zufall, false); break;
        case 21: mapAktiviert.put(Rolle.Geist_Verfolgung, true); break;
        case 22: mapAktiviert.put(Rolle.Geist_Verteidigung, true); break;
        case 23: mapAktiviert.put(Rolle.Geist_Zufall, true); break;
       
        case 100: powerup = false; break;
 
//        Rolle wird bereits bei Erstellung des Objekts erzeugt (Klasse Sepman, verfolger, Zufall, Verteidiger - siehe super Konstruktor)
//        case 101: rolle = Rolle.Geist_Verfolgung; break;
//        case 102: rolle = Rolle.Geist_Verteidigung; break;
//        case 103: rolle = Rolle.Geist_Zufall; break;
//        case 104: rolle = Rolle.SEPman; break;
       
        case 127: //Spielende
        	spielLaeuft = false;
            break;
        }
    }
   
    public boolean getDrucksensor(){
        return sensor.druckSensor();
    }
   
    public boolean isAktiviert(){
        return mapAktiviert.get(rolle);
    }
   
    public Rolle getRolle() {
        return rolle;
    }
   
    public boolean isPause(){
        return pause;
    }
   
    public boolean isPowerup(){
        return powerup;
    }
   
    public Richtung getSepmanRichtung() {
        return sepmanRichtung;
    }
    
    public boolean isSpielLaeuft() {
    	return spielLaeuft;
    }
   
    //ENUMS
   
    public enum Rolle {
        Geist_Verfolgung, Geist_Verteidigung, Geist_Zufall, SEPman;
    }
   
    public enum Richtung {
        Norden(0), Osten(90), Süden(180), Westen(270);
        private int grad;
        Richtung(int grad) {
            this.grad = grad;
        }
        public int getGrad() {
            return grad;
        }
       
        /**
         * AktuelleAusrichtung.getDifferenz(neueAusrichtung) = Gradzahl, um die sich der Roboter drehen muss
         * @param neueRichtung
         * @return gradZahl, um Drehung auszuwerten. 90° nach rechts, 180° wenden (zB zwei mal links), 270° links
         */
        public int getDifferenz(Richtung neueRichtung) {
        	if(neueRichtung == null) {
        		System.out.println("neueRichtung == null");
        		return 0;
        	}
            return (neueRichtung.grad - grad) % 360;
        }
    }
 
 
}