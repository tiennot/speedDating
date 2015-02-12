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
	private final int ageMin = 25; 
	private final int ageMax = 35;
	//Person, int{perX, perY}
	private ArrayList<Person> people;
	private HashMap<Person, int[]> coords =  new HashMap<Person, int[]>();
	//For hovering effect
	private Person hovered = null;
	private boolean mousePressed = false;
	 //+2 for last boolean that tells if at least one age is true
	private boolean[] selectedAges = new boolean[ageMax-ageMin+2];
	
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
					setSelected(age, !isSelected(age));
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
				//If someone is hovered we don't display connections for other than him/her
				if(d.getMan()!=hovered
						&& d.getWoman()!=hovered
						&& !this.isSelected(d.getMan().getAge())
						&& !this.isSelected(d.getWoman().getAge())) continue;
				
				int xy1[] = coords.get(d.getMan());
				int xy2[] = coords.get(d.getWoman());
				//Draws line for a match
				if(d.match()){
					p.strokeWeight(1);
					p.stroke(p.color(0,0,0));
					p.line(x+xy1[0], y+xy1[1], x+xy2[0], y+xy2[1]);
				//Or a "half match"
				}else if(d.hisDec() || d.herDec()){
					p.strokeWeight(1/2);
					p.stroke(d.hisDec() ? MainWindow.BLUE : MainWindow.PINK);
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
}
