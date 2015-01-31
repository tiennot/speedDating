package controller;

import processing.core.*;
import view.MainWindow;

public class SpeedDating extends PApplet {
	private MainWindow mw = new MainWindow(this);
	
	public void setup() {
		mw.setup();
	}
	
	public void draw() {
		mw.draw();
	}
	  
	public static void main(String args[]) {
		PApplet.main(new String[] {"controller.SpeedDating" });
	}
}