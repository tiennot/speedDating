package model;

import java.util.ArrayList;

import model.constants.Sex;

public class Stat {
	
	//Liste exhaustive des personnes presentes dans le dataset
	private ArrayList<Person> personList ;
	
	//Liste exhaustive des Dates effectue dans le dataset
	private ArrayList<Date> dateList;

	public Stat(ArrayList<Person> personList) {
		this.personList = personList;
	}
	
	/**
	 * 
	 * @param age
	 * @return liste des personnes qui ont l'age donné en paramètre
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
	 * @return liste des personnes qui ont l'age et le sex demandé
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

}
