package de.hhn.se.labsw.gdt.library;

import java.util.ArrayList;

/**
 * Class representing the current state of a game.
 * 
 * @author Maximilian Roeck
 *
 */
public class GameState {
	
	/**
	 * ArrayList of all the players participating in the game.
	 */
	protected ArrayList<Player> players;
	/**
	 * 3-dimensional integer array representing the map of the game (game field/stage).
	 * The innermost array holds the actual information about the cell.
	 * A cell is the equivalent of a hole.
	 * 
	 * The first slot of the innermost array indicates the status of occupancy of the cell.
	 * Valid values are {0,1,2,3,4,5}.
	 * 0 = not occupied/empty cell
	 * 1 = occupied by player 1
	 * 2 = occupied by player 2
	 * 3 = occupied by player 3
	 * 4 = occupied by player 4
	 * 5 = occupied by the excavator
	 * 
	 * The second slot of the innermost array holds information regarding the depth of the cell.
	 * Only positive values are valid.
	 * A value of 1 indicates a cell of depth 1 below ground floor etc.
	 */
	protected int[][][] map;
	/**
	 * The host of the game represented by a User object.
	 */
	protected User host;
	/**
	 * 2-dimensional integer array indicating the status of currently active phase and round of the 
	 * game in relation to the maximum round limit set for each phase.
	 * Inner arrays indicate phases. The number of inner arrays equates to the max number of phases.
	 * The first slot in an inner array holds either 0 or the currently active round within the phase. 
	 * A "0" indicates that the phase is not currently active, a "-1" indicates that the phase is over.
	 * The second slot in an inner array holds an integer value indicating the max number of rounds within the phase.
	 * 
	 * {{-1,4},{2,3},{0,3}} = 2nd phase, 2nd round, with a maximum of 3 rounds in phase 2
	 */
	protected int[][] phaseRoundStatus;
	
}
