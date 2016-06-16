package Steuerbefehle;


public interface ISteuerbefehl { //Muss eigentlich nur "IKommunikation" benutzt werden anstatt diese Interface...
								//Steuerbefehl muss Anweisungswert von "IKommunikation" nehmen.

	public void fahreGeradeaus();
	public void drehenLinks();
	public void drehenRechts();
	public void rotieren();
}
