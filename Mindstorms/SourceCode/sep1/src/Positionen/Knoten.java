/**
 * @author ${user}
 *
 * 
 */
package Positionen;

import java.util.LinkedList;
import java.util.List;

public class Knoten {
	private int position;
	
	public Knoten(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
	}
	
	public boolean equals(Object o) {
		if(o != null && o instanceof Knoten)
			return position == ((Knoten) o).position;
		return false;
	}
	
	public static List<Knoten> generateKnotenList() {
		List<Knoten> knotenList = new LinkedList<>();
		for(int y = 0; y < 60; y+=10) {
			for(int x = 1; x < 7; x++) {
				knotenList.add(new Knoten(y+x));
			}
		}
		return knotenList;
	}
}

