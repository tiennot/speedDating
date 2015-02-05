package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;

import model.AttrBag;
import model.Date;
import model.InterestsBag;
import model.Person;
import model.ScoreCard;
import model.constants.Field;
import model.constants.Frequency;
import model.constants.Goal;
import model.constants.Race;
import model.constants.Sex;

/*
 * The loader class role is to take the CSV file and instantiate all the
 * java object with the right data
 * 
 * WARNING : If the value is -10, it means it is not in the csv !!!
 * 
 */
public class Loader {
	
	// File path
	private String filePath = "data/SpeedDating.csv";
	// The HashMap for keeping track of the persons with their iid;
	private HashMap<Integer, Person> iidPersons = new HashMap<Integer, Person>();

	public Loader() {
		super();
	}

	/**
	 * writes in the SpeedDatingKey file. In data folder. 
	 * @param number : Determines the csv files we get the data from, and the txt file in which we write
	 * @throws IOException
	 */
	public void writeSpeedDatingKey(int number) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("data/SpeedDating"+number+".csv"));
		String line = "";
		String splitter = ";";
		// the first time, it's only the names of the columns
		String nameString = br.readLine();
		String[] names = nameString.split(splitter);
		System.out.println("Longeur: "+ names.length);
		String pathname = "data/SpeedDatingKey"+ number+".txt" ;
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
	 * @param number which csv file should we get the data from ?
	 * @throws IOException
	 */
	public void writeConstantClass(int number) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("data/SpeedDating"+number+".csv"));
		String line = "";
		String splitter = ";";
		// the first time, it's only the names of the columns
		String nameString = br.readLine();
		String[] names = nameString.split(splitter);
		
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
			
			
			int iid = Loader.parseInteg(values[0]);
			// Il faut recuperer toutes les donnees utiles du dataset
			// TODO : Checker si "parseboolean" marche bien ? 
			Sex sex = new Sex(Loader.parseBool(values[2]));
			int wave = Loader.parseInteg(values[5]);
			boolean on100 = wave < 6 || wave > 9;
			int round = Loader.parseInteg(values[6]);
			int position = Loader.parseInteg(values[7]);
			int positin1 = Loader.parseInteg(values[8]);
			int order = Loader.parseInteg(values[9]);
			int pid = Loader.parseInteg(values[11]);
			byte match = Loader.parseByte2(values[12]);
			double int_corr = Double.parseDouble(values[13]);
			byte dec_o = Loader.parseByte2(values[23]);
			AttrBag attr_o = new AttrBag(values, 24, on100, false);
			
			//Ils sont pas dans le pdf ... Mais vu leur nom, c'est vraisemblablement ca.
			byte like_o = Loader.parseByte2(values[30]);
			byte prob_o = Loader.parseByte2(values[31]);
			//In the dataset, met_o = 1 if not met, and met_o = 2 if met ... 
			//A little math is needed here
			boolean met_o = Loader.intToBool(1-(Loader.parseInteg(values[32])-1));
			//Scorecard of partner : 
			ScoreCard scoreCard_o = new ScoreCard(ScoreCard.intToBool(dec_o), attr_o, like_o, prob_o, met_o);
			
			int age = Loader.parseInteg(values[33]);
			Field field = new Field(Loader.parseByte2(values[35]));
			int mnSAT = Loader.parseInteg(values[37]);
			Race race = new Race(Loader.parseByte2(values[39]));
			byte imprace = Loader.parseByte2(values[40]);
			byte imprelig = Loader.parseByte2(values[41]);
			Goal goal = new Goal(Loader.parseByte2(values[45]));
			Frequency date = new Frequency(Loader.parseByte2(values[46]));
			Frequency goOut = new Frequency(Loader.parseByte2(values[47]));
			// Career
			InterestsBag interests = new InterestsBag(values, 50);
			byte expHappy = Loader.parseByte2(values[67]);
			byte expnum = Loader.parseByte2(values[68]);
			AttrBag looksFor_1 = new AttrBag(values, 69, on100, false);
			AttrBag fellowLooksFor_1 = new AttrBag(values, 75, on100, false);
			AttrBag oppSexlookFor_1 = new AttrBag(values, 81, on100, false);
			AttrBag measureUp_1 = new AttrBag(values, 87, on100, true);
			AttrBag otherPerceivesYou_1 = new AttrBag(values, 93, on100, true);
			// Pour le Date
			byte dec = Loader.parseByte2(values[97]);
			// TODO: Toujours sur 10 apparemment. A verifier.
			AttrBag notes = new AttrBag(values, 98, false, false);

			byte like = Loader.parseByte2(values[104]);
			byte prob = Loader.parseByte2(values[105]);
			// yes=1 & no=0
			int met = (1 - (Loader.parseInteg(values[105]) - 1));
			
			//Scorecard of the person
			ScoreCard scoreCard = new ScoreCard(ScoreCard.intToBool(dec), notes, like, prob, ScoreCard.intToBool(met));
			
			int match_es = Loader.parseInteg(values[107]);

			// Pour la Person
			AttrBag looksFor_s = new AttrBag(values, 108, on100, false);
			AttrBag measureUp_s = new AttrBag(values, 114, on100, true);

			int satis_2 = Loader.parseInteg(values[119]);
			int longueur = Loader.parseInteg(values[120]);
			int numDates = Loader.parseInteg(values[121]);

			AttrBag importance = new AttrBag(values, 122, on100, false);
			AttrBag looksFor_2 = new AttrBag(values, 128, on100, false);
			AttrBag fellowLooksFor_2 = new AttrBag(values, 134, on100, false);
			AttrBag oppSexLooksFor_2 = new AttrBag(values, 140, on100, false);
			AttrBag measureUp_2 = new AttrBag(values, 146, on100, true);
			AttrBag otherPerceivesYou_2 = new AttrBag(values, 151, on100, true);
			
			System.out.println("Fini la ligne !");
			
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
	
	//All those methods return -10 if the string = "", 
	//Otherwise, it's the same (more or less) as Class.parseClass(..)
	/**
	 * 
	 * @param s
	 * @return 
	 */
	public static int parseInteg(String s){
		if(s.equals("")){
			return -10;
		}
		else{
			return (int) Double.parseDouble(s);
		}
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static byte parseByte2(String s){
		if(s.equals("")){
			return -10;
		}
		else{
			return (byte)Double.parseDouble(s);
		}
	}
	
	public static Boolean parseBool(String s){
		if(s.equals("")){
			return null;
		}
		else{
			return Boolean.parseBoolean(s);
		}
	}
	// End of Parsing methods
	
	/**
	 * 
	 * @param i
	 * @return 0 -> false ; 1 -> yes (easy right?) 
	 * @throws ClassFormatException
	 */
	public static boolean intToBool(int i) throws ClassFormatException{
		if(i==0){
			return false;
		}
		else if(i==1){
			return true; 
		}
		else{
			throw new ClassFormatException();
		}
	}
	
}
