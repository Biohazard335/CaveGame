package collision.shapes;

public class Pllgm extends Shape {
	public double height=0.0, width=0.0;

	public Pllgm(Point origin, double width, double height, Point position) {
		super(origin, "pllgm",position);
		this.height=height;
		this.width=width;
//		for(int i=(int) (this.origin.x+this.position.x);i<=(this.origin.x+this.position.x+this.width);i++){
//			this.perimeter.add(new Point(i,(this.origin.y+this.position.y)));
//			this.perimeter.add(new Point(i,(this.origin.y+this.position.y+this.height)));
//		}
//		for(int i=(int)(this.origin.y+this.position.y);i<=(this.origin.y+this.position.y+this.height);i++){
//			this.perimeter.add(new Point((this.origin.x+this.position.x),i));
//			this.perimeter.add(new Point((this.origin.x+this.position.x+this.width),i));
//		}
	}
	
	@Override
	public boolean collision(Point p){
		if( (( (int)p.x == (int)(this.location.x)||(int)p.x ==(int)(location.x+this.width) ) 
				&& (int)p.y>=(int)(this.location.y) &&(int)p.y <=(int)(this.location.y+this.height))
		||( (( (int)p.y == (int)(this.location.y)||(int)p.y ==(int)(location.y+this.height) )) 
				&& (int)p.x>=(int)(this.location.x) &&(int)p.x <=(int)(this.location.x+this.width))){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean collision(Pllgm pllgm){
		if(((this.location.x+this.width>=pllgm.location.x)&&(this.location.x<=pllgm.location.x+pllgm.width))&&	
			((this.location.y+this.height>=pllgm.location.y)&&(this.location.y<=pllgm.location.y+pllgm.height))){
			return true;
		}else{
			return false;
		}
	}

	
	@Override
	public boolean collision(Circle circle){
		if(
			( ((circle.location.x>=this.location.x)&&(circle.location.x<=this.location.x+this.width)) &&
			((circle.location.y+circle.radius>=this.location.y)&&(circle.location.y-circle.radius<=this.location.y+this.height)) ) ||
			( ((circle.location.x+circle.radius>=this.location.x)&&(circle.location.x-circle.radius<=this.location.x+this.width)) &&
			((circle.location.y>=this.location.y)&&(circle.location.y<=this.location.y+this.height)) ) ||
			(circle.location.distance(new Point(this.location.x,this.location.y))<=circle.radius)||
			(circle.location.distance(new Point(this.location.x+this.width,this.location.y))<=circle.radius)||
			(circle.location.distance(new Point(this.location.x+this.width,this.location.y+this.height))<=circle.radius)||
			(circle.location.distance(new Point(this.location.x,this.location.y+this.height))<=circle.radius)
			){
			return true;
		}else{
			return false;
		}
	}
}