package kalah;

public class MankalaGameBoard implements Board{
	
	private PlayerBoard playerOneHouses;
	private PlayerBoard playerTwoHouses;
	private Player activePlayer;
	
	public MankalaGameBoard(){
		this.playerOneHouses = new PlayerBoard(new Player(1)); // Possibly pass in a player
		this.playerTwoHouses = new PlayerBoard(new Player(2));
		this.activePlayer = this.playerOneHouses.getPlayer();
	}

	
	// Refactor game rules out
	// takes in origin and destination to move seed
	// Returns the next player to play
	public Player moveSeed(int origin){
		PlayerBoard currentPlayerBoard = getCurrentPlayBoard();
		PlayerBoard oppositionBoard = getOppositionBoard();
		PlayerBoard activeBoard = currentPlayerBoard;
		
		int seedCount = currentPlayerBoard.getSeedCountAndClear(origin);
		int position = origin+1;
		for (int i = 0; i < seedCount; i++){
			// check if final seed
			if (i == seedCount-1){
				if (position > currentPlayerBoard.getBoardSize()){
					if(currentPlayerBoard.getPlayer() == activeBoard.getPlayer()){
						currentPlayerBoard.incrementScore(1);
						return currentPlayerBoard.getPlayer();
					}
				}
				
			}
			
			if (position > currentPlayerBoard.getBoardSize()){
				// outside of board boundaries add to score and resent position
				position = 0;
				if(currentPlayerBoard.getPlayer() == activeBoard.getPlayer()){
					currentPlayerBoard.incrementScore(1);
				}else{
					//Toggle boards
					activeBoard = oppositionBoard;
					activeBoard.addSeed(position, 1);
				}
			}else{
				// simply add a seed;
				activeBoard.addSeed(position, 1);
				position++;
			}
		}
		
		return null;
	}


	private PlayerBoard getOppositionBoard() {
		return (activePlayer != playerOneHouses.getPlayer()) ? playerTwoHouses : playerOneHouses;
	}


	private PlayerBoard getCurrentPlayBoard() {
		return (activePlayer == playerOneHouses.getPlayer()) ? playerOneHouses : playerTwoHouses;
	}
}
