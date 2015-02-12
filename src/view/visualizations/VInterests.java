package view.visualizations;

import java.util.HashMap;

import model.constants.Interest;
import model.constants.Sex;
import controller.Controller;
import processing.core.PApplet;
import view.DetailWindow;

/*
 * The interests visualization class
 */
public class VInterests extends Visualization {
	//Colors for display
	int[] colors = new int[6];
	//Tells if focused on an interest
	int focus = 5;
	//Array with the names of the interests
	String[] labels = new String[]{
			"Sports",
			"TV Sports",
			"Exercice",
			"Dining",
			"Museums",
			"Art",
			"Hiking",
			"Gaming",
			"Clubbing",
			"Reading",
			"TV",
			"Theatre",
			"Movies",
			"Concerts",
			"Music",
			"Shopping",
			"Yoga"
	};
	//Arrays with the data
	int[][] interestsM = new int[17][11];
	int[][] interestsW = new int[17][11];
	  
	public VInterests(PApplet p, DetailWindow parent, Controller controller) {
		super(p, parent, controller);
		//Sets the colors pour display
		colors[0] = p.color(220,0,0,150);
		colors[1] = p.color(200,0,150,150);
		colors[2] = p.color(0,0,200,150);
		colors[3] = p.color(0,100,0,150);
		colors[4] = p.color(230,170,0,150);
		colors[5] = p.color(0,200,220,150);
		
		HashMap<Interest, int[]> tasteMale = controller.getTaste(Sex.MALE);
		HashMap<Interest, int[]> tasteFemale = controller.getTaste(Sex.FEMALE);
		
		for(Interest i: Interest.getInterestsList()){
			for(int j=0; j<11; j++){
				interestsM[i.getInterestNb()][j] = tasteMale.get(i)[j];	
				interestsW[i.getInterestNb()][j] = tasteFemale.get(i)[j];
			}
		}
	}
	
	@Override
	public void draw(){		
		int newFocus = -1;
		p.textSize(12);
		int diamGlobal = p.min(w, h);
		p.fill(200,200,200,30);
		p.stroke(200);
		p.ellipse(x+w/2, y+h/2, diamGlobal/2, diamGlobal/2);
		//Tiny circle in the center
		p.fill(255,255,255);
		p.ellipse(x+w/2, y+h/2, 20,20);
		p.line(x+w/2, y+h/2-20, x+w/2, y+h/2+20);
		p.fill(200,200,200);
		p.textAlign(p.CENTER, p.CENTER);
		p.text("W M", x+w/2, y+h/2);
		//For each couple (interest, 1-10 mark) we draw a circle which size is
		//Proportional to the amount of people
		for(int i=0; i<17; i++){
			int diam = focus >=0 && i==focus ? diamGlobal : diamGlobal;
			/*
			 * Draw for men
			 */
			if(focus<0 || i==focus){
				p.fill(colors[i%6]);
			}else{
				p.fill(p.color(150,150,150,150));
			}
			int[] O = new int[]{
				w/2,
				h/2
			};
			int[] M = new int[]{
				w/2 + (int)((float)(diam/2) * p.cos((float) (p.HALF_PI-p.PI*((1+i))/18.))),
				h/2 - (int)((float)(diam/2) * p.sin((float)(p.HALF_PI-p.PI*((1+i))/18.)))
			};
			//The label & line
			p.stroke(200);
			p.line(x+M[0], y+M[1], x+w/2+diamGlobal/2+5, y+diamGlobal*(i+1)/18);
			p.textAlign(p.LEFT, p.CENTER);
			p.text(labels[i], x+w/2+diamGlobal/2+10 , y+diamGlobal*(i+1)/18);
			//The circles
			for(int j=0; j<11; j++){
				int xi = (int)((O[0])*(1.- (j+1)/12.) + (float)(M[0])*((j+1)/12.));
				int yi = (int)((O[1])*(1.- (j+1)/12.) + (float)(M[1])*((j+1)/12.));
				int radius = (int)((float)(interestsM[i][j])/3.5);
				p.noStroke();
				p.ellipse(x + xi, y + yi , radius, radius);
			}
			//If mouse in the sector
			if((p.mouseX-x-w/2)*(p.mouseX-x-w/2)+(p.mouseY-y-h/2)*(p.mouseY-y-h/2)<=diamGlobal*diamGlobal/4){
				float cos = p.abs((float)((p.mouseX-(x+w/2))));
				float sin = (float)((-p.mouseY+(y+h/2)));
				if(sin/cos <= p.tan((float) (p.HALF_PI-p.PI*((i+0.5))/18.))
						&& sin/cos >= p.tan((float) (p.HALF_PI-p.PI*((i+1.5))/18.))){
					newFocus = i;
				}	
			}
			/*
			 * Draws for women
			 */
			if(focus<0 || i==focus){
				p.fill(colors[i%6]);
			}else{
				p.fill(p.color(150,150,150,150));
			}
			O = new int[]{
				w/2,
				h/2
			};
			M = new int[]{
				w/2 - (int)((float)(diam/2) * p.cos((float) (p.HALF_PI-p.PI*((1+i))/18.))),
				h/2 - (int)((float)(diam/2) * p.sin((float)(p.HALF_PI-p.PI*((1+i))/18.)))
			};
			//The label & line
			p.stroke(200);
			p.line(x+M[0], y+M[1], x+w/2-diamGlobal/2-5, y+diamGlobal*(i+1)/18);
			p.textAlign(p.RIGHT, p.CENTER);
			p.text(labels[i], x+w/2-diamGlobal/2-10 , y+diamGlobal*(i+1)/18);
			//The circles
			for(int j=0; j<11; j++){
				int xi = (int)((O[0])*(1.- (j+1)/12.) + (float)(M[0])*((j+1)/12.));
				int yi = (int)((O[1])*(1.- (j+1)/12.) + (float)(M[1])*((j+1)/12.));
				int radius = (int)((float)(interestsW[i][j])/3.5);
				p.noStroke();
				p.ellipse(x + xi, y + yi , radius, radius);
			}
		}
		focus = newFocus;
	}
}
