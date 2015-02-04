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
	private ScoreCard hisScoreCard ; 
	private ScoreCard herScoreCard ;
	
	//TODO : Constructeur + Getter/setter 
	public Date(Person man, Person woman, int stationNumber, int orderNumber,
			double intCorr, ScoreCard hisScoreCard, ScoreCard herScoreCard) {
		super();
		this.man = man;
		this.woman = woman;
		this.stationNumber = stationNumber;
		this.orderNumber = orderNumber;
		this.intCorr = intCorr;
		this.hisScoreCard = hisScoreCard;
		this.herScoreCard = herScoreCard;
	}
	
	//Match method
	public boolean match(){
		return hisScoreCard.getDec() && herScoreCard.getDec();
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

	public final ScoreCard getHisScoreCard() {
		return hisScoreCard;
	}

	public final void setHisScoreCard(ScoreCard hisScoreCard) {
		this.hisScoreCard = hisScoreCard;
	}

	public final ScoreCard getHerScoreCard() {
		return herScoreCard;
	}

	public final void setHerScoreCard(ScoreCard herScoreCard) {
		this.herScoreCard = herScoreCard;
	}

	
}
