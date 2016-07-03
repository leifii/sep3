/**
 * @author ${Tristan}
 *
 * 
 */
package PowerUps;

public class PowerUp implements IPowerUp {
	
	public PowerUp powerUp;              
	
	boolean powerUpAktiv = false;
	
	public PowerUp(){                   	//Konstruktor
		
	}
	
	public void setPowerUp(PowerUp p){  	//setter
		this.powerUp = p;
	}
	
	public PowerUp getPowerUp(){       	    //getter
		return this.powerUp;
	}

	@Override                               //aktiviert powerUp
	public void aktivierePowerUp() {
		powerUpAktiv = true;
		
	}

	@Override
	public void deaktivierePowerUp() {      //deaktiviert powerUp
		powerUpAktiv = false;
		}
	
	public boolean isPowerUpAktiv(){        //gibt powerUp-Status zurück
		return powerUpAktiv;
		}

}
