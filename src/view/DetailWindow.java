package view;

import processing.core.PApplet;
import processing.core.PImage;

/*
 * This class represents a window for a specific visualization, which should be
 * an attribute of a MainWindow object. The DetailWindow is displayed above the 
 * MainWindow and contains items just as the MainWIndow do.
 */
public class DetailWindow extends XywhObject{
	MainWindow parent;
	//Back button
	TextLabel backLabel;
	PImage backImg;
	XywhObject backTracker;
	//Left title
	TextLabel leftTitle;
	//Description
	TextLabel description;
	
	//Object for the visualization itself
	XywhObject visualization;
	
	//Constructor
	public DetailWindow(PApplet p, MainWindow parent,  int w, int h, String title){
		//Calls to parent constructor
		super(p, (p.width-w)/2, (p.height-h)/2, w, h);
		this.parent = parent;
		//Initialize stuff for the back button
		backLabel = new TextLabel(p, x+48, y+20, 200, 20, 16, p.color(100,100,100), "Back to overview");
		backImg = p.loadImage("back.png");
		backTracker = new XywhObject(p,x+0,y+5,200,50);
		//Stuff for the left title
		leftTitle = new TextLabel(p, x+15, y+55, 250, 30, 22, parent.BLUE, p.LEFT, p.CENTER, title);
		//Stuff for description
		description = new TextLabel(p, x+15, y+100, 250, h-120, 12, p.color(0,0,0));
		//Creates the new visualization
		visualization = new XywhObject(p, x+300, y+15, w-300-15, h-15-15);
	}
	
	public void draw(){
		//Draws the rectangle shape of the window
		p.stroke(p.color(150,150,150));
		p.strokeWeight(1);
		p.fill(p.color(255,255,255));
		p.rect(x, y, w, h);
		
		//Draws back button
		backLabel.draw();
		p.image(backImg, x+15, y+19);
		
		//Draw left title
		leftTitle.draw();
		
		//Draw description
		description.draw("A dot represent the number of people who rated one of the 17 interest with a given mark (from 1 to 10 from center to edges on a scale of 10). We can see that men are more likely to express interest in things such as sport, TV and concert while women are strongly attracted by diner and art. It’s interesting to see not only the differences between the two opposite sexes but also the repartition of interests among people generally speaking. Also keep in mind that people don’t necessarily tell the truth.");
		
		//Draws separator line
		p.stroke(p.color(150,150,150));
		p.line(x+285, y+30, x+285, y+h-30);
		visualization.drawRect();
		//Handles window exit
		if(p.mousePressed && (backTracker.over() || !this.over()) ){
			//Goes back to parent
			parent.setActiveDetailWindow(null);
			parent.setMousePressedHandled(true);
		}
	}

}
