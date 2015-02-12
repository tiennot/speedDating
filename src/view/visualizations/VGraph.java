package view.visualizations;

import java.util.ArrayList;
import java.util.HashMap;

import model.Date;
import model.Person;
import model.constants.Sex;
import controller.Controller;
import processing.core.PApplet;
import view.DetailWindow;
import view.MainWindow;

public class VGraph extends Visualization {
	private final int ageMin = 22; 
	private final int ageMax = 33;
	//Person, int{perX, perY}
	private ArrayList<Person> people;
	private HashMap<Person, int[]> coords =  new HashMap<Person, int[]>();
	//For hovering effect
	private Person hovered = null;
	private boolean mousePressed = false;
	 //+2 for last boolean that tells if at least one age is true
	private boolean[] selectedAges = new boolean[ageMax-ageMin+2];
	private boolean displayGirls = true;
	private boolean displayBoys = true;
	private boolean displayMatches = true;
	private boolean displayHMGirls = true;
	private boolean displayHMBoys = true;
	
	public VGraph(PApplet p, DetailWindow parent, Controller controller) {
		super(p, parent, controller);
		people = controller.getListOfPersons();
		//Creates the hash map with people and their "coordinates"
		for(int age = ageMin; age <= ageMax; age++){
			//Draws the people points for men of the age
			ArrayList<Person> list = controller.getListOfPersons(age);
			int count = list.size();
			for(int i=0; i<count; i++){
				//float angle = (i)*2*p.PI/(count);
				int perX = (i+2)*w/(count+2);
				int perY = liney(age)
						+ (list.get(i).getSex().isEqualTo(Sex.MALE) ? 5 : -5);
				this.coords.put(list.get(i), new int[]{perX, perY});
			}
		}
		//Sets all the values of the boolean array to false
		for(int i=0; i<selectedAges.length; i++) selectedAges[i] = false;
	}

