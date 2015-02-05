package model.constants;

public class Goal {
	private byte goal;

	public Goal(byte goal) {
		super();
		this.goal = goal;
	}

	public byte getGoal() {
		return goal;
	}

	public void setGoal(byte goal) {
		this.goal = goal;
	}
	
	//The static Goals
	public static final Goal ForFun = new Goal((byte) 1);
	public static final Goal ToMeet = new Goal((byte) 2);
	public static final Goal GetDate = new Goal((byte) 3);
	public static final Goal SeriousRelation = new Goal((byte) 4);
	public static final Goal DidIt = new Goal((byte) 5);
	public static final Goal OTHER = new Goal((byte) 6);
	
	
}
