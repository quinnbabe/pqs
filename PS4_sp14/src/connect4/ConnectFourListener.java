package connect4;

public interface ConnectFourListener {
	
	/**
	 * This method should be called when the game starts successfully.
	 */
	public void gameStart();
	
	/**
	 * This method should be called when the game has already started if someone press the start button..
	 */
	public void gameAlreadyStart();
	
	/**
	 * This method should be called when the game has not started if someone make a move.
	 */
	public void gameNotStartYet();
	
	/**
	 * This method should be called when player make a move each time.
	 */
	public void gameCurrentMove();
	
	/**
	 * This method should be called when one player want to make a move in the full column.
	 */
	public void gameColumnFull();
	
	/**
	 * This method should be called when one player want to make a move in the full column.
	 */
	public void gameBoardFull();
	
	/**
	 * This method should be called when one player win the game.
	 */
	public void gameWinner();
	
	/**
	 * This method should be called when one player wants to make a move, but the game has finished.
	 */
	public void gameEnded();
	
	/**
	 * This method should be called when reset the game.
	 */
	public void gameReset();
	
}
