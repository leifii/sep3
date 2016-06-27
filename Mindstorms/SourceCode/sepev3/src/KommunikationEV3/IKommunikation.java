/**
 * @author ${user}
 *
 * 
 */
package KommunikationEV3;

public interface IKommunikation {

	public void senden();
	public byte[] empfangen();
	public byte[] erzeugeByteArray(int ausgangsknoten, int zielknoten, boolean sensor, boolean aktivierung, boolean powerup, boolean pause);
	public void nachrichtverarbeiten();
	public boolean druckSensor();
}
