package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import model.constants.Interest;
import model.constants.Interests;
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
	 * @return liste des personnes qui ont l'age donn� en param�tre
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
	 * @return liste des personnes qui ont l'age et le sex demand�
	 */
	public ArrayList<Person> getPersonAgeSex(int age, Sex sex){
		ArrayList<Person> list = new ArrayList<Person>();
		for (int i = 0; i< personList.size(); i++){
			if(personList.get(i).getAge()==age && personList.get(i).getSex().equals(sex)){
				list.add(personList.get(i));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param age
	 * @param sex
	 * @return les interets moyens des personnes de l'age et du sex choisi
	 */
	public InterestsBag getPreferences(int age, Sex sex){
		ArrayList<Person> list = this.getPersonAgeSex(age, sex);
		InterestsBag interests = new InterestsBag();
		for (int i=0; i<list.size(); i++){
			interests.addInterestsBag(list.get(i).getInterests()); //On ajoute les interets de tout le monde
		}
		interests.intToDouble(); // On passe en double pour avoir des valeurs exactes quand on divise.
		interests.divise(list.size());
		return interests;
	}
	
	public int avgInterestRate(int age, Sex sex, int interest) {
		int sum = 0;
		int ansNbr = 0;
		int rate;
		ArrayList<Person> list = this.getPersonAgeSex(age, sex);
		
		for (int i = 0 ; i < list.size() ; i++) {
			rate = (int)(list.get(i).getInterests().getInterests())[interest];
			if(rate >= 0) {
				sum += rate;
				ansNbr++;
			}
		}
		
		return sum / ansNbr;
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

}
