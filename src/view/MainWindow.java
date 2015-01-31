package view;

import processing.core.PApplet;

public class MainWindow {
	private PApplet p;
	
	public MainWindow(PApplet p){
		this.p = p;
	}
	
	public void draw(){
		p.stroke(255);
	    if (p.mousePressed) {
	      p.line(p.mouseX,p.mouseY,p.pmouseX,p.pmouseY);
	    }
	}
	
	public void setup(){
		p.size(800,450);
	    p.background(200,0,100);
	}
}
