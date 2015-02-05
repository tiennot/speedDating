package view;

import processing.core.PApplet;

public class Smiley extends XywhObject{
	int color;
	private byte humor;
	
	public static byte HAPPY = 1;
	public static byte SAD = 2;
	public static byte EQUAL = 3;
	
	public Smiley(PApplet p, int x, int y, int color, byte humor){
		super(p, x, y, 24, 24); //Smiley's dimension is 24, 24
		this.color = color;
		this.humor = humor;
	}
	
	public void draw(){
		p.noFill();
		p.stroke(color);
		p.strokeWeight(2);
	
		//Draws the outside circle
		p.ellipseMode(p.RADIUS);
		p.ellipse(x,  y,  w/2,  h/2);
		
		//Draws the eyes
		p.ellipse(x+w/6, y-h/6, 1,1);
		p.ellipse(x-w/6, y-h/6, 1,1);
		
		//Draws the mouth according to humor
		if(humor==Smiley.HAPPY){
			p.arc(x, y+h/10, 5, 5, 0, p.PI);
		}else if(humor==Smiley.SAD){
			p.arc(x, y+h/10+4, 5, 5, p.PI, p.PI*2);
		}else{
			p.line(x-w/6,  y+w/6,  x+w/6,  y+w/6);
		}
	}
	
	public boolean over(){
		return ((p.mouseX-x)*(p.mouseX-x) + (p.mouseY-y)*(p.mouseY-y)) <= ((w/2+1)*(w/2+1));
	}

	public byte getHumor() {
		return humor;
	}

	public void setHumor(byte humor) {
		this.humor = humor;
	}
}
