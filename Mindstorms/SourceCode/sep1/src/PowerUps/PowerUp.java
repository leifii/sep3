package PowerUps;

public class PowerUp implements IPowerUp {
	
	boolean powerUpAktiv = false;
	
	public void aktivierePowerUp(){
		powerUpAktiv = true;
		
	}
	
	public void deaktivierePowerUp(){
		powerUpAktiv = false;
	}
	
	public boolean isPowerUpAktiv(){
		return powerUpAktiv;
	}

}
