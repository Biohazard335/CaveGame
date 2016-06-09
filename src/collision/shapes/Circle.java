package collision.shapes;



public class Circle extends Shape {
	public double radius;

	public Circle(Point origin, double radius, Point position) {
		super(origin, "circle",position);
		this.origin = origin;
		this.radius = radius;
		this.position = position;
//		for (int i = (int) (-radius-1+origin.x+position.x); i < (int)(radius+1+origin.x+position.x); i++) {
//			for (int u = (int) (-radius-1+origin.y+position.y); u < (int)(radius+1+origin.y+position.y); u++) {
//				if(this.collision(new Point(i,u))){
//					this.perimeter.add(new Point(i,u));
//				}
//			}		
//		}
	}

	public boolean collision(Point p1) {
		if (distance(p1, new Point(origin.x+position.x,origin.y+position.y)) <= radius+.5 
				&& distance(p1, new Point(origin.x+position.x,origin.y+position.y))>=radius-.5) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean collision(Circle c){
		if(this.location.distance(c.location)<=(this.radius+c.radius)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean collision(Pllgm pllgm){
		if(
			( ((this.location.x>=pllgm.location.x)&&(this.location.x<=pllgm.location.x+pllgm.width)) &&
			((this.location.y+this.radius>=pllgm.location.y)&&(this.location.y-this.radius<=pllgm.location.y+pllgm.height)) ) ||
			( ((this.location.x+this.radius>=pllgm.location.x)&&(this.location.x-this.radius<=pllgm.location.x+pllgm.width)) &&
			((this.location.y>=pllgm.location.y)&&(this.location.y<=pllgm.location.y+pllgm.height)) ) ||
			(this.location.distance(new Point(pllgm.location.x,pllgm.location.y))<=this.radius)||
			(this.location.distance(new Point(pllgm.location.x+pllgm.width,pllgm.location.y))<=this.radius)||
			(this.location.distance(new Point(pllgm.location.x+pllgm.width,pllgm.location.y+pllgm.height))<=this.radius)||
			(this.location.distance(new Point(pllgm.location.x,pllgm.location.y+pllgm.height))<=this.radius)
			){
			return true;
		}else{
			return false;
		}
	}
}
