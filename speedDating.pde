Table table; int maxIid; Person[] persons;

boolean isValid(Table table, int rowNumber){
  return table.getInt(33,rowNumber)>0 && table.getInt(15,rowNumber)>0 && table.getInt(11,rowNumber)-1 > 0 ;
}

/*
* Instanciates objects fro the data
*/
void setup() {
  //Creates a new table from csv file with "," as separator
  table = new Table("SpeedDating.csv", ",", new int[]{});
  //Counts the number of persons in speed dating
  maxIid = max(table.getColumnAsInts(table.getColumnIndex("iid")));  
  //Creates an array of persons
  persons = new Person[maxIid];
  
  //Goes through the table to create instances
  for(int i=0; i < table.getRowCount(); i++){
    int iid = table.getInt(0,i);
    if(persons[iid-1]!=null || !isValid(table, i)){
      continue;
    }
    //Creates new person
    Person p = new Person(table, i);    
    //Assigns to the array
    persons[iid-1] = p;
  }
  
  //Goes through the table to create instances
  for(int i=0; i < table.getRowCount(); i++){
    Person you = persons[table.getInt(0,i)-1];
    if(!isValid(table, i)) continue;
    Person partner = persons[table.getInt(11,i)-1];
    //Creates new Date
    Date d = new Date(table, i, you, partner);    
    you.dates.add(d);
  }
  
  /*
  * Print basics statistics
  */
  println("The average age of participants is: " + round(Stat.avgAge()*100)/100.);
  println("The average age of women is: " + round(Stat.avgAgeWomen()*100)/100.);
  println("The average age of men is: " + round(Stat.avgAgeMen()*100)/100.);
  println("The max age difference between dates is: " + Stat.maxAgeDiff);
  println("The min age difference between dates is: " + Stat.minAgeDiff);
  
  /*
  * Graphs possibilities
  */
  
  //First graph display matches by ageDiff
    //diffAge();
  //Second one display a two dimensionnal gaph with the number of matches according to age
    //matchesAge(22, 30); //People from 22 to 30 years old
  //Third one displays matches according to attractiveness
    matchesAttr();
  //Fourth one displays interests of people
    //displayInterests();
  
}


/*
*  Displays interests of men/women
*/
void displayInterests() {
  //The colors to represent each interest
  int[][] colors = new int[6][];
  colors[0] = new int[]{220,0,0,150};
  colors[1] = new int[]{200,0,150,150};
  colors[2] = new int[]{0,0,200,150};
  colors[3] = new int[]{0,100,0,150};
  colors[4] = new int[]{230,170,0,150};
  colors[5] = new int[]{0,200,220,150};
  //Array with the names of the interests
  String[] labels = new String[]{"Sports", "TV Sports", "Exercice", "Dining", "Museums", "Art", "Hiking", "Gaming", "Clubbing", "Reading", "TV", "Theatre", "Movies", "Concerts", "Music", "Shopping", "Yoga"};
  //The font settings
  PFont tahoma = loadFont("Tahoma-22.vlw");
  textFont(tahoma, 16);
  //Sets up layout
  size(900,650);
  background(255,255,255);
  //Prints the main circle
  int padding = 20;
  int diamGlobal = min(width-padding*2, height-padding*2);
  fill(200,200,200,30);
  stroke(200);
  ellipse(width/2, height/2, diamGlobal, diamGlobal);
  //Tiny circle in the center
  fill(255,255,255);
  ellipse(width/2, height/2, 60,60); line(width/2, height/2-30, width/2, height/2+30);
  fill(200,200,200); textAlign(CENTER, CENTER); text("W  M", width/2, height/2);
  //Arrays to keep track of interests for men and women
  int[][] interestsM = new int[17][11];
  int[][] interestsW = new int[17][11];
  //Goes through the persons
  for(int i=0; i<maxIid; i++){
    if(persons[i]==null) continue;
    //Updates interests count
    if(persons[i].gender == 1){
      for(int j=0; j<17; j++){
        interestsM[j][persons[i].interests[j]]++;
      }
    }else{
      for(int j=0; j<17; j++){
        interestsW[j][persons[i].interests[j]]++;
      }
    }
  }
  //Now displays the data
  //For each couple (interest, 1-10 mark) we draw a circle which size is proportionnal to the amount of people
  for(int i=0; i<17; i++){
    //We draw for men
    fill(colors[i%6][0], colors[i%6][1], colors[i%6][2], colors[i%6][3]);
    int[] O = new int[]{ width/2, height/2 };
    int[] M = new int[]{ width/2 + int(float(diamGlobal/2) * cos(HALF_PI-PI*(float(1+i))/18.)), height/2 - int(float(diamGlobal/2) * sin(HALF_PI-PI*(float(1+i))/18.)) };
    for(int j=0; j<11; j++){
      int x = int(float(O[0])*(1.-float(j+1)/12.) + float(M[0])*(float(j+1)/12.));
      int y = int(float(O[1])*(1.-float(j+1)/12.) + float(M[1])*(float(j+1)/12.));
      int radius = int(float(interestsM[i][j])/1.4);
      noStroke();
      ellipse(x, y, radius, radius);
    }
    //The label & line
    stroke(200); fill(max(0,colors[i%6][0]-30), max(0,colors[i%6][1]-30), max(0,colors[i%6][2]-30));
    line(M[0], M[1], width/2+diamGlobal/2+30, padding+diamGlobal*(i+1)/18);
    textAlign(LEFT, CENTER); text(labels[i], width/2+diamGlobal/2+35 , padding+diamGlobal*(i+1)/18);
    //We draw for woman
    fill(colors[i%6][0], colors[i%6][1], colors[i%6][2], colors[i%6][3]);
    O = new int[]{ width/2, height/2 };
    M = new int[]{ width/2 - int(float(diamGlobal/2) * cos(HALF_PI-PI*(float(1+i))/18.)), height/2 - int(float(diamGlobal/2) * sin(HALF_PI-PI*(float(1+i))/18.)) };
    for(int j=0; j<11; j++){
      int x = int(float(O[0])*(1.-float(j+1)/12.) + float(M[0])*(float(j+1)/12.));
      int y = int(float(O[1])*(1.-float(j+1)/12.) + float(M[1])*(float(j+1)/12.));
      int radius = int(float(interestsW[i][j])/1.4);
      noStroke();
      ellipse(x, y, radius, radius);
    }
    //The label & line
    stroke(200); fill(max(0,colors[i%6][0]-30), max(0,colors[i%6][1]-30), max(0,colors[i%6][2]-30));
    line(M[0], M[1], width/2-diamGlobal/2-30, padding+diamGlobal*(i+1)/18);
    textAlign(RIGHT, CENTER); text(labels[i], width/2-diamGlobal/2-35 , padding+diamGlobal*(i+1)/18);
  }
}




