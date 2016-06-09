package enemies;

import collision.shapes.Line;
import collision.shapes.Pllgm;
import collision.shapes.Point;
import collision.shapes.Shape;

public class Lizard extends Enemy{

	public Lizard(Point origin) {
		super(origin, 5/*health*/,"lizard",1,new Shape []{new Pllgm(origin, 80,20,new Point(-40,-20))},
				new Point(origin.x+40,origin.y),new Point(origin.x-40,origin.y-20));
		this.freefalling=true;
		this.xvelocity=2;
		this.line = new Line(new Point(0,0), new Point(0,0));
		this.visible=true;
	}
}