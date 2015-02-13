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
	Interest interest = Interest.SPORTS;
	//See models.constants.Sex
	Sex sex = Sex.FEMALE;
	PImage icon;
	//Infobulle
	InfoBulle infoBulle;
	
	//Constructor
	public InterestIcon(PApplet p, int x, int y, Interest interest, Sex sex) {
		super(p, x, y, 50, 50);
		int ibColor;
		if(sex==Sex.MALE){
			this.setH(110);
			this.setW(162);
			backImg = p.loadImage("maskInterestM.png");
			iconX = 36; iconY = 43;
			ibColor = MainWindow.BLUE;
		}else{
			this.setH(85);
			this.setW(217);
			backImg = p.loadImage("maskInterestW.png");
			iconX = 83; iconY = 18;
			ibColor = MainWindow.PINK;
		}
		this.infoBulle = new InfoBulle(p, x+iconX+50, y+iconY+25, ibColor, "...");
		this.setInterest(interest);
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
		//If we are over the icon draws infobulle
		if(this.over()){
			this.infoBulle.draw();
		}
	}
	
	//Load image
	private void loadIcon(){
		this.icon = p.loadImage("data/interests-icons/" + (interest.getInterestNb()+1) + (sex==Sex.MALE ? "m" : "w") + ".png");
	}
	
	public Interest getInterest() {
		return interest;
	}

	//Custom setter for interest
	public void setInterest(Interest interest) {
		System.out.println(interest.toString()+interest.getInterestNb());
		//If animating we wait
		if(this.isAnimating){
			this.waitingInterest = interest;
			return;
		}
		//If the interest has not changed don't do anything
		if(this.interest==interest){
			return;
		}
		//Else standard stuff
		this.interest = interest;
		this.oldIcon = this.icon;
		this.infoBulle.setText(interest.toString());
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
