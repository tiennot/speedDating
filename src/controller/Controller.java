package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import model.AttrBag;
import model.Person;
import model.constants.Interest;
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


	//Returns a list with favorite tasted of men or women this age.
	public ArrayList<Interest> getPreferredTaste(Sex sex) {
		HashMap<Interest, Double> h = loader.avgInterestRateList(age, sex) ;
		
		for(Interest i: Interest.getInterestsList()){
		}
		
		ArrayList<Interest> listeOfInterest = Interest.getInterestsList();
		
		for (int i = 1; i <listeOfInterest.size(); i++) {
			Interest x = listeOfInterest.get(i) ;
			for (int j = i-1 ; j>=0; j--) {
				Interest y = listeOfInterest.get(j);
				if (h.get(x)>h.get(y)) {
					listeOfInterest.add(j, x);
					listeOfInterest.remove(i+1);
				}
			}
		}
			
		return listeOfInterest;
	}

	//Return match percentage halfway for women or men this age
	public double getMatchPercentage(Sex sex) {
		int totalNb=loader.nbrPersons(age, sex);
		int matchNb=loader.personsThatMatched(age, sex);
		//If there are less than 5 people this kind we consider it's no relevant
		return totalNb < 5 ? -1 : (double)matchNb/(double)totalNb;
	}

	//Returns number of yes the person thinks he/she got (answer > 5) during the night
	public int getAmountOfYess(Sex sex) {
		ArrayList<Integer> array = loader.predictionRateYesAnswers(age, sex);
		int totalNumberOfYes = 0;
		for (int i =0; i< array.size(); i++){
			if (array.get(i)>5){
				totalNumberOfYes +=1;
			}
		}
		int totalNumberOfPeople=loader.nbrPersons(age, sex);
		return totalNumberOfYes/totalNumberOfPeople;
	}

	//Returns enum (ChangementDePerception) indicating how much perception has changed through the night
	public ChangementDePerception hasPerceptionChanged(Sex sex, TypeDePerception type) {
		switch (type) {
		case Perception_de_soi_meme: 
			int[] deb = loader.avgSelfRate(sex, Step.Debut) ;
			int[] fin = loader.avgSelfRate(sex, Step.Fin) ;
			AttrBag d = new AttrBag(deb[0], deb[1], deb[2], deb[3], deb[4], deb[5]);
			AttrBag f = new AttrBag(fin[0], fin[1], fin[2], fin[3], fin[4], fin[5]);
			AttrBag diff = d.AttrBagDifference(f) ;
			
			double attr = (double)diff.getAttr() ;
			double amb = (double)diff.getAmb() ;
			double sinc = (double)diff.getSinc() ;
			double fun = (double)diff.getFun() ;
			double intel = (double)diff.getIntel() ;
			double shar = (double)diff.getShar();
			// a1 is the non normalized distance between two perceptions
			double a = Math.sqrt(attr*attr + amb*amb + sinc*sinc + intel*intel + shar*shar + fun*fun ) ;
		
			if (a < 3.5) {
				return ChangementDePerception.AGardeLaMemePerception ;
			}
			else {
				if (a < 7) {
					return ChangementDePerception.AEvolueDansSaPerception ;
				}
				else {
					return ChangementDePerception.ARadicalementChangeDePerception ;
				}
			}
			
		case Recherche_personnelle_dans_le_sexe_oppose :
			int[] deb1 = loader.avgSearchRates(sex, Step.Debut) ;
			int[] fin1 = loader.avgSearchRates(sex, Step.Fin) ;
			AttrBag d1 = new AttrBag(deb1[0], deb1[1], deb1[2], deb1[3], deb1[4], deb1[5]);
			AttrBag f1 = new AttrBag(fin1[0], fin1[1], fin1[2], fin1[3], fin1[4], fin1[5]);
			AttrBag diff1 = d1.AttrBagDifference(f1) ;
			
			double attr1 = (double)diff1.getAttr() ;
			double amb1 = (double)diff1.getAmb() ;
			double sinc1 = (double)diff1.getSinc() ;
			double fun1 = (double)diff1.getFun() ;
			double intel1 = (double)diff1.getIntel() ;
			double shar1 = (double)diff1.getShar();
			// a1 is the non-normalized distance between the two perceptions
			double a1 = Math.sqrt(attr1*attr1 + amb1*amb1 + sinc1*sinc1 + intel1*intel1 + shar1*shar1 + fun1*fun1 ) ;
		
			if (a1 < 3.5) {
				return ChangementDePerception.AGardeLaMemePerception;
			}
			else {
				if (a1 < 7) {
					return ChangementDePerception.AEvolueDansSaPerception ;
				}
				else {
					return ChangementDePerception.ARadicalementChangeDePerception ;
				}
			}
			
			
			
		default : return ChangementDePerception.AGardeLaMemePerception ;
		}
	}


	//Returns enum(Satisfaction) giving satisfaction for the whole night

	public Satisfaction getSatisfactionOverall(Sex sex) {
		int satisfaction = loader.avgSatisfactionRate(age, sex);
		switch (satisfaction){
		case 10 : ;
		case 9: return Satisfaction.Tres_satisfait;
		case 8:;
		case 7:; 
		case 6: return Satisfaction.Satisfait;
		case 5: ;
		case 4: return Satisfaction.Moyennement_Satisfait;
		case 3: ;
		case 2: return Satisfaction.Peu_Satisfait;
		case 1:;
		case 0: return Satisfaction.Tres_Peu_Satisfait;
		default : return Satisfaction.Tres_Peu_Satisfait;
		}

	}

	//Sets the filter age
	public void setAge(int age) {
		this.age=age;

	}

	/*
	 * Returns a hash map with the number of people that gave a given mark
	 * to each interest for the sex passed as parameter
	 */
	public HashMap<Interest, int[]> getTaste(Sex sex) {
		HashMap<Interest, int[]> map = new HashMap<Interest, int[]>();
		
		for (Interest interest : Interest.getInterestsList()){
			HashMap<Integer, Integer> rates = loader.peopleByRate(sex, interest);
			int[] tab = new int[11];
			for (int i =0; i<11; i++){
				tab[i]= rates.get(i);
			}
			map.put(interest, tab);
		}
		return map;
	}

	//Returns marks for perception questions 
	public AttrBag getPerception(Sex sex,
			TypeDePerception type, Step step) {
		int[] rates = {5,5,5,5,5,5};
		switch(type){
		case Perception_de_soi_meme : 
			rates = loader.avgSelfRate(sex, step);break;
		case Recherche_personnelle_dans_le_sexe_oppose :
			rates = loader.avgSearchRates(sex, step);break ;

		}

		return new AttrBag(rates[0],rates[1],rates[2],rates[3],rates[4],rates[5]);

	}

	public ArrayList<Person> getListOfPersons() {
		return loader.getStat().getPersonList();
	}

	public ArrayList<Person> getListOfPersons(int age, Sex sex) {
		return loader.getStat().getPersonAgeSex(age, sex);
	}
	
	public ArrayList<Person> getListOfPersons(int age) {
		return loader.getStat().getPersonAge(age);
	}

	//Returns the percentage of people this age who have been on a real date after the SD
	public double getRealDatePercentage(Sex sex) {
		return loader.percentRealDaters(age, sex);
	}

}

