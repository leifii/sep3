package Spieldatenverarbeitung;

public interface ISpieldaten {

	
	public int getLifes() ;
	public void setLifes(int lifes);
	public int getSEPMANPos();
	public void setSEPMANPos(int pos);
	public int getDefenderPos();
	public void setDefenderPos(int pos);
	public int getTracerPos();
	public void setTracerPos(int pos);
	public int getRandomPos();
	public void setRandomPos(int pos);
	public int getPowerUp1();
	public int getPowerUp2();
	public int getPowerUp3();
	public int getPowerUp4() ;
	public int[] gefahreneKanten();
	public boolean kanteGefahren(int kante);
	public String[] getRoboterbelegung(); // Hinzugefügt von Mark
	public void  setRoboterbelegung (int pos, int wert); // Hinzugefügt von Mark
	
	
	
}
