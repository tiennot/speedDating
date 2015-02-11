package view;

import model.constants.Interest;
import model.constants.Interest;
import model.constants.Sex;
import processing.core.PApplet;
import processing.core.PImage;

/*
 * This class represents an interest (1-17) and its icon
 * which can be displayed in the main window or in a detail window
 * the default size for the icon is 50x50 pixels
 */
public class InterestIcon extends XywhObject{
	//Background mask
	PImage backImg;
	//Position of the icon in the mask
	int iconX, iconY;
	//The variables for animation in interest transition
	boolean isAnimating;
	PImage oldIcon;
	int progress = 0; //Should be between 0 & 100
	Interest waitingInterest = null;
	//See models.constants.Interests
	int interest = 1;
	//See models.constants.Sex
	Sex sex = Sex.FEMALE;
	PImage icon;
	
	//Constructor
	public InterestIcon(PApplet p, int x, int y, Interest interest, Sex sex) {
		super(p, x, y, 50, 50);
		this.setInterest(interest);
		if(sex==Sex.MALE){
			this.setH(110);
			this.setW(162);
			backImg = p.loadImage("maskInterestM.png");
			iconX = 36; iconY = 43;
		}else{
			this.setH(85);
			this.setW(217);
			backImg = p.loadImage("maskInterestW.png");
			iconX = 83; iconY = 18;
		}
		this.setSex(sex);
	}
	
	//Draw method
	public void draw(){
		//Draws the icon itself
		if(!this.isAnimating){
			p.image(icon, x+iconX, y+iconY, 50, 50);
		}else{
			int offset = ((this.sex==Sex.MALE?50:0)+iconX)*progress/100;
			int offsetOld = (w-50-iconX)*(100-progress)/100;
			if(oldIcon != null){
				p.image(oldIcon, x+iconX-offset, y+iconY, 50, 50);
			}
			p.image(icon, x+iconX+offsetOld, y+iconY, 50, 50);
			//Sleeps the thread to give the animated effect
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Increments progress
			this.progress+=5;
			//If we reached the end of animation
			if(progress>=100){
				progress = 0;
				isAnimating = false;
				//If a new interest was requested while animating
				if(this.waitingInterest!=null){
					Interest waitingInt = this.waitingInterest;
					this.waitingInterest = null;
					this.setInterest(waitingInt);
				}
			}
		}
		//Draws mask
		p.image(backImg, x, y);
	}
	
	//Load image
	private void loadIcon(){
		this.icon = p.loadImage("data/interests-icons/" + (interest+1) + (sex==Sex.MALE ? "m" : "w") + ".png");
	}
	
	public int getInterest() {
		return interest;
	}

	public void setInterest(Interest interest) {
		//If animating we wait
		if(this.isAnimating){
			this.waitingInterest = interest;
			return;
		}
		//If the interest has not changed don't do anything
		if(this.interest==interest.getInterestNb()+1){
			return;
		}
		//Else standard stuff
		this.interest = interest.getInterestNb()+1;
		this.oldIcon = this.icon;
		loadIcon();
		this.isAnimating = true;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
		loadIcon();
	}
}
