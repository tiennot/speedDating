package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import model.Person;

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
	
	//The main method
	public void load() throws IOException{
		//Material for reading the file
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		String splitter = ",";
		//Loops through the file
		while ((line = br.readLine()) != null) {
 			//Puts the cells values into an array
			String[] values = line.split(splitter);
			/*
			 * Significant amount of work to be done right here
			 */
		}
	}
}
