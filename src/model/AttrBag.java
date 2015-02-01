package model;

/*
 * AttrBag is a class meant to describe a set of ratings for the 6 attributes:
 * attractive, sincere, intelligent, fun, ambitious & sharing
 */

public class AttrBag {
	//The average rating used to convert
	public static double meanRating = 8.5;
	//The six attributes ratings (on 10);
	private int attr; //Attractive
	private int sinc; //Sincere
	private int intel; //Intelligent
	private int fun; //Fun
	private int amb; // Ambitious
	private int shar; //Shared interests
	
	
	/**
	 * Empty Constructor, to fill the fields after instanciating the Object
	 */
	public AttrBag() {
		super();
	}

	//Constructor (with ratings on 10)
	public AttrBag(int attr, int sinc, int intel, int fun, int amb, int shar){
		this(attr, sinc, intel, fun, amb, shar, false);
	}
	
	//Constructor (with ratings on 100 if boolean is true)
	public AttrBag(int attr, int sinc, int intel, int fun, int amb, int shar, boolean on100){
		if(on100 || attr>10 || sinc>10 || intel>10 || fun>10 || amb>10 || shar>10){
			this.attr = (int) (meanRating*(((double)attr) /100.));
			this.sinc = (int) (meanRating*(((double)sinc) /100.));
			this.intel = (int) (meanRating*(((double)intel) /100.));
			this.fun = (int) (meanRating*(((double)fun) /100.));
			this.amb = (int) (meanRating*(((double)amb) /100.));
			this.shar = (int) (meanRating*(((double)shar) /100.));
		}else{
			this.attr = attr;
			this.sinc = sinc;
			this.intel = intel;
			this.fun = fun;
			this.amb = amb;
			this.shar = shar;
		}
	}

	/**
	 * 
	 * @param anotherOne Autre paquet d'attribut avec lequel on compare celui là. 
	 * @return On applique la fonction de cout sur chaque attribut
	 */
	public AttrBag AttrBagDifference(AttrBag anotherOne){
		return new AttrBag(this.costFunction(this.getAttr(),anotherOne.getAttr()),
				this.costFunction(this.getSinc(),anotherOne.getSinc()),
				this.costFunction(this.getIntel(),anotherOne.getIntel()),
				this.costFunction(this.getFun(),anotherOne.getFun()),
				this.costFunction(this.getFun(),anotherOne.getFun()),
				this.costFunction(this.getShar(),anotherOne.getShar())) ;
	}
	
	/**
	 * 
	 * @return C'est juste une fonction de cout. (Exemple, valeur absolue, norme 2, etc ... )
	 */
	public int costFunction(int a, int b){
		return Math.abs(a-b) ;
	}
	
	public int getAttr() {
		return attr;
	}

	public void setAttr(int attr) {
		this.attr = attr;
	}

	public int getSinc() {
		return sinc;
	}

	public void setSinc(int sinc) {
		this.sinc = sinc;
	}

	public int getIntel() {
		return intel;
	}

	public void setIntel(int intel) {
		this.intel = intel;
	}

	public int getFun() {
		return fun;
	}

	public void setFun(int fun) {
		this.fun = fun;
	}

	public int getAmb() {
		return amb;
	}

	public void setAmb(int amb) {
		this.amb = amb;
	}

	public int getShar() {
		return shar;
	}

	public void setShar(int shar) {
		this.shar = shar;
	}
	
	
}
