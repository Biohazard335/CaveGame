package main;

import java.awt.Image;

import collision.shapes.Point;

public class Animation {

	public Image[] frames;
	
	public int framenum=0;
	
	public Point location;

	public Animation(Image[] frames, Point location){
		this.frames=frames;
		this.location=location;
	}
}