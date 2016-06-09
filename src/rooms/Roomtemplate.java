package rooms;


import java.util.ArrayList;

import rocks.*;
import collision.*;
import collision.shapes.Point;
import enemies.*;

public class Roomtemplate extends Room {
	public Roomtemplate(){
		super(
				new ArrayList<Floor>(){private static final long serialVersionUID=1L;{
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(0,0)); add(new Point(0,0));
					}},false/*fallable*/));	
				}},
				
				new ArrayList<Ceiling>(){private static final long serialVersionUID = 1L;{
					add(new Ceiling(0/*y*/,0/*x1*/,750/*x2*/));
				}},
				
				new ArrayList<Wall>(){private static final long serialVersionUID = 1L;{
					add(new Wall(0/*x*/,0/*y1*/,0/*y2*/));
					add(new Ladder(0/*x*/,0/*y1*/,0/*segments(100pxls)*/));
				}},
			
				new ArrayList<Exit>(){private static final long serialVersionUID = 1L;{
					add(new Exit(new Point(0,325)/*location*/, 0/*corresponding room*/,0/*corresponding door*/, true/*open*/));
				}},
				
				new ArrayList<ExitLadder>(){private static final long serialVersionUID = 1L;{
					add(new ExitLadder(new Point(0,0)/*location*/,0/*corresponding room*/,0/*corresponding door*/,
							true/*open*/,true/*up*/));
				}},
				
				new Point(1/*x size*/,1/*y size*/),
			
				new ArrayList<Enemy>(){private static final long serialVersionUID = 1L;{
					add(new Crab(new Point (300,229)/*location*/));
				}},
				
				new ArrayList<Rock>(){private static final long serialVersionUID = 1L;{
					add(new Iron(new Point(500,900)/*location*/));
				}},
				
				"A1r1"
				
				);
		
		for(Floor f:floors){
			f.number=floors.indexOf(f);
		}
	}
}

