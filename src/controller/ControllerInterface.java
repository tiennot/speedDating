package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.AttrBag;
import model.Person;
import model.constants.Interest;
import model.constants.Sex;

public interface ControllerInterface {
	
	ArrayList<Interest> getPreferredTaste(Sex sex);
	double getMatchPercentage (Sex sex);
	int getAmountOfYess(Sex sex);
	ChangementDePerception hasPerceptionChanged(Sex sex, TypeDePerception type);
	Satisfaction getSatisfactionOverall(Sex sex);
	void setAge(int age);
	HashMap<Interest,int[]> getTaste(Sex sex);
	AttrBag getPerception(Sex sex, TypeDePerception type, Step step);
	ArrayList<Person> getListOfPersons();
	ArrayList<Person> getListOfPersons(int age, Sex sex);

}
