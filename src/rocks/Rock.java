package rocks;

import main.CaveGame;
import collision.Floor;
import collision.Instance;
import collision.Wall;
import collision.shapes.Circle;
import collision.shapes.Line;
import collision.shapes.Point;
import collision.shapes.Shape;

public class Rock extends Instance{
	
	public  boolean exists=false;
	
	public double weight=0.0, radius=0.0, power=0.0, capacity=0.0, amount=0.0;

	public Rock(Point origin,double radius, double weight, double power, double capacity, double amount,String name) {
		super(origin,  new Shape[] {new Circle(origin, radius, new Point(0,-radius))}, name,
				new Point(origin.x+radius,origin.y), new Point(origin.x-radius,origin.y-2*radius));
		this.weight=weight;
		this.power=power;
		this.capacity = capacity;
		this.amount=amount;
		this.radius=radius;
		this.terminal=10;
	}
	
	@Override
	public void contact(Floor f){
		for(Line l:f.lines){
			if(this.location.x>=l.p1.x&& this.location.x<l.p2.x){
				this.location.x+=this.xvelocity;
				this.location.y=(int)((l.m*(this.location.x+this.xvelocity))+l.b-1);
				this.exists=false;
				this.hitwall=false;
				this.yvelocity=0;
				this.acceleration=0;
				this.freefalling=false;
				this.lnnum=f.lines.indexOf(l);
				this.line= new Line(l.p1,l.p2);
				this.floornum=f.number;
				this.xvelocity=0;
				if(!this.type.equals("Stone")){
					CaveGame.rocks[CaveGame.player.rocknum].amount-=1;
					this.types(this.type);
				}
				if(CaveGame.rocks[CaveGame.player.rocknum].amount==0){
					CaveGame.player.rocknum=0;
				}
				this.location=new Point(0,0);
				this.shapes[0].location= new Point(0,0);
				break;
			}
		}
	}
	
	public void reset(){
		this.exists=false;
		this.hitwall=false;
		this.yvelocity=0;
		this.acceleration=0;
		this.freefalling=false;
		this.location=new Point(0,0);
		this.shapes[0].location= new Point(0,0);
		
	}
	
	public void types(String s){
		if(s.equals("Iron")){
			CaveGame.rooms.get(CaveGame.player.roomnum).rocks.add(new Iron(this.location));
		}if(s.equals("Bronze")){
			CaveGame.rooms.get(CaveGame.player.roomnum).rocks.add(new Bronze(this.location));
		}
	}
	
	@Override
	public void hitwall(Wall w){
		if(((this.posbound.x+this.displacement.x+this.xvelocity>w.x && this.location.x <w.x)
				||(this.negbound.x+this.displacement.x+this.xvelocity<w.x && this.location.x>w.x))
				&& this.negbound.y+this.displacement.y>=w.y1 && this.posbound.y+this.displacement.y<=w.y2 
				&& !w.type.equals("ladder")){
			if((int)(this.location.x)>(int)w.x){                 
				this.location.x=w.x-this.negbound.x+this.origin.x+1;
			}else if((int)(this.location.x)<(int)w.x){
				this.location.x=w.x-this.posbound.x+this.origin.x-1;
			}if(this.freefalling==false){
				this.location.y=this.line.m*this.location.x+this.line.b;
			}
			hitwall=true;
			this.xvelocity=0;
		}
	}
	
	@Override
	public void move(){
		if(!hitwall){
			this.location.x+=this.xvelocity*0.80;
		}
		hitwall=false;
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