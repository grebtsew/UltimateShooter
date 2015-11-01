package Maps;

import java.awt.Point;
import java.util.Comparator;

public class SortPointY implements Comparator<Point> {
	public int compare(Point a, Point b) {
		if (a.y < b.y) {
			return -1;
		} else if (a.y > b.y) {
			return 1;
		} else {
			return 0;
		}
	}
}
