package edu.nyu.pqs.drawpanel;

public class DrawPanelApp {

	private DrawPanelModel drawPanelModel;
	private DrawPanelView drawPanelView;
	
	private DrawPanelApp(){
		drawPanelModel=DrawPanelModel.getInstance();
		drawPanelView=new DrawPanelView(drawPanelModel);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DrawPanelApp();
		new DrawPanelApp();

	}

}
