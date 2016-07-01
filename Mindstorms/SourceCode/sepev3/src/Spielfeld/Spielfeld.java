/**
 * @author ${user}
 *
 * 
 */
package Spielfeld;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Spielfeld {
	
	Plane[] Spielbrett;
	 int[] pos=new int[4];
	 int Sepman;
	 int Defender;
	 int Random;
	 int Tracer;
	 int Größe;
	 public int Länge;
	 public int Breite;
	Planeinit Spiel;
	
	public void create(String a) throws IOException
	{
		FileReader fr = new FileReader(a);
	    BufferedReader br = new BufferedReader(fr);
	    String hilfs = null;
	    int[] name=new int[1];
	    boolean[] Nord=new boolean[1];
	    boolean[] Süd=new boolean[1];
	    boolean[] West=new boolean[1];
	    boolean[] Ost=new boolean[1];
	    boolean[] power=new boolean[1];
	    
	    
	    
	    int u=0;
	    hilfs=br.readLine();
		while(hilfs.contains("end")!=true){
	    hilfs=br.readLine();
	    if(hilfs.contains("#")==true)
	    		{
	    	
	    		}
	    else if(hilfs.contains("x")==true)
	    {
	    	char[] ch=hilfs.toCharArray();
	    	char hilfs1=ch[0];
	    	char hilfs2=ch[1];
	    	char hilfs3=ch[3];
	    	char hilfs4=ch[4];
	    	String s1=String.valueOf(hilfs1)+String.valueOf(hilfs2);
	    	String s2=String.valueOf(hilfs3)+String.valueOf(hilfs4);
	    	Breite=Integer.parseInt(s1);
	    	Länge=Integer.parseInt(s2);
	    }
	    else if(hilfs.length()<11 && hilfs.contains("end")==false&&hilfs.length()>=2)
	    {
	    	Größe= Integer.parseInt(hilfs);
	    	name=new int[Größe];
	    	Nord=new boolean[Größe];
	 	    Süd=new boolean[Größe];
	 	    West=new boolean[Größe];
	 	    Ost=new boolean[Größe];
	 	    power=new boolean[Größe];
	    	
	    }
	    else if(hilfs.length()==11)
	    {
	    	char[] ch=hilfs.toCharArray();
	    	char hilfs1=ch[0];
	    	char hilfs2=ch[2];
	    	char hilfs3=ch[4];
	    	char hilfs4=ch[6];
	    	char hilfs5=ch[8];
	    	char hilfs6=ch[10];
	    	String s=String.valueOf(hilfs1);
	    	name[u]=Integer.parseInt(s);
	    	String y="y";
	    	
	    	if(String.valueOf(hilfs2).contains(y)==true)
	    		Nord[u]=true;
	    	else
	    		Nord[u]=false;
	    	
	    	if(String.valueOf(hilfs3).contains(y)==true)
	    		Süd[u]=true;
	    	else
	    		Süd[u]=false;
	    	
	    	if(String.valueOf(hilfs5).contains(y)==true)
	    		Ost[u]=true;
	    	else
	    		Ost[u]=false;
	    	
	    	if(String.valueOf(hilfs4).contains(y)==true)
	    		West[u]=true;
	    	else
	    		West[u]=false;
	    	
	    	if(String.valueOf(hilfs6).contains(y)==true)
	    		power[u]=true;
	    	else
	    		power[u]=false;
	    	
	    	u++;
	    	//name[u]=Integ
	    }
	    else if(hilfs.length()==12)
	    {
	    	char[] ch=hilfs.toCharArray();
	    	char hilfs1=ch[0];
	    	char hilfs11=ch[1];
	    	char hilfs2=ch[3];
	    	char hilfs3=ch[5];
	    	char hilfs4=ch[7];
	    	char hilfs5=ch[9];
	    	char hilfs6=ch[11];
	    	String y="y";
	    	String s=String.valueOf(hilfs1)+String.valueOf(hilfs11);
	    	name[u]=Integer.parseInt(s);
	    	
	    	if(String.valueOf(hilfs2).contains(y)==true)
	    		Nord[u]=true;
	    	else
	    		Nord[u]=false;
	    	if(String.valueOf(hilfs3).contains(y)==true)
	    		Süd[u]=true;
	    	else
	    		Süd[u]=false;
	    	if(String.valueOf(hilfs5).contains(y)==true)
	    		Ost[u]=true;
	    	else
	    		Ost[u]=false;
	    	if(String.valueOf(hilfs4).contains(y)==true)
	    		West[u]=true;
	    	else
	    		West[u]=false;
	    	if(String.valueOf(hilfs6).contains(y)==true)
	    		power[u]=true;
	    	else
	    		power[u]=false;
	    	
	    	u++;
	    }
	    else if(hilfs.length()==14)
	    {
	    	char[] ch=hilfs.toCharArray();
	    	char hilfs1=ch[0];
	    	char hilfs11=ch[1];
	    	char hilfs2=ch[3];
	    	char hilfs22=ch[4];
	    	char hilfs3=ch[6];
	    	char hilfs33=ch[7];
	    	char hilfs4=ch[9];
	    	char hilfs44=ch[10];
	    	
	    	String s1=String.valueOf(hilfs1)+String.valueOf(hilfs11);
	    	Defender=Integer.parseInt(s1);
	    	pos[3]=Defender;
	    	String s2=String.valueOf(hilfs2)+String.valueOf(hilfs22);
	    	Sepman=Integer.parseInt(s2);
	    	pos[0]=Sepman;
	    	String s3=String.valueOf(hilfs3)+String.valueOf(hilfs33);
	    	Tracer=Integer.parseInt(s3);
	    	pos[2]=Tracer;
	    	String s4=String.valueOf(hilfs4)+String.valueOf(hilfs44);
	    	Random=Integer.parseInt(s4);
	    	pos[1]=Random;

	    }
	    
		}
		
		Spiel=new Planeinit(name,Nord,Süd,Ost,West,power);//so ists richtig
		Spielbrett=Spiel.getSpiel();
	    br.close();
	}
	
	public Planeinit getSpiel(){
		return Spiel;
	}
	

}
