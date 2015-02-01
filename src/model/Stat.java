package model;

import java.util.ArrayList;

import model.constants.Sex;

public class Stat {
	
	private ArrayList<Person> personList ;

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

}
