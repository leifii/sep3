package com.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grafiken.Objekte;
import com.mygdx.menu.PlayState;

public class Trank extends Item {

	private int heal;
	private float alpha = 1;
	public Trank(int heal) {
		super("Heiltrank", ItemType.Trank, heal, heal / 10, Objekte.ITEMS2[5][3]);
		this.heal = heal;
		setDescription("Heilt " + heal + "  HP");
	}

	public int getHeal() {
		return heal;
	}
	
	//only as gainIcon
	@Override
	public void draw(SpriteBatch sb) {
		if(alpha > 0) {
			sb.setColor(1, 1, 1, alpha);
			sb.draw(getTextureRegion(), PlayState.getInstance().getPlayer().getPosition().x, 
					PlayState.getInstance().getPlayer().getPosition().y);
			alpha -= 0.02d;	
		}
		
		sb.setColor(1, 1, 1, 1);
	}

	@Override
	public boolean isDisposable() {
		return alpha <= 0;
	}

	@Override
	public boolean isVisible() {
		return alpha <= 0;
	}

	@Override
	public void setVisible(boolean visible) {
		alpha = visible ? 1 : 0;
	}
	
	public enum TrankType {
		kleinerHeiltrank("Kleiner Heiltrank", 20), mittlererHeiltrank("Mittlerer Heiltrank", 50), großerHeilTrank("Großer Heiltrank",100);
		
		private String name;
		private int heal;
		TrankType(String name, int heal) {
			this.name = name;
			this.heal = heal;
		}
		
		public static Trank getTrank(String name) {
			for(TrankType t : TrankType.values())
				if(t.name.compareTo(name) == 0)
					return new Trank(t.heal);
			
			System.out.println("Kein Trank mit dem Namen " + name);
			return null;
		}
	}

}
