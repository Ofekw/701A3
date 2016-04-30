package kalah;

import java.util.ArrayList;
import java.util.List;

public class MankalaGameBoard implements Board{
	
	public MankalaGameBoard(int boardCount){
		for (int i = 1; i <= boardCount; i++){
			PlayerBoard board = new PlayerBoard(new Player(i));
			this.boards.add(board);
		}
	}
	
	private List<PlayerBoard> boards = new ArrayList<PlayerBoard>();
	
	public List<PlayerBoard> getPlayerBoards() {
		return boards;
	}

	public void setBoards(List<PlayerBoard> boards) {
		this.boards = boards;
	}

	
	
}
