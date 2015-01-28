package model.constants;

public class Field {
	private byte field;
	//Constructor
	public Field(byte field){
		this.field = field;
	}
	//Static constants
	public static final Field LAW = new Field((byte) 1);
	public static final Field MATH = new Field((byte) 2);
	public static final Field SOCIAL = new Field((byte) 3);
	public static final Field MEDICAL = new Field((byte) 4);
	public static final Field ENGINEERING = new Field((byte) 5);
	public static final Field ENGLISH = new Field((byte) 6);
	public static final Field HISTORY = new Field((byte) 7);
	public static final Field BUSINESS = new Field((byte) 8);
	public static final Field EDUCATION = new Field((byte) 9);
	public static final Field BIOLOGY = new Field((byte) 10);
	public static final Field SOCIAL_WORK = new Field((byte) 11);
	public static final Field UNDERGRAD = new Field((byte) 12);
	public static final Field POLITICAL = new Field((byte) 13);
	public static final Field FILM = new Field((byte) 14);
	public static final Field ART = new Field((byte) 15);
	public static final Field LANGUAGES = new Field((byte) 16);
	public static final Field ARCHITECTURE = new Field((byte) 17);
	public static final Field OTHER = new Field((byte) 18);
}
