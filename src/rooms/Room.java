package rooms;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import rocks.Rock;
import collision.*;
import collision.shapes.Point;
import enemies.Enemy;

public class Room {
	public ArrayList<Floor> floors = new ArrayList<Floor>();
	public ArrayList<Ceiling> ceilings = new ArrayList<Ceiling>();
	public ArrayList<Wall> walls = new ArrayList<Wall>();
	public ArrayList<Exit> exits = new ArrayList<Exit>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Rock> rocks = new ArrayList<Rock>();
	public ArrayList<ExitLadder> exitladders = new ArrayList<ExitLadder>();
	public String name ="";
	public Point sectors = new Point(1,1);
	public int xboundright =375, xboundleft=375,yboundup=100,ybounddown=400;
	public Image image;
	
	public Room(ArrayList<Floor> f, ArrayList<Ceiling> c, ArrayList<Wall> w, ArrayList<Exit> e, ArrayList<ExitLadder> el,
			Point p, ArrayList<Enemy> enemies, ArrayList<Rock> rocks,String name){
		this.floors=f;
		this.walls=w;
		this.ceilings=c;
		this.sectors=p;
		this.exits=e;
		this.exitladders=el;
		this.enemies=enemies;
		this.rocks=rocks;
		this.xboundright=(int)(750*this.sectors.x)-368;
		this.yboundup=(int)(500*this.sectors.y)-100;
		this.name=name;
		this.image=Toolkit.getDefaultToolkit().getImage("./data/rooms/"+this.name+".png");
	}
}