package controller;

import java.io.IOException;

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
	  
	public static void main(String args[]){
		Loader loader = new Loader();
		try {
			loader.writeConstantClass(2);
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PApplet.main(new String[] {"controller.SpeedDating" });
	}
}