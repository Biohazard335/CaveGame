package collision;

import java.util.ArrayList;

import collision.shapes.Point;

public class Wall {
	public ArrayList<Point> points = new ArrayList<Point>();
	public double x=0.0, y1=0.0, y2=0.0;
	public String type="wall";
	
	public Wall(double x, double y1, double y2){
		this.x=x;
		this.y1=y1;
		this.y2=y2;
		for(int i=(int)y1; i<=(int)y2; i++){
			this.points.add(new Point(x,i));
		}
	}

}
