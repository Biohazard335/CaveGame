package collision;

public class Ladder extends Wall{
	
	public boolean open =true;
	public int segments=0;
			
	public Ladder(int x, int y1, int seg){
		super(x,y1,(seg*100+y1));
		this.type="ladder";
		this.segments=seg;
	}
}
