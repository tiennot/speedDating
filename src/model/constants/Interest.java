package model.constants;

import java.util.ArrayList;

/*
 * Represents one of the 17 interests used in the data set 
 */
public class Interest {
	int interestNb;
	
	// Constructor
	public Interest() {
		super();
	}
	public Interest(int nb) {
		super();
		this.interestNb = nb;
	}

	public int getInterestNb() {
		return interestNb;
	}


	//Returns a list of the 17 interests
	public static ArrayList<Interest> getInterestsList(){
		ArrayList<Interest> toReturn = new ArrayList<Interest>();
		toReturn.add(Interest.SPORTS);
		toReturn.add(Interest.TVSPORTS);
		toReturn.add(Interest.EXERCISE);
		toReturn.add(Interest.DINING);
		toReturn.add(Interest.MUSEUMS);
		toReturn.add(Interest.ART);
		toReturn.add(Interest.HIKING);
		toReturn.add(Interest.GAMING);
		toReturn.add(Interest.CLUBBING);
		toReturn.add(Interest.READING);
		toReturn.add(Interest.TV);
		toReturn.add(Interest.THEATER);
		toReturn.add(Interest.MOVIES);
		toReturn.add(Interest.CONCERTS);
		toReturn.add(Interest.MUSIC);
		toReturn.add(Interest.SHOPPING);
		toReturn.add(Interest.YOGA);
		return toReturn;
	}
	
	//To get the interest as a string
	@Override
	public String toString(){
		switch(this.interestNb){
		case 0:
			return "Sports";
		case 1:
			return "TV Sports";
		case 2:
			return "Exercice";
		case 3:
			return "Dining";
		case 4:
			return "Museums";
		case 5:
			return "Art";
		case 6:
			return "Hiking";
		case 7:
			return "Gaming";
		case 8:
			return "Clubbing";
		case 9:
			return "Reading";
		case 10:
			return "TV";
		case 11:
			return "Theatre";
		case 12:
			return "Movies";
		case 13:
			return "Concerts";
		case 14:
			return "Music";
		case 15:
			return "Shopping";
		case 16:
			return "Yoga";
		default:
			return "Unknow interest";
		}
	}
	
	
	// Static constants
	public static final Interest SPORTS = new Interest(0);
	public static final Interest TVSPORTS = new Interest(1);
	public static final Interest EXERCISE = new Interest(2);
	public static final Interest DINING = new Interest(3);
	public static final Interest MUSEUMS = new Interest(4);
	public static final Interest ART = new Interest(5);
	public static final Interest HIKING = new Interest(6);
	public static final Interest GAMING = new Interest(7);
	public static final Interest CLUBBING = new Interest(8);
	public static final Interest READING = new Interest(9);
	public static final Interest TV = new Interest(10);
	public static final Interest THEATER = new Interest(11);
	public static final Interest MOVIES = new Interest(12);
	public static final Interest CONCERTS = new Interest(13);
	public static final Interest MUSIC = new Interest(14);
	public static final Interest SHOPPING = new Interest(15);
	public static final Interest YOGA = new Interest(16);
}
