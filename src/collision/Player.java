package collision;

import collision.shapes.*;

public class Player extends Instance{

	public Player(Point origin) {
		super(origin, 
				new Shape[] {new Pllgm(origin,40,80,new Point(-20,-80))
				},
				"player", new Point(origin.x+20,origin.y), new Point(origin.x-20,origin.y-80));
		this.acceleration=5;
		this.freefalling=true;
	}
/*
 * new Circle(origin,12,new Point(0,-67)), new Pllgm(origin,30,20,new Point(-10,-55)),
					new Pllgm(origin,25,10,new Point(-13,-25)),new Pllgm(origin,25,10,new Point(3,-25))
 */
}