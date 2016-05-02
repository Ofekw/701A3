package kalah;

public interface PlayerBoard {

	void initilise();

	void addSeed(int position, int count);

	void incrementScore(int count);

	void removeSeed(int position, int count);

	int getSeedCount(int position);

	void clearHouse(int position);

	Player getPlayer();

	int getBoardSize();

	int getScore();

	int getHouseSeedCount();

	int compareTo(PlayerBoard arg0);

}