/*
*  Displays matches according to attractiveness rating
*/
void matchesAttr() {
  //Sets up layout
  size(650,650);
  background(255,255,255);
  //Prints the axes
  int padding = 40;
  line(padding, height-padding, width-padding, height-padding); // x axis
  line(padding, height-padding, padding, padding); // y axis
  //And labels
  PFont tahoma = loadFont("Tahoma-22.vlw");
  textAlign(CENTER, CENTER);
  textFont(tahoma, 16);
  fill(0,0,0,200);
  text("W",padding,padding/2);
  text("M",width-padding/2,height-padding);
  for(int i=4; i<11; i++){
    int x = int( float(i-3)/7. * float(width - 2*padding) ) + padding;
    int y = int( (1.-float(i-3)/7.) * float(height - 2*padding) ) + padding;
    text(i, x, height-padding/2);
    text(i, padding/2, y);
  }
  //Arrays
  int[][] matches = new int[11][11];
  int[][] total = new int[11][11];
  //Goes through the persons
  for(int i=0; i<maxIid; i++){
    if(persons[i]==null || persons[i].gender==0) continue; //skips males
    int ageM = persons[i].age;
    //Goes through the dates
    for(Date date: persons[i].dates){
      int ageW = date.partner.age;
      //Gets man's rating by the woman
      int attr = int(date.attr);
      //Gets woman's rating by the man
      int attr_o = int(date.attr_o);
      //adds data to the arrays
      matches[attr][attr_o] += date.match() ? 1 : 0;
      total[attr][attr_o] += 1;
    }
  }
  //Looks for the max ratio
  float maxRatio = 0.;
  for(int i=3; i<11; i++){
    for(int j=3; j<11; j++){
      maxRatio = max( float(matches[i][j])/float(total[i][j]), maxRatio );
    }
  }
  //At this point we've got the whole data
  noStroke();
  for(int i=3; i<11; i++){
    for(int j=3; j<11; j++){
      if(total[i][j] < 20) fill(170,170,170, 100);
      else fill(255,0,0,200); 
      int x = int( float(i-3)/7. * float(width - 2*padding) ) + padding;
      int y = int( (1.-float(j-3)/7.) * float(height - 2*padding) ) + padding;
      int d = int( float(matches[i][j])/float(total[i][j])/maxRatio * float((min(height, width) - 2*padding))/7.);
      ellipse( x, y, d, d);
    }
  }
}


