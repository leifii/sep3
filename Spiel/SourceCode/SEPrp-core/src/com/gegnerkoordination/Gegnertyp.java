package com.gegnerkoordination;

public class Gegnertyp {
	float laufspeed;
	int STR;
	int INT;
	int STA;
	int Angriff;
	int Verteidigung;
	int AtkSpeed;
	Gegnertyp(float laufspeed,int STR,int INT,int STA,int Angriff,int Verteidigung, int AtkSpeed){
		this.laufspeed=laufspeed;
		this.STR=STR;
		this.INT=INT;
		this.STA=STA;
		this.Angriff=Angriff;
		this.Verteidigung=Verteidigung;
		this.AtkSpeed=AtkSpeed;
	}
}
