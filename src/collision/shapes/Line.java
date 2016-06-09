package collision.shapes;

import java.util.ArrayList;



public class Line {
	public double m=0.0,b=0.0, angle=0.0;
	public ArrayList<Point> points= new ArrayList<Point>();
	public Point p1, p2;
	
	public Line(Point p1, Point p2){
		this.p1=p1;
		this.p2=p2;
		m=(p2.y-p1.y)/(p2.x-p1.x);
		b=-(m*p1.x)+p1.y;
		for(int i=(int)p1.x;i<=(int)p2.x;i++){
			this.points.add(new Point(i,(m*i+b)));
		}
		this.angle=Math.asin((this.p2.y-this.p1.y)/(this.p1.distance(this.p2)));
		if(this.m<0){
			this.angle=-this.angle;
		}
	}
}
