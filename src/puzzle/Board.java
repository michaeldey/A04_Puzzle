package puzzle;

import java.io.IOException;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
	
	private int[][] tiles;	//Array to hold puzzle
	private int N; 			//size puzzle side (puzzle is N by N)
	private int[] oneDArray;	//one dimensional version of tiles
	

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) 
    {
    	if (blocks[0].length != blocks[1].length)
    	{
    		throw new java.lang.Error("Puzzle width must match Puzzle height");
    	}
    	this.N = blocks[0].length;	//set width of the puzzle side
    	tiles = blocks;				//set the size of tiles
    	
    	    	
    	//load oneDArray
    	oneDArray = new int[size()];
    	int pointer = 0;
    	for (int i = 0; i<N; i++)
    	{
    		for (int j=0; j<N; j++)
    		{
    			oneDArray[pointer++]= tiles[i][j];
    		}
    	}
    	System.out.print("oneDArray:");
    	for (int m : oneDArray){
    		System.out.print(m);
    	}
    	System.out.println();
    }
    
   private int[][] node(int[] orignialArray) 
   {	   
	   return null;	   
   }
    
    // board size N
    public int size()
    {
		return (tiles[0].length * tiles[1].length);  //this works because tiles[][] should be N by N  	
    }
    
    // number of blocks out of place
    public int hamming()
    {
		//todo
    	return 0;    	
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() 
    {    	
    	//todo
		return 0;    	
    }
    
    // is this board the goal board?   
    public boolean isGoal() 
    {
    	//todo
		return hamming() == 0;    	
    }
    
    // is this board solvable?
    public boolean isSolvable() 
    {    	
    	if (size() % 2 != 0) {
    		// inversions are even its true
    	} else {
    		// inversions plus location row of 0 is odd true
    	} 
    		
		return false;
    	
    }
    
	// does this board equal y?
    @Override
    public boolean equals(Object y) 
    { 	
    	//todo
    	return false;    
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors() 
    {
		return null;    	
    }
    
    // string representation of this board (in the output format specified below)
    @Override
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append(N + "\n");
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			s.append(String.format("%2d ", tiles[i][j]));
    		}
    		s.append("\n");
    	}    	   
    	return s.toString(); 	
    
    }
    
    //calculate the number of inversions
    private int inversions(){
    	int high = size()-1; 	//size of the largest int on the board (-1 to account for the 0)
    	int inversions = 0;
    	for (int i = 0; i<high;i++)
    	{
    		for (int j = i+1; j <= high; j++)
    		{
    			if (oneDArray[j] < oneDArray[i]) inversions++;
    		}
    	}
    	return inversions;
    }
    
    // unit tests (not graded)
    public static void main(String[] args) {
    	int[][] testArray = {{1,2,3,4},{5,0,6,8},{9,10,7,11},{13,14,15,12}};	//create 3 by 3 array
    	Board testBoard = new Board(testArray);
    	System.out.println("Size: " + testBoard.size());	//test size method
    	
    	System.out.print("inversions:");
    	System.out.println(testBoard.inversions());
    	
    	
    	
    }//end of main
}//end of Board
