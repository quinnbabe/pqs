package edu.nyu.pqs.drawpanel;

/**
 * This interface defines the function of Draw Panel
 * @author gongjieming1989
 *
 */
public interface DrawPanelListener {
	//draw point on the canvas, when drag the mouse in the view,
	//the brush draw every point it goes through 
	public void drawPoint(DrawPanelModel drawPanelModel);
	
	//clear the draw panel
	public void clear();	

}
