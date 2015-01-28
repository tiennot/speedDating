package model.constants;

public class Race {
	private byte race;
	//Constructor
	public Race(byte race){
		this.race = race;
	}
	//The static races
	public static final Race BLACK = new Race((byte) 1);
	public static final Race EUROPEAN = new Race((byte) 2);
	public static final Race LATINO = new Race((byte) 3);
	public static final Race ASIAN = new Race((byte) 4);
	public static final Race NATIVE_AMERICAN = new Race((byte) 5);
	public static final Race OTHER = new Race((byte) 6);	
}
