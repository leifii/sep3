/**
 * @author ${user}
 *
 * 
 */
package Spielfeld;


public class Planeinit implements IPlaneinit{
	Plane[] plane;
	int i=0;
	int si=0;
	public  Planeinit(int[] knoten,boolean[] n,boolean[] s,boolean[] o,boolean[] w,boolean[] power)
	{
		plane=new Plane[knoten.length];
		
		for(Plane k: plane)
		{
			
			plane[si]=new Plane();
			si++;
		}
		for(int k: knoten)
		{
			plane[i].knoten=k;
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
}
