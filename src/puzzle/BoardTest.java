/**
 * @author Chad Seale, Michael Dey
 * CSIS2420-004-Sp17 - A04 - 8 Puzzle 
 */
package puzzle;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BoardTest {

	@Rule public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testEmptyConstructor(){
		thrown.expect(java.lang.Error.class);
		thrown.expectMessage("Blocks cannot be null");
		Board emptyBoard = new Board(null);
	}
	
	@Test
	public void testEmptyArray(){
		thrown.expect(java.lang.Error.class);
		thrown.expectMessage("Blocks cannot be empty");
		Board emptyBoard = new Board(new int[0][0]);
	}
	
	@Test
	public void testIncorrectSizeArray(){
		int[][] wrongSize = new int[][]{{0,1,2},{3,4}};
		thrown.expect(java.lang.Error.class);
		thrown.expectMessage("Puzzle width must match Puzzle height");
		Board emptyBoard = new Board(wrongSize);
	}

	@Test
	public void testSize() {
		int[][] testArray1 = {{1,2,3},{4,0,6},{7,8,5}};	//create 3 by 3 array
		Board sizeTest = new Board(testArray1);
		int stSize = sizeTest.size();
		assertTrue(stSize==9); 			//testArray1.size should == 9
		assertFalse(stSize!=9);			//test that we aren't getting a false true
	}

	@Test
	public void testHamming() {
		int[][] hamming5 = new int[][]{{8,1,3},{4,0,2},{7,6,5}};
		Board hammingFive = new Board(hamming5);	//create a board with a known Hamming value of 5
		assertTrue(hammingFive.hamming()==5);		//test hamming value comes back as 5
		assertFalse(hammingFive.hamming()!=5);		//test hamming value doesn't come back as anything BUT 5
	}

	@Test
	public void testManhattan() {
		int[][] manhatten10 = new int[][]{{8,1,3},{4,0,2},{7,6,5}};
		Board man10 = new Board(manhatten10);	//create a board with a known Hamming value of 10
		assertTrue(man10.manhattan()==10);		//test manhatten value comes back as 10
		assertFalse(man10.manhattan()!=10);		//test manhatten value doesn't come back as anything BUT 10
	}

	@Test
	public void testIsGoal() {
		//test board already in order
		int[][] instantGoal = new int[][]{{1,2,3},{4,5,6},{7,8,0}};
		Board goalBoard = new Board(instantGoal);
		assertTrue(goalBoard.isGoal());
		
		//test board NOT in order
		int[][] notGoal = new int[][]{{1,2,3},{4,0,6},{7,8,5}};
		Board notGoalBoard = new Board(notGoal);
		assertFalse(notGoalBoard.isGoal());
	}

	@Test
	public void testIsSolvable_OddSizeOddInversions() {
		//test ODD board size with ODD inversions (unsolvable)
		int[][] oddSize = new int[][]{{1,2,3},{4,5,6},{8,7,0}};
		Board unsolvable1 = new Board(oddSize);
		assertFalse(unsolvable1.isSolvable());	
		assertTrue(!unsolvable1.isSolvable()); //test we don't have a false positive
	}

	@Test
	public void testIsSolvable_OddSizeEvenInversions() {
		//test ODD board size with EVEN inversions (solvable)
		int[][] oddSize = new int[][]{{1,2,3},{4,0,5},{7,8,6}};
		Board solvable1 = new Board(oddSize);
		assertTrue(solvable1.isSolvable());	
		assertFalse(!solvable1.isSolvable()); //test we don't have a false positive
	}
	
	@Test
	public void testIsSolvable_EvenSizeOddInversions() {
		//test EVEN board size with ODD (inversions+blank row position) (solvable)
		int[][] evenSize = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,0},{13,14,15,12}};
		Board solvable2 = new Board(evenSize);
		assertTrue(solvable2.isSolvable());	
		assertFalse(!solvable2.isSolvable()); //test we don't have a false positive
	}
	@Test
	public void testIsSolvable_EvenSizeEvenInversions() {
		//test EVEN board size with EVEN (inversions+blank row position) (unsolvable)
		int[][] evenSize = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,15,14,0}};
		Board unsolvable2 = new Board(evenSize);
		assertFalse(unsolvable2.isSolvable());	
		assertTrue(!unsolvable2.isSolvable()); //test we don't have a false positive
	}
	
	@Test
	public void testEqualsObject() {
		int[][] testArray1 = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,15,14,0}};
		Board testBoard1 = new Board(testArray1);
		
		//create a Board that is equal to testBoard1
		int[][] testArray2 = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,15,14,0}};
		Board testBoard2 = new Board(testArray2);
		
		//create a Board that is not equal to testBoard1
		int[][] testArray3 = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,15,0,14}};
		Board testBoard3 = new Board(testArray3);
		
		assertTrue(testBoard1.equals(testBoard2));
		assertFalse(!testBoard1.equals(testBoard2));
		
		assertFalse(testBoard1.equals(testBoard3));
		assertTrue(!testBoard1.equals(testBoard3));
	}

	@Test
	public void testNeighbors() {
		fail("Not yet implemented");
	}


}
