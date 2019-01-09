package lightpaint;

import java.awt.*;
import java.util.*;

public class GraphicsInformation {

	private int prevX;
	private int prevY;
	private int x;
	private int y;
	private int thickness;
	private Color color;
	private boolean isLine;
	private ArrayList<Point> curvePoints = new ArrayList();

	public static void main(String[] args) {

	}

	public GraphicsInformation(int prevX, int prevY, int x, int y, Color color, int thickness, ArrayList<Point> curvePoints, boolean isLine) {
    	this.prevX = prevX;
    	this.prevY = prevY;
    	this.x = x;
    	this.y = y;
    	this.color = color;
    	this.thickness = thickness;
    	for(Point p: curvePoints){
        	this.curvePoints.add(p);
    	}
    	this.isLine = isLine;
	}

	public int getPrevX() {
    	return prevX;
	}

	public void setPrevX(int prevX) {
    	this.prevX = prevX;
	}

	public int getPrevY() {
    	return prevY;
	}

	public void setPrevY(int prevY) {
    	this.prevY = prevY;
	}

	public int getX() {
    	return x;
	}

	public void setX(int x) {
    	this.x = x;
	}

	public int getY() {
    	return y;
	}

	public void setY(int y) {
    	this.y = y;
	}

	public Color getColor() {
    	return color;
	}

	public void setColor(Color color) {
    	this.color = color;
	}

	public int getThickness() {
    	return thickness;
	}

	public void setThickness(int thickness) {
    	this.thickness = thickness;
	}

	public ArrayList<Point> getCurvePoints() {
    	return curvePoints;
	}

	public void setCurvePoints(ArrayList<Point> curvePoints) {
    	this.curvePoints = curvePoints;
	}

	public boolean getIsLine() {
    	return isLine;
	}

	public void setIsLine(boolean isLine) {
    	this.isLine = isLine;
	}
}



