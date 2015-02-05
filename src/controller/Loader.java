package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import model.AttrBag;
import model.Date;
import model.InterestsBag;
import model.Person;
import model.ScoreCard;
import model.Stat;
import model.constants.Field;
import model.constants.Frequency;
import model.constants.Goal;
import model.constants.Interest;
import model.constants.Interests;
import model.constants.Race;
import model.constants.Sex;

/*
 * The loader class role is to take the CSV file and instantiate all the
 * java object with the right data
 * 
 */
public class Loader {
	// File path
	private String filePath = "data/SpeedDating.csv";
	// The HashMap for keeping track of the persons with their iid;
	private HashMap<Integer, Person> iidPersons = new HashMap<Integer, Person>();
	
	private Stat stat;

	public Loader() {
		super();
	}

	// The main method
	public void load() throws IOException {
		// Material for reading the file
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		String splitter = ",";
		// the first time, it's only the names of the columns
		String[] names = br.readLine().split(splitter);
		// Loops through the file
		while ((line = br.readLine()) != null) {
			// Puts the cells values into an array
			String[] values = line.split(splitter);
			/*
			 * Significant amount of work to be done right here
			 */
			int iid = Integer.parseInt(values[0]);
			if(iidPersons.containsKey(iid)){ //On connait cette personne
				//Il faut recuperer les informations relative au Date
			}
			else{ 
				//Il faut recuperer toutes les donnees utiles du dataset
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
				//Toujours sur 10 apparemment. a verifier.
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
			}
			// Il faut recuperer toutes les donnees utiles du dataset
			// TODO : Checker si "parseboolean" marche bien ? 
			Sex sex = new Sex(Boolean.parseBoolean(values[2]));
			int wave = Integer.parseInt(values[5]);
			boolean on100 = wave < 6 || wave > 9;
			int round = Integer.parseInt(values[6]);
			int position = Integer.parseInt(values[7]);
			int positin1 = Integer.parseInt(values[8]);
			int order = Integer.parseInt(values[9]);
			int pid = Integer.parseInt(values[11]);
			byte match = Byte.parseByte(values[12]);
			double int_corr = Double.parseDouble(values[13]);
			byte dec_o = Byte.parseByte(values[23]);
			AttrBag attr_o = new AttrBag(values, 24, on100, false);
			
			//Ils sont pas dans le pdf ... Mais vu leur nom, c'est vraisemblablement ca.
			byte like_o = Byte.parseByte(values[30]);
			byte prob_o = Byte.parseByte(values[31]);
			boolean met_o = ScoreCard.intToBool(1-Integer.parseInt(values[32]));
			//Scorecard of partner : 
			ScoreCard scoreCard_o = new ScoreCard(ScoreCard.intToBool(dec_o), attr_o, like_o, prob_o, met_o);
			
			int age = Integer.parseInt(values[33]);
			Field field = new Field(Byte.parseByte(values[35]));
			int mnSAT = Integer.parseInt(values[37]);
			Race race = new Race(Byte.parseByte(values[39]));
			byte imprace = Byte.parseByte(values[40]);
			byte imprelig = Byte.parseByte(values[41]);
			Goal goal = new Goal(Byte.parseByte(values[45]));
			Frequency date = new Frequency(Byte.parseByte(values[46]));
			Frequency goOut = new Frequency(Byte.parseByte(values[47]));
			// Career
			InterestsBag interests = new InterestsBag(values, 50);
			byte expHappy = Byte.parseByte(values[67]);
			byte expnum = Byte.parseByte(values[68]);
			AttrBag looksFor_1 = new AttrBag(values, 69, on100, false);
			AttrBag fellowLooksFor_1 = new AttrBag(values, 75, on100, false);
			AttrBag oppSexlookFor_1 = new AttrBag(values, 81, on100, false);
			AttrBag measureUp_1 = new AttrBag(values, 87, on100, true);
			AttrBag otherPerceivesYou_1 = new AttrBag(values, 93, on100, true);
			// Pour le Date
			byte dec = Byte.parseByte(values[97]);
			// TODO: Toujours sur 10 apparemment. A verifier.
			AttrBag notes = new AttrBag(values, 98, false, false);

			byte like = Byte.parseByte(values[104]);
			byte prob = Byte.parseByte(values[105]);
			// yes=1 & no=0
			int met = (1 - (Integer.parseInt(values[105]) - 1));
			
			//Scorecard of the person
			ScoreCard scoreCard = new ScoreCard(ScoreCard.intToBool(dec), notes, like, prob, ScoreCard.intToBool(met));
			
			int match_es = Integer.parseInt(values[107]);

			// Pour la Person
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
			AttrBag otherPerceivesYou_2 = new AttrBag(values, 151, on100, true);
			
			if (!iidPersons.containsKey(iid)) { // If we don't know the person
				// On ajoute la nouvelle personne a la liste des personnes.
				iidPersons.put(iid, new Person(iid, wave, age, sex, race,
						field, mnSAT, imprace, imprelig, expHappy, goal, date,
						goOut, interests, looksFor_1, fellowLooksFor_1,
						oppSexlookFor_1, measureUp_1, otherPerceivesYou_1,
						looksFor_s, measureUp_s, looksFor_2, fellowLooksFor_2,
						oppSexLooksFor_2, measureUp_2, otherPerceivesYou_2));				
			}
			
			Date thisDate = new Date(iidPersons.get(iid),iidPersons.get(pid),position,order, int_corr, scoreCard, scoreCard_o);
			iidPersons.get(iid).addDate(thisDate);
			iidPersons.get(pid).addDate(thisDate);
		}
		br.close();
	}
	
	HashMap<Interest, Integer> avgInterestRateList(int age, Sex sex) {
		HashMap<Interest, Integer> hash = new HashMap<Interest, Integer>(); 
		int count = 0;
		
		for(Interest i : Interest.values()) {
			hash.put(i, stat.avgInterestRate(age, sex, count));
			count++;
		}
		
		return hash;
	}
	
	int nbrPersons(int age, Sex sex) {
		return -1;
	}
	
	int personsThatMatched(int age, Sex sex) {
		return -1;
	}
	
	int[] predictionRateYesAnswers(int age, Sex sex) {
		return new int[0];
	}
	
	int[] avgSelfRate(int age, Sex sex) {
		return new int[0];
	}

	int[] avgSearchRates(int age, Sex sex) {
		return new int[0];
	}
	
	int avgSatisfactionRate(int age, Sex sex) {
		return -1;
	}

}
