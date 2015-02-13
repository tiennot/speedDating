package view;

import java.util.Random;

import controller.Controller;
import controller.Satisfaction;
import model.constants.Interest;
import model.constants.Interest;
import model.constants.Sex;
import processing.core.PApplet;
import processing.core.PImage;
import view.visualizations.VBarchart;
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
	XywhObject graphTrigger;
	DetailWindow graphWindow;
	VGraph graphVisualization;
	
	//The barchart window
	XywhObject barchartTrigger;
	DetailWindow barchartWindow;
	VBarchart barchartVisualization;
	
	//The label for percentage of real dates
	TextLabel realDateKatyLabel;
	TextLabel realDateTomLabel;
		
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
			//this.randomizedData();
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
		
		//Draws label for real dates percentage
		realDateKatyLabel.draw();
		realDateTomLabel.draw();

		//Handles trigger zones for detail windows
		if(p.mousePressed && this.interestsTrigger.over()){
			//"Opens" the interest detail window
			setActiveDetailWindow(interestsWindow);
			setMousePressedHandled(true);
		}else if(p.mousePressed && this.graphTrigger.over()){
			//"Opens" the interest detail window
			setActiveDetailWindow(graphWindow);
			setMousePressedHandled(true);
		}else if(p.mousePressed && this.barchartTrigger.over()){
			//"Opens" the bar chart detail window
			setActiveDetailWindow(barchartWindow);
			setMousePressedHandled(true);
		}
	}
	
	/*
	 * Should be called from PApplet p setup method
	 */
	public void setup(){
		p.frameRate(60);
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
	    interestTom = new InterestIcon(p, 0, 60, Interest.MUSEUMS, Sex.MALE);
	    interestKaty = new InterestIcon(p, 359, 24, Interest.CONCERTS, Sex.FEMALE);
	    
	    //Smileys
	    halfwayKatySmiley = new Smiley(p, 352, 436, PINK, Satisfaction.Tres_satisfait);
	    halfwayTomSmiley = new Smiley(p, 212, 436, BLUE, Satisfaction.Peu_Satisfait);
	    
	    //Pie charts
	    endPieLabel = new TextLabel(p, 775, 400, 170, 20, 14, p.color(150,150,150), p.CENTER, "Chances of match");
	    endPieChartKaty = new PieChart(p, 805, 422, 50, PINK);
	    endPieChartTom = new PieChart(p, 865, 422, 50, BLUE);
	    
	    //The label for real dates
	    realDateKatyLabel = new TextLabel(p, 890, 170, 50, 20, 14, MainWindow.PINK, p.LEFT);
	    realDateTomLabel = new TextLabel(p, 850, 170, 50, 20, 14, MainWindow.BLUE, p.LEFT);
	    
	    //The interest detail window
	    interestsWindow = new DetailWindow(p, this, width-100, height-100, "Interests by gender");
	    interestsTrigger = new XywhObject(p, 23, 29, 474, 128);
	    interestsVisualization = new VInterests(p, interestsWindow, controller);
	    interestsWindow.setDescriptionText("A dot represents the number of people who rated one of the 17 interest with a given mark (from 1 to 10 from center to edges on a scale of 10). We can see that men are more likely to express interest in things such as sport, TV and concert while women are strongly attracted by diner and art. It’s interesting to see not only the differences between the two opposite sexes but also the repartition of interests among people generally speaking. Also keep in mind that people don’t necessarily tell the truth.");
	    
	    //The match graph detail window
	    graphWindow = new DetailWindow(p, this, width-100, height-100, "Match \"graph\" display");
	    graphTrigger = new XywhObject(p,790, 400, 140, 100);
	    graphVisualization = new VGraph(p, graphWindow, controller);
	    graphWindow.setDescriptionText("The key of understanding the speed dating dataset is matches. Here we display matches in a \"graph\" style. Each line is an age, pink dots are women and blue dots men. Thick black lines represent a match between a man and a woman, blue lines represent a man says \"yes\" and the woman says \"no\" and a pink line a man says \"no\" and the woman says \"yes\". Use the ages values on the left to age-filter (several ages using ctrl key) and the filters at the bottom to display only matches of woman/men this age and only matche/half-matches. Hover a person to see his/her matches.");
	
	    //The barchart detail window
	    barchartWindow = new DetailWindow(p, this, width-100, height-100, "Bar Chart display");
	    barchartTrigger = new XywhObject(p,630,390, 200,70);
	    barchartVisualization = new VBarchart(p, barchartWindow, controller);
	    barchartWindow.setDescriptionText("//TODO");
	
	    //At the end of the setup we "update" data for the first time
	    this.updateData();
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
		//Set the match percentage for tom
		endPieChartTom.setValue(controller.getMatchPercentage(Sex.MALE));
		//Updates the label for real dates percentage of Katy
		int percent = (int) Math.round(controller.getRealDatePercentage(Sex.MALE)*100);
		String rdString = percent >= 0 ? percent+"%" : "N.A";
		realDateTomLabel.setValue(rdString);
		
		//Set the age for Katy in the controller
		this.controller.setAge(this.ageKatyValue);
		this.interestKaty.setInterest(
				DataBuffer.isInterestBufferized(this.ageKatyValue, Sex.FEMALE) ?
						DataBuffer.Interest(ageKatyValue,  Sex.FEMALE)
						: this.controller.getPreferredTaste(Sex.FEMALE).get(0));
		//Sets the first smiley for Katy
		halfwayKatySmiley.setSatisfaction(controller.getSatisfactionOverall(Sex.FEMALE));
		//Set the match percentage for katy
		endPieChartKaty.setValue(controller.getMatchPercentage(Sex.FEMALE));
		//Updates the label for real dates percentage of Katy
		percent = (int) Math.round(controller.getRealDatePercentage(Sex.FEMALE)*100);
		rdString = percent >= 0 ? percent+"%" : "N.A";
		realDateKatyLabel.setValue(rdString);
		
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
	
	//Just tells if the mouse is over a rectangle (x, y, w, h)
	public static boolean overRect(PApplet p, int x, int y, int w, int h){
		return p.mouseX>=x && p.mouseY>=y && p.mouseX<=x+w && p.mouseY<=y+h;
	}
}
