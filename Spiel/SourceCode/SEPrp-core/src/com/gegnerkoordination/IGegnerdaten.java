package com.gegnerkoordination;

import com.mygdx.game.Author;

@Author(name = "?Dilara Güler?")

public interface IGegnerdaten {
	Gegner[] getGegner();
	int[] getGegnerkoordinaten(Gegner a);
}
