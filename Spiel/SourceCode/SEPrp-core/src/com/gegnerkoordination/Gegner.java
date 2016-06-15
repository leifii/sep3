package com.gegnerkoordination;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Gegner {
	private Texture bild;
	float laufspeed;
	int STR;
	int INT;
	int STA;
	int Angriff;
	int Verteidigung;
	int AtkSpeed;
	int exp;
	Gegner(float laufspeed,int STR,int INT,int STA,int Angriff,int Verteidigung, int AtkSpeed,String bild,int exp){
		this.laufspeed=laufspeed;
		this.STR=STR;
		this.INT=INT;
		this.STA=STA;
		this.Angriff=Angriff;
		this.Verteidigung=Verteidigung;
		this.AtkSpeed=AtkSpeed;
		this.bild=new Texture(Gdx.files.internal(bild));
		this.exp=exp;
	}
}
