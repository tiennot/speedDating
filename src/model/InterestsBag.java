package model;

/*
 * Represents the 17 interests of a person
 */
public class InterestsBag {
	//Interests values
	private int[] interests = new int[17];
	
	//Constructor
	public InterestsBag(int sports, int tvsports, int exercise, int dining, int museums, int art,
			int hiking, int gaming, int clubbing, int reading, int tv, int theater, int movies,
			int concerts, int music, int shopping, int yoga){
		interests[0] = sports;
		interests[1] = tvsports;
		interests[2] = exercise;
		interests[3] = dining;
		interests[4] = museums;
		interests[5] = art;
		interests[6] = hiking;
		interests[7] = gaming;
		interests[8] = clubbing;
		interests[9] = reading;
		interests[10] = tv;
		interests[11] = theater;
		interests[12] = movies;
		interests[13] = concerts;
		interests[14] = music;
		interests[15] = shopping;
		interests[16] = yoga;
	}
	
	//Other constructor with an array
	public InterestsBag(int[] interests){
		for(int i=0; i<17; i++){
			this.interests[i] = interests[i];
		}
	}

	//Getters and setters
	public int[] getInterests() {
		return interests;
	}

	public void setInterests(int[] interests) {
		this.interests = interests;
	}

}
