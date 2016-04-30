package kalah;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.stream.IntStream;

import kalah.Config.Property;

public class PlayerBoard {

	private int[] board;
	private int score;
	private Player player;
	
	public PlayerBoard(Player player){
		this.player = player;
		initilise();
	}

	public void initilise() {
		this.score = 0;
		//Get settings object to define board size;
		board = new int[6];
		//Get settings to define initial seed count
		int seedCount = Config.getProperty(Property.STARTINGSEEDS);
			Arrays.fill(board, seedCount);
	}
	
	public void addSeed(int position, int count){
		board[position] += count;
	}
	
	public void incrementScore(int count){
		this.score += count;
	}
	
	public void removeSeed(int position, int count){
		board[position] -= count;
	}
	
	public int getSeedCount(int position){
		int count = board[position];
		return count;
	}
	
	public void clearHouse(int position){
		board[position]= 0;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public int getBoardSize(){
		return this.board.length;
	}
	
	public int getScore(){
		return this.score;
	}

	public int getHouseSeedCount() {
		return IntStream.of(board).sum();
	}

}
