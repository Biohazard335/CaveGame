package rooms;


import java.util.ArrayList;

import rocks.Rock;
import collision.Ceiling;
import collision.Exit;
import collision.ExitLadder;
import collision.Floor;
import collision.Wall;
import collision.shapes.Point;
import enemies.*;

public class A1r1 extends Room {
	public A1r1(){
		super(
				new ArrayList<Floor>(){private static final long serialVersionUID=1L;{
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(0,400)); add(new Point(20,400)); add(new Point(528,240));add(new Point(550,240));
					}},false/*fallable*/));	
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(550,480)); add(new Point(1100,480));
					}},false/*fallable*/));	
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(600,380)); add(new Point(800,380));
					}},true/*fallable*/));	
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(780,240)); add(new Point(970,240));
					}},true/*fallable*/));
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(1100,240)); add(new Point(1500,240));
					}},false/*fallable*/));		
				}},
				
				new ArrayList<Ceiling>(){private static final long serialVersionUID = 1L;{
					add(new Ceiling(0/*y*/,0/*x1*/,1500/*x2*/));
				}},
				
				new ArrayList<Wall>(){private static final long serialVersionUID = 1L;{
					add(new Wall(9/*x*/,0/*y1*/,280/*y2*/)); add(new Wall(550/*x*/,240/*y1*/,480/*y2*/));
					add(new Wall(1100/*x*/,240/*y1*/,480/*y2*/));  add(new Wall(1490/*x*/,0/*y1*/,120/*y2*/));
				}},
			
				new ArrayList<Exit>(){private static final long serialVersionUID = 1L;{
					add(new Exit(new Point(0,400)/*location*/, 0/*corresponding room*/,0/*corresponding door*/, true/*open*/));
					add(new Exit(new Point(1490,240)/*location*/, 2/*corresponding room*/,0/*corresponding door*/, true/*open*/));
				}},
				
				new ArrayList<ExitLadder>(){private static final long serialVersionUID = 1L;{
					
				}},
				
				new Point(2/*x size*/,1/*y size*/),
			
				new ArrayList<Enemy>(){private static final long serialVersionUID = 1L;{
				}},
				
				new ArrayList<Rock>(){private static final long serialVersionUID = 1L;{
				}},
				
				"A1r1"
				
				);
		
		for(Floor f:floors){
			f.number=floors.indexOf(f);
		}
	}
}

