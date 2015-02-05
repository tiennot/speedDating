package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.AttrBag;
import model.constants.Sex;

public class Controller implements ControllerInterface {
	
	private Loader loader;
	private int age;

	public Controller(Loader loader) {
		super();
		this.loader = loader;
	}

	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	//Renvoie une liste des goûts préférés des hommes ou des femmes de cet âge.
	public ArrayList<String> getPreferredTaste(Sex sex) {
		// TODO Auto-generated method stub
		return null;
	}

	//Renvoie le pourcentage de match à la fin de la nuit pour les hommes (ou les femmes) de cet âge
	//how many yes halfway. Pour un homme mettre Male
	public double getMatchPercentage(Sex sex) {
		
		int totalNumberOfPeople=loader.nbrPersons(age, sex);
		int numberOfPeopleThatMatched=loader.personsThatMatched(age, sex);
		return (double)totalNumberOfPeople/(double)numberOfPeopleThatMatched;
	}

	//Renvoie le nombre de yes que la personne pense avoir obtenu (réponse supérieure à 5) dans la nuit (si on ne fait pas halfway).
	public int getAmountOfYess(Sex sex) {
		int[] array = loader.predictionRateYesAnswers(age, sex);
		int totalNumberOfYes = 0;
		for (int i =0; i< array.length; i++){
			if (array[i]>5){
				totalNumberOfYes +=1;
			}
		}
		int totalNumberOfPeople=loader.nbrPersons(age, sex);
		return totalNumberOfYes/totalNumberOfPeople;
	}

	//renvoie un enum (ChangementDePerception) qui indique combien la perception a changé
	public ChangementDePerception hasPerceptionOfOhtersChanged(Sex sex) {
		// TODO Auto-generated method stub
		
		return null;
	}

	//idem
	public ChangementDePerception hasSelfPerceptionChanged(Sex sex) {
		// TODO Auto-generated method stub
		return null;
	}

	//renvoie un enum(Satisfaction) qui donne la satisfaction sur toute la nuit.

	public Satisfaction getSatisfactionOverall(Sex sex) {
		// TODO Auto-generated method stub
		return null;
	}

	//On règle l'âge sur lequel on filtre
	public void setAge(int age) {
		this.age=age;

	}

	//Ouvrir une fenêtre lorsqu'on clique sur les goûts d'un genre particulier
	public void handleClickOnTaste(Sex sex) {
		// TODO Auto-generated method stub

	}

	//Renvoie les vecteurs correspondants aux informations pour la fenêtre detailedView
	public HashMap<String, double[]> getTaste(Sex sex) {
		// TODO Auto-generated method stub
		return null;
	}

	//ouvre la fenêtre correspondant l’évolution des différentes perceptions (de soi même, que les autres ont de soi même, de celle de son genre et de celle du genre opposé, recherche)

	public void handleClickOnPerception(Sex sex, TypeDePerception type,
			Step step) {
		// TODO Auto-generated method stub

	}

	//renvoie les différentes notes aux questions de perception 
	public HashMap<String, AttrBag> getPerception(Sex sex,
			TypeDePerception type, Step step) {
		// TODO Auto-generated method stub
		return null;
	}

}

