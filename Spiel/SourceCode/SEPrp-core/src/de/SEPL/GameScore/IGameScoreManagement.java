package de.SEPL.GameScore;

import com.badlogic.gdx.utils.Json.Serializable;

public interface IGameScoreManagement {

	void saveGameScore(com.mygdx.menu.PlayState);
	//In PlayState muss noch die VariableDeclarationID eingefügt werden!!
	//implements Serializable

	void loadGameScore();

}
