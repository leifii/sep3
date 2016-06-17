package KommunikationEV3;

public interface IKommunikation {

	public void senden();
	public void empfangen();
	public int erzeugeByteArray();
	public void nachrichtverarbeiten();
	public boolean druckSensor(boolean druck);
}
