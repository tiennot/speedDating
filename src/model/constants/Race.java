package model.constants;

public class Race {
	private int race;
	//Constructor
	public Race(int race){
		this.race = race;
	}
	//The static races
	public static final Race BLACK = new Race((int) 1);
	public static final Race EUROPEAN = new Race((int) 2);
	public static final Race LATINO = new Race((int) 3);
	public static final Race ASIAN = new Race((int) 4);
	public static final Race NATIVE_AMERICAN = new Race((int) 5);
	public static final Race OTHER = new Race((int) 6);	
}
