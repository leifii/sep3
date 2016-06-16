package com.gegnerkoordination;

public class Attributes {
	
	final public int STR, INT, STA, ATK, DEF, AS;
	final public float MS;
	public Attributes(int STR, int INT,int STA,int ATK,int DEF, int AS, float MS) {
		this.STR = STR;
		this.INT = INT;
		this.STA = STA;
		this.ATK = ATK;
		this.DEF = DEF;
		this.AS = AS; //Attackspeed
		this.MS = MS; //Movementspeed
	}

}
