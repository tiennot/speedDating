package view.visualizations;

import controller.Controller;
import processing.core.PApplet;
import view.DetailWindow;
import view.XywhObject;

/*
 * This class is abstract
 */
public abstract class Visualization extends XywhObject{

	public Visualization(PApplet p, DetailWindow parent, Controller controller) {
		super(p,0,0,0,0);
		//Calls the parent (sets dimensions)
		parent.setVisualization(this);
	}
	
	public abstract void draw();

}
