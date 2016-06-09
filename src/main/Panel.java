package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import rocks.Rock;
import rooms.Room;
import collision.*;
import collision.shapes.*;
import enemies.Enemy;

public class Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public int xoffset=0,yoffset=0;
	
	public static int framenum=0;
	
	public Toolkit toolkit= Toolkit.getDefaultToolkit();
	
	public Animation[] animations;
	
	public Image room;
	
	public Image[] rooms;
	
	public boolean loading = true;
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 550);
		if(loading){
			//drawRoom();
			oldLoad();
			loading=false;
		}
		else{
			if(CaveGame.graphics){
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 1000, 550);
				withgraphics(g);
				g.setColor(Color.BLACK);
				g.drawString(CaveGame.rocks[CaveGame.player.rocknum].type+" : "+CaveGame.rocks[CaveGame.player.rocknum].amount,20,20);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 1000, 550);
				nographics(g);
				g.setColor(Color.WHITE);
				g.drawString(CaveGame.rocks[CaveGame.player.rocknum].type+" : "+CaveGame.rocks[CaveGame.player.rocknum].amount,20,20);
			}	
		}
	}

	public void drawRoom(){
		if(room!=null){
			room.flush();
		}/**/
//		try{
//			room =ImageIO.read(new File("data/rooms/"+CaveGame.rooms.get(CaveGame.player.roomnum).name+".png"));
//		}catch(IOException e){
//		}
		room=toolkit.createImage(new File("data/rooms/"+CaveGame.rooms.get(CaveGame.player.roomnum).name+".png").getAbsolutePath());
		//g.drawImage(room,0+xoffset,0+yoffset,
			//	(int)(CaveGame.rooms.get(CaveGame.player.roomnum).sectors.x*750),
			//	(int)(CaveGame.rooms.get(CaveGame.player.roomnum).sectors.y*500),this);
	}
	
	public void load(int roomnum){	
		if(rooms==null){
			drawRoom();
		}else{
			room=rooms[roomnum];
		}
		
		int index=0;
		
		rooms = new Image[CaveGame.rooms.get(CaveGame.player.roomnum).exits.size()
		                  +CaveGame.rooms.get(CaveGame.player.roomnum).exitladders.size()];
		System.out.println(rooms.length);
		for(Exit e: CaveGame.rooms.get(CaveGame.player.roomnum).exits){
			try{
				rooms[index] =ImageIO.read(new File("data/rooms/"+CaveGame.rooms.get(e.room).name+".png"));
			}catch(IOException q){
			}
			index+=1;
		}for(ExitLadder e: CaveGame.rooms.get(CaveGame.player.roomnum).exitladders){
			try{
				rooms[index] =ImageIO.read(new File("data/rooms/"+CaveGame.rooms.get(e.room).name+".png"));
			}catch(IOException q){
			}
			index+=1;
		}
	}
	
	public void oldLoad(){
		rooms = new Image[CaveGame.rooms.size()];
		for(Room r:CaveGame.rooms){
//			rooms[CaveGame.rooms.indexOf(r)]=toolkit.createImage(new File("data/rooms/"+r.name+".png").getAbsolutePath());
			try{
				rooms[CaveGame.rooms.indexOf(r)] =ImageIO.read(new File("data/rooms/"+r.name+".png"));
			}catch(IOException e){
			}
		}
	}
	
	public void movecamera(){
		if(CaveGame.player.location.x<=CaveGame.rooms.get(CaveGame.player.roomnum).xboundleft 
				|| CaveGame.rooms.get(CaveGame.player.roomnum).sectors.x==1){
			xoffset=0;
		}else if(CaveGame.player.location.x>CaveGame.rooms.get(CaveGame.player.roomnum).xboundleft
				&&CaveGame.player.location.x<CaveGame.rooms.get(CaveGame.player.roomnum).xboundright){
			xoffset=-(int)(CaveGame.player.location.x)+CaveGame.rooms.get(CaveGame.player.roomnum).xboundleft;
		}else{
			xoffset=-CaveGame.rooms.get(CaveGame.player.roomnum).xboundright+375;
		}
		if(CaveGame.player.location.y<=CaveGame.rooms.get(CaveGame.player.roomnum).ybounddown
				|| CaveGame.rooms.get(CaveGame.player.roomnum).sectors.y==1){
			  yoffset=0;
		}else if(CaveGame.player.location.y>CaveGame.rooms.get(CaveGame.player.roomnum).ybounddown
				&&CaveGame.player.location.y<CaveGame.rooms.get(CaveGame.player.roomnum).yboundup){
			yoffset=-(int)(CaveGame.player.location.y)+CaveGame.rooms.get(CaveGame.player.roomnum).ybounddown;
		}else if(CaveGame.player.location.y>CaveGame.rooms.get(CaveGame.player.roomnum).yboundup){
			yoffset=-CaveGame.rooms.get(CaveGame.player.roomnum).yboundup+375;
		}
	}
	
	public void paintwallsfloorceilings(Graphics g){
		for(Floor f: CaveGame.rooms.get(CaveGame.player.roomnum).floors){
			if(f.fallable){
				g.setColor(Color.decode("#C49A5F"));
			}else{
				g.setColor(Color.decode("#8A5C2E"));
			}
			for (Point p : f.points) {
				g.fillRect((int) (p.x)+xoffset, (int) (p.y)+yoffset, 1, 1);
			}
			
		}
		for(Wall w:CaveGame.rooms.get(CaveGame.player.roomnum).walls){
			if(w.type.equals("ladder")){
				g.setColor(Color.YELLOW);
			}else{
				g.setColor(Color.GREEN);
			}
			for(Point p: w.points){
				g.fillRect((int) (p.x+xoffset), (int) (p.y)+yoffset, 1, 1);		
			}
		}
		g.setColor(Color.BLUE);
		for(Ceiling w:CaveGame.rooms.get(CaveGame.player.roomnum).ceilings){
			for(Point p: w.points){
				g.fillRect((int) (p.x+xoffset), (int) (p.y+yoffset), 1, 1);		
			}
		}
		g.setColor(Color.decode("#7E3F98"));
		for(Exit e:CaveGame.rooms.get(CaveGame.player.roomnum).exits){
			for(Point p: e.points){
				g.fillRect((int)(p.x+xoffset),(int)(p.y)+yoffset,10,1);
			}
		}
		for(ExitLadder w:CaveGame.rooms.get(CaveGame.player.roomnum).exitladders){
			for(Point p: w.points){
				g.fillRect((int) (p.x+xoffset), (int) (p.y)+yoffset, 1, 1);		
			}
		}
	}
	
	public void paintHealth(Graphics g, Instance i){
		int x=0;
		while(x<(int)(i.health/2)){
			g.drawImage(toolkit.createImage("./data/player/fullHeart.png")
					,(int)(i.location.x-(11*(int)(i.health+.5))/4+xoffset+11*x),(int)(i.location.y-i.height-10+yoffset),10,10,this);
			x++;
		}
		if((int)(i.health)%2==1){
			g.drawImage(toolkit.createImage("./data/player/halfHeart.png")
					,(int)(i.location.x-(11*i.health)/4+xoffset+11*x),(int)(i.location.y-i.height-10+yoffset),5,10,this);
		}
	}
	
	public void paintHealthAlt(Graphics g, Instance i){
		g.drawImage(toolkit.getImage("./data/player/all hearts.png")
				,(int)(i.location.x-11*(i.health/4)+xoffset),(int)(i.location.y-i.height-10+yoffset),
				(int)(i.location.x+11*(i.health/4)+xoffset),(int)(i.location.y-i.height+yoffset),
				0,0,(int)(11*i.health*2)+((int)(i.health)%2==1?2:0),41,this);
		
	}
	
	public void paintHealthNew(Graphics g, Instance i){
		int x=0;
		while(x<(int)(i.health/2)){
			g.drawImage(toolkit.getImage("./data/player/fullnew.png")
					,(int)(i.location.x-(11*(int)(i.health/2+.5))/2+xoffset+11*x),(int)(i.location.y-i.height-15+yoffset),10,10,this);
			x++;
		}
		if((int)(i.health)%2==1){
			g.drawImage(toolkit.getImage("./data/player/halfnew.png")
					,(int)(i.location.x-(11*(int)(i.health/2+.5))/2+xoffset+11*x),(int)(i.location.y-i.height-15+yoffset),10,10,this);
		}
		
	}
	
	public void paintHealthNo(Graphics g, Instance i){
		int x=0;
		while(x<(int)(i.health/2)){
			g.setColor(Color.RED);
			g.fillOval((int)(i.location.x-(11*(int)(i.health/2+.5))/2+xoffset+11*x),(int)(i.location.y-i.height-15+yoffset),10,10);
			x++;
		}
		if((int)(i.health)%2==1){
			g.setColor(Color.GREEN);
			g.fillOval((int)(i.location.x-(11*(int)(i.health/2+.5))/2+xoffset+11*x),(int)(i.location.y-i.height-15+yoffset),10,10);
		}
		
	}
	
	public void withgraphics(Graphics g){ 
		g.drawImage(rooms[CaveGame.player.roomnum],0+xoffset,0+yoffset,
				(int)(CaveGame.rooms.get(CaveGame.player.roomnum).sectors.x*750),
				(int)(CaveGame.rooms.get(CaveGame.player.roomnum).sectors.y*500),this);/**/
		/*g.drawImage(room,0+xoffset,0+yoffset,
				(int)(CaveGame.rooms.get(CaveGame.player.roomnum).sectors.x*750),
				(int)(CaveGame.rooms.get(CaveGame.player.roomnum).sectors.y*500),
				this);*/
		for(Wall w:CaveGame.rooms.get(CaveGame.player.roomnum).walls){
			if(w.type.equals("ladder")){
				Ladder l=(Ladder)w;
				for(int i=0;i<l.segments;i++){
					g.drawImage(toolkit.getImage("./data/ladder.png"),(int)(l.x-20+xoffset),
							(int)(100*i+yoffset+l.y1),40,100,this);
				}
			}
		}
		for(ExitLadder l:CaveGame.rooms.get(CaveGame.player.roomnum).exitladders){
			for(int i=0;i<l.segments;i++){
				g.drawImage(toolkit.getImage("./data/ladder.png"),(int)(l.x-20+xoffset),
						(int)(100*i+yoffset+l.y1),40,100,this);
			}
		}
		for(Enemy en: CaveGame.rooms.get(CaveGame.player.roomnum).enemies){
			if(en.visible){
				if(en.xvelocity>=0){
					g.drawImage(toolkit.getImage("./data/enemies/"+en.name+".png"),
						(int)(en.location.x-(en.posbound.x-en.negbound.x)/2+xoffset),(int)(en.location.y-en.height+yoffset),
						(int)(en.width),(int)(en.height),null);
				}else{
					g.drawImage(toolkit.getImage("./data/enemies/"+en.name+".png"),
						(int)(en.location.x+(en.posbound.x-en.negbound.x)/2+xoffset),(int)(en.location.y-en.height+yoffset),
						-(int)(en.width),(int)(en.height),null);
				}
				paintHealthNew(g,en);	
			}else{
				g.drawImage(toolkit.getImage("./data/enemies/inv"+en.name+".png"),
						(int)(en.location.x-(en.width)/2+xoffset),(int)(en.location.y-en.height+yoffset),
						(int)(en.width),(int)(en.height),null);
			}
		}
		paintHealthNew(g,CaveGame.player);
		g.drawImage(toolkit.getImage("./data/notmungo.png"),(int)(CaveGame.player.location.x+(CaveGame.player.direction?-20:20)+xoffset),
				(int)(CaveGame.player.location.y-80+yoffset),(CaveGame.player.direction?40:-40),80,this);
		if(CaveGame.rock.exists){
			g.drawImage(toolkit.getImage("./data/rocks/"+CaveGame.rock.type+".png"),
					(int)(CaveGame.rock.location.x-CaveGame.rock.radius+xoffset),
					(int)(CaveGame.rock.location.y-(2*CaveGame.rock.radius)+yoffset),
					(int)(CaveGame.rock.radius*2),(int)(CaveGame.rock.radius*2),null);
		}
		for(Rock r:CaveGame.rooms.get(CaveGame.player.roomnum).rocks){
			g.drawImage(toolkit.getImage("./data/rocks/"+r.type+".png"),
					(int)(r.location.x-r.radius+xoffset),(int)(r.location.y-(2*r.radius)+yoffset),
					(int)(r.radius*2),(int)(r.radius*2),null);
		}

		if(animations!=null){
			for(Animation a:animations){
				if(a.framenum<=a.frames.length-1){
					g.drawImage(a.frames[a.framenum],(int) a.location.x,(int) a.location.y,null);
					a.framenum++;
				}
			}
		}
	}
	
	public void nographics(Graphics g){	
		paintwallsfloorceilings(g);
		if(CaveGame.player.hit){
			g.setColor(Color.yellow);
		}else{
			g.setColor(Color.red);
		}
		g.fillRect((int)CaveGame.player.location.x+xoffset,(int)CaveGame.player.location.y+yoffset,1,1);
		for(Shape s: CaveGame.player.shapes){
			if(s.type.equals("pllgm")){
				Pllgm p=(Pllgm)s;
				g.drawRect((int)p.location.x+xoffset, (int)p.location.y+yoffset,(int) p.width,(int) p.height);
			}else if(s.type.equals("circle")){
				collision.shapes.Circle c=(Circle)s;
				g.drawOval((int)c.location.x+xoffset,(int)c.location.y+yoffset,(int)c.radius,(int)c.radius);
			}
		}	
		g.setColor(Color.red);
		for(Enemy en: CaveGame.rooms.get(CaveGame.player.roomnum).enemies){
			if(en.hit){
				g.setColor(Color.yellow);
			}else{
				g.setColor(Color.red);
			}
			for(Shape s: en.shapes){
				if(s.type.equals("pllgm")){
					Pllgm p=(Pllgm)s;
					g.drawRect((int)p.location.x+xoffset, (int)p.location.y+yoffset,(int) p.width,(int) p.height);
				}else if(s.type.equals("circle")){
					collision.shapes.Circle c=(Circle)s;
					g.drawOval((int)(c.location.x+xoffset-c.radius),(int)(c.location.y+yoffset-c.radius),
							(int)c.radius*2,(int)c.radius*2);
				}
			}
		}
		g.setColor(Color.gray);
		if(CaveGame.rock.exists){
			g.drawOval((int)(CaveGame.rock.location.x+xoffset-CaveGame.rock.radius),
					(int)(CaveGame.rock.location.y+yoffset-CaveGame.rock.radius*2),
					(int)CaveGame.rock.radius*2,(int)CaveGame.rock.radius*2);
		}
		for(Rock r:CaveGame.rooms.get(CaveGame.player.roomnum).rocks){
			g.drawOval((int)(r.location.x+xoffset-r.radius),(int)(r.location.y+yoffset-r.radius*2),(int)r.radius*2,(int)r.radius*2);
		}
		for(Enemy en: CaveGame.rooms.get(CaveGame.player.roomnum).enemies){
			if(en.visible){
				paintHealthNo(g,en);	
			}
		}
		paintHealthNo(g,CaveGame.player);
	}
	
	public void other(Graphics g){
		/*
		for(Platform pf: CaveGame.rooms.get(CaveGame.player.roomnum).platforms){
			g.drawImage(pf.image,(int)pf.location.x+offsetx,(int)pf.location.y+offsety,this);
			
		}
		if(framenum<7){
			g.drawImage(toolkit.getImage("./data/gear1.png"),(int)(CaveGame.player.location.x-25+offsetx),
					(int)(CaveGame.player.location.y-100+offsety),this);
		}else if(framenum>=7){
			g.drawImage(toolkit.getImage("./data/gear2.png"),(int)(CaveGame.player.location.x-25+offsetx),
					(int)(CaveGame.player.location.y-100+offsety),this);
		} if(framenum==14){
			framenum=0;
		} if(moving){
			framenum++;
		}
		*/
	}
}