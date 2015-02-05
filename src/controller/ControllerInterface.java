package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.AttrBag;

public interface ControllerInterface {
	
	ArrayList<String> getPreferredTaste(String gender);
	double getMatchPercentage (String gender);
	int getAmountOfYess(String gender);
	ChangementDePerception hasPerceptionOfOhtersChanged(String gender);
	ChangementDePerception hasSelfPerceptionChanged(String gender);
	Satisfaction getSatisfactionOverall(String gender);
	void setAge(int age);
	void handleClickOnTaste(String gender);
	HashMap<String,double[]> getTaste(String gender);
	void handleClickOnPerception(String gender, TypeDePerception type, Step step);
	HashMap<String,AttrBag> getPerception(String gender, TypeDePerception type, Step step);

}
