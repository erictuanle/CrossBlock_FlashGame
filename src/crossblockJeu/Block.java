package crossblockJeu;

import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Block {
	
	//Attributes
	private int x,y;
	private boolean destroyed = false;
	
	//Line constructor
	public Block(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	//Useful Getters and Setters
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public boolean getDestroyed(){
		return destroyed;
	}
	
	public void setDestroyed(boolean destroyed){
		this.destroyed=destroyed;
	}
	
	//Class methods
	public Rectangle getBounds(){
		//Allows the detection of the block edges
		Rectangle rectangle = new Rectangle(x,y,34,34);
		return rectangle;
	}
	
	public boolean isCrossed(Line ligne){
		//Allows to assess if the line is crossing the block
		Line2D ligne2D = new Line2D.Double(ligne.getX1(), ligne.getY1(), ligne.getX2(), ligne.getY2());
		if (ligne2D.intersects(this.getBounds())){
			return true;
		} else {
			return false;
		}
	}
	
}
