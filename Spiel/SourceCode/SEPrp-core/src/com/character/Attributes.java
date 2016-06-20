package com.character;

public class Attributes {
	
	private int STR, INT, STA, ATK, DEF, AS;
	private float MS;
	public Attributes(int STR, int INT,int STA,int ATK,int DEF, int AS, float MS) {
		this.setSTR(STR);
		this.setINT(INT);
		this.setSTA(STA);
		this.setATK(ATK);
		this.setDEF(DEF);
		this.setAS(AS); //Attackspeed
		this.setMS(MS); //Movementspeed
	}
	public int getSTR() {
		return STR;
	}
	public void setSTR(int sTR) {
		STR = sTR;
	}
	public int getINT() {
		return INT;
	}
	public void setINT(int iNT) {
		INT = iNT;
	}
	public int getSTA() {
		return STA;
	}
	public void setSTA(int sTA) {
		STA = sTA;
	}
	public int getATK() {
		return ATK;
	}
	public void setATK(int aTK) {
		ATK = aTK;
	}
	public int getDEF() {
		return DEF;
	}
	public void setDEF(int dEF) {
		DEF = dEF;
	}
	public int getAS() {
		return AS;
	}
	public void setAS(int aS) {
		AS = aS;
	}
	public float getMS() {
		return MS;
	}
	public void setMS(float mS) {
		MS = mS;
	}

}