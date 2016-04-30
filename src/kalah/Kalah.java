package kalah;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.Config.Property;

/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Create board
		MankalaGameBoard board = new MankalaGameBoard(Config.getProperty(Property.PLAYERS));
		StandardMakalaGameRules gamerules = new StandardMakalaGameRules(board);
		boolean gameOn = true;
		while(gameOn){
			printBoard(board, io);
			int move = io.readInteger("Player P"+gamerules.getActivePlayer().getPlayer()+"'s turn - Specify house number or 'q' to quit:", 1, Config.getProperty(Property.BOARDSIZE), -1, "q");
			if(move == -1){
				io.println("Game over");
				gameOn=false;
			}else{
				move--; //subtract 1 to allow move to be in range
				gamerules.PlayTurn(move);
				gameOn = !gamerules.isGameOver();
			}
		}
		//Reset
		play(io);
	}
	private void printBoard(MankalaGameBoard board, IO io) {
		PlayerBoard pb1 = board.getPlayerBoards().get(0);
		PlayerBoard pb2 =  board.getPlayerBoards().get(1);
		
		String row1 = "", row2 = "", row3 = "", row4 = "", row5 = "";
		
		row1 += "+----+";
		row2 += "| P"+pb2.getPlayer().getPlayerNumber()+" |";
		row3 += "|    |";
		row4 += "|  "+pb2.getScore()+" |";
		row5 += "+----+";
		
		for (int i = 0; i < Config.getProperty(Property.BOARDSIZE); i++){
			row1 += "-------+";
			row2 += " "+((Config.getProperty(Property.BOARDSIZE) - i))+"["+getFormattedScore(pb2.getSeedCount((Config.getProperty(Property.BOARDSIZE) - i)-1))+"] |";
			row3 += (i < Config.getProperty(Property.BOARDSIZE)-1) ? "-------+" : "-------|";
			row4 += " "+(i+1)+"["+getFormattedScore(pb1.getSeedCount(i))+"] |";
			row5 += "-------+";
		}
		
		row1 += "----+";
		row2 += "  "+pb1.getScore()+" |";
		row3 += "    |";
		row4 += " P"+pb1.getPlayer().getPlayerNumber()+" |";
		row5 += "----+";
		
		io.println(row1);
		io.println(row2);
		io.println(row3);
		io.println(row4);
		io.println(row5);
		
	}
	
	private String getFormattedScore(int score){
		return (score < 9) ? " "+score : ""+score;
	}
}