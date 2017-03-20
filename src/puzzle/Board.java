package puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
	
	private int[] tiles;
	

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
    	
    }
    
   private int[][] node(int[] orignialArray) {
	
	   
	   return null;
	   
   }
    
    // board size N
    public int size() {
		return tiles.length;
    	
    }
    
    // number of blocks out of place
    public int hamming() {
		return 0;
    	
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
    	
    	
		return 0;
    	
    }
    
    // is this board the goal board?   
    public boolean isGoal() {
		return hamming() == 0;
    	
    }
    
    // is this board solvable?
    public boolean isSolvable() {
    	
    	if (size() % 2 != 0) {
    		// inversions are even its true
    	} else {
    		// inversions plus location row of 0 is odd true
    	} 
    		
		return false;
    	
    }
    
	// does this board equal y?
    @Override
    public boolean equals(Object y) {
    	
    	
		return false;
    
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors() {
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
    
    // unit tests (not graded)
    public static void main(String[] args) {

    }
}






	
	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	
    }
    
    // min number of moves to solve initial board
    public int moves() {
		return 0;
    	
    }
    
	// sequence of boards in a shortest solution
    public Iterable<Board> solution() {
		return null;
    
    }
    
	// solve a slider puzzle (given below) 
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

        // if not, report unsolvable
        else {
            StdOut.println("Unsolvable puzzle");
        }
        
    }
