package model.constants;

public class Frequency {
	private byte freq;
	
	public Frequency(byte freq) {
		super();
		this.freq = freq;
	}

	public byte getFreq() {
		return freq;
	}

	public void setFreq(byte freq) {
		this.freq = freq;
	}
	
	public static final Frequency SeveralWeek = new Frequency((byte) 1);
	public static final Frequency TwiceWeek = new Frequency((byte) 2);
	public static final Frequency OnceWeek = new Frequency((byte) 3);
	public static final Frequency TwiceMonth = new Frequency((byte) 4);
	public static final Frequency OnceMonth = new Frequency((byte) 5);
	public static final Frequency SeveralYear = new Frequency((byte) 6);
	public static final Frequency Never = new Frequency((byte) 7);
	
	
}
