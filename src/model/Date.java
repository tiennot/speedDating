package model;

/*
 * The Date class represents a date between two person (a man and a woman)
 * during the speed dating night. A date doesn't need a unique identifier
 * since it is uniquely identified by the two iids.
 */
public class Date {
	//The two persons who date
	private Person man;
	private Person woman;
	private int stationNumber; //Where they met
	private int orderNumber; //Order number of the date. 1 means first date of the night, etc.
	private double intCorr; //Interests correlation -1 to 1
	private boolean hisDec; //Decision of the man
	private boolean herDec; //Decision of the woman
	private int int_corr; //Correlation
	private AttrBag hisScorecard ; 
	private AttrBag herScoreCard ;
	private byte heLikes ; 
	private byte sheLikes ;
	private byte hisProb;
	private byte herProb ;
	private boolean met;
	
	//TODO : Constructeur + Getter/setter 
	
	//Match method
	public boolean match(){
		return hisDec && herDec;
	}

	//Getters and setters
	public Person getMan() {
		return man;
	}

	public void setMan(Person man) {
		this.man = man;
	}

	public Person getWoman() {
		return woman;
	}

	public void setWoman(Person woman) {
		this.woman = woman;
	}

	public int getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getIntCorr() {
		return intCorr;
	}

	public void setIntCorr(double intCorr) {
		this.intCorr = intCorr;
	}

	public boolean isHisDec() {
		return hisDec;
	}

	public void setHisDec(boolean hisDec) {
		this.hisDec = hisDec;
	}

	public boolean isHerDec() {
		return herDec;
	}

	public void setHerDec(boolean herDec) {
		this.herDec = herDec;
	}	
}
