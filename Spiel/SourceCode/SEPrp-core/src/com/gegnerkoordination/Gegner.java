package com.gegnerkoordination;

public class Gegner {
	Gegnertyp typ;
	float laufspeed;
	int STR;
	int INT;
	int STA;
	int Angriff;
	int Verteidigung;
	int AtkSpeed;
	Gegner(Gegnertyp typ){
		this.typ=typ;
		this.laufspeed=typ.laufspeed;
		this.STR=typ.STR;
		this.INT=typ.INT;
		this.STA=typ.STA;
		this.Angriff=typ.Angriff;
		this.Verteidigung=typ.Verteidigung;
		this.AtkSpeed=typ.AtkSpeed;
	}
}
