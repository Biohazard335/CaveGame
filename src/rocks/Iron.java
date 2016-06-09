package rocks;

import collision.shapes.Point;

public class Iron extends Rock{
	public Iron(Point origin) {
		super(origin,10/*radius*/, 2/*weight*/, 2/*power*/,5/*capacity*/,0/*amount*/,"Iron");
	}
}