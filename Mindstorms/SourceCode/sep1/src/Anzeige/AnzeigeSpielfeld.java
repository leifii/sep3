

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

	private JPanel contentPane;
	 static Plane[] Spielbrett;
	static int Sepman;
	static int Defender;
	static int Random;
	static int Tracer;
	static int Größe;
	static int Länge;
	static int Breite;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:\\Users\\Thorben2\\workspace\\yolo\\src\\Anzeige\\Spielfeld.txt");
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
	    	if(String.valueOf(hilfs2)=="y")
	    		Nord[u]=true;
	    	else
	    		Nord[u]=false;
	    	if(String.valueOf(hilfs3)=="y")
	    		Süd[u]=true;
	    	else
	    		Süd[u]=false;
	    	if(String.valueOf(hilfs4)=="y")
	    		Ost[u]=true;
	    	else
	    		Ost[u]=false;
	    	if(String.valueOf(hilfs5)=="y")
	    		West[u]=true;
	    	else
	    		West[u]=false;
	    	if(String.valueOf(hilfs6)=="y")
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
	    	
	    	String s=String.valueOf(hilfs1)+String.valueOf(hilfs11);
	    	name[u]=Integer.parseInt(s);
	    	
	    	if(String.valueOf(hilfs2)=="y")
	    		Nord[u]=true;
	    	else
	    		Nord[u]=false;
	    	if(String.valueOf(hilfs3)=="y")
	    		Süd[u]=true;
	    	else
	    		Süd[u]=false;
	    	if(String.valueOf(hilfs4)=="y")
	    		Ost[u]=true;
	    	else
	    		Ost[u]=false;
	    	if(String.valueOf(hilfs5)=="y")
	    		West[u]=true;
	    	else
	    		West[u]=false;
	    	if(String.valueOf(hilfs6)=="y")
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
	    	String s2=String.valueOf(hilfs2)+String.valueOf(hilfs22);
	    	Sepman=Integer.parseInt(s2);
	    	String s3=String.valueOf(hilfs3)+String.valueOf(hilfs33);
	    	Tracer=Integer.parseInt(s3);
	    	String s4=String.valueOf(hilfs4)+String.valueOf(hilfs44);
	    	Random=Integer.parseInt(s4);

	    }
	    
		}
		
		Planeinit Spiel=new Planeinit(name,Nord,Süd,Ost,West,power);
		Spielbrett=Spiel.getSpiel();
		
	    br.close();
	    
	    
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnzeigeSpielfeld frame = new AnzeigeSpielfeld();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 * @param Spielfeld 
	 */
	public AnzeigeSpielfeld() {
		
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, (Länge+1)*200, (Breite+1)*200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Sepman = new JLabel("SEPMAN");
		Sepman.setForeground(Color.RED);
		Sepman.setBackground(Color.RED);
		Sepman.setBounds(336, 526, 50, 50);
		contentPane.add(Sepman);
		
		JLabel Random = new JLabel("Random");
		Random.setForeground(Color.ORANGE);
		Random.setBackground(Color.RED);
		Random.setBounds(275, 202, 50, 50);
		contentPane.add(Random);
		
		JLabel Guard = new JLabel("Guard");
		Guard.setForeground(Color.RED);
		Guard.setBackground(Color.RED);
		Guard.setBounds(141, 274, 50, 50);
		contentPane.add(Guard);
		
		JLabel Tracker = new JLabel("Tracker");
		Tracker.setForeground(Color.BLUE);
		Tracker.setBackground(Color.RED);
		Tracker.setBounds(210, 92, 50, 50);
		contentPane.add(Tracker);
		
	}
}
