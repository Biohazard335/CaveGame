package collision;

import collision.shapes.*;
import main.CaveGame;


public class Instance {
	public Point origin = new Point(0,0), location = new Point(0,0), displacement = new Point(0,0), p2= new Point(0,0);
	public Shape[] shapes;
	public boolean freefalling=false;
	public double xvelocity=0.0,yvelocity=0.0, acceleration=0.0, m=0.0,b=0.0, terminal=20, height=0, width=0;
	public Point posbound=new Point(0,0)/*up&right boundaries*/, negbound= new Point(0,0)/*down&left boundaries*/;
	public Line line , movement;
	public int lnnum=0, floornum=0, roomnum=0, hitcounter=0, rocknum=0;
	public boolean hitwall =false, direction=true/*true=right, false=left*/, hit=false,climbing=false,down=false;
	public String type = "empty";
	public Ladder ladder= null;
	
	public double health=10;
	
	public Instance(Point origin, Shape[] shapes, String type, Point posbound, Point negbound){
		this.type = type;
		this.shapes= shapes;
		this.origin= origin;
		this.location.x=this.origin.x;
		this.location.y=this.origin.y;
		this.posbound.x=posbound.x;
		this.posbound.y=posbound.y;
		this.negbound.x=negbound.x;
		this.negbound.y=negbound.y;
		
		for(Shape s: shapes){
			s.origin=this.origin;
			s.location.x=this.location.x+s.position.x;
			s.location.y=this.location.y+s.position.y;
		}
		this.height=Math.abs(this.posbound.y-this.negbound.y);
		this.width=Math.abs(this.posbound.x-this.negbound.x);
	}
	
	public boolean collision(Instance o){	
		for(Shape s1:this.shapes){
			s1.location.x+=this.xvelocity;
			s1.location.y+=this.yvelocity;
			for(Shape s2:o.shapes){
				s2.location.x+=o.xvelocity;
				s2.location.y+=o.yvelocity;
				if(s1.collision(s2)){
					s2.location.x-=o.xvelocity;
					s2.location.y-=o.yvelocity;
					s1.location.x-=this.xvelocity;
					s1.location.y-=this.yvelocity;
					return true;
				}else{
					s2.location.x-=o.xvelocity;
					s2.location.y-=o.yvelocity;
				}
			}
			s1.location.x-=this.xvelocity;
			s1.location.y-=this.yvelocity;
		}
		return false;
	}
	
