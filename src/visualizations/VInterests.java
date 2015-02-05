package visualizations;

import processing.core.PApplet;
import view.DetailWindow;

/*
 * The interests visualization class
 */
public class VInterests extends Visualization {
	//Colors for display
	int[] colors = new int[6];
	//Array with the names of the interests
	String[] labels = new String[]{
			"Sports",
			"TV Sports",
			"Exercice",
			"Dining",
			"Museums",
			"Art",
			"Hiking",
			"Gaming",
			"Clubbing",
			"Reading",
			"TV",
			"Theatre",
			"Movies",
			"Concerts",
			"Music",
			"Shopping",
			"Yoga"
	};
	//Arrays with the data
	int[][] interestsM = new int[17][11];
	int[][] interestsW = new int[17][11];
	  
	public VInterests(PApplet p, DetailWindow parent) {
		super(p, parent);
		//Sets the colors pour display
		colors[0] = p.color(220,0,0,150);
		colors[1] = p.color(200,0,150,150);
		colors[2] = p.color(0,0,200,150);
		colors[3] = p.color(0,100,0,150);
		colors[4] = p.color(230,170,0,150);
		colors[5] = p.color(0,200,220,150);
		
		//Temporary assignments for values
		interestsM[0][1] = 5;
		interestsM[0][2] = 10;
		interestsM[0][3] = 17;
		interestsM[0][4] = 14;
		interestsM[0][5] = 22;
		interestsM[0][6] = 23;
		interestsM[0][7] = 50;
		interestsM[0][8] = 40;
		interestsM[0][9] = 47;
		interestsM[0][10] = 46;
		interestsM[1][0] = 0;
		interestsM[1][1] = 42;
		interestsM[1][2] = 38;
		interestsM[1][3] = 23;
		interestsM[1][4] = 24;
		interestsM[1][5] = 27;
		interestsM[1][6] = 19;
		interestsM[1][7] = 34;
		interestsM[1][8] = 27;
		interestsM[1][9] = 22;
		interestsM[1][10] = 18;
		interestsM[2][0] = 0;
		interestsM[2][1] = 6;
		interestsM[2][2] = 21;
		interestsM[2][3] = 20;
		interestsM[2][4] = 22;
		interestsM[2][5] = 34;
		interestsM[2][6] = 32;
		interestsM[2][7] = 41;
		interestsM[2][8] = 48;
		interestsM[2][9] = 31;
		interestsM[2][10] = 19;
		interestsM[3][0] = 0;
		interestsM[3][1] = 2;
		interestsM[3][2] = 2;
		interestsM[3][3] = 6;
		interestsM[3][4] = 8;
		interestsM[3][5] = 26;
		interestsM[3][6] = 31;
		interestsM[3][7] = 54;
		interestsM[3][8] = 61;
		interestsM[3][9] = 49;
		interestsM[3][10] = 35;
		interestsM[4][0] = 1;
		interestsM[4][1] = 3;
		interestsM[4][2] = 3;
		interestsM[4][3] = 19;
		interestsM[4][4] = 25;
		interestsM[4][5] = 35;
		interestsM[4][6] = 34;
		interestsM[4][7] = 58;
		interestsM[4][8] = 47;
		interestsM[4][9] = 32;
		interestsM[4][10] = 17;
		interestsM[5][0] = 1;
		interestsM[5][1] = 6;
		interestsM[5][2] = 10;
		interestsM[5][3] = 28;
		interestsM[5][4] = 27;
		interestsM[5][5] = 36;
		interestsM[5][6] = 30;
		interestsM[5][7] = 42;
		interestsM[5][8] = 50;
		interestsM[5][9] = 21;
		interestsM[5][10] = 23;
		interestsM[6][0] = 1;
		interestsM[6][1] = 18;
		interestsM[6][2] = 16;
		interestsM[6][3] = 33;
		interestsM[6][4] = 27;
		interestsM[6][5] = 38;
		interestsM[6][6] = 34;
		interestsM[6][7] = 35;
		interestsM[6][8] = 35;
		interestsM[6][9] = 21;
		interestsM[6][10] = 16;
		interestsM[7][0] = 0;
		interestsM[7][1] = 44;
		interestsM[7][2] = 36;
		interestsM[7][3] = 35;
		interestsM[7][4] = 22;
		interestsM[7][5] = 43;
		interestsM[7][6] = 31;
		interestsM[7][7] = 32;
		interestsM[7][8] = 14;
		interestsM[7][9] = 11;
		interestsM[7][10] = 6;
		interestsM[8][0] = 1;
		interestsM[8][1] = 25;
		interestsM[8][2] = 13;
		interestsM[8][3] = 27;
		interestsM[8][4] = 26;
		interestsM[8][5] = 29;
		interestsM[8][6] = 36;
		interestsM[8][7] = 37;
		interestsM[8][8] = 48;
		interestsM[8][9] = 28;
		interestsM[8][10] = 4;
		interestsM[9][0] = 0;
		interestsM[9][1] = 1;
		interestsM[9][2] = 6;
		interestsM[9][3] = 10;
		interestsM[9][4] = 7;
		interestsM[9][5] = 25;
		interestsM[9][6] = 32;
		interestsM[9][7] = 43;
		interestsM[9][8] = 55;
		interestsM[9][9] = 61;
		interestsM[9][10] = 34;
		interestsM[10][0] = 0;
		interestsM[10][1] = 32;
		interestsM[10][2] = 30;
		interestsM[10][3] = 27;
		interestsM[10][4] = 21;
		interestsM[10][5] = 43;
		interestsM[10][6] = 42;
		interestsM[10][7] = 32;
		interestsM[10][8] = 29;
		interestsM[10][9] = 9;
		interestsM[10][10] = 9;
		interestsM[11][0] = 1;
		interestsM[11][1] = 9;
		interestsM[11][2] = 7;
		interestsM[11][3] = 18;
		interestsM[11][4] = 26;
		interestsM[11][5] = 47;
		interestsM[11][6] = 42;
		interestsM[11][7] = 54;
		interestsM[11][8] = 34;
		interestsM[11][9] = 24;
		interestsM[11][10] = 12;
		interestsM[12][0] = 1;
		interestsM[12][1] = 0;
		interestsM[12][2] = 3;
		interestsM[12][3] = 5;
		interestsM[12][4] = 8;
		interestsM[12][5] = 12;
		interestsM[12][6] = 30;
		interestsM[12][7] = 52;
		interestsM[12][8] = 64;
		interestsM[12][9] = 61;
		interestsM[12][10] = 38;
		interestsM[13][0] = 1;
		interestsM[13][1] = 2;
		interestsM[13][2] = 12;
		interestsM[13][3] = 17;
		interestsM[13][4] = 19;
		interestsM[13][5] = 30;
		interestsM[13][6] = 43;
		interestsM[13][7] = 50;
		interestsM[13][8] = 45;
		interestsM[13][9] = 28;
		interestsM[13][10] = 27;
		interestsM[14][0] = 0;
		interestsM[14][1] = 2;
		interestsM[14][2] = 2;
		interestsM[14][3] = 2;
		interestsM[14][4] = 6;
		interestsM[14][5] = 24;
		interestsM[14][6] = 30;
		interestsM[14][7] = 46;
		interestsM[14][8] = 58;
		interestsM[14][9] = 48;
		interestsM[14][10] = 56;
		interestsM[15][0] = 0;
		interestsM[15][1] = 23;
		interestsM[15][2] = 50;
		interestsM[15][3] = 27;
		interestsM[15][4] = 28;
		interestsM[15][5] = 39;
		interestsM[15][6] = 33;
		interestsM[15][7] = 31;
		interestsM[15][8] = 23;
		interestsM[15][9] = 10;
		interestsM[15][10] = 10;
		interestsM[16][0] = 2;
		interestsM[16][1] = 70;
		interestsM[16][2] = 47;
		interestsM[16][3] = 34;
		interestsM[16][4] = 18;
		interestsM[16][5] = 31;
		interestsM[16][6] = 24;
		interestsM[16][7] = 20;
		interestsM[16][8] = 11;
		interestsM[16][9] = 5;
		interestsM[16][10] = 12;
		interestsW[0][0] = 1;
		interestsW[0][1] = 19;
		interestsW[0][2] = 20;
		interestsW[0][3] = 29;
		interestsW[0][4] = 22;
		interestsW[0][5] = 36;
		interestsW[0][6] = 23;
		interestsW[0][7] = 31;
		interestsW[0][8] = 45;
		interestsW[0][9] = 21;
		interestsW[0][10] = 22;
		interestsW[1][0] = 1;
		interestsW[1][1] = 56;
		interestsW[1][2] = 41;
		interestsW[1][3] = 32;
		interestsW[1][4] = 26;
		interestsW[1][5] = 29;
		interestsW[1][6] = 21;
		interestsW[1][7] = 27;
		interestsW[1][8] = 22;
		interestsW[1][9] = 8;
		interestsW[1][10] = 6;
		interestsW[2][0] = 1;
		interestsW[2][1] = 15;
		interestsW[2][2] = 8;
		interestsW[2][3] = 16;
		interestsW[2][4] = 16;
		interestsW[2][5] = 34;
		interestsW[2][6] = 39;
		interestsW[2][7] = 39;
		interestsW[2][8] = 43;
		interestsW[2][9] = 30;
		interestsW[2][10] = 28;
		interestsW[3][0] = 1;
		interestsW[3][1] = 1;
		interestsW[3][2] = 1;
		interestsW[3][3] = 0;
		interestsW[3][4] = 3;
		interestsW[3][5] = 16;
		interestsW[3][6] = 15;
		interestsW[3][7] = 43;
		interestsW[3][8] = 67;
		interestsW[3][9] = 58;
		interestsW[3][10] = 64;
		interestsW[4][0] = 1;
		interestsW[4][1] = 1;
		interestsW[4][2] = 3;
		interestsW[4][3] = 9;
		interestsW[4][4] = 7;
		interestsW[4][5] = 23;
		interestsW[4][6] = 25;
		interestsW[4][7] = 56;
		interestsW[4][8] = 58;
		interestsW[4][9] = 48;
		interestsW[4][10] = 38;
		interestsW[5][0] = 1;
		interestsW[5][1] = 1;
		interestsW[5][2] = 6;
		interestsW[5][3] = 12;
		interestsW[5][4] = 6;
		interestsW[5][5] = 29;
		interestsW[5][6] = 31;
		interestsW[5][7] = 47;
		interestsW[5][8] = 61;
		interestsW[5][9] = 38;
		interestsW[5][10] = 37;
		interestsW[6][0] = 1;
		interestsW[6][1] = 12;
		interestsW[6][2] = 26;
		interestsW[6][3] = 26;
		interestsW[6][4] = 16;
		interestsW[6][5] = 24;
		interestsW[6][6] = 36;
		interestsW[6][7] = 41;
		interestsW[6][8] = 41;
		interestsW[6][9] = 27;
		interestsW[6][10] = 19;
		interestsW[7][0] = 3;
		interestsW[7][1] = 90;
		interestsW[7][2] = 43;
		interestsW[7][3] = 31;
		interestsW[7][4] = 25;
		interestsW[7][5] = 27;
		interestsW[7][6] = 17;
		interestsW[7][7] = 15;
		interestsW[7][8] = 13;
		interestsW[7][9] = 3;
		interestsW[7][10] = 2;
		interestsW[8][0] = 1;
		interestsW[8][1] = 24;
		interestsW[8][2] = 8;
		interestsW[8][3] = 20;
		interestsW[8][4] = 16;
		interestsW[8][5] = 31;
		interestsW[8][6] = 41;
		interestsW[8][7] = 51;
		interestsW[8][8] = 37;
		interestsW[8][9] = 36;
		interestsW[8][10] = 4;
		interestsW[9][0] = 1;
		interestsW[9][1] = 0;
		interestsW[9][2] = 4;
		interestsW[9][3] = 5;
		interestsW[9][4] = 8;
		interestsW[9][5] = 15;
		interestsW[9][6] = 22;
		interestsW[9][7] = 41;
		interestsW[9][8] = 50;
		interestsW[9][9] = 64;
		interestsW[9][10] = 59;
		interestsW[10][0] = 1;
		interestsW[10][1] = 23;
		interestsW[10][2] = 14;
		interestsW[10][3] = 17;
		interestsW[10][4] = 30;
		interestsW[10][5] = 30;
		interestsW[10][6] = 40;
		interestsW[10][7] = 38;
		interestsW[10][8] = 40;
		interestsW[10][9] = 22;
		interestsW[10][10] = 14;
		interestsW[11][0] = 1;
		interestsW[11][1] = 3;
		interestsW[11][2] = 6;
		interestsW[11][3] = 10;
		interestsW[11][4] = 10;
		interestsW[11][5] = 15;
		interestsW[11][6] = 22;
		interestsW[11][7] = 46;
		interestsW[11][8] = 54;
		interestsW[11][9] = 56;
		interestsW[11][10] = 46;
		interestsW[12][0] = 1;
		interestsW[12][1] = 0;
		interestsW[12][2] = 1;
		interestsW[12][3] = 4;
		interestsW[12][4] = 4;
		interestsW[12][5] = 12;
		interestsW[12][6] = 9;
		interestsW[12][7] = 48;
		interestsW[12][8] = 66;
		interestsW[12][9] = 65;
		interestsW[12][10] = 59;
		interestsW[13][0] = 1;
		interestsW[13][1] = 2;
		interestsW[13][2] = 3;
		interestsW[13][3] = 10;
		interestsW[13][4] = 14;
		interestsW[13][5] = 26;
		interestsW[13][6] = 38;
		interestsW[13][7] = 47;
		interestsW[13][8] = 49;
		interestsW[13][9] = 46;
		interestsW[13][10] = 33;
		interestsW[14][0] = 1;
		interestsW[14][1] = 1;
		interestsW[14][2] = 0;
		interestsW[14][3] = 1;
		interestsW[14][4] = 8;
		interestsW[14][5] = 15;
		interestsW[14][6] = 18;
		interestsW[14][7] = 50;
		interestsW[14][8] = 52;
		interestsW[14][9] = 61;
		interestsW[14][10] = 62;
		interestsW[15][0] = 1;
		interestsW[15][1] = 10;
		interestsW[15][2] = 11;
		interestsW[15][3] = 13;
		interestsW[15][4] = 22;
		interestsW[15][5] = 34;
		interestsW[15][6] = 32;
		interestsW[15][7] = 45;
		interestsW[15][8] = 34;
		interestsW[15][9] = 38;
		interestsW[15][10] = 29;
		interestsW[16][0] = 1;
		interestsW[16][1] = 33;
		interestsW[16][2] = 27;
		interestsW[16][3] = 32;
		interestsW[16][4] = 28;
		interestsW[16][5] = 23;
		interestsW[16][6] = 30;
		interestsW[16][7] = 36;
		interestsW[16][8] = 24;
		interestsW[16][9] = 23;
		interestsW[16][10] = 12;
	}
	
