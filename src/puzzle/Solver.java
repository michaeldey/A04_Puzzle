package puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver{

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) 
	{
		
	}

	// min number of moves to solve initial board
	public int moves() 
	{
		return 0;
		
	}

	// sequence of boards in a shortest solution
	public Iterable<Board> solution() 
	{
		return null;
	
	}

	// solve a slider puzzle (given below) 
	public static void main(String[] args) 
	{

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
	}//end of main
    
}//end of Solver

