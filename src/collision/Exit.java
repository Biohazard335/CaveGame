package collision;

import collision.shapes.Point;

public class Exit extends Wall{
	
	public int room =0, corresp =0;
	public boolean open =true;
			
	public Exit(Point loc, int room, int corresp, boolean open){
		super(loc.x,loc.y-120,loc.y-1);
		this.type="exit";
		this.room=room;
		this.corresp=corresp;
		this.open=open;
	}
}
