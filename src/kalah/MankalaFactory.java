package kalah;

import kalah.Config.Property;

public class MankalaFactory {
	
	public static GameRules getGameRules(){
		Board board = new MankalaGameBoard(Config.getProperty(Property.PLAYERS));
		GameRules gamerules = new StandardMakalaGameRules(board);
		return gamerules;
	}
	
	public static IOManager getIOManager(){
		return new MankalaIOManager();
	}

}
