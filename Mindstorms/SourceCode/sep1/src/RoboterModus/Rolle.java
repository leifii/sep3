package RoboterModus;

public enum Rolle {
	Geist_Verfolgung(101), 
	Geist_Verteidigung(102), 
	Geist_Zufall(103), 
	SEPman(104);
	
	private int code;
	Rolle(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
}
