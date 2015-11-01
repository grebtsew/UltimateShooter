package HighScore;

import java.awt.Graphics;

import javax.swing.JComponent;

import Editor.FileEditor;

public class HSComp extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FileEditor fe = new FileEditor();

	int fontsize = 12;

	public String playername;
	public int playerscore;

	String[] normalhsNames = new String[10];
	int[] normalhsnumber = new int[10];

	String[] timehsNames = new String[10];
	int[] timehsnumber = new int[10];

	String time = new String("TIME-MODE");
	String normal = new String("NORMAL-MODE");

	public HSComp() {
		/*
		 * normalhsNames[0] = "hej"; normalhsnumber[0] = 12; timehsNames[0] =
		 * "jjj"; timehsnumber[0] = 4444;
		 * 
		 * fe.setHighscore(normalhsNames, normalhsnumber, timehsNames,
		 * timehsnumber);
		 */
		this.setSize(50, 50);
		fe.getHighscore(this);

		repaint();
	}

	public void setHighscores(String[] n, int[] nn, String[] t, int[] tt) {
		normalhsNames = n;
		normalhsnumber = nn;

		timehsNames = t;
		timehsnumber = tt;
	}

	// get highscore list and find where to put a new highscore
	public int gettimeHSPlace() {
		fe.getHighscore(this);
		int i = 0;
		for (int t : timehsnumber) {
			i++;
			if (t <= playerscore) {
				return i;
			}
		}
		return 0;

	}

	// put a new highscore
	public void addandsavetimeHS(String ns, int ni, String ts, int ti, int place) {
		fe.getHighscore(this);
		
		//fix highscore
		if(timehsNames[place-1] != null){
			
			
			for(int i = timehsNames.length-1; i >= place-1; i--){
				if(place-1 == i){
					timehsNames[place-1] = playername;
					timehsnumber[place-1] = playerscore;
					break;
				}
				timehsNames[i] = timehsNames[i-1];
				timehsnumber[i] = timehsnumber[i-1];
			}
		} else {
			timehsNames[place-1] = playername;
			timehsnumber[place-1] = playerscore;
		}
		fe.setHighscore(normalhsNames, normalhsnumber, timehsNames, timehsnumber);
	}
	
	public void clearhighscore() {
		fe.setHighscore(new String[10], new int[10], new String[10], new int[10]);
		repaint();
	}

	public void paintComponent(Graphics g) {
		int x = 10;
		int y = 10;
		int k = 1;
		
		// normal
		g.drawString(normal, x, y);
		if (normalhsNames != null) {
			
			for (int i = 0; i < normalhsNames.length; i++) {
				y += fontsize;
				g.drawString(k + " " + normalhsNames[i] + " " + normalhsnumber[i], x, y);
				k++;
			}
		}

		// time

		y += fontsize;
		g.drawString(time, x, y);
		if (timehsNames != null) {

			k = 1;
			for (int i = 0; i < timehsNames.length; i++) {
				y += fontsize;
				g.drawString(k + " " + timehsNames[i] + " " + timehsnumber[i], x, y);
				k++;
			}
		}

	}

}
