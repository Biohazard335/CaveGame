package rooms;

import java.util.ArrayList;

import rocks.Rock;
import collision.Ceiling;
import collision.Exit;
import collision.ExitLadder;
import collision.Floor;
import collision.Wall;
import collision.shapes.Point;
import enemies.Enemy;

public class hub extends Room {
	public hub(){
		super(new ArrayList<Floor>(){private static final long serialVersionUID=1L;{
			
				add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
					add(new Point(0,1400)); ;add(new Point(1500,1400)); 	
				}},false));
		}},
				
			new ArrayList<Ceiling>(){private static final long serialVersionUID = 1L;{
				add(new Ceiling(0,0,1500));
			}},
				
			new ArrayList<Wall>(){private static final long serialVersionUID = 1L;{
				add(new Wall(0,0,1400)); add(new Wall(1490,0,1280)); 
			}},
			
			new ArrayList<Exit>(){private static final long serialVersionUID = 1L;{
				add(new Exit(new Point(1490,1400), 1,0, true));
			}},
			
			new ArrayList<ExitLadder>(){private static final long serialVersionUID = 1L;{
				
			}},
				
			new Point(2,3),
			
			new ArrayList<Enemy>(){private static final long serialVersionUID = 1L;{
			}},
			
			new ArrayList<Rock>(){private static final long serialVersionUID = 1L;{
			}},
			
			"hub"
				
			);
		
		for(Floor f:floors){
			f.number=floors.indexOf(f);
		}
	}
}

