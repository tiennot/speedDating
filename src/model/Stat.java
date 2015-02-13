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
	
	//Exhaustive list of people in the data set
	private ArrayList<Person> personList ;
	
	//Exhaustive list of dates in the data set
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
		for (Person p: personList){
			if(p.getSex().isEqualTo(sex)){
				list.add(p);
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
	
	public int numberPerson(int age) {
		return this.getPersonAge(age).size();
	}
	
	public double percentRealDaters(int age, Sex sex){
		int nTotal = 0, nRealDater = 0;
		for(Person p: this.getPersonAgeSex(age, sex)){
			if(p.getOnADate()==null) continue;
			if(p.getOnADate()) nRealDater++;
			nTotal++;
		}
		//System.out.println(nTotal + " " + nRealDater);
		return nTotal < 3 ? -1 : (double)nRealDater / (double)nTotal;
	}
	
	public int numberPerson(int age, Sex sex) {
		return this.getPersonAgeSex(age, sex).size();
	}
	
	public int numberOfPersonThatMatched(int age, Sex sex) {
		int n = 0;		
		for(Person p: this.getPersonAgeSex(age, sex)) {
			for(Date d: p.getDates()){
				if(d.match()){
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
		
		return sum / Math.max(list.size(), 1);
	}
	
	public HashMap<Integer, Integer> peopleByRate(Sex sex, Interest interest) {
		ArrayList<Person> list = this.getPersonSex(sex);
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		//Setting up hash map with 0 person for each rate
		for(int i = 0 ; i <= 10 ; i++) {
			hm.put(i, 0);
		}
		
		for(Person p: list) {
			//interests = array of rates, interestInt = number of the interest we are looking for
			int[] interests = p.getInterests().getInterests();
			int interestInt = interest.getInterestNb();
			
			//Sanity check
			int rate = interests[interestInt];
			if(rate>10 || rate<0) continue;
			hm.put(rate, hm.get(rate) + 1);
		}
		
		return hm;
		
	}

	public ArrayList<Person> getPersonList() {
		return personList;
	}

}
