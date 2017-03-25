package puzzle;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
	
	private int[][] tiles;	//Array to hold puzzle
	private int N; 			//size puzzle side (puzzle is N by N)
	private int[] oneDArray;	//one dimensional version of tiles
	private int zeroY; 		//holds Y position of 0 value
	private int zeroX; 		//holds X position of 0 value
	

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) 
    {
    	if (blocks[0].length != blocks[1].length)
    	{
    		throw new java.lang.Error("Puzzle width must match Puzzle height");
    	}
    	this.N = blocks[0].length;	//set width of the puzzle side
    	this.tiles = blocks;				//set the size of tiles
    	load1DArray();				//loads the oneDArray and sets zeroY and zeroX
    }
    
    // board size N
    public int size()
    {
		return (tiles[0].length * tiles[1].length);  //this works because tiles[][] should be N by N  	
    }
    
    // number of blocks out of place
    public int hamming()
    {
	int value = -1;
      	for (int i = 0; i < tiles.length; i++) {
          for (int j = 0; j < tiles[i].length; j++) {
              if (tiles[i][j] != (i * tiles.length + j + 1)) value++;
          }
      }
      return value;  	
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() 
    {    	
        	
    	int value = 0;
    	for (int i = 0; i < tiles.length; i++) {
    		for (int j = 0; j < tiles[i].length; j++) {
    			int expectedValue = (i * tiles.length + j + 1);
    			if (tiles[i][j] != expectedValue && tiles[i][j] != 0) {
    				int actualValue = tiles[i][j];
    				actualValue--;
    				int goalI = actualValue / size();
    				int goalJ = actualValue % size();
    				value += Math.abs(goalI - i) + Math.abs(goalJ - j);
    			}
    		}
    	}
    	return value;   	
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
    	//odd size board (N) && odd number of inversions == not solvable
    	//odd size board (N) && even number of inversion == solvable
    	//even size board (N): Sum of (blank ROW location of 0) + number of inversions == EVEN Number:  not solvable
    	//even size board (N): Sum of (blank ROW location of 0) + number of inversions == ODD Number: solvable
    	
    	boolean evenInversions = (inversions()%2==0); //evenInversions = True if # inversions is Even number, false if Odd number
    	
    	if (size() %2!=0 && evenInversions)//size is ODD and inversions is EVEN (Solvable)
    	{
    		return true;
    	} else if (size()%2==0 )//size is EVEN
    	{
			 //even Inversions + Odd row == Odd number (Solvable)
			 //odd Inversions + even row == Odd number (Solvable)
    		if ((evenInversions && (zeroY%2!=0))||(!evenInversions && (zeroY%2==0)))
    		{
    			return true;
    		}    		
    	}    		
		return false;    	
    }
    
	// does this board equal y?
    @Override
    public boolean equals(Object y) 
    { 	
    	Board newBoard = (Board)y; //cast y to Board
    	return this.toString().equals(newBoard.toString());   //if the toString boards are the same, the boards are equal
    }
    
    private boolean isTop(){return (zeroY==0);}			//returns true if 0 is on top
    private boolean isBottom(){return (zeroY==(N-1));}	//returns true if 0 is on bottom
    private boolean isLeft(){return (zeroX==0);}		//returns true if 0 is on left side
    private boolean isRight(){return (zeroX==(N-1));}	//returns true if 0 is on right side
    
    //switchTile clones tiles[][], and switches the value at zeroX,zeroY with value at xSwitch,ySwitch
    private int[][] switchTile(int xSwitch, int ySwitch)
    {
    	int[][] returnValue = new int[N][N]; //make a copy of tiles
    	
    	for (int i = 0; i <tiles.length;i++)
    	{
    		for (int j = 0; j<tiles[1].length; j++)
    		{
    			returnValue[i][j]=tiles[i][j];
    		}
    	}
    	
    	returnValue[zeroX][zeroY] = returnValue[xSwitch][ySwitch]; //copy value of xSwitch ySwitch into position of zeroX,zeroY
    	returnValue[xSwitch][ySwitch]=0;	// copy value of 0 into position xSwitch, ySwitch
    	return returnValue;
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors() 
    {		
    	neighborsIterator neighbors = new neighborsIterator();
    	if (!isTop()){
    		neighbors.push(new Board(switchTile(zeroX-1, zeroY))); //switch zero with tile above it
    	}
    	if (!isBottom()){
    		neighbors.push(new Board(switchTile(zeroX+1, zeroY)));//switch zero with tile below it
    	}
    	if (!isLeft()){
    		neighbors.push(new Board(switchTile(zeroX, zeroY-1)));//switch zero with tile to the left of it
    	}
    	if (!isRight()){
    		neighbors.push(new Board(switchTile(zeroX, zeroY+1)));//switch zero with tile to the right of it
    	}
    	
    	return neighbors;    	
    }
    
    private class neighborsIterator <Board> implements Iterable<Board>
    {
    	private Node firstNode;
    	
    	private class Node
    	{
    		Board item;
    		Node next;
    	}
    	
    	public Iterator<Board> iterator()  
    	{
    		return new ListIterator();
    	}
    	
    	private void push(Board item)
    	{
    		Node oldFirst = firstNode;
    		firstNode = new Node();
    		firstNode.item = item;
    		firstNode.next = oldFirst;
    	}
    	
    	/**
    	 * Helper iterator that returns the stack
    	 */
    	private class ListIterator implements Iterator<Board>
    	{
    		private Node current = firstNode;
            public boolean hasNext()  { return current != null;                     }
//            public void remove()      { throw new UnsupportedOperationException();  }

            public Board next() {
                if (!hasNext()) throw new NoSuchElementException();
                Board item = current.item;
                current = current.next; 
                return item;
            }
    	}
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
    
    //loads oneDArray with current tiles, but omits the 0 for inversion factoring
    //loads Y value of 0 into zeroY, loads X value of 0 into zeroX
    private void load1DArray()
    {
    	//load oneDArray
    	oneDArray = new int[size()-1]; //minus 1 to account for skipping the 0
    	int pointer = 0;
    	for (int i = 0; i<N; i++)
    	{
    		for (int j=0; j<N; j++)
    		{
    			//load the array, but skip if the value==0
    			if (tiles[i][j]!=0)
    			{
    				oneDArray[pointer++]=tiles[i][j];
    			}else
    			{
    				zeroY = i; 	//Set Y value of 0 point
    				zeroX = j;	//Set X value of 0 point
    			}
    		}
    	}
    }
    
    //calculate the number of inversions
    private int inversions(){

    	int high = oneDArray.length-1; 	//size of the largest int on the board (-1 to account for the 0)
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
//    	int[][] testArray = {{1,2,3},{4,0,6},{7,8,5}};	//create 3 by 3 array
////    	int[][] testArray = {{1,2,3,4},{5,6,7,8},{9,10,15,11},{13,14,0,12}};	//create 4 by 4 array
//    	Board testBoard = new Board(testArray);
//    	System.out.println("Size: " + testBoard.size());	//test size method
//    	
//    	System.out.print("inversions:");
//    	System.out.println(testBoard.inversions());
//    	System.out.println("Zero Row: " + testBoard.zeroY);
//    	System.out.println("Solvable: " + testBoard.isSolvable());  
//    	System.out.println(testBoard);
//    	System.out.println("Zero at location: (" + testBoard.zeroX + "," + testBoard.zeroY + ")");
//    	
//    	//testing equals()
//    	Board testBoard2 = new Board(testArray);
//    	System.out.println("Testing Equals on equal boards:");
//    	System.out.println(testBoard.equals(testBoard2));
//    	
//    	
//    	//testing equals()
//    	int[][] testArray3 = {{1,2,3},{8,6,7},{4,5,0}};
//    	Board testBoard3 = new Board(testArray3);
//    	System.out.println("Testing Equals on NOT equal boards:");
//    	System.out.println(testBoard.equals(testBoard3));
//    	
//    	//test border checks
//    	System.out.println("Top:" + testBoard.isTop());
//    	System.out.println("Bottom:" + testBoard.isBottom());
//    	System.out.println("Left:" + testBoard.isLeft());
//    	System.out.println("Right:" + testBoard.isRight());
    	
    	//Testing iterator
    	System.out.println("Testing neighbors");
    	int[][] testArray4 = {{1,2,3},{4,0,6},{7,8,5}};	//create 3 by 3 array
		Board testBoard4 = new Board(testArray4);

		System.out.println("Original:");
		System.out.println(testBoard4);
		System.out.println("Neighbors:");
		

		for (Board m : testBoard4.neighbors()){
			System.out.println(m.toString());
		}
		System.out.println("Original:");
		System.out.println(testBoard4);
    	
    }//end of main
}//end of Board
