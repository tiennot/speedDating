package model.constants;

public class Frequency {
	private int freq;
	
	public Frequency(int freq) {
		super();
		this.freq = freq;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	public static final Frequency SeveralWeek = new Frequency((int) 1);
	public static final Frequency TwiceWeek = new Frequency((int) 2);
	public static final Frequency OnceWeek = new Frequency((int) 3);
	public static final Frequency TwiceMonth = new Frequency((int) 4);
	public static final Frequency OnceMonth = new Frequency((int) 5);
	public static final Frequency SeveralYear = new Frequency((int) 6);
	public static final Frequency Never = new Frequency((int) 7);
	
	
}
