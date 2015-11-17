// Name:Yaqiao Deng
// USC loginid: yaqiaode
// CS 455 PA3
// Fall 2014

import java.util.LinkedList;

/**
 * Maze class
 * 
 * Stores information about a maze and can find a path through the maze (if
 * there is one).
 * 
 * Assumptions about structure of the mazeData (given in constructor), and the
 * path: -- no outer walls given in mazeData -- search assumes there is a
 * virtual border around the maze (i.e., the maze path can't go outside of the
 * maze boundaries) -- start location for a path is maze coordinate
 * (START_SEARCH_ROW, START_SEARCH_COL) (constants defined below) -- exit loc is
 * maze coordinate (numRows()-1, numCols()-1) (methods defined below) --
 * mazeData input 2D array of 0's and 1's (see public FREE / WALL constants
 * below) where the first index indicates the row. e.g., mazeData[row][col] --
 * only travel in 4 compass directions (no diagonal paths) -- can't travel
 * through walls
 */

public class Maze {

	public static final int START_SEARCH_COL = 0;
	public static final int START_SEARCH_ROW = 0;

	public static final int FREE = 0;
	public static final int WALL = 1;
	private int IsVisited = -1; // a special value which marks the visited path
	private int RowNum;
	private int ColNum;
	public int[][] mazeData; // create a 2D array
	private LinkedList<MazeCoord> path; // create a Linkedlist which stores the path MazeCoord

	/**
	 * Constructs a maze.
	 * 
	 * @param mazeData
	 *            the maze to search. See Maze class comments, above, for what
	 *            goes in this array.
	 */
	public Maze(int[][] mazeData) {
		this.mazeData = mazeData;
		RowNum = mazeData.length;
		ColNum = mazeData[0].length;
		path = new LinkedList<MazeCoord>();

	}

	/**
	 * Returns the number of rows in the maze
	 * 
	 * @return number of rows
	 */
	public int numRows() {

		return RowNum; // DUMMY CODE TO GET IT TO COMPILE
	}

	/**
	 * Returns the number of columns in the maze
	 * 
	 * @return number of columns
	 */
	public int numCols() {
		return ColNum; // DUMMY CODE TO GET IT TO COMPILE
	}

	/**
	 * Gets the maze data at loc.
	 * 
	 * @param loc
	 *            the location in maze coordinates
	 * @return the value at that location. One of FREE and WALL PRE: 0 <=
	 *         loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
	 */
	public int getValAt(MazeCoord loc) {

		int i = loc.getRow();
		int j = loc.getCol();

		return mazeData[i][j]; // DUMMY CODE TO GET IT TO COMPILE

	}

	/**
	 * Returns the path through the maze. First element is starting location,
	 * and last element is exit location. If there was not path, or if this is
	 * called before search, returns empty list.
	 * 
	 * @return the maze path
	 */
	public LinkedList<MazeCoord> getPath() {

		return path;
	} // DUMMY CODE TO GET IT TO COMPILE

	/**
	 * Find a path through the maze if there is one.
	 * 
	 * @return whether path was found.
	 */
	public boolean search() {

		return helper(START_SEARCH_ROW, START_SEARCH_COL); // DUMMY CODE TO GET IT TO COMPILE

	}

	/**
	 * A helper method which does the actual recursion It makes a copy of the
	 * original mazeData which only has values of 0 and 1.
	 * 
	 * @param r
	 *            the row of the mazeData
	 * @param c
	 *            the col of the mazeData
	 * @return whether path was found
	 */
	private boolean helper(int r, int c) {
		int[][] used = new int[mazeData.length][mazeData[0].length];
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[0].length; j++) {
				used[i][j] = mazeData[i][j];
			}
		}
		return helper(r, c, used);
	}

	/**
	 * @param used
	 *            the visited mazeData which could have special values other
	 *            than 0 and 1
	 */
	private boolean helper(int r, int c, int[][] used) {
		boolean exitFound = false; // a boolean which indicates the state of the recursion of the search method
		

		if (r>=START_SEARCH_ROW&&c>=START_SEARCH_COL&&r<used.length&&c<used[0].length&&used[r][c]==FREE) {

			if (r==used.length-1&&c==used[0].length-1) {
				exitFound = true;
				if (mazeData[mazeData.length-1][mazeData[0].length-1]==FREE) {
					path.add(new MazeCoord(mazeData.length-1,mazeData[0].length - 1));
				}
			} else {
				used[r][c] = IsVisited;

				boolean flag = helper(r+1,c,used) || helper(r-1,c,used)
						|| helper(r,c+1,used) || helper(r,c-1,used);
				if (flag) {
					path.addFirst(new MazeCoord(r, c)); // add the MazeCoord to path if it returns true in the recursion

				}

				return flag;

			}

		}

		return exitFound;

	}

}
