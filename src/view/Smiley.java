package view;

import controller.Satisfaction;
import processing.core.PApplet;

public class Smiley extends XywhObject{
	int color;
	private Satisfaction s;
	
	public Smiley(PApplet p, int x, int y, int color, Satisfaction s){
		super(p, x, y, 24, 24); //Smiley's dimension is 24, 24
		this.color = color;
		this.s = s;
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
		if(s==Satisfaction.Tres_satisfait || s==Satisfaction.Satisfait){
			p.arc(x, y+h/10, 5, 5, 0, p.PI);
		}else if(s==Satisfaction.Peu_Satisfait || s==Satisfaction.Tres_Peu_Satisfait){
			p.arc(x, y+h/10+4, 5, 5, p.PI, p.PI*2);
		}else{
			p.line(x-w/6,  y+w/6,  x+w/6,  y+w/6);
		}
	}
	
	public boolean over(){
		return ((p.mouseX-x)*(p.mouseX-x) + (p.mouseY-y)*(p.mouseY-y)) <= ((w/2+1)*(w/2+1));
	}

	public Satisfaction getSatisfaction() {
		return s;
	}

	public void setSatisfaction(Satisfaction s) {
		this.s = s;
	}
}
