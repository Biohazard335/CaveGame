package rooms;

import java.util.ArrayList;

import rocks.Rock;
import collision.Ceiling;
import collision.Exit;
import collision.ExitLadder;
import collision.Floor;
import collision.Wall;
import collision.shapes.Point;
import enemies.Crab;
import enemies.Enemy;

public class room1 extends Room {
	public room1(){
		super(new ArrayList<Floor>(){private static final long serialVersionUID=1L;{
			
				add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
					add(new Point(0,350)); add(new Point(200,400));add(new Point(530,400)); 	
				}},false));
				
				add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
					add(new Point(1000,350)); add(new Point(1500,300));
				}},false));
				
				add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
					add(new Point(250,230)); add(new Point(450,230));
				}},false));
				
				add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
					add(new Point(0,800)); add(new Point(1500,850));
				}},false));
				
				add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
					add(new Point(530,670)); add(new Point(650,700));
				}},true));
				
				add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
					add(new Point(750,590)); add(new Point(900,540));
				}},true));

				add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
					add(new Point(620,500)); add(new Point(750,500)); 
				}},true));		
		}},
				
			new ArrayList<Ceiling>(){private static final long serialVersionUID = 1L;{
				 add(new Ceiling(420,0,530)); add(new Ceiling(250,250,450)); add(new Ceiling(0,0,1500));
			}},
				
			new ArrayList<Wall>(){private static final long serialVersionUID = 1L;{
				add(new Wall(0,0,350)); add(new Wall(250,231,250)); add(new Wall(450,231,250));
				add(new Wall(1490,0,180)); add(new Wall(0,350,800)); add(new Wall(1500,300,850));
				add(new Wall(530,400,420)); 
			}},
			
			new ArrayList<Exit>(){private static final long serialVersionUID = 1L;{
				add(new Exit(new Point(1490,180), 1,0, true));
			}},
			
			new ArrayList<ExitLadder>(){private static final long serialVersionUID = 1L;{
				
			}},
				
			new Point(2,2),
			
			new ArrayList<Enemy>(){private static final long serialVersionUID = 1L;{
				add(new Crab(new Point (300,229)));
			}},

			new ArrayList<Rock>(){private static final long serialVersionUID = 1L;{
			}},
			
			
			""
				
			);
		
			
				
			
		
		for(Floor f:floors){
			f.number=floors.indexOf(f);
		}
	}
}

