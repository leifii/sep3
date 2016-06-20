package Anzeige;

import java.io.IOException;

public class AnzeigeSpielfeldinit implements ISpielfeldinit {
	AnzeigeSpielfeld haha;

	
	public AnzeigeSpielfeldinit() throws IOException
	{

	}
	
	
	public void Spielfeldinit(String a) throws IOException
	{ 
		haha=new AnzeigeSpielfeld(a);
		haha.setVisible(true);
	}
public static void main(String[] args) throws IOException
{
	AnzeigeSpielfeldinit rr=new AnzeigeSpielfeldinit();
	rr.Spielfeldinit("/Users/markleifeld/Desktop/SEPMan/sep1/src/Anzeige/Spielfeld.txt");
}



}
