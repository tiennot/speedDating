package view;

import model.constants.Interests;
import model.constants.Sex;
import processing.core.PApplet;
import processing.core.PImage;

/*
 * This class represents an interest (1-17) and its icon
 * which can be displayed in the main window or in a detail window
 * the default size for the icon is 50x50 pixels
 */
public class InterestIcon extends XywhObject{

	//See models.constants.Interests
	int interest = Interests.CONCERTS;
	//See models.constants.Sex
	Sex sex = Sex.FEMALE;
	PImage icon;
	
	//Constructor
	public InterestIcon(PApplet p, int x, int y) {
		super(p, x, y, 50, 50);
	} 
	
	//Overloaded constructor
	public InterestIcon(PApplet p, int x, int y, int interest, Sex sex) {
		super(p, x, y, 50, 50);
		this.setInterest(interest);
		this.setSex(sex);
	}
	
	//Draw method
	public void draw(){
		p.image(icon, x, y, w, h);
	}
	
	//Load image
	private void loadIcon(){
		this.icon = p.loadImage("data/interests-icons/" + (interest+1) + (sex==Sex.MALE ? "m" : "w") + ".png");
	}
	
	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
		loadIcon();
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
		loadIcon();
	}
}
