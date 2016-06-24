package Positionen;

public interface IPosition {
	
	int[] setAktuellePos();//Positionen der Roboter! 0:SEPMAN 1:Random 2: Tracker 3:Defender 
	int[] setZielPos();// länge des Arrays ist 4
	int[] getAktuellePos();
	int[] getZielPos();

}
