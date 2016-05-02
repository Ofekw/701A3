package kalah;

import java.util.ArrayList;
import java.util.List;

public class MankalaGameBoard implements Board {
	
	public MankalaGameBoard(int boardCount){
		for (int i = 1; i <= boardCount; i++){
			MankalaPlayerBoard board = new MankalaPlayerBoard(new Player(i));
			this.boards.add(board);
		}
	}
	
	private List<MankalaPlayerBoard> boards = new ArrayList<MankalaPlayerBoard>();
	
	@Override
	public List<MankalaPlayerBoard> getPlayerBoards() {
		return boards;
	}

	@Override
	public void setBoards(List<MankalaPlayerBoard> boards) {
		this.boards = boards;
	}

	
	
}
