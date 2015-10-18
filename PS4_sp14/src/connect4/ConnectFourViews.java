package connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConnectFourViews implements ConnectFourListener {
	public static final int COL = 7;
	public static final int ROW = 6;
	private ConnectFourModel connect_four_model;
	private Player[][] board;
	private JButton[] jbutton;
	private JTextArea jtext;
	private JPanel panel_board;

	public ConnectFourViews(ConnectFourModel connectfourmodel){
		this.connect_four_model=connectfourmodel;
		connect_four_model.addListener(this);
		JFrame Frame=new JFrame("Connect Four Game");
		JPanel panel_columns_buttons=new JPanel();
		panel_board=new JPanel(){
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				board = connect_four_model.getBoard();
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 60 + 70 * COL, 15 + 80 * ROW);
				for (int row = 0; row < ROW; row++)
					for (int col = 0; col < COL; col++) {
						if (board[col][row] == Player.Blank) {
							g.setColor(Color.WHITE);
						} else if (board[col][row] == Player.FirstPlayer) {
							g.setColor(Color.RED);
						} else if (board[col][row] == Player.SecondPlayer) {
							g.setColor(Color.YELLOW);
						} 
						g.fillOval(80 * col,
								10 + 80 * (ROW - 1 - row), 50, 50);
					}
			}
		};
		JPanel panel_game_text=new JPanel();{
			jtext = new JTextArea(30, 18);
			panel_game_text.add(new JScrollPane(jtext));	
		}
		JPanel panel_game_control=new JPanel();
		
		
		ButtonListener buttonListener = new ButtonListener();
		jbutton = new JButton[10];
		for (int i = 0; i < 7; i++) {
			jbutton[i] = new JButton(String.valueOf(i + 1));
			jbutton[i].addActionListener(buttonListener);
			panel_columns_buttons.add(jbutton[i]);
		}
		
		jbutton[7] = new JButton("Start");
		jbutton[7].addActionListener(buttonListener);
		panel_game_control.add(jbutton[7]);

		jbutton[8] = new JButton("Reset");
		jbutton[8].addActionListener(buttonListener);
		panel_game_control.add(jbutton[8]);
		
		jbutton[9] = new JButton("RandomMove");
		jbutton[9].addActionListener(buttonListener);
		panel_game_control.add(jbutton[9]);
		
		Frame.add(panel_columns_buttons, BorderLayout.NORTH);
		Frame.add(panel_board, BorderLayout.CENTER);
		Frame.add(panel_game_control, BorderLayout.SOUTH);
		Frame.add(panel_game_text, BorderLayout.WEST);
		
		Frame.setSize(1000, 600);
		Frame.setVisible(true);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<7;i++){
				if(e.getSource()==jbutton[i]){
					connect_four_model.PlayGame(i);
				}
			}
			if(e.getSource()==jbutton[7]){
				connect_four_model.StartGame();
			}
			if(e.getSource()==jbutton[8]){
				connect_four_model.ResetGame();
			}
            if(e.getSource()==jbutton[9]){
            	connect_four_model.RandomMove();
			}			
		}
		
	}
	
	
	
	@Override
	public void gameStart() {
		jtext.append("Game started!\r\n");

	}

	@Override
	public void gameAlreadyStart() {
		jtext.append("Game has already started!\r\n");

	}

	@Override
	public void gameNotStartYet() {
	
		jtext.append("Game not started yet!Please press Start first !\r\n");

	}

	@Override
	public void gameCurrentMove() {
		if(connect_four_model.getCurrentPlayer()==Player.FirstPlayer){
			jtext.append("Red Make a Move At Column "+connect_four_model.getColumn()+"\r\n");
		}
		else if(connect_four_model.getCurrentPlayer()==Player.SecondPlayer){
			jtext.append("Yellow Make a Move At Column "+connect_four_model.getColumn()+"\r\n");
		}
		panel_board.repaint();
	}

	@Override
	public void gameColumnFull() {
		jtext.append("Column "+connect_four_model.getColumn() + " is full, please choose another column!\r\n");

	}

	@Override
	public void gameWinner() {
		if(connect_four_model.getWinner()==Player.FirstPlayer){
			jtext.append("red player win the game !\r\n");
		}
		else{
			jtext.append("yellow player win the game !\r\n");
		}
	}

	@Override
	public void gameEnded() {
		jtext.append("Game has ended !\r\n");

	}

	@Override
	public void gameReset() {
		jtext.append("The board has been reset !\r\n");
		panel_board.repaint();
	}

	@Override
	public void gameBoardFull() {
		jtext.append("Game End! The Board is full, Tie!\r\n");
		
	}

}
