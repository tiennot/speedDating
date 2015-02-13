package view;

import processing.core.PApplet;

public class Cursor {
	PApplet p;
	//Drawing info
	private int height = 20;
	private int width;
	private int x;
	private int y;
	private int color;
	//Current state
	private int max = 38;
	private int min = 18;
	private int value = 25;
	
	public Cursor(PApplet p, int x, int y, int w, int color){
		this.p = p;
		this.x = x;
		this.y = y;
		this.width = w;
		this.color = color;
	}
	
	public void draw(){
		//Set the right color to draw
		p.fill(color);
		p.noStroke();
		//Draws the main line of the scroll bar
		p.rect(x, y-2, width, 4);
		//Draws the cursor
		p.rect(x+((value-min)*width/(max-min))-1, y-height/2, 6, height);
		//Draws a label with the value
		p.textAlign(p.CENTER, p.BOTTOM);
		p.textSize(12);
		p.text(value, x+((value-min)*width/(max-min))-1 + 3/2, y-height/2);
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public boolean over(){
		return p.mouseX>x && p.mouseX<x+width && p.mouseY>y-height/2 && p.mouseY<y+height/2;
	}
	
	public int getValueForMousePos(){
		return min+(max-min)*(p.mouseX-x)/width;
	}
}
