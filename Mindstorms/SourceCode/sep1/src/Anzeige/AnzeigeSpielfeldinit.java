import java.io.IOException;

public class AnzeigeSpielfeldinit {
	AnzeigeSpielfeld haha;

	
	public AnzeigeSpielfeldinit(String a) throws IOException
	{
		haha=new AnzeigeSpielfeld(a);
		haha.setVisible(true);
	}
public static void main(String[] args) throws IOException
{
	AnzeigeSpielfeldinit rr=new AnzeigeSpielfeldinit("C:\\Users\\Thorben2\\workspace\\yolo\\src\\Anzeige\\Spielfeld.txt");
}


}
