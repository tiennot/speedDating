package controller;

import java.io.IOException;

import model.InterestsBag;
import model.ScoreCard;
import model.constants.Sex;
import model.constants.SpeedDatingKey;
import processing.core.*;
import view.MainWindow;

/*
 * Main file where the main method is to run the program
 */

public class SpeedDating extends PApplet {
	private MainWindow mw;
	Loader loader;
	Controller controller;
	
	public void setup() {
		this.loader = new Loader();
		this.controller = new Controller(loader);
		this.mw = new MainWindow(this);
		//Sets the controller of the main window to controller
		this.mw.setController(controller);
		//Loads the data
		try {
			this.loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Calls the setup of the main window
		mw.setup();
	}
	
	public void draw() {
		//Calls draw from the main window
		mw.draw();
	}
	  
	public static void main(String args[]){		
		//Launch the PApplet
		PApplet.main(new String[] {"controller.SpeedDating" });
	}
}