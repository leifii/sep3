package Anzeige;
import java.io.IOException;

import javax.swing.JPanel;

public class AnzeigeSpielfeldinit implements ISpielfeldinit {
	AnzeigeSpielfeld haha;

	
	public AnzeigeSpielfeldinit() throws IOException
	{

	}
	
	
	public void Spielfeldinit(String a, JPanel panel) throws IOException
	{ 
		haha=new AnzeigeSpielfeld(a,panel);
		haha.setVisible(true);
	}
public static void main(String[] args) throws IOException
{
	JPanel j=new JPanel();
	AnzeigeSpielfeldinit rr=new AnzeigeSpielfeldinit();
	rr.Spielfeldinit("C:\\Users\\Thorben2\\workspace\\yolo\\src\\Anzeige\\Spielfeld.txt", j);
}



}
