package lightpaint;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;
import javax.swing.*;

public class LightPaint {

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();

	private JButton undo = new JButton("Undo"); //top buttons
	private JButton redo = new JButton("Redo");
	private JButton clear = new JButton("Clear");
	private JButton line = new JButton("Line");
	private JButton curve = new JButton("Curve");
	private JButton blue = new JButton("Blue");
	private JButton green = new JButton("green");
	private JButton yellow = new JButton("yellow");
	private JButton orange = new JButton("orange");
	private JButton red = new JButton("red");
	private JButton cyan = new JButton("cyan");
	private JButton magenta = new JButton("magenta");
	private JButton pink = new JButton("pink");
	private JButton gray = new JButton("gray");
	private JButton black = new JButton("black");

	private String[] thickness = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private JComboBox<String> thicknessSelection = new JComboBox(thickness);

	private int width = 800;
	private int height = 600;

	private MyCanvas canvas = new MyCanvas();

	public LightPaint() {

	}

	public static void main(String[] args) {

	}

	public void formatSetup() {
    	frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    	width = panel.getWidth();
    	height = panel.getHeight();
    	panel.setLayout(new GridBagLayout());
    	panel.setSize(width, height);
    	panel.setBackground(Color.WHITE);
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridwidth = 1;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.1;
    	gbc.weighty = 1;
    	gbc.anchor = GridBagConstraints.NORTHWEST;
    	gbc.insets = new Insets(5, 5, 5, 5);

    	//undo button
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	panel.add(undo, gbc);

    	//clear button
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	panel.add(clear, gbc);

    	//redo button
    	gbc.gridx = 0;
    	gbc.gridy = 2;
    	panel.add(redo, gbc);

    	//thickness selection combobox
    	gbc.gridx = 0;
    	gbc.gridy = 3;
    	thicknessSelection.setSelectedIndex(0);
    	panel.add(thicknessSelection, gbc);

    	//draw line
    	gbc.gridx = 0;
    	gbc.gridy = 4;
    	panel.add(line, gbc);

    	//draw curve
    	gbc.gridx = 0;
    	gbc.gridy = 5;
    	panel.add(curve, gbc);

    	//blue button
    	gbc.gridx = 0;
    	gbc.gridy = 6;
    	blue.setBackground(Color.BLUE);
    	panel.add(blue, gbc);

    	//green button
    	gbc.gridx = 0;
    	gbc.gridy = 7;
    	green.setBackground(Color.GREEN);
    	panel.add(green, gbc);

    	//yellow button
    	gbc.gridx = 0;
    	gbc.gridy = 8;
    	yellow.setBackground(Color.YELLOW);
    	panel.add(yellow, gbc);

    	//orange button
    	gbc.gridx = 0;
    	gbc.gridy = 9;
    	orange.setBackground(Color.ORANGE);
    	panel.add(orange, gbc);

    	//red button
    	gbc.gridx = 0;
    	gbc.gridy = 10;
    	red.setBackground(Color.RED);
    	panel.add(red, gbc);

    	//purple button
    	gbc.gridx = 0;
    	gbc.gridy = 11;
    	cyan.setBackground(Color.CYAN);
    	panel.add(cyan, gbc);

    	//magenta button
    	gbc.gridx = 0;
    	gbc.gridy = 12;
    	magenta.setBackground(Color.MAGENTA);
    	panel.add(magenta, gbc);

    	//pink button
    	gbc.gridx = 0;
    	gbc.gridy = 13;
    	pink.setBackground(Color.PINK);
    	panel.add(pink, gbc);

    	//gray button
    	gbc.gridx = 0;
    	gbc.gridy = 14;
    	gray.setBackground(Color.GRAY);
    	panel.add(gray, gbc);

    	//black button
    	gbc.gridx = 0;
    	gbc.gridy = 15;
    	black.setBackground(Color.BLACK);
    	panel.add(black, gbc);

    	//adds canvas
    	gbc.gridwidth = 9;
    	gbc.gridheight = 16;
    	gbc.gridx = 1;
    	gbc.gridy = 0;
    	panel.add(canvas, gbc);

    	frame.setResizable(false);
    	//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    	frame.add(panel);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.pack();
    	frame.setVisible(true);

    	undo.addActionListener((ActionListener) new undoAction());
    	clear.addActionListener((ActionListener) new clearCanvas());
    	redo.addActionListener((ActionListener) new redoAction());
    	thicknessSelection.addActionListener((ActionListener) new adjustThickness());
    	line.addActionListener((ActionListener) new drawLine());
    	curve.addActionListener((ActionListener) new drawCurve());
    	blue.addActionListener((ActionListener) new blueColor());
    	green.addActionListener((ActionListener) new greenColor());
    	yellow.addActionListener((ActionListener) new yellowColor());
    	orange.addActionListener((ActionListener) new orangeColor());
    	red.addActionListener((ActionListener) new redColor());
    	cyan.addActionListener((ActionListener) new cyanColor());
    	magenta.addActionListener((ActionListener) new magentaColor());
    	pink.addActionListener((ActionListener) new pinkColor());
    	gray.addActionListener((ActionListener) new grayColor());
    	black.addActionListener((ActionListener) new blackColor());
	}

