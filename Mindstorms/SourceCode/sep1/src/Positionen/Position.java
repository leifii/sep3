/**
 * @author ${user}
 *
 * 
 */

package Positionen;

import Anzeige.Plane;
import Anzeige.Planeinit;

public class Position  implements IPosition {
	
	int[] pos;
	int[] ziel;
	Position(Planeinit pi)
	{
		pos=pi.getRoboter();
	}

	
	
	
	public void setAktuellePos(int[] get) {
		// TODO Auto-generated method stub
		pos=get;
		
	}

	public void setZielPos(int[] get) {
		// TODO Auto-generated method stub
		ziel=get;
	}

	@Override
	public int[] getAktuellePos() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public int[] getZielPos() {
		// TODO Auto-generated method stub
		return ziel;
	}
}
