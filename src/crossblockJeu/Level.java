package crossblockJeu;

import java.util.ArrayList;

public class Level {
	
	//Attributes
	private int numLevel=0;
	private int numberBlocks;
	private ArrayList<Block> remainingBlocks;

	//Level constructor
	public Level(int numLevel, String address){
		new XMLReader(this,numLevel, address);
	}

	//Useful Getters and Setters
	public int getNumLevel(){
		return numLevel;
	}

	public void setNumLevel(int numLevel){
		this.numLevel = numLevel;
	}

	public int getNumberBlocks(){
		return numberBlocks;
	}
	
	public void setNumberBlocks(int numberBlocks){
		this.numberBlocks = numberBlocks;
	}

	public ArrayList<Block> getRemainingBlocks(){
		return remainingBlocks;
	}

	public void setRemainingBlocks(ArrayList<Block> remainingBlocks){
		this.remainingBlocks = remainingBlocks;
	}
	
	//Class methods
	public ArrayList<Block> crossedBlocks (Line line){
		//Lists all the crossed blocks by the line in parameter to store them in ArrayList<Bloc>
		ArrayList<Block> crossedBLocks = new ArrayList<Block>();
		for (int i=0; i<remainingBlocks.size(); i++){
			Block block = remainingBlocks.get(i);
			if (block.isCrossed(line)){
				crossedBLocks.add(block);
			}
		}
		return crossedBLocks;
	}
	
	public boolean validLine(ArrayList<Block> crossedBlocks){
		//Checks if the line is valid
		if (crossedBlocks.size() == this.numberBlocks){
			int abscissaId = crossedBlocks.get(0).getX();
			int ordinateId = crossedBlocks.get(0).getY();
			//Initializes of the counter for the number of blocks on the same row and column
			int counterAbscissa = 0;
			int counterOrdinate = 0;
			for (int i = 0; i<crossedBlocks.size(); i++){
				if (crossedBlocks.get(i).getX()==abscissaId){
					//Another block on the same row
					counterAbscissa++;
				}
				if (crossedBlocks.get(i).getY()==ordinateId){
					//Another block on the same column
					counterOrdinate++;
				}
			}
			if ((counterAbscissa == crossedBlocks.size())||(counterOrdinate == crossedBlocks.size())){
				//If there is the right number of blocks on either the same row or the same column
				return true;
			}
		}
		return false;
	}
	
	public void blocksDestruction(Line line){
		//Deletes the crossed blocks if the line is valid
		ArrayList<Block> crossedBlocks = this.crossedBlocks(line);
		if (this.validLine(crossedBlocks) == true){
			for (int i=0; i<crossedBlocks.size(); i++){
				crossedBlocks.get(i).setDestroyed(true);
				remainingBlocks.remove(crossedBlocks.get(i));
			}
		}
	}

	public Boolean checkFinishedLevel(){
		//Allows to check if no more block remains (finished level)
		if (remainingBlocks.size() == 0) {
			numLevel=numLevel+1;
			return true;
		} else {
			return false;
		}
	}

}
