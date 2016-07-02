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
    private Map<Roboter, Byte> mapAusgangsKnoten = new HashMap<>();
    private Map<Roboter, Byte> mapZielKnoten = new HashMap<>();
    @SuppressWarnings("serial")
    private HashMap<Roboter, Boolean> mapAktiviert = new HashMap<Roboter, Boolean>() {{
        mapAktiviert = new HashMap<>();
        boolean initalValue = true;
       
        mapAktiviert.put(Roboter.Geist1, initalValue);
        mapAktiviert.put(Roboter.Geist2, initalValue);
        mapAktiviert.put(Roboter.Geist3, initalValue);
        mapAktiviert.put(Roboter.Sepman, initalValue);
    }};
   
   
    private Richtung sepmanRichtung;
    boolean powerup;
    boolean pause;
   
   
    private Rolle rolle;
   
    protected IModus(Planeinit plane, Linienverfolgung lvfg, Drucksensor drucksensor) {
        this.planeinit = plane;
        this.lvfg = lvfg;
        this.sensor = drucksensor;
    }
   
    public abstract void run();
   
   
    //TODO Wie soll der Wert sein? Es liegen nur Informationen für Ausgangs und Zielnoten vor. Oder soll das der Ausgangsknoten sein?
    //Wegen der Leserlichkeit lieber mit enum arbeiten (siehe enum Richtung)
    public int getPos(){
//      return aktuelleposition;
        return getAusgangsKnoten();
    }
    
    public int getPos2(){			//ersatzmethode
    	return aktuelleposition;
    }
   
    public int getZielKnoten(){
        return mapZielKnoten.get(rolle);
    }
    
	public int getZiel(){
		return zielposition;
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
        mapAusgangsKnoten.put(Roboter.Geist1, nachricht[0]);
        mapAusgangsKnoten.put(Roboter.Geist2, nachricht[2]);
        mapAusgangsKnoten.put(Roboter.Geist3, nachricht[4]);
        mapAusgangsKnoten.put(Roboter.Sepman, nachricht[6]);
       
        mapZielKnoten.put(Roboter.Geist1, nachricht[1]);
        mapZielKnoten.put(Roboter.Geist2, nachricht[3]);
        mapZielKnoten.put(Roboter.Geist3, nachricht[5]);
        mapZielKnoten.put(Roboter.Sepman, nachricht[7]);
       
        //Powerup WERT -0XX (Sepman fährt über Powerup auf Knoten XX)
        if(nachricht[8] < 0) {
            powerup = true;
            //TODO Wert vom Knoten auswerten?
        }
       
        switch(nachricht[8]) {     
        case 1: //Spielstart
            break;
           
        //Pause
        case 2: pause = true; break;
        case 3: pause = false; break;
       
        //Richtung
        case 4: sepmanRichtung = Richtung.Norden;   break;
        case 5: sepmanRichtung = Richtung.Osten;    break;
        case 6: sepmanRichtung = Richtung.Süden;   break;
        case 7: sepmanRichtung = Richtung.Westen;   break;
       
        //Aktivierung/Deaktivierung
        case 11: mapAktiviert.put(Roboter.Geist1, false); break;
        case 12: mapAktiviert.put(Roboter.Geist2, false); break;
        case 13: mapAktiviert.put(Roboter.Geist3, false); break;
        case 21: mapAktiviert.put(Roboter.Geist1, true); break;
        case 22: mapAktiviert.put(Roboter.Geist2, true); break;
        case 23: mapAktiviert.put(Roboter.Geist3, true); break;
       
        case 100: powerup = false; break;
 
        //Rolle
        case 101: rolle = Rolle.Geist_Verfolgung; break;
        case 102: rolle = Rolle.Geist_Verteidigung; break;
        case 103: rolle = Rolle.Geist_Zufall; break;
        case 104: rolle = Rolle.SEPman; break;
       
        case 127: //Spielende
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
   
    //ENUMS
    public enum Roboter {
        Geist1, Geist2, Geist3, Sepman;
    }
   
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
            return (neueRichtung.grad - grad) % 360;
        }
    }
 
 
}