/**
 * @author ${user}
 *
 * 
 */

package Positionen;

import Anzeige.Plane;
import Anzeige.Planeinit;

public class Position  implements IPosition {
	
	int posSepman, posTracer, posRandom, posDefender;				 //posX - aktuelle Position RoboterX
	int destSepman  =54;																 //destX - Zielposition RoboterX
	int destTracer  =03;
	int destRandom  =26;
	int destDefender=31;
		
	
	public Position(){
		
	}
	
	
	public void setPosSepman(int i){
		this.posSepman = i;
		}
	public void setPosTracer(int i){
		this.posTracer = i;
		}
	public void setPosRandom(int i){
		this.posRandom = i;
		}
	public void setPosDefender(int i){
		this.posDefender = i;
		}
	
	public int getPosSepman(){
		return posSepman;
	}
	public int getPosTracer(){
		return posTracer;
	}
	public int getPosRandom(){
		return posRandom;
	}
	public int getPosDefender(){
		return posDefender;
	}
	
	
	public void setDestSepman(int i){
		this.destSepman = i;
	}
	
	public void setDestTracer(int i){
		this.destTracer = i;
	}
	public void setDestRandom(int i){
		this.destRandom = i;
	}
	
	public void setDestDefender(int i){
		this.destDefender = i;
	}
	
	public int getDestSepman(){
		return destSepman;
	}
	public int getDestTracer(){
		return destTracer;
	}
	public int getDestRandom(){
		return destRandom;
	}
	public int getDestDefender(){
		return destDefender;
	}
}






//	Position(Planeinit pi)
//	{
//		pos=pi.getRoboter();
//
	
	
	


	
	
	
/*	public void setAktuellePos(int[] get) {
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
*/