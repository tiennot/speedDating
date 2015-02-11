package view;

import java.util.ArrayList;
import java.util.HashMap;

import model.constants.Interest;
import model.constants.Sex;

/*
 * This class is user to cache some data retrieved from the controller 
 * instead of asking for them again and again when the user interract
 * with the view
 */
public class DataBuffer {
	//Contains the interests 
	private static HashMap<Integer, Interest[]> interestsByAge = new HashMap<Integer, Interest[]>();
	
	//Adds an interest to the buffer
	public static void addInterest(int age, Sex sex, Interest interest){
		if(!interestsByAge.containsKey(age)){
			interestsByAge.put(age, new Interest[]{ null, null});
		}
		interestsByAge.get(age)[sex==Sex.MALE?1:0] = interest;
	}
	
	//To get an interest given age and sex
	public static Interest Interest(int age, Sex sex){
		if(interestsByAge.containsKey(age)){
			return interestsByAge.get(age)[sex==Sex.MALE?1:0];
		}
		return null;
	}
	
	//Says if the interest is bufferized so far
	public static boolean isInterestBufferized(int age, Sex sex){
		return interestsByAge.containsKey(age)
				&& interestsByAge.get(age)[sex==Sex.MALE?1:0] != null;
	}
}
