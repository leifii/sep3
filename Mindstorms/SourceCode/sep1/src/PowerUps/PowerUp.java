/**
 * @author ${Tristan}
 *
 * 
 */
package PowerUps;

import KommunikationPC.QueueHandler;

public class PowerUp implements IPowerUp {
	
	public PowerUp powerUp;
	QueueHandler queue;
	
	boolean powerUpAktiv = false;
	boolean knoten06=true, knoten25=true, knoten32=true, knoten51=true;
	
	public PowerUp(){                   	//Konstruktor
		
	}
	
	public void setPowerUp(PowerUp p){  	//setter
		this.powerUp = p;
	}
	
	public PowerUp getPowerUp(){       	    //getter
		return this.powerUp;
	}

	                              //aktiviert powerUp
	public void aktivierePowerUp(int x) {
		queue = Anzeige.Menu.queue.getQueueHandler();
		powerUp = Anzeige.Menu.powerUp.getPowerUp();
		if(x==51&&knoten51){
			powerUpAktiv = true;
			knoten51=false;
			queue.startPowerUp(51);
		}
		
		if(x==32&&knoten32){
			powerUpAktiv=true;
			knoten32=false;
			queue.startPowerUp(32);
		}
		if(x==25&&knoten25){
			powerUpAktiv=true;
			knoten25=false;
			queue.startPowerUp(25);
		}
		if(x==06&&knoten06){
			powerUpAktiv=true;
			knoten06=false;
			queue.startPowerUp(06);
			}
	}
	

	@Override
	public void deaktivierePowerUp() {      //deaktiviert powerUp
		powerUpAktiv = false;
		queue.endePowerUp();
		}
	
	public boolean isPowerUpAktiv(){        //gibt powerUp-Status zurück
		return powerUpAktiv;
		}

}
