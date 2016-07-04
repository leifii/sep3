///**
// * @author ${user}
// *
// * 
// */
//
//package Anzeige;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.awt.Image;
//import java.io.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.Checkbox;
//import javax.swing.BoxLayout;
//import java.awt.Color;
//import javax.swing.JLabel;
//import javax.swing.ImageIcon;
//import java.lang.*;
//import java.math.*;
//import Positionen.Position;
//
//public class AnzeigeSpielfeld extends JFrame {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	ImageIcon iiSepman=new ImageIcon("Sepman.png");                     // Hinzugefügt von Mark
//	JLabel jSepman = new JLabel(iiSepman);                              // Hinzugefügt von Mark
//	ImageIcon iiRandom=new ImageIcon("zufall.png");                      // Hinzugefügt von Mark
//	JLabel jRandom = new JLabel(iiRandom);                              // Hinzugefügt von Mark
//	ImageIcon iiGuard=new ImageIcon("Verteidiger.png");                  // Hinzugefügt von Mark 
//	JLabel jGuard = new JLabel(iiGuard);                                // Hinzugefügt von Mark
//	ImageIcon iiTracker=new ImageIcon("Verfolger.png");                  // Hinzugefügt von Mark
//	JLabel jTracker = new JLabel(iiTracker);                            // Hinzugefügt von Mark
//	public JPanel contentPane;
//	 Plane[] Spielbrett;
//	 int[] pos=new int[4];
//	 int Sepman;
//	 int Defender;
//	 int Random;
//	 int Tracer;
//	 int Größe;
//	 public int Länge;
//	 public int Breite;
//	 AnzeigeSpielfeld haha;
//	Planeinit Spiel;
//	/**
//	 * Launch the application.
//	 * @throws IOException 
//	 */
//
//
//
//	/**
//	 * Create the frame.
//	 * @param Spielfeld 
//	 * @throws IOException 
//	 * 
//	 */
//	public JPanel getPanel()
//	{
//		return contentPane;
//	}
//	public void create(String a) throws IOException
//	{
//		FileReader fr = new FileReader(a);
//	    BufferedReader br = new BufferedReader(fr);
//	    String hilfs = null;
//	    int[] name=new int[1];
//	    boolean[] Nord=new boolean[1];
//	    boolean[] Süd=new boolean[1];
//	    boolean[] West=new boolean[1];
//	    boolean[] Ost=new boolean[1];
//	    boolean[] power=new boolean[1];
//	    
//	    
//	    
//	    int u=0;
//	    hilfs=br.readLine();
//		while(hilfs.contains("end")!=true){
//	    hilfs=br.readLine();
//	    if(hilfs.contains("#")==true)
//	    		{
//	    	
//	    		}
//	    else if(hilfs.contains("x")==true)
//	    {
//	    	char[] ch=hilfs.toCharArray();
//	    	char hilfs1=ch[0];
//	    	char hilfs2=ch[1];
//	    	char hilfs3=ch[3];
//	    	char hilfs4=ch[4];
//	    	String s1=String.valueOf(hilfs1)+String.valueOf(hilfs2);
//	    	String s2=String.valueOf(hilfs3)+String.valueOf(hilfs4);
//	    	Breite=Integer.parseInt(s1);
//	    	Länge=Integer.parseInt(s2);
//	    }
//	    else if(hilfs.length()<11 && hilfs.contains("end")==false&&hilfs.length()>=2)
//	    {
//	    	Größe= Integer.parseInt(hilfs);
//	    	name=new int[Größe];
//	    	Nord=new boolean[Größe];
//	 	    Süd=new boolean[Größe];
//	 	    West=new boolean[Größe];
//	 	    Ost=new boolean[Größe];
//	 	    power=new boolean[Größe];
//	    	
//	    }
//	    else if(hilfs.length()==11)
//	    {
//	    	char[] ch=hilfs.toCharArray();
//	    	char hilfs1=ch[0];
//	    	char hilfs2=ch[2];
//	    	char hilfs3=ch[4];
//	    	char hilfs4=ch[6];
//	    	char hilfs5=ch[8];
//	    	char hilfs6=ch[10];
//	    	String s=String.valueOf(hilfs1);
//	    	name[u]=Integer.parseInt(s);
//	    	String y="y";
//	    	
//	    	if(String.valueOf(hilfs2).contains(y)==true)
//	    		Nord[u]=true;
//	    	else
//	    		Nord[u]=false;
//	    	
//	    	if(String.valueOf(hilfs3).contains(y)==true)
//	    		Süd[u]=true;
//	    	else
//	    		Süd[u]=false;
//	    	
//	    	if(String.valueOf(hilfs5).contains(y)==true)
//	    		Ost[u]=true;
//	    	else
//	    		Ost[u]=false;
//	    	
//	    	if(String.valueOf(hilfs4).contains(y)==true)
//	    		West[u]=true;
//	    	else
//	    		West[u]=false;
//	    	
//	    	if(String.valueOf(hilfs6).contains(y)==true)
//	    		power[u]=true;
//	    	else
//	    		power[u]=false;
//	    	
//	    	u++;
//	    	//name[u]=Integ
//	    }
//	    else if(hilfs.length()==12)
//	    {
//	    	char[] ch=hilfs.toCharArray();
//	    	char hilfs1=ch[0];
//	    	char hilfs11=ch[1];
//	    	char hilfs2=ch[3];
//	    	char hilfs3=ch[5];
//	    	char hilfs4=ch[7];
//	    	char hilfs5=ch[9];
//	    	char hilfs6=ch[11];
//	    	String y="y";
//	    	String s=String.valueOf(hilfs1)+String.valueOf(hilfs11);
//	    	name[u]=Integer.parseInt(s);
//	    	
//	    	if(String.valueOf(hilfs2).contains(y)==true)
//	    		Nord[u]=true;
//	    	else
//	    		Nord[u]=false;
//	    	if(String.valueOf(hilfs3).contains(y)==true)
//	    		Süd[u]=true;
//	    	else
//	    		Süd[u]=false;
//	    	if(String.valueOf(hilfs5).contains(y)==true)
//	    		Ost[u]=true;
//	    	else
//	    		Ost[u]=false;
//	    	if(String.valueOf(hilfs4).contains(y)==true)
//	    		West[u]=true;
//	    	else
//	    		West[u]=false;
//	    	if(String.valueOf(hilfs6).contains(y)==true)
//	    		power[u]=true;
//	    	else
//	    		power[u]=false;
//	    	
//	    	u++;
//	    }
//	    else if(hilfs.length()==14)
//	    {
//	    	char[] ch=hilfs.toCharArray();
//	    	char hilfs1=ch[0];
//	    	char hilfs11=ch[1];
//	    	char hilfs2=ch[3];
//	    	char hilfs22=ch[4];
//	    	char hilfs3=ch[6];
//	    	char hilfs33=ch[7];
//	    	char hilfs4=ch[9];
//	    	char hilfs44=ch[10];
//	    	
//	    	String s1=String.valueOf(hilfs1)+String.valueOf(hilfs11);
//	    	Defender=Integer.parseInt(s1);
//	    	pos[3]=Defender;
//	    	String s2=String.valueOf(hilfs2)+String.valueOf(hilfs22);
//	    	Sepman=Integer.parseInt(s2);
//	    	pos[0]=Sepman;
//	    	String s3=String.valueOf(hilfs3)+String.valueOf(hilfs33);
//	    	Tracer=Integer.parseInt(s3);
//	    	pos[2]=Tracer;
//	    	String s4=String.valueOf(hilfs4)+String.valueOf(hilfs44);
//	    	Random=Integer.parseInt(s4);
//	    	pos[1]=Random;
//
//	    }
//	    
//		}
//		
//		Spiel=new Planeinit(name,Nord,Süd,Ost,West,power,pos);//so ists richtig
//		Spielbrett=Spiel.getSpiel();
//	    br.close();
//	}
//	
//	public JLabel[] erzeugeSpielfeld()
//	{
//		int zahl=1;
//		int i=0;
//		int k=0;
//		ImageIcon iiKnoten = new ImageIcon("Knoten.png"); // Hinzugefügt von Mark 
//		JLabel[] ele=new JLabel[Größe];
//		for(JLabel j:ele)
//		{
//				if(k>=Breite)
//				{
//					k=0;
//					i++;
//				}
//					j=new JLabel(iiKnoten); // Hinzugefügt von Mark 
//					j.setBounds(100+k*150,100+i*150,55,55);
//					contentPane.add(j);
//				zahl++;
//				ele[zahl-2]=j;
//				k++;	
//		}
//		return ele;
//	}
//	
//	public AnzeigeSpielfeld(String a,JPanel panel) throws IOException {
//		create(a);
//		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		setBounds(100, 100, (Länge+1)*150, (Breite+1)*150);
//		contentPane = panel;
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JLabel[] ele=erzeugeSpielfeld();
//		
//		/*
//		 * Hinzufügen des Sepmanns und der Geister
//		 */
//
//		jSepman.setBounds(ele[Sepman].getX() , ele[Sepman].getY(), 65, 65);
//		contentPane.add(jSepman);
//
//		jRandom.setBounds(ele[Random].getX() , ele[Random].getY(), 60, 55);
//		contentPane.add(jRandom);
//		
//		jGuard.setBounds(ele[Defender].getX() , ele[Defender].getY(), 60, 55);
//		contentPane.add(jGuard);
//		
//		jTracker.setBounds(ele[Tracer].getX() , ele[Tracer].getY(), 60, 55);
//		contentPane.add(jTracker); 
//		
//		/*
//		 * Hinzufügen der PowerUps
//		 */
//		
//		ImageIcon iiPowerup = new ImageIcon("Powerup.png");   // Hinzugefügt von Mark
//		
//		JLabel lbPowerUp1 = new JLabel(iiPowerup);  // Hinzugefügt von Mark
//		lbPowerUp1.setBounds(ele[5].getX(), ele[5].getY(), 55, 55);  // Hinzugefügt von Mark
// 		contentPane.add(lbPowerUp1);                // Hinzugefügt von Mark
// 		
//		JLabel lbPowerUp2 = new JLabel(iiPowerup);  // Hinzugefügt von Mark
//		lbPowerUp2.setBounds(ele[16].getX(), ele[16].getY(), 55, 55);  // Hinzugefügt von Mark
// 		contentPane.add(lbPowerUp2);                // Hinzugefügt von Mark
// 		
//		JLabel lbPowerUp3 = new JLabel(iiPowerup);  // Hinzugefügt von Mark
//		lbPowerUp3.setBounds(ele[19].getX(), ele[19].getY(), 55, 55);  // Hinzugefügt von Mark
// 		contentPane.add(lbPowerUp3);                // Hinzugefügt von Mark
// 		
//		JLabel lbPowerUp4 = new JLabel(iiPowerup);  // Hinzugefügt von Mark
//		lbPowerUp4.setBounds(ele[30].getX(), ele[30].getY(), 55, 55);  // Hinzugefügt von Mark
// 		contentPane.add(lbPowerUp4);                // Hinzugefügt von Mark
//		
//		
//		
//		ImageIcon Herz=new ImageIcon("herz.gif");
//		
//		JLabel Leben1=new JLabel(Herz);
//		Leben1.setSize(50, 50);
//		Leben1.setLocation(10, 10);
//		contentPane.add(Leben1);
//		
//		JLabel Leben2=new JLabel(Herz);       
//		Leben2.setSize(50, 50);
//		Leben2.setLocation(60, 10);
//		contentPane.add(Leben2);
//		
//		JLabel Leben3=new JLabel(Herz);
//		Leben3.setSize(50, 50);
//		Leben3.setLocation(110, 10);
//		contentPane.add(Leben3);
//		
//		ImageIcon linie1=new ImageIcon("Linie.png");
//		JLabel line1=new JLabel(linie1);
//		
//		ImageIcon linie2=new ImageIcon("Linie2.png");
//		JLabel line2=new JLabel(linie2);
//		
//		for(int z=0;z<Größe;z++)
//		{
//			if(Spielbrett[z].getNord()==true)
//			{
//				line2=new JLabel(linie2);
//				line2.setLocation(ele[z].getX()+5, ele[z].getY()-100); // Überarbeitet von Mark
//				line2.setSize(50, 100);
//				contentPane.add(line2);				
//			}
//			if(Spielbrett[z].getOst()==true)
//			{
//				line1=new JLabel(linie1);
//				line1.setLocation(ele[z].getX()+55, ele[z].getY()); // Überarbeitet von Mark
//				line1.setSize(100, 50);
//				contentPane.add(line1);
//			}
//			
//				
//		}
//	}
//	
//	/* 
//	 * Alles ab hier stammt von Mark
//	 */
//	public void bewegeRoboter(){	
//		int[][] pos = new int[Größe][2];
//		int k = 0 ; 
//		int j = 0 ; 
//        for ( int i = 0 ; i<40 ; i++){
//        	if(k>=Breite)
//			{
//				k=0;
//				j++;
//			}
//        	pos[i][0]=100+k*150;
//        	pos[i][1]= 100+j*150;
//        	k++;
//        	
//        }
//		int g1 = Position.getPosSepman();
//		g1 = convertKnoten(g1);
//		int g2 = Position.getPosTracer();
//		g2 = convertKnoten(g2);
//		int g3 = Position.getPosDefender();
//		g3 = convertKnoten(g3);
//		int g4 = Position.getPosRandom();
//		g4 = convertKnoten(g4);
//		
//		this.jGuard.setLocation(pos[1][0], pos[1][1]);
//		
//		this.jRandom.setLocation(pos[2][0], pos[2][1]);
//		
//		this.jSepman.setLocation(pos[3][0], pos[3][1]);
//		
//		this.jTracker.setLocation(pos[4][0], pos[4][1]); 
//		
//		
//		
//	}
//	
//	
//	public int convertKnoten(int i){
//		switch(i){
//		case 01 : return 1;
//		case 02 : return 2;
//		case 03 : return 3;
//		case 04 : return 4;
//		case 05 : return 5 ;
//		case 06 : return 6;
//		case 11 : return 7;
//		case 12 : return 8;	
//		case 13 : return 9;	
//		case 14 : return 10;	
//		case 15 : return 11;
//		case 16 : return 12;
//		case 21 : return 13 ;
//		case 22 : return 14 ;
//		case 23 : return 15;
//		case 24 : return 16 ;
//		case 25 : return 17;
//		case 26 : return 18 ;
//		case 31 : return 19;
//		case 32 : return 20 ;
//		case 33 : return 21 ;
//		case 34 : return 22 ;
//		case 35 : return 23 ;
//		case 36 : return 24 ;
//		case 41 : return 25;
//		case 42 : return 26 ;
//		case 43 : return 27;
//		case 44 : return 28;		
//		case 45 : return 29;		
//		case 46 : return 30 ;		
//		case 51 : return 31 ;	
//		case 52 : return 32 ;		
//		case 53 : return 33;	
//		case 54 : return 24;
//		case 55 : return 35 ;
//		case 56 : return 36 ;
//		}
//		return 0;
//	}	
//	
//	/*
//	 * Position des Sepmans auf dem angezeigten Spielfeld setzen 
//	 */
//	public static void setzeSepmanAnzeige(int i) {
//	
//	}
//	/*
//	 * Position des Verfolger auf dem angezeigten Spielfeld setzen 
//	 */
//	public static void setzeTracerAnzeige(int i){
//		
//	}
//	/*
//	 * Position des Verteidiger auf dem angezeigten Spielfeld ändern 
//	 */
//	public static void setzeDefenderAnzeige(int i){
//		
//	}
//	/*
//	 * Position des Verpeilten auf dem angezeigten Spielfeld setzen
//	 */
//	public  static void setzeRandomAnzeige(int i){
//		
//	}
//	/*
//	 * Abgefahrene Kanten markieren
//	 */
//	public static void makiereKanten( int f){
//		
//	}
//	
//	public void bewegeSepman(){
//		
//	}
//}
