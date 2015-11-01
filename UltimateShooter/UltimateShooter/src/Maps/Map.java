package Maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import Enums.Balls;
import Enums.Directions;

public class Map {

	public String name;
	public int x, y;
	Balls[][] map;
	ArrayList<Point> ballplaces = new ArrayList<Point>();

	public void BallList(ArrayList<Point> _ballplaces){
		ballplaces = _ballplaces;
	}

	public Map(int _x, int _y) {
		x = _x;
		y = _y;

		create_map();
	}
	
	public void printMap(Map m){
		System.out.println();
		for( int i = 0; i < m.y; i++){
			System.out.println();
			for(int j = 0; j < m.x; j++){
				if(m.getBall(j, i) != Balls.EMPTY){
					System.out.print(1);
				} else {
					System.out.print(0);
				}
				
			}
		}
		System.out.println();
	}
	
	
	public void setList(Balls[][] _map, ArrayList<Point> _ballplaces){
		map = _map;
		ballplaces = _ballplaces;
	}
	
	public ArrayList<Point> getBallList() {
		return ballplaces;
	}

	public Balls[][] getMap() {
		return map;
	}

	public Balls getBall(int _x, int _y) {
		return map[_x][_y];
	}

	public int ballsleft() {
		return ballplaces.size();
	}

	private void create_map() {

		map = new Balls[x][y];
	 
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				map[i][j] = Balls.EMPTY;
			}
		}
	}

	public void addball(int x_, int y_, Balls ball) {
		if (ball == Balls.EMPTY) {
			return;
		} else {
			map[x_][y_] = ball;
			ballplaces.add(new Point(x_, y_));
		}
	}

	public void removeball(int _x, int _y) {
		if (map[_x][_y] == Balls.EMPTY) {
			return;
		} else {
			map[_x][_y] = Balls.EMPTY;
			ballplaces.remove(new Point(_x, _y));
		}
	}

	public boolean isEmpty(int _x, int _y) {
		if (map[_x][_y] == Balls.EMPTY) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Point> hitSome(Directions d, Point p) {
		switch (d) {
		case UP:
			return hitOver(p.x, p.y);

		case DOWN:
			return hitUnder(p.x, p.y);

		case LEFT:
			return hitLeft(p.x, p.y);

		case RIGHT:
			return hitRight(p.x, p.y);
		default:
			break;

		}
		return null;

	}

	// check hits
	private ArrayList<Point> hitUnder(int _x, int _y) {
		ArrayList<Point> k = new ArrayList<Point>();
		for (Point p : ballplaces) {
			if (p.x == _x && p.y > _y) {
				k.add(p);
			}
		}
		// sort k on y and reverse it
		Collections.sort(k, new SortPointY());
		if (k.isEmpty()) {
			return null;
		} else {
			return k;
		}
	}

	private ArrayList<Point> hitOver(int _x, int _y) {
		ArrayList<Point> k = new ArrayList<Point>();

		for (Point p : ballplaces) {
			if (p.x == _x && p.y < _y) {
				k.add(p);
			}
		}

		// sort k on y
		Collections.sort(k, new SortPointY());
		k = reversePointArray(k);

		if (k.isEmpty()) {
			return null;
		} else {
			return k;
		}
	}

	private ArrayList<Point> hitLeft(int _x, int _y) {
		ArrayList<Point> k = new ArrayList<Point>();
		for (Point p : ballplaces) {
			if (p.x < _x && p.y == _y) {
				k.add(p);
			}
		}

		// sort k and reverse it
		Collections.sort(k, new SortPoint());

		k = reversePointArray(k);
		if (k.isEmpty()) {
			return null;
		} else {
			return k;
		}
	}

	private ArrayList<Point> hitRight(int _x, int _y) {
		ArrayList<Point> k = new ArrayList<Point>();
		for (Point p : ballplaces) {
			if (p.x > _x && p.y == _y) {
				k.add(p);
			}
		}

		// sort k
		Collections.sort(k, new SortPoint());

		if (k.isEmpty()) {
			return null;
		} else {
			return k;
		}
	}

	private ArrayList<Point> reversePointArray(ArrayList<Point> o) {
		ArrayList<Point> newp = new ArrayList<Point>();
		for (int i = o.size() - 1; i >= 0; i--) {
			newp.add(o.get(i));
		}
		return newp;
	}

	public boolean sameMap(Map m, Map map2) {
		for(int i = 0; i < m.x; i++){
			for(int j = 0; j < m.y; j++){
				if(m.getBall(i, j) != map2.getBall(i, j)){
					return true;
				}
			}
		}
		return false;
	}

	public void setMap(Map map2) {
		for(int i = 0; i < map2.x; i ++){
			for(int j = 0; j < map2.y; j++){
				map[i][j] = map2.getBall(i, j);
			}
		}
		
	}

}