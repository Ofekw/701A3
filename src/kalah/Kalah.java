package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.gameLogic.GameRules;
import kalah.gameLogic.MankalaGameBoard;
import kalah.io.IOManager;

/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Create game variant factory
		AbstractGameFactory factory = new MankalaFactory();
		GameRules gamerules = factory.getGameRules();
		IOManager ioManager = factory.getIOManager();
		ioManager.runGame(gamerules, io);
	}
}