package model;

import java.util.ArrayList;

import model.constants.*;

/*
 * The Person class represents a man/woman attending to the speed dating
 * night. The iid attribute is a unique identifier that we use in our whole
 * program to identify someone.
 * 
 */
public class Person {
	//Unique identifier
	private int iid;
	//Characteristics of the wave, this person was in : 
	private int totalNumberPeopleMet;
	//Characteristics of the person
	private int positin1 ;
	private int wave;
	private int age;
	private Sex sex; 
	private Race race;
	private Field field;
	private int mnSAT;
	private int imprace, imprelig, exphappy;
	
	private Goal goal;
	private Frequency date, goOut;
	private Career career;
	//Interests in 17 activities
	private InterestsBag interests;
	//Dates this person has had during the night
	ArrayList<Date> dates;
	//His/her ratings before the night
	private int expectedNumber;
	private AttrBag looksFor_1;
	private AttrBag fellowLooksFor_1;
	private AttrBag oppSexLooksFor_1;
	private AttrBag measureUp_1;
	private AttrBag otherPerceivesYou_1;
	//His/her ratings halfway through the night
	private AttrBag looksFor_s;
	private AttrBag measureUp_s;
	
	//His/her ratings the day after
	private int estimNumberOfMatch;
	private int rateLength;
	private int rateNumDates;
	private AttrBag importance;
	private AttrBag looksFor_2;
	private AttrBag fellowLooksFor_2;
	private AttrBag oppSexLooksFor_2;
	private AttrBag measureUp_2;
	private AttrBag otherPerceivesYou_2;
	private int		satis_2;
	
	//Time 3: a week later 
	private int numDate;
	private int numIn3;
	private int youCall;
	private int themCall;
	private Boolean onADate;
	private AttrBag looksFor_3;
	private AttrBag importance_3;
	private AttrBag fellowLooksFor_3;
	private AttrBag oppSexLooksFor_3;
	private AttrBag measureUp_3;
	private AttrBag otherPerceivesYou_3;
	
	//Adds a date to the ArrayList of dates
	public void addDate(Date date){
		this.dates.add(date);
	}
	
	/**
	 * 
	 * @return On renvoie juste un ensemble d'attribut qui montre comment chaque chose a evolue. 
	 * On peut comparer n'importe quels ensemble d'attributs comme cela. 
	 */
	public AttrBag looksForChange1_s(){
		return looksFor_1.AttrBagDifference(looksFor_s);
	}
	
	
	/**
	 * Useful if we don't have enough information on a Person (because of the dataset)
	 * @param iid
	 */
	public Person(int iid) {
		super();
		this.iid = iid;
		this.dates = new ArrayList<Date>();
	}
	

