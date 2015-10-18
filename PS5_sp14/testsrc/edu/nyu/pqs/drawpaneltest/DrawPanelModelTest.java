package edu.nyu.pqs.drawpaneltest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.drawpanel.DrawColor;
import edu.nyu.pqs.drawpanel.DrawPanelListener;
import edu.nyu.pqs.drawpanel.DrawPanelModel;
import edu.nyu.pqs.drawpanel.DrawPanelView;

public class DrawPanelModelTest {
	private DrawPanelModel drawPanelModel;
	private DrawPanelListener listener;

	@Before
	public void setUp(){
		drawPanelModel=DrawPanelModel.getInstance();
	}

	@After
	public void tearDown(){
		drawPanelModel=null;
		}

	@Test
	public void testGetInstanceIsNull() {
		drawPanelModel.instance=null;
		drawPanelModel.getInstance();
		assertEquals(false,drawPanelModel.instance.equals(null));
	}
	
	@Test
	public void testGetInstanceIsNotNull() {
		assertEquals(true,drawPanelModel.instance.equals(drawPanelModel.getInstance()));
	}

	@Test
	public void testGetX() {
		drawPanelModel.setX(2);
		assertEquals(true,drawPanelModel.getX()==2);
	}

	@Test
	public void testGetSetY() {
		drawPanelModel.setY(3);
		assertEquals(true,drawPanelModel.getY()==3);

	}

	@Test
	public void testGetSetSize() {
		drawPanelModel.setSize(5);
		assertEquals(true,drawPanelModel.getSize()==5);
	}

	@Test
	public void testGetSetColor() {
		drawPanelModel.setColor(DrawColor.RED);
		assertEquals(true,drawPanelModel.getColor()==DrawColor.RED);
	}
	
	@Test
	public void testSetPosition(){
		drawPanelModel.setPosition(10, 20.0);
		assertEquals(true,10==drawPanelModel.getX());
		assertEquals(true,20.0==drawPanelModel.getY());
	}

	@Test
	public void testDrawPoint() {
		drawPanelModel.drawPoint(11, 12, DrawColor.YELLOW, 8);
		assertEquals(true,11==drawPanelModel.getX());
		assertEquals(true,12==drawPanelModel.getY());
	}

}
