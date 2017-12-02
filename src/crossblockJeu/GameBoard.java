package crossblockJeu;

import java.util.ArrayList;

public class GameBoard {
	
	//Attributes
	private int[][] array = new int[20][11];
	private Level level;
	private Line line;
	private String address;

	//GameBoard constructor
	public GameBoard(String address){
		this.address=address;
		level=new Level(1,this.address);
		this.boardConstruction();
	}
	
	//Useful Getters and Setters
	public int[][] getArray(){
		return array;
	}
	
	public void setArray(int[][] array){
		this.array=array;
	}
	
	public Level getLevel(){
		return level;
	}
	
	public void setLevel(Level niveau){
		this.level=niveau;
	}
	
	public Line getLine(){
		return line;
	}
	
	public void setLine(Line line){
		this.line=line;
	}
	
	//Class methods
	public void boardConstruction(){
		//Builds the array class attributes
		array = new int[20][11];
		ArrayList<Block> remainingBlocks = level.getRemainingBlocks();
		if (remainingBlocks==null){
			return;
		}
		for (int i=0; i<remainingBlocks.size(); i++){
			array[remainingBlocks.get(i).getX()/34][remainingBlocks.get(i).getY()/34]=1;
		}
	}
	
	public void reloadLevel(){
		//Allows to reload the current level if the player is stuck
		level = new Level (level.getNumLevel(),address);
		this.boardConstruction();
	}

	public void blocksDestruction(int[] pointA,int[] pointB){
		//Allows to delete the blocks crossed by a line
		int x1 = pointA[0];
		int y1 = pointA[1];
		int x2 = pointB[0];
		int y2 = pointB[1];
		line= new Line (x1,y1,x2,y2);
		level.blocksDestruction(this.line);
		this.checkFinishedLevele();
	}
	
	
	public void checkFinishedLevele(){
		//Allows to assess if the level is finished to load the next level
		if (this.level.checkFinishedLevel() == true){
			this.reloadLevel();
		} else{
			this.boardConstruction();
		}
	}
	
}
