package Spieldatenverarbeitung;

public interface ISpieldaten {

	
	public int getLifes() ;
	public void setLifes(int lifes);
	public int[][] SEPMANPos();
	public int[][] defenderPos();
	public int[][] tracerPos();
	public int[][] randomPos();
	public int[][] powerUp1();
	public int[][] powerUp2();
	public int[][] powerUp3();
	public int[] gefahreneKanten();
	public boolean kanteGefahren(int kante);
	public String[] getRoboterbelegung(); // Hinzugefügt von Mark
	public void  setRoboterbelegung (int pos, int wert); // Hinzugefügt von Mark
	
	
	
}