	/**
	 * Constructeur de base
	 * @param iid
	 * @param wave
	 * @param age
	 * @param sex
	 * @param race
	 * @param field
	 * @param mnSAT
	 * @param imprace
	 * @param imprelig
	 * @param exphappy
	 * @param goal
	 * @param date
	 * @param goOut
	 * @param interests
	 * @param looksFor_1
	 * @param fellowLooksFor_1
	 * @param oppSexLooksFor_1
	 * @param measureUp_1
	 * @param otherPerceivesYou_1
	 * @param looksFor_s
	 * @param measureUp_s
	 * @param looksFor_2
	 * @param fellowLooksFor_2
	 * @param oppSexLooksFor_2
	 * @param measureUp_2
	 * @param otherPerceivesYou_2
	 */
	public Person(int iid, int wave, int age, Sex sex, Race race, Field field,
			int mnSAT, int imprace, int imprelig, int exphappy, Goal goal,
			Frequency date, Frequency goOut, InterestsBag interests,
			AttrBag looksFor_1, AttrBag fellowLooksFor_1,
			AttrBag oppSexLooksFor_1, AttrBag measureUp_1,
			AttrBag otherPerceivesYou_1, AttrBag looksFor_s,
			AttrBag measureUp_s, AttrBag looksFor_2, AttrBag fellowLooksFor_2,
			AttrBag oppSexLooksFor_2, AttrBag measureUp_2,
			AttrBag otherPerceivesYou_2,
			int satis_2, int totalNumberPeopleMet) {
		super();
		this.iid = iid;
		this.wave = wave;
		this.age = age;
		this.sex = sex;
		this.race = race;
		this.field = field;
		this.mnSAT = mnSAT;
		this.imprace = imprace;
		this.imprelig = imprelig;
		this.exphappy = exphappy;
		this.goal = goal;
		this.date = date;
		this.goOut = goOut;
		this.interests = interests;
		this.looksFor_1 = looksFor_1;
		this.fellowLooksFor_1 = fellowLooksFor_1;
		this.oppSexLooksFor_1 = oppSexLooksFor_1;
		this.measureUp_1 = measureUp_1;
		this.otherPerceivesYou_1 = otherPerceivesYou_1;
		this.looksFor_s = looksFor_s;
		this.measureUp_s = measureUp_s;
		this.looksFor_2 = looksFor_2;
		this.fellowLooksFor_2 = fellowLooksFor_2;
		this.oppSexLooksFor_2 = oppSexLooksFor_2;
		this.measureUp_2 = measureUp_2;
		this.otherPerceivesYou_2 = otherPerceivesYou_2;
		this.dates = new ArrayList<Date>();
		this.satis_2 = satis_2;
		this.totalNumberPeopleMet = totalNumberPeopleMet;
	}

	//Getters and setters
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getWave() {
		return wave;
	}
	public void setWave(int wave) {
		this.wave = wave;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
		this.race = race;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public ArrayList<Date> getDates() {
		return dates;
	}
	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}
	public AttrBag getLooksFor_1() {
		return looksFor_1;
	}
	public void setLooksFor_1(AttrBag looksFor_1) {
		this.looksFor_1 = looksFor_1;
	}
	public AttrBag getFellowLooksFor_1() {
		return fellowLooksFor_1;
	}
	public void setFellowLooksFor_1(AttrBag fellowLooksFor_1) {
		this.fellowLooksFor_1 = fellowLooksFor_1;
	}
	public AttrBag getOppSexLooksFor_1() {
		return oppSexLooksFor_1;
	}
	public void setOppSexLooksFor_1(AttrBag oppSexLooksFor_1) {
		this.oppSexLooksFor_1 = oppSexLooksFor_1;
	}
	public AttrBag getMeasureUp_1() {
		return measureUp_1;
	}
	public void setMeasureUp_1(AttrBag measureUp_1) {
		this.measureUp_1 = measureUp_1;
	}
	public AttrBag getOtherPerceivesYou_1() {
		return otherPerceivesYou_1;
	}
	public void setOtherPerceivesYou_1(AttrBag otherPerceivesYou_1) {
		this.otherPerceivesYou_1 = otherPerceivesYou_1;
	}
	public AttrBag getLooksFor_s() {
		return looksFor_s;
	}
	public void setLooksFor_s(AttrBag looksFor_s) {
		this.looksFor_s = looksFor_s;
	}
	public AttrBag getMeasureUp_s() {
		return measureUp_s;
	}
	public void setMeasureUp_s(AttrBag measureUp_s) {
		this.measureUp_s = measureUp_s;
	}
	public AttrBag getLooksFor_2() {
		return looksFor_2;
	}
	public void setLooksFor_2(AttrBag looksFor_2) {
		this.looksFor_2 = looksFor_2;
	}
	public AttrBag getFellowLooksFor_2() {
		return fellowLooksFor_2;
	}
	public void setFellowLooksFor_2(AttrBag fellowLooksFor_2) {
		this.fellowLooksFor_2 = fellowLooksFor_2;
	}
	public AttrBag getOppSexLooksFor_2() {
		return oppSexLooksFor_2;
	}
	public void setOppSexLooksFor_2(AttrBag oppSexLooksFor_2) {
		this.oppSexLooksFor_2 = oppSexLooksFor_2;
	}
	public AttrBag getMeasureUp_2() {
		return measureUp_2;
	}
	public void setMeasureUp_2(AttrBag measureUp_2) {
		this.measureUp_2 = measureUp_2;
	}
	public AttrBag getOtherPerceivesYou_2() {
		return otherPerceivesYou_2;
	}
	public void setOtherPerceivesYou_2(AttrBag otherPerceivesYou_2) {
		this.otherPerceivesYou_2 = otherPerceivesYou_2;
	}
	public InterestsBag getInterests() {
		return interests;
	}
	public void setInterests(InterestsBag interests) {
		this.interests = interests;
	}
	
