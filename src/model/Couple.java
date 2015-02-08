package model;

/**
 * This class is used as a key of the HashMap of the Dates,
 * @author Martin
 *
 */
public class Couple {
	private int iidMan;
	private int iidWomen;
	
	public Couple(int iidMan, int iidWomen) {
		super();
		this.iidMan = iidMan;
		this.iidWomen = iidWomen;
	}
	
	//Getters Setters
	public int getIidMan() {
		return iidMan;
	}
	public void setIidMan(int iidMan) {
		this.iidMan = iidMan;
	}
	public int getIidWomen() {
		return iidWomen;
	}
	public void setIidWomen(int iidWomen) {
		this.iidWomen = iidWomen;
	}
	
	

}
