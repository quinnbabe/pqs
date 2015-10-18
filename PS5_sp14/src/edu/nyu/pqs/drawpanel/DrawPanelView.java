package edu.nyu.pqs.drawpanel;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This is the view of draw Panel
 * @author gongjieming1989
 *
 */
public class DrawPanelView implements DrawPanelListener {
	
	private JFrame jFrame;
	private DrawPanelModel drawPanelModel = null;
	private JPanel functionPanel;
	private CanvasPanel canvasPanel;
	private String[] colorSelect = new String[] {"Color","Black", "Red", "Yellow","Green","Eraser"};
	private JComboBox colorCombox;
	private String[] sizeSelect = new String[] {"Size","1","2","3","4","5","6","7","8","9","10"};
	private JComboBox sizeCombox;
	private JButton clearButton;
	private ButtonListener buttonListen;
	private Image image;
	private Graphics2D graphics2d;
	private Point point;
	private double xPixel;
	private double yPixel;
	
	//initialize the draw panel
	public DrawPanelView(DrawPanelModel drawPanelModel){
		
		this.drawPanelModel = drawPanelModel;
		drawPanelModel.addListener(this);
		jFrame = new JFrame("Welcome to Draw Panel");
		functionPanel = new JPanel();
		canvasPanel = new CanvasPanel();
		buttonListen = new ButtonListener();
		colorCombox = new JComboBox(colorSelect);
		sizeCombox = new JComboBox(sizeSelect);
		clearButton = new JButton("clear");
		
		clearButton.addActionListener(buttonListen);
		functionPanel.add(colorCombox);
		functionPanel.add(sizeCombox);
		functionPanel.add(clearButton);
	
		jFrame.add(canvasPanel, BorderLayout.CENTER);
		jFrame.add(functionPanel, BorderLayout.NORTH);
		jFrame.setVisible(true);
		jFrame.setSize(800, 800);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void drawPoint(DrawPanelModel drawPanelModel) {
		canvasPanel.drawColorSize(drawPanelModel.getX(), drawPanelModel.getY(), drawPanelModel.getColor(), drawPanelModel.getSize());		
	}

	@Override
	public void clear() {
		canvasPanel.clearcanvasPanel();
	}

	public class CanvasPanel extends JPanel{
		
		private CanvasPanel(){
			point = null;
			setPreferredSize(new Dimension(500, 800));
			addMouseListener(new MouseAdapter(){
				
				@Override
				public void mousePressed(MouseEvent e) {
					point = e.getPoint();
					drawPanelModel.setColor(selectColor());
					drawPanelModel.setSize(selectSize());
				}
				
				@Override
				public void mouseClicked(MouseEvent e){
					drawPanelModel.drawPoint(point.getX(), point.getY(),drawPanelModel.getColor(),drawPanelModel.getSize());
				}
			});
			
			addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					double xChange = e.getX() - point.getX();
					double yChange = e.getY() - point.getY();
					double change = Math.max(Math.abs(xChange), Math.abs(yChange));
					double xChengeRate = xChange / change;
					double yChengeRate = yChange / change;
					xPixel = point.getX();
					yPixel = point.getY();
						for (int i = 0; i <= change; i++) {
							drawPanelModel.drawPoint(xPixel, yPixel, drawPanelModel.getColor(),drawPanelModel.getSize());
							xPixel += xChengeRate;
							yPixel += yChengeRate;
						}
						point = e.getPoint();
				}
			});
		}

		private DrawColor selectColor() {
			if ("Color".equals((String) colorCombox.getSelectedItem())) {
				return DrawColor.BLACK;
			} else if ("Black".equals((String) colorCombox.getSelectedItem())) {
				return DrawColor.BLACK;
			}else if ("Red".equals((String) colorCombox.getSelectedItem())) {
				return DrawColor.RED;
			} else if ("Yellow".equals((String) colorCombox.getSelectedItem())) {
				return DrawColor.YELLOW;
			} else if ("Green".equals((String) colorCombox.getSelectedItem())) {
				return DrawColor.GREEN;
			} else if ("Eraser".equals((String) colorCombox.getSelectedItem())) {
				return DrawColor.WHITE;
			}else {
				return DrawColor.WHITE;
			}
		}
		
		private int selectSize() {
			if (sizeCombox.getSelectedItem()=="1") {
				return 1;
			}else if (sizeCombox.getSelectedItem()=="2") {
				return 2;
			} else if (sizeCombox.getSelectedItem()=="3") {
				return 3;
			}else if (sizeCombox.getSelectedItem()=="4") {
				return 4;
			}else if (sizeCombox.getSelectedItem()=="5") {
				return 5;
			}else if (sizeCombox.getSelectedItem()=="6") {
				return 6;
			}else if (sizeCombox.getSelectedItem()=="7") {
				return 7;
			}else if (sizeCombox.getSelectedItem()=="8") {
				return 8;
			}else if (sizeCombox.getSelectedItem()=="9") {
				return 9;
			}else if (sizeCombox.getSelectedItem()=="10") {
				return 10;
			}else{
				return 4;
			}
		}
		
		public void drawColorSize(double x, double y, DrawColor color, int size){
			switch(drawPanelModel.getColor()){
			case BLACK :{
				graphics2d.setColor(Color.BLACK);
				break;
			}
			case RED :{
				graphics2d.setColor(Color.RED);
				break;
			}
			case YELLOW :{
				graphics2d.setColor(Color.YELLOW);
				break;
			}
			case GREEN :{
				graphics2d.setColor(Color.GREEN);
				break;
			}
			case WHITE :{
				graphics2d.setColor(Color.WHITE);
				break;
			}
			}
			graphics2d.fillRect((int)x, (int)y, size, size);
			repaint((int)x, (int)y, size, size);
		}
		
		private void clearcanvasPanel() {		
			image = null;
			repaint();
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if (image == null) {
				image = createImage(getWidth(), getHeight());
				graphics2d = (Graphics2D) image.getGraphics();
				graphics2d.setColor(Color.WHITE);
				graphics2d.fillRect(0, 0, getWidth(), getHeight());
			}
			Rectangle r = g.getClipBounds();
			g.drawImage(image, r.x, r.y, r.width + r.x, r.y + r.height, r.x,
					r.y, r.width + r.x, r.y + r.height, null);
		}
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == clearButton) {
				drawPanelModel.clearPanel();
				}
			}
	}

}
