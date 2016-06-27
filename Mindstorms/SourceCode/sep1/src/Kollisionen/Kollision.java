/**
 * @author ${Mark}
 *
 * 
 */

package Kollisionen;

import Anzeige.IMenu;

public class Kollision implements IKollision, IMenu {
	
	public static boolean kollidiert  = false;
	
	public static void setkollision(){
		if(kollidiert == false ){
			kollidiert = true;
		}
		else {
			kollidiert =false;
		}
	}
	
	
	
	
	public void kollision(){
		
		if(PowerUps.PowerUp.isPowerUpAktiv()){
			setzePausenBildschrim();
		}
		else{
		   setzePausenBildschrim();
		   // Reduziere leben um 1
		}
}

	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		// Wird nicht verwendet
		
	}

	@Override
	public void setzeStartbildschirm() {
		// TODO Auto-generated method stub
		// Wird nicht verwendet
		
	}

	@Override
	public void setzeAuswahldisplay() {
		// TODO Auto-generated method stub
		// Wird nicht verwendet
		
	}

	@Override
	public void setzeSpielvorbereitungsdisplay() {
		// TODO Auto-generated method stub
		// Wird nicht verwendet
		
	}

	@Override
	public void setzeSiegbildschirm() {
		// TODO Auto-generated method stub
		// Wird nicht verwendet
		
	}

	@Override
	public void setzeNiederlageBildschirm() {
		// TODO Auto-generated method stub
		// Wird nicht verwendet
		
	}

	@Override
	public void setzePausenBildschrim() {
		// TODO Auto-generated method stub
		// Wird nicht verwendet
		
	}

	@Override
	public String[] getRoboterbelegung() {
		// TODO Auto-generated method stub
		// Wird nicht verwendet
		return null;
	}

	@Override
	public int getTaste() {
		// TODO Auto-generated method stub
		// Wird nicht verwendet
		return 0;
	}  
}
