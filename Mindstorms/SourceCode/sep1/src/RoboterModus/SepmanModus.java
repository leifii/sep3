package RoboterModus;

import java.net.Socket;
import java.util.List;

import KommunikationPC.Kommunikation;
import Positionen.Knoten;

public class SepmanModus extends Kommunikation {
	
	private int leben;
	private boolean powerUp;
	private List<Knoten> knotenListe_nochAbzufahren;
	private Knoten ausgangsKnoten, zielKnoten;
	
	public SepmanModus(Socket soc) {
		super(soc, Rolle.SEPman);
		knotenListe_nochAbzufahren = Knoten.generateKnotenList();
	}


	public void run() {
		
	}
	
	public void nachrichtVerarbeiten(byte[] nachricht) {
		
		for(int i=0; i<nachricht.length; i++){
			if(i==0){
//				setAusgangsKnoten(whatever[i]);           Ausgangsknoten des Roboters. Die Nummer des Knotens, von dem der Roboter kommt.
				Knoten aK = new Knoten((int)nachricht[i]);
				if(aK != ausgangsKnoten) {
					knotenListe_nochAbzufahren.remove(aK);
					ausgangsKnoten = aK;
				}
			}
			else if(i==1){
//				setZielKnoten(whatever[i]);               Zielknoten des Roboters. Die Nummers des Knotens, zu dem der Roboter geht.
				zielKnoten = new Knoten((int)nachricht[i]);
			}
			else if(i==2){
				if(nachricht[i]==1){
					Kollisionen.Kollision.kollision();    // Wird aufgerufen, wenn der Drucksensor aktiviert ist, es also zu einer Kollision kam.
				}
			}
			else if(i==3){								//Aktivierungsstatus des Roboters. 0 = Inaktiv; 1 = Aktiv
				if(nachricht[i]==0){
//					doSmthWhileInactive();                Verarbeitung der Mitteilung, dass Roboter inaktiv ist.                 
				}
				else{
//					doSmthWhileActive();                  Verarbeitung der Mitteilung, dass Roboter aktiv ist.
				}
			}
			else if(i==4){								//PowerUp-Status des Sepman. 0 = Inaktiv; 1 = Aktiv
				powerUp = nachricht[i] != 0;
			}
			else if(i==5){                              //Pausenstatus des Spiels. 0 = nicht pausiert; 1 = pausiert            
				if(nachricht[i]==0){
//					spielNichtPausiert();
				}
				else{
//					spielPausiert();
				}
			}
			
		}
		
	}

	public int getLeben() {
		return leben;
	}
	
	public boolean isPowerUp() {
		return powerUp;
	}
	
	public Knoten getAusgangsKnoten() {
		return ausgangsKnoten;
	}
	
	public Knoten getZielKnoten() {
		return zielKnoten;
	}

	@Override
	public Rolle getModus() {
		return Rolle.SEPman;
	}
	
}

