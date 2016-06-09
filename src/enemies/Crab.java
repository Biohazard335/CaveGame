package enemies;

import main.CaveGame;
import collision.shapes.Circle;
import collision.shapes.Line;
import collision.shapes.Pllgm;
import collision.shapes.Point;
import collision.shapes.Shape;

public class Crab extends Enemy{

	public Crab(Point origin) {
		super(origin, 5/*health*/,"crab",1, new Shape []{new Circle(origin,20,new Point(0,-20)), new Pllgm(origin, 40,20,new Point(-20,-20))},
				new Point(origin.x+20,origin.y), new Point(origin.x-20,origin.y-40));
		this.freefalling=true;
		this.xvelocity=2;
		this.line = new Line(new Point(0,0), new Point(0,0));
		this.visible=false;
	}
	@Override
	public void move(){
		if(Math.abs(this.location.x-CaveGame.player.location.x)<250 && Math.abs(this.location.y-CaveGame.player.location.y)<20){
			this.visible=true;
			if(this.location.x>=CaveGame.player.location.x){
				this.xvelocity=-1;
			}else{
				this.xvelocity=1;
			}
			if(this.line.p1.y!=0){
				this.location.x+=this.xvelocity*Math.cos(this.line.angle);
				if(this.line.m>0){
					this.location.y+=this.xvelocity*Math.sin(this.line.angle);
				}else{
					this.location.y-=this.xvelocity*Math.sin(this.line.angle);		
				}
			}
			this.location.y+=this.yvelocity;
			if(this.freefalling && this.yvelocity<this.terminal){
				this.acceleration=CaveGame.gravity;
				this.yvelocity+=this.acceleration;
			}
			this.displacement.x=this.location.x-this.origin.x;
			this.displacement.y=this.location.y-this.origin.y;
			for(Shape s:this.shapes){
				s.location.x=this.location.x+s.position.x;
				s.location.y=this.location.y+s.position.y;
			}
			this.movement = new Line(this.location, new Point(this.location.x+this.xvelocity,this.location.y+this.yvelocity));		
		}
	}
}