/**
 * @author ${user}
 *
 * 
 */
package Anzeige;


public class Planeinit implements IPlaneinit{
	Plane[] plane;
	int[] pos;
	int i=0;
	int si=0;
	public  Planeinit(int[] knoten,boolean[] n,boolean[] s,boolean[] o,boolean[] w,boolean[] power, int[] pos)
	{
		plane=new Plane[knoten.length];
		this.pos=pos;
		
		for(Plane k: plane)
		{
			
			plane[si]=new Plane();
			si++;
		}
		for(int k: knoten)
		{
			plane[i].knotenNR=k;
			plane[i].nKante=n[i];
			plane[i].sKante=s[i];
			plane[i].oKante=o[i];
			plane[i].wKante=w[i];
			plane[i].powerup=power[i];
			
			i++;
		}
	}	
	
	


	public Plane getInfo(int knoten)
	{
		return plane[knoten];
	}
	public Plane[] getSpiel()
	{
		return plane;
	}
	public int[] getRoboter()
	{
		return pos;
	}




	@Override
	public void setRoboter(int[] erf) {
		// TODO Auto-generated method stub
		pos=erf;
	}
}
