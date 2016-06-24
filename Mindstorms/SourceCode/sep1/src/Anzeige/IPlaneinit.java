package Anzeige;
public interface IPlaneinit {
	Plane getInfo(int arrayposition);//gibt Knoten an der Position zurück
	Plane[] getSpiel();//gibt alle Knoten als array zurück
	int[] getRoboter();//Positionen der Roboter! 0:SEPMAN 1:Random 2: Tracker 3:Defender 
}
