package view;

import processing.core.PApplet;

public class Smiley {
	private PApplet p;
	int x, y, color;
	int width = 24;
	int height = 24;
	private byte humor;
	
	public static byte HAPPY = 1;
	public static byte SAD = 2;
	public static byte EQUAL = 3;
	
	public Smiley(PApplet p, int x, int y, int color, byte humor){
		this.p = p;
		this.x = x;
		this.y = y;
		this.color = color;
		this.humor = humor;
	}
	
	public void draw(){
		p.noFill();
		p.stroke(color);
		p.strokeWeight(2);
	
		//Draws the outside circle
		p.ellipseMode(p.RADIUS);
		p.ellipse(x,  y,  width/2,  height/2);
		
		//Draws the eyes
		p.ellipse(x+width/6, y-height/6, 1,1);
		p.ellipse(x-width/6, y-height/6, 1,1);
		
		//Draws the mouth according to humor
		if(humor==Smiley.HAPPY){
			p.arc(x, y+height/10, 5, 5, 0, p.PI);
		}else if(humor==Smiley.SAD){
			p.arc(x, y+height/10+4, 5, 5, p.PI, p.PI*2);
		}else{
			p.line(x-width/6,  y+width/6,  x+width/6,  y+width/6);
		}
	}
	
	public boolean over(){
		return ((p.mouseX-x)*(p.mouseX-x) + (p.mouseY-y)*(p.mouseY-y)) <= ((width/2+1)*(width/2+1));
	}
}
