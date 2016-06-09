package collision;

import collision.shapes.Point;

public class ExitLadder extends Ladder{
	
	public int room=0,corresp=0;
	
	public boolean open=true, up=true;
	
	public ExitLadder(Point p, int room, int corresp, boolean open, boolean up){
		super((int)p.x,(int)p.y,2);
		this.room=room;
		this.corresp=corresp;
		this.open=open;
		this.type="exitladder";
		this.up=up;
	}
}