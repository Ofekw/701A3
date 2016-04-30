package kalah;

import java.util.ArrayList;
import java.util.List;

import com.qualitascorpus.testsupport.MockIO;

public class StandardMakalaGameRules implements GameRules {
	
	private MankalaGameBoard board;
	private PlayerBoard activePlayerBoard;


	public StandardMakalaGameRules(MankalaGameBoard board){
		this.board = board;
		this.activePlayerBoard = this.board.getPlayerBoards().get(0);
	}
	
	public PlayerBoard getActivePlayer() {
		return activePlayerBoard;
	}

	public void setActivePlayer(PlayerBoard activePlayerBoard) {
		this.activePlayerBoard = activePlayerBoard;
	}
	
	public void PlayTurn(int move){
		this.activePlayerBoard = moveSeed(move);
	}

	
	// takes in origin and destination to move seed
		// Returns the next player to play
		private PlayerBoard moveSeed(int origin){
			PlayerBoard activeBoard = this.activePlayerBoard;

			int seedCount = this.activePlayerBoard.getSeedCount(origin);
			this.activePlayerBoard.clearHouse(origin);
			int position = origin+1;
			for (int i = 0; i < seedCount; i++){
				if (position > this.activePlayerBoard.getBoardSize()){
					position = 0;
					// outside of board boundaries add to score if player house
					if(this.activePlayerBoard.getPlayer() == activeBoard.getPlayer()){
						this.activePlayerBoard.incrementScore(1);
					}else{
						//Toggle boards
						activeBoard = getNextBoard(activeBoard);
						activeBoard.addSeed(position, 1);
					}
				}else{
					// simply add a seed;
					activeBoard.addSeed(position, 1);
				}
				// check if final seed
				if (i == seedCount-1){
					//Check for extra turn
					if (position > activeBoard.getBoardSize()){
						if(this.activePlayerBoard.getPlayer() == activeBoard.getPlayer()){
							return activeBoard;
						}else{  //If position outside of playerboard and not players board (ie loop around)
							return getNextBoard(activeBoard);
						}
						// Crazy Mankala rule that captures opponents seeds
					}else if(this.activePlayerBoard.getPlayer() == activeBoard.getPlayer()){
						if (activeBoard.getSeedCount(position) == 0){
							int oppositePosition = getOppositePosition(position);
							PlayerBoard nextBoard = getNextBoard(activeBoard);
							if (nextBoard.getSeedCount(oppositePosition) > 0){
								int oppositionSeeds = nextBoard.getSeedCount(oppositePosition);
								nextBoard.clearHouse(oppositePosition);
								int houseSeeds = this.activePlayerBoard.getSeedCount(position);
								this.activePlayerBoard.clearHouse(position);
								this.activePlayerBoard.incrementScore(houseSeeds + oppositionSeeds);
								return nextBoard;
							}
						}
					}
					return getNextBoard(activeBoard);
				}
				position++;
			}
			return null;
		}
		
		private int getOppositePosition(int position) {
			return this.activePlayerBoard.getBoardSize() - position;
		}

		private PlayerBoard getNextBoard(PlayerBoard activeBoard) {
			boolean boardFound = false;
			PlayerBoard board = this.board.getPlayerBoards().get(0); // base case get first
			
			for (PlayerBoard b : this.board.getPlayerBoards()){
				if (b.getPlayer() == this.activePlayerBoard.getPlayer()){
					boardFound = true;
				}else if (boardFound){
					return b;
				}
			}
			
			return board;
		}

		public boolean isGameOver(){
			for (PlayerBoard board : this.board.getPlayerBoards()){
				if (board.getHouseSeedCount() == 0){
					for (PlayerBoard board2 : this.board.getPlayerBoards()){
						if (board2.getPlayer() != board.getPlayer()){
							board2.incrementScore(board2.getHouseSeedCount());
						}
					}
					return true;
				}
			}
			return false;
		}
		
		private int getContinousPosition(int position){
			return position % 7; // % sizeOfBoard
			
		}


		

}
