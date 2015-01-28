package controller;

import processing.core.*;

public class SpeedDating extends PApplet {

  public void setup() {
    size(800,450);
    background(200,0,100);
  }

  public void draw() {
    stroke(255);
    if (mousePressed) {
      line(mouseX,mouseY,pmouseX,pmouseY);
    }
  }
  
  public static void main(String args[]) {
	    PApplet.main(new String[] {"controller.SpeedDating" });
	  }
}