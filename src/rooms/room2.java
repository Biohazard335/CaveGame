package rooms;


import java.util.ArrayList;

import rocks.*;
import collision.Ceiling;
import collision.Exit;
import collision.ExitLadder;
import collision.Floor;
import collision.Wall;
import collision.shapes.Point;
import enemies.Enemy;

public class room2 extends Room {
	public room2(){
		super(new ArrayList<Floor>(){private static final long serialVersionUID=1L;{
			
				add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
					add(new Point(0,450)); add(new Point(250,400));add(new Point(400,400)); add(new Point(743,300));	
				}},false));	
		}},
				
			new ArrayList<Ceiling>(){private static final long serialVersionUID = 1L;{
				add(new Ceiling(0,0,750));
			}},
				
			new ArrayList<Wall>(){private static final long serialVersionUID = 1L;{
				add(new Wall(0,0,325)); add(new Wall(743,0,300));
			}},
			
			new ArrayList<Exit>(){private static final long serialVersionUID = 1L;{
				add(new Exit(new Point(0,325), 0,0, true));
			}},
			
			new ArrayList<ExitLadder>(){private static final long serialVersionUID = 1L;{
				
			}},
				
			new Point(1,1),
			
			new ArrayList<Enemy>(){private static final long serialVersionUID = 1L;{
			}}, 
		
			new ArrayList<Rock>(){private static final long serialVersionUID = 1L;{
			}},
		
			"");
		for(Floor f:floors){
			f.number=floors.indexOf(f);
		}
	}
}

