package rocks;

import collision.shapes.Point;

public class Bronze extends Rock{
	public Bronze(Point origin) {
		super(origin,6/*radius*/, 0.5/*weight*/, 1/*power*/,5/*capacity*/,0/*amount*/,"Bronze");
	}
}