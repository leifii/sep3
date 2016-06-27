/**
 * @author ${user}
 *
 * 
 */
package KommunikationEV3;

public interface IKommunikation {

	public void senden();
	public byte[] empfangen();
	public int erzeugeByteArray();
	public void nachrichtverarbeiten();
	public boolean druckSensor();
}
