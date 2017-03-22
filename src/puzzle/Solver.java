package puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class Solver{
	private Stack<Board> solution;
	private int moves;
	
	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	
    	if (initial.isSolvable() == false) throw new IllegalArgumentException();
		solution = new Stack<>();
		MinPQ<Move> q = new MinPQ<>();
		q.insert(new Move(initial, 0, null));
		while(true){
			Move move = q.delMin();
			if(move.board.isGoal()){ //goal has been reached, populate fields of interest and return
				this.moves = move.moves;
				do{
					solution.push(move.board);
					move = move.parent;
				}
				while(move != null);
				return; //done solving
			}
			for(Board next : move.board.neighbors()){
				if(move.parent == null || !next.equals(move.parent.board)) //look back one move to prevent useless looping
					q.insert(new Move(next, move.moves+1, move));
			}
		}
    	
    }
    
    // min number of moves to solve initial board
    public int moves() {
    	  
          return moves;
    	
    }
    
	// sequence of boards in a shortest solution
    public Iterable<Board> solution() {
		return solution;
    
    }
    
    
    private class Move implements Comparable<Move>{
		private Board board;
		private int moves;
		private Move parent;
		
		public Move(Board board, int moves, Move parent){
			this.board = board;
			this.moves = moves;
			this.parent = parent;
		}

		@Override
		public int compareTo(Move o) {
			int difference = this.board.manhattan() + this.moves - o.board.manhattan() - o.moves;
			if (difference != 0) return difference; //return normal difference of priority functions
			if (this.moves > o.moves) return -1; //if priority is the same give preference to the one with more moves
			return 1;
		}
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
}

