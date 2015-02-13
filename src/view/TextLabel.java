package view;

import processing.core.PApplet;
import processing.core.PConstants;

public class TextLabel extends XywhObject{
	private int color;
	private float fontSize;
	String value = "";
	private int textAlign, textAlign2;
	private boolean hasTA2 = false;
	
	public TextLabel(PApplet p, int x, int y, int w, int h, float fontSize, int color){
		super(p, x, y, w, h);
		this.fontSize = fontSize;
		this.color = color;
		this.textAlign = p.LEFT; //Default
	}
	
	public TextLabel(PApplet p, int x, int y, int w, int h, float fontSize, int color, int textAlign){
		this(p, x, y, w, h, fontSize, color);
		this.textAlign = textAlign;
	}
	
	public TextLabel(PApplet p, int x, int y, int w, int h, float fontSize, int color, String value){
		this(p, x, y, w, h, fontSize, color);
		this.value = value;
	}
	
	public TextLabel(PApplet p, int x, int y, int w, int h, float fontSize, int color, int textAlign, String value){
		this(p, x, y, w, h, fontSize, color, textAlign);
		this.value = value;
	}
	
	public TextLabel(PApplet p, int x, int y, int w, int h, float fontSize, int color, int textAlign, int textAlign2, String value){
		this(p, x, y, w, h, fontSize, color, textAlign);
		this.value = value;
		this.textAlign2 = textAlign2;
		this.hasTA2 = true;
	}
	
	public TextLabel(PApplet p, int x, int y, int w, int h, float fontSize, int color, int textAlign, int textAlign2){
		this(p, x, y, w, h, fontSize, color, textAlign);
		this.textAlign2 = textAlign2;
		this.hasTA2 = true;
	}
	
	public void draw(String value){
		//this.drawRect();
		if(this.hasTA2){
			p.textAlign(textAlign, textAlign2);
		}else{
			p.textAlign(textAlign);
		}
		p.fill(color);
		p.textSize(fontSize);
		p.text(value, x, y, w, h);
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public void draw(){
		draw(value);
	}
	
}
