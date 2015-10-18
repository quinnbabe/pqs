package edu.nyu.pqs.drawpanel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



/**
 * This class is the model of Draw Panel. It receives the value from view, 
 * listens the draw action between view and model, 
 * and then update the values in the model
 * @author gongjieming1989
 *
 */
public class DrawPanelModel {
	
	public List<DrawPanelListener> listeners = 
			new CopyOnWriteArrayList<DrawPanelListener>();
	public static DrawPanelModel instance=null;
	public double x;
	private double y;
	private int size;
	private DrawColor color;
	
	/**
	 * mark the construct as private to construct singleton pattern
	 */
	private DrawPanelModel(){
		x=0;
		y=0;
		size=1;
		color=DrawColor.BLACK;
	}
	
	/**
	 * @return a instance of DrawPanelModel
	 */
	public static DrawPanelModel getInstance(){
		if(instance==null){
			instance=new DrawPanelModel();
		}
		return instance;
	}
	
	/**
	 * @return x
	 *     the horizon value of the point
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @param x
	 *    the horizon value of a point
	 */
	public void setX(double x) {
		this.x=x;
	}
	
	/**
	 * @return y
	 *    the vertical value of the point
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @param y
	 *   the verticly value of a point
	 */
	public void setY(double y) {
		this.y=y;
	}
	
	/**
	 * @return size
	 *   the brush size of draw panel
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @param size
	 *   the brush size of draw panel
	 */
	public void setSize(int size) {
		this.size=size;
	}
	
	/**
	 * @return color
	 *   the color of brush
	 */
	public DrawColor getColor() {
		return color;
	}
	
	/**
	 * @param color
	 *    the color of brush
	 */
	public void setColor(DrawColor color) {
		this.color=color;
	}
	
	/**
	 * set the location of point
	 * @param x
	 *     horizon position of point
	 * @param y
	 *     vertical position of point
	 */
	public void setPosition(double x,double y){
		setX(x);
		setY(y);
	}
	
	/**
	 * 
	 * @param x
	 *   horizon position of point
	 * @param y
	 *   vertical position of point
	 * @param color
	 *   color of brush
	 * @param size
	 *   size of brush
	 */
	public void drawPoint(double x,double y,DrawColor color, int size){
		setPosition(x,y);
		fireDrawPoint();
	}

	/**
	 * clear the draw panel
	 */
	public void clearPanel(){
		fireClear();
	}
	
	/**
	 * listen the drawing action in the view
	 */
	public void fireDrawPoint(){
		for(DrawPanelListener listener : listeners){
			listener.drawPoint(this);
		}
	}
	
	/**
	 * listen the clear draw panel action in the view
	 */
	public void fireClear(){
		for(DrawPanelListener listener : listeners){
			listener.clear();
		}
	}
	
	/**
	 * register the view to the viewlists
	 * @param listener
	 *     view of draw panel
	 */
	public void addListener(DrawPanelListener listener){
		listeners.add(listener);
	}
	
	/**
	 * remove the view from the viewlists
	 * @param listener
	 *   view of draw panel
	 */
	public void removeListener(DrawPanelListener listener){
		listeners.remove(listener);
	}
	
}
