package view;

import processing.core.PApplet;

public class PieChart extends XywhObject{
	int bgColor;
	int dataColor;
	float value = (float) 0.75; //From 0 to 1;
	
	public PieChart(PApplet p, int x, int y, int size, int dataColor) {
		super(p, x, y, size, size);
		bgColor = p.color(217,217,217); //Default
		this.dataColor = dataColor;
	}
	
	public void draw(){
		p.ellipseMode(p.RADIUS);
		float angle = (float) value *p.PI*2;
		//Draw the gray circle
		p.noFill();
		p.stroke(dataColor);
		p.strokeWeight(1);
		p.arc(x+w/2,  y+h/2,  w/2-1,  h/2-1,  0, p.PI*2);
		//Draw the colored circle
		p.noStroke();
		p.fill(dataColor);
		p.arc(x+w/2,  y+h/2,  w/2,  h/2, p.PI*3/2, p.PI*3/2+angle);
	}

	public double getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
}
