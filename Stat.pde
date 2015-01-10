static class Stat {
  //Count nb of persons and men/women
  static int nbWomen = 0;
  static int nbMen = 0;
  public static int nbPersons(){
    return nbWomen + nbMen;
  }
  //Keep track of ages
  static int ageWoman = 0, ageMen = 0;
  public static float avgAgeWomen(){
    return float(ageWoman)/float(max(nbWomen, 1));
  }
  public static float avgAgeMen(){
    return float(ageMen)/float(max(nbMen, 1));
  }
  public static float avgAge(){
    return float(ageWoman+ageMen)/float(max(nbPersons(), 1));
  }
  //Max and min age difference (age of man - age of woman)
  static int minAgeDiff = 0;
  static int maxAgeDiff = 0;
}