	public int getSatis_2() {
		return satis_2;
	}

	public void setSatis_2(int satis_2) {
		this.satis_2 = satis_2;
	}
	
	public int getPositin1() {
		return positin1;
	}

	public void setPositin1(int positin1) {
		this.positin1 = positin1;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public int getExpectedNumber() {
		return expectedNumber;
	}

	public void setExpectedNumber(int expectedNumber) {
		this.expectedNumber = expectedNumber;
	}

	public int getEstimNumberOfMatch() {
		return estimNumberOfMatch;
	}

	public void setEstimNumberOfMatch(int estimNumberOfMatch) {
		this.estimNumberOfMatch = estimNumberOfMatch;
	}

	public int getRateLength() {
		return rateLength;
	}

	public void setRateLength(int rateLength) {
		this.rateLength = rateLength;
	}

	public int getRateNumDates() {
		return rateNumDates;
	}

	public void setRateNumDates(int rateNumDates) {
		this.rateNumDates = rateNumDates;
	}

	public AttrBag getImportance() {
		return importance;
	}

	public void setImportance(AttrBag importance) {
		this.importance = importance;
	}

	public int getNumDate() {
		return numDate;
	}

	public void setNumDate(int numDate) {
		this.numDate = numDate;
	}

	public int getNumIn3() {
		return numIn3;
	}

	public void setNumIn3(int numIn3) {
		this.numIn3 = numIn3;
	}

	public AttrBag getLooksFor_3() {
		return looksFor_3;
	}

	public void setLooksFor_3(AttrBag looksFor_3) {
		this.looksFor_3 = looksFor_3;
	}

	public AttrBag getFellowLooksFor_3() {
		return fellowLooksFor_3;
	}

	public void setFellowLooksFor_3(AttrBag fellowLooksFor_3) {
		this.fellowLooksFor_3 = fellowLooksFor_3;
	}

	public AttrBag getOppSexLooksFor_3() {
		return oppSexLooksFor_3;
	}

	public void setOppSexLooksFor_3(AttrBag oppSexLooksFor_3) {
		this.oppSexLooksFor_3 = oppSexLooksFor_3;
	}

	public AttrBag getMeasureUp_3() {
		return measureUp_3;
	}

	public void setMeasureUp_3(AttrBag measureUp_3) {
		this.measureUp_3 = measureUp_3;
	}

	public AttrBag getOtherPerceivesYou_3() {
		return otherPerceivesYou_3;
	}

	public void setOtherPerceivesYou_3(AttrBag otherPerceivesYou_3) {
		this.otherPerceivesYou_3 = otherPerceivesYou_3;
	}

	public int getYouCall() {
		return youCall;
	}

	public void setYouCall(int youCall) {
		this.youCall = youCall;
	}

	public int getThemCall() {
		return themCall;
	}

	public void setThemCall(int themCall) {
		this.themCall = themCall;
	}

	public Boolean getOnADate() {
		return onADate;
	}

	public void setOnADate(Boolean onADate) {
		this.onADate = onADate;
	}

	public AttrBag getImportance_3() {
		return importance_3;
	}

	public void setImportance_3(AttrBag importance_3) {
		this.importance_3 = importance_3;
	}
	
	

}
