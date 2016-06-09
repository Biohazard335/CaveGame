package rooms;


import java.util.ArrayList;

import rocks.*;
import collision.*;
import collision.shapes.Point;
import enemies.*;

public class A1r4 extends Room {
	public A1r4(){
		super(
				new ArrayList<Floor>(){private static final long serialVersionUID=1L;{
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(0,400)); add(new Point(1500,400));
					}},false/*fallable*/));	
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(600,350)); add(new Point(800,350));
					}},false/*fallable*/));	
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(1300,350)); add(new Point(1490,350));
					}},false/*fallable*/));	
				}},
				
				new ArrayList<Ceiling>(){private static final long serialVersionUID = 1L;{
					add(new Ceiling(0/*y*/,0/*x1*/,1500/*x2*/));
				}},
				
				new ArrayList<Wall>(){private static final long serialVersionUID = 1L;{
					add(new Wall(10/*x*/,0/*y1*/,280/*y2*/));
					add(new Wall(1490/*x*/,0/*y1*/,350/*y2*/));
					add(new Wall(600/*x*/,350/*y1*/,400/*y2*/));
					add(new Wall(800/*x*/,350/*y1*/,400/*y2*/));
					add(new Wall(1300/*x*/,350/*y1*/,400/*y2*/));
				//	add(new Ladder(0/*x*/,0/*y1*/,0/*segments(100pxls)*/));
				}},
			
				new ArrayList<Exit>(){private static final long serialVersionUID = 1L;{
					add(new Exit(new Point(0,400)/*location*/, 2/*corresponding room*/,1/*corresponding door*/, true/*open*/));
				}},
				
				new ArrayList<ExitLadder>(){private static final long serialVersionUID = 1L;{
					add(new ExitLadder(new Point(1350,300)/*location*/,0/*corresponding room*/,0/*corresponding door*/,
							false/*open*/,false/*up*/));
				}},
				
				new Point(2/*x size*/,1/*y size*/),
			
				new ArrayList<Enemy>(){private static final long serialVersionUID = 1L;{
					add(new Lizard(new Point (1200,400)/*location*/));
				}},
				
				new ArrayList<Rock>(){private static final long serialVersionUID = 1L;{
				}},
				
				"A1r4"
				
				);
		
		for(Floor f:floors){
			f.number=floors.indexOf(f);
		}
	}
}

