package main;

import java.util.ArrayList;

import rocks.Bronze;
import rocks.Iron;
import rocks.Rock;
import rocks.Stone;
import rooms.*;
import collision.*;
import collision.shapes.Point;
import enemies.Enemy;

public class CaveGame {
	public static ArrayList<Instance> objects = new ArrayList<Instance>();
	public static Instance player = new Player(new Point(0, 0));
	public static ArrayList<Room> rooms = new ArrayList<Room>() {
		private static final long serialVersionUID = 1L;
		{
			add(new hub());
			add(new A1r1());
			add(new A1r2());
			add(new A1r3());
			add(new A1r4());
		}
	};
	public static Rock[] rocks = { new Stone(new Point(100, 100)),
			new Iron(new Point(100, 100)), new Bronze(new Point(100, 100)) };
	public static Panel panel = new Panel();
	public static Frame frame = new Frame(750, 500);
	public static int frames = 10;
	public static double gravity = .6, jump = -15, speed = 4, vertical = 0, horizontal = 0;
	
	public static Rock rock = new Stone(new Point(0, 0));

	public static int animationCounter = 0;
	
	public static long memory=0;

	public static boolean graphics = false,paint=false;

	public static void main(String[] args) {
		player = new Player(new Point(1400, 900));
		player.roomnum = 2;
		player.yvelocity = 0;
		player.freefalling = true;
		rock.freefalling = true;
	//	panel.oldLoad();
		physicsThread.start();
		graphicsThread.start();
	//	loader.start();
	//	all.start();
	}
	
	public static void returnHV(){
		double t = -jump / gravity;
		vertical = jump * t + (.5 * gravity * (t * t)) + (jump / 1.7);
		horizontal = 2 * t * speed;
		System.out.println(horizontal+", "+vertical);
	}
	
	public static void returnMemory(){
		System.out.println(((Runtime.getRuntime().totalMemory()/1)-(Runtime.getRuntime().freeMemory()/1))+"/"+(Runtime.getRuntime
				().totalMemory()/1)+" : "+memory);
	}

	public static void physics() {
		for (Floor f : rooms.get(player.roomnum).floors) {
			if (player.freefalling == true && player.collision(f)) {
				player.contact(f);
			}
			if (rock.freefalling == true && rock.exists && rock.collision(f)) {
				rock.contact(f);
			}
			for (Enemy en : rooms.get(player.roomnum).enemies) {
				if (en.freefalling == true && en.collision(f)) {
					en.contact(f);
				}
			}
		}
		for (Ceiling c : rooms.get(player.roomnum).ceilings) {
			player.hitceiling(c);
			if (rock.exists) {
				rock.hitceiling(c);
			}
		}
		for (Wall w : rooms.get(player.roomnum).walls) {
			player.hitwall(w);
			if (rock.exists)
				rock.hitwall(w);
			for (Enemy en : rooms.get(player.roomnum).enemies) {
				en.hitwall(w);
			}
		}
		int rockindex = -1;
		for (Rock r : rooms.get(player.roomnum).rocks) {
			if (player.collision(r)) {
				rockindex = rooms.get(player.roomnum).rocks.indexOf(r);
				for (Rock r1 : rocks) {
					if (r1.type.equals(r.type)) {
						r1.amount += 1;
						break;
					}
				}
				break;
			}
		}
		if (rockindex != -1) {
			rooms.get(player.roomnum).rocks.remove(rockindex);
		}
		for (Exit e : rooms.get(player.roomnum).exits) {
			player.hitexit(e);
			if (rock.exists) {
				rock.hitwall(e);
			}
			for (Enemy en : rooms.get(player.roomnum).enemies) {
				en.hitwall(e);
			}
		}
		for (ExitLadder l : rooms.get(player.roomnum).exitladders) {
			if(l.open){
				player.hitwall(l);
			}
		}
		player.ladder();

		if (!player.hit) {
			for (Enemy en : rooms.get(player.roomnum).enemies) {
				if (player.collision(en)) {
					player.hit = true;
					player.hitcounter = 50;
					player.health -= en.power;
				}
			}
		} else {
			player.hit();
		}
		int delete = -1;
		for (Enemy en : rooms.get(player.roomnum).enemies) {
			en.move();
			en.switchlines(rooms.get(player.roomnum).floors.get(en.floornum));
			if (!en.hit) {
				if (rock.exists && en.collision(rock)) {
					en.hit = true;
					en.hitcounter = 50;
					en.health -= rock.power;
					if (en.health <= 0) {
						delete = rooms.get(player.roomnum).enemies.indexOf(en);
					}
				}
			} else {
				en.hit();
			}
		}
		if (delete != -1) {
			rooms.get(player.roomnum).enemies.remove(delete);
		}
		if (player.line != null) {
			player.move();
			player.switchlines(rooms.get(player.roomnum).floors.get(player.floornum));
		} else {
			player.location.y += player.yvelocity;
			player.displacement.x = player.origin.x - player.location.x;
			player.displacement.y = player.origin.y - player.location.y;
			player.yvelocity += player.acceleration;
			player.freefalling = true;
		}
		if (rock.exists) {
			rock.move();
		}
		panel.movecamera();
	}

	public static Thread graphicsThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				if(paint)
					frame.repaint();
				paint=!paint;
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	public static Thread loader = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				if(panel.loading)
					panel.drawRoom();;
				panel.loading=false;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	public static Thread physicsThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				if(((Runtime.getRuntime().totalMemory()/1)-(Runtime.getRuntime().freeMemory()/1))>memory){
				//	System.out.println("a");
					memory=((Runtime.getRuntime().totalMemory()/1)-(Runtime.getRuntime().freeMemory()/1));
				}
				if (animationCounter != 0) {
					physics();
				} else {
					animationCounter -= 1;
				}
				try {
					Thread.sleep(frames);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	public static Thread all = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				if((int)((Runtime.getRuntime().totalMemory()/1000000)-(Runtime.getRuntime().freeMemory()/1000000))>memory){
					memory=(int) ((Runtime.getRuntime().totalMemory()/1000000)-(Runtime.getRuntime().freeMemory()/1000000));
				}
				if (animationCounter != 0) {
					physics();
				} else {
					animationCounter -= 1;
				}
				if(paint)
					frame.repaint();
				paint=!paint;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
}