	public void move(){
		if(!hitwall){
			this.location.x+=this.xvelocity*Math.cos(this.line.angle);
		}
		hitwall=false;
		if(this.line.m>0){
			this.location.y+=this.xvelocity*Math.sin(this.line.angle);
		}else{
			this.location.y-=this.xvelocity*Math.sin(this.line.angle);		
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
	
	public void switchlines(Floor f){
		if((int)(this.location.x+(this.xvelocity*Math.cos(this.line.angle)))>(int)(this.line.p2.x) 
				&& !this.freefalling && !this.climbing){
			if(f.lines.size()-1!=this.lnnum){
				this.location.x=this.line.p2.x;
				this.location.y=this.line.p2.y-1;
				this.lnnum+=1;
				this.line = new Line(f.lines.get(this.lnnum).p1,f.lines.get(lnnum).p2);
			}else if((int)(this.location.x+this.xvelocity*Math.cos(this.line.angle))-20 >(int)(this.line.p2.x) ){
				this.freefalling=true;
			}
		}else if((int)(this.location.x+(this.xvelocity+Math.cos(this.line.angle)))<(int)(this.line.p1.x)
				&& !this.freefalling && !this.climbing){
			if(lnnum!=0){
				this.location.x=this.line.p1.x;
				this.location.y=this.line.p1.y-1;
				this.lnnum-=1;
				this.line = new Line(f.lines.get(this.lnnum).p1,f.lines.get(lnnum).p2);
			}else if((int)(this.location.x+this.xvelocity*Math.cos(this.line.angle))+20<(int)(this.line.p1.x) ){
				this.freefalling=true;
			}	
		}
	}
	
	public void hitwall(Wall w){
		if(w.type.equals("wall") && hitingwall(w)){
			if((int)(this.location.x)>(int)w.x){                 
				this.location.x=w.x-this.negbound.x+this.origin.x+1;
			}else if((int)(this.location.x)<(int)w.x){
				this.location.x=w.x-this.posbound.x+this.origin.x-1;
			}if(this.freefalling==false){
				this.location.y=this.line.m*this.location.x+this.line.b-1;
			}
			hitwall=true;
		}else if((w.type.equals("ladder") || w.type.equals("exitladder"))&& hitingladder((Ladder)w)){
			if((this.down || this.freefalling) && !this.climbing && this.location.y<w.y2-10){
				this.xvelocity=0;
				this.climbing=true;
				this.location.y+=this.yvelocity;
				this.yvelocity=0;
				this.acceleration=0.0;
				this.freefalling=false;
				this.location.x=w.x;
				this.ladder=(Ladder)w;
				this.xvelocity=0;
			}
		}
	}
	
	public boolean hitingwall(Wall w){
		if(((this.posbound.x+this.displacement.x+this.xvelocity>w.x && this.location.x <=w.x)
				||(this.negbound.x+this.displacement.x+this.xvelocity<w.x && this.location.x>w.x))
				&& this.posbound.y+this.displacement.y>=w.y1 && this.negbound.y+this.displacement.y<=w.y2 ){
			return true;
		}else{
			return false;	
		}
	}
	
	public boolean hitingladder(Ladder w){
		if(((this.location.x+this.xvelocity+20>w.x && this.location.x <=w.x)
				||(this.location.x+this.xvelocity-20<w.x && this.location.x>=w.x))
				&& this.posbound.y+this.displacement.y>=w.y1 && this.negbound.y+this.displacement.y<=w.y2 ){
			return true;
		}else{
			return false;	
		}
	}
	
	public void ladder(){
		if(!climbing){
			ladder=null;
		}else if(ladder!=null){
			if (this.location.y>ladder.y2-10){
				this.climbing=false;
				ladder=null;
				this.yvelocity=0;
				this.acceleration=CaveGame.gravity;
				this.freefalling=true;
			}else if (this.location.y+this.yvelocity<ladder.y1+10){
				this.yvelocity=0;
				this.location.y=ladder.y1+10;
			}
			if(ladder!=null && ladder.type.equals("exitladder")){
				ExitLadder w = (ExitLadder)ladder;
				if(w.open){
					if(this.yvelocity<0 && w.up){
						this.roomnum=w.room;
						this.freefalling=true;
						this.floornum=0;
						this.yvelocity=0;
						this.climbing=false;
						this.ladder=null;
						this.location=new Point(CaveGame.rooms.get(w.room).exitladders.get(w.corresp).x,
								CaveGame.rooms.get(w.room).exitladders.get(w.corresp).y2-111);
						//CaveGame.panel.loading=true;
					}else if(this.yvelocity>0 && !w.up){
						this.roomnum=w.room;
						this.freefalling=true;
						this.floornum=0;
						this.yvelocity=0;
						this.climbing=false;
						this.ladder=null;
						this.location=new Point(CaveGame.rooms.get(w.room).exitladders.get(w.corresp).x,
								CaveGame.rooms.get(w.room).exitladders.get(w.corresp).y2-89);
					}
				}
			}
		}
	}
	
	public void hitexit(Exit w){
		if(hitingwall(w)){
			if(w.open){
				this.roomnum=w.room;
				if(this.location.x<w.x){
					this.location=new Point(CaveGame.rooms.get(w.room).exits.get(w.corresp).x+20,
						CaveGame.rooms.get(w.room).exits.get(w.corresp).y2);
				}else{
					this.location=new Point(CaveGame.rooms.get(w.room).exits.get(w.corresp).x-20,
							CaveGame.rooms.get(w.room).exits.get(w.corresp).y2);
				}
				this.freefalling=true;
				this.floornum=0;
				this.yvelocity=0;
				//CaveGame.panel.loading=true;
			}else{
				if((int)(this.location.x)>(int)w.x){                 
					this.location.x=w.x+this.negbound.x-8;
				}else if((int)(this.location.x)<(int)w.x){
					this.location.x=w.x-this.posbound.x+this.origin.x;
				}if(this.freefalling==false){
					this.location.y=this.line.m*this.location.x+this.line.b-1;
				}
				hitwall=true;
			}
		}
	}
	
	public void hitceiling(Ceiling c){
		if(c.x1-20<this.location.x && c.x2+20>this.location.x &&
				this.location.y-this.height>=c.y && this.location.y-this.height+this.yvelocity<=c.y){
			this.location.y=(c.y+(this.height))+1;
			this.yvelocity=1;
		}
	}
	
	public boolean collision(Line l){
		if((int)this.location.x >= (int)l.p1.x-20 && (int) this.location.x <= (int) l.p2.x+20
				&& (int)this.location.y <= (int)(l.m * this.location.x + l.b)
				&& (int)(this.location.y + this.yvelocity) >= (int)(l.m * (this.location.x+this.xvelocity) + l.b)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean collision(Floor f){ 
		if(f.lines.get(0).p1.x-20 < this.location.x + this.xvelocity 
				&& f.lines.get(f.lines.size()-1).p2.x+20 >= this.location.x + this.xvelocity){
			for(Line l:f.lines){
				if(this.collision(l)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void contact(Floor f){
		this.p2= new Point(this.location.x+this.xvelocity,this.location.y+this.yvelocity);
		this.movement= new Line(this.location,this.p2);
		for(Line l:f.lines){
			if(this.location.x>=l.p1.x + (f.lines.indexOf(l)==0?-20:0) && this.location.x<l.p2.x+(f.lines.indexOf(l)==f.lines.size()-1?20:0)){
				this.location.y=(int)((l.m*(this.location.x+this.xvelocity))+l.b-1);
				this.yvelocity=0;
				this.acceleration=0;
				this.freefalling=false;
				this.lnnum=f.lines.indexOf(l);
				this.line= new Line(l.p1,l.p2);
				this.floornum=f.number;
				this.climbing=false;
				break;
			}
		}
	}
	
	public void hit(){
		if(this.hitcounter==0){
			this.hit=false;
		}
		if(this.hitcounter!=0){
			this.hitcounter-=1;
		}
	}
	
	public void changerock(int i){
		while(true){
			this.rocknum+=i;
			if(this.rocknum<0){
				this.rocknum=CaveGame.rocks.length-1;
			}else if(this.rocknum>=CaveGame.rocks.length){
				this.rocknum=0;
			}
			if(CaveGame.rocks[this.rocknum].amount!=0){
				CaveGame.rock=CaveGame.rocks[this.rocknum];
				break;
			}
		}
	}
	
	public void throwrock(){
		if(!CaveGame.rock.exists &&(CaveGame.rocks[this.rocknum].amount!=0 || CaveGame.rocks[this.rocknum].amount==-1)){
			CaveGame.rock.location=new Point(this.location.x,this.location.y-55);
			CaveGame.rock=CaveGame.rocks[CaveGame.player.rocknum];
			CaveGame.rock.displacement=new Point(0,0);
			CaveGame.rock.line=CaveGame.player.line;
			CaveGame.rock.freefalling=true;
			CaveGame.rock.yvelocity=(-CaveGame.gravity*10+this.yvelocity/2)/Math.pow(CaveGame.rock.weight, 0.3);
			if(CaveGame.player.direction){
				CaveGame.rock.xvelocity = (2*CaveGame.speed +this.xvelocity/2)/Math.pow(CaveGame.rock.weight, 0.3);
			}else{
				CaveGame.rock.xvelocity = (-2*CaveGame.speed+this.xvelocity/2)/Math.pow(CaveGame.rock.weight, 0.3);
			}
			CaveGame.rock.exists=true;
		}
	}
	
	public void print(){
		System.out.println(this.location.x+", "+this.location.y);
	}
}
