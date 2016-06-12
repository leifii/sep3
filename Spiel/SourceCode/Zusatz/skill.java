package test;

import com.badlogic.gdx.Gdx;

public class skill {

	int lvl;
	int dmg;
	int dmgfaktor;
	int cd;
	int cdfaktor;
	private Texture bild;
	
	
	skill(int lvl, int dmg, int dmgfaktor, int cd, int cdfaktor, String bild){
		this.lvl = lvl;
		this.dmg = dmg;
		this.dmgfaktor = dmgfaktor;
		this.cd = cd;
		this.cdfaktor = cdfaktor;
		this.bild = new Texture();
		
	}
	
	public void upgrade(){
		lvl += 1;
		dmg += 2 * dmgfaktor;
		cd -= 1*cdfaktor;
	}
	
	
}
