package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;



import model.AttrBag;
import model.Couple;
import model.Date;
import model.InterestsBag;
import model.Person;
import model.ScoreCard;
import model.Stat;
import model.constants.Career;
import model.constants.Field;
import model.constants.Frequency;
import model.constants.Goal;
import model.constants.Interest;
import model.constants.Interest;
import model.constants.Race;
import model.constants.Sex;
import model.constants.SpeedDatingKey;

/*
 * The loader class role is to take the CSV file and instantiate all the
 * java object with the right data
 * 
 * WARNING : If the value is -10, it means it is not in the original data !!!
 * 
 */
public class Loader {
	
	// File path
	private final String FilePath = "./data/dataset";
	private final String Delimitter = "\t";
	public static int erreurCount = 0; 
	private final int nbColumns = 195 ;
	// The HashMap for keeping track of the persons with their iid;
	private HashMap<Integer, Person> iidPersons = new HashMap<Integer, Person>();
	//Keeping all the dates, and mapping them to a couple (iid, pid)
	private HashMap<Couple, Date> coupleDate = new HashMap<Couple, Date>();
	
	private Stat stat;

	public Loader() {
		super();
	}
	
	/**
	 * Updates the stat attribute
	 * to be called after load()
	 */
	public void update(){
		this.stat = new Stat(iidPersons, coupleDate);
	}
	
