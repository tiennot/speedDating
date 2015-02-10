package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.AttrBag;
import model.constants.Interest;
import model.constants.Sex;

public interface ControllerInterface {
	
	ArrayList<String> getPreferredTaste(Sex sex);
	double getMatchPercentage (Sex sex);
	int getAmountOfYess(Sex sex);
	ChangementDePerception hasPerceptionChanged(Sex sex, TypeDePerception type);
	Satisfaction getSatisfactionOverall(Sex sex);
	void setAge(int age);
	void handleClickOnTaste(Sex sex);
	HashMap<Interest,double[]> getTaste(Sex sex);
	void handleClickOnPerception(Sex sex, TypeDePerception type, Step step);
	AttrBag getPerception(Sex sex, TypeDePerception type, Step step);

}
