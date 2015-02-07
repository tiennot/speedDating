package model.constants;

public class Goal {
	private int goal;

	public Goal(int goal) {
		super();
		this.goal = goal;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(byte goal) {
		this.goal = goal;
	}
	
	//The static Goals
	public static final Goal ForFun = new Goal((int) 1);
	public static final Goal ToMeet = new Goal((int) 2);
	public static final Goal GetDate = new Goal((int) 3);
	public static final Goal SeriousRelation = new Goal((int) 4);
	public static final Goal DidIt = new Goal((int) 5);
	public static final Goal OTHER = new Goal((int) 6);
	
	
}
