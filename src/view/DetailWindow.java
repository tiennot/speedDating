package view;

import processing.core.PApplet;
import processing.core.PImage;

/*
 * This class represents a window for a specific visualization, which should be
 * an attribute of a MainWindow object. The DetailWindow is displayed above the 
 * MainWindow and contains items just as the MainWIndow do.
 */
public class DetailWindow {
	PApplet p;
	MainWindow parent;
	int width, height;
	int x, y; //For convenience
	//Items
	TextLabel backLabel;
	PImage backImg;
	
	
	public DetailWindow(PApplet p, MainWindow parent,  int w, int h){
		this.p = p;
		this.parent = parent;
		this.width = w;
		this.height = h;
		x = (p.width-width)/2;
		y = (p.height-height)/2;
		backLabel = new TextLabel(p, x+48, y+20, 16, p.color(100,100,100), p.LEFT, p.TOP, "Back to overview");
		backImg = p.loadImage("back.png");
	}
	
	public void draw(){
		//Draws the rectangle shape of the window
		p.stroke(p.color(150,150,150));
		p.strokeWeight(1);
		p.fill(p.color(255,255,255));
		p.rect(x, x, width, height);
		//Draws items
		backLabel.draw();
		p.image(backImg, x+15, y+19);
	}

}
