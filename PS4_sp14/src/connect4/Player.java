package connect4;

/**
 * Enumeration for different players 
 */
public enum Player {
	Blank(0),FirstPlayer(1), SecondPlayer(2);
	private int playerNumber;
	private Player(int palyerNumber){
		this.playerNumber = palyerNumber;
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}
}
