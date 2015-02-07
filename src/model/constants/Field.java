package model.constants;

public class Field {
	private int field;
	//Constructor
	public Field(int field){
		this.setField(field);
	}
	public int getField() {
		return field;
	}
	public void setField(int field) {
		this.field = field;
	}
	//Static constants
	public static final Field LAW = new Field((int) 1);
	public static final Field MATH = new Field((int) 2);
	public static final Field SOCIAL = new Field((int) 3);
	public static final Field MEDICAL = new Field((int) 4);
	public static final Field ENGINEERING = new Field((int) 5);
	public static final Field ENGLISH = new Field((int) 6);
	public static final Field HISTORY = new Field((int) 7);
	public static final Field BUSINESS = new Field((int) 8);
	public static final Field EDUCATION = new Field((int) 9);
	public static final Field BIOLOGY = new Field((int) 10);
	public static final Field SOCIAL_WORK = new Field((int) 11);
	public static final Field UNDERGRAD = new Field((int) 12);
	public static final Field POLITICAL = new Field((int) 13);
	public static final Field FILM = new Field((int) 14);
	public static final Field ART = new Field((int) 15);
	public static final Field LANGUAGES = new Field((int) 16);
	public static final Field ARCHITECTURE = new Field((int) 17);
	public static final Field OTHER = new Field((int) 18);
}
