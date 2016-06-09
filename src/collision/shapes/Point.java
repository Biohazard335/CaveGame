package collision.shapes;

public class Point {
	public double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distance(Point p1) {
		return Math.sqrt(Math.pow(p1.x - this.x, 2) + Math.pow(p1.y - this.y, 2));
	}

}
