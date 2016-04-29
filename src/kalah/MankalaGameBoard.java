package kalah;

import java.util.ArrayList;
import java.util.List;

public class MankalaGameBoard implements Board{
	
	private PlayerBoard playerOneHouses;
	private PlayerBoard playerTwoHouses;
	
	private List<PlayerBoard> boards = new ArrayList<PlayerBoard>();
	
	public PlayerBoard getPlayerOneHouses() {
		return playerOneHouses;
	}

	public void setPlayerOneHouses(PlayerBoard playerOneHouses) {
		this.playerOneHouses = playerOneHouses;
	}

	public PlayerBoard getPlayerTwoHouses() {
		return playerTwoHouses;
	}

	public void setPlayerTwoHouses(PlayerBoard playerTwoHouses) {
		this.playerTwoHouses = playerTwoHouses;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	private Player activePlayer;
	
	public MankalaGameBoard(int boardCount){
		for (int i = 1; i <= boardCount; i++){
			PlayerBoard board = new PlayerBoard(new Player(i));
			this.boards.add(board);
		}
		this.activePlayer = this.boards.get(0).getPlayer();
	}
	
}
