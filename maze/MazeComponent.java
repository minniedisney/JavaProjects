// Name:Yaqiao Deng
// USC loginid: yaqiaode
// CS 455 PA3
// Fall 2014

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;

/**
 * MazeComponent class
 * 
 * A component that displays the maze and path through it if one has been found.
 */
public class MazeComponent extends JComponent {

	private static final int START_X = 10; // where to start drawing maze in
											// frame
	private static final int START_Y = 10; // (i.e., upper-left corner)

	private static final int BOX_WIDTH = 20; // width and height of one maze
												// unit
	private static final int BOX_HEIGHT = 20;
	private Maze maze; // create a Maze object

	/**
	 * Constructs the component.
	 * 
	 * @param maze
	 *            the maze to display
	 */
	public MazeComponent(Maze maze) {
		this.maze = maze;

	}

	/**
	 * Draws the current state of maze including the path through it if one has
	 * been found.
	 * 
	 * @param g
	 *            the graphics context
	 */
	public void paintComponent(Graphics g) {

		g.setColor(Color.black);

		g.drawRect(START_X, START_Y, maze.numCols() * BOX_WIDTH, maze.numRows()* BOX_HEIGHT);
		if (maze.mazeData[maze.numRows() - 1][maze.numCols() - 1] == maze.FREE) {
			g.setColor(Color.green);
			g.fillRect(START_X + (maze.numCols() - 1) * BOX_WIDTH, START_Y
					+ (maze.numRows() - 1) * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
		}
		for (int i = 0; i < maze.numRows(); i++) { // if there is a wall(1), it will draw a black-filled rectangular
			for (int j = 0; j < maze.numCols(); j++) {
				if (maze.mazeData[i][j] == maze.WALL) {

					g.setColor(Color.black);
					g.fillRect(START_X + j * BOX_WIDTH, START_Y + i * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);

				}
			}
		}


		LinkedList<MazeCoord> path = new LinkedList<MazeCoord>();
		path = maze.getPath();
		ListIterator<MazeCoord> iter = path.listIterator();
		if (iter.hasNext()) {

			MazeCoord next1 = iter.next();
			if (iter.hasNext()) {
				MazeCoord next2 = iter.next();
				int firststepcol = next2.getCol();
				int firststeprow = next2.getRow();
				g.setColor(Color.blue);
				int startLocX = START_X + BOX_WIDTH / 2;
				int startLocY = START_Y + BOX_HEIGHT / 2;
				g.drawLine(startLocX, startLocY, startLocX + BOX_WIDTH * firststepcol, 
						startLocY + BOX_HEIGHT * firststeprow);
				while (iter.hasNext()) {
					next1 = next2;
					next2 = iter.next();
					int x1 = next1.getCol();
					int y1 = next1.getRow();

					int x2 = next2.getCol();
					int y2 = next2.getRow();

					g.drawLine(startLocX + BOX_WIDTH * x1, startLocY+ BOX_HEIGHT * y1, 
							startLocX + BOX_WIDTH * x2,startLocY + BOX_HEIGHT * y2);

				}
				path.clear(); // remove all the elements in the path linkedlist after drawing the path

			}

		}

	}

}
