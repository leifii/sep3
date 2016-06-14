
public class Plane {
	Plane[] plane;
	int knoten;
	bolean nKante;
	bolean sKante;
	bolean oKante;
	bolean wKante;
	int i=0;
	public Plane(int[] knoten,bolean[] n,bolean[] s,bolean[] o,bolean[] w)
	{
		plane[]=new Plane[knoten.length];
		for(int k: knoten)
		{
			plane[i].knoten=k;
			plane[i].nKante=n;
			plane[i].sKante=s;
			plane[i].oKante=o;
			plane[i].wKante=w;
			i++;
		}
	}	
	public Plane Standard()
	{
		Plane hilfs;
		int[] knoten=new int[36];
		bolean[] n=new bolean[36];
		bolean[] s=new bolean[36];
		bolean[] o=new bolean[36];
		bolean[] w=new bolean[36];
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
		}
		 hilfs= new Plane(knoten,n,s,o,w);
		return hilfs;
	}
	
}
