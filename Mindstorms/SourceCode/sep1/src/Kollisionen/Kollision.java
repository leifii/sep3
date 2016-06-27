/**
 * @author ${user}
 *
 * 
 */

package Kollisionen;

import Anzeige.IMenu;

public class Kollision implements IKollision, IMenu {
	
	public void kollision(){
		
		if(PowerUps.PowerUp.isPowerUpAktiv()){
			setzePausenBildschrim();
			//pausiere spiel
			//setze Roboter, der mit aktiviertem PowerUp getroffen wurde zurück
			//setze spiel fort
			
		}
		else{
		   setzePausenBildschrim();
			//reduziere leben des sepman um 1
			//setze alle Roboter auf ihre Startposition
			//setze Spiel fort
			
		}
}

	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setzeStartbildschirm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setzeAuswahldisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setzeSpielvorbereitungsdisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setzeSiegbildschirm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setzeNiederlageBildschirm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setzePausenBildschrim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getRoboterbelegung() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTaste() {
		// TODO Auto-generated method stub
		return 0;
	}  
}
