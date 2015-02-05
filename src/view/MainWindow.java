package view;

import java.util.Random;

import model.constants.Interests;
import model.constants.Sex;
import processing.core.PApplet;
import processing.core.PImage;
import visualizations.VInterests;


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
	DetailWindow activeDetailWindow = null;
	boolean mousePressedHandled = false;
	
	//The two cursors for ages
	private Cursor ageKaty;
	private Cursor ageTom;
	
	//The labels for Tom and Katy
	private TextLabel labelKaty;
	private TextLabel labelTom;
	
	//Interests icons for Tom and Katy
	private InterestIcon interestTom;
	private InterestIcon interestKaty;
	
	//Smileys
	Smiley halfwayKatySmiley;
	Smiley halfwayTomSmiley;
	
	//Pie charts
	TextLabel endPieLabel;
	PieChart endPieChartTom;
	PieChart endPieChartKaty;
	
	//The detail Windows
	XywhObject interestsTrigger;
	DetailWindow interestsWindow;
	VInterests interestsVisualization;
		
	//Our two main colors
	public static int PINK;
	public static int BLUE;
	
	/*
	 * Pretty simple constructor
	 */
	public MainWindow(PApplet p){
		this.p = p;
	}
	
	/*
	 * Easy method that tells if we are currently in a detail window
	 */
	public boolean hasActiveDetailWindow(){
		return this.activeDetailWindow != null;
	}
	
	
	/*
	 * Called to refresh display
	 */
	public void draw(){
		if(this.mousePressedHandled && !p.mousePressed){
			this.mousePressedHandled = false;
		}
		if(this.hasActiveDetailWindow()){
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
			this.randomizedData();
		}
		else if(p.mousePressed && ageTom.over()){
			ageTom.setValue(ageTom.getValueForMousePos());
			this.randomizedData();
		}
		
		//Draws Tom & Katy's labels
		labelTom.draw("Tom, "+ageTom.getValue());
		labelKaty.draw("Katy, "+ageKaty.getValue());
		
		//Draws Tom & Katy's interests
		interestTom.draw();
		interestKaty.draw();
		
		//Draws smileys
		halfwayKatySmiley.draw();
		halfwayTomSmiley.draw();
		
		//Draws pie charts
		endPieLabel.draw();
		endPieChartKaty.draw();
		endPieChartTom.draw();

		//Handles trigger zones for detail windows
		if(p.mousePressed && this.interestsTrigger.over()){
			//"Opens" the interest detail window
			setActiveDetailWindow(interestsWindow);
			setMousePressedHandled(true);
		}
	}
	
	/*
	 * Should be called from PApplet p setup method
	 */
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
	    labelKaty = new TextLabel(p, 220, 30, 100, 30, 16, PINK, p.RIGHT, p.CENTER);
	    labelTom = new TextLabel(p, 205, 60, 100, 30, 16, BLUE, p.LEFT, p.CENTER);
	    
	    //Initializes interests icons
	    interestTom = new InterestIcon(p, 55, 96, Interests.TVSPORTS, Sex.MALE);
	    interestKaty = new InterestIcon(p, 417, 42, Interests.MOVIES, Sex.FEMALE);
	    
	    //Smileys
	    halfwayKatySmiley = new Smiley(p, 352, 436, PINK, Smiley.HAPPY);
	    halfwayTomSmiley = new Smiley(p, 212, 436, BLUE, Smiley.SAD);
	    
	    //Pie charts
	    endPieLabel = new TextLabel(p, 775, 400, 170, 20, 14, p.color(150,150,150), p.CENTER, "Chances of match");
	    endPieChartKaty = new PieChart(p, 805, 422, 50, PINK);
	    endPieChartTom = new PieChart(p, 865, 422, 50, BLUE);
	    
	    //The interest detail window
	    interestsWindow = new DetailWindow(p, this, width-100, height-100, "Interests by gender");
	    interestsTrigger = new XywhObject(p, 140, 20, 220, 100);
	    interestsVisualization = new VInterests(p, interestsWindow);
	}

	public DetailWindow getActiveDetailWindow() {
		return activeDetailWindow;
	}

	public void setActiveDetailWindow(DetailWindow activeDetailWindow) {
		if(!this.mousePressedHandled){
			this.activeDetailWindow = activeDetailWindow;
		}
	}

	public boolean isMousePressedHandled() {
		return mousePressedHandled;
	}

	public void setMousePressedHandled(boolean mousePressedHandled) {
		this.mousePressedHandled = mousePressedHandled;
	}
	
	//For debug purposes only
	public void randomizedData(){
		Random rand = new Random();
		this.interestKaty.setInterest((byte) (rand.nextInt((16 - 0) + 1)));
		this.interestTom.setInterest((byte) (rand.nextInt((16 - 0) + 1)));
		this.halfwayKatySmiley.setHumor((byte) (rand.nextInt((3 - 0) + 1)+1));
		this.halfwayTomSmiley.setHumor((byte) (rand.nextInt((3 - 0) + 1)+1));
		this.endPieChartKaty.setValue(rand.nextFloat());
		this.endPieChartTom.setValue(rand.nextFloat());
	}
}
