package de.hhn.se.labsw.gdt.library;

import java.util.ArrayList;

/**
 * Class representing a game of "Grab dich Tief". 
 * The class is supposed to be used as the model in a MVC architecture.
 * Functionality that drives the game logic is supposed to be implemented through other classes.
 * 
 * @author Maximilian Roeck
 *
 */
public class Game extends GameState implements GameDefaults {
	
	/**
	 * Adjustable value for the number of rows. Initialized with the default value.
	 */
	private int rows = ROWS_DEFAULT;
	/**
	 * Adjustable value for the number of columns. Initialized with the default value.
	 */
	private int columns = COLUMNS_DEFAULT;
	/**
	 * Adjustable mapping for maximum number of rounds per phase.
	 */
	private int[] phaseRound = PHASE_ROUND_DEFAULTS;
	/**
	 * Default constructor.
	 */
	public Game() {
		
		map = new int[rows][columns][CELL_PROPERTIES];
		players = new ArrayList<Player>();
		
		// Setting up the initial state of phases and rounds when the game is started.
		// The length of phaseRound indicates the max number of phases.
		// The inner array needs 2 slots. The first indicating the current round, the second the round limit per phase.
		phaseRoundStatus = new int[phaseRound.length][2];
		for (int phase = 0; phase < phaseRound.length; phase++) {
			// The game always starts with round 1 in phase 1.
			phaseRoundStatus[phase][0] = (phase == 0) ? 1 : 0;
			// The round limit is set for each phase.
			phaseRoundStatus[phase][1] = phaseRound[phase];
		}
	}
	/**
	 * Constructor for greater customization.
	 * 
	 * @param rows
	 * 			the vertical number of cells of the map
	 * @param columns
	 * 			the horizontal number of cells of the map
	 * @param phaseRound
	 * 			round limits for each phase
	 * @param host
	 * 		  	the host of the game
	 */
	public Game(int rows, int columns, int[] phaseRound, User host) {
		
		this.rows = rows;
		this.columns = columns;
		this.phaseRound = phaseRound;
		this.host = host;
		
		map = new int[rows][columns][CELL_PROPERTIES];
		players = new ArrayList<Player>();
		
		// Setting up the initial state of phases and rounds when the game is started.
		// The length of phaseRound indicates the max number of phases.
		// The inner array needs 2 slots. The first indicating the current round, the second the round limit per phase.
		phaseRoundStatus = new int[phaseRound.length][2];
		for (int phase = 0; phase < phaseRound.length; phase++) {
			// The game always starts with round 1 in phase 1.
			phaseRoundStatus[phase][0] = (phase == 0) ? 1 : 0;
			// The round limit is set for each phase.
			phaseRoundStatus[phase][1] = phaseRound[phase];
		}
	}
	/**
	 * Constructor accepting the host as a parameter.
	 * 
	 * @param host
	 * 		  	the host of the game
	 */
	public Game(User host) {
		
		this.host = host;
		
		map = new int[rows][columns][CELL_PROPERTIES];
		players = new ArrayList<Player>();
		
		// Setting up the initial state of phases and rounds when the game is started.
		// The length of phaseRound indicates the max number of phases.
		// The inner array needs 2 slots. The first indicating the current round, the second the round limit per phase.
		phaseRoundStatus = new int[phaseRound.length][2];
		for (int phase = 0; phase < phaseRound.length; phase++) {
			// The game always starts with round 1 in phase 1.
			phaseRoundStatus[phase][0] = (phase == 0) ? 1 : 0;
			// The round limit is set for each phase.
			phaseRoundStatus[phase][1] = phaseRound[phase];
		}
	}
	/**
	 * Method for retrieving the number of the map's rows.
	 * 
	 * @return the number of rows of the map
	 */
	public int getRows() {
		return this.rows;
	}
	/**
	 * Method for updating the number of the map's rows.
	 * 
	 * @param rows
	 * 			the updated number of rows of the map
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * Method for retrieving the number of the map's columns.
	 * 
	 * @return the number of columns of the map
	 */
	public int getColumns() {
		return columns;
	}
	/**
	 * Method for updating the number of the map's columns.
	 * 
	 * @param columns
	 * 			the updated number of columns of the map
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}
	/**
	 * Method that returns an ArrayList of the game's players.
	 * 
	 * @return ArrayList of players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	/**
	 * Method for retrieving individual players by the number.
	 * @param player
	 * 			the number of the player to be returned
	 * @return
	 * 			the player with the given number
	 */
	public Player getPlayer(int player) {
		if (player < 1 || player > players.size()) {
			return null;
		} else {
			for (Player p : players) {
				if (p.getNumber() == p) {
					return p;
				}
			}
			return null;
		}
	}
	/**
	 * Method used to add new players to a game.
	 * 
	 * @param player
	 * 			the player to be added to the game
	 */
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	/**
	 * Method that returns the map of the game.
	 * 
	 * @return the game's map
	 */
	public int[][][] getMap() {
		return map;
	}
	/**
	 * Method for updating the game's map.
	 * 
	 * @param row
	 * 			the row number of the cell to be updated
	 * 
	 * @param column
	 * 			the column number of the cell to be updated
	 * 
	 * @param occupancy
	 * 			the updated occupancy of the cell
	 * 
	 * @param depth
	 * 			the updated depth of the cell
	 */
	public void updateMap(int row, int column, int occupancy, int depth) {
		this.map[row][column][0] = occupancy;
		this.map[row][column][1] = depth;
	}
	/**
	 * Method for retrieving cell information.
	 * 
	 * @param row
	 * 			the row of the cell
	 * @param column
	 * 			the column of the cell
	 * @return the cell's value
	 */
	public int[] getCell(int row, int column) {
		return this.map[row][column];
	}
	/**
	 * Method for retrieving the game's host.
	 * 
	 * @return the host of the game
	 */
	public User getHost() {
		return host;
	}
	/**
	 * Method for setting the game's host.
	 * 
	 * @param host
	 * 			the host of the game
	 */
	public void setHost(User host) {
		this.host = host;
	}
}

