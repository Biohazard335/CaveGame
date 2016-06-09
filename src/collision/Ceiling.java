package collision;

import java.util.ArrayList;

import collision.shapes.Point;

public class Ceiling {
	public ArrayList<Point> points = new ArrayList<Point>();
	public double y=0.0, x1=0.0, x2=0.0;
	
	public Ceiling(double y, double x1, double x2){
		this.y=y;
		this.x1=x1;
		this.x2=x2;
		for(int i=(int)x1; i<=(int)x2; i++){
			this.points.add(new Point(i,y));
		}
	}

}
