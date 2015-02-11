package model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath.Step;

import model.constants.Interest;
import model.constants.Interest;
import model.constants.Sex;

public class Stat {
	
	//Liste exhaustive des personnes presentes dans le dataset
	private ArrayList<Person> personList ;
	
	//Liste exhaustive des Dates effectue dans le dataset
	private ArrayList<Date> dateList;

	public Stat(ArrayList<Person> personList) {
		this.personList = personList;
	}
	
	public Stat(HashMap<Integer, Person> iidPersons){
		Collection<Person> collec = iidPersons.values() ;
		Iterator<Person> it = collec.iterator();
		this.personList = new ArrayList<Person>();
		while(it.hasNext()){
			personList.add(it.next());
		}	
	}
	
	public Stat(HashMap<Integer, Person> iidPersons, HashMap<Couple, Date> coupleDate){
		this(iidPersons);
		Collection<Date> collec = coupleDate.values() ;
		Iterator<Date> it = collec.iterator();
		this.dateList = new ArrayList<Date>();
		while(it.hasNext()){
			dateList.add(it.next());
		}
	}
	
	/**
	 * 
	 * @param age
	 * @return List of people with the given age
	 */
	public ArrayList<Person> getPersonAge(int age){
		ArrayList<Person> list = new ArrayList<Person>();
		for (int i = 0; i< personList.size(); i++){
			if(personList.get(i).getAge()==age){
				list.add(personList.get(i));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param age
	 * @param sex
	 * @return list of people who are the given age and the given sex
	 */
	public ArrayList<Person> getPersonAgeSex(int age, Sex sex){
		ArrayList<Person> list = new ArrayList<Person>();

		for (int i = 0; i< personList.size(); i++){
			if(personList.get(i).getAge()==age && personList.get(i).getSex().isEqualTo(sex)){
				list.add(personList.get(i));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @parm sex
	 * @return List of person with the given sex
	 */
	public ArrayList<Person> getPersonSex(Sex sex) {
		ArrayList<Person> list = new ArrayList<Person>();
		for (int i = 0; i< personList.size(); i++){
			if(personList.get(i).getSex().equals(sex)){
				list.add(personList.get(i));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param age
	 * @param sex
	 * @return Interests of person from given age and sex
	 */
	public InterestsBag getPreferences(int age, Sex sex){
		ArrayList<Person> list = this.getPersonAgeSex(age, sex);
		InterestsBag interests = new InterestsBag();
		for (int i=0; i<list.size(); i++){
			interests.addInterestsBag(list.get(i).getInterests()); //On ajoute les interets de tout le monde
		}
		interests.intToDouble(); // On passavgSatisfactionRatee en double pour avoir des valeurs exactes quand on divise.
		interests.divise(list.size());
		return interests;
	}
	
	public double avgInterestRate(int age, Sex sex, Interest interest) {
		int sum = 0;
		int ansNbr = 0;
		int rate;
		ArrayList<Person> list = this.getPersonAgeSex(age, sex);
		for (int i = 0 ; i < list.size() ; i++) {
			rate = (int)(list.get(i).getInterests().getInterests())[interest.getInterestNb()];
			if(rate >= 0) {
				sum += rate;
				ansNbr++;
			}
		}
		
		return (double)(sum) / Math.max((double) ansNbr, 1);
	}
	
	public int numberPerson(int age, Sex sex) {
		return this.getPersonAgeSex(age, sex).size();
	}
	
	public int numberOfPersonThatMatched(int age, Sex sex) {
		ArrayList<Person> list = this.getPersonAgeSex(age, sex);
		int n = 0;
		
		for(int i = 0 ; i < list.size() ; i++) {
			ArrayList<Date> dates = list.get(i).dates;
			for(int j = 0 ; j < dates.size() ; j++) {
				if(dates.get(i).match()) {
					n++;
					break;
				}
			}
		}
		
		return n;
	}
	
	public ArrayList<Integer> predictionRateYesAnswers(int age, Sex sex) {
		ArrayList<Person> list = this.getPersonAgeSex(age, sex);
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i = 0 ; i < list.size() ; i++) {
			ArrayList<Date> dates = list.get(i).dates;
			Sex currSex = list.get(i).getSex();
			for(int j = 0 ; j < dates.size() ; j ++) {
				//if man
				if(currSex == Sex.MALE) {
					array.add(dates.get(i).getHisScoreCard().getProb());
				} else { //woman
					array.add(dates.get(i).getHerScoreCard().getProb());
				}
			}
		}
		
		return array;
	}
	
	public int[] avgSelfRate(Sex sex, controller.Step step) {
		ArrayList<Person> list = this.getPersonSex(sex);
		int[] avg = {0, 0, 0, 0, 0, 0};
		Method m;
		
		if(step == controller.Step.Debut) {
			for(int i = 0 ; i < list.size() ; i++) {
				avg[0] += list.get(i).getMeasureUp_1().getAttr();
				avg[1] += list.get(i).getMeasureUp_1().getSinc();
				avg[2] += list.get(i).getMeasureUp_1().getIntel();
				avg[3] += list.get(i).getMeasureUp_1().getFun();
				avg[4] += list.get(i).getMeasureUp_1().getAmb();
				avg[5] += list.get(i).getMeasureUp_1().getShar();
			}
		} else if(step == controller.Step.Milieu) {
			for(int i = 0 ; i < list.size() ; i++) {
				avg[0] += list.get(i).getMeasureUp_s().getAttr();
				avg[1] += list.get(i).getMeasureUp_s().getSinc();
				avg[2] += list.get(i).getMeasureUp_s().getIntel();
				avg[3] += list.get(i).getMeasureUp_s().getFun();
				avg[4] += list.get(i).getMeasureUp_s().getAmb();
				avg[5] += list.get(i).getMeasureUp_s().getShar();
			}
		} else { // Fin
			for(int i = 0 ; i < list.size() ; i++) {
				avg[0] += list.get(i).getMeasureUp_2().getAttr();
				avg[1] += list.get(i).getMeasureUp_2().getSinc();
				avg[2] += list.get(i).getMeasureUp_2().getIntel();
				avg[3] += list.get(i).getMeasureUp_2().getFun();
				avg[4] += list.get(i).getMeasureUp_2().getAmb();
				avg[5] += list.get(i).getMeasureUp_2().getShar();
			}
		}
		
		for(int i = 0 ; i < 6 ; i++) {
			avg[i] /= list.size();
		}
		
		return avg;
	}
	
	public int[] avgSearchRate(Sex sex, controller.Step step) {
		ArrayList<Person> list = this.getPersonSex(sex);
		int[] avg = {0, 0, 0, 0, 0, 0};
		Method m;
		
		if(step == controller.Step.Debut) {
			for(int i = 0 ; i < list.size() ; i++) {
				avg[0] += list.get(i).getLooksFor_1().getAttr();
				avg[1] += list.get(i).getLooksFor_1().getSinc();
				avg[2] += list.get(i).getLooksFor_1().getIntel();
				avg[3] += list.get(i).getLooksFor_1().getFun();
				avg[4] += list.get(i).getLooksFor_1().getAmb();
				avg[5] += list.get(i).getLooksFor_1().getShar();
			}
		} else if(step == controller.Step.Milieu) {
			for(int i = 0 ; i < list.size() ; i++) {
				avg[0] += list.get(i).getLooksFor_s().getAttr();
				avg[1] += list.get(i).getLooksFor_s().getSinc();
				avg[2] += list.get(i).getLooksFor_s().getIntel();
				avg[3] += list.get(i).getLooksFor_s().getFun();
				avg[4] += list.get(i).getLooksFor_s().getAmb();
				avg[5] += list.get(i).getLooksFor_s().getShar();
			}
		} else { // Fin
			for(int i = 0 ; i < list.size() ; i++) {
				avg[0] += list.get(i).getLooksFor_2().getAttr();
				avg[1] += list.get(i).getLooksFor_2().getSinc();
				avg[2] += list.get(i).getLooksFor_2().getIntel();
				avg[3] += list.get(i).getLooksFor_2().getFun();
				avg[4] += list.get(i).getLooksFor_2().getAmb();
				avg[5] += list.get(i).getLooksFor_2().getShar();
			}
		}
		
		for(int i = 0 ; i < 6 ; i++) {
			avg[i] /= list.size();
		}
		
		return avg;
	}
	
	/*
	 * TODO : demander à Martin comment on trouve satis_2 (cf p.10 du pdf)
	 */
	public int avgSatisfactionRate(int age, Sex sex) {
		ArrayList<Person> list = this.getPersonAgeSex(age, sex);
		int sum = 0;
		
		for(int i = 0 ; i < list.size() ; i++) {
			sum += list.get(i).getSatis_2();
		}
		
		return sum / list.size();
	}
	
	public HashMap<Integer, Integer> peopleByRate(Sex sex, Interest interest) {
		ArrayList<Person> list = this.getPersonSex(sex);
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
		//Setting up hashmap with 0 person fo each rate
		for(int i = 1 ; i <= 10 ; i++) {
			hm.put(i, 0);
		}
		
		for(int i = 0 ; i < list.size() ; i++) {
			//interests = array of rates, interestInt = n° of the interest we are looking for
			int[] interests = list.get(i).getInterests().getInterests();
			int interestInt = interest.getInterestNb();
			int rate = interests[interestInt];
			
			hm.put(rate, hm.get(rate) + 1);
		}
		
		return hm;
		
	}

}
