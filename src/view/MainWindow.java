package view;

import processing.core.PApplet;
import processing.core.PImage;


/*
 * This class represents the "home" window, i.e. what the user
 * sees first. It is a kind of time line going through the speed
 * dating night. When clicking on the different elements, the
 * MainWindow object can instantiate DetailWindow object to display
 * more detailed visualizations.
 */
public class MainWindow {
	private PApplet p;
	private int width, height;
	PImage background, blurBackground;
	boolean hasActiveDetailWindow;
	
	//The two cursors for ages
	private Cursor ageKaty;
	private Cursor ageTom;
	
	//The labels for Tom and Katy
	private TextLabel labelKaty;
	private TextLabel labelTom;
	
	//Smileys
	Smiley halfwayKatySmiley;
	Smiley halfwayTomSmiley;
	
	//The detail Windows
	DetailWindow interestsWindow;
		
	//Our two main colors
	public static int PINK;
	public static int BLUE;
	
	public MainWindow(PApplet p){
		this.p = p;
	}
	
	
	/*
	 * Called to refresh display
	 */
	public void draw(){
		if(p.mousePressed || this.hasActiveDetailWindow){
			p.image(blurBackground, 0, 0);
			interestsWindow.draw();
		}else{
			drawMain();
		}
	}
	
	
	/*
	 * drawMain() is called when no detail window is active
	 */
	public void drawMain(){
		//Draws background and title
		p.image(background, 0, 0);
		
		//Draws cursors
		this.ageKaty.draw();
		this.ageTom.draw();
		
		//Interaction with the age cursors
		if(p.mousePressed && ageKaty.over()){
			ageKaty.setValue(ageKaty.getValueForMousePos());
		}
		else if(p.mousePressed && ageTom.over()){
			ageTom.setValue(ageTom.getValueForMousePos());
		}
		
		//Draws Tom & Katy's labels
		labelTom.draw("Tom, "+ageTom.getValue());
		labelKaty.draw("Katy, "+ageKaty.getValue());
		
		//Draws smileys
		halfwayKatySmiley.draw();
		if(halfwayKatySmiley.over()){
			System.out.println(p.mouseX);
		}
		halfwayTomSmiley.draw();
	}
	
	public void setup(){
		//Sets up height and width (16/9 ratio)
		width = 960;
		height = 540;
		
		//Basic layout
		p.size(width, height);
	    p.background(255,255,255);
	    
	    //Loads background & title
	    background = p.loadImage("background.png");
	    blurBackground = p.loadImage("blurBackground.png");
	    
	    //Initializes men and woman colors
	    PINK = p.color(255, 0, 110);
	    BLUE = p.color(41, 83, 159);
	    
	    //Initializes cursors for ages
	    ageKaty = new Cursor(p, 140, p.height-20, 100, PINK);
	    ageTom = new Cursor(p, 20, p.height-20, 100, BLUE);
	    ageKaty.setValue(24);
	    ageTom.setValue(30);
	    
	    //Initializes age labels
	    labelKaty = new TextLabel(p, 320, 50, 16, PINK, p.RIGHT);
	    labelTom = new TextLabel(p, 205, 80, 16, BLUE, p.LEFT);
	    
	    //Smileys
	    halfwayKatySmiley = new Smiley(p, 352, 436, PINK, Smiley.HAPPY);
	    halfwayTomSmiley = new Smiley(p, 212, 436, BLUE, Smiley.SAD);
	    
	    //The detail windows
	    interestsWindow = new DetailWindow(p, this, width-150, height-150);
	}
}
