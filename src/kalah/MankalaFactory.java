package kalah;

import kalah.Config.Property;
import kalah.gameLogic.Board;
import kalah.gameLogic.GameRules;
import kalah.gameLogic.MankalaGameBoard;
import kalah.gameLogic.StandardMankalaGameRules;
import kalah.io.IOManager;
import kalah.io.MankalaIOManager;

public class MankalaFactory {
	
	public static GameRules getGameRules(){
		Board board = new MankalaGameBoard(Config.getProperty(Property.PLAYERS));
		GameRules gamerules = new StandardMankalaGameRules(board);
		return gamerules;
	}
	
	public static IOManager getIOManager(){
		return new MankalaIOManager();
	}

}
