package Anzeige;
public interface IPlaneinit {
	Plane getInfo(int arrayposition);
	Plane[] getSpiel();
	int[] getRoboter();//0:SEPMAN 1:Random 2: Tracker 3:Defender 
}
