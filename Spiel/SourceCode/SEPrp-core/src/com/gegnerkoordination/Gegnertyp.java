package com.gegnerkoordination;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Gegnertyp {
	private Texture bild;
	float laufspeed;
	int STR;
	int INT;
	int STA;
	int Angriff;
	int Verteidigung;
	int AtkSpeed;
	Gegnertyp(float laufspeed,int STR,int INT,int STA,int Angriff,int Verteidigung, int AtkSpeed,String bild){
		this.laufspeed=laufspeed;
		this.STR=STR;
		this.INT=INT;
		this.STA=STA;
		this.Angriff=Angriff;
		this.Verteidigung=Verteidigung;
		this.AtkSpeed=AtkSpeed;
		this.bild=new Texture(Gdx.files.internal(bild));
	}
}
