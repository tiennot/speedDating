package model.constants;

public class Sex {
	boolean sex;
	//Constructor
	public Sex(boolean sex){
		this.sex = sex;
	}
	//Static constants
	public static final Sex MALE = new Sex(false);
	public static final Sex FEMALE = new Sex(true);
}
