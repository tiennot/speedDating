package model;

/*
 * AttrBag is a class meant to describe a set of ratings for the 6 attributes:
 * attractive, sincere, intelligent, fun, ambitious & sharing
 */

public class AttrBag {
	//The average rating used to convert
	public static double meanRating = 8.5;
	//The six attributes ratings (on 10);
	private int attr;
	private int sinc;
	private int intel;
	private int fun;
	private int amb;
	private int shar;
	
	//Constructor (with ratings on 10)
	AttrBag(int attr, int sinc, int intel, int fun, int amb, int shar){
		this(attr, sinc, intel, fun, amb, shar, false);
	}
	
	//Constructor (with ratings on 100 if boolean is true)
	AttrBag(int attr, int sinc, int intel, int fun, int amb, int shar, boolean on100){
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
}
