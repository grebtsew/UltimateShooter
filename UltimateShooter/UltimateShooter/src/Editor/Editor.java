package Editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import Enums.Balls;
import Enums.GameMode;
import Game.Game;
import Maps.Map;
import Panels.EditorPanel;

public class Editor extends JComponent implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map map = new Map(10, 10);
	int rectsize = 30;
	Point mouse;
	FileEditor fe = new FileEditor();
	public Balls focusedball;
	ArrayList<Map> automaps = new ArrayList<Map>();
	ArrayList<Map> checkmaps = new ArrayList<Map>();

	public boolean autosolve = false;
	boolean solveable = false;
	EditorPanel editorpanel;

	Balls[] values = Balls.values();
	public boolean fantasy = false;

	public Editor(EditorPanel _editorPanel) {
		editorpanel = _editorPanel;
		addMouseListener(this);
		repaint();
	}

	public void setFantasy(boolean b) {
		fantasy = b;
	}

	public void removeMaps() {
		fe.removeallmaps();
	}

	private Color getColor(int x, int y) {
		Color c = Color.red;
		Balls b = map.getBall(x, y);
		if (b != null) {

			switch (map.getBall(x, y)) {
			case NORMAL:
				c = Color.red;
				break;
			case EMPTY:
				c = Color.white;
				break;
			case EXPLODE:
				c = Color.pink;
				break;
			case SPEED:
				c = Color.yellow;
				break;
			case FREEZE:
				c = Color.blue;
				break;
			}
		}
		return c;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, map.x * rectsize, map.y * rectsize);

		for (int i = 0; i < map.x; i++) {
			for (int j = 0; j < map.y; j++) {

				g.setColor(Color.BLACK);
				g.drawRect(i * rectsize, j * rectsize, rectsize, rectsize);

				if (!map.isEmpty(i, j)) {
					g.setColor(getColor(i, j));
					g.fillOval(i * rectsize, j * rectsize, rectsize, rectsize);

				}
			}
		}
	}

	public Map getMap() {
		return map;
	}

	public void clear() {
		map = new Map(10, 10);
		repaint();
	}

	private Point getBoardPos(Point p) {
		return new Point((int) p.x / rectsize, (int) p.y / rectsize);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// solve map, check if possible true/false and save solvepattern
	public boolean solve() {
		Game g = new Game(GameMode.SOLVER, null, 0, 0);
		Map solvemap = new Map(10, 10);
		this.fixidenticalmap(map, solvemap);
		g.setMap(solvemap);
		solveable = g.solve();
		return solveable;

	}

	public void save() {

		map.name = Integer.toString(fe.getnumber());
		fe.SaveMap(map);
	}

	public void open() {
		Map m = fe.manualopen();
		if (m != null) {
			map = m;
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mouse = getBoardPos(this.getMousePosition());
		if (mouse.x < map.x && mouse.y < map.y) {
			if (map.isEmpty(mouse.x, mouse.y)) {
				map.addball(mouse.x, mouse.y, focusedball);
			} else {
				map.removeball(mouse.x, mouse.y);
			}
			if (autosolve) {
				solveable = solve();

				editorpanel.solveable = solveable;
				editorpanel.changelabel();
			}
		}
		repaint();

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setfocusball(Balls selectedItem) {
		focusedball = selectedItem;

	}

	public ArrayList<Map> getAutoMaps() {
		return automaps;
	}

	public void autocreate(int i, int _level, boolean savemaps) {

		// maptemps
		ArrayList<Map> maptemp = new ArrayList<Map>();

		boolean dontadd = false;
		// create maps
		int mapcount = i;
		int level = 0;

		while (mapcount > 0) {
			level = _level;
			map = new Map(10, 10);

			while (level > 0) {

				int addx = (int) Math.round((Math.random() * 9));
				int addy = (int) Math.round((Math.random() * 9));

				Balls addball;
				if (fantasy) {
					addball = values[(int) (Math.random() * 5)];
					if (addball == Balls.EMPTY) {
						addball = Balls.NORMAL;
					}
				} else {
					addball = Balls.NORMAL;

				}

				// add a ball
				if (map.isEmpty(addx, addy)) {
					map.addball(addx, addy, addball);

					// set new map

					// check if solveable
					// if not, remove and loop without count
					if (solve()) {
						level--;
						// map.printMap(map);
					} else {
						map.removeball(addx, addy);
					}
				}

			}

			for (Map m : maptemp) {
				if (m.sameMap(m, map)) {
					dontadd = true;
					break;
				}
			}

			// check if the map is already created
			if (maptemp.size() == 0 || dontadd) {
				Map map2 = new Map(10, 10);
				fixidenticalmap(map, map2);
				maptemp.add(map);
				mapcount--;
			}
		}

		automaps = maptemp;

		if (savemaps) {
			saveall(maptemp);
		}
		
		/*
		 * for (Map m : maptemp) { m.printMap(m); }
		 */
		
		System.out.println("Successfully created " + i + " maps in level "
				+ _level + ".");

		map = new Map(10, 10);
		repaint();

	}

	private void saveall(ArrayList<Map> ma) {
		for (Map m : ma) {
			m.name = Integer.toString(fe.getnumber());
			fe.SaveMap(m);
		}
	}

	private void fixidenticalmap(Map inmap, Map outmap) {
		for (int i = 0; i < inmap.x; i++) {
			for (int j = 0; j < inmap.y; j++) {
				outmap.addball(i, j, inmap.getBall(i, j));
			}
		}
	}

}
