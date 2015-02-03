package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import jdk.internal.jfr.events.FileWriteEvent;
import model.AttrBag;
import model.InterestsBag;
import model.Person;
import model.constants.Field;
import model.constants.Frequency;
import model.constants.Goal;
import model.constants.Race;
import model.constants.Sex;

/*
 * The loader class role is to take the CSV file and instantiate all the
 * java object with the right data
 * 
 */
public class Loader {
	//File path
	private String filePath = "data/SpeedDating.csv";
	//The HashMap for keeping track of the persons with their iid;
	private HashMap<Integer, Person> iidPersons = new HashMap<Integer, Person>();
	
	public Loader() {
		super();
	}

	//The main method
	public void load() throws IOException{
		//Material for reading the file
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		String splitter = ",";
		//the first time, it's only the names of the columns
		String[] names = br.readLine().split(splitter);
		//Loops through the file
		while ((line = br.readLine()) != null) {
 			//Puts the cells values into an array
			String[] values = line.split(splitter);
			/*
			 * Significant amount of work to be done right here
			 */
			int iid = Integer.parseInt(values[0]);
			if(iidPersons.containsKey(iid)){ //On connait cette personne
				//Il faut récupérer les informations relative au Date
			}
			else{
				//Il faut récupérer toutes les données utiles du dataset
				Sex sex = new Sex(Boolean.parseBoolean(values[2]));
				int wave = Integer.parseInt(values[5]);
				int age = Integer.parseInt(values[33]);
				Field field = new Field(Byte.parseByte(values[35]));
				int mnSAT = Integer.parseInt(values[37]);
				Race race = new Race(Byte.parseByte(values[39]));
				byte imprace = Byte.parseByte(values[40]);
				byte imprelig = Byte.parseByte(values[41]);
				Goal goal = new Goal(Byte.parseByte(values[45]));
				Frequency date = new Frequency(Byte.parseByte(values[46]));
				Frequency goOut = new Frequency(Byte.parseByte(values[47]));
				//Career 
				InterestsBag interests = new InterestsBag(values,50);
				byte expHappy = Byte.parseByte(values[67]);
				byte expnum = Byte.parseByte(values[68]);
				boolean on100 = wave<6 || wave>9 ;
				AttrBag looksFor_1 = new AttrBag(values, 69, on100,false);
				AttrBag fellowLooksFor_1 = new AttrBag(values, 75, on100,false);
				AttrBag oppSexlookFor_1 = new AttrBag(values, 81, on100,false);
				AttrBag measureUp_1 = new AttrBag(values, 87, on100,true);
				AttrBag otherPerceivesYou_1 = new AttrBag(values, 93, on100,true);
				//Pour le Date 
				byte dec = Byte.parseByte(values[97]);
				//Toujours sur 10 apparemment. à vérifier.
				AttrBag notes = new AttrBag(values, 98, false,false);
				
				byte like = Byte.parseByte(values[104]);
				byte prob = Byte.parseByte(values[105]);
				//yes=1 & no=0
				int met = (1-(Integer.parseInt(values[105])-1));
				int match_es = Integer.parseInt(values[107]);
				
				//Pour la Person
				AttrBag looksFor_s = new AttrBag(values, 108, on100, false);
				AttrBag measureUp_s = new AttrBag(values, 114, on100, true);
				
				int satis_2 = Integer.parseInt(values[119]);
				int longueur = Integer.parseInt(values[120]);
				int numDates = Integer.parseInt(values[121]);
				
				AttrBag importance = new AttrBag(values, 122, on100, false);
				AttrBag looksFor_2 = new AttrBag(values, 128, on100, false);
				AttrBag fellowLooksFor_2 = new AttrBag(values, 134, on100, false);
				AttrBag oppSexLooksFor_2 = new AttrBag(values, 140, on100, false);
				AttrBag measureUp_2 = new AttrBag(values, 146, on100, true);
				AttrBag otherPerceivesYou_2 = new AttrBag(values,151,on100,true);
			}
		}
		br.close();
	}
}
