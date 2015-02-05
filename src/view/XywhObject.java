package view;

import processing.core.PApplet;

/*
 * This class provides basics methods for an object that is characterized
 * by its x & y positions, its width and its height
 */
public class XywhObject {
	protected PApplet p;
	protected int x, y, h, w;
	
	//Constructor
	public XywhObject(PApplet p, int x, int y, int w, int h){
		this.p = p;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	//Simple method to tell if the mouse is over the object
	public boolean over(){
		return p.mouseX>=x && p.mouseX<=x+w && p.mouseY>=y && p.mouseY<=y+h;
	}
	
	//For debug purposes only
	public void drawRect(){
		p.noStroke();
		p.fill(p.color(222,40,57,125));
		p.rect(x, y, w, h);
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
