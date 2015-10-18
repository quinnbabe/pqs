package connect4;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class ConnectFourModel {
	
	public Player[][] board;
	public Player current_player;
	public Player last_player;
	public int column;
	
	//CopyOnWriteArrayList is thread-safe, do not need to consider synchronization
	private List<ConnectFourListener> listeners=
			new CopyOnWriteArrayList<ConnectFourListener>();
	
	/**
	 * create and initialize the board when create a Connect Four Model
	 */
	public ConnectFourModel(){
		initialBoard();
		current_player=Player.Blank;
		last_player=Player.Blank;
	}
	
	/**
	 * get the player's information in the Board
	 * @return player's information
	 */
	public Player[][] getBoard() {
		Player[][] boardView=new Player[7][6];
		for(int i=0;i<7;i++) {
			for(int j=0;j<6;j++) {
				boardView[i][j]=board[i][j];
			}
		}
		return boardView;
	}
	
	/**
	 * initial the whole board
	 */
	public void initialBoard(){
		board=new Player[7][6];
		for(int i=0;i<7;i++){
			for(int j=0;j<6;j++){
				board[i][j]=Player.Blank;
			}
		}
	}
	
	/**
	 * Start playing the connect four game,
	 */
	public void StartGame(){
		if(current_player==Player.Blank){
			initialBoard();
			current_player=Player.FirstPlayer;
			last_player=Player.Blank;
			fireGameStart();
		}
		else{
			fireGameAlreadyStart();
		}
	}

	 /** 
	  * Make a move on the board
	 * @param column
	 *             column player want to put
	 * @throws IllegalArgumentException
	 *             if the column number is larger than board's length.
	 */
	public void PlayGame(int column) {
		if(column<0||column>board.length-1){
			throw new IllegalArgumentException("The column cannot larger than length of the board.");
		}
	    else if(current_player==Player.Blank){
			fireGameNotStartYet();
		}
		else if(board[column][board[column].length-1]!=Player.Blank){
			if(gameBoardFull()){
				fireGameBoardFull();
			}
			else{
				fireGameColumnFull();
			}	
		}
		else if(gameEnded()){
			fireGameEnd();
		}
		else{
			for(int i=0;i<board[column].length;i++){
				if(board[column][i]==Player.Blank){
					board[column][i]=current_player;
					this.column=column+1;
//					System.out.println("current_player is"+current_player);
//					System.out.println("last_player is"+last_player);
					break;
				}
			}
			fireGameCurrentMove();
			this.last_player=current_player;
			switchPlayer();
			if(getWinner()!=Player.Blank){
				fireGameWon();
			}
		}
	} 
	
    /**
     * Make a random move 
     */
	public void RandomMove() {
		int random=(int) (Math.random() * 7);
		PlayGame(random);		
	}
	
	/**
	 * 
	 * @return column
	 *         the column player input
	 */
	public int getColumn(){
		return column;
	}
	
	/**
	 * 
	 * @return current_player
	 *         show who is the current_player, FirstPlayer or SecondPlayer or Blank 
	 */
	public Player getCurrentPlayer(){
		return current_player;
	}
	
	/**
	 * Reset the game, clear all player's history moves. 
	 */
	public void ResetGame(){
		initialBoard();
		fireGameReset();
	}
	
	/**
	 * 
	 * @return true if game is end
	 * @return false if game is not end
	 */
    public boolean gameEnded() {
		if (getWinner()!=Player.Blank) {
			return true;
		}
		if (gameBoardFull()) {
			return true;
		}
		return false;
	}

    /**
     * 
     * @return true if the board is full
     */
	public boolean gameBoardFull() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (board[i][j] == Player.Blank) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * how to win the game
	 * @return Player
	 *         who wins the game
	 */
	public Player getWinner() {
		for (int i=0; i<7;i++) {
			for (int j=0;j<6;j++) {
				if (RowWin(i, j)) {
					return board[i][j];
				} else if (ColumnWin(i, j)) {
					return board[i][j];
				} else if (DiagonalRightWin(i, j)) {
					return board[i][j];
				} else if (DiagonalLeftWin(i, j)) {
					return board[i][j];
				} 
			}
		}
		return Player.Blank;
	}

	/**
	 * 
	 * @param column 
	 * @param row 
	 *        which identyfy the winner's location on board[column][row]
	 * @return true if the wins the game in Diagonal Right direction
	 */
	public boolean DiagonalRightWin(int column, int row) {
		if (column<0||(column>board.length-1)||row<0||row>(board[column].length-1)) {
			throw new IllegalArgumentException(
					"The player location should be inside the board!");
		}
		Player player_location = board[column][row];
		if (player_location == Player.Blank) {
			return false;
		}
		for (int i=1; i<4;i++) {
			int column2=column-i;
			int row2 =row+i;
			if (column2<0||row2>board[column2].length-1) {
				return false;
			}
			if (player_location != board[column2][row2]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param column 
	 * @param row 
	 *        which identyfy the winner's location on board[column][row]
	 * @return true if the wins the game in Diagonal Left direction
	 */
	public boolean DiagonalLeftWin(int column, int row) {
		if (column<0||(column>board.length-1)||row<0||row>(board[column].length-1)) {
			throw new IllegalArgumentException(
					"The player location should be inside the board!");
		}
		Player player_location = board[column][row];
		if (player_location == Player.Blank) {
			return false;
		}
		for (int i=1; i<4;i++) {
			int column2=column+i;
			int row2 =row+i;
			if (column2>board.length-1||row2>board[column2].length-1) {
				return false;
			}
			if (player_location!= board[column2][row2]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param column 
	 * @param row 
	 *        which identyfy the winner's location on board[column][row]
	 * @return true if the wins the game in column direction
	 */
	public boolean ColumnWin(int column, int row) {
		if (column<0||(column>board.length-1)||row<0||row>(board[column].length-1)) {
			throw new IllegalArgumentException(
					"The player location should be inside the board!");
		}
		Player player_location = board[column][row];
		if (player_location==Player.Blank) {
			return false;
		}
		for (int i=1;i<4;i++) {
			int row2 = row + i;
			if (row2>board[column].length - 1) {
				return false;
			}
			if (player_location!=board[column][row2]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param column 
	 * @param row 
	 *        which identyfy the winner's location on board[column][row]
	 * @return true if the wins the game in row direction
	 */
	public boolean RowWin(int column, int row) {
		if (column<0||(column>board.length-1)||row<0||(row>board[column].length - 1)){
			throw new IllegalArgumentException(
					"The player location should be inside the board!");
		}
		Player player_location=board[column][row];
		if (player_location==Player.Blank) {
			return false;
		}
		for (int i=1; i<4;i++) {
			int column2=column + i;
			if (column2>board.length-1) {
				return false;
			}
			if (player_location!=board[column2][row]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Switch the player after one player making a move
	 */
	public void switchPlayer() {
		if (current_player==Player.FirstPlayer) {
			current_player=Player.SecondPlayer;
		} else {
			current_player=Player.FirstPlayer;
		}
	}

	public void fireGameStart(){
			for (ConnectFourListener listener : listeners) {
				listener.gameStart();
			}
	}
	
	public void fireGameAlreadyStart(){
		for (ConnectFourListener listener : listeners) {
			listener.gameAlreadyStart();
		}
	}
	
    public void fireGameNotStartYet(){
    	for (ConnectFourListener listener : listeners) {
			listener.gameNotStartYet();
		}
	}
    
    public void fireGameCurrentMove(){
    	for (ConnectFourListener listener : listeners) {
			listener.gameCurrentMove();
		}
    }
    
    public void fireGameColumnFull(){
    	for (ConnectFourListener listener : listeners) {
			listener.gameColumnFull();
		}
    }
    public void fireGameBoardFull(){
    	for (ConnectFourListener listener : listeners) {
			listener.gameBoardFull();
		}
    }
	
	public void fireGameWon() {
		for (ConnectFourListener listener : listeners) {
			listener.gameWinner();
		}
	}
	
	public void fireGameEnd(){
		for (ConnectFourListener listener : listeners) {
			listener.gameEnded();
		}
	}
	
	public void fireGameReset(){
		for (ConnectFourListener listener : listeners) {
			listener.gameReset();
		}
	}
	
	public void addListener(ConnectFourListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(ConnectFourListener listener){
		listeners.remove(listener);
	}
}
