package model;

import java.util.ArrayList;

import controller.Parser;

/*
 * Represents the 17 interests of a person
 */
public class InterestsBag {
	//Interests values
	private int[] interestsInt = new int[17];
	
	/* Probleme avec les entiers si l'on veut repr�senter des moyennes etc... 
	 * Solution : on utilise une liste de double. Plus lourd en m�moire, mais on essaye au max de pas l'utiliser.  
	 */
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
	 * Constructeur avec offset, a utiliser pour loader les data (String-> int)
	 * @param interests
	 * @param offset
	 */
	public InterestsBag(String[] interests, int offset){
		for(int i=0; i<17; i++){
			this.interestsInt[i]= Parser.parseInteg(interests[i+offset]);
		}
	}

	/**
	 * Methode qui permet d'ajouter deux InterestsBag
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
	 * Copy les donn�es du vecteur "Int" dans le "Double". 
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

}
