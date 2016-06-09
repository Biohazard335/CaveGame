package rooms;


import java.util.ArrayList;

import rocks.Iron;
import rocks.Rock;
import collision.Ceiling;
import collision.Exit;
import collision.ExitLadder;
import collision.Floor;
import collision.Ladder;
import collision.Wall;
import collision.shapes.Point;
import enemies.Enemy;

public class A1r2 extends Room {
	public A1r2(){
		super(
				new ArrayList<Floor>(){private static final long serialVersionUID=1L;{
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(0,240)); add(new Point(1500,240));
					}},false/*fallable*/));	
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						 add(new Point(1000,700));add(new Point(1300,600));add(new Point(1500,600));
					}},false/*fallable*/));
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(750,750)); add(new Point(900,750));
					}},true/*fallable*/));	
					add(new Floor(new ArrayList<Point>(){private static final long serialVersionUID=1L;{
						add(new Point(0,900)); ;add(new Point(1500,900)); 	
					}},false));	
				}},
				
				new ArrayList<Ceiling>(){private static final long serialVersionUID = 1L;{
					add(new Ceiling(0/*y*/,0/*x1*/,1500/*x2*/));
					add(new Ceiling(700/*y*/,1000/*x1*/,1500/*x2*/));
				}},
				
				new ArrayList<Wall>(){private static final long serialVersionUID = 1L;{
					add(new Wall(10/*x*/,0/*y1*/,120/*y2*/)); add(new Wall(10/*x*/,240/*y1*/,900/*y2*/));
					add(new Wall(1490,0,600)); add(new Wall(1490,700,780));
					add(new Wall(1000/*x*/,700/*y1*/,700/*y2*/));
					add(new Ladder(1400/*x*/,200/*y1*/,4/*segments(100pxls)*/));
				}},
			
				new ArrayList<Exit>(){private static final long serialVersionUID = 1L;{
					add(new Exit(new Point(0,240)/*location*/, 1/*corresponding room*/,1/*corresponding door*/, true/*open*/));
					add(new Exit(new Point(1490,900)/*location*/, 4/*corresponding room*/,0/*corresponding door*/, true/*open*/));
				}},
				
				new ArrayList<ExitLadder>(){private static final long serialVersionUID = 1L;{
					add(new ExitLadder(new Point(300,810)/*location*/,3/*corresponding room*/,0/*corresponding door*/,
							true/*open*/,false/*up*/));
				}},
				
				new Point(2/*x size*/,2/*y size*/),
			
				new ArrayList<Enemy>(){private static final long serialVersionUID = 1L;{
		//			add(new enemies.Circle(new Point(500,700)));
		//			add(new enemies.Square(new Point(700,700)));
				}},
				
				new ArrayList<Rock>(){private static final long serialVersionUID = 1L;{
					add(new Iron(new Point(500,900)/*location*/));
				//	add(new Bronze(new Point(450,900)/*location*/));
				}},
				
				"A1r2"
				
				);
		
		for(Floor f:floors){
			f.number=floors.indexOf(f);
		}
	}
}

