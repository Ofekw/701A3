package kalah;

import kalah.Config.Property;
import kalah.gameLogic.Board;
import kalah.gameLogic.GameRules;
import kalah.gameLogic.MankalaGameBoard;
import kalah.gameLogic.StandardMankalaGameRules;
import kalah.io.IOManager;
import kalah.io.MankalaIOManager;

public class MankalaFactory implements AbstractGameFactory{
	
	public GameRules getGameRules(){
		Board board = new MankalaGameBoard(Config.getProperty(Property.PLAYERS));
		GameRules gamerules = new StandardMankalaGameRules(board);
		return gamerules;
	}
	
	public IOManager getIOManager(){
		return new MankalaIOManager();
	}

}
