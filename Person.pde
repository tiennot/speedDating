/*
* The class Person represents an actual person of the speed dating, either male or female
*/

class Person {
  //Basics
  int iid, id, idg, gender, age;
  //How often do you go out
  int go_out; // 1=several times a week, 2=twice a week, 3=once a week, 4=twice a month, 5=once a month, 6=several times a year, 7=almost never
  //
  int[] interests = new int[17];
  //An arrayList of Date to describe his/her dates
  ArrayList<Date> dates = new ArrayList<Date>();

  //Constructor from table
  Person(Table table, int rowNumber){
    this.iid = iid;
    this.id = table.getInt(1,rowNumber);
    this.gender = table.getInt(2,rowNumber);
    this.idg = table.getInt(3,rowNumber);
    this.age = table.getInt(33,rowNumber);
    //Adds the 1-10 ranking for the 17 interests
    for(int i=0; i<17; i++) interests[i] = min(table.getInt(50+i,rowNumber),10);
    //According to gender we increment men/women counter
    if(this.gender==0){
      Stat.nbWomen++;
      Stat.ageWoman += this.age;
    }
    else{
      Stat.nbMen++;
      Stat.ageMen += this.age;
    }    
  }
  
  //Returns number of matches
  public int nbMatches(){
    int nb = 0;
    for(int i=0; i<dates.size(); i++) nb += dates.get(i).match() ? 1 : 0;
    return nb;
  }
  
  //Returns number of dates
  public int nbDates(){
    return dates.size();
  }
  
  //
}


