package collision;

import java.util.ArrayList;

import collision.shapes.Line;
import collision.shapes.Point;


public class Floor {
	public ArrayList<Point> corners;
	public ArrayList<Point> points= new ArrayList<Point>();
	public ArrayList<Line> lines= new ArrayList<Line>();
	public int number=0;
	public boolean fallable=false;
	
	public Floor(ArrayList<Point> corners, boolean fall){
		this.corners= corners;
		this.fallable=fall;
		for(int i = 0; i<this.corners.size()-1;i++){
			lines.add(new Line(this.corners.get(i),this.corners.get(i+1)));
		}
		
		for(Line l:lines){
			for(Point p:l.points){
				this.points.add(new Point(p.x,p.y));
			}
		}
	}
}
