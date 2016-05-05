package kalah.gameLogic;

public abstract class PlayerBoard implements Comparable<PlayerBoard> {

	public abstract void initilise();

	public abstract void addSeed(int position, int count);

	public abstract void incrementScore(int count);

	public abstract void removeSeed(int position, int count);

	public abstract int getSeedCount(int position);

	public abstract void clearHouse(int position);

	public abstract Player getPlayer();

	public abstract int getBoardSize();

	public abstract int getScore();

	public abstract int getHouseSeedCount();

	@Override
	public int compareTo(PlayerBoard arg0) {
		return getScore() - arg0.getScore();
	}

}