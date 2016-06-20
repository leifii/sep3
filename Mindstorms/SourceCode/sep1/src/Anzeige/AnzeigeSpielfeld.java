package Anzeige;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Checkbox;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.lang.*;
import java.math.*;

public class AnzeigeSpielfeld extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	 Plane[] Spielbrett;
	 int[] pos=new int[4];
	 int Sepman;
	 int Defender;
	 int Random;
	 int Tracer;
	 int Größe;
	 int Länge;
	 int Breite;
	 AnzeigeSpielfeld haha;
	Planeinit Spiel;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */



	/**
	 * Create the frame.
	 * @param Spielfeld 
	 * @throws IOException 
	 * 
	 */
	public JPanel getPanel()
	{
		return contentPane;
	}
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
		
		Spiel=new Planeinit(name,Nord,Süd,Ost,West,power,pos);//so ists richtig
		Spielbrett=Spiel.getSpiel();
	    br.close();
	}
	
	public JLabel[] erzeugeSpielfeld()
	{
		int zahl=1;
		int i=0;
		int k=0;
		JLabel[] ele=new JLabel[Größe];
		for(JLabel j:ele)
		{
				if(k>=Breite)
				{
					k=0;
					i++;
				}
					j=new JLabel(String.valueOf(zahl));
					j.setBackground(Color.BLACK);
					j.setBounds(100+k*150,100+i*150,20,20);
					contentPane.add(j);
				zahl++;
				ele[zahl-2]=j;
				k++;
					
			
			
		}
		return ele;
	}
	
	
	public AnzeigeSpielfeld(String a,JPanel panel) throws IOException {
		
		
		create(a);
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		setBounds(100, 100, (Länge+1)*150, (Breite+1)*150);
		contentPane = panel;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel[] ele=erzeugeSpielfeld();
	/*			EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				AnzeigeSpielfeld frame = new AnzeigeSpielfeld();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});*/
		//haha.setVisible(true);
		
		JLabel jSepman = new JLabel("SEPMAN");
		jSepman.setForeground(Color.GREEN);
		jSepman.setBackground(Color.GREEN);
		jSepman.setBounds(ele[Sepman].getX() , ele[Sepman].getY(), 100, 50);
		contentPane.add(jSepman);
		
		JLabel jRandom = new JLabel("Random");
		jRandom.setForeground(Color.ORANGE);
		jRandom.setBackground(Color.RED);
		jRandom.setBounds(ele[Random].getX() , ele[Random].getY(), 100, 50);
		contentPane.add(jRandom);
		
		JLabel jGuard = new JLabel("Guard");
		jGuard.setForeground(Color.RED);
		jGuard.setBackground(Color.RED);
		jGuard.setBounds(ele[Defender].getX() , ele[Defender].getY(), 50, 50);
		contentPane.add(jGuard);
		
		JLabel jTracker = new JLabel("Tracker");
		
		jTracker.setForeground(Color.BLUE);
		jTracker.setBackground(Color.RED);
		jTracker.setBounds(ele[Tracer].getX() , ele[Tracer].getY(), 50, 50);
		contentPane.add(jTracker);
		ImageIcon Herz=new ImageIcon("herz.gif");
		JLabel Leben1=new JLabel(Herz);
		//Leben1.setVisible(true);
		Leben1.setSize(50, 50);
		
		//Leben.setForeground(Color.RED);
		//Leben.setBackground(Color.black);
		
		Leben1.setLocation(10, 10);
		contentPane.add(Leben1);
		JLabel Leben2=new JLabel(Herz);
		JLabel Leben3=new JLabel(Herz);
		Leben2.setSize(50, 50);
		Leben3.setSize(50, 50);
		Leben2.setLocation(60, 10);
		contentPane.add(Leben2);
		Leben3.setLocation(110, 10);
		contentPane.add(Leben3);
		ImageIcon linie1=new ImageIcon("Linie.png");
		JLabel line1=new JLabel(linie1);
		//JLabel[] low=new JLabel[1];
		//line.setSize(150, 150);
		//line.setLocation(ele[0].getX()+75, ele[0].getY());
		//contentPane.add(line);
		ImageIcon linie2=new ImageIcon("Linie2.png");
		JLabel line2=new JLabel(linie2);
		
		for(int z=0;z<Größe;z++)
		{
			if(Spielbrett[z].getNord()==true)
			{
				line2=new JLabel(linie2);
				line2.setLocation(ele[z].getX(), ele[z].getY()-100);
				line2.setSize(10, 100);
				contentPane.add(line2);				
			}
			if(Spielbrett[z].getOst()==true)
			{
				line1=new JLabel(linie1);
				line1.setLocation(ele[z].getX()+50, ele[z].getY());
				line1.setSize(100, 10);
				contentPane.add(line1);
			}
			
				
		}
		
		
		
		
	}
}
