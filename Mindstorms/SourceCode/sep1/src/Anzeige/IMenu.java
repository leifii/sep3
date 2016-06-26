package Anzeige;

import javax.print.DocFlavor.STRING;

public interface IMenu {
	public void initComponents();
	public void setzeStartbildschirm();
	public void setzeAuswahldisplay ();
	public void setzeSpielvorbereitungsdisplay();
	public void setzeSiegbildschirm();
	public void setzeNiederlageBildschirm();
	public void setzePausenBildschrim();
	public String[] getRoboterbelegung();
	boolean oben();
	boolean unten();
	boolean links();
	boolean rechts();
	boolean spielstart();
	boolean pause();
	boolean pauseende();
}
