package kalah;

import java.util.ArrayList;
import java.util.List;

public class MankalaGameBoard implements Board {
	private List<PlayerBoard> boards = new ArrayList<PlayerBoard>();
	
	public MankalaGameBoard(int boardCount){
		for (int i = 1; i <= boardCount; i++){
			PlayerBoard board = new MankalaPlayerBoard(new Player(i));
			this.boards.add(board);
		}
	}
	
	@Override
	public List<PlayerBoard> getPlayerBoards() {
		return boards;
	}

	@Override
	public void setBoards(List<PlayerBoard> boards) {
		this.boards = boards;
	}

	
	
}
