/**
 * @author ${user}
 *
 * 
 */
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
	public int getTaste();

}
