package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.AttrBag;

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

	@Override
	//Renvoie une liste des goûts préférés des hommes ou des femmes de cet âge.
	public ArrayList<String> getPreferredTaste(String gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//Renvoie le pourcentage de match à la fin de la nuit pour les hommes (ou les femmes) de cet âge
	//how many yes halfway
	public double getMatchPercentage(String gender) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	//Renvoie le nombre de yes que la personne pense avoir obtenu (réponse supérieure à 5) dans la nuit (si on ne fait pas halfway).
	public int getAmountOfYess(String gender) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	//renvoie un enum (ChangementDePerception) qui indique combien la perception a changé
	public ChangementDePerception hasPerceptionOfOhtersChanged(String gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//idem
	public ChangementDePerception hasSelfPerceptionChanged(String gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//renvoie un enum(Satisfaction) qui donne la satisfaction sur toute la nuit.

	public Satisfaction getSatisfactionOverall(String gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//On règle l'âge sur lequel on filtre
	public void setAge(int age) {
		this.age=age;

	}

	@Override
	//Ouvrir une fenêtre lorsqu'on clique sur les goûts d'un genre particulier
	public void handleClickOnTaste(String gender) {
		// TODO Auto-generated method stub

	}

	@Override
	//Renvoie les vecteurs correspondants aux informations pour la fenêtre detailedView
 
	public HashMap<String, double[]> getTaste(String gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//ouvre la fenêtre correspondant l’évolution des différentes perceptions (de soi même, que les autres ont de soi même, de celle de son genre et de celle du genre opposé, recherche)

	public void handleClickOnPerception(String gender, TypeDePerception type,
			Step step) {
		// TODO Auto-generated method stub

	}

	@Override
	//renvoie les différentes notes aux questions de perception 
	public HashMap<String, AttrBag> getPerception(String gender,
			TypeDePerception type, Step step) {
		// TODO Auto-generated method stub
		return null;
	}

}

