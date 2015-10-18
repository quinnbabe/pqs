package connect4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectFourModelTest {

	ConnectFourModel connect_four_model;

	@Before
	public void setUp() throws Exception {
		System.out.println("Test beigins!");
		connect_four_model=new ConnectFourModel();
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("Test finishes!");
	}
	

	@Test
	public void testConnectFourModel() {
		assertEquals(true,connect_four_model.current_player==Player.Blank
				&&connect_four_model.last_player==Player.Blank);
		
	}

	@Test
	public void testGetBoard() {
		assertEquals(true,arrayEquals(connect_four_model.getBoard(), connect_four_model.board));
	}

	private static boolean arrayEquals(Object[][] o1,Object[][] o2) {
		if(o1==o2) return true;
		if(o1.length!=o2.length) return false;
		for(int i=0;i<7;i++){
			for(int j=0;j<6;j++){
				if(!o1[i][j].equals(o2[i][j])) return false;
			}			
		}
		return true;
	}
	
	@Test
	public void testInitialBoard() {
		for(int i=0;i<7;i++){
			for(int j=0;j<6;j++){
				assertEquals(true,connect_four_model.board[i][j]==Player.Blank);
			}
		}
	}

	@Test
	public void testStartGame() {
		connect_four_model.current_player=Player.Blank;
		connect_four_model.StartGame();
		assertEquals(true,connect_four_model.current_player==Player.FirstPlayer
				&&connect_four_model.last_player==Player.Blank);
	}
	
	@Test
	public void testGameAlreadyStart() {
		connect_four_model.current_player=Player.FirstPlayer;
		connect_four_model.StartGame();
		assertEquals(true,connect_four_model.current_player!=Player.Blank
				||connect_four_model.last_player!=Player.Blank);	
	}
	
	@Test
	public void testGameNotStartYet() {
		connect_four_model.current_player=Player.Blank;
		connect_four_model.PlayGame(1);
		assertEquals(true,connect_four_model.current_player==Player.Blank
				&&connect_four_model.last_player==Player.Blank);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentException() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(7);
		fail("IllegalArgumentException");
	}
	
	@Test
	public void testPlayGameColumnFull() {
		connect_four_model.StartGame();
		for(int i=0;i<7;i++){
			connect_four_model.PlayGame(0);
		}
		assertEquals(true,connect_four_model.current_player==Player.FirstPlayer
				&&connect_four_model.last_player==Player.SecondPlayer);
	}
	
	@Test
	public void testPlayGameSuccessfully() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(0);
		assertEquals(true,connect_four_model.current_player==Player.SecondPlayer
				&&connect_four_model.last_player==Player.FirstPlayer);
	}
	
	@Test
	public void testRandomMove() {
		connect_four_model.StartGame();
		connect_four_model.RandomMove();
		connect_four_model.RandomMove();
		assertEquals(true,connect_four_model.current_player==Player.FirstPlayer
				&&connect_four_model.last_player==Player.SecondPlayer);
	}

	@Test
	public void testGetColumn() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(3);
		assertEquals(true,connect_four_model.column==4);
	}

	@Test
	public void testGetCurrentPlayer() {
		connect_four_model.StartGame();
		assertEquals(true,connect_four_model.getCurrentPlayer()==Player.FirstPlayer);
	}

	@Test
	public void testResetGame() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(3);
		connect_four_model.ResetGame();
		if(checkBoardEmpty()){
			assertEquals(true,connect_four_model.current_player==Player.SecondPlayer
					&&connect_four_model.last_player==Player.FirstPlayer);
		}
		else{
			fail("ResetGame is fail");
		}
	}
	
	public boolean checkBoardEmpty(){
		for(int i=0;i<7;i++){
			for(int j=0;j<6;j++){
				if(connect_four_model.board[i][j]!=Player.Blank){
					return false;
				}
			}
		}	
		return true;
	}

	@Test
	public void testGameWinnerEnded() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(4);
		assertEquals(true,connect_four_model.gameEnded());	
	}
	
	@Test
	public void testGameBoardFull() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(5);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(6);
		connect_four_model.PlayGame(5);
		connect_four_model.PlayGame(5);
		connect_four_model.PlayGame(6);
		connect_four_model.PlayGame(6);
		connect_four_model.PlayGame(6);
		connect_four_model.PlayGame(5);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(5);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(6);
		connect_four_model.PlayGame(6);
		connect_four_model.PlayGame(5);
		assertEquals(true,connect_four_model.gameEnded());	
	}


	@Test
	public void testGetWinner() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(4);
		assertEquals(true,connect_four_model.getWinner()==Player.FirstPlayer);		
	}

	@Test
	public void testDiagonalRightWin() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(3);
		assertEquals(true,connect_four_model.board[1][1]==Player.FirstPlayer
				&&connect_four_model.board[1][1]==Player.FirstPlayer
				&&connect_four_model.board[2][2]==Player.FirstPlayer
				&&connect_four_model.board[3][3]==Player.FirstPlayer);
	}

	@Test
	public void testDiagonalLeftWin() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(6);
		connect_four_model.PlayGame(5);
		connect_four_model.PlayGame(5);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(3);
		connect_four_model.PlayGame(4);
		connect_four_model.PlayGame(3);
		assertEquals(true,connect_four_model.board[6][0]==Player.FirstPlayer
				&&connect_four_model.board[5][1]==Player.FirstPlayer
				&&connect_four_model.board[4][2]==Player.FirstPlayer
				&&connect_four_model.board[3][3]==Player.FirstPlayer);
	}

	@Test
	public void testColumnWin() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(0);
		assertEquals(true,connect_four_model.board[0][0]==Player.FirstPlayer
				&&connect_four_model.board[0][1]==Player.FirstPlayer
				&&connect_four_model.board[0][2]==Player.FirstPlayer
				&&connect_four_model.board[0][3]==Player.FirstPlayer);
	}

	@Test
	public void testRowWin() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(0);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(1);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(2);
		connect_four_model.PlayGame(3);
		assertEquals(true,connect_four_model.board[0][0]==Player.FirstPlayer
				&&connect_four_model.board[1][0]==Player.FirstPlayer
				&&connect_four_model.board[2][0]==Player.FirstPlayer
				&&connect_four_model.board[3][0]==Player.FirstPlayer);
	}

	@Test
	public void testSwitchPlayer() {
		connect_four_model.StartGame();
		connect_four_model.PlayGame(0);
		assertEquals(true,connect_four_model.current_player==Player.SecondPlayer
				&&connect_four_model.last_player==Player.FirstPlayer);
	}

}
