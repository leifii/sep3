package Anzeige;

public class Plane implements IPlane{
	
	int knoten;
	boolean nKante;
	boolean sKante;
	boolean oKante;
	boolean wKante;
	boolean powerup;
	public int getPosition()
	{
		return knoten;
	}
	
	public boolean getNord()
	{
		return nKante;
	}
	public boolean getSüd()
	{
		return sKante;
	}
	public boolean getOst()
	{
		return oKante;
	}
	public boolean getWest()
	{
		return wKante;
	}
	public boolean getPowerup()
	{
		return powerup;
	}
	
	
	public static void main(String[] args) //Plane Standard()
	{
		/*
		//Planeinit hilfs;
		int[] knoten=new int[36];
		boolean[] n=new boolean[36];
		boolean[] s=new boolean[36];
		boolean[] o=new boolean[36];
		boolean[] w=new boolean[36];
		boolean[] power=new boolean[36];
		for(int k=0;k<36;k++)
		{
			knoten[k]=k;
		}
		for(int i=0;i<36;i++)
		{
			n[i]=false;
			s[i]=false;
			o[i]=false;
			w[i]=false;
			power[i]=false;
		}
		
		 //hilfs= new Planeinit(knoten,n,s,o,w,power);
		//return hilfs;
		 * 
		 */
	}
	
}