/*
*  Displays the matches number according to age of the two persons
*/
void matchesAge(int begins, int ends) {
  //Sets up layout
  size(650,650);
  background(255,255,255);
  //Prints the axes
  int padding = 40;
  line(padding, height-padding, width-padding, height-padding); // x axis
  line(padding, height-padding, padding, padding); // y axis
  //And labels
  PFont tahoma = loadFont("Tahoma-22.vlw");
  textAlign(CENTER, CENTER);
  textFont(tahoma, 16);
  fill(0,0,0,200);
  text("W",padding,padding/2);
  text("M",width-padding/2,height-padding);
  for(int i=begins; i<ends+1; i++){
    int x = int( (float(i-begins)+0.5)/float(ends-begins+1) * float(width - 2*padding) ) + padding;
    int y = int( (float(ends-i)+0.5)/float(ends-begins+1) * float(height - 2*padding) ) + padding;
    text(i, x, height-padding/2);
    text(i, padding/2, y);
  }
  //Two two-dimensional array to store the data
  int[][] matches = new int[ends-begins+1][ends-begins+1];
  int[][] total = new int[ends-begins+1][ends-begins+1];
  //Goes through the persons
  for(int i=0; i<maxIid; i++){
    if(persons[i]==null || persons[i].gender==0) continue; //Skips males
    int ageM = persons[i].age;
    if(ageM<begins || ageM>ends) continue;
    //Goes through the dates
    for(Date date: persons[i].dates){
      int ageW = date.partner.age;
      if(ageW<begins || ageW>ends || date.condtn==1) continue; //skips limited choice waves 
      //Adds the date to the data
      matches[ageM-begins][ageW-begins] += date.match() ? 1 : 0;
      total[ageM-begins][ageW-begins] += 1;
    }
  }
  //Looks for the max ratio
  float maxRatio = 0.;
  for(int ageM=begins; ageM<=ends; ageM++){
    for(int ageW=begins; ageW<=ends; ageW++){
      maxRatio = max( float(matches[ageM-begins][ageW-begins])/float(total[ageM-begins][ageW-begins]), maxRatio );
    }
  }
  //At this point we've got the whole data
  noStroke();
  for(int ageM=begins; ageM<=ends; ageM++){
    for(int ageW=begins; ageW<=ends; ageW++){
      if(matches[ageM-begins][ageW-begins] < 5) fill(170,170,170, 100);
      else fill(255,0,0,200); 
      int x = int( (float(ageM-begins)+0.5)/float(ends-begins+1) * float(width - 2*padding) ) + padding;
      int y = int( (float(ends-ageW)+0.5)/float(ends-begins+1) * float(height - 2*padding) ) + padding;
      int d = int(float(matches[ageM-begins][ageW-begins])/float(total[ageM-begins][ageW-begins])/maxRatio * float((min(height, width) - 2*padding)/(ends-begins+1)));
      ellipse( x, y, d, d);
    }
  }
}

/*
*  Displays the % of matches by age difference
*/
void diffAge() {
  size(800,500);
  background(255,255,255);
  int[] ageDiffMatches = new int[Stat.maxAgeDiff-Stat.minAgeDiff+1];
  int[] ageDiffTotal = new int[Stat.maxAgeDiff-Stat.minAgeDiff+1];
  for(int i=0; i<maxIid; i++){
    //ignores missing iid and only takes women (avoid to take twice the same date)
    if(persons[i]==null || persons[i].gender==0) continue;
    //
    ArrayList<Date> dates = persons[i].dates;
    for (int j=0; j<dates.size(); j++) {
      ageDiffTotal[dates.get(j).ageDiff()-Stat.minAgeDiff]++;
      if(dates.get(j).match()) ageDiffMatches[dates.get(j).ageDiff()-Stat.minAgeDiff]++;
    }
  }
  //Displays the array
  println("###############");
  for(int i=0; i<Stat.maxAgeDiff-Stat.minAgeDiff+1; i++){
    if(i+1*Stat.minAgeDiff <=10 && i+1*Stat.minAgeDiff >= -10){
      println(ageDiffTotal[i] + " and "+ageDiffMatches[i]+" matches when difference of "+int(i+1*Stat.minAgeDiff));
      float ratio = float(ageDiffMatches[i])/float(ageDiffTotal[i])/0.3;
      int abs = i+1*Stat.minAgeDiff + 10;
      fill(255,0,0,200);
      noStroke();
      ellipse( abs*((width-50)/20)+25, (1-ratio)*(height-50)+25 , 10, 10);
    }
  }
}