package kalah.gameLogic;

import java.util.List;

public interface Board {

	List<PlayerBoard> getPlayerBoards();

	void setBoards(List<PlayerBoard> boards);

}