package enemies;

import main.CaveGame;
import collision.Exit;
import collision.Floor;
import collision.Instance;
import collision.Wall;
import collision.shapes.Line;
import collision.shapes.Point;
import collision.shapes.Shape;

public class Enemy extends Instance{
	
	public double power=0.0;
	public boolean visible=true;
	public String name="";

	public Enemy(Point origin, double health, String name, double power,Shape[] shapes, Point posbound,Point negbound) {
		super(origin, shapes, "enemy",posbound,negbound);
		this.health=health;
		this.power=power;
		this.name=name;
	}
	
	@Override
	public void move(){
		if(this.line.p1.y!=0){
		this.location.x+=this.xvelocity*Math.cos(this.line.angle);
		if(this.line.m>0){
			this.location.y+=this.xvelocity*Math.sin(this.line.angle);
		}else{
			this.location.y-=this.xvelocity*Math.sin(this.line.angle);		
		}}
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
	
	@Override
	public void hitexit(Exit w){
		hitwall(w);
	}
	
	@Override
	public void switchlines(Floor f){
		if((int)(this.location.x+(this.xvelocity*Math.cos(this.line.angle)))>(int)(this.line.p2.x) && !this.freefalling){
			if(f.lines.size()>this.lnnum+1){
				this.location.x=this.line.p2.x;
				this.location.y=this.line.p2.y-1;
				this.lnnum+=1;
				this.line = new Line(f.lines.get(this.lnnum).p1,f.lines.get(lnnum).p2);
			}else if((int)(this.negbound.x+this.displacement.x+(this.xvelocity*Math.cos(this.line.angle)))
					>(int)(this.line.p2.x) ){
				this.xvelocity=-this.xvelocity;
			}
		}else if((int)(this.location.x+(this.xvelocity+Math.cos(this.line.angle)))<(int)(this.line.p1.x)
				&& !this.freefalling){
			if(lnnum-1>=0){
				this.location.x=this.line.p1.x;
				this.location.y=this.line.p1.y-1;
				this.lnnum-=1;
				this.line = new Line(f.lines.get(this.lnnum).p1,f.lines.get(lnnum).p2);
			}else if((int)(this.posbound.x+this.displacement.x+(this.xvelocity*Math.cos(this.line.angle)))
					<(int)(this.line.p1.x) ){
				this.xvelocity=-this.xvelocity;
			}	
		}
	}
	
	@Override
	public void hitwall(Wall w){
		if(((this.posbound.x+this.displacement.x+this.xvelocity>w.x && this.location.x <w.x)
				||(this.negbound.x+this.displacement.x+this.xvelocity<w.x && this.location.x>w.x))
				&& this.negbound.y+this.displacement.y>=w.y1 && this.posbound.y+this.displacement.y<=w.y2 ){
			if((int)(this.location.x)>(int)w.x){                 
				this.location.x=w.x-this.negbound.x+this.origin.x+1;
			}else if((int)(this.location.x)<(int)w.x){
				this.location.x=w.x-this.posbound.x+this.origin.x-1;
			}if(this.freefalling==false){
				this.location.y=this.line.m*this.location.x+this.line.b-1;
			}
			this.xvelocity=-this.xvelocity;
		}
	}
	

}
