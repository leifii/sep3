package de.SEPL.GameScore;

import com.mygdx.game.Author;

@Author(name = "Dominikus Häckel")


public interface IGameScoreManagement {

	void saveGameScore(com.mygdx.menu.PlayState playState);

	void loadGameScore();

}