	private class undoAction implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.remove();
        	canvas.undoPressed = true;
        	canvas.repaint();
    	}
	}

	private class clearCanvas implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.clear = true;
        	canvas.undoPressed = false;
        	canvas.repaint();
    	}
	}

	private class redoAction implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	if (canvas.redoStack.size() > 0) { //must have elements to run
            	canvas.gi.add(canvas.redoStack.pop());
            	canvas.repaint();
        	}
    	}
	}

	private class adjustThickness implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	if (e.getSource() == thicknessSelection) {
            	JComboBox cb = (JComboBox) e.getSource();
            	String selectedThickness = (String) cb.getSelectedItem();
            	switch (selectedThickness) {
                	case "1":
                    	canvas.thickness = 1;
                    	break;
                	case "2":
                    	canvas.thickness = 2;
                    	break;
                	case "3":
                    	canvas.thickness = 3;
                    	break;
                	case "4":
                    	canvas.thickness = 4;
                    	break;
                	case "5":
                    	canvas.thickness = 5;
                    	break;
                	case "6":
                    	canvas.thickness = 6;
                    	break;
                	case "7":
                    	canvas.thickness = 7;
                    	break;
                	case "8":
                    	canvas.thickness = 8;
                    	break;
                	case "9":
                    	canvas.thickness = 9;
                    	break;
                	case "10":
                    	canvas.thickness = 10;
                    	break;
            	}
        	}
    	}
	}

	private class drawLine implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.isLine = true;
    	}
	}

	private class drawCurve implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.isLine = false;
    	}
	}

	private class blueColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.color = Color.BLUE;
    	}
	}

	private class greenColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.color = Color.GREEN;
    	}
	}

	private class yellowColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.color = Color.YELLOW;
    	}
	}

	private class orangeColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.color = Color.ORANGE;
    	}
	}

	private class redColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.color = Color.RED;
    	}
	}

	private class cyanColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.color = Color.CYAN;
    	}
	}

	private class magentaColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.color = Color.MAGENTA;
    	}
	}

	private class pinkColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.color = Color.PINK;
    	}
	}

	private class grayColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.color = Color.GRAY;
    	}
	}

	private class blackColor implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
        	canvas.setColor1(Color.BLACK);
    	}
	}

	private class MyCanvas extends Canvas implements MouseListener, MouseMotionListener {

    	private Color color;
    	private boolean clear = false;
    	private boolean undoPressed = false;
    	private boolean isLine = true;
    	private int prevX = -1;
    	private int prevY = -1;
    	private int x = -1;
    	private int y = -1;
    	private int thickness = 1;
    	private ArrayList<Point> curvePoints = new ArrayList();

    	private ArrayList<GraphicsInformation> gi = new ArrayList();
    	private Stack<GraphicsInformation> redoStack = new Stack();

    	public MyCanvas() {
        	setSize(width, height);
        	setVisible(true);
        	this.addMouseListener(this);
        	this.addMouseMotionListener(this);
    	}

    	public void setColor1(Color newColor) {
        	color = newColor;
    	}

    	public void remove() {
        	if (gi.size() > 0) {
            	GraphicsInformation temp = gi.get(gi.size() - 1);
            	redoStack.push(temp);
            	gi.remove(gi.size() - 1);
        	}
    	}

    	public void paint(Graphics g) {
        	Graphics2D g2 = (Graphics2D) g;
        	if (undoPressed == false) { //clears redo stack if a line is drawn
            	Iterator<GraphicsInformation> itr = redoStack.iterator();
            	while (itr.hasNext()) {
                	itr.next();
                	itr.remove();
            	}
        	}
        	if (clear == false) {
            	for (int i = 0; i < gi.size(); i++) {
                	g.setColor(gi.get(i).getColor());
                	g2.setStroke(new BasicStroke(gi.get(i).getThickness()));
                	if (gi.get(i).getIsLine() == true) { //paints line
                    	g2.draw(new Line2D.Float(gi.get(i).getPrevX(), gi.get(i).getPrevY(), gi.get(i).getX(), gi.get(i).getY()));
                	} else { //paints freehand
                    	for (int j = 0; j < gi.get(i).getCurvePoints().size() - 2; j++) {
                        	Point p1 = gi.get(i).getCurvePoints().get(j);
                        	Point p2 = gi.get(i).getCurvePoints().get(j + 1);
                        	g2.draw(new Line2D.Float((float) p1.getX(), (float) p1.getY(), (float) p2.getX(), (float) p2.getY()));
                    	}
                	}
            	}
        	}
        	//clear class curvePoints after freehand line drawn
        	Iterator<Point> itr = curvePoints.iterator();
        	while (itr.hasNext()) {
            	itr.next();
            	itr.remove();
        	}
        	if (clear == true) { //clears board
            	Iterator<GraphicsInformation> graphicsDelete = gi.iterator();
            	while (graphicsDelete.hasNext()) {
                	graphicsDelete.next();
                	graphicsDelete.remove();
            	}
            	clear = false;
        	}
    	}

    	@Override
    	public void mouseClicked(MouseEvent e) {
    	}

    	@Override
    	public void mousePressed(MouseEvent e) {
        	prevX = e.getX();
        	prevY = e.getY();
    	}

    	@Override
    	public void mouseReleased(MouseEvent e) {
        	x = e.getX();
        	y = e.getY();
        	GraphicsInformation newGI = new GraphicsInformation(prevX, prevY, x, y, color, thickness, curvePoints, isLine);
        	gi.add(newGI);
        	undoPressed = false;
        	repaint();
    	}

    	@Override
    	public void mouseEntered(MouseEvent e) {
    	}

    	@Override
    	public void mouseExited(MouseEvent e) {
    	}

    	@Override
    	public void mouseDragged(MouseEvent e) {
        	curvePoints.add(new Point(e.getX(), e.getY()));
    	}

    	@Override
    	public void mouseMoved(MouseEvent e) {
    	}
	}
}



