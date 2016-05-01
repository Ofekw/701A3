package kalah;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.TooManyListenersException;
import java.util.stream.Collectors;

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
		int move = -1;
		while(gameOn){
			printBoard(board, io);
			move = io.readInteger("Player P"+gamerules.getActivePlayer().getPlayer()+"'s turn - Specify house number or 'q' to quit: ", 1, Config.getProperty(Property.BOARDSIZE), -1, "q");
			if(move == -1){
				gameOn=false;
			}else{
				move--; //subtract 1 to allow move to be in range
				boolean validMove = gamerules.PlayTurn(move);
				if (!validMove){
					io.println("House is empty. Move again.");
				}else{
					gameOn = !gamerules.isGameOver();
				}
			}
		}
		
		// If player quit
		if (move == -1){
			io.println("Game over");
			printBoard(board, io);
		}else{ // Otherwsise game ended
			printBoard(board, io);					
			io.println("Game over");
			printBoard(board, io);
			gamerules.calculateGameOverScore();
			board.getPlayerBoards().forEach( b -> io.println("	player "+b.getPlayer()+":"+b.getScore()));
			PlayerBoard winner = Collections.max(board.getPlayerBoards());
			int highestScore = board.getPlayerBoards().stream().mapToInt( x -> x.getScore()).max().orElse(-1);
			List<PlayerBoard> winners = board.getPlayerBoards().stream().filter( b -> b.getScore() == highestScore).collect(Collectors.toList());
			if (winners.size()> 1){
				io.print("A tie!");
			}else{
				io.println("Player " + winner.getPlayer()+ " wins!" );
			}
		}
	}
	private void printBoard(MankalaGameBoard board, IO io) {
		PlayerBoard pb1 = board.getPlayerBoards().get(0);
		PlayerBoard pb2 =  board.getPlayerBoards().get(1);
		
		String row1 = "", row2 = "", row3 = "", row4 = "", row5 = "";
		
		row1 += "+----+";
		row2 += "| P"+pb2.getPlayer().getPlayerNumber()+" |";
		row3 += "|    |";
		row4 += "| "+getFormattedScore(pb2.getScore())+" |";
		row5 += "+----+";
		
		for (int i = 0; i < Config.getProperty(Property.BOARDSIZE); i++){
			row1 += "-------+";
			row2 += " "+((Config.getProperty(Property.BOARDSIZE) - i))+"["+getFormattedScore(pb2.getSeedCount((Config.getProperty(Property.BOARDSIZE) - i)-1))+"] |";
			row3 += (i < Config.getProperty(Property.BOARDSIZE)-1) ? "-------+" : "-------|";
			row4 += " "+(i+1)+"["+getFormattedScore(pb1.getSeedCount(i))+"] |";
			row5 += "-------+";
		}
		
		row1 += "----+";
		row2 += " "+getFormattedScore(pb1.getScore())+" |";
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
		if (score > 9){
			return ""+score;
		}else{
			return " "+score;
		}
	}
}