	@Override
	public void draw() {
		Person newHovered = null;
		//draws the age lines
		for(int age=ageMin; age<=ageMax; age++){
			//Draws the circle for the age
			p.noFill();
			p.stroke(p.color(200,200,200));
			p.strokeWeight(1);
			p.line(x, y+liney(age), x+w, y+liney(age));
			//A circle to hover an entire age
			p.fill(this.isSelected(age) ? p.color(255,0,0) : p.color(200,200,200));
			p.noStroke();
			p.ellipse(x, y+liney(age), 15,15);
			//Checks if circle under mouse
			if(MainWindow.overCircle(p, x, y+liney(age), 10)){
				if(p.mousePressed && !this.mousePressed){
					if(p.keyPressed && p.keyCode==17){
						setSelected(age, !isSelected(age));
					}else{
						setOnlySelected(age, !isSelected(age));
					}
				}
			}
		}
		//Now have some fun and draw the people's connections
		p.strokeWeight(1/2);
		p.ellipseMode(p.CENTER);
		for(Person pers: this.people){
			if(hovered==null && !ageFiltering()) break;
			if(!coords.containsKey(pers)) continue;
			//Browse the date of the person
			for(Date d: pers.getDates()){
				//Sanity check
				if(!coords.containsKey(d.getMan()) || !coords.containsKey(d.getWoman())) continue;
				//We display someone's connection only if hovered or belongs to 
				//currently selected age
				if(d.getMan()!=hovered
						&& d.getWoman()!=hovered
						&& !(this.isSelected(d.getMan().getAge()) && this.displayBoys)
						&& !(this.isSelected(d.getWoman().getAge()) && this.displayGirls))
				continue;
				
				int xy1[] = coords.get(d.getMan());
				int xy2[] = coords.get(d.getWoman());
				//Draws line for a match
				if(this.displayMatches && d.match()){
					p.strokeWeight(1);
					p.stroke(p.color(0,0,0));
					p.line(x+xy1[0], y+xy1[1], x+xy2[0], y+xy2[1]);
				//Or a "half match"
				}else if(this.displayHMBoys && !d.match() && d.hisDec()){
					p.strokeWeight(1/2);
					p.stroke(MainWindow.BLUE);
					p.line(x+xy1[0], y+xy1[1], x+xy2[0], y+xy2[1]);
				}else if(this.displayHMGirls && !d.match() && d.herDec()){
					p.strokeWeight(1/2);
					p.stroke(MainWindow.PINK);
					p.line(x+xy1[0], y+xy1[1], x+xy2[0], y+xy2[1]);
				}
			}
		}
		//Now draws the person's circle above
		for(Person pers: this.people){
			if(!coords.containsKey(pers)) continue;
			//Draws basic circle for the person
			p.fill(pers.getSex().isEqualTo(Sex.MALE) ? MainWindow.BLUE : MainWindow.PINK);
			p.noStroke();
			p.ellipse(x+coords.get(pers)[0], y+coords.get(pers)[1], 5, 5);
			if(MainWindow.overCircle(p, x+coords.get(pers)[0],y+coords.get(pers)[1], 3)){
				newHovered = pers;
			}
		}
		
		/*
		 * Now we draw the 5 little tiny buttons at the bottom of the screen
		 */
		//The display boys button
		p.strokeWeight(1);
		if(this.displayBoys) p.fill(MainWindow.BLUE);
		else p.noFill();
		p.stroke(MainWindow.BLUE);
		p.ellipse(x+w/2-60,  y+h, 10, 10);
		if(MainWindow.overCircle(p, x+w/2-60,  y+h, 7) && !mousePressed && p.mousePressed){
			this.displayBoys = !this.displayBoys;
		}
		//The display half matches girls line
		if(this.displayHMGirls) p.fill(MainWindow.PINK);
		else p.noFill();
		p.stroke(MainWindow.PINK);
		p.rect(x+w/2-35-10, y+h-2, 20, 4);	
		if(MainWindow.overRect(p, x+w/2-35-10, y+h-4, 20, 8) && !mousePressed && p.mousePressed){
			this.displayHMGirls = !this.displayHMGirls;
		}
		//The display matches line
		if(this.displayMatches) p.fill(p.color(0,0,0));
		else p.noFill();
		p.stroke(p.color(0,0,0));
		p.rect(x+w/2-10, y+h-2, 20, 4);
		if(MainWindow.overRect(p, x+w/2-10, y+h-4, 20, 8) && !mousePressed && p.mousePressed){
			this.displayMatches = !this.displayMatches;
		}
		//The display half matches boys line
		if(this.displayHMBoys) p.fill(MainWindow.BLUE);
		else p.noFill();
		p.stroke(MainWindow.BLUE);
		p.rect(x+w/2+35-10, y+h-2, 20, 4);
		if(MainWindow.overRect(p, x+w/2+35-10, y+h-4, 20, 8) && !mousePressed && p.mousePressed){
			this.displayHMBoys = !this.displayHMBoys;
		}
		//The display girls button
		if(this.displayGirls) p.fill(MainWindow.PINK);
		else p.noFill();
		p.stroke(MainWindow.PINK);
		p.ellipse(x+w/2+60,  y+h, 10, 10);
		if(MainWindow.overCircle(p, x+w/2+60,  y+h, 7) && !mousePressed && p.mousePressed){
			this.displayGirls = !this.displayGirls;
		}
		
		//Pass on
		hovered = newHovered;
		//Marks mouse as pressed if it's the case
		this.mousePressed = p.mousePressed;
	}
	
	private int radius(int age){
		return Math.min(h, w)*(ageMax-age+1)/2/(ageMax-ageMin+1);
	}
	
	private int liney(int age){
		return h*(ageMax-age+1)/(ageMax-ageMin+2);
	}

	private void setSelected(int age, boolean b){
		this.selectedAges[age-ageMin] = b;
		//Adjusts the last boolean
		if(b) selectedAges[ageMax-ageMin+1] = true;
		else{
			selectedAges[ageMax-ageMin+1] = false;
			for(int a = ageMin; a<= ageMax; a++){
				if(this.selectedAges[a-ageMin]){
					selectedAges[ageMax-ageMin+1] = true;
					return;
				}
			}
		}
	}
	
	private boolean isSelected(int age){
		return this.selectedAges[age-ageMin];
	}
	
	private boolean ageFiltering(){
		//Reminder: The last boolean tells if at least one age is true
		return this.selectedAges[ageMax-ageMin+1];
	}
	
	private void setOnlySelected(int age, boolean b){
		for(int a=ageMin; a<=ageMax; a++){
			if(a!=age) setSelected(a, false);
		}
		setSelected(age, b);
	}
}
