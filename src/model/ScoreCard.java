package model;

public class ScoreCard {
	
	private boolean dec; //1 = yes, 0= no
	private AttrBag attrBag;
	private int like ;
	private int prob;
	private boolean met ;  

	//Constructors
	public ScoreCard() {
		super();
	}
	
	public ScoreCard(boolean dec, AttrBag attrBag, int like, int prob, boolean met) {
		super();
		this.dec = dec;
		this.attrBag = attrBag;
		this.like = like;
		this.prob = prob;
		this.met = met;
	}
	
	public static boolean intToBool(int truc){
		if(truc==0){
			return false ;
		}
		else if(truc==1){
			return true;
		}
		else{ //Je sais pas comment thrower une exception :) 
			int a=1;
			int b=0; 
			int c = a/b;
			return false;
			
		}
	}
	
	//Getters Setters
	
	public final boolean getDec() {
		return dec;
	}
	
	public final void setDec(boolean dec) {
		this.dec = dec;
	}
	public final AttrBag getAttrBag() {
		return attrBag;
	}
	public final void setAttrBag(AttrBag attrBag) {
		this.attrBag = attrBag;
	}
	public final int getLike() {
		return like;
	}
	public final void setLike(int like) {
		this.like = like;
	}
	public final int getProb() {
		return prob;
	}
	public final void setProb(int prob) {
		this.prob = prob;
	}
	public final boolean getMet() {
		return met;
	}
	public final void setMet(boolean met) {
		this.met = met;
	}

	
}
