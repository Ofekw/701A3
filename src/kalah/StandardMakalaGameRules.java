package kalah;

public class StandardMakalaGameRules implements GameRules {
	
	private MankalaGameBoard board;


	public StandardMakalaGameRules(MankalaGameBoard board){
		this.board = board;
	}
	
	// takes in origin and destination to move seed
		// Returns the next player to play
		public Player moveSeed(int origin){
			PlayerBoard currentPlayerBoard = getCurrentPlayBoard();
			PlayerBoard oppositionBoard = getOppositionBoard();
			PlayerBoard activeBoard = currentPlayerBoard;

			int seedCount = currentPlayerBoard.getSeedCount(origin);
			currentPlayerBoard.clearHouse(origin);
			int position = origin+1;
			for (int i = 0; i < seedCount; i++){
				if (position > currentPlayerBoard.getBoardSize()){
					position = 0;
					// outside of board boundaries add to score if player house
					if(currentPlayerBoard.getPlayer() == activeBoard.getPlayer()){
						currentPlayerBoard.incrementScore(1);
					}else{
						//Toggle boards
						activeBoard = ToggleActiveBoard(activeBoard, currentPlayerBoard, oppositionBoard);
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
						if(currentPlayerBoard.getPlayer() == activeBoard.getPlayer()){
							return currentPlayerBoard.getPlayer();
						}else{  //If position outside of playerboard and not players board (ie loop around)
							return oppositionBoard.getPlayer();
						}
						// Crazy Mankala rule that captures opponents seeds
					}else if(currentPlayerBoard.getPlayer() == activeBoard.getPlayer()){
						if (currentPlayerBoard.getSeedCount(position) == 0){

							int oppositePosition = getOppositePosition(position);
							if (oppositionBoard.getSeedCount(oppositePosition) > 0){
								int oppositionSeeds = oppositionBoard.getSeedCount(oppositePosition);
								oppositionBoard.clearHouse(oppositePosition);
								int houseSeeds = currentPlayerBoard.getSeedCount(position);
								currentPlayerBoard.clearHouse(position);
								currentPlayerBoard.incrementScore(houseSeeds + oppositionSeeds);
								return oppositionBoard.getPlayer();
							}
						}
					}
					return oppositionBoard.getPlayer();
				}
				position++;
			}
			return null;
		}


		private PlayerBoard ToggleActiveBoard(PlayerBoard activeBoard, PlayerBoard board1, PlayerBoard board2) {
			return (activeBoard.getPlayer() == board1.getPlayer()) ? board2 : board1;
		}


		private int getOppositePosition(int position) {
				return getOppositionBoard().getBoardSize() - position; // Or boarsize + position % 12
		}
		
		private int getContinousPosition(int position){
			return position % 7;
			
		}


		private PlayerBoard getOppositionBoard() {
			return (board.getActivePlayer() != board.getPlayerOneHouses().getPlayer()) ? board.getPlayerTwoHouses() : board.getPlayerOneHouses();
		}


		private PlayerBoard getCurrentPlayBoard() {
			return (board.getActivePlayer() == board.getPlayerOneHouses().getPlayer()) ? board.getPlayerOneHouses() : board.getPlayerTwoHouses() ;
		}

}
