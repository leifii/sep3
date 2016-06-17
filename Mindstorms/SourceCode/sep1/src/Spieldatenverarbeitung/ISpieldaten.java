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
	
	
	
}
