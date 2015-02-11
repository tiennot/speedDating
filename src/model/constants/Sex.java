package model.constants;

public class Sex {
	Boolean sex;
	//Constructor
	public Sex(boolean sex){
		this.sex = sex;
	}
	//Static constants
	public static final Sex MALE = new Sex(true);
	public static final Sex FEMALE = new Sex(false);
	
	@Override
	public String toString() {
		return "Sex [sex=" + sex + "]";
	}
	
	public boolean isEqualTo(Sex sex){
		return this.sex.equals(sex.getSexBol());
	}
	
	public boolean getSexBol(){
		return this.sex;
	}
	
	
}
