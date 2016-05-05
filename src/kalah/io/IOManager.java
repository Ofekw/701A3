package kalah.io;

import com.qualitascorpus.testsupport.IO;

import kalah.gameLogic.GameRules;

public interface IOManager {

	void runGame(GameRules rules, IO io);

}