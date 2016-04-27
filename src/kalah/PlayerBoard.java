package kalah;

import java.security.InvalidParameterException;

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
		int seedCount = 4;
		for (int i = 0; i < board.length; i++){
			board[i] = seedCount;
		}
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
	
	public int getSeedCountAndClear(int position){
		int count = board[position];
		board[position]= 0;
		return count;
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
	

}
