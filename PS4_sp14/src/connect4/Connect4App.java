package connect4;


public class Connect4App {
	
	private Connect4App(){
		FirstPlayerView();
		SecondPlayerView();	
	}
	ConnectFourModel cfm=new ConnectFourModel();
	private void FirstPlayerView(){
		ConnectFourViews cfv=new ConnectFourViews(cfm);
	}
	
	private void SecondPlayerView(){
		ConnectFourViews cfv=new ConnectFourViews(cfm);
	}
	
	public static void main(String[] args) {
		new Connect4App();
		
	}

}
