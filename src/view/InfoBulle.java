package view;

import processing.core.PApplet;

public class InfoBulle extends XywhObject{

	String text;
	int color;
	
	public InfoBulle(PApplet p, int x, int y, int color, String text) {
		super(p, x+7, y-15, 100, 20);
		this.text = text;
		this.color = color;
	}
	
	public void draw(){
		this.drawRect();
		p.fill(p.color(255,255,255));
		p.stroke(color);
		p.rect(x,  y,  w,  h);
		//Draw the little triangle
		p.noStroke();
		p.triangle(x-5,  y+h/2, x+1,  y+h/2-5,  x+1, y+h/2+5);
		p.stroke(color);
		p.line(x-5,  y+h/2, x,  y+h/2-5);
		p.line(x-5,  y+h/2, x, y+h/2+5);
		//Draw the text
		p.textAlign(p.CENTER,  p.CENTER);
		p.fill(color);
		p.textSize(12);
		p.text(text, x, y, w, h);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
