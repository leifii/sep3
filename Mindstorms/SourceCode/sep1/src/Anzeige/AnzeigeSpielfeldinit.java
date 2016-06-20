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
	rr.Spielfeldinit("C:\\Users\\Thorben2\\workspace\\yolo\\src\\Anzeige\\Spielfeld.txt");
}



}