	@Override
	public void draw(){		
		p.textSize(12);
		int diamGlobal = p.min(w, h);
		p.fill(200,200,200,30);
		p.stroke(200);
		p.ellipse(x+w/2, y+h/2, diamGlobal/2, diamGlobal/2);
		//Tiny circle in the center
		p.fill(255,255,255);
		p.ellipse(x+w/2, y+h/2, 20,20);
		p.line(x+w/2, y+h/2-20, x+w/2, y+h/2+20);
		p.fill(200,200,200);
		p.textAlign(p.CENTER, p.CENTER);
		p.text("W M", x+w/2, y+h/2);
		//For each couple (interest, 1-10 mark) we draw a circle which size is
		//Proportional to the amount of people
		for(int i=0; i<17; i++){
			/*
			 * Draw for men
			 */
			p.fill(colors[i%6]);
			int[] O = new int[]{
				w/2,
				h/2
			};
			int[] M = new int[]{
				w/2 + (int)((float)(diamGlobal/2) * p.cos((float) (p.HALF_PI-p.PI*((1+i))/18.))),
				h/2 - (int)((float)(diamGlobal/2) * p.sin((float)(p.HALF_PI-p.PI*((1+i))/18.)))
			};
			//The label & line
			p.stroke(200);
			p.line(x+M[0], y+M[1], x+w/2+diamGlobal/2+5, y+diamGlobal*(i+1)/18);
			p.textAlign(p.LEFT, p.CENTER);
			p.text(labels[i], x+w/2+diamGlobal/2+10 , y+diamGlobal*(i+1)/18);
			//The circles
			for(int j=0; j<11; j++){
				int xi = (int)((O[0])*(1.- (j+1)/12.) + (float)(M[0])*((j+1)/12.));
				int yi = (int)((O[1])*(1.- (j+1)/12.) + (float)(M[1])*((j+1)/12.));
				int radius = (int)((float)(interestsM[i][j])/3.5);
				p.noStroke();
				p.ellipse(x + xi, y + yi , radius, radius);
			}
			/*
			 * Draws for women
			 */
			p.fill(colors[i%6]);
			O = new int[]{
				w/2,
				h/2
			};
			M = new int[]{
				w/2 - (int)((float)(diamGlobal/2) * p.cos((float) (p.HALF_PI-p.PI*((1+i))/18.))),
				h/2 - (int)((float)(diamGlobal/2) * p.sin((float)(p.HALF_PI-p.PI*((1+i))/18.)))
			};
			//The label & line
			p.stroke(200);
			p.line(x+M[0], y+M[1], x+w/2-diamGlobal/2-5, y+diamGlobal*(i+1)/18);
			p.textAlign(p.RIGHT, p.CENTER);
			p.text(labels[i], x+w/2-diamGlobal/2-10 , y+diamGlobal*(i+1)/18);
			//The circles
			for(int j=0; j<11; j++){
				int xi = (int)((O[0])*(1.- (j+1)/12.) + (float)(M[0])*((j+1)/12.));
				int yi = (int)((O[1])*(1.- (j+1)/12.) + (float)(M[1])*((j+1)/12.));
				int radius = (int)((float)(interestsW[i][j])/3.5);
				p.noStroke();
				p.ellipse(x + xi, y + yi , radius, radius);
			}
		}
	}
}
