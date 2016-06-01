package kalah.gameLogic;

public class BasicHumanPlayer implements Player{
	private int playerNumber;
	
	public int getPlayerIdentifier() {
		return playerNumber;
	}

	public void setIdentifier(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public BasicHumanPlayer(int number){
		playerNumber = number;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if ((o instanceof BasicHumanPlayer) && (((BasicHumanPlayer) o).getPlayerIdentifier() == this.playerNumber)) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	 
	 @Override
	 public String toString(){
		 return this.playerNumber+"";
	 }

}
