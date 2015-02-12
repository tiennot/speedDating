package model;

import java.util.ArrayList;
import java.util.Arrays;

import controller.Parser;

/*
 * Represents the 17 interests of a person
 */
public class InterestsBag {
	//Interests values
	private int[] interestsInt = new int[17];
	
	//More accurate for average values
	private double[] interestsDouble = new double[17];
	
	//Constructor
	public InterestsBag(int sports, int tvsports, int exercise, int dining, int museums, int art,
			int hiking, int gaming, int clubbing, int reading, int tv, int theater, int movies,
			int concerts, int music, int shopping, int yoga){
		interestsInt[0] = sports;
		interestsInt[1] = tvsports;
		interestsInt[2] = exercise;
		interestsInt[3] = dining;
		interestsInt[4] = museums;
		interestsInt[5] = art;
		interestsInt[6] = hiking;
		interestsInt[7] = gaming;
		interestsInt[8] = clubbing;
		interestsInt[9] = reading;
		interestsInt[10] = tv;
		interestsInt[11] = theater;
		interestsInt[12] = movies;
		interestsInt[13] = concerts;
		interestsInt[14] = music;
		interestsInt[15] = shopping;
		interestsInt[16] = yoga;
		interestsDouble = null; //On essaye de pas l'utiliser si possible ... 
	}
	
	//Other constructor with an array
	public InterestsBag(int[] interests){
		for(int i=0; i<17; i++){
			this.interestsInt[i] = interests[i];
		}
	}

	//Empty Constructor
	public InterestsBag() {
		super();
	}
	
	/**
	 * Constructor with offset, should be user to load data (String -> int)
	 * <strong>Catches IndexOutOfBoundExceptions<strong>
	 * @param interests It is the row data
	 * @param offset Where to begin in the table
	 */
	public InterestsBag(String[] interests, int offset) {
		for (int i = 0; i < 17; i++) {
			this.interestsInt[i] = -10;
		}
		try {
			for (int i = 0; i < 17; i++) {
				this.interestsInt[i] = Parser.parseInteg(interests[i + offset]);
			}
		} catch (IndexOutOfBoundsException e) {

		}
	}
	

	/**
	 * Method to allow to add interestbag
	 * @param interestsBag
	 */
	public void addInterestsBag(InterestsBag interestsBag){
		for (int i = 0; i<17; i++ ){
			this.interestsInt[i] += interestsBag.getInterests()[i];
		}
	}
	
	/**
	 * 
	 * @param diviseur
	 */
	public void divise(double diviseur){
		for(int i=0; i<17; i++){
			this.interestsDouble[i] = this.interestsDouble[i]/diviseur;
		}
	}
	
	/**
	 * Copies data from int vector to double vector
	 */
	public void intToDouble(){
		for(int i =0; i<17; i++){
			interestsDouble[i] = interestsInt[i];
		}
	}
	
	//Getters and setters
	public int[] getInterests() {
		return interestsInt;
	}

	public void setInterests(int[] interests) {
		this.interestsInt = interests;
	}

	public double[] getInterestsDouble() {
		return interestsDouble;
	}

	public void setInterestsDouble(double[] interestsDouble) {
		this.interestsDouble = interestsDouble;
	}

	
	public String toStringDouble() {
		return "InterestsBag [interestsDouble="
				+ Arrays.toString(interestsDouble) + "]";
	}
	
	public String toStringInt() {
		return "InterestsBag [interests="
				+ Arrays.toString(this.interestsInt) + "]";
	}

	
}
