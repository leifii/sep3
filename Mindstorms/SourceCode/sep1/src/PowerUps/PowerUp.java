/**
 * @author ${user}
 *
 * 
 */
package PowerUps;

public class PowerUp implements IPowerUp {
	
	static boolean powerUpAktiv = false;
	
	public void aktivierePowerUp(){
		powerUpAktiv = true;
		
	}
	
	public void deaktivierePowerUp(){
		powerUpAktiv = false;
	}
	
	public static boolean isPowerUpAktiv(){
		return powerUpAktiv;
	}

}
