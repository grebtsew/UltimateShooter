package Editor;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Enums.Balls;
import HighScore.HSComp;
import Maps.Map;
import Start.Start;

public class FileEditor {

	int mapnumber = 0;

	String oldpath;
	Balls[][] ball;
	ArrayList<Point> ballList;
	String path;

	String[] normalhsNames;
	int[] normalhsnumber;
	String[] timehsNames;
	int[] timehsnumber;

	public FileEditor() {
		getSavePath();
		getmapnumber();
	}

	private void getSavePath() {
		CodeSource codeSource = Start.class.getProtectionDomain()
				.getCodeSource();
		File jarFile;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
			String jarDir = jarFile.getParentFile().getPath();

			oldpath = jarDir;
			path = jarDir + "/UltimateShooterData/Maps/";
			File f = new File(path);
			if (f.exists() && !f.isDirectory()) {

			} else {
				f.mkdirs();
			}

			File f1 = new File(oldpath + "/UltimateShooterData/HighScore/");
			if (f1.exists() && !f1.isDirectory()) {

			} else {
				f1.mkdirs();
			}

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("no integer");
			return false;

		}
		// only got here if we didn't return false
		return true;
	}

	private void getmapnumber() {

		if (mapnumber == 0) {
			File f = new File(path + "MapNumber" + ".ser");
			if (f.exists() && !f.isDirectory()) {

				try {
					FileInputStream fileIn = new FileInputStream(path
							+ "MapNumber" + ".ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					mapnumber = (int) in.readObject();
					in.close();
					fileIn.close();
				} catch (IOException i) {

					i.printStackTrace();
					return;
				} catch (ClassNotFoundException c) {
					System.out.println("Map class not found");
					c.printStackTrace();
					return;
				}
			} else {
				try {

					FileOutputStream fileOut = new FileOutputStream(path
							+ "MapNumber" + ".ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(mapnumber);
					out.close();
					fileOut.close();
					System.out.printf("Serialized data is saved");
				} catch (IOException i) {
					i.printStackTrace();
				}
			}
		}
	}

	private void editnumber() {

		mapnumber++;

		try {
			FileOutputStream fileOut = new FileOutputStream(path + "MapNumber"
					+ ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(mapnumber);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public int getnumber() {
		return mapnumber;
	}

	@SuppressWarnings("unchecked")
	public Map openMap(String name) {
		Balls[][] ball;
		ArrayList<Point> ballList;

		File f = new File(path + name + ".ser");
		if (f.exists() && !f.isDirectory()) {

			try {
				FileInputStream fileIn = new FileInputStream(path + name
						+ ".ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				ball = (Balls[][]) in.readObject();
				ballList = (ArrayList<Point>) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				i.printStackTrace();
				return null;
			} catch (ClassNotFoundException c) {
				System.out.println("Map class not found");
				c.printStackTrace();
				return null;
			}

			Map map = new Map(10, 10);
			map.setList(ball, ballList);
			return map;
		}
		return null;
	}

	public void SaveMap(Map map) {

		try {
			FileOutputStream fileOut = new FileOutputStream(path + map.name
					+ ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(map.getMap());
			out.writeObject(map.getBallList());
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved ");
		} catch (IOException i) {
			i.printStackTrace();
		}
		editnumber();
	}

	public void removeallmaps() {
		File dir = new File(path);
		for (File file : dir.listFiles()) {
			file.delete();
		}
		mapnumber = 0;
		getmapnumber();

	}

	public Map manualopen() {
		JFileChooser chooser = new JFileChooser(path);

		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ chooser.getSelectedFile().getName());
		}

		if (chooser.getSelectedFile() != null) {

			String tem = chooser
					.getSelectedFile()
					.getName()
					.substring(
							0,
							chooser.getSelectedFile().getName()
									.lastIndexOf('.'));

			if (!isInteger(tem)) {
				return null;
			} else {
				return openMap(tem);
			}
		} else {
			return null;
		}
	}

	public void setHighscore(String[] n, int[] nn, String[] t, int[] tt) {
		// see if file excist

		normalhsNames = n;
		normalhsnumber = nn;
		timehsNames = t;
		timehsnumber = tt;

		try {
			FileOutputStream fileOut = new FileOutputStream(oldpath
					+ "/UltimateShooterData/HighScore/" + "HighScore" + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(normalhsNames);
			out.writeObject(normalhsnumber);
			out.writeObject(timehsNames);
			out.writeObject(timehsnumber);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved ");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void getHighscore(HSComp h) {

		// see if file excist
		File f = new File(oldpath + "/UltimateShooterData/HighScore/"
				+ "HighScore" + ".ser");
		if (f.exists() && !f.isDirectory()) {

			try {
				FileInputStream fileIn = new FileInputStream(oldpath
						+ "/UltimateShooterData/HighScore/" + "HighScore"
						+ ".ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				normalhsNames = (String[]) in.readObject();
				normalhsnumber = (int[]) in.readObject();
				timehsNames = (String[]) in.readObject();
				timehsnumber = (int[]) in.readObject();

				in.close();
				fileIn.close();
			} catch (IOException i) {

				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println("Map class not found");
				c.printStackTrace();
				return;
			}
			h.setHighscores(normalhsNames, normalhsnumber, timehsNames,
					timehsnumber);
		} else {
			try {

				FileOutputStream fileOut = new FileOutputStream(oldpath
						+ "/UltimateShooterData/HighScore/" + "HighScore"
						+ ".ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(normalhsNames);
				out.writeObject(normalhsnumber);
				out.writeObject(timehsNames);
				out.writeObject(timehsnumber);

				out.close();
				fileOut.close();
				System.out.printf("Serialized data is saved");
			} catch (IOException i) {
				i.printStackTrace();
			}

		}

	}
}
