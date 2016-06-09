package collision.shapes;

public class Shape {
	public Point origin = new Point(0, 0);
	public Point location = new Point(0,0);
	public Point position = new Point(0,0);
	public String type = "";

	public Shape(Point origin, String type, Point position) {
		this.origin = origin;
		this.location.x = origin.x+position.x;
		this.location.y = origin.y+position.y;
		this.type = type;
		this.position=position;
	}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	public boolean collision(Point p1) {
		return false;
	}

	
	public boolean collision(Shape s){
		if(s.type.equals("circle")){
			return	this.collision((Circle)s);
		}else if(s.type.equals("pllgm")){
			return this.collision((Pllgm)s);
		}else{
			return false;
		}
	}
	
	public boolean collision(Pllgm pllgm){
		return false;
	}
	
	public boolean collision(Circle circle){
		return false;
	}
}
