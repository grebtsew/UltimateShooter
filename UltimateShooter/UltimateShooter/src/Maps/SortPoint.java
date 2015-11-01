package Maps;

import java.awt.Point;
import java.util.Comparator;

public class SortPoint implements Comparator<Point> {
	public int compare(Point a, Point b) {
		if (a.x < b.x) {
			return -1;
		} else if (a.x > b.x) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
}