package test;

import com.badlogic.gdx.Gdx;

public class skill {

	int lvl;
	int dmg;
	int dmgfaktor;
	int cdnow;
	int cd;
	int cdfaktor;
	private Texture bild;
	boolean locked;
	boolean ready;
	
	
	skill(int lvl, int dmg, int dmgfaktor, int cdnow, int cd, int cdfaktor, String bild, boolean locked, boolean ready){
		this.lvl = lvl;
		this.dmg = dmg;
		this.dmgfaktor = dmgfaktor;
		this.cdnow = cdnow;
		this.cd = cd;
		this.cdfaktor = cdfaktor;
		this.bild = new Texture(Gdx.files.internal(bild));
		this.unlocked = locked;
		this.ready = ready;
		
	}
	
	public void upgrade(){
		lvl += 1;
		dmg += 2 * dmgfaktor;
		cd -= 1*cdfaktor;
	}
	
	public void cdtimer(){
		
	}
	
	
	
	
	
}
