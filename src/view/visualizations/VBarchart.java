package view.visualizations;

import java.util.HashMap;

import model.Person;
import model.constants.Interest;
import model.constants.Sex;
import processing.core.PApplet;
import view.DetailWindow;
import controller.Controller;

public class VBarchart extends Visualization{

	int ageMin = 18;
	int ageMax = 40;
	int[] colors = new int[6];
	HashMap<Integer, double[]> values = new HashMap<Integer, double[]>();
	
	public VBarchart(PApplet p, DetailWindow parent, Controller controller) {
		super(p, parent, controller);
		colors[0] = p.color(220,0,0,150);
		colors[1] = p.color(200,0,150,150);
		colors[2] = p.color(0,0,200,150);
		colors[3] = p.color(0,100,0,150);
		colors[4] = p.color(230,170,0,150);
		colors[5] = p.color(0,200,220,150);
		//We build the values
		for(int age = ageMin; age<=ageMax; age++){
			double total = 0;
			values.put(age, new double[17]);
			for(int j=0; j<17; j++) values.get(age)[j]=0;
			for(Interest i: Interest.getInterestsList()){
				for(Person pers: controller.getListOfPersons(age)){
					values.get(age)[i.getInterestNb()]+=
							(double) pers.getInterests().getInterests()[i.getInterestNb()];
				}	
				total += values.get(age)[i.getInterestNb()];
			}
			for(Interest i : Interest.getInterestsList()){
				values.get(age)[i.getInterestNb()]/=total;
			}
		}
	}

	@Override
	public void draw() {
		//We draw the axis
		p.strokeWeight(1);
		p.stroke(p.color(150,150,150));
		p.line(x, y+h, x+w, y+h);
		
		//Draws a bar per age
		for(int age = ageMin; age<=ageMax; age++){
			int xBar = (age-ageMin)*w/(ageMax-ageMin+1)+5;
			int wBar = w/(ageMax-ageMin)-10;
			int yBar = 0;
			for(int i=0; i<17; i++){
				int hBar = (int) (values.get(age)[i]* (double)h);
				p.fill(colors[i%5]);
				p.noStroke();
				p.rect(x+xBar, y+yBar, wBar, hBar);
				yBar+= hBar;
			}
		}
	}

}