	/**
	 * writes in the SpeedDatingKey file. In data folder. 
	 * @param id : Determines the file we get the data from, and the txt file in which we write
	 * @throws IOException
	 */
	public void writeSpeedDatingKey(String id) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("data/SpeedDatingExcel.txt"));
		
		// the first time, it's only the names of the columns
		String nameString = br.readLine();
		String[] names = nameString.split(this.Delimitter);
		String pathname = "data/SpeedDating"+ id+".txt" ;
		File file = new File(pathname);
		if(!file.exists()){
			file.createNewFile();
		}
		PrintWriter pw = new PrintWriter(file);
		for(int i=0; i<names.length; i++){
			pw.write(names[i]+ " " + i + "\n" );
		}
		pw.close();
		br.close();
		
	}
	
	/**
	 * Writes in the class SpeedDatingKey.java from the row data.
	 * @throws IOException
	 */
	public void writeConstantClass() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(this.FilePath));

		// the first time, it's only the names of the columns
		String nameString = br.readLine();
		String[] names = nameString.split(this.Delimitter);
		
		String pathname = "src/model/constants/SpeedDatingKey.java" ;
		File file = new File(pathname);
		if(!file.exists()){
			file.createNewFile();
		}
		PrintWriter pw = new PrintWriter(file);
		pw.write("package model.constants;\n\npublic class SpeedDatingKey{ \n\n");
		
		for(int i=0; i<names.length; i++){
			pw.write("\tpublic static final int "+ names[i]+ " = " + i + ";\n" );
		}
		pw.write("}");
		pw.close();
		br.close();
		
	}
	
	/**
	 * Only to understand the dataset... No other purpose. 
	 * @throws IOException
	 */
	public void printer() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(this.FilePath));
		String line = "";
		int lineCount = 1;  
		//System.out.println("Nombre de colonnes en temps normal :" + br.readLine().split(this.Delimitter).length);
		while ((line = br.readLine()) != null){
			String[] values = line.split(this.Delimitter);
			lineCount++;
			/*
			 * Test in here
			 */
			/*
			 * Stop here
			 */
		}
		//System.out.println(((double)taillemoy)/(lineCount-1));
		br.close();
	}
	
	
	/**
	 * Instantiate all Persons and Date of the dataset
	 * Some lines do not have any values for the columns. Therefore I just put
	 * -10 for number values (int double ...) And null for other (Boolean,
	 * AttrBag ...)
	 * Mean length is 168.7 
	 * @throws IOException 
	 */
	public void load() throws IOException {
		// Material for reading the file
		BufferedReader br = new BufferedReader(new FileReader(this.FilePath));
		String line = "";
		// the first time, it's only the names of the columns
		String[] names = br.readLine().split(this.Delimitter);
		// Loops through the file
		while ((line = br.readLine()) != null) {
			// Puts the cells values into an array
			String[] values = line.split(this.Delimitter);
			/*
			 * Significant amount of work to be done right here
			 */
			
			//Unique identifier of a person : 
			int iid = Parser.parseInteg(values,SpeedDatingKey.iid);
			
			Sex sex = new Sex(Parser.parseBool(values[SpeedDatingKey.gender]));
			//Wave on which the person was :
			int wave = Parser.parseInteg(values,SpeedDatingKey.wave);
			
			
			//Wave 6 to 9 are different for the ratings (scale on 1-10, vs scale on a 100 ...)
			boolean on100 = wave < 6 || wave > 9;
			
			//Number of people that met in wave in total : 
			int round = Parser.parseInteg(values,SpeedDatingKey.round);
			//Station number where met
			int position = Parser.parseInteg(values,SpeedDatingKey.position);
			//station number where started
			int positin1 = Parser.parseInteg(values,SpeedDatingKey.positin1);
			//Number of date before this one
			int order = Parser.parseInteg(values,SpeedDatingKey.order);
			//partner's iid
			int pid = Parser.parseInteg(values,SpeedDatingKey.pid);
			
			int match = Parser.parseInteg(values,SpeedDatingKey.match); 
			//Correlation between interests
			double int_corr = Parser.parseDouble2(values[SpeedDatingKey.int_corr]); 
			//Decision of the partner
			boolean dec_o = Parser.parseBool(values[SpeedDatingKey.dec_o]);
			
			//Not really usefull if we have the pid ... But we never know... 
			AttrBag attr_o = new AttrBag(values, SpeedDatingKey.attr_o, on100, false);
			
			//On 10 : how much he/she liked the person
			int like_o = Parser.parseInteg(values,SpeedDatingKey.like_o);
			//How probably will the other person say yes
			int prob_o = Parser.parseInteg(values,SpeedDatingKey.prob_o);
			
			//In the dataset, met_o = 1 if not met, and met_o = 2 if met ... 
			//A little math is needed here
			//Have you met before ? 
			Boolean met_o = Parser.intToBool(1-(Parser.parseInteg(values,SpeedDatingKey.met_o)-1)); 
			
			//Scorecard of partner : 
			ScoreCard scoreCard_o = new ScoreCard(dec_o, attr_o, like_o, prob_o, met_o);

			int age = Parser.parseInteg(values,SpeedDatingKey.age);
			
			Field field = new Field(Parser.parseInteg(values,SpeedDatingKey.field_cd));
			
			int mnSAT = Parser.parseTextFormat(values[SpeedDatingKey.mn_sat]);
			
			Race race = new Race(Parser.parseInteg(values,SpeedDatingKey.race));
			
			int imprace = Parser.parseInteg(values,SpeedDatingKey.imprace);
			
			int imprelig = Parser.parseInteg(values,SpeedDatingKey.imprelig);
			
			Goal goal = new Goal(Parser.parseInteg(values,SpeedDatingKey.goal));
			
			Frequency date = new Frequency(Parser.parseInteg(values,SpeedDatingKey.date));
			
			Frequency goOut = new Frequency(Parser.parseInteg(values,SpeedDatingKey.go_out));
			
				
				/*
				 * Some lines do not go further than here ... 
				 */
			
			
			Career career = new Career(Parser.parseInteg(values,SpeedDatingKey.career_c)) ;	
			
			InterestsBag interests = new InterestsBag(values, SpeedDatingKey.sports);
			
			int expHappy = Parser.parseInteg(values,SpeedDatingKey.exphappy);
			//Estimation of number of people that'll be interested in dating
			int expnum = Parser.parseInteg(values,SpeedDatingKey.expnum);
			
			AttrBag looksFor_1 = new AttrBag(values, SpeedDatingKey.attr1_1, on100, false);
			
			AttrBag fellowLooksFor_1 = new AttrBag(values, SpeedDatingKey.attr4_1, on100, false);
			
			AttrBag oppSexlookFor_1 = new AttrBag(values, SpeedDatingKey.attr2_1, on100, false);
			
			AttrBag measureUp_1 = new AttrBag(values, SpeedDatingKey.attr3_1, on100, true);
			
			AttrBag otherPerceivesYou_1 = new AttrBag(values, SpeedDatingKey.attr5_1, on100, true);
			
			Boolean dec = Parser.parseBool(values[SpeedDatingKey.dec]);
			
			AttrBag notes = new AttrBag(values, SpeedDatingKey.attr, false, false);
			
			int like = Parser.parseInteg(values,SpeedDatingKey.like);
			
			int prob = Parser.parseInteg(values,SpeedDatingKey.prob);
			
			// Same as before : yes=1 & no=0
			Boolean met = Parser.intToBool(Parser.parseInteg(values,SpeedDatingKey.met));
			
			//Scorecard of the person
			ScoreCard scoreCard = new ScoreCard(dec, notes, like, prob, met);
			
			//Estimate the number of matches he/she will get :
			int match_es = Parser.parseInteg(values,SpeedDatingKey.match_es);

			
			// For the person
			AttrBag looksFor_s = new AttrBag(values, SpeedDatingKey.attr1_s, on100, false);
			
			AttrBag measureUp_s = new AttrBag(values, SpeedDatingKey.attr3_s, on100, true);

			int satis_2 = Parser.parseInteg(values,SpeedDatingKey.satis_2);
			
			int longueur = Parser.parseInteg(values,SpeedDatingKey.length);
			
			int numDates = Parser.parseInteg(values,SpeedDatingKey.numdat_2);
			
			AttrBag importance = new AttrBag(values, SpeedDatingKey.attr7_2, on100, false);
			
			//TODO : Pour une raison inconnue, ces valeurs sont avec virgules ... Qu'en fait on ? On tronque ? 
			AttrBag looksFor_2 = new AttrBag(values, SpeedDatingKey.attr1_2, on100, false);
			
			AttrBag fellowLooksFor_2 = new AttrBag(values, SpeedDatingKey.attr4_2, on100, false);
			
			AttrBag oppSexLooksFor_2 = new AttrBag(values, SpeedDatingKey.attr2_2, on100, false);
			
			AttrBag measureUp_2 = new AttrBag(values, SpeedDatingKey.attr3_2, on100, true);
			
			AttrBag otherPerceivesYou_2 = new AttrBag(values, SpeedDatingKey.attr5_2, on100, true);
			
			//TIME 3 
			int youCall = Parser.parseInteg(values,SpeedDatingKey.you_call);
			
			int themCall = Parser.parseInteg(values,SpeedDatingKey.them_cal);
			
			Boolean date_3 = Parser.parseBool(values,SpeedDatingKey.date_3);
			
			//TODO : Quelle difference entre numdat_3 et num_in_3 ? 
			int numDate3 = Parser.parseInteg(values,SpeedDatingKey.numdat_3);
			
			int numIn3 = Parser.parseInteg(values,SpeedDatingKey.num_in_3);
			
			AttrBag looksFor_3 = new AttrBag(values, SpeedDatingKey.attr1_3, on100, false);
			
			AttrBag importance_3 = new AttrBag(values, SpeedDatingKey.attr7_3, on100, false);
			
			AttrBag fellowLooksFor_3 = new AttrBag(values, SpeedDatingKey.attr4_3, on100, false);
			
			AttrBag oppSexLooksFor_3 = new AttrBag(values, SpeedDatingKey.attr2_3, on100, false);
			
			AttrBag measureUp_3 = new AttrBag(values, SpeedDatingKey.attr3_3, on100, true);
			
			AttrBag  otherPerceivesYou_3 = new AttrBag(values, SpeedDatingKey.attr5_3, on100, true);
			
			
			
			if (!iidPersons.containsKey(iid)) { // If we don't know the person
				// We add the new person to the list of persons.
				Person person = new Person(iid, wave, age, sex, race,
						field, mnSAT, imprace, imprelig, expHappy, goal, date,
						goOut, interests, looksFor_1, fellowLooksFor_1,
						oppSexlookFor_1, measureUp_1, otherPerceivesYou_1,
						looksFor_s, measureUp_s, looksFor_2, fellowLooksFor_2,
						oppSexLooksFor_2, measureUp_2, otherPerceivesYou_2, satis_2,round);
				person.setPositin1(positin1);
				person.setCareer(career);
				person.setExpectedNumber(expnum);
				person.setEstimNumberOfMatch(match_es);
				person.setRateLength(longueur);
				person.setNumDate(numDates);
				person.setImportance(importance);
				person.setYouCall(youCall);
				person.setThemCall(themCall);
				person.setOnADate(date_3);
				person.setNumDate(numDate3);
				person.setNumIn3(numIn3);
				person.setLooksFor_3(looksFor_3);
				person.setImportance_3(importance_3);
				person.setFellowLooksFor_3(fellowLooksFor_3);
				person.setOppSexLooksFor_3(oppSexLooksFor_3);
				person.setMeasureUp_3(measureUp_3);
				person.setOtherPerceivesYou_3(otherPerceivesYou_3);
				iidPersons.put(iid, person);
			}
			
			/*
			 * To keep a logic with the name of the attributes, 
			 * The first argument of the constructor is the man. 
			 */
			if(iidPersons.containsKey(iid)&&iidPersons.containsKey(pid)){ //If we know both persons, we create a new Date corresponding to those two persons
				if(iidPersons.get(iid).getSex().isEqualTo(Sex.MALE)){
					Date thisDate = new Date(iidPersons.get(iid),iidPersons.get(pid), position, order, int_corr, scoreCard, scoreCard_o);
					iidPersons.get(iid).addDate(thisDate);
					iidPersons.get(pid).addDate(thisDate);
					coupleDate.put(new Couple(iid, pid), thisDate);
				}
				else{
					Date thisDate = new Date(iidPersons.get(pid),iidPersons.get(iid), position, order, int_corr, scoreCard, scoreCard_o);
					iidPersons.get(iid).addDate(thisDate);
					iidPersons.get(pid).addDate(thisDate);
					coupleDate.put(new Couple(pid, iid), thisDate);
				}
			}
			Loader.erreurCount =0;
		}
		br.close();
		this.update();
	}

	
	/**
	 * Doesn't work for now.
	 * Complete a line with blank fields. 
	 * @param s
	 * @return Completed line
	 */
	public String stringComplete(String s){
		String ans = s;
		String[] test =  s.split(this.Delimitter);
		if(test.length>this.nbColumns){
			System.err.println("ATTENTION !");
		}
		while(ans.split(Delimitter).length != this.nbColumns){
			ans += "" + this.Delimitter ;
		}
		return ans;
	}
	
	HashMap<Interest, Double> avgInterestRateList(int age, Sex sex) {
		HashMap<Interest, Double> hash = new HashMap<Interest, Double>(); 
		int count = 0;
		
		for(Interest i : Interest.getInterestsList()) {
			hash.put(i, stat.avgInterestRate(age, sex, i));
			count++;
		}
		
		return hash;

	}
	
	int nbrPersons(int age, Sex sex) {
		return stat.numberPerson(age, sex);
	}
	
	int nbrPersons(int age) {
		return stat.numberPerson(age);
	}
	
	double percentRealDaters(int age, Sex sex){
		return stat.percentRealDaters(age, sex);
	}
	
	int personsThatMatched(int age, Sex sex) {
		return stat.numberOfPersonThatMatched(age, sex);
	}
	
	ArrayList<Integer> predictionRateYesAnswers(int age, Sex sex) {
		return stat.predictionRateYesAnswers(age,sex);
	}
	
	int[] avgSelfRate(Sex sex, Step step) {
		return stat.avgSelfRate(sex, step);
	}

	int[] avgSearchRates(Sex sex, Step step) {
		return stat.avgSearchRate(sex, step);
	}
	
	int avgSatisfactionRate(int age, Sex sex) {
		return stat.avgSatisfactionRate(age, sex);
	}
	
	HashMap<Integer, Integer> peopleByRate(Sex sex, Interest interest) {
		return stat.peopleByRate(sex, interest);
	}

	//Getters
	public HashMap<Integer, Person> getIidPersons() {
		return iidPersons;
	}

	public HashMap<Couple, Date> getCoupleDate() {
		return coupleDate;
	}

	public Stat getStat() {
		return stat;
	}

	
	
}
