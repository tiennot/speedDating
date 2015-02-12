package view;

import java.util.Random;

import controller.Controller;
import controller.Satisfaction;
import model.constants.Interest;
import model.constants.Interest;
import model.constants.Sex;
import processing.core.PApplet;
import processing.core.PImage;
import view.visualizations.VGraph;
import view.visualizations.VInterests;


/*
 * This class represents the "home" window, i.e. what the user
 * sees first. It is a kind of time line going through the speed
 * dating night. When clicking on the different elements, the
 * MainWindow object can instantiate DetailWindow object to display
 * more detailed visualizations.
 */
public class MainWindow {
	private PApplet p;
	private Controller controller;
	private int width, height;
	PImage background, blurBackground;
	DetailWindow activeDetailWindow = null;
	boolean mousePressedHandled = false;
	boolean updatingData = false;
	
	//The ages for Tom and Katy
	int ageTomValue, ageKatyValue;
	
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
	
	//The interest Window
	XywhObject interestsTrigger;
	DetailWindow interestsWindow;
	VInterests interestsVisualization;
	
	//The halfway window
	XywhObject halfwayTrigger;
	DetailWindow halfwayWindow;
	VGraph halfwayVisualization;
		
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
			this.activeDetailWindow.draw();
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
			//Sets the age of Katy
			this.ageKatyValue = ageKaty.getValueForMousePos();
			ageKaty.setValue(this.ageKatyValue);
			//this.randomizedData();
			//Calls for update
			this.updateData();
		}
		else if(p.mousePressed && ageTom.over()){
			//Sets the age of Tom
			this.ageTomValue = ageTom.getValueForMousePos();
			ageTom.setValue(this.ageTomValue);
			this.randomizedData();
			//Calls for update
			this.updateData();
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
		}else if(p.mousePressed && this.halfwayTrigger.over()){
			//"Opens" the interest detail window
			setActiveDetailWindow(halfwayWindow);
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
	    interestTom = new InterestIcon(p, 0, 60, Interest.TVSPORTS, Sex.MALE);
	    interestKaty = new InterestIcon(p, 359, 24, Interest.MOVIES, Sex.FEMALE);
	    
	    //Smileys
	    halfwayKatySmiley = new Smiley(p, 352, 436, PINK, Satisfaction.Tres_satisfait);
	    halfwayTomSmiley = new Smiley(p, 212, 436, BLUE, Satisfaction.Peu_Satisfait);
	    
	    //Pie charts
	    endPieLabel = new TextLabel(p, 775, 400, 170, 20, 14, p.color(150,150,150), p.CENTER, "Chances of match");
	    endPieChartKaty = new PieChart(p, 805, 422, 50, PINK);
	    endPieChartTom = new PieChart(p, 865, 422, 50, BLUE);
	    
	    //The interest detail window
	    interestsWindow = new DetailWindow(p, this, width-100, height-100, "Interests by gender");
	    interestsTrigger = new XywhObject(p, 23, 29, 474, 128);
	    interestsVisualization = new VInterests(p, interestsWindow, controller);
	    interestsWindow.setDescriptionText("A dot represents the number of people who rated one of the 17 interest with a given mark (from 1 to 10 from center to edges on a scale of 10). We can see that men are more likely to express interest in things such as sport, TV and concert while women are strongly attracted by diner and art. It’s interesting to see not only the differences between the two opposite sexes but also the repartition of interests among people generally speaking. Also keep in mind that people don’t necessarily tell the truth.");
	    
	    //The halfway detail window
	    halfwayWindow = new DetailWindow(p, this, width-100, height-100, "Match \"graph\" display");
	    halfwayTrigger = new XywhObject(p,200, 404, 167, 70);
	    halfwayVisualization = new VGraph(p, halfwayWindow, controller);
	    halfwayWindow.setDescriptionText("The key of understanding the speed dating dataset is matches. Here we try to help you understand how matches work according to gender and age by displaying them in a \"graph\" style. Each line is an age, pink dots are women and blue dots men. Hover a dot and you'll see who this person matches with - thick line - and who he/she half matched. Click a gray cicle on the left to activate age filtering.");
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
	
	
	/*
	 * This methods updates the data when age is changed by calling the controller, etc.
	 */
	public void updateData(){
		if(updatingData) return;
		updatingData = true;
		//Set the age for Tom in the controller
		this.controller.setAge(this.ageTomValue);	
		this.interestTom.setInterest(
				DataBuffer.isInterestBufferized(this.ageTomValue, Sex.MALE) ?
						DataBuffer.Interest(ageTomValue,  Sex.MALE)
						: this.controller.getPreferredTaste(Sex.MALE).get(0));
		//Sets the first smiley for tom
		halfwayTomSmiley.setSatisfaction(controller.getSatisfactionOverall(Sex.MALE));
		
		//Set the age for Katy in the controller
		this.controller.setAge(this.ageKatyValue);
		this.interestKaty.setInterest(
				DataBuffer.isInterestBufferized(this.ageKatyValue, Sex.FEMALE) ?
						DataBuffer.Interest(ageKatyValue,  Sex.FEMALE)
						: this.controller.getPreferredTaste(Sex.FEMALE).get(0));
		//Sets the first smiley for Katy
		halfwayKatySmiley.setSatisfaction(controller.getSatisfactionOverall(Sex.FEMALE));
		//We are done
		updatingData = false;
	}
	
	//For debug purposes only
	public void randomizedData(){
		Random rand = new Random();
		//this.interestKaty.setInterest(new Interest(rand.nextInt(16)));
		//this.interestTom.setInterest(new Interest(rand.nextInt(16)));
		//this.halfwayKatySmiley.setHumor((byte) (rand.nextInt((3 - 0) + 1)+1));
		//this.halfwayTomSmiley.setHumor((byte) (rand.nextInt((3 - 0) + 1)+1));
		this.endPieChartKaty.setValue(rand.nextFloat());
		this.endPieChartTom.setValue(rand.nextFloat());
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	//Just tells if the mouse is over a circle (x, y, radius)
	public static boolean overCircle(PApplet p, int x, int y, int radius){
		return (p.mouseX-x)*(p.mouseX-x)+(p.mouseY-y)*(p.mouseY-y) <= radius*radius;
	}
}
