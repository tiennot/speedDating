/*
* The Date class represents a date beetwen two persons
*/

class Date {
  Person you;
  Person partner;
  int wave;
  int condtn; //1=limited choice, 2=extensive choice
  int order; //Order in the dates that night (e.g 7 means you met 6 persons before)
  float int_corr; //Correlation between interests (0 to 1)
  boolean dec_o; //Partner's decision
  boolean dec; //Your decision
  float attr; //Your rating of partner's attractiveness out of 10
  float attr_o; //How your partner rates your attractiveness out of 10
  boolean match(){ return dec_o && dec; }
  
  //Constructor from table
  Date(Table table, int rowNumber, Person you, Person partner){
    this.you = you;
    this.partner = partner;
    //Assigns values to attributes
    this.wave = table.getInt(5,rowNumber);
    this.condtn = table.getInt(4, rowNumber);
    this.order = table.getInt(9, rowNumber);
    this.int_corr = table.getFloat(13, rowNumber);
    this.dec_o = table.getInt(23, rowNumber) == 1;
    this.dec = table.getInt(97, rowNumber) == 1;
    this.attr = table.getFloat(98, rowNumber);
    this.attr_o = table.getFloat(24, rowNumber);
    //Updates age diff in Stat
    Stat.minAgeDiff = min(Stat.minAgeDiff, ageDiff());
    Stat.maxAgeDiff = max(Stat.maxAgeDiff, ageDiff());
  }
  
  //Return age difference
  public int ageDiff(){
    return you.gender==1 ? you.age - partner.age : partner.age - you.age;
  }
}
