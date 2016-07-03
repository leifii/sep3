package com.character;

import java.io.Serializable;

import com.mygdx.game.Author;

@Author(name = "?????")

public class Attributes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int STR, INT, DEX, STA, ATK, DEF, AS;
	private float MS;
	public Attributes(int STR, int INT, int DEX, int STA, int ATK, int DEF, int AS, float MS) {
		this.setSTR(STR);
		this.setINT(INT);
		this.setSTA(STA);
		this.setATK(ATK);
		this.setDEF(DEF);
		this.setAS(AS); //Attackspeed
		this.setMS(MS); //Movementspeed
	}
	
	public Attributes(Attributes copy) {
		this(copy.STR, copy.INT, copy.DEX, copy.STA, copy.ATK, copy.DEF, copy.AS, copy.MS);
	}
	
	public void addAttributeValues(Attributes a) {
		if(a == null)
			return;
		
		STR += a.STR;
		INT += a.INT;
		DEX += a.DEX;
		STA += a.STA;
		ATK += a.ATK;
		DEF += a.DEF;
		AS += a.AS;
		MS += a.MS;
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
	public int getDEX() {
		return DEX;
	}
	public void setDEX(int dEX) {
		DEX = dEX;
	}

	public String getValueAsString(String key) {
		switch(key) {
		case "STR": return Integer.toString(STR);
		case "INT": return Integer.toString(INT);
		case "DEX": return Integer.toString(DEX);
		case "STA": return Integer.toString(STA);
		case "ATK": return Integer.toString(ATK);
		case "DEF": return Integer.toString(DEF);
		case "AS":  return Integer.toString(AS);
		case "MS":  return Float.toString(MS);
		}
		return null;
	}
	
	public String toString() {
		return "STR "+ STR + 
				"\nDEF " + DEF +
				"\nINT " + INT + 
				"\nDEX "+ DEF + 
				"\nSTA "+ STA +
				"\nATK " + ATK + 
				"\nAS " + AS + 
				"\nMS " + MS;
	}
}
