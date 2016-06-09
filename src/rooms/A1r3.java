package rooms;


import java.util.ArrayList;

import rocks.*;
import collision.*;
import collision.shapes.Point;
import enemies.Crab;
import enemies.Enemy;

public class A1r3 extends Room {
	public A1r3(){
		super(
				new ArrayList<Floor>(){private static final long serialVersionUID=1L;{
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(10,200)); add(new Point(300,200)); add(new Point(600,220));
					}},false/*fallable*/));	
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(620,350)); add(new Point(735,350));
					}},true/*fallable*/));
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(620,450)); add(new Point(735,450));
					}},true/*fallable*/));
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(10,550)); add(new Point(735,550));
					}},false/*fallable*/));		
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(0,900)); add(new Point(750,900));
					}},false/*fallable*/));	
				}},
				
				new ArrayList<Ceiling>(){private static final long serialVersionUID = 1L;{
					add(new Ceiling(0/*y*/,10/*x1*/,750/*x2*/));
					add(new Ceiling(260/*y*/,10/*x1*/,600/*x2*/));
				}},
				
				new ArrayList<Wall>(){private static final long serialVersionUID = 1L;{
					add(new Wall(10/*x*/,0/*y1*/,780/*y2*/));
					add(new Wall(735/*x*/,0/*y1*/,780/*y2*/));
					add(new Wall(600/*x*/,220/*y1*/,260/*y2*/));
					add(new Ladder(80/*x*/,500/*y1*/,4/*segments(100pxls)*/));
				}},
			
				new ArrayList<Exit>(){private static final long serialVersionUID = 1L;{
					add(new Exit(new Point(0,900)/*location*/, 3/*corresponding room*/,1/*corresponding door*/, true/*open*/));
					add(new Exit(new Point(735,900)/*location*/, 3/*corresponding room*/,0/*corresponding door*/, true/*open*/));
				}},
				
				new ArrayList<ExitLadder>(){private static final long serialVersionUID = 1L;{
					add(new ExitLadder(new Point(80,0)/*location*/,2/*corresponding room*/,0/*corresponding door*/,
							true/*open*/,true/*up*/));
				}},
				
				new Point(1/*x size*/,2/*y size*/),
			
				new ArrayList<Enemy>(){private static final long serialVersionUID = 1L;{
					add(new Crab(new Point (300,550)/*location*/));
				}},
				
				new ArrayList<Rock>(){private static final long serialVersionUID = 1L;{
				}},
				
				"A1r3"
				
				);
		
		for(Floor f:floors){
			f.number=floors.indexOf(f);
		}
	}
}

